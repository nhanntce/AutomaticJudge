/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package judge;

import java.awt.Toolkit;

/**
 *
 * @author ADMIN
 */
public class frmAbout extends javax.swing.JFrame {
    private frmJudge parent;
    /**
     * Creates new form frmAbout
     * @param parent main frame
     */
    public frmAbout(frmJudge parent) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/btninfo.png")));
        this.parent = parent; 
//        this.setLayout(new BorderLayout());
//        this.setContentPane(new JLabel(new ImageIcon("./src/img/background_about.png")));
//        this.setResizable(false);
//        ImagePanel imagePanel = new ImagePanel();
//        this.getContentPane().add(imagePanel, BorderLayout.CENTER);
//        loadAndShowImage(imagePanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAboutUs = new javax.swing.JPanel();
        lblContactUs = new javax.swing.JLabel();
        lblThaoNM = new javax.swing.JLabel();
        lblIconThaoNM = new javax.swing.JLabel();
        lnlEmailThaoNM = new javax.swing.JLabel();
        lblIconThaoNM1 = new javax.swing.JLabel();
        lnlEmailThaoNM1 = new javax.swing.JLabel();
        lblThaoNM1 = new javax.swing.JLabel();
        lblIconThaoNM2 = new javax.swing.JLabel();
        lnlEmailThaoNM2 = new javax.swing.JLabel();
        lblIconThaoNM3 = new javax.swing.JLabel();
        lnlEmailThaoNM3 = new javax.swing.JLabel();
        lblThaoNM2 = new javax.swing.JLabel();
        lblIconThaoNM4 = new javax.swing.JLabel();
        lnlEmailThaoNM4 = new javax.swing.JLabel();
        lblIconThaoNM5 = new javax.swing.JLabel();
        lnlEmailThaoNM5 = new javax.swing.JLabel();
        lblThaoNM3 = new javax.swing.JLabel();
        lblIconThaoNM6 = new javax.swing.JLabel();
        lnlEmailThaoNM6 = new javax.swing.JLabel();
        lblIconThaoNM7 = new javax.swing.JLabel();
        lnlEmailThaoNM7 = new javax.swing.JLabel();
        lblThaoNM4 = new javax.swing.JLabel();
        lblIconThaoNM8 = new javax.swing.JLabel();
        lnlEmailThaoNM8 = new javax.swing.JLabel();
        lblIconThaoNM9 = new javax.swing.JLabel();
        lnlEmailThaoNM9 = new javax.swing.JLabel();
        lblThaoNM5 = new javax.swing.JLabel();
        lblIconThaoNM10 = new javax.swing.JLabel();
        lnlEmailThaoNM10 = new javax.swing.JLabel();
        lblIconThaoNM11 = new javax.swing.JLabel();
        lnlEmailThaoNM11 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About Us");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnlAboutUs.setBackground(new java.awt.Color(253, 239, 239));
        pnlAboutUs.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblContactUs.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblContactUs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContactUs.setLabelFor(pnlAboutUs);
        lblContactUs.setText("CONTACT US");
        lblContactUs.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(205, 187, 167), new java.awt.Color(218, 208, 194)));

        lblThaoNM.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblThaoNM.setText("NGUYEN MINH THAO");

        lblIconThaoNM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N

        lnlEmailThaoNM.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM.setText("nguyenthaobt1@gmail.com");

        lblIconThaoNM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/call.png"))); // NOI18N

        lnlEmailThaoNM1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM1.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM1.setText("0363154540");

        lblThaoNM1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblThaoNM1.setText("NGUYEN THANH NHAN");

        lblIconThaoNM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N

        lnlEmailThaoNM2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM2.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM2.setText("nhannt909@gmail.com");

        lblIconThaoNM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/call.png"))); // NOI18N

        lnlEmailThaoNM3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM3.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM3.setText("0965689616");

        lblThaoNM2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblThaoNM2.setText("NGUYEN CHI LINH");

        lblIconThaoNM4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N

        lnlEmailThaoNM4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM4.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM4.setText("chilinh361@gmail.com");

        lblIconThaoNM5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/call.png"))); // NOI18N

        lnlEmailThaoNM5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM5.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM5.setText("0974440621");

        lblThaoNM3.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblThaoNM3.setText("VO TRUONG HAI DANG");

        lblIconThaoNM6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N

        lnlEmailThaoNM6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM6.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM6.setText("dangvth1999@gmail.com");

        lblIconThaoNM7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/call.png"))); // NOI18N

        lnlEmailThaoNM7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM7.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM7.setText("0916931735");

        lblThaoNM4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblThaoNM4.setText("PHAM TRUONG ANH TU");

        lblIconThaoNM8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N

        lnlEmailThaoNM8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM8.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM8.setText("tupta1999@gmail.com");

        lblIconThaoNM9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/call.png"))); // NOI18N

        lnlEmailThaoNM9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM9.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM9.setText("0343114777");

        lblThaoNM5.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblThaoNM5.setText("NGUYEN VUONG KHANG HY");

        lblIconThaoNM10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N

        lnlEmailThaoNM10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM10.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM10.setText("khanghy3004@gmail.com");

        lblIconThaoNM11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/call.png"))); // NOI18N

        lnlEmailThaoNM11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlEmailThaoNM11.setLabelFor(lblIconThaoNM);
        lnlEmailThaoNM11.setText("0902857788");

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_2.png"))); // NOI18N
        lblLogo.setMaximumSize(new java.awt.Dimension(1984, 465));
        lblLogo.setMinimumSize(new java.awt.Dimension(1984, 465));

        javax.swing.GroupLayout pnlAboutUsLayout = new javax.swing.GroupLayout(pnlAboutUs);
        pnlAboutUs.setLayout(pnlAboutUsLayout);
        pnlAboutUsLayout.setHorizontalGroup(
            pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContactUs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAboutUsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAboutUsLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM5))
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM4))
                    .addComponent(lblThaoNM2)
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM3))
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM2))
                    .addComponent(lblThaoNM1)
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM1))
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM))
                    .addComponent(lblThaoNM))
                .addGap(71, 71, 71)
                .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM11))
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM10))
                    .addComponent(lblThaoNM5)
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM9))
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM8))
                    .addComponent(lblThaoNM4)
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM7))
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblIconThaoNM6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnlEmailThaoNM6))
                    .addComponent(lblThaoNM3))
                .addGap(83, 83, 83))
        );
        pnlAboutUsLayout.setVerticalGroup(
            pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContactUs)
                .addGap(51, 51, 51)
                .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblThaoNM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM)
                            .addComponent(lnlEmailThaoNM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM1)
                            .addComponent(lnlEmailThaoNM1))
                        .addGap(18, 18, 18)
                        .addComponent(lblThaoNM1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM2)
                            .addComponent(lnlEmailThaoNM2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM3)
                            .addComponent(lnlEmailThaoNM3))
                        .addGap(18, 18, 18)
                        .addComponent(lblThaoNM2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM4)
                            .addComponent(lnlEmailThaoNM4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM5)
                            .addComponent(lnlEmailThaoNM5)))
                    .addGroup(pnlAboutUsLayout.createSequentialGroup()
                        .addComponent(lblThaoNM3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM6)
                            .addComponent(lnlEmailThaoNM6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM7)
                            .addComponent(lnlEmailThaoNM7))
                        .addGap(18, 18, 18)
                        .addComponent(lblThaoNM4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM8)
                            .addComponent(lnlEmailThaoNM8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM9)
                            .addComponent(lnlEmailThaoNM9))
                        .addGap(18, 18, 18)
                        .addComponent(lblThaoNM5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM10)
                            .addComponent(lnlEmailThaoNM10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconThaoNM11)
                            .addComponent(lnlEmailThaoNM11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.btnAbout.setEnabled(true);
    }//GEN-LAST:event_formWindowClosed


