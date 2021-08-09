package com.ho.UserService.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public final class RestaurantController {
    private final RtrtKitchenService kitchen;
    @GetMapping(value = "/dishes", produces = "application/json; charset=utf8")
    Flux<RtrtDish> serveDishes(){
        return kitchen.getDishes();
    }
}
