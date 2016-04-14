package com.asker.springmvcjpa.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.asker.springmvcjpa.dao.ProductDao;
import com.asker.springmvcjpa.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{

	public List<Product> getProductList() {
		TypedQuery<Product> query = em.createQuery("Select p From Product p",Product.class);
		return query.getResultList();
	}

	public List<Product> getProductByNameList(String description) {
		// TODO Auto-generated method stub
		Query query =em.createNamedQuery("Product.description");
		query.setParameter("description",description);
		return query.getResultList();
	}
	
}
