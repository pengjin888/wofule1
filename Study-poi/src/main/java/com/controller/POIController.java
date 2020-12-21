package com.controller;

import com.model.Customer;
import com.model.Tb_brand;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Package com
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-10-19 15:19
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@RestController
@RequestMapping("/POI")
public class POIController {

    @RequestMapping("/uploadexcel")
    public String importData(MultipartFile file){
        //上传文件
        try {
            InputStream inputStream = file.getInputStream();
            Workbook excel = WorkbookFactory.create(inputStream);
            Sheet sheet = excel.getSheetAt(0);
            List cuss = new ArrayList();
            for(int i = 3 ;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                Customer cus = new Customer();
                    for (int j = 0 ; j<= 6;j++){
                        Cell cell = row.getCell(j);
                        String s = poiCell(cell);
                        System.out.println(s);
                        switch (j){
                            case 0 :
                                cus.setCusid(Integer.parseInt(s));
                                break;
                            case 1:
                                cus.setCusname(s);
                                break;
                            case 2:
                                cus.setAddress(s);
                                break;
                            case 3:
                                cus.setContact(s);
                                break;
                            case 4:
                                cus.setTel(s);
                                break;
                            case 5:
                                cus.setEmail(s);
                                break;
                            case 6:
                                cus.setEmpid(Integer.parseInt(s));
                                break;
                            default:
                                System.out.println("error");
                                break;
                        }
                }
                cuss.add(cus);
            }
            System.out.println(cuss);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String poiCell(Cell cell){
        if (cell!=null){
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }else {
            return null;
        }

    }
    @RequestMapping("/export")
    public String export(HttpServletResponse response)  {

        List<Tb_brand> brands = Arrays.asList(new Tb_brand(250000,"kekou","","K",1),
                new Tb_brand(250001,"kekou","","K",2));
        HSSFWorkbook excel = new HSSFWorkbook();

        HSSFSheet sheets = excel.createSheet("品牌信息");

        HSSFRow row = sheets.createRow(0);
        String[] strs= {"编号","名称","图片","首字母","seq"};

        for (int  i = 0 ; i<strs.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(strs[i]);
        }
        for (int i = 1;i<=brands.size();i++){
            HSSFRow row1 = sheets.createRow(i);
            row1.createCell(0).setCellValue(brands.get(i-1).getId());
            row1.createCell(1).setCellValue(brands.get(i-1).getName());
            row1.createCell(2).setCellValue(brands.get(i-1).getImage());
            row1.createCell(3).setCellValue(brands.get(i-1).getLetter());
            row1.createCell(4).setCellValue(brands.get(i-1).getSeq());
        }
//        try {
//            FileOutputStream fos = new FileOutputStream( "F:\\Game\\444.xls");
//            excel.write(fos);
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String name = UUID.randomUUID().toString().replace("","");
        //实现web点击链接下载
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition","attachment；filename="+name+".xls");

        try {
            excel.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (response.getOutputStream()!=null){
                    response.getOutputStream().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载成功");
        return "success";
    }



}
