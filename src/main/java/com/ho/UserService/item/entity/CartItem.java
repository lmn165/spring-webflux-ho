package com.ho.UserService.item.entity;

import lombok.Data;

@Data
public final class CartItem {
    private Item item;
    private int quantity;

    private CartItem() {}

    public CartItem(Item item){
        this.item = item;
        this.quantity = 1;
    }

    public void increment() {
    }
}
