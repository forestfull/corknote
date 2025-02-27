package com.forestfull.router;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/{pathname}")
    public String index(@PathVariable String pathname) {

        return pathname;
    }
}
