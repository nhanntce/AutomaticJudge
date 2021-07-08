/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 *
 * @author NhanNT
 */
public class Plagiarism {
    int percentageOfPlagiarism;
    String origianal;
    String compareWith;
    String nameOfFileHasPlagiarism;

    public Plagiarism(String origianal, String compareWith) {
        this.origianal = origianal;
        this.compareWith = compareWith;
    }

    public Plagiarism(int percentageOfPlagiarism, String nameOfFileHasPlagiarism) {
        this.percentageOfPlagiarism = percentageOfPlagiarism;
        this.nameOfFileHasPlagiarism = nameOfFileHasPlagiarism;
    }
    
    public Plagiarism(int percentageOfPlagiarism, String origianal, String compareWith) {
        this.percentageOfPlagiarism = percentageOfPlagiarism;
        this.origianal = origianal;
        this.compareWith = compareWith;
    }

    public Plagiarism(int percentageOfPlagiarism, String origianal, String compareWith, String nameOfFileHasPlagiarism) {
        this.percentageOfPlagiarism = percentageOfPlagiarism;
        this.origianal = origianal;
        this.compareWith = compareWith;
        this.nameOfFileHasPlagiarism = nameOfFileHasPlagiarism;
    }
    
    public String getOrigianal() {
        return origianal;
    }

    public void setOrigianal(String origianal) {
        this.origianal = origianal;
    }

    public String getCompareWith() {
        return compareWith;
    }

    public void setCompareWith(String compareWith) {
        this.compareWith = compareWith;
    }

    public int getPercentageOfPlagiarism() {
        return percentageOfPlagiarism;
    }

    public void setPercentageOfPlagiarism(int percentageOfPlagiarism) {
        this.percentageOfPlagiarism = percentageOfPlagiarism;
    }

    public String getNameOfFileHasPlagiarism() {
        return nameOfFileHasPlagiarism;
    }

    public void setNameOfFileHasPlagiarism(String nameOfFileHasPlagiarism) {
        this.nameOfFileHasPlagiarism = nameOfFileHasPlagiarism;
    }
}
