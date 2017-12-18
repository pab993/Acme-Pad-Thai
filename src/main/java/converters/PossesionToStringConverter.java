package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Possesion;

@Component
@Transactional
public class PossesionToStringConverter implements Converter<Possesion, String>{

	@Override
	public String convert(Possesion possesion) {
		
		String result;
		if(possesion == null){
			result = null;
		}else{
			result = String.valueOf(possesion.getId());
		}
		return result;
	}

}
