package com.ecommerce.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.spring.entities.Products;
import com.ecommerce.spring.entities.User;
import com.ecommerce.spring.repositories.UserRepository;
import com.ecommerce.spring.reqresmodels.ProductEditResponseModel;
import com.ecommerce.spring.reqresmodels.ProductGetResponseModel;
import com.ecommerce.spring.reqresmodels.UserAddingRequestModel;
import com.ecommerce.spring.reqresmodels.UserAddingResponseModel;
import com.ecommerce.spring.reqresmodels.UserDetailRequestModel;
import com.ecommerce.spring.reqresmodels.UserDetailResponseModel;
import com.ecommerce.spring.reqresmodels.UserLoginRequestModel;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	//used to check if user has an order details
	@Autowired 
	OrderDetailsService orderDetailsService;
	
	public UserDetailResponseModel editUser(UserDetailRequestModel requestModel) {
		Optional<User> user = userRepository.findById(requestModel.getId());
		boolean valid = validateInput(requestModel);
		//Checks if the email input given is a duplicate so that it does not violate the duplicate constraint on email
		boolean duplicateEmail = checkUniqueEmail(requestModel);
		if(valid && duplicateEmail) {
			if(user.isPresent()) {		
				user.get().setAddress(requestModel.getAddress());
				user.get().setDob(requestModel.getDateOfBirth());
				user.get().setEmail(requestModel.getEmail());
				user.get().setFirstName(requestModel.getFirstName());
				user.get().setLastName(requestModel.getLastName());
				user.get().setPassword(requestModel.getPassword());
				user.get().setPhone_number(requestModel.getPhoneNumber());
				user.get().setTitle(requestModel.getTitle());
				userRepository.save(user.get());
				return new UserDetailResponseModel(user.get().getUserId(), user.get().getFirstName(), user.get().getLastName(), user.get().getEmail(), user.get().getTitle(), user.get().getPassword(),
						user.get().getDob(), user.get().getPhone_number(), user.get().getAddress());
			}else {
				return null;
			}
		}else {
			return null;
		}

	}
	
	public UserAddingResponseModel addUser(@RequestBody UserAddingRequestModel requestModel) {
		UserAddingResponseModel userModel = new UserAddingResponseModel();
		ModelMapper mapper = new ModelMapper();
		boolean valid = validateInput(requestModel);
		//Checks if the email input given is a duplicate so that it does not violate the duplicate constraint on email
		boolean duplicateEmail = checkUniqueEmail(requestModel.getEmail());
		if(valid && duplicateEmail) {
			userModel.setAddress(requestModel.getAddress());
			userModel.setDateOfBirth(requestModel.getDateOfBirth());
			userModel.setEmail(requestModel.getEmail());
			userModel.setFirstName(requestModel.getFirstName());
			userModel.setLastName(requestModel.getLastName());
			userModel.setPassword(requestModel.getPassword());
			userModel.setPhoneNumber(requestModel.getPhoneNumber());
			userModel.setTitle(requestModel.getTitle());
			User user = mapper.map(userModel, User.class);
			user.setAdmin(false);
			user.setDob(requestModel.getDateOfBirth());
			user.setPhone_number(requestModel.getPhoneNumber());
			userRepository.save(user);
			return userModel;
		}else {
			return null;
		}

	}
	
	//AddUser check unique email
	public boolean checkUniqueEmail(String email) {
		Optional<User> userCheck = userRepository.findByEmail(email);
		if(userCheck.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	//EditUser check unique email
	public boolean checkUniqueEmail(UserDetailRequestModel requestModel) {
		Optional<User> userCheck = userRepository.findByEmail(requestModel.getEmail());
		if(userCheck.isEmpty()) {
			return true;
		}else {
			if(userCheck.get().getUserId() == requestModel.getId()) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	//Edit User  
	public boolean validateInput(UserDetailRequestModel requestModel) {
		boolean validInput = true;
		if(requestModel.getAddress().length() > 150) {
			validInput = false;
		}
		if(requestModel.getEmail().length() > 50) {
			validInput = false;

		}
		if(requestModel.getFirstName().length() > 20) {
			validInput = false;

		}
		if(requestModel.getLastName().length() > 20) {
			validInput = false;

		}
		if(requestModel.getPassword().length() > 20) {
			validInput = false;

		}
		if(requestModel.getPhoneNumber().length() > 10) {
			validInput = false;

		}
		if(requestModel.getTitle().length() > 5) {
			validInput = false;

		}
		return validInput;
		
	}
	
	//Add User validate input
	public boolean validateInput(UserAddingRequestModel requestModel) {
		boolean validInput = true;
		if(requestModel.getAddress().length() > 150) {
			validInput = false;
		}
		if(requestModel.getEmail().length() > 50) {
			validInput = false;

		}
		if(requestModel.getFirstName().length() > 20) {
			validInput = false;

		}
		if(requestModel.getLastName().length() > 20) {
			validInput = false;

		}
		if(requestModel.getPassword().length() > 20) {
			validInput = false;

		}
		if(requestModel.getPhoneNumber().length() > 10) {
			validInput = false;

		}
		if(requestModel.getTitle().length() > 5) {
			validInput = false;

		}
		return validInput;
		
	}

	public boolean delUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			//Checks if user has an order details and if so checks if order details has items in it
			if(orderDetailsService.checkIfUserHasOrderDetailsOrOrders(user.get())) {
				//this section of code is for when order details exists and it has items in it 
				return false;
			}else {
				//this section of code is for when order details exists and it has no items in it or if there is no order details
				if(user.get().getOrderDetails() != null) {
					//Deletes both user and order details row
					orderDetailsService.delOrderDetails(user.get().getOrderDetails().getOrderDetailsID());
				}else {
					userRepository.deleteById(id);
				}
				return true;
			}
		}else {
		return false;
		}
	}

	public UserDetailResponseModel getUserById(long id) {
		ModelMapper mapper = new ModelMapper();
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			UserDetailResponseModel response = mapper.map(user.get(), UserDetailResponseModel.class);
				return response;			
		}else {
		return null;
		}
	}

	//Only gets customers not administrators
	public List<UserDetailResponseModel> getAllUser() {
		ModelMapper mapper = new ModelMapper();
		List<UserDetailResponseModel> responseList = new ArrayList<>();
		
		List<Optional<User>> list = userRepository.findByisAdmin(false);
		
		if(!list.isEmpty()) {
			for (Optional<User> user : list) {
				UserDetailResponseModel modelObject = mapper.map(user.get(), UserDetailResponseModel.class);
				modelObject.setDateOfBirth(user.get().getDob());
				modelObject.setPhoneNumber(user.get().getPhone_number());
				responseList.add(modelObject);
			}
			return responseList;
		}else {
			return null;
		}
	}

//	public UserDetailResponseModel getUserByEmail(String email) {
//		ModelMapper mapper = new ModelMapper();
//		Optional<User> user = userRepository.findByEmail(email);
//		UserDetailResponseModel response = mapper.map(user.get(), UserDetailResponseModel.class);
//		response.setDateOfBirth(user.get().getDob());
//		response.setPhoneNumber(user.get().getPhone_number());
//		return response;
//	}
//
//	public boolean isUserAdmin(String email) {
//		ModelMapper mapper = new ModelMapper();
//		Optional<User> user = userRepository.findByEmail(email);
//		return user.get().isAdmin();		
//	}
}
