package com.yang.yangbi.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel 相关工具类
 */
@Slf4j
public class ExcelUtils {

    public static String excelToCsv(MultipartFile multipartFile) {
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("表格处理错误", e);
            e.printStackTrace();
        }

        // 转为csv
        StringBuilder stringBuilder = new StringBuilder();
        // 读取表头
        LinkedHashMap<Integer, String> headMap = (LinkedHashMap<Integer, String>) list.get(0);
        List<String> headList = headMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        stringBuilder.append(StringUtils.join(headList, ",")).append("\n");
        // 读取数据
        for (int i = 1; i < list.size(); i++) {
            LinkedHashMap<Integer, String> dataMap = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            stringBuilder.append(StringUtils.join(dataList, ",")).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * excelToList
     *
     * @param multipartFile
     * @return
     */
    public static Map<String, Object> excelToList(MultipartFile multipartFile) {
        List<Map<Integer, Object>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (Exception e) {
            log.error("表格处理错误", e);
            e.printStackTrace();
        }

        // 构造返回数据
        Map<String, Object> resutltDataMap = new HashMap<>();

        // 读取表头
        List<Object> headList = new ArrayList<>();
        LinkedHashMap<Integer, Object> headMap = (LinkedHashMap<Integer, Object>) list.get(0);
        headList = headMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        resutltDataMap.put("headList", headList);

        List<List<Object>> dataList = new ArrayList<>();
        // 转换为集合
        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<Integer, Object> dataMap = (LinkedHashMap<Integer, Object>) list.get(i);
            List<Object> data = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            dataList.add(data);
        }
        resutltDataMap.put("dataList", dataList);

        return resutltDataMap;
    }

    public static Map<String, Object> excelToList() {
        List<Map<Integer, Object>> list = null;
        try {
            list = EasyExcel.read(ResourceUtils.getFile("classpath:test_excel.xlsx"))
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (Exception e) {
            log.error("表格处理错误", e);
            e.printStackTrace();
        }

        // 构造返回数据
        Map<String, Object> resutltDataMap = new HashMap<>();

        // 读取表头
        List<Object> headList = new ArrayList<>();
        LinkedHashMap<Integer, Object> headMap = (LinkedHashMap<Integer, Object>) list.get(0);
        headList = headMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        resutltDataMap.put("headList", headList);

        List<List<Object>> dataList = new ArrayList<>();
        // 转换为集合
        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<Integer, Object> dataMap = (LinkedHashMap<Integer, Object>) list.get(i);
            List<Object> data = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            dataList.add(data);
        }
        resutltDataMap.put("dataList", dataList);

        return resutltDataMap;
    }
}
