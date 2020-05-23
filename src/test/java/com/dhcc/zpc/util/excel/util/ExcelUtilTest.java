package com.dhcc.zpc.util.excel.util;

import com.alibaba.excel.metadata.Sheet;
import com.dhcc.zpc.util.excel.entity.TableHeaderExcelProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelUtilTest {

    /**
     * @Author 赵朋超
     * @Description 读取少于1000行的excel
     * @Date 12:57 2020/2/23
     **/
    @Test
    public void readLessThan1000RowTest() {
        String filePath = "E:\\data\\test.xlsx";
        List<Object> objects = ExcelUtil.readLessThan1000Row(filePath);
        objects.forEach(System.out::println);
    }

    /**
     * @Author 赵朋超
     * @Description 读取少于1000行的excel，可以指定sheet和从第几行读起
     * @Date 12:59 2020/2/23
     **/
    @Test
    public void readLessThan1000RowBySheetTest() {
        String filePath = "E:\\data\\test.xlsx";
        Sheet sheet = new Sheet(2, 2);// sheet从1开始，行数从0开始
        List<Object> objects = ExcelUtil.readLessThan1000RowBySheet(filePath, sheet);
        objects.forEach(System.out::println);
    }

    /**
     * @Author 赵朋超
     * @Description 读取超过1000行的excel
     * 带sheet参数的方法可参照测试方法readMoreThan1000RowBySheet()
     * @Date 13:05 2020/2/23
     **/
    @Test
    public void readMoreThan1000RowTest() {
        String filePath = "E:\\data\\test.xlsx";
        List<Object> objects = ExcelUtil.readMoreThan1000Row(filePath);
        objects.forEach(System.out::println);
    }

    /**
     * @return void
     * @Author 赵朋超
     * @Description 读取多于1000行的excel，可以指定sheet和从第几行读起
     * @Date 13:11 2020/2/23
     **/
    @Test
    public void readMoreThan1000RowBySheetTest() {
        String filePath = "E:\\data\\test.xlsx";
        Sheet sheet = new Sheet(2, 0);// sheet从1开始，行数从0开始
        List<Object> objects = ExcelUtil.readMoreThan1000RowBySheet(filePath, sheet);
        objects.forEach(System.out::println);
    }

    /**
     * @return void
     * @Author 赵朋超
     * @Description 生成excel
     * @Date 13:16 2020/2/23
     **/
    @Test
    public void writeBySimpleTest() {
        String filePath = "E:\\data\\测试.xlsx";
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("111", "222", "333"));
        data.add(Arrays.asList("111", "222", "333"));
        data.add(Arrays.asList("111", "222", "333"));
        List<String> head = Arrays.asList("表头1", "表头2", "表头3");
        ExcelUtil.writeBySimple(filePath, data, head);
    }

    /**
     * @Author 赵朋超
     * @Description 生成excel，带有模型
     * @Date 13:32 2020/2/23
     * @return void
     **/
    @Test
    public void writeWithTemplateTest() {
        String filePath = "E:\\data\\测试1.xlsx";
        ArrayList<TableHeaderExcelProperty> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
            tableHeaderExcelProperty.setName("赵朋超" + i);
            tableHeaderExcelProperty.setAge(22 + i);
            tableHeaderExcelProperty.setSchool("清华大学" + i);
            data.add(tableHeaderExcelProperty);
        }
        ExcelUtil.writeWithTemplate(filePath, data);
    }

    /**
     * @Author 赵朋超
     * @Description 生成excel，包含多个sheet
     * @Date 13:36 2020/2/23
     * @return void
     **/
    @Test
    public void writeWithMultipleSheetTest(){
        ArrayList<ExcelUtil.MultipleSheelPropety> list1 = new ArrayList<>();
        for(int j = 1; j < 4; j++){
            ArrayList<TableHeaderExcelProperty> list = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
                tableHeaderExcelProperty.setName("cmj" + i);
                tableHeaderExcelProperty.setAge(22 + i);
                tableHeaderExcelProperty.setSchool("清华大学" + i);
                list.add(tableHeaderExcelProperty);
            }

            Sheet sheet = new Sheet(j, 0);
            sheet.setSheetName("sheet" + j);

            ExcelUtil.MultipleSheelPropety multipleSheelPropety = new ExcelUtil.MultipleSheelPropety();
            multipleSheelPropety.setData(list);
            multipleSheelPropety.setSheet(sheet);

            list1.add(multipleSheelPropety);
        }
        ExcelUtil.writeWithMultipleSheel("E:\\data\\测试2.xlsx",list1);
    }
}