//    private static void loadAndShowImage(ImagePanel imagePanel) {
//        try {
//            BufferedImage image = ImageIO.read(new File("./src/img/background_about.png"));
//            imagePanel.setImage(image);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

//    private static class ImagePanel extends JPanel {
//
//        private BufferedImage image;
//
//        void setImage(BufferedImage image) {
//            this.image = image;
//            repaint();
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            if (image != null) {
//                g.drawImage(image, 0, 0, null);
//            }
//        }
//
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblContactUs;
    private javax.swing.JLabel lblIconThaoNM;
    private javax.swing.JLabel lblIconThaoNM1;
    private javax.swing.JLabel lblIconThaoNM10;
    private javax.swing.JLabel lblIconThaoNM11;
    private javax.swing.JLabel lblIconThaoNM2;
    private javax.swing.JLabel lblIconThaoNM3;
    private javax.swing.JLabel lblIconThaoNM4;
    private javax.swing.JLabel lblIconThaoNM5;
    private javax.swing.JLabel lblIconThaoNM6;
    private javax.swing.JLabel lblIconThaoNM7;
    private javax.swing.JLabel lblIconThaoNM8;
    private javax.swing.JLabel lblIconThaoNM9;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblThaoNM;
    private javax.swing.JLabel lblThaoNM1;
    private javax.swing.JLabel lblThaoNM2;
    private javax.swing.JLabel lblThaoNM3;
    private javax.swing.JLabel lblThaoNM4;
    private javax.swing.JLabel lblThaoNM5;
    private javax.swing.JLabel lnlEmailThaoNM;
    private javax.swing.JLabel lnlEmailThaoNM1;
    private javax.swing.JLabel lnlEmailThaoNM10;
    private javax.swing.JLabel lnlEmailThaoNM11;
    private javax.swing.JLabel lnlEmailThaoNM2;
    private javax.swing.JLabel lnlEmailThaoNM3;
    private javax.swing.JLabel lnlEmailThaoNM4;
    private javax.swing.JLabel lnlEmailThaoNM5;
    private javax.swing.JLabel lnlEmailThaoNM6;
    private javax.swing.JLabel lnlEmailThaoNM7;
    private javax.swing.JLabel lnlEmailThaoNM8;
    private javax.swing.JLabel lnlEmailThaoNM9;
    private javax.swing.JPanel pnlAboutUs;
    // End of variables declaration//GEN-END:variables
}
