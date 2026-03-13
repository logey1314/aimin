package com.logey.aimin.drug.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.logey.aimin.drug.entity.Drug;
import com.logey.aimin.drug.Mapper.DrugMapper;
import com.logey.aimin.drug.Service.DrugService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 药品表，用于存储具体药品的信息和库存状态 服务实现类
 *
 * @author logey
 * @since 2026/03/03
 */
@Service
@RequiredArgsConstructor
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements DrugService {

    private final DrugMapper drugMapper;


    @Override
    @Cacheable(value = "drugCache", key = "#categoryId")
    public List<Drug> findDrugByCategoryId(Integer categoryId) {
        LambdaQueryWrapper<Drug> eq = new LambdaQueryWrapper<Drug>().eq(Drug::getCategoryId, categoryId);
        return list(eq);
    }

    @Cacheable(value = "drugCache", key = "#id")
    // public ValueWrapper get(Object key)   put
    public Drug get(Integer id) {
        return drugMapper.selectById(id);
    }

    @CachePut(value = "drugCache", key = "#drug.drugId")
    // put
    public void update(Drug drug) {
        drugMapper.updateById(drug);
    }

    @CacheEvict(value = "drugCache", key = "#id")
    // evict
    public void delete(Integer id) {
        drugMapper.deleteById(id);
    }


}
