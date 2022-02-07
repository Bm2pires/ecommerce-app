package com.ecommerce.spring.reqresmodels;

import com.ecommerce.spring.entities.Brand;
import com.ecommerce.spring.entities.Categories;

import lombok.Data;

@Data
public class ProductGetResponseModel {
	private long productId;

	private String productName;

	private String productDescription;

	private String productPrice;
	
	private String productCategory;
    
	private String productBrand;
	

	public ProductGetResponseModel() {
		super();
	}


	public ProductGetResponseModel(long productId, String productName, String productDescription, String productPrice,
			String productCategory, String productBrand) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productBrand = productBrand;
	}

	

	
}
