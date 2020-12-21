package team5.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import team5.service.EmailService;

@SpringBootTest
public class SendMail {
	
	  @Autowired 
	  private EmailService emailService;	 
	
	  @Test 
	  public void testEmail() {
		  emailService.sendMail("eaintchitthae94@gmail.com", "Test Subject", "Testing"); 
	  }
}
