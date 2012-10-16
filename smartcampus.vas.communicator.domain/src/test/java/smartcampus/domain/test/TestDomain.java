package smartcampus.domain.test;

import it.sayservice.platform.client.InvocationException;
import it.sayservice.platform.client.ServiceBusClient;
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

import smartcampus.services.communicator.AbstractFunnelDOEngine;
import smartcampus.services.communicator.AbstractFunnelFactoryDOEngine;
import smartcampus.services.communicator.GetNewsDOEngine;
import smartcampus.services.communicator.JourneyPlannerFunnelDOEngine;
import smartcampus.services.communicator.JourneyPlannerFunnelFactoryDOEngine;
import smartcampus.services.communicator.SocialFunnelDOEngine;
import smartcampus.services.communicator.SocialFunnelFactoryDOEngine;
import smartcampus.services.communicator.UnitnFunnelDOEngine;
import smartcampus.services.communicator.UnitnFunnelFactoryDOEngine;


public class TestDomain {

	public static void main(String[] args) throws Exception {
		HornetQJMSConnectionFactory cf = 
			     new HornetQJMSConnectionFactory(false,
			                  new TransportConfiguration(
			                    "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory"));
			  ServiceBusClient client = new JMSServiceBusClient(cf);

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
						  new AbstractFunnelFactoryDOEngine(),
						  new AbstractFunnelDOEngine(),
						  new GetNewsDOEngine(),
						  new JourneyPlannerFunnelDOEngine(),
						  new JourneyPlannerFunnelFactoryDOEngine(),
						  new UnitnFunnelDOEngine(),
						  new UnitnFunnelFactoryDOEngine(),
						  new SocialFunnelDOEngine(),
						  new SocialFunnelFactoryDOEngine()
				);

				DomainObject o = helper.getDOById("smartcampus.services.communicator.SocialFunnelFactory", "smartcampus.services.communicator.SocialFunnelFactory.0");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", "someId");
				params.put("userId", "37");
				params.put("userSocialId", "396");
				params.put("title", "title");
				params.put("funnelData", "{\"topics\":[95],\"description\":\"Following LOS VIVANCOS in Extreme Flamenco\"}");
				helper.invokeDOOperation(o.getType(), o.getId(), "createFunnel", params );

				System.err.println();
	}

}
