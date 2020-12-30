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
    
	
	@RequestMapping(value = "/add-restock")
	public String addRestock(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		StockTransaction st = st_svc.makeNewTxn("restock", -1);
		model.addAttribute("txn", st); 
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/add-usage")
	public String addUsage(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";

		StockTransaction st = st_svc.makeNewTxn("use", -1);
		model.addAttribute("txn", st);
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/add-usage-{id}")
	public String addUsage(@PathVariable("id") Long id, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		StockTransaction st = st_svc.makeNewTxn("use", id);
		model.addAttribute("txn", st);
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/add-return")
	public String addReturn(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		StockTransaction st = st_svc.makeNewTxn("return", -1);
		model.addAttribute("txn", st);
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";

		model.addAttribute("txns", st_svc.findAll());
		return "stockTransaction";	
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		StockTransaction txn = st_svc.findById(id);
		txn.setPrev_val(txn.getQtyChange());
		model.addAttribute("txn", txn);
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/save")
	public String saveTxn(@ModelAttribute("txn") @Valid StockTransaction txn, BindingResult bindingResult, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("txn", txn);
			return "stockTransactionForm";
		}
		
		st_svc.save(txn);
		return "forward:/stock/list";
	}
	
	@RequestMapping(value = "/save-{id}")
	public String saveTxn(@PathVariable("id") Long id, @ModelAttribute("txn") @Valid StockTransaction txn, BindingResult bindingResult, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
	
		if (bindingResult.hasErrors()) {
			model.addAttribute("txn", txn);
			return "stockTransactionForm";
		}
		
		st_svc.save(txn);
		return "forward:/usage/edit/{id}"; // id is the UsageRecord Id
	}
	
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Long id) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		st_svc.delete(st_svc.findById(id));
		return "forward:/stock/list";
	}

}
