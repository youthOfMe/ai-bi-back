package com.yang.yangbi.model.dto.chart;

import lombok.Data;

@Data
public class GenChartByAiRequest {

    /**
     * 名字
     */
    private String name;

    /**
     * 目标
     */
    private String goal;

    /***
     * 图表类型
     */
    private String chartType;
}
