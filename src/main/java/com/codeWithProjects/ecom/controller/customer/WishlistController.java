package com.codeWithProjects.ecom.controller.customer;

import com.codeWithProjects.ecom.services.customer.wishlist.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
}
