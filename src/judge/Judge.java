package judge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import management.FunctionManagement;
import obj.Plagiarism;
import utility.Formatter;
import utility.Plagiarismer;
import utility.Tool;

/**
 *
 * @author Nguyen Vuong Khang Hy Email: khanghy3004@gmail.com Automatic Judger
 */
public class Judge {

    frmJudge parent;
    FileHandle fhandle;
    String error;
    long maxMemory;
    private boolean checkFormat; // is formatted
    private double checkCommet; // percent commented
    private ArrayList<Plagiarism> listPlagiarisms;
    private int timeLimitConfig;
    private int memoryLimitConfig;
    private boolean checkFormatConfig;
    private double minusFormatPointConfig;
    private boolean checkCommentConfig;
    private String commentModeConfig;
    private double acceptCommentPercentage;
    private double minusCommentPointConfig;
    private boolean checkPlagiarismConfig;
    private double acceptPlagiarsimPercentageConfig;
    private String penaltyMode;
    private int limitSubmission;

    /**
     * Construct a Judge DangVTH
     *
     * @param parent Judge Frame
     */
    public Judge(frmJudge parent) {
        this.parent = parent;
        this.fhandle = new FileHandle();
        this.error = "";
        this.maxMemory = 0;
    }

    /**
     * Judging and writing a log file DangVTH
     *
     * @param folderPath List of Path to the submission folder
     * @param fileName List of file name
     * @param auto True if auto judge, false if not auto judge
     */
    public void judge(ArrayList<String> folderPath, ArrayList<String> fileName, boolean auto) {
        int point;
        long maxTime;
        String name; // Store the name of the solution including name extension DangVTH
        String tenbai; // Store the name of the solution DangVTH
        String stuclass; // Store the name of class DangVTH
        String user; // Store the name of user DangVTH
        String problem; // Store the name of the problem DangVTH
        String type; // Store the type of the solution DangVTH
        FileWriter writer = null;
        String originalFile;

        // scan workspace DangVTH
        for (int i = 0; i < folderPath.size(); ++i) {
            try {
                name = fileName.get(i);
                // Get name of solution DangVTH
                tenbai = name.split("\\.")[0];
                originalFile = tenbai;
                // Get class of student DangVTH
                stuclass = getStuClass(name);
                // Get username of student DangVTH
                user = getUser(name);
                // Get name of problem DangVTH
                problem = getProblem(name);
                // Get type of solution DangVTH
                type = getType(name);

                // copy file from submission DangVTH
                if ("java".equals(type)) {
                    tenbai = problem;
                    fhandle.copyFile(folderPath.get(i), tenbai + "." + type);

                    try {
                        File file = new File(tenbai + "." + type);
                        BufferedReader br = new BufferedReader(new FileReader(file));

                        String st = "";
                        String tmpString = "";
                        String newData = "";
                        String oldClassName = "";
                        boolean checkFixClassName = false;
                        while ((st = br.readLine()) != null) {
                            tmpString = st.trim();
                            String pattern = "(^public\\s{1}class|^class)\\s{1}\\w+(\\s{1}|)[{](w|[^w])*";
                            Pattern r = Pattern.compile(pattern);
                            Matcher m = r.matcher(tmpString);
                            String[] string = tmpString.split(" ");
                            if (checkFixClassName == false && m.find()) {
                                for (int l = 0; l < string.length; l++) {
                                    if ("class".equals(string[l])) {
                                        oldClassName = string[l + 1].endsWith("{") ? string[l + 1] = string[l + 1].replace(string[l + 1].substring(string[l + 1].length() - 1), "") : string[l + 1];
                                        break;
                                    }
                                }
                                st = st.replaceFirst(oldClassName, problem);
                                checkFixClassName = true;
                                newData += st + System.lineSeparator();
                            } else {
                                if (checkFixClassName) {
                                    String pattCheck1 = "^" + oldClassName + "\\s{1}\\w+((([;]|\\s+[;]))|(([=]|\\s{1}[=])(new|\\s{1}new)\\s{1}" + oldClassName + "[(][)][;]*))";
                                    Pattern r1 = Pattern.compile(pattCheck1);
                                    Matcher m1 = r1.matcher(tmpString);
                                    if (m1.find()) {
                                        st = st.replaceAll(oldClassName, tenbai);
                                        newData += st + System.lineSeparator();
                                    } else {
                                        String pattCheck2 = "^\\w+(\\s{1}[=]|[=])(\\s{1}new|new)\\s{1}" + oldClassName + "[(][)][;]";
                                        Pattern r2 = Pattern.compile(pattCheck2);
                                        Matcher m2 = r2.matcher(tmpString);
                                        if (m2.find()) {
                                            st = st.replaceFirst(oldClassName, tenbai);
                                            newData += st + System.lineSeparator();
                                        } else {
                                            newData += st + System.lineSeparator();
                                        }
                                    }
                                } else {
                                    newData += st + System.lineSeparator();
                                }
                            }
                        }
                        br.close();

                        PrintWriter writerClear = new PrintWriter(file);
                        writerClear.print("");
                        writerClear.close();

                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(newData);
                        fileWriter.close();

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Judge.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    fhandle.copyFile(folderPath.get(i), name);
                }

                // Create log file DangVTH
                writer = new FileWriter(parent.folderNopbaiPath + "/Logs/" + stuclass + "/" + name + ".log");

                parent.pro.getTxtCompileRun().setText("Compiling " + problem);
                // if comile error DangVTH
                if (!compile(tenbai, problem, type, stuclass, originalFile)) {
                    writer.write("Compile Error\n" + error);
                    setPoint(stuclass, "Compile Error", parent.hmStuIndex.get(user + stuclass),
                            parent.hmTable.get(stuclass).getColumn(problem).getModelIndex());
                    setPoint(stuclass, getTotalPoint(stuclass, user), parent.hmStuIndex.get(user + stuclass),
                            parent.hmTable.get(stuclass).getColumnCount() - 1);
                    Files.deleteIfExists(Paths.get(name));
                    Files.deleteIfExists(Paths.get(tenbai + ".java"));
                    // if auto judging, delete solution in submissions folder DangVTH
                    if (auto) {
                        Files.deleteIfExists(Paths.get(folderPath.get(i)));
                    }
                    continue;
                }
                // if runtime error DangVTH
                if (!error.equals("")) {
                    writer.write("Run Time Error\n" + error);
                    setPoint(stuclass, "Run Time Error", parent.hmStuIndex.get(user + stuclass),
                            parent.hmTable.get(stuclass).getColumn(problem).getModelIndex());
                    setPoint(stuclass, getTotalPoint(stuclass, user), parent.hmStuIndex.get(user + stuclass),
                            parent.hmTable.get(stuclass).getColumnCount() - 1);

                    Files.deleteIfExists(Paths.get(name));
                    Files.deleteIfExists(Paths.get(tenbai));
                    Files.deleteIfExists(Paths.get(tenbai + ".exe"));
                    Files.deleteIfExists(Paths.get(tenbai + ".pyc"));
                    // delete file java NhanNT
                    Files.deleteIfExists(Paths.get(tenbai + ".class"));
                    Files.deleteIfExists(Paths.get(tenbai + ".java"));
                    // if auto judging, delete solution in submissions folder DangVTH
                    if (auto) {
                        Files.deleteIfExists(Paths.get(folderPath.get(i)));
                    }
                    continue;
                }

                boolean time = true;
                // Loop all of problem DangVTH
                for (int j = 0; j < parent.listProbName.size(); ++j) {

                    StringBuilder result = new StringBuilder();
                    // if problem exist on folder
                    if (parent.listProbName.get(j).equalsIgnoreCase(problem)
                            && parent.listProbPath.get(j).contains("\\" + stuclass + "\\")) {
                        File[] testPath = new File(parent.listProbPath.get(j)).listFiles(File::isDirectory);
                        int k = 0;
                        // Browse the testcase
                        point = 0;
                        maxTime = 0;
                        for (File test : testPath) {
                            // set status for process bar DangVTH
                            parent.pro.getPrgJudging().setValue(k += 100 / testPath.length);
                            // get all file of testcase DangVTH
                            File[] inout = new File(test.getAbsolutePath()).listFiles(File::isFile);
                            Arrays.sort(inout);
                            // Loop all file of testcase DangVTH
                            for (File file : inout) {
                                // If it's an input file DangVTH
                                if (file.getName().split("\\.")[1].equals("inp")) {
                                    // Copy file from submission DangVTH
                                    fhandle.copyFile(file.getPath(), file.getName());
                                } else {// If it's an output file DangVTH
                                    try {
                                        if (time) {
                                            long start = System.currentTimeMillis();
                                            // Execute the solution
                                            time = run(tenbai, problem, type);
                                            String distance = String.valueOf(
                                                    (double) (System.currentTimeMillis() - start) / 1000) + "s";
                                            // Calculate the time it takes to run out of testcase DangVTH
                                            maxTime = Math.max(maxTime, System.currentTimeMillis() - start);
                                            parent.pro.getTxtCompileRun().setText("Judging " + problem);
                                            // check time limit or memory limit
                                            if (!time) {
                                                result.append(test.getName()).append(": ").append(error).append("\n");
                                                setPoint(stuclass, getTotalPoint(stuclass, user),
                                                        parent.hmStuIndex.get(user + stuclass),
                                                        parent.hmTable.get(stuclass).getColumnCount() - 1);
                                                break;
                                            }
                                            // If the output is correct DangVTH
                                            if (fhandle.compareTwoFiles(file.getName(), file.getAbsolutePath())) {
                                                result.append(test.getName()).append(": Accepted. Time: ")
                                                        .append(distance).append("\n");
                                                parent.pro.getTxtContent().setText(test.getName() + ": Accepted\n");
                                                point++;
                                            } else { // If the output is incorrect DangVTH
                                                result.append(test.getName()).append(": Wrong Answer. Time: ")
                                                        .append(distance).append("\n");
                                                parent.pro.getTxtContent().setText(test.getName() + ": Wrong Answer\n");
                                            }
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }

                        // write the result
                        DecimalFormat newFormat = new DecimalFormat("#.#");
                        double points = Double.valueOf(newFormat.format((double) point / testPath.length * 10));
                        writer.write(points + "\n");
                        writer.write("Max time: " + maxTime + "\n");
                        writer.write("Max memory: " + maxMemory + "\n");
                        if (checkFormatConfig) {
                            writer.write("Format: " + checkFormat + "\n");
                        } else {
                            writer.write("Format: null\n");
                        }
                        if (checkCommentConfig) {
                            writer.write("Comment: " + checkCommet + "\n");
                        } else {
                            writer.write("Comment: -1\n");
                        }
                        if (checkPlagiarismConfig) {
                            if (listPlagiarisms != null && listPlagiarisms.size() > 0) {
                                writer.write(
                                        "Plagiarism: " + listPlagiarisms.get(0).getPercentageOfPlagiarism() + "\n");
                                writer.write(listPlagiarisms.get(0).getNameOfFileHasPlagiarism() + "\n");
                                writer.write((listPlagiarisms.size() - 1) + "\n");
                                for (int idx = 1; idx < listPlagiarisms.size(); idx++) {
                                    writer.write(listPlagiarisms.get(idx).getOrigianal() + "\n");
                                    writer.write(listPlagiarisms.get(idx).getCompareWith() + "\n");
                                }
                            } else {
                                writer.write("Plagiarism: 0\n");
                            }
                        } else {
                            writer.write("Plagiarism: 0\n");
                        }
                        writer.write(result.toString());
                        writer.close();
                        // Set point for problem column
                        setPoint(stuclass, String.format("%.1f", calculatePoint(stuclass, user, problem, type)), parent.hmStuIndex.get(user + stuclass),
                                parent.hmTable.get(stuclass).getColumn(parent.listProbName.get(j)).getModelIndex());
                        // Set total point for user
                        setPoint(stuclass, getTotalPoint(stuclass, user), parent.hmStuIndex.get(user + stuclass),
                                parent.hmTable.get(stuclass).getColumnCount() - 1);
                        break;
                    }
                }
                // Delete excuted file
                Files.deleteIfExists(Paths.get(tenbai));
                Files.deleteIfExists(Paths.get(tenbai + "." + type));
                Files.deleteIfExists(Paths.get(tenbai + "." + type + ".orig"));
                Files.deleteIfExists(Paths.get(tenbai + ".exe"));
                Files.deleteIfExists(Paths.get(tenbai + ".pyc"));
                Files.deleteIfExists(Paths.get(tenbai + ".class"));
                Files.deleteIfExists(Paths.get(problem + ".inp"));
                Files.deleteIfExists(Paths.get(problem + ".out"));
                // if auto judging, delete solution in submissions folder DangVTH
                if (auto) {
                    Files.deleteIfExists(Paths.get(folderPath.get(i)));
                }
            } catch (IOException ex) {
                Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Calculate point
     *
     * @param contest
     * @param student
     * @param problem
     * @return
     */
    private double calculatePoint(String contest, String student, String problem, String type) {
        int pen = 0;
        String lastestLogPath = "";
        String studentAndProblem = "[" + student + "][" + problem + "]";
        File[] pathlog = new File(parent.folderNopbaiPath + "/Logs/" + contest).listFiles();
        Arrays.sort(pathlog, Comparator.comparingLong(File::lastModified));
        for (File f : pathlog) {
            if (f.getAbsolutePath().contains(studentAndProblem)) {
                pen++;
                lastestLogPath = f.getAbsolutePath();
            }
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(lastestLogPath), StandardCharsets.UTF_8);
            if (!lines.get(0).contains("Error") && !lines.get(0).contains("Time") && !"".equals(lines.get(0))) {
                double mainPoint = Float.parseFloat(lines.get(0));
                if (!"py".equals(type)) {
                    boolean formatResult = Boolean.parseBoolean(lines.get(3).split(": ")[1]);
                    double commentPercentage = Float.parseFloat(lines.get(4).split(": ")[1]);
                    double plagiarismPercentage = Float.parseFloat(lines.get(5).split(": ")[1]);
                    if (checkFormatConfig && !formatResult) {
                        mainPoint -= minusFormatPointConfig;
                    }
                    if (checkCommentConfig) {
                        if ("Fixed".equals(commentModeConfig)) {
                            if (commentPercentage < acceptCommentPercentage) {
                                mainPoint -= minusCommentPointConfig;
                            }
                        } else {
                            if (commentPercentage < acceptCommentPercentage) {
                                mainPoint -= mainPoint * minusCommentPointConfig * 0.01;
                            }
                        }
                    }
                    if (checkPlagiarismConfig && plagiarismPercentage >= acceptPlagiarsimPercentageConfig) {
                        mainPoint = 0;
                    }
                }
                if ("Hard".equals(penaltyMode)) {
                    try {
                        double logPoint = Double.parseDouble(lines.get(0));
                        if (logPoint != 10) {
                            mainPoint = 0;
                        }
                    } catch (Exception e) {

                    }
                } else {
                    mainPoint = mainPoint * (limitSubmission - pen + 1) / limitSubmission;
                }
                return mainPoint > 0 ? mainPoint : 0.0;

            }
        } catch (IOException ex) {
            Logger.getLogger(Judge.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    /**
     *
     * @param value
     * @param precision
     * @return
     */
    private static float round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }

    /**
     * Set points into table
     *
     * @param stuclass Name of contest
     * @param point Point
     * @param i Index of row
     * @param j Index of column
     */
    private void setPoint(String stuclass, String point, int i, int j) {
        parent.hmTable.get(stuclass).setValueAt(point, i, j);
    }

    /**
     * Get total point for user
     *
     * @param stuclass Name of contest
     * @param user Name of user
     * @return Total point type of String
     */
    private String getTotalPoint(String stuclass, String user) {
        double totalpoint = 0;
        // Set totalpoint for sum column
        for (int t = 1; t < parent.hmTable.get(stuclass).getColumnCount() - 1; ++t) {
            if (isNumeric(
                    parent.hmTable.get(stuclass).getValueAt(parent.hmStuIndex.get(user + stuclass), t).toString())) {
                totalpoint += Double.parseDouble(
                        parent.hmTable.get(stuclass).getValueAt(parent.hmStuIndex.get(user + stuclass), t).toString());
            }
        }
        return String.valueOf(totalpoint);
    }

    /**
     * Check if string is numeric
     *
     * @param str String to check
     * @return True if it's numeric, false if it isn't numeric
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Compile submission file
     *
     * @param fileName Name of file without file extension
     * @param problem Name of problem
     * @param type File extension
     * @param stuClass Name of contest
     * @param origialFile Name of file without file extension
     * @return True if compile successfully, false if compile not successfully
     */
    public boolean compile(String fileName, String problem, String type, String stuClass, String origialFile) {
        error = "";
        String cmd = "";
        int exitCode = 0;
        String pathToSettingConfig = parent.problemDir + "\\" + stuClass + "\\config.txt";
        try {
            if (Files.exists(Paths.get(pathToSettingConfig))) {
                List<String> lines = Files.readAllLines(Paths.get(pathToSettingConfig), StandardCharsets.UTF_8);
                // set setting content to interface
                timeLimitConfig = Integer.parseInt(lines.get(0).split("=")[1]);
                memoryLimitConfig = Integer.parseInt(lines.get(1).split("=")[1]);
                checkFormatConfig = Boolean.parseBoolean(lines.get(2).split("=")[1]);
                minusFormatPointConfig = Float.parseFloat(lines.get(3));
                checkCommentConfig = Boolean.parseBoolean(lines.get(4).split("=")[1]);
                commentModeConfig = String.valueOf(lines.get(5).split("=")[1]);
                acceptCommentPercentage = Float.parseFloat(lines.get(6));
                minusCommentPointConfig = Float.parseFloat(lines.get(7));
                checkPlagiarismConfig = Boolean.parseBoolean(lines.get(8).split("=")[1]);
                acceptPlagiarsimPercentageConfig = Float.parseFloat(lines.get(9));
                penaltyMode = String.valueOf(lines.get(10).split("=")[1]);
                limitSubmission = Integer.parseInt(lines.get(11));
            }

            // Check format
            if (checkFormatConfig) {
                // Generate formatted file
                Formatter.Format(fileName + "." + type, type, parent);
                // check file is exist
                if (Files.exists(Paths.get(fileName + "." + type + ".orig"))) { // 2 files are different, then generate
                    // new file.orig
                    fhandle.copyFile(fileName + "." + type + ".orig", fileName + "." + type);
                    Files.deleteIfExists(Paths.get(fileName + "." + type + ".orig"));

                    String elseType = "";
                    if (type.equals("c") || type.equals("cpp")) {
                        elseType = "java";
                    } else {
                        elseType = "cpp";
                    }
                    Formatter.Format(fileName + "." + type, elseType, parent);
                    if (Files.exists(Paths.get(fileName + "." + type + ".orig"))) {
                        Files.exists(Paths.get(fileName + "." + type + ".orig"));
                        checkFormat = false;
                    } else {
                        checkFormat = true;
                    }
                } else { // 2 files are the same
                    checkFormat = true;
                }

            }
            // Calculate the percent of comments
            if (checkCommentConfig) {
                FunctionManagement fm = new FunctionManagement();
                checkCommet = fm.calculatePercentOfAllFunctionCmt(fileName + "." + type);

            }
            // check plagiarism
            if (checkPlagiarismConfig) {
                File[] listWorkspaceStudent = new File(parent.studentDir + "\\" + stuClass)
                        .listFiles(File::isDirectory);
                ArrayList<String> listSolutionToCompare = new ArrayList<>();
                String currentSolutionToCompare = "";
                for (File f : listWorkspaceStudent) {
                    File[] listStudentSolution = new File(f.getAbsolutePath()).listFiles(File::isFile);
                    for (File solution : listStudentSolution) {
                        currentSolutionToCompare = solution.getAbsolutePath();
                        if (currentSolutionToCompare.contains(problem)) {
                            listSolutionToCompare.add(currentSolutionToCompare);
                        }
                    }
                }
                listPlagiarisms = Plagiarismer.plagiarism(
                        parent.studentDir + "\\" + stuClass + "\\" + getUser(origialFile) + "\\" + origialFile,
                        listSolutionToCompare, type, parent);

            }
        } catch (IOException ex) {
            Logger.getLogger(frmJudge.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        if (!checkHack(problem, type, fileName + "." + type, parent.checkFormat, parent.checkCmt)) {
            return true;
        }
        if (type.equals("sql")) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(fileName + ".sql"));
                DBManagement db = new DBManagement();
                Connection conn = db.getConnection();
                Statement st = conn.createStatement();
                String bailam = scanner.useDelimiter("\\A").next();
                scanner.close();
                return runSql(bailam, st);
            } catch (IOException | SQLException | NoSuchElementException ex) {
                scanner.close();
                error = "SQL syntax: Empty command";
                return false;
            }
        } else {
            switch (type) {
                // get time
                case "cpp":
                    cmd = compileCMD(fileName, type);
                    break;
                case "c":
                    cmd = compileCMD(fileName, type);
                    break;
                case "py":
                    cmd = parent.typepy + " -m compileall " + fileName + ".py -q -b";
                    break;
                // judge Java NhanNT
                case "java":
                    cmd = "javac " + fileName + "." + type;
                    break;
            }
            try {
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(cmd);
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                BufferedReader br1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String s;
                // get time
                while ((s = br.readLine()) != null || (s = br1.readLine()) != null) {
                    error += s + "\n";
                }
                exitCode = p.waitFor();

            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return exitCode == 0;
    }

    /**
     * Get String compile command
     *
     * @param problem source code 
     * @param type type of source code
     * @return string to run command line
     */
    public String compileCMD(String problem, String type) {
        String compilecmd = "";
        String Wall = "";
        if (parent.checkWall) {
            Wall = " -Wall";
        }
        switch (parent.ostype) {
            case Windows:
                if (type.equals("cpp")) {
                    compilecmd = parent.typecpp + Wall + " -o " + problem + ".exe " + problem + ".cpp";
                } else {
                    compilecmd = parent.typec + Wall + " -o " + problem + ".exe " + problem + ".c";
                }
                break;
            case MacOS:
                break;
            case Linux:
                if (type.equals("cpp")) {
                    compilecmd = parent.typecpp + " -Wall -o " + problem + " " + problem + ".cpp";
                } else {
                    compilecmd = parent.typec + " -Wall -o " + problem + " " + problem + ".c";
                }
                break;
            case Other:
                break;
        }
        return compilecmd;
    }

    /**
     * Run executable file
     *
     * @param tenbai name of file source code
     * @param problem problem name
     * @param type type of source code
     * @return run fail or success
     */
    public boolean run(String tenbai, String problem, String type) {
        String cmd = "";
        if (type.equals("sql")) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(problem + ".inp"));
                String inp = scanner.useDelimiter("\\A").next();
                scanner.close();

                DBManagement db = new DBManagement();
                Connection conn = db.getConnection();
                Statement st = conn.createStatement();
                String[] array = inp.split("\\;", -1);
                String sql = insertString(array[0], "TEMPORARY ", 6);
                st.executeUpdate(sql);
                sql = array[1];
                st.executeUpdate(sql);
                scanner = new Scanner(new File(tenbai + ".sql"));
                String bailam = scanner.useDelimiter("\\A").next();
                scanner.close();

                return runSql(bailam, st);
            } catch (IOException | SQLException | NoSuchElementException ex) {
                scanner.close();
                return false;
            }
        } else {
            switch (type) {
                case "cpp":
                case "c":
                    cmd = runCMD(tenbai);
                    break;
                case "py":
                    cmd = parent.typepy + " " + tenbai + ".py";
                    break;
                case "java":
                    cmd = "java " + tenbai;
                    break;
            }

            try {
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(cmd);
                boolean exitCode = p.waitFor(timeLimitConfig, TimeUnit.MILLISECONDS);
                if (!exitCode) {
                    p.destroyForcibly();
                    error = "Time Limit Exceeded";
                    return false;
                }
                if (!checkMemory(r, memoryLimitConfig)) {
                    p.destroyForcibly();
                    error = "Memory Limit Exceeded";
                    return false;
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                BufferedReader br1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String s;
                while ((s = br.readLine()) != null || (s = br1.readLine()) != null) {
                    error += s + "\n";
                }
                if (error.length() > 0) {
                    return false;
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return true;
    }

    /**
     * Get String executable file
     *
     * @param problem source code
     * @return String to run command line
     */
    public String runCMD(String problem) {
        String runcmd = "";
        switch (parent.ostype) {
            case Windows:
                runcmd = problem + ".exe";
                break;
            case MacOS:
                break;
            case Linux:
                runcmd = "./" + problem;
                break;
            case Other:
                break;
        }
        return runcmd;
    }

    /**
     * Get student class
     *
     * @param s get class in file source code name
     * @return class name
     */
    public String getStuClass(String s) {
        s = s.substring(1);
        try {
            String pattern = "\\]\\[";
            String[] lsuser = s.split(pattern);
            String user = lsuser[lsuser.length - 3];
            return user;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return "";
    }

    /**
     * Get username
     *
     * @param s get Student code in file's source code name
     * @return student code
     */
    public String getUser(String s) {
        try {
            String pattern = "\\]\\[";
            String[] lsuser = s.split(pattern);
            String user = lsuser[lsuser.length - 2];
            return user;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return "";
    }

    /**
     * Get problem
     *
     * @param s get problem name
     * @return problem name
     */
    public String getProblem(String s) {
        try {
            String pattern = "\\]\\[";
            String[] lsprob = s.split(pattern);
            String[] lsprob1 = lsprob[lsprob.length - 1].split("\\]\\.");
            String prob = lsprob1[0];
            return prob;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return "";
    }

    /**
     * get type submission file
     *
     * @param s file name of source code
     * @return type of source code
     */
    public String getType(String s) {
        return s.split("\\.")[1];
    }

    /**
     * Execute Judge DangVTH
     *
     * @param NopbaiPath path workspace
     * @param NopbaiName name of each file
     * @param auto check auto judge or judge in workspace
     */
    public void foo(ArrayList<String> NopbaiPath, ArrayList<String> NopbaiName, boolean auto) {
        if (NopbaiPath.isEmpty() || NopbaiName.isEmpty()) {
            return;
        }
        parent.pro = new frmProcess(parent);
        parent.pro.setVisible(true);
        parent.judge = new Thread() {
            @Override
            public void run() {
                parent.btnJudgeAContest.setEnabled(false);
                parent.btnUpdateOnline.setEnabled(false);
                parent.btnJudgeAllContests.setEnabled(false);
                judge(NopbaiPath, NopbaiName, auto);
                parent.pro.setVisible(false);
                parent.btnJudgeAContest.setEnabled(true);
                parent.btnJudgeAllContests.setEnabled(true);
                parent.btnUpdateOnline.setEnabled(true);
            }
        };
        parent.timer = new Thread() {
            @Override
            public void run() {
                int timecnt = 0;
                while (true) {
                    if (!parent.judge.isAlive()) {
                        stop();
                        break;
                    }
                    try {
                        parent.pro.getTxtTime().setText(secondtoTime(timecnt++));
                        sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(frmJudge.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        };
        parent.judge.start();
        parent.timer.start();
    }

    /**
     * Convert second to formatted time
     *
     * @param second
     * @return formatted time
     */
    private String secondtoTime(int second) {
        int h = second / 3600;
        int m = (second / 60) % 60;
        int s = second % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    /**
     * Check hack
     *
     * @param problem
     * @param type
     * @param file
     * @param checkFunc
     * @param checkCmt
     * @return
     */
    private boolean checkHack(String problem, String type, String file, boolean checkFormat, boolean checkCmt) {
        FileReader fr = null;
//        int cntFunc = 0;
        int cntCmt = 0;
        try {
            File f = new File(file); // creates a new file instance
            fr = new FileReader(f); // reads the file
            BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
            String line;
            String string = "";
            boolean flag = false;
            if ("py".equals(type)) {
                string += "import sys\n"
                        + "sys.stdin = open('A.inp', 'r')\n"
                        + "sys.stdout = open('A.out', 'w')\n";
            }
            while ((line = br.readLine()) != null) {
                if (line.contains("fopen")) {
                    fr.close();
                    error = "Function \"fopen\" is not allow";
                    return false;
                }
                if (line.contains("system")) {
                    fr.close();
                    error = "Function \"system\" is not allow";
                    return false;
                }
                if (containsInorgeCase(line, "DROP")) {
                    fr.close();
                    error = "Function \"DROP\" is not allow";
                    return false;
                }
                if (containsInorgeCase(line, "CREATE")) {
                    fr.close();
                    error = "Function \"CREATE\" is not allow";
                    return false;
                }

                if ("java".equals(type)) {
                    if (line.contains("System.in")) {
                        String scVar = line.trim().split("\\s+")[1];
                        string += "Scanner " + scVar + " = null; "
                                + "try { " + scVar + " = new java.util.Scanner(new java.io.InputStreamReader(new java.io.FileInputStream(new java.io.File(\"" + problem + ".inp\"))));"
                                + "} catch (java.io.FileNotFoundException ex) {}"
                                + "try { " + scVar + " = new java.util.Scanner(new java.io.InputStreamReader(new java.io.FileInputStream(new java.io.File(\"" + problem
                                + ".inp\"))));" + "} catch (java.io.FileNotFoundException ex) {}";
                        line = "";
                    }
                }
                string += line + "\n";
                if ("java".equals(type)) {
                    if (line.contains("main")) {
                        if (line.contains("{")) {
                            string += "\njava.io.PrintStream fileOutzzz;\n" + "try {\n"
                                    + "            fileOutzzz = new java.io.PrintStream(\"" + problem + ".out\");\n"
                                    + "             System.setOut(fileOutzzz); \n"
                                    + "        } catch (java.io.FileNotFoundException ex) {}";
                        } else {
                            flag = true;
                        }
                    }
                    if (flag && line.contains("{")) {
                        string += "\njava.io.PrintStream fileOutzzz;\n" + "try {\n"
                                + "            fileOutzzz = new java.io.PrintStream(\"" + problem + ".out\");\n"
                                + "             System.setOut(fileOutzzz); \n"
                                + "        } catch (java.io.FileNotFoundException ex) {}";
                        flag = false;
                    }
                } else {
                    if (line.contains("main")) {
                        if (line.contains("{")) {
                            string += "\n    freopen(\"" + problem + ".inp\", \"r\", stdin);\n" + "    freopen(\"" + problem
                                    + ".out\", \"w\", stdout);";
                        } else {
                            flag = true;
                        }
                    }
                    if (flag && line.contains("{")) {
                        string += "\n    freopen(\"" + problem + ".inp\", \"r\", stdin);\n" + "    freopen(\"" + problem
                                + ".out\", \"w\", stdout);";
                        flag = false;
                    }
                }
            }
            fr.close();
            FileWriter writer = new FileWriter(file);
            writer.write(string);
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(Judge.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
//        if (checkFunc && cntFunc < 1) {
//            error = "You don't have any function in code";
//            return false;
//        }
//        if (checkCmt && cntCmt < 1) {
//            error = "You don't have any comment in code";
//            return false;
//        }
        return true;
    }

    /**
     * Check if it's a function DangVTH
     *
     * @param line
     * @return
     */
    private boolean isFunction(String line) {
        String[] arr = new String[]{"boolean", "char", "double", "float", "int", "long", "string", "void"};
        return Arrays.stream(arr).anyMatch(line::contains) && !line.endsWith(";") && !line.contains("main");
    }

    /**
     * Check if it's a comment DangVTH
     *
     * @param line
     * @return
     */
    private boolean isCmt(String line) {
        String[] arr = new String[]{"//", "/*"};
        return Arrays.stream(arr).anyMatch(line::contains) && !line.endsWith(";");
    }

    /**
     * Check if there is a memory overflow DangVTH
     *
     * @param r
     * @param mem
     * @return
     */
    private boolean checkMemory(Runtime r, int mem) {
        maxMemory = Math.max(maxMemory, ((r.totalMemory() - r.freeMemory()) / 1024 / 1024));
        return ((r.totalMemory() - r.freeMemory()) / 1024 / 1024) <= mem;
    }

    /**
     * Insert String to other String by index DangVTH
     *
     * @param originalString
     * @param stringToBeInserted
     * @param index
     * @return
     */
    private String insertString(String originalString, String stringToBeInserted, int index) {
        // Create a new string
        String newString = originalString.substring(0, index + 1) + stringToBeInserted
                + originalString.substring(index + 1);
        // return the modified String
        return newString;
    }

    /**
     * Check if the string contains a different string DangVTH
     *
     * @param original
     * @param tobeChecked
     * @return
     */
    private boolean containsInorgeCase(String original, String tobeChecked) {
        return original.toLowerCase().contains(tobeChecked.toLowerCase());
    }

    /**
     * Execute SQL statement DangVTH
     *
     * @param sql sql
     * @param st st
     * @return boolean
     */
    public boolean runSql(String sql, Statement st) {
        long start = System.currentTimeMillis();
        try {
            String[] array = sql.split("\\;", -1);
            ResultSet rs = null;
            for (int i = 0; i < array.length; ++i) {
                sql = array[i];
                if (containsInorgeCase(sql, "select")) {
                    rs = st.executeQuery(sql);
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    FileWriter writer = new FileWriter("A.out");

                    for (int j = 1; j <= columnCount; j++) {
                        String col_name = metaData.getColumnName(j);
                        writer.write(col_name);
                    }
                    while (rs.next()) {
                        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                            Object object = rs.getObject(columnIndex);
                            writer.write(object == null ? "NULL" : object.toString());
                        }
                    }
                    writer.close();
                } else {
                    st.executeUpdate(sql);
                }
            }
        } catch (SQLException | IOException ex) {
            error = ex.getMessage();
        }
        if (error.contains("syntax")) {
            return false;
        } else if ((System.currentTimeMillis() - start) > parent.timeLimit) {
            error = "Time Limit Exceeded";
            return false;
        }
        error = "";

        return true;
    }
}
