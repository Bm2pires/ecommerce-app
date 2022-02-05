package com.ecommerce.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.spring.reqresmodels.ProductAddRequestModel;
import com.ecommerce.spring.reqresmodels.ProductAddResponseModel;
import com.ecommerce.spring.reqresmodels.ProductEditRequestModel;
import com.ecommerce.spring.reqresmodels.ProductEditResponseModel;
import com.ecommerce.spring.reqresmodels.UserLoginRequestModel;
import com.ecommerce.spring.reqresmodels.UserLoginResponseModel;
import com.ecommerce.spring.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService prodService;
	
	@PutMapping(value = "/editProd", produces = "application/json")
	public ResponseEntity<ProductEditResponseModel> editProd(@RequestBody ProductEditRequestModel requestModel) {
		ProductEditResponseModel predm = prodService.editProd(requestModel);
		if(predm != null) {
			return new ResponseEntity<ProductEditResponseModel>(predm, HttpStatus.OK);
		}else {
			return new ResponseEntity<ProductEditResponseModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/addProd", produces = "application/json")
	public ResponseEntity<ProductAddResponseModel> addProd(@RequestBody ProductAddRequestModel requestModel) {
		ProductAddResponseModel predm = prodService.editProd(requestModel);
		if(predm != null) {
			return new ResponseEntity<ProductAddResponseModel>(predm, HttpStatus.OK);
		}else {
			return new ResponseEntity<ProductAddResponseModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/delProd", produces = "application/json")
	public ResponseEntity<String> delProd(@RequestParam(name = "id") long id) {
		boolean deleted = prodService.delProd(id);
		if(deleted) {
			return new ResponseEntity<String>("Product Deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Deletion unsuccesfull", HttpStatus.NOT_FOUND);
		}
	}

}
