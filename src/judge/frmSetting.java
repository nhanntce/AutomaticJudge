package judge;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Nguyen Vuong Khang Hy Email: khanghy3004@gmail.com Automatic Judger
 */
public class frmSetting extends javax.swing.JFrame {

    frmJudge parent;

    /**
     * Creates new form frmSetting
     *
     * @param parent
     */
    public frmSetting(frmJudge parent) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/btnsettings.png")));
        this.parent = parent;
//        txtNopbaiPath.setText(parent.folderNopbaiPath);
        if (parent.checkFunction) {
            chkCheckFormat.setSelected(true);
        }
        if (parent.checkCmt) {
            chkCheckCmt.setSelected(true);
        }
        if (parent.checkWall) {
            chkWall.setSelected(true);
        }
        txtTimeLimit.setText(String.valueOf(parent.timeLimit));
        txtMemoryLimit.setText(String.valueOf(parent.memoryLimit));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        chkCheckFormat = new javax.swing.JCheckBox();
        chkCheckCmt = new javax.swing.JCheckBox();
        txtTimeLimit = new javax.swing.JTextField();
        lblTimeLimit = new javax.swing.JLabel();
        lblMemoryLimit = new javax.swing.JLabel();
        txtMemoryLimit = new javax.swing.JTextField();
        chkWall = new javax.swing.JCheckBox();
        chkApplyAll = new javax.swing.JCheckBox();
        lblNoteText = new javax.swing.JLabel();

        setTitle("Setting");
        setName("frmSetting"); // NOI18N

        btnOk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCheck.png"))); // NOI18N
        btnOk.setText("Accept");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCross.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        chkCheckFormat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkCheckFormat.setText("Check Format");
        chkCheckFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCheckFormatActionPerformed(evt);
            }
        });

        chkCheckCmt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkCheckCmt.setText("Check Comment");

        txtTimeLimit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblTimeLimit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTimeLimit.setText("Time Limit (ms):");

        lblMemoryLimit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMemoryLimit.setText("Memory Limit (MB):");

        txtMemoryLimit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        chkWall.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkWall.setText("All Warning");

        chkApplyAll.setText("Apply to all contest");

        lblNoteText.setForeground(new java.awt.Color(255, 0, 51));
        lblNoteText.setText("NOTE: You can setting for all contest");
        lblNoteText.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMemoryLimit)
                            .addComponent(lblTimeLimit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimeLimit)
                            .addComponent(txtMemoryLimit))
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkApplyAll)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkCheckFormat)
                                .addGap(18, 18, 18)
                                .addComponent(chkCheckCmt)
                                .addGap(18, 18, 18)
                                .addComponent(chkWall))
                            .addComponent(lblNoteText))
                        .addGap(0, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimeLimit)
                    .addComponent(txtTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMemoryLimit)
                    .addComponent(txtMemoryLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCheckFormat)
                    .addComponent(chkCheckCmt)
                    .addComponent(chkWall))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNoteText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkApplyAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * get config value from user and set to frmJudge NhanNT
     *
     * @param evt
     */
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        String selectedTab = parent.tabTable.getTitleAt(parent.tabTable.getSelectedIndex());
        String pathToSettingConfig = parent.problemDir + "\\" + selectedTab + "\\config.txt";

        File configFile = new File(pathToSettingConfig);
        try {
            //delete content of config file
            PrintWriter writer = new PrintWriter(configFile);
            writer.print("");
            writer.close();

            FileWriter fileWriter = new FileWriter(configFile);
            //write new content into config file
            fileWriter.write("time_limit=" + txtTimeLimit.getText() + "\n");
            fileWriter.write("memory_limit=" + txtMemoryLimit.getText() + "\n");
            fileWriter.write("check_format=" + chkCheckFormat.isSelected() + "\n");
            fileWriter.write("check_comment=" + chkCheckCmt.isSelected() + "\n");
            fileWriter.close();
            
            //update apply all contest checkbox to property file
//            parent.props.setProperty("apply_all", Boolean.toString(chkApplyAll.isSelected()));
//            OutputStream out = new FileOutputStream(parent.FILE);
//            parent.props.store(out, "User properties");

//            parent.folderNopbaiPath = txtNopbaiPath.getText();
//            parent.checkFunction = chkCheckFunc.isSelected();
//            parent.checkCmt = chkCheckCmt.isSelected();
//            parent.checkWall = chkWall.isSelected();
//            parent.timeLimit = Integer.parseInt(txtTimeLimit.getText());
//            parent.memoryLimit = Integer.parseInt(txtMemoryLimit.getText());
//            Path path = Paths.get(parent.folderNopbaiPath);
//            FileHandlerTest fileHandlerTest = new FileHandlerTest(parent);
//            FileWatcher fileWatcher = new FileWatcher(path, fileHandlerTest, false, StandardWatchEventKinds.ENTRY_CREATE); //For non-recursive polling
//            Thread watcherThread = new Thread(fileWatcher);
//            watcherThread.start();  
        } catch (IOException ex) {
            Logger.getLogger(frmSetting.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            setVisible(false);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void chkCheckFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCheckFormatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCheckFormatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    public javax.swing.JCheckBox chkApplyAll;
    public javax.swing.JCheckBox chkCheckCmt;
    public javax.swing.JCheckBox chkCheckFormat;
    private javax.swing.JCheckBox chkWall;
    private javax.swing.JLabel lblMemoryLimit;
    public javax.swing.JLabel lblNoteText;
    private javax.swing.JLabel lblTimeLimit;
    public javax.swing.JTextField txtMemoryLimit;
    public javax.swing.JTextField txtTimeLimit;
    // End of variables declaration//GEN-END:variables
}
