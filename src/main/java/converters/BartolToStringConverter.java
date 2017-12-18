package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Bartol;


@Component
@Transactional
public class BartolToStringConverter implements Converter<Bartol, String>{

	@Override
	public String convert(Bartol bartol) {
		
		String result;
		if(bartol == null){
			result = null;
		}else{
			result = String.valueOf(bartol.getId());
		}
		return result;
	}
}
