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
import it.sayservice.platform.core.message.Core.DODataRequest;
import it.sayservice.platform.core.message.Core.DomainEvent;
import it.sayservice.platform.domain.test.DomainListener;
import it.sayservice.platform.domain.test.DomainTestHelper;

import java.util.List;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;

import eu.trentorise.smartcampus.domain.communicator.AbstractFeedDOEngine;
import eu.trentorise.smartcampus.domain.communicator.AbstractSourceDOEngine;
import eu.trentorise.smartcampus.domain.communicator.CiscaFeedDOEngine;
import eu.trentorise.smartcampus.domain.communicator.CiscaNewsDOEngine;
import eu.trentorise.smartcampus.domain.communicator.OperaFeedDOEngine;
import eu.trentorise.smartcampus.domain.communicator.OperaNewsDOEngine;
import eu.trentorise.smartcampus.domain.communicator.UnitnDataNewsDOEngine;
import eu.trentorise.smartcampus.domain.communicator.UnitnFeedDOEngine;



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
						  new CiscaFeedDOEngine(),
						  new CiscaNewsDOEngine(),
						  new OperaFeedDOEngine(),
						  new OperaNewsDOEngine(),
						  new UnitnDataNewsDOEngine(),
						  new UnitnFeedDOEngine()
				);

				System.err.println();
	}

}
