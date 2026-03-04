package com.logey.aimin.drug.Service.impl;

import com.logey.aimin.drug.entity.Drug;
import com.logey.aimin.drug.Mapper.DrugMapper;
import com.logey.aimin.drug.Service.DrugService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 药品表，用于存储具体药品的信息和库存状态 服务实现类
 *
 * @author logey
 * @since 2026/03/03
 */
@Service
@RequiredArgsConstructor
public class DrugServiceImpl
        extends ServiceImpl<DrugMapper, Drug> implements DrugService {

}
