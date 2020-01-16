package com.example.demo_jdk8.mobile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private MobileProduct mobileProduct;

    @GetMapping("/test")
    public Map<String, String> test() {
        log.info("is ok!");
        return ResponseBase.response("200", "ok");
    }



    @PostMapping("/product")
    public Map<String, String> product(@RequestBody MobileReq mobileReq) throws IOException {
        log.info("product mobile ...");
        if (mobileReq.isBlank())
            return ResponseBase.response("400", "param error");
        try {
            mobileProduct.product(mobileReq);
        } catch (IOException e) {
            log.error("product mobile error：{}", e.getMessage(), e);
        }
        return ResponseBase.response("200", "ok");
    }
}
