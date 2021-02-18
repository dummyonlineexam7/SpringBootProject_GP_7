package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Product;

@Repository
public class ProductDao {

	@Autowired
	EntityManagerFactory emf;
	
	public List<Product> getAllProduct() {
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select p from Product p");
		List<Product> listOfEmp = qry.getResultList();
		return listOfEmp;
	}
	
	public int storeProduct(Product p) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		try {
		tran.begin();
					manager.persist(p);
		tran.commit();
		}catch(Exception e) {
			return 2;
		}
		Product pp = manager.find(Product.class, p.getPid());
		if(pp!=null) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
	public int updateProduct(Product pp) {
		EntityManager manager = emf.createEntityManager();	// Statement or PreparedStatement 					// Statement or PreparedStatement 
		EntityTransaction tran = manager.getTransaction();
		
		Product p= manager.find(Product.class, pp.getPid());			// primary key
		if(p==null) {
			return 0;
		}else {
			tran.begin();
						p.setPrice(p.getPrice()+pp.getPrice());
						manager.merge(p);									//update price using pid column 
			tran.commit();
			return 1;
		}
	}
	
	public int deleteProduct(int id) {
		EntityManager manager = emf.createEntityManager();	// Statement or PreparedStatement 
		EntityTransaction tran = manager.getTransaction();
		
		Product p= manager.find(Product.class, id);			// primary key	if record is available automatically convert record to object 
		if(p==null) {															// select * from product where pid =1
			return 0;
		}else {
			tran.begin();
						manager.remove(p);						// delete from product where pid = 1;
			tran.commit();
			return 1;
		}
	}
	

}
