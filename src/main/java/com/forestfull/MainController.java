package com.forestfull;

import com.forestfull.tunneling.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final Test test;

    @GetMapping("/{path}")
    public String index(@PathVariable String path) {
        return test.test(path);
    }
}
