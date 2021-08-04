package com.ho.UserService.dish;

import lombok.Data;

@Data
public class Dish {
    private  String description;
    private boolean delivered = false;

    public static Dish deliver(Dish dish){
        Dish deliveredDish = new Dish(dish.description);
        deliveredDish.delivered = true;
        return deliveredDish;
    }

    public Dish(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "description='" + description + '\'' +
                "delivered='" + delivered + '\'' +
                '}';
    }
}
