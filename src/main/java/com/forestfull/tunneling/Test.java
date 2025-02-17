package com.forestfull.tunneling;

import com.forestfull.logger.spring.Observable;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @Observable(arguments = true, returnValue = true)
    public String test(String input) {
        return "test" + input;
    }
}
