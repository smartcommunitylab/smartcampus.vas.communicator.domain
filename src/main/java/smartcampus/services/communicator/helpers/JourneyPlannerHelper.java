package smartcampus.services.communicator.helpers;

import java.util.ArrayList;
import java.util.List;

import smartcampus.services.communicator.beans.EntityObject;
import smartcampus.services.communicator.beans.JourneyPlannerParameters;
import smartcampus.services.communicator.beans.Notification;
import smartcampus.services.communicator.beans.NotificationAuthor;
import smartcampus.smartplanner.data.message.alerts.Alert;
import smartcampus.smartplanner.data.message.alerts.AlertDelay;
import smartcampus.smartplanner.data.message.alerts.AlertParking;
import smartcampus.smartplanner.data.message.alerts.AlertStrike;

public class JourneyPlannerHelper {

	public static Notification[] buildNotification(Alert alert, String funnelId, JourneyPlannerParameters pars, String clientId, String name) {
		List<Notification> result = new ArrayList<Notification>();

		boolean all = true;
		if (pars.getSources() != null && pars.getSources().size() != 0) {
			all = false;
			if (!pars.getSources().contains(alert.getCreatorType().toString())) {
				return (Notification[]) result.toArray(new Notification[result.size()]);
			}
		}

		if (alert instanceof AlertDelay) {
			if (pars.getTypes().contains(JourneyPlannerParameters.DELAYS) || all) {
				Notification not = buildNotification(alert, funnelId, "Delay", clientId, name);
				result.add(not);
			}
		} else if (alert instanceof AlertStrike) {
			if (pars.getTypes().contains(JourneyPlannerParameters.STRIKES) || all) {
				Notification not = buildNotification(alert, funnelId, "Strike", clientId, name);
				result.add(not);
			}
		} else if (alert instanceof AlertParking) {
			if (pars.getTypes().contains(JourneyPlannerParameters.PARKINGS) || all) {
				Notification not = buildNotification(alert, funnelId, "Parking", clientId, name);
				result.add(not);
			}
		}
		return (Notification[]) result.toArray(new Notification[result.size()]);
	}

	private static Notification buildNotification(Alert alert, String funnelId, String title, String clientId, String name) {
		Notification not = new Notification();
		not.setTitle(title + " Alert for journey '"+name+"'");
		not.setDescription(alert.getNote());
		not.setFunnelId(funnelId);
		
		List<EntityObject> eos = new ArrayList<EntityObject>();
		EntityObject eo = new EntityObject();
		eo.setId(clientId);
		eo.setType("journey");
		eo.setTitle(name);
		eos.add(eo);
		
		not.setEntities(eos);
		
		NotificationAuthor author = new NotificationAuthor();
		author.setName("Journey Planner - " + title);
		not.setAuthor(author);		
		
		return not;
	}

}
