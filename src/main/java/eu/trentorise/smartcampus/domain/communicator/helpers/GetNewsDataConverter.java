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

import it.sayservice.platform.core.domain.actions.DataConverter;
import it.sayservice.platform.core.domain.ext.Tuple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import smartcampus.service.unitnnews.data.message.Unitnnews.NewsEntry;

import com.google.protobuf.ByteString;

import eu.trentorise.smartcampus.domain.communicator.beans.UnitnNews;

public class GetNewsDataConverter implements DataConverter {

	public Serializable toMessage(Map<String, Object> parameters) {
		if (parameters != null) {
			return new TreeMap<String, Object>(parameters);
		} else {
			return new TreeMap<String, Object>();
		}
	}

	@SuppressWarnings("unchecked")
	public Object fromMessage(Serializable object) {
		Tuple res = new Tuple();
		List<ByteString> data = (List<ByteString>) object;
		List<UnitnNews> list = new ArrayList<UnitnNews>();
		for (ByteString bs : data) {
			try {
				NewsEntry e = NewsEntry.parseFrom(bs);
				UnitnNews un = new UnitnNews(e);
				list.add(un);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list.toArray(new UnitnNews[list.size()]);
	}

}
