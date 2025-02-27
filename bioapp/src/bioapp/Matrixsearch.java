/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Matrixsearch.java
 *
 * Created on Jul 10, 2013, 12:02:47 PM
 */
package bioapp;

import genelib.Createseqalignmentfile;
import genelib.Readalignmentfile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author slim
 */
public class Matrixsearch extends javax.swing.JFrame {

    BorderLayout myLayout = new BorderLayout();
    ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("images/icons/orislogo.png"));

    /** Creates new form Matrixsearch */
    public Matrixsearch() {
        initComponents();
        this.setIconImage(img.getImage());
        this.setLayout(myLayout);
        this.setTitle("Enter Wt-Matrix");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldcolumns = new javax.swing.JTextField();
        jButtoncreate = new javax.swing.JButton();
        jButtonopen = new javax.swing.JButton();
        jLabelopenfile = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter no. of columns : ");

        jButtoncreate.setText("Create");
        jButtoncreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoncreateActionPerformed(evt);
            }
        });

        jButtonopen.setText("Open");
        jButtonopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonopenActionPerformed(evt);
            }
        });

        jLabelopenfile.setText("Choose MSA file");

        jLabel3.setText("Or");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabelopenfile, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonopen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtoncreate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jTextFieldcolumns, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldcolumns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButtoncreate)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonopen)
                    .addComponent(jLabelopenfile))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtoncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoncreateActionPerformed
        // TODO add your handling code here:

        final int cols = Integer.parseInt(jTextFieldcolumns.getText());
        //only 4 rows for A,G,C and T
        final int rows = 4;
        //create array of jtextfields
        final JTextField[] textfieldarr = new JTextField[rows * cols];

        //create four jtextfields for AGCT
        JTextField textfieldA = new JTextField("A");
        textfieldA.setEditable(false);
        textfieldA.setBackground(Color.green);
        JTextField textfieldG = new JTextField("G");
        textfieldG.setEditable(false);
        textfieldG.setBackground(Color.yellow);
        JTextField textfieldC = new JTextField("C");
        textfieldC.setEditable(false);
        textfieldC.setBackground(Color.cyan);
        JTextField textfieldT = new JTextField("T");
        textfieldT.setEditable(false);
        textfieldT.setBackground(Color.red);

        //create a new panel to have textfeilds as matrix
        JPanel matrixpanel = new JPanel(new GridLayout(rows, cols + 1));
        //create panel with Go button
        JPanel gopanel = new JPanel(new GridBagLayout());
        JButton gobutton = new JButton("Go");
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        /*c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
         */
        gopanel.add(gobutton, c);


        for (int i = 0; i < ((rows) * (cols)); i++) {
            if (i == 0) {
                matrixpanel.add(textfieldA);
            } else if (i == cols * 1) {
                matrixpanel.add(textfieldG);
            } else if (i == cols * 2) {
                matrixpanel.add(textfieldC);
            } else if (i == cols * 3) {
                matrixpanel.add(textfieldT);
            }
            textfieldarr[i] = new JTextField("");
            matrixpanel.add(textfieldarr[i]);

        }

        final float[] tempmatrix = new float[rows * cols];





        //action for go button
        gobutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {


                //create float matrix for copying values into
                float[][] wtmatrix = new float[rows][cols];
                //flag to check error in matrix
                int errorflag = 0;
                //p for row q for col of wtmatrix;k for index of jtextfieldarr
                int p = 0;
                int q = 0;
                int k = 0;
                for (p = 0; p < rows; p++) {
                    for (q = 0; q < cols; q++) {

                        wtmatrix[p][q] = Float.parseFloat(textfieldarr[k++].getText());
                    }

                }


                //check row sum to be equal to one

                float rowsum = 0;
                for (q = 0; q < cols; q++) {
                    if (errorflag == 1) {
                        break;
                    }
                    rowsum = 0;
                    for (p = 0; p < rows; p++) {

                        rowsum += wtmatrix[p][q];
                    }
                    System.out.printf("\nrowsum of col %d is %f", q, rowsum);
                    //error if rowsum is not 1
                    if (rowsum != 1) {
                        errorflag = 1;
                        JOptionPane.showMessageDialog(null, "Error in matrix values, row sum is not 1!!");
                    }

                }
                if (errorflag == 0) {
                    Entersequence ob = new Entersequence(wtmatrix, rows, cols);
                    ob.setVisible(true);


                }



            }
        });

        //2this.dispose();

        this.add(matrixpanel, myLayout.PAGE_START);
        this.add(gopanel, myLayout.PAGE_END);
        this.setSize(600, 250);
        jButtoncreate.setVisible(false);
        jTextFieldcolumns.setVisible(false);
        jLabel1.setVisible(false);
        jLabel3.setVisible(false);
        jLabelopenfile.setVisible(false);
        jButtonopen.setVisible(false);

    }//GEN-LAST:event_jButtoncreateActionPerformed

    private void jButtonopenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonopenActionPerformed
        // TODO add your handling code here:
        new Createseqalignmentfile().run();
        JFileChooser chooser = new JFileChooser();
        //chooser.addChoosableFileFilter(ff);


        int status = chooser.showOpenDialog(jLabelopenfile);
        //String filedata;
        if (status == JFileChooser.APPROVE_OPTION) {
            File chosenFile = chooser.getSelectedFile();
            Readalignmentfile ob = new Readalignmentfile(chosenFile.getAbsolutePath());
            ob.read();
            //get no of columns
            //subtract 1 to get actual columns
            final int cols = ob.returncols() - 1;
            //4 rows for A,G,C,T
            final int rows=4;
            //get wtmatrix
            final float[][] wtmatrix = ob.returnmatrix();

                   
       
        //hide othe components and display wt matrix
          
        //same code as for creating matrix; here matrix values are added by calculating matrix from the read file
        //create array of jtextfields
        final JTextField[] textfieldarr = new JTextField[rows * cols];

        //create four jtextfields for AGCT
        JTextField textfieldA = new JTextField("A");
        textfieldA.setEditable(false);
        textfieldA.setBackground(Color.green);
        JTextField textfieldG = new JTextField("G");
        textfieldG.setEditable(false);
        textfieldG.setBackground(Color.yellow);
        JTextField textfieldC = new JTextField("C");
        textfieldC.setEditable(false);
        textfieldC.setBackground(Color.cyan);
        JTextField textfieldT = new JTextField("T");
        textfieldT.setEditable(false);
        textfieldT.setBackground(Color.red);

        //create a new panel to have textfeilds as matrix
        JPanel matrixpanel = new JPanel(new GridLayout(rows, cols + 1));
        //create panel with Go button
        JPanel gopanel = new JPanel(new GridBagLayout());
        JButton gobutton = new JButton("Go");
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        /*c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
         */
        gopanel.add(gobutton, c);

        int row=0;
        int col=0;
        int index=0;
        for (int i = 0; i < ((rows) * (cols)); i++) {
            if (i == 0) {
                matrixpanel.add(textfieldA);
            } else if (i == cols * 1) {
                matrixpanel.add(textfieldG);
            } else if (i == cols * 2) {
                matrixpanel.add(textfieldC);
            } else if (i == cols * 3) {
                matrixpanel.add(textfieldT);
            }
            //add values to matrix from wtmatrix
            textfieldarr[i] = new JTextField(""+wtmatrix[row][col++]);
            if(col>=cols){
                row++;
                col=0;
            }
            matrixpanel.add(textfieldarr[i]);

        }
            //action for go button
        gobutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {


                //create float matrix for copying values into
               // float[][] wtmatrix = new float[rows][cols];
                //flag to check error in matrix
                int errorflag = 0;
                //p for row q for col of wtmatrix;k for index of jtextfieldarr
                int p = 0;
                int q = 0;
                int k = 0;
                


                //check row sum to be equal to one
                float rowsum = 0;
                for (q = 0; q < cols; q++) {
                    if (errorflag == 1) {
                        break;
                    }
                    rowsum = 0;
                    for (p = 0; p < rows; p++) {

                        rowsum += wtmatrix[p][q];
                    }
                    //System.out.printf("\nrowsum of col %d is %f", q, rowsum);
                    //error if rowsum is not 1
                    if (rowsum != 1) {
                        errorflag = 1;
                        JOptionPane.showMessageDialog(null, "Error in matrix values, row sum is not 1!!");
                    }

                }
                if (errorflag == 0) {
                    Entersequence ob = new Entersequence(wtmatrix, rows, cols);
                    ob.setVisible(true);


                }



            }
        });

        //2this.dispose();

        this.add(matrixpanel, myLayout.PAGE_START);
        this.add(gopanel, myLayout.PAGE_END);
        this.setSize(600, 250);
        jButtoncreate.setVisible(false);
        jTextFieldcolumns.setVisible(false);
        jLabel1.setVisible(false);
        jLabel3.setVisible(false);
        jLabelopenfile.setVisible(false);
        jButtonopen.setVisible(false);

    }//GEN-LAST:event_jButtonopenActionPerformed
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Matrixsearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Matrixsearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Matrixsearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Matrixsearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Matrixsearch().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtoncreate;
    private javax.swing.JButton jButtonopen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelopenfile;
    private javax.swing.JTextField jTextFieldcolumns;
    // End of variables declaration//GEN-END:variables
}
