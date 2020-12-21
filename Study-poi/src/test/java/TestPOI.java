import com.model.Tb_brand;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Package PACKAGE_NAME
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-10-19 9:03
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
public class TestPOI {
    public static void main(String[] args) {

//        //poi基本对象操作
//        //poi基本操做
//        //excel工作簿
//        HSSFWorkbook excel = new HSSFWorkbook();
//        //sheet对象
//        HSSFSheet sheet = excel.createSheet();
//
//        //行对象
//        HSSFRow row = sheet.createRow(0);
//
//        //列对象
//        HSSFCell c0 = row.createCell(0);
//        HSSFCell c1 = row.createCell(1);
//        //写入数据，导出
//        c0.setCellValue("1001");
//        c1.setCellValue("沈腾");
//
//        try {
//            FileOutputStream fos = new FileOutputStream( "F:\\Game\\444.xlsx");
//            excel.write(fos);
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        exportData();
//        importData();
    }

    private static void exportData(){
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
        try {
            FileOutputStream fos = new FileOutputStream( "F:\\Game\\444.xls");
            excel.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void importData(){
        try {
            FileInputStream fis = new FileInputStream("F:\\工作\\物资\\月结资料\\御五区间\\月结资料御五区间\\1月御五区间\\上海钢银的钢筋1月对账\\2020年1月钢筋对账单（御五区间)上海钢银.xlsx");
            Workbook excel = WorkbookFactory.create(fis);
            Sheet sheet = excel.getSheetAt(1);

            Row row = sheet.getRow(4);

            for (int i = 0 ; i >= 0; i++){
                Cell cell = row.getCell(i);
                if (cell!=null){
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    System.out.println(value);
                }else {
                    break;
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
