package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.LikeDislikeRepository;
import domain.LikeDislike;

@Component
@Transactional
public class StringToLikeDislikeConverter implements Converter<String, LikeDislike>{
@Autowired LikeDislikeRepository likeDislikeRepository;

	@Override
	public LikeDislike convert(String text) {
		LikeDislike result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = likeDislikeRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
