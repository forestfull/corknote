package com.forestfull;

import com.forestfull.log.up.spring.Observable;
import com.forestfull.log.up.util.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Observable(arguments=true, returnValue = true)
    @GetMapping("/{pathname}")
    public String index(@PathVariable String pathname) {
        Log.info(pathname);

        return pathname;
    }
}
