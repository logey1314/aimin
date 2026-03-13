package com.logey.aimin.admin.controller.drug;

import com.logey.aimin.admin.fegin.DrugFeignClient;
import com.logey.aimin.base.response.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drug")
@RequiredArgsConstructor
public class DrugController {
    private final DrugFeignClient drugFeignClient;

    @GetMapping("/list")
    public Result<?> all(){

         return  drugFeignClient.list();
    }

}
