package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.FollowerRepository;
import domain.Follower;

@Component
@Transactional
public class StringToFollowerConverter implements Converter<String, Follower>{
@Autowired FollowerRepository followerRepository;

	@Override
	public Follower convert(String text) {
		Follower result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = followerRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}