package com.example.demo_jdk8.idcard;

import com.example.demo_jdk8.area.AreaInfo;
import com.example.demo_jdk8.mobile.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/card")
public class IdCardController {

    @Autowired
    private IdCardProduct idCardProduct;

    @GetMapping("/test")
    public Map<String, String> test() {
        log.info("is ok!");
        return ResponseBase.response("200", "ok");
    }

    @PostMapping("/product")
    public Map<String, String> product(@RequestBody IdCardReq idCardReq) {
        log.info("product idCard ...");
        if (idCardReq.isBlank())
            return ResponseBase.response("400", "param error");

        List<String> areaInfos = AreaInfo.AREAS;
        if (idCardReq.getAreas() != null && idCardReq.getAreas().size() > 0)
            areaInfos.addAll(idCardReq.getAreas());
        else if (idCardReq.getExcludeAreas() != null && idCardReq.getExcludeAreas().size() > 0 )
            areaInfos.removeAll(idCardReq.getExcludeAreas());
        try {
            idCardProduct.product(idCardReq, areaInfos);
        } catch (IOException e) {
            log.error("product idCard error：{}", e.getMessage(), e);
        }
        return ResponseBase.response("200", "ok");
    }

}
