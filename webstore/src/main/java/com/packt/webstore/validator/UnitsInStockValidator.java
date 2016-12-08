package com.packt.webstore.validator;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

@Component
public class UnitsInStockValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);		
	}
	
	public void validate(Object target, Errors errors) {
		Product product = (Product)target; 
		
		if (product.getUnitPrice() != null && 
				new BigDecimal(10000).compareTo(product.getUnitPrice()) <= 0 && 
				product.getUnitsInStock() > 99) {
			errors.rejectValue("unitsInStock", 
					"com.packt.webstore.validtor.UnitsInStockValidator.message");
		}
	}

	public BeanDescriptor getConstraintsForClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Set<ConstraintViolation<T>> validate(T arg0, Class<?>... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Set<ConstraintViolation<T>> validateProperty(T arg0, String arg1, Class<?>... arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> arg0, String arg1, Object arg2, Class<?>... arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
