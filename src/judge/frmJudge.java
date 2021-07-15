package judge;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Nguyen Vuong Khang Hy Email: khanghy3004@gmail.com Automatic Judger
 */
public class frmJudge extends javax.swing.JFrame {

    public ArrayList<String> listNopbaiPath;    //store the path of each student solution NhanNT
    public ArrayList<String> listNopbaiName;    //store the name of each student solution NhanNT
    public ArrayList<String> listProbPath;      //store the path of each testcase file NhanNT
    public ArrayList<String> listProbName;      //store the name of each testcase file NhanNT
    public ArrayList<String> listStuPath;       //store the path to each workspace folder of student in contest NhanNT
    public ArrayList<String> listStuName;       //store the name of each workspace folder of student in contest NhanNT
    public ArrayList<String> listStuClassPath;  //store the path of each contest NhanNT
    public ArrayList<String> listStuClassName;  //store the name of each contest NhanNT
    public HashMap<String, String> hmStuPath;
    public HashMap<String, JTable> hmTable;
    public HashMap<String, Integer> hmStuIndex;
    public HashMap<String, Integer> hmTotalPoint;
    public ArrayList<ArrayList<String>> lsParentProblem;    //list of list test case name folder NhanNT
    public ArrayList<String> listContestLoadPannalty;       //list of contestant is loading pannalty NhanNT
    public DefaultTableModel tableModelExport;

    public String typecpp;
    public String typec;
    public String typepy;
    public String typejava;
    public String studentDir;       //store the path of workspace NhanNT
    public String problemDir;      //store the path of testcase of contests NhanNT
    private String excelPath;
    public String folderNopbaiPath; //store the path of submissions NhanNT
    public String testcaseEventPath; //store the path catch the event when update testcase NhanNT
    public String workspaceEventPath; //store the path catch the event when update workspace NhanNT
//    public boolean checkFunction;
    public boolean checkFormat;
    public boolean checkCmt;
    public String checkCmtMode;
    public int percentCmtAcp;
    public double minusPoint;
    public int minusPercent;
    public boolean checkPlagiarism;
    public boolean checkWall;
    public int timeLimit;
    public int memoryLimit;

    public Properties props;       //store properties config file NhanNT
    public File FILE;              //store path name of config file NhanNT
    public frmProcess pro;          //process frame
    public Judge tool;              //judge class
    public FileHandle fhandle;
    public OsCheck.OSType ostype;
    public ExcelHandle excel;

    public Thread judge = null;
    public Thread timer = null;
    private Thread watcherThread = null;
    private RowPopup pop;

    private String tmptype;
    public String astylePath;
    public String simPath;

    /**
     * Creates new form frmThemis
     */
    public frmJudge() {
        initComponents();
        //make UI beautiful NhanNT
        this.getContentPane().setBackground(Color.WHITE);
        pnlToolbar.setBackground(Color.WHITE);
        FontUIResource font = new FontUIResource("Verdana", Font.PLAIN, 14);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TabbedPane.background", new Color(120, 120, 120));
        tabTable.setFont(new Font("Dialog", Font.ITALIC, 14));
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/btnjudge.png")));
        init();
        initProperties();
        //load test cases NhanNT
        listProblem();
        //load student solutions NhanNT
        listStudent();
        addEvent();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileFolderProblem = new javax.swing.JFileChooser();
        fileFolderStudent = new javax.swing.JFileChooser();
        fileFolderNopbai = new javax.swing.JFileChooser();
        fileFile = new javax.swing.JFileChooser();
        tabTable = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlToolbar = new javax.swing.JPanel();
        btnListProblem = new javax.swing.JButton();
        btnUpdateOnline = new javax.swing.JButton();
        btnImportExcel = new javax.swing.JButton();
        btnExportExcel = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        btnJudgeAContest = new javax.swing.JButton();
        btnJudgeAllContests = new javax.swing.JButton();
        btnJudgeAContest1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automatic Judger");
        setSize(new java.awt.Dimension(884, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Contestants", "Total"
            }
        ));
        tblTable.setEnabled(false);
        tblTable.getTableHeader().setFont(new Font("Verdana", Font.ITALIC, 14));
        jScrollPane1.setViewportView(tblTable);

        tabTable.addTab("Class", jScrollPane1);

        txtFilter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Find students:");

        pnlToolbar.setPreferredSize(new java.awt.Dimension(1200, 100));

