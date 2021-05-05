/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs277filemanager;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
/**
 *
 * @author glitc
 */
public class dirReader {
    // initializing the file object
    public File[] files;
    public double resetDir;
    public double totalSpace = 0;
    public double usableSpace = 0;
    public double freeSpace = 0;
    
//    File currentDrive = new File(FileFrame.driveSelected);
    
    public void setDirect(String dir){
        File file = new File(dir);
        files = file.listFiles();
        
        totalSpace = 0;
        usableSpace = 0;
        freeSpace = 0;
        resetDir = 0;
        
        // getting the total space for the status bar
        totalSpace = file.getTotalSpace();
        freeSpace = file.getFreeSpace();
        usableSpace = totalSpace - freeSpace;
    }
    
    public double getUsableSpace(){
        return (usableSpace / (1024 * 1024 * 1024));
    }
    
    public double getFreeSpace(){
        return (freeSpace / (1024 * 1024 * 1024));
    }
    
    public double getTotalSpace(){
        return (totalSpace / (1024 * 1024 * 1024));
    }
    
    
    public File[] getCurrentDrives() {

        File[] files;
        files = File.listRoots(); //Lists all drives on the computer
        
        return files;
        
    }
    
    public String[] fileLister(String dir){
        File file = new File(dir);
        ArrayList<String> directory = new ArrayList<>();
        SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
        
        for (int i = 0; i < files.length; i++) {
//            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            if (!files[i].isHidden() && files[i].isDirectory()) {
                directory.add(files[i].toString());
                
            }
        }
        String[] convert = directory.toArray(new String [directory.size()]);
        
        return convert;
    }
    
    
    
    public String getDriveNames(File drive){
        return FileSystemView.getFileSystemView().getSystemDisplayName(drive);

    }
    
}
