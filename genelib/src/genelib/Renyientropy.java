/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genelib;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author jnu
 */
public class Renyientropy extends Thread {

    ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("images/icons/orislogo.png"));
    private int flag = 0;
    final char[] sequence;
    final JFrame frame = new JFrame("Progress");
    private JProgressBar pBar = new JProgressBar();
    int winsize;
    int increament;
    double alpha;
    int saveflag;
    String filename;

    public Renyientropy(char[] seq, int wsize, int inc, double parameter, int save, String fname) {
        sequence = seq;
        winsize = wsize;
        increament = inc;
        alpha = parameter;
        filename = fname;
        saveflag = save;
    }

    @Override
    public void run() {
        int start = 0, totlwin = 0;

        //if savefile is required 
        BufferedWriter writer = null;
        String tmpindex;
        if (saveflag == 1) {
            filename = filename + ".txt";
            File f = new File(filename);
            try {
                writer = new BufferedWriter(new FileWriter(f));
            } catch (IOException ex) {
                Logger.getLogger(Searchseq.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                writer.write("Data for the Renyi's Entropy plot with alpha = " + String.valueOf(alpha));
                writer.newLine();
                writer.write("Winsize = " + String.valueOf(winsize));
                writer.write("\tIncreament  = " + String.valueOf(increament));
                writer.newLine();
                writer.write("Window Number\t Entropy H(x) ");
                writer.newLine();
            } catch (IOException ex) {
                Logger.getLogger(Searchseq.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        //get size to store all the results from each window
        for (start = 0; start + winsize <= sequence.length; start = start + increament) {
            totlwin++;
        }

        //for progress bar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        pBar.setMinimum(0);
        pBar.setMaximum(totlwin);
        pBar.setStringPainted(true);
        frame.add(pBar);
        frame.setSize(400, 80);
        //  frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(img.getImage());
        //set the frame in the middle of screen
        frame.setLocation((width / 2), (height / 2));
        frame.setResizable(false);
        frame.setVisible(true);

        int countA, countC, countG, countT;
        double pa, pc, pg, pt;
        Compositioncount sob2 = new Compositioncount();
        double[] enresults = new double[totlwin];
        double[] xaxis = new double[totlwin];
        int rindex = 0;
        char[] subsequence = new char[winsize];

        for (start = 0; start + winsize <= sequence.length; start = start + increament) {
            for (int i = 0; i < winsize; i++) {
                subsequence[i] = sequence[start + i];
            }
            countA = sob2.returncount(subsequence, 'A');
            countC = sob2.returncount(subsequence, 'C');
            countG = sob2.returncount(subsequence, 'G');
            countT = sob2.returncount(subsequence, 'T');


            // calculate pilogpi
            pa = ((double) countA / (double) subsequence.length);
            pc = ((double) countC / (double) subsequence.length);
            pg = ((double) countG / (double) subsequence.length);
            pt = ((double) countT / (double) subsequence.length);

            pa = Math.pow(pa, alpha);
            pc = Math.pow(pc, alpha);
            pg = Math.pow(pg, alpha);
            pt = Math.pow(pt, alpha);

            enresults[rindex] = (double) ((1 / (1 - alpha)) * (Math.log(pa + pc + pg + pt) / Math.log(2)));
            xaxis[rindex] = rindex + 1;
            rindex++;

            if (rindex % 100 == 0) {
                //update progress bar
                pBar.setValue(rindex);
                pBar.update(pBar.getGraphics());
            }
        }

        frame.dispose();
        if (saveflag == 1) {
            for (int i = 0; i < enresults.length; i++) {
                try {
                    //   System.out.printf("\n%d\t%f", i + 1, atresults[i]);
                    writer.write(String.valueOf(i + 1) + "\t" + String.valueOf(enresults[i]));
                    writer.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(Entropy.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //after writing
        if (saveflag == 1) {
            try {
                JOptionPane.showMessageDialog(null, filename + "File saved");
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Entropy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //create plot object
        Plot newplot = new Plot();
        newplot.doplot(sequence, xaxis, enresults, "Renyi Entropy results with alpha =" + String.valueOf(alpha), "Window Number", " Renyi Entropy H(x)", winsize, increament);
    }
}
