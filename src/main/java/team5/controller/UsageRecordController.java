package team5.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import team5.model.UsageRecord;
import team5.service.SessionService;
import team5.service.SessionServiceImpl;
import team5.service.UsageRecordService;
import team5.service.UsageRecordServiceImpl;

@Controller
@RequestMapping("/usage")
public class UsageRecordController {
	
	@Autowired
	UsageRecordService ur_svc;
	
	@Autowired
	private SessionService session_svc;

	@Autowired 
	public void setImplimentation(UsageRecordServiceImpl ur_svcimpl, SessionServiceImpl session_svcimpl) {
		this.ur_svc = ur_svcimpl;
		this.session_svc = session_svcimpl;
	}
	
	
	@RequestMapping(value = "/add")
	public String addform(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		model.addAttribute("usage",new UsageRecord());
		return "stock-usage-form";
	}
	
	@RequestMapping(value = "/save")
    public String saveSupplier(@ModelAttribute("usage") @Valid UsageRecord usagerecord, BindingResult bindingResult, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("usage", usagerecord);
			return "stock-usage-form";
		}
		
		ur_svc.save(usagerecord);
        return "stock-usage-list";
    }
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		model.addAttribute("usage", ur_svc.findAll());
		return "stock-usage-list";
	}
	
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		UsageRecord ur = ur_svc.findById(id);
		model.addAttribute("usage", ur);
		return "stock-usage-form";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Long id) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		ur_svc.delete(ur_svc.findById(id));
		return "forward:/usage/list";
	}
	

}
