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

import java.util.Map;
import java.util.TreeMap;

public class AlertsSent {

	private static final long KEEP = 1000 * 60 * 60 * 12;
	
	private Map<String, Long> alertsValues;
	private Map<String, Long> alertsTimes;
	
	public static AlertsSent getInstance() {
		return new AlertsSent();
	}
	
	public AlertsSent() {
		alertsTimes = new TreeMap<String, Long>();
		alertsValues = new TreeMap<String, Long>();
	}
	
	public boolean check(String id, Long value, long threshold) {
		boolean result = false;
		if (!alertsTimes.containsKey(id)) {
			result = true;
		} else if ((System.currentTimeMillis() - alertsTimes.get(id)) > KEEP) {
			result = true;
		} else if (Math.abs(alertsValues.get(id) - value) >= threshold) {
			result = true;
		}
		return result;
	}
	
	public void add(String id, Long value) {
		alertsValues.put(id, value);
		alertsTimes.put(id, System.currentTimeMillis());
	}

	public Map<String, Long> getAlertsValues() {
		return alertsValues;
	}

	public void setAlertsValues(Map<String, Long> alertsValues) {
		this.alertsValues = alertsValues;
	}

	public Map<String, Long> getAlertsTimes() {
		return alertsTimes;
	}

	public void setAlertsTimes(Map<String, Long> alertsTimes) {
		this.alertsTimes = alertsTimes;
	}	
	
}
