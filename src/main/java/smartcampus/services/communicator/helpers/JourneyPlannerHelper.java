package smartcampus.services.communicator.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import smartcampus.services.communicator.beans.JourneyPlannerParameters;
import smartcampus.services.communicator.beans.Notification;
import smartcampus.smartplanner.data.message.alerts.Alert;
import smartcampus.smartplanner.data.message.alerts.AlertDelay;
import smartcampus.smartplanner.data.message.alerts.AlertParking;
import smartcampus.smartplanner.data.message.alerts.AlertStrike;

public class JourneyPlannerHelper {

	public static Notification[] buildNotification(Alert alert, String funnelId, JourneyPlannerParameters pars) {
		List<Notification> result = new ArrayList<Notification>();

		if (pars.getSources() != null && pars.getSources().size() != 0 && !pars.getSources().contains(alert.getCreatorType().toString())) {
			
				return (Notification[]) result.toArray(new Notification[result.size()]);
		}

		if (alert instanceof AlertDelay && pars.getTypes().contains(JourneyPlannerParameters.DELAYS)) {
			Notification not = buildNotification(alert, funnelId, "Delay");
			result.add(not);
		} else if (alert instanceof AlertStrike && pars.getTypes().contains(JourneyPlannerParameters.STRIKES)) {
			Notification not = buildNotification(alert, funnelId, "Strike");
			result.add(not);
		} else if (alert instanceof AlertParking && pars.getTypes().contains(JourneyPlannerParameters.PARKINGS)) {
			Notification not = buildNotification(alert, funnelId, "Parking");
			result.add(not);
		}
		return (Notification[]) result.toArray(new Notification[result.size()]);
	}

	private static Notification buildNotification(Alert alert, String funnelId, String title) {
		Notification not = new Notification();
		not.setTitle(title + " Alert");
		not.setDescription(alert.getNote());
		not.setFunnelId(funnelId);
		return not;
	}

}
