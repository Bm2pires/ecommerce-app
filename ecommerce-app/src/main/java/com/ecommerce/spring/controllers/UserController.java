package com.ecommerce.spring.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.spring.repositories.UserRepository;
import com.ecommerce.spring.reqresmodels.UserAddingRequestModel;
import com.ecommerce.spring.reqresmodels.UserAddingResponseModel;
import com.ecommerce.spring.reqresmodels.UserDetailRequestModel;
import com.ecommerce.spring.reqresmodels.UserDetailResponseModel;
import com.ecommerce.spring.reqresmodels.UserLoginRequestModel;
import com.ecommerce.spring.reqresmodels.UserLoginResponseModel;
import com.ecommerce.spring.services.UserService;
import com.ecommerce.spring.services.UserLoginService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserLoginService userLoginService;
	
	@Autowired
	UserService userEditService;
	

	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<UserLoginResponseModel> authenticateUser(@RequestBody UserLoginRequestModel requestModel,
			HttpSession session) {
		userLoginService.authenticate(requestModel, session);
		return new ResponseEntity<UserLoginResponseModel>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/editUser", produces = "application/json")
	public ResponseEntity<UserDetailResponseModel> editUser(@RequestBody UserDetailRequestModel udRequestModel) {
		UserDetailResponseModel udrm = userEditService.editUser(udRequestModel);
		if(udrm != null) {
			return new ResponseEntity<UserDetailResponseModel>(udrm, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserDetailResponseModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/addUser", produces = "application/json")
	public ResponseEntity<UserAddingResponseModel> addUser(@RequestBody UserAddingRequestModel requestModel) {
		UserAddingResponseModel userResponse = userEditService.addUser(requestModel);
		if(userResponse != null) {
			return new ResponseEntity<UserAddingResponseModel>(userResponse, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserAddingResponseModel>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@DeleteMapping(value = "/delUser/{id}", produces = "application/json")
	public ResponseEntity<String> delUser(@PathVariable String id) {
		long userId = Long.parseLong(id.substring(4));
		boolean userDeleted = userEditService.delUser(userId);
		if(userDeleted) {
			return new ResponseEntity<String>("User deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Deletion Unsuccesfull", HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping(value = "/getUser", produces = "application/json")
	public ResponseEntity<UserDetailResponseModel> getUserById(@RequestParam long id) {
		UserDetailResponseModel user = userEditService.getUserById(id);
		if(user != null) {
			return new ResponseEntity<UserDetailResponseModel>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserDetailResponseModel>( HttpStatus.NOT_MODIFIED);
		}
	}
	
	//Only gets customers not admins
	@GetMapping(value = "/getAllUsers", produces = "application/json")
	public ResponseEntity<List<UserDetailResponseModel>> getAllUser() {
		List<UserDetailResponseModel> listOfUsers = userEditService.getAllUser();
		if(!listOfUsers.isEmpty()) {
			return new ResponseEntity<List<UserDetailResponseModel>>(listOfUsers, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<UserDetailResponseModel>>(HttpStatus.NOT_MODIFIED);
		}
	}
	
}
