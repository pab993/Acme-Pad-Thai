package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;

@Transactional
@Service
public class CreditCardService {

	public CreditCardService(){
		super();
	}

	@Autowired
	private CreditCardRepository cardRepository;
	
	//Aux

	
	//Methods CRUDs ------------------
	
		public CreditCard create(){
			
			checkIsSponsor();
			CreditCard c = new CreditCard();
			return c;
		}
		

		public Collection<CreditCard> findAll(){
			
			Collection<CreditCard> cards = cardRepository.findAll();
			Assert.notNull(cards);
			return cards;
		}
		
		public CreditCard findOne(int id){
			
			Assert.notNull(id);
			CreditCard card = cardRepository.findOne(id);
			return card;
		}
		
		public CreditCard save(CreditCard c){
			
			checkIsSponsor();
			checkCreditCard(c);
			Assert.notNull(c);
			CreditCard creditCardRes = cardRepository.save(c);
			return creditCardRes;
		}
		
		public void delete(CreditCard c){
			
			checkIsSponsor(); //¿es la persona logueada un sponsor?
			checkCreditCard(c);
			Assert.notNull(c);
			Assert.isTrue(c.getId()!= 0);
			Assert.isTrue(cardRepository.exists(c.getId()));
			cardRepository.delete(c);
		}
		
		// Other Business Method ---
		
		
		private void checkIsSponsor() {
			// TODO Auto-generated method stub
				UserAccount account = LoginService.getPrincipal();
				Authority authority = new Authority();
				authority.setAuthority("SPONSOR");
				Assert.isTrue(account.getAuthorities().contains(authority));
		}
		
		private void checkCreditCard(CreditCard d){
			UserAccount account = LoginService.getPrincipal();
			Assert.isTrue(d.getSponsor().getUserAccount().getId() == account.getId());
		}
}
