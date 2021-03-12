package com.sagar.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface ExcelService {
    List<Object> getColumnData();

    List<Object> getColumnNames();

    boolean generateExcel() throws FileNotFoundException, SQLException;
}
