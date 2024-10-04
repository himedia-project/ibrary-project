package excel;

import book.Book;
import book.BookRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelManager {

    private BookRepository bookRepository;

    public ExcelManager() {
        bookRepository = new BookRepository();
    }

    public void readExcel() {
        List<Book> bookList = bookRepository.findbBookList();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("도서 목록");

            // 헤더 스타일 설정
            CellStyle headerStyle = workbook.createCellStyle();
            // 굵은 bold 처리
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row headerRow = sheet.createRow(0);

            List<String> headerList = List.of("ISBN", "카테고리", "제목", "저자", "출판사", "부연설명", "대여여부");

            for (int i = 0; i < headerList.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headerList.get(i));
                cell.setCellStyle(headerStyle);
            }
//            headerRow.createCell(0).setCellValue("ISBN");
//            headerRow.createCell(1).setCellValue("카테고리");
//            headerRow.createCell(2).setCellValue("제목");
//            headerRow.createCell(3).setCellValue("저자");
//            headerRow.createCell(4).setCellValue("출판사");
//            headerRow.createCell(5).setCellValue("부연설명");
//            headerRow.createCell(6).setCellValue("대여여부");

            for (int i = 0; i < bookList.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(bookList.get(i).getId());
                row.createCell(1).setCellValue(bookList.get(i).getCategory());
                row.createCell(2).setCellValue(bookList.get(i).getTitle());
                row.createCell(3).setCellValue(bookList.get(i).getWriter());
                row.createCell(4).setCellValue(bookList.get(i).getPublisher());
                row.createCell(5).setCellValue(bookList.get(i).getDescription());
                row.createCell(6).setCellValue(bookList.get(i).isRented() == true ? "대여중" : "대여가능");
            }

            // 엑셀 파일 저장
            String filename = "book_list.xlsx";
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("엑셀 파일 생성 완료!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("엑셀 파일 생성 실패!");

        }

    }
}
