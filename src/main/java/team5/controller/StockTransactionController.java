package team5.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team5.model.Annotation;
import team5.model.Product;
import team5.model.StockTransaction;
import team5.model.UsageRecord;
import team5.model.User;
import team5.repo.AnnotationRepo;
import team5.service.ProductService;
import team5.service.ProductServiceImpl;
import team5.service.SessionService;
import team5.service.SessionServiceImpl;
import team5.service.StockTransactionService;
import team5.service.UsageRecordService;
import team5.service.UsageRecordServiceImpl;

@Controller
@RequestMapping("/stock")
public class StockTransactionController {
	
	@Autowired
	private ProductService product_svc;
	
    @Autowired
    UsageRecordService ur_svc;
    
    @Autowired
    StockTransactionService st_svc;
    
    @Autowired
    AnnotationRepo arepo;
    
    @Autowired
	private SessionService session_svc;
    
    @Autowired 
    HttpSession session;
    
    @Autowired 
    public void setImplementation(ProductServiceImpl product_svcimpl, UsageRecordServiceImpl ur_svcimpl, StockTransactionService st_svcimpl, SessionServiceImpl session_svcimpl){
    	this.product_svc = product_svcimpl;
    	this.ur_svc = ur_svcimpl;
    	this.st_svc = st_svcimpl;
    	this.session_svc = session_svcimpl;
    }
    
	
	@RequestMapping(value = "/list")
	public String list(Model model) {

		model.addAttribute("txns", st_svc.findAll());
		return "stockTransaction";	
	}
	
	@RequestMapping(value = "/add-restock")
	public String addRestock(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		User user = (User) session.getAttribute("user");
		Annotation a = arepo.save(new Annotation(user));
		model.addAttribute("txn", new StockTransaction(null, 0, a)); // type == "restock" for this params combo
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/add-usage")
	public String addUsage(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";

		User user = (User) session.getAttribute("user");
		Annotation a = arepo.save(new Annotation(user));
		model.addAttribute("txn", new StockTransaction(null, null, 0, a));  // type == "use" for this params combo
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/add-return")
	public String addRetrun(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		User user = (User) session.getAttribute("user");
		Annotation a = arepo.save(new Annotation(user));
		model.addAttribute("txn", new StockTransaction(null, 0, "return", a));
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		
		StockTransaction txn = st_svc.findById(id);
		txn.setPrev_val(txn.getQtyChange());
		model.addAttribute("txn", txn);
		return "stockTransactionForm";
	}
	
	@RequestMapping(value = "/save")
	public String saveSupplier(@ModelAttribute("txn") @Valid StockTransaction txn,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return "stockTransactionForm";
		
		Product p = product_svc.findById(txn.getProduct().getId());
		p.setQty(p.getQty() - txn.getPrev_val() + txn.getQtyChange());
		
		st_svc.save(txn);
		product_svc.save(p);
		return "forward:/stock/list";
	}
	
	@RequestMapping(value = "/saveX")
	public String saveX(@ModelAttribute("usage") @Valid UsageRecord usage,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return "stock-usage-form";
		
		for (StockTransaction txn : usage.getStockTranxList())
		{
			Product p = product_svc.findById(txn.getProduct().getId());
			p.setQty(p.getQty() - txn.getPrev_val() + txn.getQtyChange());
			
			st_svc.save(txn);
			product_svc.save(p);
		}
		return "forward:/usage/list";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Long id) {
		
		st_svc.delete(st_svc.findById(id));
		return "forward:/stock/list";
	}

}
