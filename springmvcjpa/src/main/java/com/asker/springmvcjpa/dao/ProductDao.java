package com.asker.springmvcjpa.dao;

import java.util.List;

import com.asker.springmvcjpa.model.Product;

public interface ProductDao extends GenericDao<Product>{

	public List<Product> getProductList();
	public List<Product> getProductByNameList(String description);
}
