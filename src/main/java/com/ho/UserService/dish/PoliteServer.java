package com.ho.UserService.dish;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
/**
 * 주방장의 인스턴트를 생성자 주입으로 받는다.(연관관계 - association)
 * 주방장(KitchenService) 가 없으면 PoliteServer의 기능은 동작하지 않는다.
 * kitchen.getDishes() 는 알바가 주방에 가서 요리를 가져오는 모습니다.
 * 요리가 완성되면 즉시 손님에게 전달한다. ... map()
 * Reactive Consumer 는 다른 Reactive Service 를 호출하고 결과를 반환(transform) 한다.
 * */
@RequiredArgsConstructor
public final class PoliteServer {

    private final KitchenService kitchen;

    Flux<Dish> doingMyJob(){
        return this.kitchen.getDishes()
                .doOnNext(dish -> System.out.println(String.format("Thank you for %s!", dish)))
                .doOnError(error -> System.out.println("So sorry about " + error.getMessage()))
                .doOnComplete(() -> System.out.println("Thanks for all your hard work!"))
                .map(Dish::deliver);
    }
}
