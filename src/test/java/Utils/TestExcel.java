package Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class TestExcel {

    public static void main(String[] args) throws IOException {
        File file = new File("src/test/resources/data/TestLastLesson.xlsx");
        System.out.println(file.exists());

        FileInputStream input = new FileInputStream(file);

        Workbook book = new XSSFWorkbook(input); // it is reading the Excelfile
        Sheet sheet = book.getSheet("Sheet1");
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(cell);

        System.out.println(sheet.getRow(2).getCell(1));
        System.out.println(sheet.getRow(2).getCell(3));

        Cell cell2 = sheet.getRow(2).getCell(3);
        cell2.setCellValue("Thank you, thank you!");

        Cell cell3 = sheet.getRow(5).createCell(3);
        cell3.setCellValue("Say Hi");

        Cell cell8 = sheet.getRow(8).createCell(1);
        cell8.setCellValue("Training, and ...");

        System.out.println(""+sheet.getRow(8).getCell(1)+"check");

        sheet.getRow(2).getCell(4).setCellValue("8/5/2020");

        FileOutputStream output =new FileOutputStream(file);
        book.write(output);
        output.close();
    }

}
