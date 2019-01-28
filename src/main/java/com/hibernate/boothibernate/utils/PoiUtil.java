package com.hibernate.boothibernate.utils;

import com.hibernate.boothibernate.utils.annotation.Excel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lushun
 * @date: 2018/12/29 10:51
 * Title: Controller
 */

public class PoiUtil<T> {

    private static Logger logger = LoggerFactory.getLogger(PoiUtil.class);

    //根据文件后缀生成响应的工作簿，我这里只支持xlsx格式
    public Workbook getWorkBook(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename().toLowerCase();
        try {
            if (fileName.endsWith("xlsx")) {
                return new XSSFWorkbook(file.getInputStream());
            } else if (fileName.endsWith("xls")) {
                return new HSSFWorkbook(file.getInputStream());
            } else {
                throw new Exception("excel文件类型错误");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception("文件格式错误");
        }
    }


    /**
     * 导入
     *
     * @param workbook 工作簿
     * @param clazz    对应实体类
     * @return
     */
    public List<T> importExcel(Workbook workbook, Class<T> clazz) {

        //利用反射获取我在注解里定义好的字段顺序
//        String[] fields = clazz.getAnnotation(Excel.class).value();
        Field[] fields = clazz.getDeclaredFields();

        //返回的对象列表
        List<T> list = new ArrayList<>();
        int columnIndex;
        Cell cell;
        T t;

        //获取第一个工作簿，只支持解析第一个工作簿
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            //第一次循环  表头跳过
            if (row == sheet.getRow(0)) {
                continue;
            }
            //第二次往后
            try {
                t = clazz.newInstance();
                //row.getLastCellNum()获取的不是行数，是下标，所以+1
                for (Field f : fields) {
                    // 设置属性可访问
                    f.setAccessible(true);
                    // 判断是否是注解
                    if (f.isAnnotationPresent(Excel.class)) {
                        // 获取列索引
                        columnIndex = f.getAnnotation(Excel.class).columnIndex();
                        // 获取单元格
                        cell = row.getCell(columnIndex);
                        // 设置属性
                        if (cell != null) {
                            if (f.getType() == int.class || f.getType() == Integer.class) {
                                f.set(t, Integer.valueOf(getCellStringVal(cell)));
                            } else {
                                f.set(t, getCellStringVal(cell));
                            }
                        }
                    }
                }
                list.add(t);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return list;
    }

    /**
     * 导出
     *
     * @param fileName
     * @param list
     * @param response
     * @param clazz
     */
    public void exportData(String fileName, List<T> list, HttpServletResponse response, Class<T> clazz) {
        Field[] field = clazz.getDeclaredFields();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = null;
        try {
            //获取注解中定义好的中文表头顺序
            String[] title = clazz.getAnnotation(Excel.class).title();
            //获取注解中定义好的字段顺序
            String[] fields = clazz.getAnnotation(Excel.class).value();
            for (int i = 0; i <= list.size(); i++) {
                row = sheet.createRow(i);
                Cell cell = null;
                if (i == 0) { //第一次创建标题行
                    for (int j = 0; j < title.length; j++) {
                        cell = row.createCell(j);
                        //安顺序设置excel表头
                        cell.setCellValue(title[j]);
                    }
                    continue;
                }
                //第二次循环开始设置数据
                T t = list.get(i - 1);
                for (int j = 0; j < fields.length; j++) {
                    Field f = t.getClass().getDeclaredField(fields[j]);
                    f.setAccessible(true);
                    cell = row.createCell(j);
                    if (f.get(t) != null) { //此条数据的该字段有值
                        cell.setCellValue(f.get(t).toString());
                    }
                }
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("multipart/form-data");
            workbook.write(output);
            output.close();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String getCellStringVal(Cell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()); //日期型
                } else {
                    // 解决问题：1，科学计数法(如2.6E+10)，2，超长小数小数位不一致（如1091.19649281798读取出1091.1964928179796），3，整型变小数（如0读取出0.0）
                    return NumberToTextConverter.toText(cell.getNumericCellValue());
                }
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return "";
        }

    }

}
