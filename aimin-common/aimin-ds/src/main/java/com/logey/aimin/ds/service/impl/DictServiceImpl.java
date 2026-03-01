package com.logey.aimin.ds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logey.aimin.ds.mapper.DictMapper;
import com.logey.aimin.ds.service.DictService;
import com.logey.aimin.ds.entity.Dict;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *  字典表  服务实现类
 *
 * @author logey
 * @since 2026/02/01
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

}
