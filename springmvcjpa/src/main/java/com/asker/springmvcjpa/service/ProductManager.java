package com.asker.springmvcjpa.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.asker.springmvcjpa.model.Product;

public interface ProductManager extends Serializable{

    public void increasePrice(int percentage);
    
    public List<Product> getProducts();
    
}