package services;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Bartol;
import domain.Nutritionist;
import domain.Recipe;

import repositories.BartolRepository;
import security.LoginService;


@Service
@Transactional
public class BartolService {

	
	//Managed Repositories--------------------------
	
	@Autowired
	private BartolRepository bartolRepository;
	
	//Supporting Services---------------------------
	
	@Autowired
	private NutritionistService nutritionistService;
	
	//Methods CRUDS---------------------------------
	
	public Bartol create(){
		
		Bartol b = new Bartol();
		Nutritionist n = nutritionistService.findByUserAccountId(LoginService.getPrincipal().getId());
		b.setNutritionist(n);
		b.setLabel(createLabel());
		return b;
		
	}
	
	// Método para crear el label de un bartol.
	public String createLabel(){
		String result = "";
		String digits = "0123456789";
		String alphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
		Random rnd = new Random();
		
		boolean equal = false;
		do{
			for(int i=0;i<5;i++){
				result = result + alphas.charAt(rnd.nextInt(alphas.length()));
			}
			result = result + "/";
			
			for(int i=0;i<5;i++){
				result = result + digits.charAt(rnd.nextInt(digits.length()));
			}
			Assert.isTrue(result.matches("^[a-zA-Z0-9_]{5}/[0-9]{5}$"));
			
			Collection<Bartol> bartols = bartolRepository.findAll();
			for(Bartol b:bartols){
				equal = result.contentEquals(b.getLabel());
				if(equal == true){
					break;
				}
			}
		}while(equal);
		
		return result;
	}	
	
	public Collection<Bartol> findAll(){
		
		Collection<Bartol> bartols = bartolRepository.findAll();
		Assert.notNull(bartols);
		return bartols;
	}
	
	public Bartol findOne(int id){
		
		Assert.notNull(id);
		Bartol b = bartolRepository.findOne(id);
		Assert.notNull(b, "Bartol con id: "+id+" no encontrado");
		return b;
	}
	
	public Bartol save(Bartol b){
		
		Assert.notNull(b);
		Bartol bartolRes = bartolRepository.save(b);
		return bartolRes;
	}
	
	public void delete(Bartol b) {
		Assert.notNull(b);
		Assert.isTrue(b.getId() != 0);
		Assert.isTrue(bartolRepository.exists(b.getId()));
		bartolRepository.delete(b);

	}
	
}
