package com.yang.yangbi.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.yangbi.model.dto.chart.ChartQueryRequest;
import com.yang.yangbi.model.entity.Chart;

/**
* @author 20406
* @description 针对表【chart(图表信息表)】的数据库操作Service
* @createDate 2024-07-11 09:22:10
*/
public interface ChartService extends IService<Chart> {

    Wrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest);
}
