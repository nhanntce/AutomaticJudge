package judge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import obj.StudentResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
    private final String MAIN_SHEET = "IT1";
    private final String FORMAT_SHEET = "Format";
    private final String COMMENT_SHEET = "Comment";
    private final String PLAGIARISM_SHEET = "Plagiarism";
    private final String TEMPLATE = "template";

    public ExcelHandle(frmJudge parent) {
        this.parent = parent;
    }

    /**
     * Constructor
     *
     * @param path
     * @param name
     * @param studentDir
     * @param problemDir
     * @param parent
     */
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
     * @author NhanNT
     * @param table
     * @param file
     * @throws FileNotFoundException
     */
    public void writeToExcell(JTable table, File file, String contestName) throws FileNotFoundException {
        try {
            TableModel dtm = table.getModel();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //get format, comment, plagiarism result
            StudentResult studentResult = getStudentResult(contestName, dtm);
            String semester = contestName.split("_")[0] != null ? contestName.split("_")[0] : "";
            String subject = contestName.split("_")[1] != null ? contestName.split("_")[1] : "";
            String clas = contestName.split("_")[2] != null ? contestName.split("_")[2] : "";
            //get student name in the contest
            Map<String, String> studentName = new HashMap<>();
            if (Files.exists(Paths.get(parent.problemDir + "\\" + contestName + "\\student.txt"))) {
                List<String> students = Files.readAllLines(Paths.get(parent.problemDir + "\\" + contestName + "\\student.txt"));
                for (int i = 0, len = students.size(); i < len; i++) {
                    studentName.put(students.get(i).split("=")[0], students.get(i).split("=")[1]);
                }
            }
            //get attribute in config file in the contest
            boolean checkFormat = true;
            boolean checkComment = true;
            double percetageComment = 0;
            boolean checkPlagiarism = true;
            double percetagePlagiarism = 0;
            double formatMinus = 0;
            String commentMode = "Fixed";
            double commentMinus = 0;
            if (Files.exists(Paths.get(parent.problemDir + "\\" + contestName + "\\config.txt"))) {
                List<String> config = Files.readAllLines(Paths.get(parent.problemDir + "\\" + contestName + "\\config.txt"));
                checkFormat = Boolean.parseBoolean(config.get(2).split("=")[1]);
                formatMinus = Double.parseDouble(config.get(3));
                checkComment = Boolean.parseBoolean(config.get(4).split("=")[1]);
                commentMode = config.get(5).split("=")[1];
                percetageComment = Double.parseDouble(config.get(6));
                commentMinus = Double.parseDouble(config.get(7));
                checkPlagiarism = Boolean.parseBoolean(config.get(8).split("=")[1]);
                percetagePlagiarism = Double.parseDouble(config.get(9));
            }
            //get excel template
            Workbook wbexport = new XSSFWorkbook(parent.excelPath + "\\template\\template_final.xlsx");
            //get main sheet
            Sheet mainSheet = wbexport.getSheet(MAIN_SHEET);
            Sheet tmp = wbexport.getSheet(TEMPLATE);
            //set Style for cell
            CellStyle headerCellStyle = tmp.getRow(5).getCell(3).getCellStyle();
            headerCellStyle.setWrapText(false);
            headerCellStyle.setAlignment(HorizontalAlignment.LEFT);
            CellStyle title = tmp.getRow(3).getCell(0).getCellStyle();
            CellStyle problemHeader = tmp.getRow(9).getCell(3).getCellStyle();
            CellStyle pointHeader = tmp.getRow(10).getCell(3).getCellStyle();
            CellStyle calPoint = tmp.getRow(12).getCell(8).getCellStyle();
            CellStyle normalCell = tmp.getRow(11).getCell(0).getCellStyle();
            CellStyle note = tmp.getRow(17).getCell(0).getCellStyle();

            //write Semester
            Row semesterRow = mainSheet.getRow(5);
            Cell semesterCell = semesterRow.createCell(4);
            semesterCell.getSheet().addMergedRegion(new CellRangeAddress(5, 5, 4, 6));
            semesterCell.setCellStyle(headerCellStyle);
            semesterCell.setCellValue(semester);
            //write subject
            Row subjectRow = mainSheet.getRow(6);
            Cell subjectCell = subjectRow.createCell(4);
            subjectCell.getSheet().addMergedRegion(new CellRangeAddress(6, 6, 4, 6));
            subjectCell.setCellStyle(headerCellStyle);
            subjectCell.setCellValue(subject);
            //write class
            Row clasRow = mainSheet.getRow(7);
            Cell classCell = clasRow.createCell(4);
            classCell.getSheet().addMergedRegion(new CellRangeAddress(7, 7, 4, 6));
            classCell.setCellStyle(headerCellStyle);
            classCell.setCellValue(clas);
            //write Date
            Row dateRow = mainSheet.getRow(8);
            Cell dateCell = dateRow.createCell(4);
            dateCell.getSheet().addMergedRegion(new CellRangeAddress(8, 8, 4, 6));
            dateCell.setCellStyle(headerCellStyle);
            dateCell.setCellValue(dateFormat.format(new Date(System.currentTimeMillis())));

            Cell c = null;
            Row problemRow = mainSheet.getRow(9);
            Row pointRow = mainSheet.getRow(10);
            int excelRowIdx = 0, excelColIdx = 0, dtRowIdx = 0, dtColIdx = 0;
            //write header problem
            for (excelColIdx = 3, dtColIdx = 1; dtColIdx < dtm.getColumnCount() - 1; excelColIdx++, dtColIdx++) {
                c = problemRow.createCell(excelColIdx);
                c.setCellStyle(problemHeader);
                c.setCellValue(dtm.getColumnName(dtColIdx));

                c = pointRow.createCell(excelColIdx);
                c.setCellStyle(pointHeader);
                c.setCellValue("10.0");
            }
            //format sheet
            Sheet formatSheet = null;
            if (checkFormat) {
                formatSheet = wbexport.cloneSheet(wbexport.getSheetIndex(MAIN_SHEET));
                wbexport.setSheetName(wbexport.getSheetIndex(formatSheet.getSheetName()), FORMAT_SHEET);
                Row fr = formatSheet.getRow(3);
                Cell fc = fr.createCell(0);
                fc.setCellStyle(title);
                fc.setCellValue("FORMAT RESULT");
            }
            //comment sheet
            Sheet commentSheet = null;
            if (checkComment) {
                commentSheet = wbexport.cloneSheet(wbexport.getSheetIndex(MAIN_SHEET));
                wbexport.setSheetName(wbexport.getSheetIndex(commentSheet.getSheetName()), COMMENT_SHEET);
                Row cr = commentSheet.getRow(3);
                Cell cc = cr.createCell(0);
                cc.setCellStyle(title);
                cc.setCellValue("COMMENT RESULT");
            }
            //comment sheet
            Sheet plagiarismSheet = null;
            if (checkPlagiarism) {
                plagiarismSheet = wbexport.cloneSheet(wbexport.getSheetIndex(MAIN_SHEET));
                wbexport.setSheetName(wbexport.getSheetIndex(plagiarismSheet.getSheetName()), PLAGIARISM_SHEET);
                Row pr = plagiarismSheet.getRow(3);
                Cell pc = pr.createCell(0);
                pc.setCellStyle(title);
                pc.setCellValue("PLAGIARISM RESULT");
            }
            //write Sum header
            c = problemRow.createCell(excelColIdx);
            c.getSheet().addMergedRegion(new CellRangeAddress(9, 9, excelColIdx, excelColIdx + 1));
            c.setCellStyle(problemHeader);
            c.setCellValue("Sum");
            c = problemRow.createCell(excelColIdx + 1);
            c.setCellStyle(problemHeader);
            //write sum point header
            c = pointRow.createCell(excelColIdx);
            c.setCellStyle(pointHeader);
            c.setCellValue((dtColIdx - 1) * 10.0);
            //write point / 100 header
            c = pointRow.createCell(excelColIdx + 1);
            c.setCellStyle(pointHeader);
            c.setCellValue(100.0);
            int notRange = 0;
            int nextCol = excelColIdx;
            if (checkFormat) {
                notRange++;
            }
            if (checkComment) {
                notRange++;
            }
            if (checkPlagiarism) {
                notRange++;
            }
            if (notRange > 0) {
                //write Note header
                c = problemRow.createCell(excelColIdx + 2);
                if (notRange > 1) {
                    c.getSheet().addMergedRegion(new CellRangeAddress(9, 9, excelColIdx + 2, excelColIdx + notRange + 1));
                }
                c.setCellStyle(problemHeader);
                c.setCellValue("Note");
                if (notRange > 1) {
                    c = problemRow.createCell(excelColIdx + 3);
                    c.setCellStyle(problemHeader);
                }
                if (notRange > 2) {
                    c = problemRow.createCell(excelColIdx + 4);
                    c.setCellStyle(problemHeader);
                }
                nextCol += 2;
                //write format header
                if (checkFormat) {
                    c = pointRow.createCell(nextCol++);
                    c.setCellStyle(problemHeader);
                    c.setCellValue("Format");
                }
                //write format header
                if (checkComment) {
                    c = pointRow.createCell(nextCol++);
                    c.setCellStyle(problemHeader);
                    c.setCellValue("Comment");
                }
                //write format header
                if (checkPlagiarism) {
                    c = pointRow.createCell(nextCol++);
                    c.setCellStyle(problemHeader);
                    c.setCellValue("Plagiarism");
                }
            }
            //write points
            double total = 0;
            for (excelRowIdx = 11, dtRowIdx = 0; dtRowIdx < dtm.getRowCount(); excelRowIdx++, dtRowIdx++) {
                Row row = mainSheet.createRow(excelRowIdx);
                Row rowFormat = null;
                if (checkFormat) {
                    rowFormat = formatSheet.createRow(excelRowIdx);
                }
                Row rowComment = null;
                if (checkComment) {
                    rowComment = commentSheet.createRow(excelRowIdx);
                }
                Row rowPlagiarism = null;
                if (checkPlagiarism) {
                    rowPlagiarism = plagiarismSheet.createRow(excelRowIdx);
                }
                total = 0;
                String id = "";
                String format = "";
                String comment = "";
                String plagiarism = "";
                String idAndPro = "";
                //No. main sheet
                Cell cell = row.createCell(0);
                cell.setCellStyle(normalCell);
                cell.setCellValue(dtRowIdx + 1);
                //No.format sheet
                Cell cellFormat = null;
                if (checkFormat) {
                    cellFormat = rowFormat.createCell(0);
                    cellFormat.setCellStyle(normalCell);
                    cellFormat.setCellValue(dtRowIdx + 1);
                }
                //No.comment sheet
                Cell cellComment = null;
                if (checkComment) {
                    cellComment = rowComment.createCell(0);
                    cellComment.setCellStyle(normalCell);
                    cellComment.setCellValue(dtRowIdx + 1);
                }
                //No.plagiarism sheet
                Cell cellPlagiarism = null;
                if (checkPlagiarism) {
                    cellPlagiarism = rowPlagiarism.createCell(0);
                    cellPlagiarism.setCellStyle(normalCell);
                    cellPlagiarism.setCellValue(dtRowIdx + 1);
                }
                for (excelColIdx = 1, dtColIdx = 0; dtColIdx < dtm.getColumnCount() - 1; excelColIdx++, dtColIdx++) {
                    if (dtColIdx > 0) {
                        //point
                        cell = row.createCell(excelColIdx + 1);
                        cell.setCellStyle(normalCell);
                        cell.setCellValue(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx)));
                        idAndPro = id + dtm.getColumnName(dtColIdx);
                        String formatResult = studentResult.getFormat().get(idAndPro);
                        String commentResult = studentResult.getComment().get(idAndPro);
                        String plagiarismResult = studentResult.getPlagiarsm().get(idAndPro);
                        if (checkFormat) {
                            //write in format sheet
                            cellFormat = rowFormat.createCell(excelColIdx + 1);
                            cellFormat.setCellStyle(normalCell);
                            if (formatResult != null && formatResult.equals("false")) {
                                format += dtm.getColumnName(dtColIdx);
                                cellFormat.setCellValue("No");
                            } else if (formatResult != null && formatResult.equals("true")) {
                                cellFormat.setCellValue("Yes");
                            } else {
                                cellFormat.setCellValue("-");
                            }

                        }
                        if (checkComment) {
                            //write in comment sheet
                            cellComment = rowComment.createCell(excelColIdx + 1);
                            cellComment.setCellStyle(normalCell);
                            if (commentResult != null) {
                                cellComment.setCellValue(Double.parseDouble(commentResult));
                                if (Double.parseDouble(commentResult) < percetageComment) {
                                    comment += dtm.getColumnName(dtColIdx);
                                }
                            } else {
                                cellComment.setCellValue("-");
                            }

                        }
                        if (checkPlagiarism) {
                            //write in comment sheet
                            cellPlagiarism = rowPlagiarism.createCell(excelColIdx + 1);
                            cellPlagiarism.setCellStyle(normalCell);
                            if (plagiarismResult != null) {
                                cellPlagiarism.setCellValue(Double.parseDouble(plagiarismResult));
                                if (Double.parseDouble(plagiarismResult) > percetagePlagiarism) {
                                    plagiarism += dtm.getColumnName(dtColIdx);
                                }
                            } else {
                                cellPlagiarism.setCellValue("-");
                            }

                        }

                    } else {
                        //ID
                        cell = row.createCell(excelColIdx);
                        cell.setCellStyle(normalCell);
                        cell.setCellValue(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx)));
                        //Name
                        cell = row.createCell(excelColIdx + 1);
                        cell.setCellStyle(normalCell);
                        cell.setCellValue(studentName.get(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx))));
                        //ID format sheet
                        if (checkFormat) {
                            cellFormat = rowFormat.createCell(excelColIdx);
                            cellFormat.setCellStyle(normalCell);
                            cellFormat.setCellValue(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx)));
                            //Name format sheet
                            cellFormat = rowFormat.createCell(excelColIdx + 1);
                            cellFormat.setCellStyle(normalCell);
                            cellFormat.setCellValue(studentName.get(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx))));
                        }
                        //ID comment sheet
                        if (checkComment) {
                            cellComment = rowComment.createCell(excelColIdx);
                            cellComment.setCellStyle(normalCell);
                            cellComment.setCellValue(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx)));
                            //Name comment sheet
                            cellComment = rowComment.createCell(excelColIdx + 1);
                            cellComment.setCellStyle(normalCell);
                            cellComment.setCellValue(studentName.get(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx))));
                        }
                        //ID plgiarism sheet
                        if (checkPlagiarism) {
                            cellPlagiarism = rowPlagiarism.createCell(excelColIdx);
                            cellPlagiarism.setCellStyle(normalCell);
                            cellPlagiarism.setCellValue(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx)));
                            //Name plagiarism sheet
                            cellPlagiarism = rowPlagiarism.createCell(excelColIdx + 1);
                            cellPlagiarism.setCellStyle(normalCell);
                            cellPlagiarism.setCellValue(studentName.get(String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx))));
                        }

                        id = String.valueOf(dtm.getValueAt(dtRowIdx, dtColIdx));
                    }
                    if (isNumeric(dtm.getValueAt(dtRowIdx, dtColIdx).toString())) {
                        total += Double.parseDouble(dtm.getValueAt(dtRowIdx, dtColIdx).toString());
                    }

                }
                //Sum point
                cell = row.createCell(excelColIdx + 1);
                cell.setCellStyle(calPoint);
                cell.setCellValue(total);
                //Final point
                cell = row.createCell(excelColIdx + 2);
                cell.setCellStyle(calPoint);
                cell.setCellValue(total * 10 / (dtm.getColumnCount() - 2));
                //Format
                nextCol = excelColIdx + 3;
                if (checkFormat) {
                    cell = row.createCell(nextCol++);
                    cell.setCellStyle(normalCell);
                    cell.setCellValue(format.equals("") ? "-" : format);
                }
                //Comment
                if (checkComment) {
                    cell = row.createCell(nextCol++);
                    cell.setCellStyle(normalCell);
                    cell.setCellValue(comment.equals("") ? "-" : comment);
                }
                //Plagiarism
                if (checkPlagiarism) {
                    cell = row.createCell(nextCol++);
                    cell.setCellStyle(normalCell);
                    cell.setCellValue(plagiarism.equals("") ? "-" : plagiarism);
                }
            }
            //auto Size fit content
            for (int i = 3, len = dtm.getColumnCount() + 6; i < len; i++) {
                mainSheet.autoSizeColumn(i);
            }
            //Note
            Row endRow = mainSheet.createRow(excelRowIdx);
            Cell endCell = endRow.createCell(0);
            if (checkComment || checkPlagiarism || checkFormat) {
                endCell.getSheet().addMergedRegion(new CellRangeAddress(excelRowIdx, excelRowIdx, 0, dtm.getColumnCount() - 2));
                endCell.setCellStyle(note);
                endCell.setCellValue("Note: View detail in others sheet");
            }
            //date end
            endRow = mainSheet.createRow(excelRowIdx + 2);
            endCell = endRow.createCell(dtm.getColumnCount() + 2);
            endCell.getSheet().addMergedRegion(new CellRangeAddress(excelRowIdx + 2, excelRowIdx + 2, dtm.getColumnCount() + 2, dtm.getColumnCount() + 5));
            endCell.setCellStyle(tmp.getRow(18).getCell(9).getCellStyle());
            SimpleDateFormat dFormat = new SimpleDateFormat("dd MMMM yyyy");
            endCell.setCellValue(dFormat.format(new Date(System.currentTimeMillis())));
            //Lecture
            endRow = mainSheet.createRow(excelRowIdx + 3);
            endCell = endRow.createCell(dtm.getColumnCount() + 2);
            endCell.getSheet().addMergedRegion(new CellRangeAddress(excelRowIdx + 3, excelRowIdx + 3, dtm.getColumnCount() + 2, dtm.getColumnCount() + 5));
            endCell.setCellStyle(tmp.getRow(19).getCell(9).getCellStyle());
            endCell.setCellValue("Lecture");
            //Lecture
            endRow = mainSheet.createRow(excelRowIdx + 6);
            endCell = endRow.createCell(dtm.getColumnCount() + 2);
            endCell.getSheet().addMergedRegion(new CellRangeAddress(excelRowIdx + 6, excelRowIdx + 6, dtm.getColumnCount() + 2, dtm.getColumnCount() + 5));
            endCell.setCellStyle(tmp.getRow(22).getCell(9).getCellStyle());
            endCell.setCellValue("Tên giảng viên");

            //end for format
            if (checkFormat) {
                Row endFormatRow = formatSheet.createRow(excelRowIdx);
                Cell endFormatCell = endFormatRow.createCell(0);
                endFormatCell.getSheet().addMergedRegion(new CellRangeAddress(excelRowIdx, excelRowIdx, 0, dtm.getColumnCount() + 1));
                endFormatCell.setCellStyle(note);
                endFormatCell.setCellValue(String.format("Minus %.1f point when student have not formatted their source code", formatMinus));

            }
            //end for comment
            if (checkComment) {
                Row endCommentRow = commentSheet.createRow(excelRowIdx);
                Cell endCommentCell = endCommentRow.createCell(0);
                endCommentCell.getSheet().addMergedRegion(new CellRangeAddress(excelRowIdx, excelRowIdx, 0, dtm.getColumnCount() + 1));
                endCommentCell.setCellStyle(note);
                if ("Fixed".equals(commentMode)) {
                    endCommentCell.setCellValue(String.format("Minus %.1f point(s) when student have not comment source code more than %.1f %%", commentMinus, percetageComment));
                } else {
                    endCommentCell.setCellValue(String.format("Minus %.1f of points when student have not comment source code more than %.1f %%", commentMinus, percetageComment));
                }
            }
            //end for plagiarism
            if (checkPlagiarism) {
                Row endPlagiarismRow = plagiarismSheet.createRow(excelRowIdx);
                Cell endPlagiarismCell = endPlagiarismRow.createCell(0);
                endPlagiarismCell.getSheet().addMergedRegion(new CellRangeAddress(excelRowIdx, excelRowIdx, 0, dtm.getColumnCount() + 1));
                endPlagiarismCell.setCellStyle(note);
                endPlagiarismCell.setCellValue(String.format("ZERO point if student plagiarized more than %.1f %%", percetagePlagiarism));

            }
            //set main sheet active
            wbexport.setActiveSheet(wbexport.getSheetIndex(MAIN_SHEET));
            //remove template sheet
            wbexport.removeSheetAt(wbexport.getSheetIndex(TEMPLATE));
            if (!file.toString().contains(".xlsx")) {
                file = new File(file.toString() + ".xlsx");
            }
            FileOutputStream out = new FileOutputStream(file);
            wbexport.write(out);
            out.close();

        } catch (IOException e) {

        }
    }

    /**
     * Round point LinhNC
     */
    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    private void writeFormat(Sheet formatSheet, CellStyle normalStyle, boolean checkFormat) {

    }

    /**
     * get format, comment, plagiarism result
     *
     * @author NhanNT
     * @param contest
     * @param dtm
     * @return
     */
    private StudentResult getStudentResult(String contest, TableModel dtm) {
        HashMap<String, String> format = new HashMap<>();
        HashMap<String, String> comment = new HashMap<>();
        HashMap<String, String> plagiarsm = new HashMap<>();
        StudentResult sr = new StudentResult(format, comment, plagiarsm);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            String user = dtm.getValueAt(i, 0).toString();
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                File[] logs = new File(parent.folderNopbaiPath + "/Logs/" + contest).listFiles();
                Arrays.sort(logs, Comparator.comparingLong(File::lastModified));
                String problem = dtm.getColumnName(j);
                String studentAndProblem = "[" + user + "][" + problem + "]";
                for (int k = logs.length - 1; k >= 0; k--) {
                    if (logs[k].getAbsolutePath().contains(studentAndProblem)) {
                        try {
                            List<String> lines = Files.readAllLines(Paths.get(logs[k].getAbsolutePath()), StandardCharsets.UTF_8);
                            if (!lines.get(0).contains("Error") && !lines.get(0).contains("Time") && !"".equals(lines.get(0))) {
                                format.put(user + problem, lines.get(3).split(": ")[1]);
                                comment.put(user + problem, lines.get(4).split(": ")[1]);
                                plagiarsm.put(user + problem, lines.get(5).split(": ")[1]);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ExcelHandle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
            }
        }
//        System.out.println(sr.getComment().toString());
//        System.out.println(sr.getFormat().toString());
//        System.out.println(sr.getPlagiarsm().toString());
        return sr;
    }
}
