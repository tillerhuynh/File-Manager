/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs277filemanager;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author glitc
 */
public class dirReader {

    File currentDrive = new File(FileFrame.driveSelected);
    
    
    public static void main(String[] args){
        File file = new File(FileFrame.driveSelected);
        File[] files;
        files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            if (files[i].isDirectory()) {
                //System.out.println("Directory: " + file1.getAbsolutePath() + " Date:" + dateFormat.format(file1.lastModified()) + " Size:" + decimalFormat.format(file1.length()));
                File[] directories;
                directories = files[i].listFiles();
                
            }
            
//            else
//                System.out.println("Files: " + file1.getAbsolutePath() 
//                        + " Date:" + dateFormat.format(file1.lastModified()) 
//                        + " Size:" + decimalFormat.format(file1.length()));
        }
    }
    
    
}
