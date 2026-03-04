package com.logey.aimin.drug.Mapper;

import com.logey.aimin.drug.entity.Drug;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药品表，用于存储具体药品的信息和库存状态 Mapper 接口
 *
 * @author logey
 * @since 2026/03/03
 */
@Mapper
public interface DrugMapper extends MPJBaseMapper<Drug> {
}
