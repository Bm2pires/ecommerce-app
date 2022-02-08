package com.ecommerce.spring.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDetailsID;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private User user;
	
	@Column
	private double totalPrice;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date dateOfOrder;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date shippingAddress;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date billingAddress;
	
	@OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItems> orders = new ArrayList<>();

	public OrderDetails(long orderDetailsID, User user, double totalPrice, Date dateOfOrder, Date shippingAddress,
			Date billingAddress, List<OrderItems> orders) {
		super();
		this.orderDetailsID = orderDetailsID;
		this.user = user;
		this.totalPrice = totalPrice;
		this.dateOfOrder = dateOfOrder;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.orders = orders;
	}

	public OrderDetails() {
		super();
	}

	
	
}
