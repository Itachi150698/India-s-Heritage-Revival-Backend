package com.codeWithProjects.ecom.services.customer.cart;

import com.codeWithProjects.ecom.dto.AddProductInCartDto;
import com.codeWithProjects.ecom.entity.CartItems;
import com.codeWithProjects.ecom.entity.Order;
import com.codeWithProjects.ecom.entity.Product;
import com.codeWithProjects.ecom.entity.User;
import com.codeWithProjects.ecom.repository.OrderRepository;
import com.codeWithProjects.ecom.repository.ProductRepository;
import com.codeWithProjects.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        try {
            Order order = orderRepository.findById(addProductInCartDto.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            Product product = productRepository.findById(addProductInCartDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            User user = userRepository.findById(addProductInCartDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            CartItems cartItem = new CartItems();
            cartItem.setOrder(order);
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(1L); // or your business logic for quantity
            cartItem.setPrice(product.getPrice());

            if (order.getCartItems() == null) {
                order.setCartItems(new ArrayList<>());
            }

            order.getCartItems().add(cartItem);
            orderRepository.save(order);

            return ResponseEntity.ok("Product added to cart successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding product to cart: " + e.getMessage());
        }
    }
}
