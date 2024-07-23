package com.codeWithProjects.ecom.services.customer.cart;

import com.codeWithProjects.ecom.dto.AddProductInCartDto;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
}
