package com.asker.springmvcjpa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.asker.springmvcjpa.model.Product;

@Component("productManager")
public class SimpleProductManager implements ProductManager {

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void increasePrice(int percentage) {
		if (products != null) {
			for (Product product : products) {
				double newPrice = product.getPrice().doubleValue()
						* (100 + percentage) / 100;
				product.setPrice(newPrice);
			}
		}
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}