package com.yang.yangbi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.yangbi.model.entity.Chart;

import java.util.List;
import java.util.Map;

/**
* @author 20406
* @description 针对表【chart(图表信息表)】的数据库操作Mapper
* @createDate 2024-07-11 09:22:10
* @Entity com.yupi.springbootinit.model.entity.Chart
*/
public interface ChartMapper extends BaseMapper<Chart> {

    List<Map<String, Object>> queryChartData(String querySql);
}




