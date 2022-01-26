package com.team6.ecommercebackend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", nullable=false)
	private Integer id;
	
	@Column(name = "product_type", nullable=false)
	private Integer prodType;
	
	@Column(name = "product_name", nullable=false)
	private Integer prodName;
	
	@Column(name = "product_description", nullable=false)
	private Integer prodDescription;
	
	@Column(name = "product_price", nullable=false)
	private Integer prodPrice;

	public Products() {
		super();
	}

	public Products(Integer id, Integer prodType, Integer prodName, Integer prodDescription, Integer prodPrice) {
		super();
		this.id = id;
		this.prodType = prodType;
		this.prodName = prodName;
		this.prodDescription = prodDescription;
		this.prodPrice = prodPrice;
	}

	public Products(Integer prodType, Integer prodName, Integer prodDescription, Integer prodPrice) {
		super();
		this.prodType = prodType;
		this.prodName = prodName;
		this.prodDescription = prodDescription;
		this.prodPrice = prodPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdType() {
		return prodType;
	}

	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}

	public Integer getProdName() {
		return prodName;
	}

	public void setProdName(Integer prodName) {
		this.prodName = prodName;
	}

	public Integer getProdDescription() {
		return prodDescription;
	}

	public void setProdDescription(Integer prodDescription) {
		this.prodDescription = prodDescription;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", prodType=" + prodType + ", prodName=" + prodName + ", prodDescription="
				+ prodDescription + ", prodPrice=" + prodPrice + "]";
	}
	
	
	
}
