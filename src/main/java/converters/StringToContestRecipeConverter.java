package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ContestRecipeRepository;
import domain.ContestRecipe;

@Component
@Transactional
public class StringToContestRecipeConverter implements Converter<String, ContestRecipe>{
@Autowired ContestRecipeRepository contestRecipeRepository;

	@Override
	public ContestRecipe convert(String text) {
		ContestRecipe result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = contestRecipeRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