        btnListProblem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnListProblem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnloadtasks.png"))); // NOI18N
        btnListProblem.setToolTipText("Path Setting");
        btnListProblem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListProblemActionPerformed(evt);
            }
        });

        btnUpdateOnline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdateOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnrefreshonline.png"))); // NOI18N
        btnUpdateOnline.setToolTipText("Refresh Online");
        btnUpdateOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateOnlineActionPerformed(evt);
            }
        });

        btnImportExcel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnImportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnimport.png"))); // NOI18N
        btnImportExcel.setToolTipText("Import Excel");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        btnExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnexport.png"))); // NOI18N
        btnExportExcel.setToolTipText("Export Excel");
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });

        btnConfig.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btncompilerconfig.png"))); // NOI18N
        btnConfig.setToolTipText("Configure ");
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });

        btnSetting.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnsettings.png"))); // NOI18N
        btnSetting.setToolTipText("Setting");
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });

        btnJudgeAContest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnJudgeAContest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnjudge.png"))); // NOI18N
        btnJudgeAContest.setToolTipText("Judge Current Tab");
        btnJudgeAContest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJudgeAContestActionPerformed(evt);
            }
        });

        btnJudgeAllContests.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnJudgeAllContests.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnjudgeall.png"))); // NOI18N
        btnJudgeAllContests.setToolTipText("Judge All Contestant");
        btnJudgeAllContests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJudgeAllContestsActionPerformed(evt);
            }
        });

        btnJudgeAContest1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnJudgeAContest1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btninfo.png"))); // NOI18N
        btnJudgeAContest1.setToolTipText("Judge Current Tab");
        btnJudgeAContest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJudgeAContest1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlToolbarLayout = new javax.swing.GroupLayout(pnlToolbar);
        pnlToolbar.setLayout(pnlToolbarLayout);
        pnlToolbarLayout.setHorizontalGroup(
            pnlToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlToolbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnListProblem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateOnline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSetting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnJudgeAContest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnJudgeAllContests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnJudgeAContest1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlToolbarLayout.setVerticalGroup(
            pnlToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlToolbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnJudgeAContest1)
                    .addComponent(btnJudgeAllContests)
                    .addComponent(btnJudgeAContest)
                    .addComponent(btnSetting)
                    .addComponent(btnConfig)
                    .addComponent(btnExportExcel)
                    .addComponent(btnImportExcel)
                    .addComponent(btnUpdateOnline)
                    .addComponent(btnListProblem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabTable, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(pnlToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabTable, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabTable.getAccessibleContext().setAccessibleName("tab1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * button load or reload list test cases NhanNT
     *
     * @param evt
     */
    private void btnListProblemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListProblemActionPerformed
        frmSetPaths setPaths = new frmSetPaths(this);
        setPaths.setVisible(true);
    }//GEN-LAST:event_btnListProblemActionPerformed

    public void loadAllPoint() {
        File[] listContestsPaths = new File(studentDir).listFiles(File::isDirectory);
        String[] splitResult;
        for (File f : listContestsPaths) {
            String currentContest = f.getName();
            for (String s : listContestLoadPannalty) {
                JTable tb = hmTable.get(currentContest);
                Thread tload = null;
                if (currentContest.equals(s)) {
                    tload = new Thread() {
                        @Override
                        public void run() {
                            try {
                                loadPointPenalty(currentContest, tb);
                            } catch (IOException ex) {
                                Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    tload.start();
                    break;
                } else {
                    tload = new Thread() {
                        @Override
                        public void run() {
                            loadPoint(currentContest, tb);
                        }
                    };
                    tload.start();
                    break;
                }
            }
        }
    }

    /**
     * list all problem
     *
     * @author NhanNT
     */
    public void listProblem() {
        listProbPath.clear();
        listProbName.clear();
        lsParentProblem.clear();
        tabTable.removeAll();

        File[] dirParentProblem = new File(problemDir).listFiles(File::isDirectory);
        for (File dir : dirParentProblem) {
            if (dir.getName().contains("$")) {
                continue;
            }
            ArrayList<String> lsHead1 = new ArrayList<>();
            lsHead1.add("Contestants");
            File[] dirHead = new File(dir.getAbsolutePath()).listFiles(File::isDirectory);
            for (File file : dirHead) {
                lsHead1.add(file.getName());
                listProbPath.add(file.getAbsolutePath());
                listProbName.add(file.getName());
            }
            lsHead1.add("Total");
            lsParentProblem.add(lsHead1);

            DefaultTableModel dtm = new DefaultTableModel(lsHead1.toArray(), 0);

            JTable tb = new JTable(dtm);
            tb.setEnabled(false);
            setupTable(tb, dir.getName());
            hmTable.put(dir.getName(), tb);
            JScrollPane scr = new JScrollPane(tb);
            tabTable.addTab(dir.getName(), scr);
        }
        if (!listProbName.isEmpty() && !listStuName.isEmpty()) {
            btnUpdateOnline.setEnabled(true);
            btnImportExcel.setEnabled(true);
            btnExportExcel.setEnabled(true);
            btnJudgeAContest.setEnabled(true);
        }
    }

    /**
     * list a test case
     *
     * @author NhanNT
     */
    public void listProblemA() {
        listProbPath.clear();
        listProbName.clear();
        lsParentProblem.clear();

        File[] dirParentProblem = new File(problemDir).listFiles(File::isDirectory);
        for (File dir : dirParentProblem) {
            if (dir.getName().contains("$")) {
                continue;
            }
            ArrayList<String> lsHead1 = new ArrayList<>();
            lsHead1.add("Contestants");
            File[] dirHead = new File(dir.getAbsolutePath()).listFiles(File::isDirectory);
            for (File file : dirHead) {
                lsHead1.add(file.getName());
                listProbPath.add(file.getAbsolutePath());
                listProbName.add(file.getName());
            }
            lsHead1.add("Total");
            lsParentProblem.add(lsHead1);

        }
    }

    /**
     * list all student
     *
     * @author NhanNT
     */
    public void listStudent() {
        listStuPath.clear();
        listStuName.clear();
        tabTable.removeAll();

        int index = 0;
        File[] dirStu = new File(studentDir).listFiles(File::isDirectory);

        for (File dir : dirStu) {
            if (dir.getName().contains("$")) {
                continue;
            }
            int totalStudent = 0;
            listStuClassPath.add(dir.getAbsolutePath());
            listStuClassName.add(dir.getName());
            ArrayList<ArrayList<String>> lsRow1 = new ArrayList<>();
            File[] dirStuClass = new File(dir.getAbsolutePath()).listFiles(File::isDirectory);
            for (File dirStuClas : dirStuClass) {
                if (dirStuClas.getName().contains("$")) {
                    continue;
                }
                ArrayList<String> enity = new ArrayList<>();
                enity.add(dirStuClas.getName());

                for (int i = 0; i < lsParentProblem.get(index).size() - 1; ++i) {
                    enity.add("Not submit");
                }

                lsRow1.add(enity);
                listStuPath.add(dirStuClas.getAbsolutePath());
                listStuName.add(dirStuClas.getName());
                hmStuIndex.put(dirStuClas.getName() + dir.getName(), totalStudent++);

                hmStuPath.put(dirStuClas.getName() + dir.getName(), dirStuClas.getAbsolutePath());
                hmTotalPoint.put(dirStuClas.getName() + dir.getName(), 0);
            }
            DefaultTableModel dtm = new DefaultTableModel(lsParentProblem.get(index).toArray(), 0);
            for (ArrayList<String> arrayList : lsRow1) {
                dtm.addRow(arrayList.toArray());
            }
            JTable tb = new JTable(dtm);
            tb.setEnabled(false);
            setupTable(tb, dir.getName());
            hmTable.put(dir.getName(), tb);
            JScrollPane scr = new JScrollPane(tb);
            tabTable.addTab(dir.getName(), scr);
            index++;
        }
        if (!listProbName.isEmpty() && !listStuName.isEmpty()) {
            btnUpdateOnline.setEnabled(true);
            btnImportExcel.setEnabled(true);
            btnExportExcel.setEnabled(true);
            btnJudgeAContest.setEnabled(true);
        }
    }

    /**
     * list all student
     *
     * @author NhanNT
     */
    public void loadStudentToTable() {
        tabTable.removeAll();

        int index = 0;
        File[] dirStu = new File(studentDir).listFiles(File::isDirectory);

        for (File dir : dirStu) {
            if (dir.getName().contains("$")) {
                continue;
            }
            int totalStudent = 0;
            listStuClassPath.add(dir.getAbsolutePath());
            listStuClassName.add(dir.getName());
            ArrayList<ArrayList<String>> lsRow1 = new ArrayList<>();
            File[] dirStuClass = new File(dir.getAbsolutePath()).listFiles(File::isDirectory);
            for (File dirStuClas : dirStuClass) {
                if (dirStuClas.getName().contains("$")) {
                    continue;
                }
                ArrayList<String> enity = new ArrayList<>();
                enity.add(dirStuClas.getName());

                for (int i = 0; i < lsParentProblem.get(index).size() - 1; ++i) {
                    enity.add("Not submit");
                }

                lsRow1.add(enity);
                hmStuIndex.put(dirStuClas.getName() + dir.getName(), totalStudent++);

                hmStuPath.put(dirStuClas.getName() + dir.getName(), dirStuClas.getAbsolutePath());
                hmTotalPoint.put(dirStuClas.getName() + dir.getName(), 0);
            }
            DefaultTableModel dtm = new DefaultTableModel(lsParentProblem.get(index).toArray(), 0);
            for (ArrayList<String> arrayList : lsRow1) {
                dtm.addRow(arrayList.toArray());
            }
            JTable tb = new JTable(dtm);
            tb.setEnabled(false);
            setupTable(tb, dir.getName());
            hmTable.put(dir.getName(), tb);
            JScrollPane scr = new JScrollPane(tb);
            tabTable.addTab(dir.getName(), scr);
            index++;
        }
    }

    /**
     * load setting form NhanNT
     *
     * @param evt
     */
    private void btnSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingActionPerformed

        frmSetting setting = new frmSetting(this);
        String selectedTab = tabTable.getTitleAt(tabTable.getSelectedIndex());
        String pathToSettingConfig = problemDir + "\\" + selectedTab + "\\config.txt";
        File tempFile = new File(pathToSettingConfig);

        String check_comment_mode = "";
        boolean isCmtChecked = true;
        boolean isFormatChecked = true;
        boolean isPlagiarismChecked = true;
        
        //if dont have config file -> create new file and write default setting to config file
        if (!tempFile.exists()) {
            try {
                tempFile.createNewFile();
                FileWriter fileWriter = new FileWriter(tempFile);

                //get default setting from property file
                String time_limit = this.props.getProperty("time_limit");
                String memory_limit = this.props.getProperty("memory_limit");
                String check_format = this.props.getProperty("check_format");
                String check_format_minusValue = this.props.getProperty("check_format_minusValue");
                String check_comment = this.props.getProperty("check_comment");
                check_comment_mode = this.props.getProperty("check_comment_mode");
                String perentage_accept = this.props.getProperty("perentage_accept");
                String minus_value = this.props.getProperty("minus_value");
                String check_plagiarism = this.props.getProperty("check_plagiarism");
                String check_plagiarism_perAccept = this.props.getProperty("check_plagiarism_perAccept");

                //write setting to specify contest's config file
                fileWriter.write("time_limit=" + time_limit + "\n");
                fileWriter.write("memory_limit=" + memory_limit + "\n");
                fileWriter.write("check_format=" + check_format + "\n");
                fileWriter.write(check_format_minusValue + "\n");
                fileWriter.write("check_comment=" + check_comment + "\n");
                fileWriter.write("check_comment_mode=" + check_comment_mode + "\n");
                fileWriter.write(perentage_accept + "\n");
                fileWriter.write(minus_value + "\n");
                fileWriter.write("check_plagiarism=" + check_plagiarism + "\n");
                fileWriter.write(check_plagiarism_perAccept + "\n");
                
                fileWriter.close();

                //get setting content in config file to interface
                setting.txtTimeLimit.setText(time_limit);
                setting.txtMemoryLimit.setText(memory_limit);
                setting.chkCheckFormat.setSelected(Boolean.parseBoolean(this.props.getProperty("check_format")));
                setting.txtFormatMinusValue.setText(check_format_minusValue);
                setting.chkCheckCmt.setSelected(Boolean.parseBoolean(this.props.getProperty("check_comment")));
                setting.cbbCommentMode.setSelectedItem(check_comment_mode);
                setting.txtPercentageAccept.setText(perentage_accept);
                setting.txtMinusValue.setText(minus_value);
                setting.chkCheckPlagiarism.setSelected(Boolean.parseBoolean(this.props.getProperty("check_plagiarism")));
                setting.txtPercentagePlaAccept.setText(check_plagiarism_perAccept);
                
            } catch (IOException ex) {
                Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
            }
            //if have config file -> read and get setting content to interface
        } else {
            try {
                List<String> lines = Collections.emptyList();
                lines = Files.readAllLines(Paths.get(pathToSettingConfig), StandardCharsets.UTF_8);

                //set setting content to interface 
                setting.txtTimeLimit.setText(lines.get(0).split("=")[1]);
                setting.txtMemoryLimit.setText(lines.get(1).split("=")[1]);
                setting.chkCheckFormat.setSelected(Boolean.parseBoolean(lines.get(2).split("=")[1]));
                setting.txtFormatMinusValue.setText(lines.get(3));
                setting.chkCheckCmt.setSelected(Boolean.parseBoolean(lines.get(4).split("=")[1]));
                check_comment_mode = lines.get(5).split("=")[1];
                setting.cbbCommentMode.setSelectedItem(check_comment_mode);
                setting.txtPercentageAccept.setText(lines.get(6));
                setting.txtMinusValue.setText(lines.get(7));
                setting.chkCheckPlagiarism.setSelected(Boolean.parseBoolean(lines.get(8).split("=")[1]));
                setting.txtPercentagePlaAccept.setText(lines.get(9));

                isCmtChecked = Boolean.parseBoolean(lines.get(4).split("=")[1]);
                isPlagiarismChecked = Boolean.parseBoolean(lines.get(8).split("=")[1]);
                isFormatChecked = Boolean.parseBoolean(lines.get(2).split("=")[1]);
                
            } catch (IOException ex) {
                Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //if dont check comment is not checked => hiden 
        if (!isCmtChecked) {
            setting.pnlSettingCheckCmt.setVisible(false);
        } 
        if (!isFormatChecked) {
            setting.pnlSettingCheckFormat.setVisible(false);
        }
        if (!isPlagiarismChecked) {
            setting.pnlSettingCheckPlagiarism.setVisible(false);
        }
        
        //set text for label by mode check
        if ("By Percentage".equals(check_comment_mode)) {
            setting.lblMinusPoints.setText("Minus Points (%):");
        } else {
            setting.lblMinusPoints.setText("Minus Points (points):");
        }
        
        setting.pack();
        setting.setVisible(true);
    }//GEN-LAST:event_btnSettingActionPerformed

    /**
     * button run judge
     *
     * @param evt
     */
    private void btnJudgeAContestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJudgeAContestActionPerformed
        String s = "\\" + tabTable.getTitleAt(tabTable.getSelectedIndex()) + "\\";

        listNopbaiPath.clear();
        listNopbaiName.clear();
        for (int i = 0; i < listStuPath.size(); ++i) {
            if (listStuPath.get(i).contains(s)) {
                File[] directories = new File(listStuPath.get(i)).listFiles(File::isFile);
                Arrays.sort(directories, Comparator.comparingLong(File::lastModified)); // sort by time asc
                for (File dir : directories) {
                    if (dir.getName().equalsIgnoreCase(".htaccess") || !tmptype.contains(tool.getType(dir.getName()))) {
                        continue;
                    }
                    listNopbaiPath.add(dir.getAbsolutePath());
                    listNopbaiName.add(dir.getName());
                }
            }
        }
        tool.foo(listNopbaiPath, listNopbaiName, false);

    }//GEN-LAST:event_btnJudgeAContestActionPerformed
    /**
     * open config frame NhanNT
     *
     * @param evt
     */
    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        frmConfig frmCon = new frmConfig(this);
        frmCon.setVisible(true);
    }//GEN-LAST:event_btnConfigActionPerformed
    /**
     * import excel NhanNT
     *
     * @param evt
     */
    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        fileFile.removeChoosableFileFilter(fileFile.getFileFilter());
        fileFile.setCurrentDirectory(new File(excelPath));
        fileFile.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("Excel File (.xlsx)", "xlsx");
        fileFile.setFileFilter(filter);

        int choice = fileFile.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) { // if option Open file in JFileChooser
            File f = fileFile.getSelectedFile(); // get file
            excelPath = f.getAbsolutePath(); // get file path
            String excelName = f.getName(); // get file name
            excel = new ExcelHandle(excelPath, excelName, studentDir, problemDir, this);
            excel.importExcel();
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    /**
     * save config file NhanNT
     *
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            //TODO
            //write properties here
            //holding relative path
            String AbPath = this.FILE.getAbsolutePath();
            String path1 = AbPath.substring(0, AbPath.length() - 33);
            props.setProperty("studentDir", studentDir.replace(path1, ""));
            props.setProperty("problemDir", problemDir.replace(path1, ""));
            props.setProperty("testcaseEventPath", testcaseEventPath.replace(path1, ""));
            props.setProperty("workspaceEventPath", workspaceEventPath.replace(path1, ""));
            props.setProperty("folderNopbaiPath", folderNopbaiPath.replace(path1, ""));
            props.setProperty("excelPath", excelPath);
            props.setProperty("typecpp", typecpp);
            props.setProperty("typec", typec);
            props.setProperty("typepy", typepy);
            props.setProperty("typejava", typejava);
//            props.setProperty("checkFunction", String.valueOf(checkFunction));
            props.setProperty("checkFormat", String.valueOf(checkFormat));
            props.setProperty("checkCmt", String.valueOf(checkCmt));
            props.setProperty("checkWall", String.valueOf(checkWall));
            props.setProperty("timeLimit", String.valueOf(timeLimit));
            props.setProperty("memoryLimit", String.valueOf(memoryLimit));
            OutputStream out = new FileOutputStream(FILE);
            props.store(out, "User properties");
        } catch (IOException e) {
        } finally {
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing
    /**
     * open Export excel frame
     *
     * @param evt
     */
    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
        frmExportExcel frmCon = new frmExportExcel(this);
        frmCon.setVisible(true);
    }//GEN-LAST:event_btnExportExcelActionPerformed

    private void btnUpdateOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateOnlineActionPerformed
        autoJudge();
    }//GEN-LAST:event_btnUpdateOnlineActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            JTable tb = hmTable.get(tabTable.getTitleAt(tabTable.getSelectedIndex()));
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(((DefaultTableModel) tb.getModel()));
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFilter.getText()));
            tb.setRowSorter(sorter);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtFilterKeyReleased

    private void btnJudgeAllContestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJudgeAllContestsActionPerformed

        listNopbaiPath.clear();
        listNopbaiName.clear();
        for (int i = 0; i < listStuPath.size(); ++i) {
            File[] directories = new File(listStuPath.get(i)).listFiles(File::isFile);
            Arrays.sort(directories, Comparator.comparingLong(File::lastModified)); // sort by time asc
            for (File dir : directories) {
                if (dir.getName().equalsIgnoreCase(".htaccess") || !tmptype.contains(tool.getType(dir.getName()))) {
                    continue;
                }
                listNopbaiPath.add(dir.getAbsolutePath());
                listNopbaiName.add(dir.getName());
            }
        }
        tool.foo(listNopbaiPath, listNopbaiName, false);

    }//GEN-LAST:event_btnJudgeAllContestsActionPerformed

    private void btnJudgeAContest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJudgeAContest1ActionPerformed
        frmAbout about = new frmAbout();
        about.setVisible(true);
    }//GEN-LAST:event_btnJudgeAContest1ActionPerformed
    /**
     * Method load point from logs file
     *
     * @param s
     * @param tb
     */
    private void loadPoint(String s, JTable tb) {
        String contest = s;
        listContestLoadPannalty.remove(contest);
        for (int i = 0; i < tb.getRowCount(); ++i) {
            String user = tb.getValueAt(i, 0).toString();
            double total = 0;
            for (int j = 1; j < tb.getColumnCount() - 1; ++j) {
                String problem = tb.getColumnName(j);
                String log = "[" + contest + "][" + user + "][" + problem + "]";
                // Duyá»‡t thÆ° má»¥c logs chá»©a káº¿t quáº£ bÃ i lÃ m
                File[] pathlog = new File(folderNopbaiPath + "/Logs/" + contest).listFiles();
                Arrays.sort(pathlog, Comparator.comparingLong(File::lastModified).reversed()); // sáº¯p xáº¿p theo thá»�i gian giáº£m dáº§n
                for (File file : pathlog) {
                    if (file.getAbsolutePath().contains(log)) {
                        try {
                            String strCurrentLine;
                            BufferedReader reader = null;
                            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                            while ((strCurrentLine = reader.readLine()) != null) {
                                hmTable.get(s).setValueAt(strCurrentLine, i, j);
                                if (!strCurrentLine.contains("Error")) {
                                    total += Double.parseDouble(strCurrentLine);
                                }
                                break;
                            }
                            break;
                        } catch (IOException ex) {
                            Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            hmTable.get(s).setValueAt(total, i, tb.getColumnCount() - 1);
        }
    }

    /**
     * Method load point from logs file, calculate penalty point
     *
     * @param s
     * @param tb
     */
    private void loadPointPenalty(String s, JTable tb) throws IOException {
        String contest = s;
        String selectedTab = tabTable.getTitleAt(tabTable.getSelectedIndex());
        String pathToSettingConfig = problemDir + "\\" + selectedTab + "\\config.txt";
        File tempFile = new File(pathToSettingConfig);
        List<String> logLines = Collections.emptyList();
        if (!listContestLoadPannalty.contains(contest)) {
            listContestLoadPannalty.add(contest);
        }
        for (int i = 0; i < tb.getRowCount(); ++i) {
            String user = tb.getValueAt(i, 0).toString();
            double total = 0;
            String comment = null;
            String format = null;
            String plag = null;
            String percentage = null;
            String subPoint = null;
            String mode_cmt = null;
            Double pnt = 0.0;
            for (int j = 1; j < tb.getColumnCount() - 1; ++j) {
                String point = "";
                int pen = 0;
                String problem = tb.getColumnName(j);
                String log = "[" + user + "][" + problem + "]";
                // Duyá»‡t thÆ° má»¥c logs chá»©a káº¿t quáº£ bÃ i lÃ m
                File[] pathlog = new File(folderNopbaiPath + "/Logs/" + contest).listFiles();
                Arrays.sort(pathlog, Comparator.comparingLong(File::lastModified)); // sáº¯p xáº¿p theo thá»�i gian tÄƒng dáº§n
                for (File file : pathlog) {
                    if (file.getAbsolutePath().contains(log)) {
                        pen++;
                        try {
                            String strCurrentLine;
                            BufferedReader reader = null;
                            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

                            logLines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);

                            //get point
                            point = logLines.get(0);

                        } catch (IOException ex) {
                            Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                if (!point.contains("Error") && !point.contains("Time") && point != "") {
                    List<String> configLines = Collections.emptyList();
                    configLines = Files.readAllLines(Paths.get(pathToSettingConfig), StandardCharsets.UTF_8);
                    //get format value
                    if (configLines.get(2).split("=")[1].equals("true")) {
                        format = logLines.get(3).split(": ")[1];

                    }
                    //get value of comment
                    if (configLines.get(3).split("=")[1].equals("true")) {
                        mode_cmt = configLines.get(4).split("=")[1];
                        comment = logLines.get(4).split(": ")[1];

                        percentage = configLines.get(5);
                        subPoint = configLines.get(6);

                        // get value of plagiarism
                        if (configLines.get(7).split("=")[1].equals("true")) {
                            plag = logLines.get(5).split(": ")[1];
                        }

                    } else {// get value of plagiarism
                        if (configLines.get(4).split("=")[1].equals("true")) {
                            plag = logLines.get(5).split(": ")[1];
                        }
                    }
                    pnt = Double.parseDouble(point);
                    if (format.equals("false")) {
                        pnt -= 1;
                    }
                    if (configLines.get(3).split("=")[1].equals("true")) {
                        if (mode_cmt.equals("Fixed")) {
                            if (Double.parseDouble(comment) < Double.parseDouble(percentage)) {
                                pnt -= Double.parseDouble(subPoint);
                            }
                        } else {
                            pnt -= Double.parseDouble(percentage) * Double.parseDouble(subPoint) * 0.01;
                        }
                    }

                    if (Double.parseDouble(plag) >= 75) {
                        pnt = 0.0;
                    }
                    hmTable.get(s).setValueAt(round(pnt * (11 - pen) * 0.1, 1), i, j);
                    total += round((pnt * (11 - pen) * 0.1), 1);

                } else {
                    if (point == "") {
                        hmTable.get(s).setValueAt("Not submit", i, j);
                    } else {
                        hmTable.get(s).setValueAt(point, i, j);
                    }
                }
            }
            hmTable.get(s).setValueAt(total, i, tb.getColumnCount() - 1);
        }
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     * Update data of contestants and tasks
     */
    private void updateTable() {
        int input = JOptionPane.showConfirmDialog(null,
                "Data will be lost. Do you want to refresh?", "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (input == 0) {
            try {
                btnUpdateOnline.setEnabled(true);
                btnImportExcel.setEnabled(true);
                btnExportExcel.setEnabled(true);
                btnJudgeAContest.setEnabled(true);
                tabTable.removeAll();
                listProbPath.clear();
                listProbName.clear();
                listStuPath.clear();
                listStuName.clear();
                listStuClassPath.clear();
                listStuClassName.clear();
                lsParentProblem.clear();

                File[] dirParentProblem = new File(problemDir).listFiles(File::isDirectory);
                for (File dir : dirParentProblem) {
                    if (dir.getName().contains("$")) {
                        continue;
                    }
                    ArrayList<String> lsHead1 = new ArrayList<>();
                    lsHead1.add("Contestants");
                    File[] dirHead = new File(dir.getAbsolutePath()).listFiles(File::isDirectory);
                    for (File file : dirHead) {
                        lsHead1.add(file.getName());
                        listProbPath.add(file.getAbsolutePath());
                        listProbName.add(file.getName());
                    }
                    lsHead1.add("Total");
                    lsParentProblem.add(lsHead1);
                }

                int index = 0;
                File[] dirStu = new File(studentDir).listFiles(File::isDirectory);
                for (File dir : dirStu) {
                    if (dir.getName().contains("$")) {
                        continue;
                    }
                    int totalStudent = 0;
                    listStuClassPath.add(dir.getAbsolutePath());
                    listStuClassName.add(dir.getName());
                    ArrayList<ArrayList<String>> lsRow1 = new ArrayList<>();
                    File[] dirStuClass = new File(dir.getAbsolutePath()).listFiles(File::isDirectory);
                    for (File dirStuClas : dirStuClass) {
                        if (dirStuClas.getName().contains("$")) {
                            continue;
                        }
                        ArrayList<String> enity = new ArrayList<>();
                        enity.add(dirStuClas.getName());
                        for (int i = 0; i < lsParentProblem.get(index).size() - 1; ++i) {
                            enity.add("Not submit");
                        }
                        lsRow1.add(enity);
                        listStuPath.add(dirStuClas.getAbsolutePath());
                        listStuName.add(dirStuClas.getName());
                        hmStuIndex.put(dirStuClas.getName() + dir.getName(), totalStudent++);

                        hmStuPath.put(dirStuClas.getName() + dir.getName(), dirStuClas.getAbsolutePath());
                        hmTotalPoint.put(dirStuClas.getName() + dir.getName(), 0);
                    }

                    DefaultTableModel dtm = new DefaultTableModel(lsParentProblem.get(index).toArray(), 0);
                    for (ArrayList<String> arrayList : lsRow1) {
                        dtm.addRow(arrayList.toArray());
                    }
                    JTable tb = new JTable(dtm) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                            Component comp = super.prepareRenderer(renderer, row, col);
                            String value = getModel().getValueAt(row, col).toString();
                            if (value.contains("(")) { // set color red if point is different
                                comp.setForeground(Color.red);
                            } else if (value.equalsIgnoreCase("Not submit")) {
                                comp.setForeground(Color.lightGray);
                            } else {
                                comp.setForeground(Color.black);
                            }
                            return comp;
                        }
                    };
                    setupTable(tb, dir.getName());
                    hmTable.put(dir.getName(), tb);
                    JScrollPane scr = new JScrollPane(tb);
                    tabTable.addTab(dir.getName(), scr);
                    index++;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please add the full path!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Setup styles table show data in table NhanNT
     *
     * @param tb
     */
    private void setupTable(JTable tb, String nameTable) {
        tb.setEnabled(false);
        tb.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tb.setColumnSelectionAllowed(true);
        tb.setRowSelectionAllowed(true);
        tb.setRowHeight(30);
        tb.setShowGrid(true);

        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    if (tool.parent.judge.isAlive()) {
                        return;
                    }
                } catch (Exception e) {
                }
                int row = tb.rowAtPoint(me.getPoint());
                int col = tb.columnAtPoint(me.getPoint());
                if (SwingUtilities.isRightMouseButton(me) == true) {
                    // If has judged
                    if (col != tb.getColumnCount() - 1 && col != 0) {
                        // Láº¥y tÃªn bÃ i ná»™p trÃªn báº£ng Ä‘iá»ƒm
                        String contest = tabTable.getTitleAt(tabTable.getSelectedIndex());
                        String user = tb.getValueAt(row, 0).toString();
                        String problem = tb.getColumnName(col);
                        String log = "[" + contest + "][" + user + "][" + problem + "]";
                        // Duyá»‡t thÆ° má»¥c logs chá»©a káº¿t quáº£ bÃ i lÃ m
                        File[] pathlog = new File(folderNopbaiPath + "/Logs/" + nameTable).listFiles();
                        Arrays.sort(pathlog, Comparator.comparingLong(File::lastModified).reversed()); // sáº¯p xáº¿p theo thá»�i gian giáº£m dáº§n
                        String popcontent = "";
                        for (File file : pathlog) {
                            if (file.getAbsolutePath().contains(log)) {
                                // Ä‘Æ°á»�ng dáº«n bÃ i lÃ m khi chá»�n trÃªn báº£ng Ä‘iá»ƒm
                                popcontent = file.getAbsolutePath();
                                break;
                            }
                        }
                        ArrayList<String> nopbaipath = new ArrayList<>();
                        ArrayList<String> nopbainame = new ArrayList<>();
                        pro = new frmProcess(tool.parent);
                        for (int i = 0; i < listStuPath.size(); ++i) {
                            File[] directories = new File(listStuPath.get(i)).listFiles(File::isFile);
                            for (File dir : directories) {
                                if (dir.getName().equalsIgnoreCase(".htaccess") || !tmptype.contains(tool.getType(dir.getName()))) {
                                    continue;
                                }
                                if (dir.getName().contains(log)) {
                                    nopbaipath.add(dir.getAbsolutePath());
                                    nopbainame.add(dir.getName());
                                }
                            }
                        }
                        //your popup menu goes here.
                        pop = new RowPopup(tool, tb, popcontent, nopbaipath, nopbainame);
                        pop.show(me.getComponent(), me.getX(), me.getY());
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                tb.clearSelection();
            }
        });
        tb.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                int row = tb.rowAtPoint(me.getPoint());
                int col = tb.columnAtPoint(me.getPoint());
                tb.clearSelection();
                tb.changeSelection(row, col, false, false);
            }
        });
    }

    /**
     * Auto judge in online
     */
    public void autoJudge() {
        if (hmStuPath.isEmpty()) {
            return;
        }
        String contestantPath;
        String user;
        String stuClass;
        String problem;
        String log;
        String dirContestantPath;
        listNopbaiPath.clear();
        listNopbaiName.clear();
        File[] directories = new File(folderNopbaiPath).listFiles(File::isFile);
        Arrays.sort(directories, Comparator.comparingLong(File::lastModified)); // sáº¯p xáº¿p theo thá»�i gian tÄƒng dáº§n
        for (File dir : directories) {
            if (dir.getName().equalsIgnoreCase(".htaccess") || !tmptype.contains(tool.getType(dir.getName()))) {
                continue; // skip if not .c, .cpp. .py
            }
            listNopbaiPath.add(dir.getAbsolutePath());
            listNopbaiName.add(dir.getName());
            user = tool.getUser(dir.getName());
            stuClass = tool.getStuClass(dir.getName());
            problem = tool.getProblem(dir.getName());
            log = "[" + user + "][" + problem + "]";
            dirContestantPath = hmStuPath.get(user + stuClass);
            File[] dirContestant = new File(dirContestantPath).listFiles(File::isFile);
            try {
                for (File file : dirContestant) {
                    if (file.getName().contains(log)) { // Náº¿u Ä‘Ã£ cÃ³ bÃ i theo mÃ£ Ä‘á»� trong thumuclambai, thÃ¬ bá»� vÃ o $History
                        if (Files.notExists(Paths.get(dirContestantPath + "/$History/"))) {
                            new File(dirContestantPath + "/$History/").mkdirs();
                        }
                        fhandle.copyFile(file.getPath(), dirContestantPath + "/$History/" + file.getName());
                        Files.deleteIfExists(Paths.get(file.getPath()));
                    }
                }

                contestantPath = dirContestantPath + "/" + dir.getName();
                // copy to contestant folder
                fhandle.copyFile(dir.getAbsolutePath(), contestantPath);
            } catch (IOException ex) {
                Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tool.foo(listNopbaiPath, listNopbaiName, true);
    }

    /**
     * Initialize components
     */
    private void init() {
        this.btnUpdateOnline.setEnabled(false);
        this.btnUpdateOnline.setEnabled(false);
        this.btnImportExcel.setEnabled(false);
        this.btnExportExcel.setEnabled(false);
//        this.btnJudge.setEnabled(false);
        this.tmptype = "cpppysqljava"; // C, C++, python
        this.listNopbaiPath = new ArrayList<>();
        this.listNopbaiName = new ArrayList<>();
        this.listProbPath = new ArrayList<>();
        this.listProbName = new ArrayList<>();
        this.listStuPath = new ArrayList<>();
        this.listStuName = new ArrayList<>();
        this.listStuClassPath = new ArrayList<>();
        this.listStuClassName = new ArrayList<>();
        this.hmStuPath = new HashMap<>();
        this.hmTable = new HashMap<>();
        this.hmStuIndex = new HashMap<>();
        this.hmTotalPoint = new HashMap<>();
        this.lsParentProblem = new ArrayList<>();
        this.tool = new Judge(this);
        this.fhandle = new FileHandle();
        this.ostype = OsCheck.getOperatingSystemType();
        this.props = new Properties();
        this.studentDir = ".";
        this.problemDir = ".";
        this.folderNopbaiPath = ".";
        this.excelPath = ".";
        this.typec = "gcc";
        this.typecpp = "g++";
        this.typepy = "python";
        this.typejava = "java";
//        this.checkFunction = false;
        this.checkFormat = false;
        this.checkCmt = false;
        this.checkWall = false;
        this.checkCmtMode = "";
        this.timeLimit = 1000;
        this.memoryLimit = 1000;
        // file chooser
        this.fileFolderProblem.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileFolderStudent.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileFolderNopbai.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        listContestLoadPannalty = new ArrayList<>();
    }

    /**
     * Initialize Properties read config file then assign value to global
     * variable NhanNT
     */
    private void initProperties() {
        switch (this.ostype) {
            case Windows:
                this.FILE = new File("windows.properties");
                break;
            case MacOS:
                break;
            case Linux:
                this.FILE = new File("linux.properties");
                break;
            case Other:
                break;
        }

        try {
            String AbPath = this.FILE.getAbsolutePath();
            String path1 = AbPath.substring(0, AbPath.length() - 33);
            FileInputStream in = new FileInputStream(this.FILE);
            this.props.load(in);
            this.studentDir = path1 + this.props.getProperty("studentDir");
            this.problemDir = path1 + this.props.getProperty("problemDir");
            this.testcaseEventPath = path1 + this.props.getProperty("testcaseEventPath");
            this.workspaceEventPath = path1 + this.props.getProperty("workspaceEventPath");
            this.folderNopbaiPath = path1 + this.props.getProperty("folderNopbaiPath");
            this.excelPath = this.props.getProperty("excelPath");
            this.typecpp = this.props.getProperty("typecpp");
            this.typec = this.props.getProperty("typec");
            this.typepy = this.props.getProperty("typepy");
            this.typejava = this.props.getProperty("typejava");
//            this.checkFunction = Boolean.parseBoolean(this.props.getProperty("checkFunction"));
            this.checkFormat = Boolean.parseBoolean(this.props.getProperty("checkFormat"));
            this.checkCmt = Boolean.parseBoolean(this.props.getProperty("checkCmt"));
            this.checkWall = Boolean.parseBoolean(this.props.getProperty("checkWall"));
            this.timeLimit = Integer.parseInt(this.props.getProperty("timeLimit"));
            this.memoryLimit = Integer.parseInt(this.props.getProperty("memoryLimit"));
            this.astylePath = this.props.getProperty("astylePath");
            this.simPath = this.props.getProperty("simPath");
            if (!this.folderNopbaiPath.equals("")) {
                Path path = Paths.get(this.folderNopbaiPath);
                FileHandlerTest fileHandlerTest = new FileHandlerTest(this);
                FileWatcher fileWatcher = new FileWatcher(path, fileHandlerTest, false, StandardWatchEventKinds.ENTRY_CREATE); //For non-recursive polling
                watcherThread = new Thread(fileWatcher);
                watcherThread.start();
            }
        } catch (IOException e) {
            try {
                this.FILE.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        //Change UI look of table.
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmJudge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmJudge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmJudge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmJudge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmJudge().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton btnImportExcel;
    public javax.swing.JButton btnJudgeAContest;
    public javax.swing.JButton btnJudgeAContest1;
    public javax.swing.JButton btnJudgeAllContests;
    private javax.swing.JButton btnListProblem;
    private javax.swing.JButton btnSetting;
    public javax.swing.JButton btnUpdateOnline;
    private javax.swing.JFileChooser fileFile;
    public javax.swing.JFileChooser fileFolderNopbai;
    private javax.swing.JFileChooser fileFolderProblem;
    private javax.swing.JFileChooser fileFolderStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlToolbar;
    public javax.swing.JTabbedPane tabTable;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables

    private void addEvent() {
        FileTestcaseHandlerTest folderHandlerTest = new FileTestcaseHandlerTest(this);
        Path path = Paths.get(testcaseEventPath);
        FileWatcher fileWatcher;
        try {
            fileWatcher = new FileWatcher(path, folderHandlerTest, false, StandardWatchEventKinds.ENTRY_MODIFY); //For non-recursive polling
            Thread watcherThread = new Thread(fileWatcher);
            watcherThread.start();
        } catch (IOException ex) {
            Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
        }

        FileWorkspaceHandlerTest fileWorkspaceHandlerTest = new FileWorkspaceHandlerTest(this);
        Path workspaceEventPathPath = Paths.get(workspaceEventPath);
        FileWatcher fileWorkspaceWatcher;
        try {
            fileWorkspaceWatcher = new FileWatcher(workspaceEventPathPath, fileWorkspaceHandlerTest, false, StandardWatchEventKinds.ENTRY_MODIFY); //For non-recursive polling
            Thread watcherworkspaceThread = new Thread(fileWorkspaceWatcher);
            watcherworkspaceThread.start();
        } catch (IOException ex) {
            Logger.getLogger(frmJudge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
