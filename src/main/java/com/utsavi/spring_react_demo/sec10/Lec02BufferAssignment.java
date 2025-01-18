package com.utsavi.spring_react_demo.sec10;

import com.utsavi.spring_react_demo.assignment.RevenueReport;
import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec02BufferAssignment {
    public static final Logger log = LoggerFactory.getLogger(Lec02BufferAssignment.class);

    public static void main(String[] args) {
        bookSell()
                .buffer(Duration.ofSeconds(5))
                .map(
                        bookOrders -> {
                            final int[] scienceRevenue = {0};
                            final int[] fantancyRevenue = {0};
                            final int[] suspenseRevenue = {0};
                            bookOrders.forEach(bookOrder -> {
                                switch (bookOrder.genre) {
                                    case "Science fiction":
                                        scienceRevenue[0] = scienceRevenue[0] + bookOrder.price;
                                        break;
                                    case "Fantasy":
                                        fantancyRevenue[0] = fantancyRevenue[0] + bookOrder.price;
                                        break;
                                    case "Suspense/Thriller":
                                        suspenseRevenue[0] = suspenseRevenue[0] + bookOrder.price;
                                        break;
                                    default:
                                        break;

                                }
                            });
                            return new Revenue(scienceRevenue[0], fantancyRevenue[0], suspenseRevenue[0]);
                        })
                .subscribe(Util.subscriber());

//        Instructor approach
        var allowedCategories = Set.of("Science fiction", "Fantasy", "Suspense/Thriller");
        orderStream()
                .filter(order -> allowedCategories.contains(order.genre()))
                        .buffer(Duration.ofSeconds(5))
                                .map(Lec02BufferAssignment ::generateReport)
                                        .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }

    private static Flux<BookOrder> bookSell() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new BookOrder(Util.faker().book().genre(), Util.faker().book().title(), Util.faker().random().nextInt(10, 100)))
                .transform(Util.fluxLogger("producer1"));
    }

//    Instructor method to get orderStream every 200ms
    private static Flux<com.utsavi.spring_react_demo.assignment.BookOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> com.utsavi.spring_react_demo.assignment.BookOrder.create());
    }
//    Instructor method - to get report of EVERY GENRE
    private static RevenueReport generateReport(List<com.utsavi.spring_react_demo.assignment.BookOrder> orders){
        Map<String, Integer> revenue = orders.stream()
                .collect(Collectors.groupingBy(
                        com.utsavi.spring_react_demo.assignment.BookOrder::genre,
                        Collectors.summingInt(com.utsavi.spring_react_demo.assignment.BookOrder::price)
                ));
        return new RevenueReport(LocalTime.now(), revenue);
    }

    record BookOrder(String genre, String title, Integer price) {
    }

    record Revenue(Integer ScienceFiction, Integer Fantasy, Integer Suspense) {
    }

}
