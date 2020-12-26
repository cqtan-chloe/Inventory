package team5.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import team5.model.Product;
import team5.model.StockTransaction;
import team5.service.EmailService;
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
	private SessionService session_svc;
    
	@Autowired
	HttpSession session;		// not an interface. an object passed automatically by the framework 
    
    @Autowired 
    public void setImplementation(ProductServiceImpl product_svcimpl, UsageRecordServiceImpl ur_svcimpl, StockTransactionService st_svcimpl, SessionServiceImpl session_svcimpl){
    	this.product_svc = product_svcimpl;
    	this.ur_svc = ur_svcimpl;
    	this.st_svc = st_svcimpl;
    	this.session_svc = session_svcimpl;
    }
    
	@Autowired
	private EmailService emailService;
	
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		model.addAttribute("txns", st_svc.findAll());
		return "stockTransaction";	
	}
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		
		model.addAttribute("txn", new StockTransaction());
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
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Long id) {
		
		st_svc.delete(st_svc.findById(id));
		return "forward:/stock/list";
	}
	
	
	
	/*------------------------------------Create------------------------------------*/
	
	// create stock transaction entry to increase quantity		// checked 
	// for admin when stock arrives 
	@GetMapping("/add2")
	public String addStock(Model model, @Param("keyword") String keyword) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		if (session_svc.hasNoPermission()) return "nopermission";
		/*
		}else if(user.getRole()==RoleType.ADMIN){
			keyword = null;*/

		model.addAttribute("products", product_svc.findAll());
		model.addAttribute("keyword", keyword);
		model.addAttribute("product", new Product());			

		return "stockEntryForm";
	}
	
	@GetMapping("/save")		// checked 
	public String saveStockEntry(@ModelAttribute("product") @Valid @RequestBody Product product, BindingResult result, Model model) {
		if (result.hasErrors()) return "stockEntryForm";
		
		Product p = product_svc.findById(product.getId());
		p.setQty(product.getQty() + p.getQty());
		product_svc.save(p);
		return "forward:/product/listproducts";
	}
	
	/*--------------------------------Read/Retrieve------------------------------------*/
	

	@GetMapping("/report")
	public String usageReport(Model model) {
		List<Product> products = product_svc.findAll();
		model.addAttribute("products", products);
		return "usageReport";
	}
	
	// filter by productId and date range 
	// done with the custom method defined in StockTransactionRepo extended from JPARepository 	// checked 
	@PostMapping("/report")
	public String usageReport(Model model, @RequestParam("startDate") String startD, @RequestParam("endDate") String endD, @RequestParam("productSelected") long id) throws ParseException {
		if (endD == "" || startD == "") {
			return "redirect:/stock/report";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse(startD);
		Date endDate = formatter.parse(endD);
		
		Product product = product_svc.findById(id);
		List<StockTransaction> fullUsageList = product.getStockTranxList();
		List<StockTransaction> usageList = new ArrayList<StockTransaction>();
		for(StockTransaction x : fullUsageList) {
			if(!x.getUsageRecord().getDate().after(endDate) && !x.getUsageRecord().getDate().before(startDate)) {
				usageList.add(x);
			}
		}

		model.addAttribute("product",product);
		model.addAttribute("usageList", usageList);
		model.addAttribute("fromDate", startD);
		model.addAttribute("ToDate", endD);
		return "usageReportDetails"; // "stockTranxHistory";
	}
	

	// filter by UsageRecordId
	// done with the custom method defined in StockTransactionRepo extended from JpaRepository 
	// the page shows the details of the UsageRecord and a list of StockTransaction records 
	// quantity change is implied to be negative (withdraw from inventory)
	// can change 

    
	/*------------------------------------Update------------------------------------*/

	
	
}
