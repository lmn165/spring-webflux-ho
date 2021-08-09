package com.ho.UserService.item.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public final class Item {
    private @Id String id;
    private String name;
    private double price;

    private Item() {}

    Item(String name, double price){
        this.name = name;
        this.price = price;
    }

}
