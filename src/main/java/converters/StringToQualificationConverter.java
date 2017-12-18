package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.QualificationRepository;

import domain.Qualification;

@Component
@Transactional
public class StringToQualificationConverter implements Converter<String, Qualification>{
	@Autowired QualificationRepository qualificationRepository;

	@Override
	public Qualification convert(String text) {
		Qualification result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = qualificationRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
