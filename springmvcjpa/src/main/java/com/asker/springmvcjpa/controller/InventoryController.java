package com.asker.springmvcjpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asker.springmvcjpa.dao.ProductDao;
import com.asker.springmvcjpa.service.ProductManager;

@Controller
public class InventoryController {
	private static final Logger logger = LoggerFactory
			.getLogger(InventoryController.class);
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private ProductDao productDao;
	
	/**
	 * Selects the home page and populates the model with a message
	 */
	@RequestMapping(value = "/productList.htm", method = RequestMethod.GET)
	public String productList(Model model) {
		String now = (new java.util.Date()).toString();
        model.addAttribute("now", now);
        model.addAttribute("products", this.productDao.getProductByNameList("T"));
		return "productList";
	}
}
