package com.logey.aimin.drug.Controller;

import com.logey.aimin.base.response.Result;
import com.logey.aimin.drug.Service.DrugService;
import com.logey.aimin.drug.entity.Drug;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 药品表，用于存储具体药品的信息和库存状态 前端控制器
 * </p>
 *
 * @author logey
 * @since 2026/03/03
 */
@RestController
@RequestMapping("/drug")
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @RequestMapping("/getAll")
    public Result<?> getAll(){
        List<Drug> list = drugService.list();
        return Result.success(list);
    }


    @RequestMapping("/list")
    public Result<?> getAll1(){
        List<Drug> list = drugService.list();
        return Result.success(list);
    }

    @RequestMapping("/getById1")
    public Result<?> getById1(@RequestParam("id") String id){
        Drug drug = drugService.getById(id);
        return Result.success(drug);
    }

    @RequestMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id){
        Drug drug = drugService.getById(id);
        return Result.success(drug);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id){
        drugService.removeById(id);
        return Result.success();
    }

}
