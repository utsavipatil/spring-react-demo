package com.utsavi.spring_react_demo.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {
    private static final Map<String, String> USER_CATEGORY = Map.of(
            "Dolly" , "standard",
            "Prashant" , "prime"
    );

    static Function<Context, Context> userCategoryContext(){
        return context -> context.getOrEmpty("user")
                .filter(user -> USER_CATEGORY.containsKey(user))
                .map(user -> USER_CATEGORY.get(user))
                .map(category -> context.put("category", category))
                .orElse(Context.empty());
    }
}
