package com.ecommerce.spring.reqresmodels;

import lombok.Data;

@Data
public class UserLoginResponseModel {

	private String email;
	private String password;
	private String title;
	private String firstName;
	private String lastName;
	private String phone_number;
	private String address;
	private String isAdmin;

	public UserLoginResponseModel(String email, String password, String title, String firstName, String lastName,
			String phone_number, String address, String isAdmin) {
		this.email = email;
		this.password = password;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone_number = phone_number;
		this.address = address;
		this.isAdmin = isAdmin;
	}

	public UserLoginResponseModel() {
	}

}
