package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.IngredientRepository;
import domain.Ingredient;

@Component
@Transactional
public class StringToIngredientConverter implements Converter<String, Ingredient>{
@Autowired IngredientRepository ingredientRepository;

	@Override
	public Ingredient convert(String text) {
		Ingredient result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = ingredientRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
