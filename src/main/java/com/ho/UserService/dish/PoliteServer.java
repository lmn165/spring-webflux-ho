package com.ho.UserService.dish;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class PoliteServer {
    private final KitchenService kitchen;
    Flux<Dish> doingMyJob(){
        return this.kitchen.getDishes()
                .doOnNext(dish -> System.out.println(String.format("Thank you for %s!", dish)))
                .doOnError(error -> System.out.println("So sorry about " + error.getMessage()))
                .doOnComplete(() -> System.out.println("Thanks for all your hard work!"))
                .map(Dish::deliver);
    }
}
