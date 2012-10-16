package smartcampus.services.communicator.helpers;

import it.sayservice.platform.core.domain.actions.DataConverter;
import it.sayservice.platform.core.domain.ext.Tuple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import smartcampus.services.communicator.beans.SocialNews;

import com.google.protobuf.ByteString;

import eu.trentorise.smartcampus.services.social.data.message.Social.News;
import eu.trentorise.smartcampus.services.social.data.message.Social.NewsList;

public class SocialNewsConverter implements DataConverter {

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
		List<SocialNews> list = new ArrayList<SocialNews>();
		NewsList nl = null;
		for (ByteString bs : data) {
			try {
				nl = NewsList.parseFrom(bs);
				for (News n : nl.getNewsList()) {
					SocialNews sn = new SocialNews(n);
					list.add(sn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		res.put("data", list.toArray(new SocialNews[list.size()]));
		res.put("socialId", ""+nl.getSocialId());
		return res;
		
	}

}
