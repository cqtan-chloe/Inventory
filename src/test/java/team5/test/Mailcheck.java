package team5.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import team5.repo.ProductRepo;
import team5.repo.UserRepo;

@Component
@Service
public class Mailcheck {

	private JavaMailSender javaMailSender;
	@Autowired
	public ProductRepo productRepo;
	@Autowired
	public UserRepo urepo;
	
	@Test
	public void checkProdQuantity()
	{
		SimpleMailMessage mailn=new SimpleMailMessage();
		mailn.setTo("tharhtetaung@u.nus.edu");
		mailn.setSubject("the quantity level of the stock is reached to min-reorder leverl");
	//	mailn.setText(productRepo.findById(id).getName() + " need to reorder ");
		mailn.setText("hello world");
		
		try {
			javaMailSender.send(mailn);//This line produces the exception
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't send message");
        }
		long usedQ= 5;
		long id = 2;
		
//	Product	plist = productRepo.findById(2).getReorderLevel();
	//	Product product= new Product();
		//Product product = productRepo.findById(id);
//		for (Iterator<Product> iterator = plist.iterator(); iterator.hasNext();) {
//			 product = (Product) iterator.next();
//			
//		}
	
		long QfromDB= (long) productRepo.findById(2).getReorderLevel();
		long remain= QfromDB-usedQ;
		productRepo.findById(id).setReorderLevel(remain);
		long min= (long)productRepo.findById(id).getMinReoderLevel();
		if (min>=remain)
		{
			
			mailn.setTo("tharhtetaung@u.nus.edu");
			mailn.setSubject("the quantity level of the stock is reached to min-reorder leverl");
		//	mailn.setText(productRepo.findById(id).getName() + " need to reorder ");
			mailn.setText("hello world");
			javaMailSender.send(mailn);
		}
		
	}
	@Test
	public void checkMailNoti()
	{
		
	}
}
