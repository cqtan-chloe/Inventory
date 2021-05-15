package team5.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import team5.model.StockTransaction;
import team5.model.TxnType;
import team5.service.SessionService;
import team5.service.SessionServiceImpl;
import team5.service.StockTransactionService;

@Controller
@RequestMapping("/stock")
public class StockTransactionController {
	
    @Autowired
    StockTransactionService st_svc;
    
    @Autowired
	private SessionService session_svc;
    
    
    @Autowired 
    public void setImplementation(StockTransactionService st_svcimpl, SessionServiceImpl session_svcimpl){
    	this.st_svc = st_svcimpl;
    	this.session_svc = session_svcimpl;
    }
    
	// restock, use, return 
//	@RequestMapping(value = "/add")
//	public String create(@PathVariable("id") Long usagerecord_id, TxnType txntype, Model model) {
//		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
//		
//		StockTransaction st = st_svc.makeNewTxn(txntype, usagerecord_id);	// usagerecord_id == null if not specified 
//		model.addAttribute("txn", st);
//		return "stockTransactionForm";
//	}
	
	@RequestMapping(value = "/add")
	// public String create(@PathVariable("id") Long usagerecord_id, TxnType txntype, Model model) {
	public String create(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		Long usagerecord_id = (long) 1;
		model.addAttribute("txn", st_svc.createNewTxn(usagerecord_id));
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/list")
	public String readAll(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";

		model.addAttribute("txns", st_svc.findAll());
		return "stockTransaction";	
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		model.addAttribute("txn", st_svc.findById(id));
		return "stockTransactionForm";
	}
	
	
	@RequestMapping(value = "/save")
	//public String save(@PathVariable("id") Long id, @ModelAttribute("txn") @Valid StockTransaction txn, BindingResult bindingResult, Model model) {
	public String save(@ModelAttribute("txn") @Valid StockTransaction txn, BindingResult bindingResult, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		if (bindingResult.hasErrors()) { model.addAttribute("txn", txn); return "stockTransactionForm"; }
		
		st_svc.save(txn);
		//return "forward:/usage/edit/{id}"; // id is the UsageRecord Id
		return "forward:/stock/list";
	}
	
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		st_svc.deleteById(id);
		return "forward:/stock/list";
	}

}
