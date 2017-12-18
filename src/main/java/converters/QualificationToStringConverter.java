package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Qualification;

@Component
@Transactional
public class QualificationToStringConverter implements Converter<Qualification, String>{

	@Override
	public String convert(Qualification qualification) {
		
		String result;
		if(qualification == null){
			result = null;
		}else{
			result = String.valueOf(qualification.getId());
		}
		return result;
	}
}
