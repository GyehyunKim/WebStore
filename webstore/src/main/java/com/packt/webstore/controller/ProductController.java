package com.packt.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.UnitsInStockValidator;


@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired 
	private ProductService productService; 
	
	@Autowired
	private UnitsInStockValidator unitsInStockValidator;
	
	@RequestMapping
	public String list(Model model) {
				
		model.addAttribute("products", productService.getAllProducts());		
		return "products";
	}
	
	@RequestMapping("/all")
	public String allProducts(Model model){
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@RequestMapping("/{category}") 
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		
		List<Product> products = productService.getProductsByCategory(productCategory);
		if (products == null || products.isEmpty()) 
			throw new NoProductsFoundUnderCategoryException();
		
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping("/filter/{byCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="byCriteria") 
	Map<String, List<String>> filterParams, Model model){
		System.out.println(filterParams.size());
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		Product newProduct = new Product(); 
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, 
			BindingResult result, HttpServletRequest request){
		
		// for validation 
		if(result.hasErrors()) {
			  return "addProduct";
		}
		
		String[] suppressedFields = result.getSuppressedFields();
		
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " + 
			StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		
		try {
		if (productImage != null && !productImage.isEmpty()) {
			productImage.transferTo(new File(rootDirectory + "resources\\images\\" 
					+ newProduct.getProductId() + ".png"));
			
		}
		} catch (Exception e) {
			throw new RuntimeException("Product Image saving failed", e);
		}
		
		productService.addProduct(newProduct);
		return "redirect:/products";
	}	
	
	@InitBinder 
	public void initializeBinder(WebDataBinder binder) {
		binder.setDisallowedFields("discontinued", "unitsInOrder"); 
		//binder.setAllowedFields(allowedFields);
		binder.setValidator(unitsInStockValidator);
				
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception ){
		System.out.println("Exception NoProducts");
		ModelAndView mav = new ModelAndView(); 
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURI() + "?" + req.getQueryString());
		
		mav.setViewName("productNotFound");
		
		return mav;
	}
	
	@RequestMapping(value="invalidPromoCode")
	public String invalidPromoCode() {
		
		return "invalidPromoCode";
	}
}
