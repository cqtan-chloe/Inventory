package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import team5.model.User;
import team5.service.SessionService;
import team5.service.SessionServiceImpl;

@Controller
@RequestMapping("/user")
public class SessionController {

	@Autowired
	private SessionService session_svc;
	
	@Autowired
	public void SetImplimentation(SessionServiceImpl session_svcimpl) {
		this.session_svc = session_svcimpl;
	}
	
	@RequestMapping(path = "/login")
	public String login(Model model) {

		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session_svc.logout();
		return "redirect:/user/login";
	}
	
	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("user") User user, Model model) {
		if(session_svc.authenticate(user)) 
		{
			session_svc.setUser(user);
			return "index";
		}
		else
			return "login";
	}
	
}
