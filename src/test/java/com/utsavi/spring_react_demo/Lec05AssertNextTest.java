package com.utsavi.spring_react_demo;

import com.utsavi.spring_react_demo.common.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

/* "assertNext" is a method in StepVerifier
*   assertNext = consumeNextWith
*   We can also collect all items and test
*  */
public class Lec05AssertNextTest {
    record Book(int id, String author, String title){
    }

    private Flux<Book> getBooks(){
        return Flux.range(1,3)
                .map(value -> new Book(value, Util.faker().book().author(), Util.faker().book().title()));
    }

    @Test
    public void assertNextTest(){
        StepVerifier.create(getBooks())
                .assertNext(book -> Assertions.assertEquals(1,book.id()))
                .thenConsumeWhile(book -> Objects.nonNull(book.title()))
                .expectComplete()
                .verify();
    }

    @Test
    public void collectAllAndTest(){
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .verifyComplete();
    }
}
