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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;

import smartcampus.services.communicator.domain.AbstractFeedDOEngine;
import smartcampus.services.communicator.domain.AbstractSourceDOEngine;
import smartcampus.services.communicator.domain.JourneyPlannerSourceDOEngine;
import smartcampus.services.communicator.domain.JourneyPlannerSourceFactoryDOEngine;
import smartcampus.services.communicator.domain.SocialNewsServiceDOEngine;
import smartcampus.services.communicator.domain.SocialSourceDOEngine;


public class TestDomain {

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
						  new SocialSourceDOEngine(),
						  new SocialNewsServiceDOEngine()
				);

				DomainObject o = helper.getDOById("smartcampus.services.communicator.SocialSourceFactory", "smartcampus.services.communicator.SocialSourceFactory.0");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("userId", "37");
				params.put("userSocialId", "396");
				helper.invokeDOOperation(o.getType(), o.getId(), "createDefaultSource", params );

				adminClient.restartService("eu.trentorise.smartcampus.services.social.SocialService", "GetTopicNews");
				
				System.err.println();
	}

}
