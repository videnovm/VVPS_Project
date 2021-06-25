package service;

import entities.Entity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class XslxFileReader {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yy, HH:mm");

    public static List<Entity> extractEntitiesFromFile(File excelFile) {
        List<Entity> entities = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(excelFile)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Entity entity = new Entity();
                Iterator<Cell> cellIterator = row.cellIterator();
                entity.setTime(LocalDateTime.parse(row.getCell(0).getStringCellValue(), DATE_TIME_FORMATTER));
                entity.setEventContext(row.getCell(1).getStringCellValue());
                entity.setComponent(row.getCell(2).getStringCellValue());
                entity.setEventName(row.getCell(3).getStringCellValue());
                entity.setDescription(row.getCell(4).getStringCellValue());
                entities.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }
}