package com.packt.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.exception.ProductNotFoundInCategoryException;
import com.packt.webstore.service.ProductService;


@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@RequestMapping
	public String list(Model model) {
		
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@RequestMapping("/all")
	public String allProducts(Model model) {
		
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
//	@RequestMapping("/{id}")
//	public String productById(Model model, @PathVariable("id") String id) {
//		
//		model.addAttribute("product", productService.getProductById(id));
//		return "products";
//	}

	@RequestMapping("/{category}")
	public String getByCategory(Model model, @PathVariable("category") String category) {
		
		List<Product> products = productService.getProductByCategory(category);
		
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundInCategoryException();
		}
		
		model.addAttribute("products", products);
		return "products";
	}
	
	// http://localhost:8080/webstore/products/filter/ByCriteria;brand=google,dell;category=tablet,laptop
	// http://localhost:8080/webstore/products/filter/ByCriteria;brand=google;brand=dell;category=tablet;category=laptop
	@RequestMapping("/filter/{ByCriteria}")
	public String getByFilter(Model model, @MatrixVariable(pathVar="ByCriteria") Map<String, List<String>> filterParams) {
		
		model.addAttribute("products", productService.getProductByFilter(filterParams));
		return "products";		
	}
	
	@RequestMapping("/product")
	public String getById(Model model, @RequestParam("id") String id) {
		
		model.addAttribute("product", productService.getProductById(id));
		return "product";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String getNewProdForm(Model model) {
		
		model.addAttribute("newProduct", new Product());
		return "addProduct";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processNewProdForm(@ModelAttribute("newProduct") Product newProduct,
			BindingResult result, HttpServletRequest request) {
		
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " 
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile prodImage = newProduct.getProductImage();
		String rootDir = request.getSession().getServletContext().getRealPath("/");
		
		if (prodImage != null && !prodImage.isEmpty()) {
			try {
				
				prodImage.transferTo(
						new File(rootDir + "resources/images/" + newProduct.getProductId() + ".png")
						);
				
			}
			catch (Exception e) {
				throw new RuntimeException("Product Image Saving failed!", e);
			}
		}
				
		productService.addProduct(newProduct);
		return "redirect:/products";
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}
	
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setDisallowedFields("unitsInOrder", "discontinued");
	}
	
}
