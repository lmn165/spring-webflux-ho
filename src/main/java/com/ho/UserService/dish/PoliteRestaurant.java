package com.ho.UserService.dish;

/**
 * 구독(subscription) 을 하기 전에는 어떤 연산도 일어나지 않는다.
 * kitchen.getDishes() 는 알바가 주방에 가서 요리를 가져오는 모습니다.
 * 요리가 완성되면 즉시 손님에게 전달한다... map()
 * Reactive Consumer 는 다른 Reactive Service 를 호출하고 결과를 변환(transform)한다.
 * 서빙 점원의 역할은 웹 컨트롤이다.
 * */

public class PoliteRestaurant {

    public static void main(String... args) {
        PoliteServer server = new PoliteServer(new KitchenService());
        server.doingMyJob().subscribe(
                dish -> System.out.println("Consuming " + dish),
                throwable -> System.err.println(throwable));
    }

}
