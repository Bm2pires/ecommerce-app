package com.ecommerce.spring.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.spring.entities.Products;
import com.ecommerce.spring.entities.User;
import com.ecommerce.spring.repositories.ProductRepository;
import com.ecommerce.spring.reqresmodels.ProductAddRequestModel;
import com.ecommerce.spring.reqresmodels.ProductAddResponseModel;
import com.ecommerce.spring.reqresmodels.ProductEditRequestModel;
import com.ecommerce.spring.reqresmodels.ProductEditResponseModel;
import com.ecommerce.spring.reqresmodels.UserDetailRequestModel;
import com.ecommerce.spring.reqresmodels.UserDetailResponseModel;

@Service
public class ProductService {

	@Autowired
	ProductRepository prodRepo;

	public ProductEditResponseModel editProd(ProductEditRequestModel requestModel) {
		ModelMapper mapper = new ModelMapper();
		Optional<Products> product = prodRepo.findById(requestModel.getProductId());
		boolean valid = validateInput(requestModel);
		boolean duplicateFields = checkUniqueFields(requestModel);
		if(valid && duplicateFields) {
			if(product != null) {		
				product.get().setProductName(requestModel.getProductName());
				product.get().setProductDescription(requestModel.getProductDescription());
				product.get().setProductPrice(requestModel.getProductPrice());

				prodRepo.save(product.get());
				ProductEditResponseModel response = mapper.map(requestModel, ProductEditResponseModel.class);

				return response;
			}else {
				return null;
			}
		}else {
			return null;
		}

	}
	
	public ProductAddResponseModel editProd(ProductAddRequestModel requestModel) {
		ModelMapper mapper = new ModelMapper();
		Products product = new Products();
		boolean valid = validateInput(requestModel);
		boolean duplicateFields = checkUniqueFields(requestModel);
		if(valid && duplicateFields) {
			if(product != null) {		
				product.setProductName(requestModel.getProductName());
				product.setProductDescription(requestModel.getProductDescription());
				product.setProductPrice(requestModel.getProductPrice());

				prodRepo.save(product);
				ProductAddResponseModel response = mapper.map(requestModel, ProductAddResponseModel.class);

				return response;
			}else {
				return null;
			}
		}else {
			return null;
		}

	}
	
	public boolean checkUniqueFields(ProductEditRequestModel requestModel) {
		boolean uniqueName = checkUniqueName(requestModel.getProductName(), requestModel.getProductId());
		boolean uniqueDesc = checkUniqueName(requestModel.getProductDescription(), requestModel.getProductId());

		if(uniqueName && uniqueDesc) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkUniqueFields(ProductAddRequestModel requestModel) {
		boolean uniqueName = checkUniqueName(requestModel.getProductName(), 0);
		boolean uniqueDesc = checkUniqueName(requestModel.getProductDescription(), 0);

		if(uniqueName && uniqueDesc) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean checkUniqueName(String name, long id) {
		Optional<Products> prodCheck = prodRepo.findByProductName(name);
		if(prodCheck.isEmpty()) {
			return true;
		}else {
			if(prodCheck.get().getProductId() == id) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean checkUniqueDesc(String desc, long id) {
		Optional<Products> prodCheck = prodRepo.findByProductName(desc);
		if(prodCheck.isEmpty()) {
			return true;
		}else {
			if(prodCheck.get().getProductId() == id) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean validateInput(ProductEditRequestModel requestModel) {
		boolean validInput = true;
		if(requestModel.getProductName().length() > 50) {
			validInput = false;
		}
		if(requestModel.getProductDescription().length() > 250) {
			validInput = false;
		}
		if(requestModel.getProductPrice() == null) {
			validInput = false;
		}

		return validInput;
		
	}
	
	public boolean validateInput(ProductAddRequestModel requestModel) {
		boolean validInput = true;
		if(requestModel.getProductName().length() > 50) {
			validInput = false;
		}
		if(requestModel.getProductDescription().length() > 250) {
			validInput = false;
		}
		if(requestModel.getProductPrice() == null) {
			validInput = false;
		}

		return validInput;
		
	}

	//Need to check wheather anyone has this product in their order_items table
	public boolean delProd(long id) {
		ModelMapper mapper = new ModelMapper();
		Optional<Products> product = prodRepo.findById(id);
		if(product.isPresent()) {
			product.get().setBrand(null);
			product.get().setCategories(null);
			ProductEditResponseModel response = mapper.map(product, ProductEditResponseModel.class);
			prodRepo.save(product.get());
			prodRepo.deleteById(id);
			return true;
		}else {
		return false;
		}

	}
	
	
}
