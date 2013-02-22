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
package eu.trentorise.smartcampus.domain.communicator.helpers;

import it.sayservice.platform.smartplanner.data.message.alerts.Alert;
import it.sayservice.platform.smartplanner.data.message.alerts.AlertDelay;
import it.sayservice.platform.smartplanner.data.message.alerts.AlertParking;
import it.sayservice.platform.smartplanner.data.message.alerts.AlertStrike;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import eu.trentorise.smartcampus.domain.communicator.beans.EntityObject;
import eu.trentorise.smartcampus.domain.communicator.beans.Notification;
import eu.trentorise.smartcampus.domain.communicator.beans.NotificationAuthor;

public class JourneyPlannerHelper {

	public static Notification[] buildNotification(Alert alert, String funnelId, String clientId, String name) {
		List<Notification> result = new ArrayList<Notification>();

		if (alert instanceof AlertDelay) {
			Notification not = buildNotification(alert, funnelId, "Delay", clientId, name);
			if (not != null) {
				result.add(not);
			}
		} else if (alert instanceof AlertStrike) {
			Notification not = buildNotification(alert, funnelId, "Strike", clientId, name);
			if (not != null) {
				result.add(not);
			}
		} else if (alert instanceof AlertParking) {
			Notification not = buildNotification(alert, funnelId, "Parking", clientId, name);
			if (not != null) {
				result.add(not);
			}
		}
		return (result.size() == 0) ? null : (Notification[]) result.toArray(new Notification[result.size()]);
	}

	private static Notification buildNotification(Alert alert, String funnelId, String title, String clientId, String name) {
		Notification not = new Notification();
		not.setTitle(title + " Alert for journey '" + name + "'");
		not.setDescription(alert.getNote());

		List<EntityObject> eos = new ArrayList<EntityObject>();
		EntityObject eo = new EntityObject();
		eo.setId(clientId);
		eo.setType("journey");
		eo.setTitle(name);
		eos.add(eo);

		not.setEntities(eos);
		
		Map<String, Object> content = buildContent(alert);
		not.setContent(content);

		NotificationAuthor author = new NotificationAuthor();
		author.setName("Journey Planner - " + title);
		not.setAuthor(author);

		return not;
	}

	private static Map<String, Object> buildContent(Alert alert) {
		Map<String, Object> content = new TreeMap<String, Object>();
		if (alert instanceof AlertDelay) {
			content.put("type", "alertDelay");
			content.put("agencyId", ((AlertDelay) alert).getTransport().getAgencyId());
			content.put("routeId", ((AlertDelay) alert).getTransport().getRouteId());
			content.put("tripd", ((AlertDelay) alert).getTransport().getTripId());
			content.put("delay", ((AlertDelay) alert).getDelay());
		} else if (alert instanceof AlertStrike) {
			content.put("type", "alertStrike");
			content.put("agencyId", ((AlertStrike) alert).getTransport().getAgencyId());
			content.put("routeId", ((AlertStrike) alert).getTransport().getRouteId());
			content.put("tripd", ((AlertStrike) alert).getTransport().getTripId());
			content.put("stopId", ((AlertStrike) alert).getStop().getId());
		} else if (alert instanceof AlertParking) {
			content.put("type", "alertParking");
			content.put("agencyId", ((AlertParking) alert).getPlace().getAgencyId());
			content.put("stopId", ((AlertParking) alert).getPlace().getId());
			content.put("placesAvailable", ((AlertParking) alert).getPlacesAvailable());
		}
		content.put("from", alert.getFrom());
		content.put("to", alert.getTo());

		return content;
	}

}
