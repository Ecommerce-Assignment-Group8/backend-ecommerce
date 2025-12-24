package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dto.response.CartItemResponseDTO;
import com.example.backend.entity.CartItem;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.repository.CartItemRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<CartItemResponseDTO> addToCart(Integer userId, Integer productId, Integer quantity) {
        try {
            Optional<User> userOpt = userRepository.findById(userId);
            if (!userOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Optional<Product> productOpt = productRepository.findById(productId);
            if (!productOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Product product = productOpt.get();
            quantity = quantity == null || quantity < 1 ? 1 : quantity;

            if (product.getStockQuantity() < quantity) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
            
            CartItem cartItem;
            if (existingCartItem.isPresent()) {
                cartItem = existingCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cartItem = new CartItem();
                cartItem.setUser(userOpt.get());
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
            }

            cartItem = cartItemRepository.save(cartItem);
            return ResponseEntity.ok(toCartItemResponseDTO(cartItem));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<List<CartItemResponseDTO>> getCart(Integer userId) {
        try {
            List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
            List<CartItemResponseDTO> responses = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                responses.add(toCartItemResponseDTO(cartItem));
            }
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    public ResponseEntity<CartItemResponseDTO> updateCartItem(Integer cartItemId, Integer newQuantity) {
        try {
            Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
            if (!cartItemOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            CartItem cartItem = cartItemOpt.get();
            Product product = cartItem.getProduct();

            if (product.getStockQuantity() < newQuantity) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            if (newQuantity < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            cartItem.setQuantity(newQuantity);
            cartItem = cartItemRepository.save(cartItem);
            return ResponseEntity.ok(toCartItemResponseDTO(cartItem));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    public ResponseEntity<?> removeFromCart(Integer cartItemId) {
        try {
            Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
            if (!cartItemOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            cartItemRepository.deleteById(cartItemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    public ResponseEntity<?> removeFromCartByUserAndProduct(Integer userId, Integer productId) {
        try {
            cartItemRepository.deleteByUserIdAndProductId(userId, productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    public ResponseEntity<?> clearCart(Integer userId) {
        try {
            List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
            cartItemRepository.deleteAll(cartItems);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<List<CartItem>> getCartItemsByIds(List<Integer> cartItemIds) {
        try {
            List<CartItem> cartItems = cartItemRepository.findAllById(cartItemIds);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem) {
        CartItemResponseDTO dto = new CartItemResponseDTO();
        dto.setId(cartItem.getId());
        dto.setProductId(cartItem.getProduct().getId());
        dto.setProductName(cartItem.getProduct().getName());
        dto.setProductDescription(cartItem.getProduct().getDescription());
        dto.setProductPrice(cartItem.getProduct().getPrice());
        dto.setProductImage(cartItem.getProduct().getImage());
        dto.setQuantity(cartItem.getQuantity());
        dto.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        return dto;
    }
}
