package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.ContestRecipe;

@Component
@Transactional
public class ContestRecipeToStringConverter implements Converter<ContestRecipe, String>{

	@Override
	public String convert(ContestRecipe contestRecipe) {
		
		String result;
		if(contestRecipe == null){
			result = null;
		}else{
			result = String.valueOf(contestRecipe.getId());
		}
		return result;
	}

}
