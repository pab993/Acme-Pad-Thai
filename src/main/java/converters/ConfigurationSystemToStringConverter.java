package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.ConfigurationSystem;

@Component
@Transactional
public class ConfigurationSystemToStringConverter implements Converter<ConfigurationSystem, String>{

	@Override
	public String convert(ConfigurationSystem configurationSystem) {
		
		String result;
		if(configurationSystem == null){
			result = null;
		}else{
			result = String.valueOf(configurationSystem.getId());
		}
		return result;
	}

}
