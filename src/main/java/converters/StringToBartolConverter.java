package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.BartolRepository;

import domain.Bartol;

@Component
@Transactional
public class StringToBartolConverter implements Converter<String, Bartol>{

	@Autowired BartolRepository bartolRepository;

	@Override
	public Bartol convert(String text) {
		Bartol result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = bartolRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
