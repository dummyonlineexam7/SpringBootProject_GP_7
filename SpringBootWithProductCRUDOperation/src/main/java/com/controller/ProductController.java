package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Product;
import com.service.Productservice;

@RestController
@RequestMapping(value="product")
public class ProductController {

	Product p=new Product();
	@Autowired
	Productservice ps;
	@GetMapping(value="info")
	public String simpleMessage() {
		return "Welcome to spring rest product controller";
	}
	
	@GetMapping(value="allproduct",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProduct() {
		List<Product> listofprod=new ArrayList<>();
	    listofprod=ps.getAllProduct();
		listofprod.forEach(p->System.out.println(p));
		return listofprod;
	}
	
	@PostMapping(value="store",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String storeProduct(@RequestBody Product p) {
		String res=ps.storeProduct(p);
		return res;
	}
	
	@PutMapping(value="update",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String updateProduct(@RequestBody Product p) {
		int res=ps.updateProduct(p);
		if(res==1) {
			return "updated successfully";
		}
		else {
			return "Id not found";
		}
	}
	
	@DeleteMapping(value="delete/{pid}")
	public String deleteProduct(@PathVariable("pid") int pid) {
		String res=ps.deleteProduct(pid);
		return res;
	}
	
	
	
	
	
	
	
	//ByStringData
	
	//http://localhost:8080/product/allproductdata
	@GetMapping(value="allproductdata",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllDetailsFromSpringData(){
		return ps.getAllProductFromSpringData();
	}
	
	@PostMapping(value="storespringdata", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String storeProductByStringData(@RequestBody Product p) {
		return ps.storeProductSpringData(p);
	}
	
	@DeleteMapping(value="deletespringdata/{pid}")
	public String deleteProductByStringData(@PathVariable("pid") int pid) {
		return ps.deleteProductSpringData(pid);
	}
	
	@PutMapping(value="updatespringdata",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String updateProductBySpringData(@RequestBody Product p) {
		return ps.updateProductBySpringData(p);
	}
	
}
