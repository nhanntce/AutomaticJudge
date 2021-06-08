package judge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Nguyen Vuong Khang Hy Email: khanghy3004@gmail.com Automatic Judger
 */
public class ExcelHandle {

    frmJudge parent;
    FileInputStream fis;
    //creating workbook instance that refers to .xls file
    XSSFWorkbook wb;
    //creating a Sheet object to retrieve the object  
    XSSFSheet sheet;

    String studentDir;
    String problemDir;
    String path;
    String name;

    public ExcelHandle(frmJudge parent) {
        this.parent = parent;
    }

    public ExcelHandle(String path, String name, String studentDir, String problemDir, frmJudge parent) {
        try {
            this.parent = parent;
            this.studentDir = studentDir;
            this.problemDir = problemDir;
            this.path = path;
            this.name = name.split("\\.")[0];
            this.fis = new FileInputStream(new File(path));
            this.wb = new XSSFWorkbook(fis);
            this.sheet = wb.getSheetAt(0);
        } catch (IOException ex) {
            Logger.getLogger(ExcelHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generate List Student/Contestants
     */
    public void importExcel() {
        int row = sheet.getPhysicalNumberOfRows() - 1, col = 0;
        while (sheet.getRow(0).getCell(col) != null) {
            col++;
        }
        col--;
        ArrayList<String> columnNames = new ArrayList<>();
        for (int j = 0; j < col; ++j) {
            columnNames.add(sheet.getRow(0).getCell(j).toString());
        }
        parent.tableModelExport = new DefaultTableModel(columnNames.toArray(), 0);
        ArrayList<ArrayList<String>> lsRow1 = new ArrayList<>();
        try {
            for (int i = 0; i < row; ++i) {
                int index = parent.hmStuIndex.get(sheet.getRow(i + 1).getCell(0).toString() + name);
                ArrayList<String> enity = new ArrayList<>();
                for (int j = 0; j < 3; ++j) {
                    enity.add(sheet.getRow(i + 1).getCell(j).toString());
                }
                for (int j = 3; j < col; ++j) {
                    String val = sheet.getRow(i + 1).getCell(j).toString();
                    if (parent.hmTable.get(name).getValueAt(index, j - 2).toString().contains("(")) {
                        val = parent.hmTable.get(name).getValueAt(index, j - 2).toString();
                    } else {
                        if (isNumeric(parent.hmTable.get(name).getValueAt(index, j - 2).toString()) && Double.parseDouble(parent.hmTable.get(name).getValueAt(index, j - 2).toString()) != Double.parseDouble(val)) {
                            val = parent.hmTable.get(name).getValueAt(index, j - 2).toString() + " (" + val + ")";
                        }
                        if (parent.hmTable.get(name).getValueAt(index, j - 2).toString().contains("Error") && Double.parseDouble(val) > 0) {
                            val = parent.hmTable.get(name).getValueAt(index, j - 2).toString() + " (" + val + ")";
                        }
                    }

                    parent.hmTable.get(name).setValueAt(val, index, j - 2);
                    enity.add(val);
                }
                lsRow1.add(enity);
            }
            for (ArrayList<String> arrayList : lsRow1) {
                parent.tableModelExport.addRow(arrayList.toArray());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File error occurred!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Export data to file Excel
     *
     * @param table
     * @param file
     * @throws FileNotFoundException
     */
    public void writeToExcell(JTable table, File file) throws FileNotFoundException {
        try {
            TableModel dtm = table.getModel();
            Workbook wbexport = new XSSFWorkbook();
            Sheet sheet1 = wbexport.createSheet("new sheet");

            for (int i = 0; i < dtm.getRowCount() + 1; i++) {
                Row row = sheet1.createRow(i);
                int j = 0;
                for (j = 0; j < dtm.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    if (i == 0) {
                        cell.setCellValue(dtm.getColumnName(j));
                    } else {
                        if (dtm.getValueAt(i - 1, j).toString().contains("Error") || dtm.getValueAt(i - 1, j).toString().contains("Time") || dtm.getValueAt(i - 1, j).toString().contains("Not")) {
                            cell.setCellValue(0);
                        } else if (isNumeric(dtm.getValueAt(i - 1, j).toString())) {
                            cell.setCellValue(Double.parseDouble(dtm.getValueAt(i - 1, j).toString()));
                        } else { // Roll number
                            cell.setCellValue(dtm.getValueAt(i - 1, j).toString());
                        }
                    }
                }
                Cell cell = row.createCell(j);
                if (i == 0) {
                    cell.setCellValue("Final");
                } else {
                    if (isNumeric(dtm.getValueAt(i - 1, j - 1).toString())) {
                        cell.setCellValue(round(Double.parseDouble(dtm.getValueAt(i - 1, j - 1).toString()) / (dtm.getColumnCount() - 2), 1));
                    } else {
                        cell.setCellValue(0);
                    }
                }
            }
            if (!file.toString().contains(".xlsx")) {
                file = new File(file.toString() + ".xlsx");
            }
            FileOutputStream out = new FileOutputStream(file);
            wbexport.write(out);
            out.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
