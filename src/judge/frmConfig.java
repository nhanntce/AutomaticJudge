package judge;

import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Vuong Khang Hy 
 * Email: khanghy3004@gmail.com 
 * Automatic Judger
 */
public class frmConfig extends javax.swing.JFrame {
    frmJudge parent;
    /**
     * Creates new form frmConfig
     * @param parent main frame
     */
    public frmConfig(frmJudge parent) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/btncompilerconfig.png")));
        this.parent = parent;
        tblConfig.setModel(new DefaultTableModel(
            new Object [][] {
                {".cpp", null},
                {".c", null},
                {".py", null},
                {".java", null}
            },
            new String [] {
                "File type", "Compiler"
            }
        ));
        this.setSize(500, 270);
        this.setResizable(false);
        tblConfig.setValueAt(parent.typecpp, 0, 1);
        tblConfig.setValueAt(parent.typec, 1, 1);
        tblConfig.setValueAt(parent.typepy, 2, 1);
        tblConfig.setValueAt(parent.typejava, 3, 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConfig = new javax.swing.JTable();
        btnOk = new javax.swing.JButton();

        setTitle("Configure the compilation environment");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
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

        tblConfig.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {".cpp", null},
                {".c", null},
                {".py", null}
            },
            new String [] {
                "File type", "Compiler"
            }
        ));
        jScrollPane1.setViewportView(tblConfig);
        tblConfig.setBounds(0, 0, 500, 100);

        btnOk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCheck.png"))); // NOI18N
        btnOk.setText("Accept");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 174, Short.MAX_VALUE)
                        .addComponent(btnOk)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
        parent.btnConfig.setEnabled(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        parent.typecpp = tblConfig.getValueAt(0, 1).toString();
        parent.typec = tblConfig.getValueAt(1, 1).toString();
        parent.typepy = tblConfig.getValueAt(2, 1).toString();
        parent.typejava = tblConfig.getValueAt(3, 1).toString();
        setVisible(false);
        parent.btnConfig.setEnabled(true);
    }//GEN-LAST:event_btnOkActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        parent.btnConfig.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.btnConfig.setEnabled(true);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConfig;
    // End of variables declaration//GEN-END:variables
}
