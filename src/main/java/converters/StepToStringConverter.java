package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Step;

@Component
@Transactional
public class StepToStringConverter implements Converter<Step, String>{

	@Override
	public String convert(Step step) {
		
		String result;
		if(step == null){
			result = null;
		}else{
			result = String.valueOf(step.getId());
		}
		return result;
	}

}
