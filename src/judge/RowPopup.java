package judge;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Nguyen Vuong Khang Hy 
 * Email: khanghy3004@gmail.com 
 * Automatic Judger
 */
public class RowPopup extends JPopupMenu {

    public RowPopup(Judge tool, JTable tb, String popcontent, ArrayList<String> nopbaipath, ArrayList<String> nopbainame) {
        JMenuItem open = new JMenuItem("Open");
        JMenuItem show = new JMenuItem("Show result");
        JMenuItem judge = new JMenuItem("Judge");
        open.setIcon(new ImageIcon(getClass().getResource("/img/code.png")));
        show.setIcon(new ImageIcon(getClass().getResource("/img/detail.png")));
        judge.setIcon(new ImageIcon(getClass().getResource("/img/judge.png")));
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (nopbaipath.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error! Not found submission", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Thread desk = new Thread() {
                        @Override
                        public void run() {
                            try {
                                Desktop.getDesktop().open(new File(nopbaipath.get(0)));
                            } catch (IOException ex) {
                                Logger.getLogger(RowPopup.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    desk.start();
                }
            }
        });
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    JTextArea textArea = new JTextArea();
                    String strCurrentLine;
                    BufferedReader reader = null;
                    reader = new BufferedReader(new FileReader(popcontent));

                    while ((strCurrentLine = reader.readLine()) != null) {
                        textArea.append(strCurrentLine + "\n");
                    }
                    textArea.setLineWrap(true);
                    JScrollPane jsp = new JScrollPane(textArea) {
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(200, 300);
                        }
                    };
                    JOptionPane.showMessageDialog(null, jsp, "Details of result", JOptionPane.PLAIN_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error! Not found submission", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        judge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (nopbaipath.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error! Not found submission", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tool.foo(nopbaipath, nopbainame, false);
                }
            }
        });

        add(open);
        add(show);
        add(judge);
    }
}
