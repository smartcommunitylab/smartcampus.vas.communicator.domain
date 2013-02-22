/*******************************************************************************
 * Copyright 2012-2013 Trento RISE
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/
package smartcampus.domain.test;

import it.sayservice.platform.client.ServiceBusAdminClient;
import it.sayservice.platform.client.ServiceBusClient;
import it.sayservice.platform.client.jms.JMSServiceBusAdminClient;
import it.sayservice.platform.client.jms.JMSServiceBusClient;
import it.sayservice.platform.core.domain.DomainObject;
import it.sayservice.platform.core.message.Core.DODataRequest;
import it.sayservice.platform.core.message.Core.DomainEvent;
import it.sayservice.platform.domain.test.DomainListener;
import it.sayservice.platform.domain.test.DomainTestHelper;
import it.sayservice.platform.smartplanner.data.message.SimpleLeg;
import it.sayservice.platform.smartplanner.data.message.TType;
import it.sayservice.platform.smartplanner.data.message.Transport;
import it.sayservice.platform.smartplanner.data.message.alerts.AlertDelay;
import it.sayservice.platform.smartplanner.data.message.alerts.AlertType;
import it.sayservice.platform.smartplanner.data.message.alerts.CreatorType;
import it.sayservice.platform.smartplanner.data.message.journey.RecurrentJourney;
import it.sayservice.platform.smartplanner.data.message.journey.RecurrentJourneyParameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bson.types.ObjectId;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;

import smartcampus.services.journeyplanner.AlertFactoryDOEngine;
import smartcampus.services.journeyplanner.ItineraryObjectDOEngine;
import smartcampus.services.journeyplanner.RecurrentJourneyFactoryDOEngine;
import smartcampus.services.journeyplanner.RecurrentJourneyObjectDOEngine;
import smartcampus.services.journeyplanner.TrainsAlertsSenderDOEngine;
import eu.trentorise.smartcampus.domain.communicator.AbstractFeedDOEngine;
import eu.trentorise.smartcampus.domain.communicator.AbstractSourceDOEngine;
import eu.trentorise.smartcampus.domain.communicator.JourneyPlannerSourceDOEngine;
import eu.trentorise.smartcampus.domain.communicator.JourneyPlannerSourceFactoryDOEngine;
import eu.trentorise.smartcampus.domain.communicator.SocialNewsServiceDOEngine;
import eu.trentorise.smartcampus.domain.communicator.SocialSourceDOEngine;

public class TestAlert {

	public static void main(String[] args) throws Exception {
		HornetQJMSConnectionFactory cf = 
			     new HornetQJMSConnectionFactory(false,
			                  new TransportConfiguration(
			                    "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory"));
			  ServiceBusClient client = new JMSServiceBusClient(cf);
			  
			  ServiceBusAdminClient adminClient = new JMSServiceBusAdminClient(cf);

			  DomainTestHelper helper = new DomainTestHelper(client,new DomainListener() {
			    public void onDomainEvents(List<DomainEvent> events) {
			    	System.err.println(events);
			    }

			    public void onDataRequest(DODataRequest req) {
			      // DO someth...
			    }
			  });

			  helper.cleanDomainData();

				helper.start(
						 new AbstractFeedDOEngine(),
						  new AbstractSourceDOEngine(),
						  new JourneyPlannerSourceDOEngine(),
						  new JourneyPlannerSourceFactoryDOEngine(),
						  new SocialSourceDOEngine(),
						  new SocialNewsServiceDOEngine(),						
						  new RecurrentJourneyFactoryDOEngine(),
						  new RecurrentJourneyObjectDOEngine(),
						  new ItineraryObjectDOEngine(),
						  new AlertFactoryDOEngine(),
						  new TrainsAlertsSenderDOEngine()
				);

				DomainObject o = helper.getDOById("smartcampus.services.journeyplanner.RecurrentJourneyFactory", "smartcampus.services.journeyplanner.RecurrentJourneyFactory.0");
				Map<String, Object> params = new HashMap<String, Object>();
				RecurrentJourney rj = buildRecurrentJourney();
				params.put("recurrentJourney", rj);
				params.put("name", "test");
				String clientId = new ObjectId().toString();
				params.put("clientId", clientId);
				params.put("userId", "-1");
				params.put("monitor", true);
				helper.invokeDOOperation(o.getType(), o.getId(), "saveRecurrentJourney", params );
				
				o = helper.getDOById("eu.trentorise.smartcampus.domain.communicator.JourneyPlannerSourceFactory", "eu.trentorise.smartcampus.domain.communicator.JourneyPlannerSourceFactory.0");
				params = new HashMap<String, Object>();
				params.put("userId", "-1");
				params.put("userSocialId", "42");				
				helper.invokeDOOperation(o.getType(), o.getId(), "createDefaultSource", params );				
				
				o = helper.getDOById("smartcampus.services.journeyplanner.AlertFactory", "smartcampus.services.journeyplanner.AlertFactory.0");
				params = new HashMap<String, Object>();
				AlertDelay alert = buildAlertDelay();
				params.put("newAlert", alert);
				helper.invokeDOOperation(o.getType(), o.getId(), "submitAlertDelay", params );
	}
	
	private static RecurrentJourney buildRecurrentJourney() {
		RecurrentJourney rj = new RecurrentJourney();
		SimpleLeg leg = new SimpleLeg();
		leg.setFrom("S.Francesco  Porta Nuova");
		leg.setTo("POVO  Piazza Manci");
		leg.setTransport(buildTransport());
		List<SimpleLeg> legs = new ArrayList<SimpleLeg>();
		legs.add(leg);
		rj.setLegs(legs);
		
		Map<String, Boolean> ml = new TreeMap<String, Boolean>();
		ml.put("12_5", true);
		rj.setMonitorLegs(ml);
		
		rj.setParameters(null);
		
		RecurrentJourneyParameters rjp = new RecurrentJourneyParameters();
		List<Integer> rec = new ArrayList<Integer>();
		rec.add(1);
		rec.add(2);
		rec.add(3);
		rec.add(4);
		rec.add(5);
		rec.add(6);
		rec.add(7);
		rjp.setRecurrence(rec);
		rj.setParameters(rjp);
		
		return rj;
	}
	
	private static Transport buildTransport() {
		Transport trans = new Transport();
		trans.setAgencyId("12");
		trans.setRouteId("5");
		trans.setTripId("05A-Feriale_056");
		trans.setType(TType.BUS);
		return trans;
	}
	
	private static AlertDelay buildAlertDelay() {
		AlertDelay delay = new AlertDelay();
		delay.setDelay(60000);
		delay.setType(AlertType.DELAY);
		delay.setTransport(buildTransport());
		delay.setCreatorType(CreatorType.USER);
		return delay;
	}
	

}
