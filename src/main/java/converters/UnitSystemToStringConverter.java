package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.UnitSystem;

@Component
@Transactional
public class UnitSystemToStringConverter implements Converter<UnitSystem, String>{

	@Override
	public String convert(UnitSystem unitSystem) {
		
		String result;
		if(unitSystem == null){
			result = null;
		}else{
			result = String.valueOf(unitSystem.getId());
		}
		return result;
	}

}
