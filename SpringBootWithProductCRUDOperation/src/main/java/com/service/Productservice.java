package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Product;
import com.dao.ProductDao;
import com.dao.ProductRepository;

@Service
public class Productservice {

	@Autowired
	ProductDao productdao;
	@Autowired
	ProductRepository pr;
	public List<Product> getAllProduct() {
		return productdao.getAllProduct().stream().collect(Collectors.toList());
	}
	
	public String storeProduct(Product p) {
		int res =productdao.storeProduct(p); 
		if(res ==1) {
			return "Successfully Stored";
		}else if(res==2) {
			return "Record already present";
		}else {
			return "didnt store";
		}
	}
	
	public int updateProduct(Product p)
	{
		int res=productdao.updateProduct(p);
		if(res==1) {
			return 1;
		}
		else {
			return 0;
		}
		
	}
	
	public String deleteProduct(int id)
	{
		int res=productdao.deleteProduct(id);
		if(res==1) {
			return "deleted successfully";
		}
		else {
			return "didnt deleted";
		}
		
	}
	
	
	
	
	
	
	//By SpringData
	
	public List<Product> getAllProductFromSpringData(){
		return pr.findAll();
	}
	
	
	public String storeProductSpringData(Product pp) {
		Optional<Product> op= pr.findById(pp.getPid());
		if(op.isPresent()) 
		{
			return "recored already present";
		}
		else 
		{
			Product p=pr.save(pp);
			if(p!=null) 
			{
				return "recored stored sucessfully";
			}else 
			{
				return "Recored didn't stored";
			}
		}
	}
	
	public String deleteProductSpringData(int pid) {
		if(pr.existsById(pid)) {
			pr.deleteById(pid);
			return "Deleted successfully";
		}else {
			return "Record not present";
		}
	}
	
	public String updateProductBySpringData(Product p) {
			Optional<Product> op=pr.findById(p.getPid());
			if(op.isPresent()) 
			{
				Product pp=op.get();
				pp.setPrice(p.getPrice());
				pr.saveAndFlush(pp);
				return "updated successfully";
			}
			else 
			{
				return "Record not present";
			}
			
	}
	
}
