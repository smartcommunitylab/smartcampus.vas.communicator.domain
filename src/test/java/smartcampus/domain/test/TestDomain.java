package smartcampus.domain.test;

import it.sayservice.platform.client.InvocationException;
import it.sayservice.platform.client.ServiceBusClient;
import it.sayservice.platform.client.jms.JMSServiceBusClient;
import it.sayservice.platform.core.domain.DomainObject;
import it.sayservice.platform.core.message.Core.DODataRequest;
import it.sayservice.platform.core.message.Core.DomainEvent;
import it.sayservice.platform.domain.test.DomainListener;
import it.sayservice.platform.domain.test.DomainTestHelper;

import java.util.List;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;

import smartcampus.services.communicator.AbstractFunnelDOEngine;
import smartcampus.services.communicator.AbstractFunnelFactoryDOEngine;
import smartcampus.services.communicator.GetNewsDOEngine;
import smartcampus.services.communicator.JourneyPlannerFunnelDOEngine;
import smartcampus.services.communicator.JourneyPlannerFunnelFactoryDOEngine;
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
			      // DO someth...
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
						  new UnitnFunnelFactoryDOEngine()
				);

				try {
					List<DomainObject> factories = helper.getDOByType("smartcampus.services.communicator.AbstractFunnelFactory");
					System.err.println(factories);
				} catch (InvocationException e1) {
					e1.printStackTrace();
				}

	}

}
