package com.logey.aimin.admin.fegin;

import com.logey.aimin.base.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "aimin-drug", path = "/aimin-drug")
public interface DrugFeignClient {

    @RequestMapping(value = "/drug/list", method = RequestMethod.GET)
    Result<?> list();


}