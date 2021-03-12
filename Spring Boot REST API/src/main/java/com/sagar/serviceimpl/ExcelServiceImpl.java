package com.sagar.serviceimpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.dao.BookDao;
import com.sagar.entity.Book;
import com.sagar.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    BookDao bookDao;

    public ExcelServiceImpl() {
    }

    public List<Object> getColumnData() {
        List<Book> books = this.bookDao.findAll();
        List<Object> obj = new ArrayList<Object>();
        books.forEach((book) -> {
            obj.add(book);
        });
        return obj;
    }

    public List<Object> getColumnNames() {
        List<Object> obj = new ArrayList<Object>();
        Book.getColumnNames().forEach((book) -> {
            obj.add(book);
        });
        return obj;
    }

    public boolean generateExcel() throws FileNotFoundException, SQLException {
        List<Object> columnData = this.getColumnData();
        List<Object> columnNames = this.getColumnNames();
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Map<Integer, ArrayList<Object>> data = new TreeMap<Integer, ArrayList<Object>>();
        data.put(0, (ArrayList<Object>)columnNames);

        for(int i = 0; i < columnData.size(); ++i) {
            ArrayList<Object> obj = new ArrayList<Object>();
            Book book = (Book)columnData.get(i);
            obj.add(book.getId());
            obj.add(book.getBookName());
            obj.add(book.getIsbn());
            obj.add(book.getAuthor());
            data.put(i + 1, obj);
        }

        Set<Integer> keyset = data.keySet();
        keyset.forEach((it) -> {
            System.out.println(it);
        });
        int rownum = 0;
        Iterator<Integer> var9 = keyset.iterator();

        while(var9.hasNext()) {
            Integer key = (Integer)var9.next();
            Row row = sheet.createRow(rownum++);
            ArrayList<Object> objArr = (ArrayList<Object>)data.get(key);
            int cellnum = 0;
            Iterator<Object> var14 = objArr.iterator();

            while(var14.hasNext()) {
                Object objj = var14.next();
                Cell cell = row.createCell(cellnum++);
                if (objj instanceof String) {
                    cell.setCellValue((String)objj);
                } else if (objj instanceof Integer) {
                    cell.setCellValue((double)(Integer)objj);
                }
            }
        }

        try {
            InputStream iss = ExcelServiceImpl.class.getResourceAsStream("/file_paths.properties");
            Properties pr = new Properties();
            pr.load(iss);
            System.out.println(pr.getProperty("excelPath"));
            File file = new File(pr.getProperty("excelPath") + "books" + ".xls");
            OutputStream out = new FileOutputStream(file);
            wb.write(out);
            out.close();
            System.out.println("books.xls written successfully on disk.");
            return true;
        } catch (Exception var16) {
            var16.printStackTrace();
            return false;
        }
    }
}