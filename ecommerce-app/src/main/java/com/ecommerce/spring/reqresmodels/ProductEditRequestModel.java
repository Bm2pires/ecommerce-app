package com.ecommerce.spring.reqresmodels;

import com.ecommerce.spring.entities.Brand;
import com.ecommerce.spring.entities.Categories;

import lombok.Data;

@Data
public class ProductEditRequestModel {
	
	private long productId;

	private String productName;

	private String productDescription;

	private String productPrice;

	

	public ProductEditRequestModel() {
		super();
	}



	public ProductEditRequestModel(long productId, String productName, String productDescription, String productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}
	
	

}
