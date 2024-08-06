package com.codeWithProjects.ecom.controller.customer;

import com.codeWithProjects.ecom.dto.WishlistDto;
import com.codeWithProjects.ecom.services.customer.wishlist.WishlistService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto) {
        if (wishlistDto.getUserId() == null || wishlistDto.getProductId() == null) {
            logger.error("Invalid WishlistDto: userId or productId is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID and Product ID must not be null");
        }

        WishlistDto postedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);

        if (postedWishlistDto == null) {
            logger.error("Failed to add product to wishlist: {}", wishlistDto);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product could not be added to the wishlist");
        }

        logger.info("Product added to wishlist successfully: {}", postedWishlistDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<?> getWishlistByUserId(@PathVariable Long userId) {
        if (userId == null) {
            logger.error("Invalid userId: null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID must not be null");
        }

        List<WishlistDto> wishlist = wishlistService.getWishlistByUserId(userId);

        if (wishlist.isEmpty()) {
            logger.info("Wishlist is empty for userId: {}", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No wishlist found for the given user ID");
        }

        logger.info("Retrieved wishlist for userId: {}", userId);
        return ResponseEntity.ok(wishlist);
    }
}
