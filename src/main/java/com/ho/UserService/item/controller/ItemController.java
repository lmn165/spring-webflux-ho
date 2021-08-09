package com.ho.UserService.item.controller;

import com.ho.UserService.item.entity.Cart;
import com.ho.UserService.item.entity.CartItem;
import com.ho.UserService.item.repository.CartRepository;
import com.ho.UserService.item.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@RestController
public final class ItemController {
    ItemRepository itemRepository;
    CartRepository cartRepository;

    @GetMapping
    Mono<?> home(){
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", this.itemRepository.findAll())
            .modelAttribute("cart",
                this.cartRepository.findById("My Cart")
                    .defaultIfEmpty(new Cart("My Cart")))
            .build());
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id){
        return this.cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart"))
                .flatMap(cart -> cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getItem()
                        .getId().equals(id))
                .findAny()
                .map(cartItem -> {
//                    cartItem.increment();
                    return Mono.just(cart);
                })
                .orElseGet(() ->{
                    return this.itemRepository.findById(id)
                        .map(item -> new CartItem(item))
                        .map(cartItem -> {
                            cart.getCartItems().add(cartItem);
                            return cart;
                        });
                }))
            .flatMap(cart -> this.cartRepository.save(cart))
            .thenReturn("redirect:/");
    }
}
