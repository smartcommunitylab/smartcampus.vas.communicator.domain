package smartcampus.services.communicator.helpers;

import it.sayservice.platform.core.domain.actions.DataConverter;
import it.sayservice.platform.core.domain.ext.Tuple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import smartcampus.service.unitnnews.data.message.Unitnnews.NewsEntry;
import smartcampus.services.communicator.beans.UnitnNews;

import com.google.protobuf.ByteString;

public class UnitnNewsDataConverter implements DataConverter {

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

		res.put("data",  list.toArray(new UnitnNews[list.size()]));
		return res;
		
	}

}
