package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.UnitSystemRepository;
import domain.UnitSystem;

@Component
@Transactional
public class StringToUnitSystemConverter implements Converter<String, UnitSystem>{
@Autowired UnitSystemRepository unitSystemRepository;

	@Override
	public UnitSystem convert(String text) {
		UnitSystem result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = unitSystemRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

