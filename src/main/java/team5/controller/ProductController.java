package team5.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.data.repository.query.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team5.model.Product;
import team5.service.ProductService;
import team5.service.ProductServiceImpl;
import team5.service.SessionService;
import team5.service.SessionServiceImpl;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService product_svc;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	public void SetImplimentation(ProductServiceImpl product_svcimpl, SessionServiceImpl session_svcimpl) {
		this.product_svc = product_svcimpl;
		this.session_svc = session_svcimpl;
	}

	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		
	}
	
	
	@RequestMapping("/add")
	public String showProductForm(Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		if (session_svc.hasNoPermission()) return "nopermission";
		
		model.addAttribute("product", new Product());
		return "productform";
	}
	
	
	@PostMapping("/save")
	public String saveProductForm(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("product", product);
			return "productform";
		}
		
		product_svc.save(product);
		return "forward:/product/list";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		if (session_svc.hasNoPermission()) return "nopermission";
		
		model.addAttribute("product", product_svc.findById(id));
		return "productform";
	}
	
	
	@RequestMapping(value = "/list")
	public String list(Model model, @Param("keyword") String keyword) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		
		model.addAttribute("products",product_svc.searchByKeyword(keyword));
		model.addAttribute("hasPermission",session_svc.hasPermission());
		return "products";
	}
	

	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Long id) {
		if (session_svc.isNotLoggedIn()) return "redirect:/user/login";
		if (session_svc.hasNoPermission()) return "nopermission";
		
		Product product = product_svc.findById(id);
		product_svc.delete(product);
		return "forward:/product/list";
	}

}
