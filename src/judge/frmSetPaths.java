/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package judge;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class frmSetPaths extends javax.swing.JFrame {

    frmJudge parent;

    /**
     * Creates new form frmSetPaths
     */
    public frmSetPaths(frmJudge parent) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/btnsettings.png")));
        this.parent = parent;

        txtFolderWorkspace.setText(parent.studentDir);
        txtFolderTestcase.setText(parent.problemDir);
        txtStudentSubmission.setText(parent.folderNopbaiPath);
        this.fileChooserWorkspace.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileChooserTestcase.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooserWorkspace = new javax.swing.JFileChooser();
        fileChooserTestcase = new javax.swing.JFileChooser();
        txtFolderWorkspace = new javax.swing.JTextField();
        lbFolderWorkspace = new javax.swing.JLabel();
        btnFolderWorkspace = new javax.swing.JButton();
        txtFolderTestcase = new javax.swing.JTextField();
        lbFolderTestcase = new javax.swing.JLabel();
        btnFolderTestcase = new javax.swing.JButton();
        txtStudentSubmission = new javax.swing.JTextField();
        lbStudentSubmission = new javax.swing.JLabel();
        btnStudentSubmission = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Path Setting");
        setResizable(false);

        txtFolderWorkspace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbFolderWorkspace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbFolderWorkspace.setText("Folder workspace");

        btnFolderWorkspace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFolderWorkspace.setText("Select");
        btnFolderWorkspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFolderWorkspaceActionPerformed(evt);
            }
        });

        txtFolderTestcase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbFolderTestcase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbFolderTestcase.setText("Folder Testcase");

        btnFolderTestcase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFolderTestcase.setText("Select");
        btnFolderTestcase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFolderTestcaseActionPerformed(evt);
            }
        });

        txtStudentSubmission.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbStudentSubmission.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbStudentSubmission.setText("Folder online submission");

        btnStudentSubmission.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStudentSubmission.setText("Select");
        btnStudentSubmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentSubmissionActionPerformed(evt);
            }
        });

        btnAccept.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAccept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCheck.png"))); // NOI18N
        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtFolderWorkspace)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFolderWorkspace))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtFolderTestcase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFolderTestcase))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtStudentSubmission)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStudentSubmission))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFolderWorkspace)
                            .addComponent(lbFolderTestcase)
                            .addComponent(lbStudentSubmission))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(btnAccept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbFolderWorkspace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFolderWorkspace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFolderWorkspace))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFolderTestcase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFolderTestcase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFolderTestcase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbStudentSubmission)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStudentSubmission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStudentSubmission))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccept)
                    .addComponent(btnCancel))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFolderWorkspaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFolderWorkspaceActionPerformed
        if (!parent.listProbName.isEmpty() && !parent.listStuName.isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null,
                    "Data will be lost. Do you want to refresh?", "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (input != 0) {
                return;
            }
        }
        fileChooserWorkspace.setCurrentDirectory(new File(parent.studentDir));
        System.out.println(parent.studentDir);
        int choice = fileChooserWorkspace.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) { // if option Open file in JFileChooser
            File f = fileChooserWorkspace.getSelectedFile(); // get file
            String folderPath = f.getAbsolutePath(); // get path file
//            parent.studentDir = folderPath;
            txtFolderWorkspace.setText(folderPath);
        }
    }//GEN-LAST:event_btnFolderWorkspaceActionPerformed

    private void btnFolderTestcaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFolderTestcaseActionPerformed
        if (!parent.listProbName.isEmpty() && !parent.listStuName.isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null,
                    "Data will be lost. Do you want to refresh?", "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (input != 0) {
                return;
            }
        }
        fileChooserTestcase.setCurrentDirectory(new File(parent.problemDir));
        int choice = fileChooserTestcase.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) { // if option Open file in JFileChooser
            File f = fileChooserTestcase.getSelectedFile(); // get file
            String folderPath = f.getAbsolutePath(); // get path file
//            parent.problemDir = folderPath;
            txtFolderTestcase.setText(folderPath);
        }
    }//GEN-LAST:event_btnFolderTestcaseActionPerformed

    private void btnStudentSubmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentSubmissionActionPerformed
        parent.fileFolderNopbai.setCurrentDirectory(new File(parent.folderNopbaiPath));
        int choice = parent.fileFolderNopbai.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) { // if option Open file in JFileChooser
            File f = parent.fileFolderNopbai.getSelectedFile(); // get file
            txtStudentSubmission.setText(f.getAbsolutePath());
        }
    }//GEN-LAST:event_btnStudentSubmissionActionPerformed

    /**
     * @author NhanNT Load testcases, load workspaces, add event for scanning
     * submission folder
     * @param evt
     */
    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        try {
            //load test case 
            parent.problemDir = txtFolderTestcase.getText();
            parent.listProblem();
            parent.studentDir = txtFolderWorkspace.getText();
            parent.listStudent();

            //add event scan folder submission 
            parent.folderNopbaiPath = txtStudentSubmission.getText();
            Path path = Paths.get(txtStudentSubmission.getText());
            FileHandlerTest fileHandlerTest = new FileHandlerTest(parent);
            FileWatcher fileWatcher = new FileWatcher(path, fileHandlerTest, false, StandardWatchEventKinds.ENTRY_CREATE); //For non-recursive polling
            Thread watcherThread = new Thread(fileWatcher);
            watcherThread.start();
        } catch (IOException ex) {
            Logger.getLogger(frmSetting.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            setVisible(false);
        }
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFolderTestcase;
    private javax.swing.JButton btnFolderWorkspace;
    private javax.swing.JButton btnStudentSubmission;
    private javax.swing.JFileChooser fileChooserTestcase;
    private javax.swing.JFileChooser fileChooserWorkspace;
    private javax.swing.JLabel lbFolderTestcase;
    private javax.swing.JLabel lbFolderWorkspace;
    private javax.swing.JLabel lbStudentSubmission;
    public javax.swing.JTextField txtFolderTestcase;
    private javax.swing.JTextField txtFolderWorkspace;
    private javax.swing.JTextField txtStudentSubmission;
    // End of variables declaration//GEN-END:variables
}
