package com.warehouse_management_system.util.io;

import com.warehouse_management_system.model.Product;
import com.warehouse_management_system.model.Attribute;
import com.warehouse_management_system.util.DatabaseUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {

    public static List<Product> readProductsFromExcelFile(String filePath) {
        List<Product> productList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Bỏ qua dòng tiêu đề
            if (rows.hasNext()) {
                rows.next();
                rows.next();
            }

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Product product = new Product();

                Cell sttCell = currentRow.getCell(0);
                if (sttCell == null || sttCell.getCellType() == CellType.BLANK) {
                    continue; // Bỏ qua các dòng trống
                }

                product.setProd_id(currentRow.getCell(0).toString());
                product.setProd_name(currentRow.getCell(1).toString());
                product.setProd_quantity((int)Double.parseDouble(currentRow.getCell(3).toString()));
                product.setProd_cost(Double.parseDouble(currentRow.getCell(4).toString()));

                productList.add(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public static List<Attribute> readAttributesFromExcelFile(String filePath) {
        List<Attribute> attributeList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Bỏ qua dòng tiêu đề
            if (rows.hasNext()) {
                rows.next();
                rows.next();
            }
            String stt = "";
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Attribute attribute = new Attribute();

//                System.out.println(currentRow.getCell(0));
//                System.out.println(currentRow.getCell(2));

                Cell sttCell = currentRow.getCell(0);
                if (sttCell == null || sttCell.getCellType() == CellType.BLANK) {
                    attribute.setProd_id(stt);
                    attribute.setAttr_name(currentRow.getCell(2).toString());
                    attributeList.add(attribute);
                    continue; // Bỏ qua các dòng trống
                }
                stt = currentRow.getCell(0).toString();
                attribute.setProd_id(currentRow.getCell(0).toString());
                attribute.setAttr_name(currentRow.getCell(2).toString());

                attributeList.add(attribute);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return attributeList;
    }
}