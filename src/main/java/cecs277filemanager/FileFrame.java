/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs277filemanager;

import java.io.File;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

/**
 *
 * @author glitc
 */
public class FileFrame extends JInternalFrame{
    DirPanel dPanel;
    FilePanel fPanel;
    JSplitPane splitpane;

    
    public FileFrame(String string){
        
        dPanel = new DirPanel(string, this);
        dPanel.setSize(250,500);
        dPanel.setFilePanel(fPanel);
        fPanel = new FilePanel();
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dPanel, fPanel);
        splitpane.setDividerLocation(250);
        
        this.setTitle(string); // title
        this.getContentPane().add(splitpane);
        this.setClosable(true); // the exit button for the drive window
        this.setMaximizable(true); // minimize / max button for drive window
        this.setIconifiable(true); // when you minimize it, it becomes an icon
        this.setSize(600, 400); // the size of the drive window
        this.setVisible(true); // the drive window being visible    
        this.setResizable(true);
        
    }
    
    
}
