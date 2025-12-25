package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.response.CartItemResponseDTO;
import com.example.backend.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItemResponseDTO> addToCart(
            @RequestParam Integer userId,
            @RequestParam Integer productId,
            @RequestParam(required = false, defaultValue = "1") Integer quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponseDTO>> getCart(@PathVariable Integer userId) {
        return cartService.getCart(userId);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItemResponseDTO> updateCartItem(
            @PathVariable Integer cartItemId,
            @RequestParam Integer quantity) {
        return cartService.updateCartItem(cartItemId, quantity);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Integer cartItemId) {
        return cartService.removeFromCart(cartItemId);
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<?> removeFromCartByUserAndProduct(
            @PathVariable Integer userId,
            @PathVariable Integer productId) {
        return cartService.removeFromCartByUserAndProduct(userId, productId);
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable Integer userId) {
        return cartService.clearCart(userId);
    }
}
