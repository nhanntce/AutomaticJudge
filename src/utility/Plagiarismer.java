/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import judge.frmJudge;
import obj.Plagiarism;

/**
 *
 * @author NhanNT
 */
public class Plagiarismer {

    public static ArrayList<Plagiarism> plagiarism(String fileCheck, ArrayList<String> listCheck, String type, frmJudge parent) throws IOException {
        ArrayList<Plagiarism> res = new ArrayList<>();
        if ("py".equals(type)) {
            return res;
        }
        Runtime r = Runtime.getRuntime();
        Process process = null;
        BufferedReader br1 = null;
        int percentage = 0;
        String plagiarismPath = "";
        String cmdPercentage = "\"" + fileCheck + "." + type + "\" / ";
        String cmdSimilar = "\"" + fileCheck + "." + type + "\" / ";
        listCheck.remove(fileCheck + "." + type);
        String user = getUser(fileCheck);
        for (String s : listCheck) {
            if (s.contains(user)) {
                continue;
            }
            cmdPercentage += " \"" + s + "\"";
        }

        switch (type) {
            case "c":
                process = r.exec("sim_c.exe -p " + cmdPercentage);
                break;
            case "cpp":
                process = r.exec("\"sim_c++.exe\" -p " + cmdPercentage);
                break;
            case "java":
                process = r.exec("\"sim_java.exe\" -p " + cmdPercentage);
                break;
            default:
                break;
        }
        br1 = new BufferedReader(new InputStreamReader(process.getInputStream()));
        ArrayList<String> resultPercentage = new ArrayList<>();
        String s = "";
        while ((s = br1.readLine()) != null) {
            resultPercentage.add(s);
        }
        String result = resultPercentage.get(resultPercentage.size() - 1).trim();
        if (!result.equals("")) {
            try {
                BasicFileAttributes fAtt1 = Files.readAttributes(Paths.get(fileCheck + "." + type), BasicFileAttributes.class);
                BasicFileAttributes fAtt2 = Files.readAttributes(Paths.get(result.substring(result.indexOf(" % of ") + 6,
                        result.length() - 9)), BasicFileAttributes.class);
                if (fAtt1.creationTime().toMillis() < fAtt2.creationTime().toMillis()) {
                    return res;
                }
                plagiarismPath = result.substring(result.indexOf(" % of ") + 6, result.length() - 9);
                percentage = Integer.parseInt(result.substring(result.indexOf(" consists for ") + 14, result.indexOf(" % of ")));
                res.add(new Plagiarism(percentage, plagiarismPath));
            } catch (Exception e) {

            }
            if (percentage > 0) {
                cmdSimilar += "\"" + plagiarismPath + "\"";
                if ("c".equals(type)) {
                    process = r.exec("sim_c.exe -n " + cmdSimilar);
                } else if ("cpp".equals(type)) {
                    process = r.exec("\"sim_c++.exe\" -n " + cmdSimilar);
                } else if ("java".equals(type)) {
                    process = r.exec("\"sim_java.exe\" -n " + cmdSimilar);
                }
                br1 = new BufferedReader(new InputStreamReader(process.getInputStream()));
                ArrayList<String> resultSimilar = new ArrayList<>();
                String sSemilar = "";
                while ((sSemilar = br1.readLine()) != null) {
                    resultSimilar.add(sSemilar);
                }
                for (int i = 5; i < resultSimilar.size(); i += 2) {
                    String fullStr = resultSimilar.get(i);
                    String lineOriginal = fullStr.substring(fullStr.indexOf(": line ") + 7,
                            fullStr.indexOf("|")).trim().replace("-", " ");
                    String lineSimilar = fullStr.substring(fullStr.lastIndexOf(": line ") + 7,
                            fullStr.lastIndexOf("[")).trim().replace("-", " ");
                    res.add(new Plagiarism(lineOriginal, lineSimilar));
                }
            }
        }

        return res;
    }

    /**
     * Get username
     *
     * @param s get Student code in file's source code name
     * @return student code
     */
    public static String getUser(String s) {
        try {
            String pattern = "\\]\\[";
            String[] lsuser = s.split(pattern);
            String user = lsuser[lsuser.length - 2];
            return user;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return "";
    }
}
