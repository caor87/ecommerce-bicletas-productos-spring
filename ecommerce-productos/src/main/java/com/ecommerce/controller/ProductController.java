package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.ProductDTO;
import com.ecommerce.service.ProductService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/v1/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<?> create(@Validated @RequestBody ProductDTO product) {
		String productName = productService.findByname(product.getName_pro());
		if(productName.equalsIgnoreCase("Producto existente")) {
			return new ResponseEntity<String>("El producto con el nombre " + product.getName_pro() + " ya existe", HttpStatus.BAD_REQUEST);
		}
		ProductDTO prodNew = productService.save(product);
		return ResponseEntity.ok(prodNew);
	}

	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> getAll(){
		if(productService.findAll() == null)
			ResponseEntity.notFound().build();
		List<ProductDTO> products = productService.findAll();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") int id){
			ProductDTO product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<ProductDTO> updateAmountProduct(@PathVariable("id") int id, @Validated @RequestBody ProductDTO product) {
		if(productService.getProductById(id) == null)
			ResponseEntity.notFound().build();
		ProductDTO prod = productService.updateProduct(id, product);
		return ResponseEntity.ok(prod);
	}
	@PutMapping("/product")
	public ResponseEntity<List<ProductDTO>> updateAmountProduct(@RequestBody List<ProductDTO> product) {
	
		List<ProductDTO> prod = productService.modifyAmountProductById(product);
		return ResponseEntity.ok(prod);
	}
	
	
	
	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
	}
	

}
