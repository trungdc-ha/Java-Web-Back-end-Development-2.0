package service.impl;

import model.Note;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcelServiceImpl {
    private Workbook getWorkbook(String excelFilePath)
            throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    public void writeExcel(List<Note> notes, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("employee");
        createHeaderRow(sheet);
        int rowCount = 0;

        for (Note employee : notes) {
            Row row = sheet.createRow(++rowCount);
            writeBook(employee, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeBook(Note note, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(note.getId());

        cell = row.createCell(1);
        cell.setCellValue(note.getTitle());

        cell = row.createCell(2);
        cell.setCellValue(note.getContent());

        cell = row.createCell(3);
        cell.setCellValue(note.getNoteType().getName());
    }

    private void createHeaderRow(Sheet sheet) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);

        Cell cellID = row.createCell(0);
        cellID.setCellStyle(cellStyle);
        cellID.setCellValue("ID");

        Cell cellTitle = row.createCell(1);
        cellTitle.setCellStyle(cellStyle);
        cellTitle.setCellValue("Title");

        Cell cellContent = row.createCell(2);
        cellContent.setCellStyle(cellStyle);
        cellContent.setCellValue("Content");

        Cell cellNoteType = row.createCell(3);
        cellNoteType.setCellStyle(cellStyle);
        cellNoteType.setCellValue("NoteType");
    }
}
