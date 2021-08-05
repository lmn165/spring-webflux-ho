package com.ho.UserService.dish;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Data class RtrtDish {
    private String description;
    private boolean delivered = false;

    RtrtDish(String description){
        this.description = description;
    }

    public static RtrtDish deliver(RtrtDish dish){
        RtrtDish deliveredDish = new RtrtDish(dish.description);
        deliveredDish.delivered = true;
        return deliveredDish;
    }
    @Override
    public String toString(){
        return delivered ? description +" 먹는다" : description +" 기다린다";
    }
}
@Service
class RtrtKitchenService {
    Flux<RtrtDish> getDishes(){
        return Flux.just(new RtrtDish("김치찌개"),
                new RtrtDish("떡볶이"),
                new RtrtDish("삼계탕"));
    }
}
@RequiredArgsConstructor class PrototypeServer {
    private final RtrtKitchenService kitchen;
    Flux<RtrtDish> doingMyJob(){
        return kitchen.getDishes().map(dish -> RtrtDish.deliver((dish)));
    }
}
@RequiredArgsConstructor class AdvancedServer {
    private final RtrtKitchenService kitchen;

    Flux<RtrtDish> doingMyJob(){
        return kitchen.getDishes()
                .doOnNext(dish -> System.out.println(dish))
                .doOnError(error -> System.out.println(error.getMessage()))
                .doOnComplete(() -> System.out.println("모든 주문이 완료되었다"))
                .map(RtrtDish::deliver);
    }
}
public class Restaurant {
    public void subscribe() {
        AdvancedServer server = new AdvancedServer(new RtrtKitchenService());
        server.doingMyJob().subscribe(
                dish -> System.out.println(dish),
                throwable -> System.out.println(throwable)
        );
    }
}
