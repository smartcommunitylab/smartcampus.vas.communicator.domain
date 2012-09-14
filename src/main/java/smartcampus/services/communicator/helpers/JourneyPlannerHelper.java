package smartcampus.services.communicator.helpers;

import java.util.ArrayList;
import java.util.List;

import smartcampus.services.communicator.beans.JourneyPlannerParameters;
import smartcampus.services.communicator.beans.Notification;
import smartcampus.smartplanner.data.message.alerts.Alert;
import smartcampus.smartplanner.data.message.alerts.AlertDelay;
import smartcampus.smartplanner.data.message.alerts.AlertParking;
import smartcampus.smartplanner.data.message.alerts.AlertStrike;

public class JourneyPlannerHelper {

	// public static String[] buildAlertIds(String ids[], Alert alert) {
	// List<String> idsList = Arrays.asList(ids);
	// if (!idsList.contains(alert.getId())) {
	// idsList.add(alert.getId());
	// return (String[])idsList.toArray();
	// }
	// return null;
	// }

	// public static String buildDelayDescription(Alert alert) {
	// return alert.getNote();
	// }

	public static Notification[] buildNotification(Alert alert, String funnelId, JourneyPlannerParameters pars) {
		List<Notification> result = new ArrayList<Notification>();

		if (pars.getSource() != null && !pars.getSource().equals(alert.getCreatorType().toString())) {
				return (Notification[]) result.toArray();
		}

		if (alert instanceof AlertDelay && pars.isDelays()) {
			Notification not = buildNotification(alert, funnelId, "Delay");
			result.add(not);
		} else if (alert instanceof AlertStrike && pars.isStrikes()) {
			Notification not = buildNotification(alert, funnelId, "Strike");
			result.add(not);
		} else if (alert instanceof AlertParking && pars.isParkings()) {
			Notification not = buildNotification(alert, funnelId, "Parking");
			result.add(not);
		}
		return (Notification[]) result.toArray();
	}

	private static Notification buildNotification(Alert alert, String funnelId, String title) {
		Notification not = new Notification();
		not.setTitle(title + " Alert");
		not.setDescription(alert.getNote());
		not.setFunnelId(funnelId);
		return not;
	}

}
