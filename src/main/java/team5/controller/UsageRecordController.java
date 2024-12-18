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
	public String create(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		UsageRecord ur = ur_svc.create();
		model.addAttribute("usage", ur);
		return "usageRecordForm";
	}
	
	@RequestMapping(value = "/list")
	public String readAll(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		model.addAttribute("usage", ur_svc.find());
		return "usageRecord";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		UsageRecord ur = ur_svc.findById(id);
		model.addAttribute("usage", ur);
		return "usageRecordForm";
	}
	
	@RequestMapping(value = "/save")
    public String save(@ModelAttribute("usage") @Valid UsageRecord usagerecord, BindingResult bindingResult, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		if (bindingResult.hasErrors()) { model.addAttribute("usage", usagerecord); return "usageRecordForm"; }
		
		ur_svc.save(usagerecord);
        return "usageRecord";
    }
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		ur_svc.deleteById(id);
		return "forward:/usage/list";
	}
}
