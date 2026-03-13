package com.logey.aimin.drug.Controller;

import com.logey.aimin.base.response.Result;
import com.logey.aimin.drug.Service.DrugService;
import com.logey.aimin.drug.entity.Drug;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @GetMapping("/categoryId/{categoryId}")
    public Result<?> findDrugByCategoryId(@PathVariable Integer categoryId) {
        List<Drug> list = drugService.findDrugByCategoryId(categoryId);
        return Result.success(list);
    }
    @GetMapping("/get/{id}")
    public Result<Drug> get(@PathVariable Integer id){
        return Result.success(drugService.get(id));
    }

    @GetMapping("/update")
    public Result<?> update() {
        Drug drug = new Drug();
        drug.setDrugId(27);
        drug.setName("复方板蓝根颗粒");
        drug.setGenericName("板蓝根");
        drug.setCategoryId(8);
        drug.setBrand("白云山");
        drug.setManufacturer("广州白云山和记黄埔中药有限公司");
        drug.setDescription("具有清热解毒，凉血利咽的功效。用于风热感冒、咽喉肿痛、口咽干燥。");
        drug.setDosageForm("颗粒剂");
        drug.setStrength("10g*20袋");
        drug.setPrice(new BigDecimal("32.00"));
        drug.setDiscountPrice(null);  // 无折扣
        drug.setPrescription(false);
        drug.setShelfLife(24);
        drug.setShelfLifeUnit(1);
        drug.setStorageInstructions("遮光、密封保存");
        drug.setCreateAt(LocalDateTime.now());
        drug.setUpdateAt(LocalDateTime.now());
        drug.setStatus((byte) 1);
        drug.setDeleted(false);
        drug.setCreatorId(1);
        drug.setSku("SKU12345");
        drug.setBarCode("6924567890123");
        drug.setStockQuantity(100);
        drugService.update(drug);
        return Result.success();
    }

    @GetMapping("/delete/{id}")
    public void delete(){
        drugService.delete(27);
    }
}
