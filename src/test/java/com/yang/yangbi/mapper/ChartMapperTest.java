package com.yang.yangbi.mapper;

import com.yang.yangbi.utils.ExcelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class ChartMapperTest {

    @Resource
    private ChartMapper chartMapper;

    @Test
    void testCreateChartDataTable() {
        Map<String, Object> map = ExcelUtils.excelToList();
        map.put("id", 123);
        chartMapper.createChartDataTable(map);
    }
}
