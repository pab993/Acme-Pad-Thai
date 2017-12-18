package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.LikeDislike;

@Component
@Transactional
public class LikeDislikeToStringConverter implements Converter<LikeDislike, String>{

	@Override
	public String convert(LikeDislike likeDislike) {
		
		String result;
		if(likeDislike == null){
			result = null;
		}else{
			result = String.valueOf(likeDislike.getId());
		}
		return result;
	}

}
