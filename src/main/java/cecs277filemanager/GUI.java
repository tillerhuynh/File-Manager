/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs277filemanager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Dimension;

/**
 *
 * @author glitc
 */
class GUI extends JFrame{
    JPanel panel;
    JComboBox drives;
    JButton details, simple;
    JMenuBar menubar, statusbar, toolBar;
    JScrollPane scrollpane;
    JDesktopPane desktopPane;
    dirReader drivereader;
    JTree tree;
    static String getDrive = "C:\\";
    
    public GUI(){
        panel = new JPanel();//        drivePanel = new DirPanel();
        statusbar = new JMenuBar();
        toolBar = new JMenuBar();
        desktopPane = new JDesktopPane();
        panel.setLayout(new BorderLayout());
        drivereader = new dirReader();
        tree = new JTree();
    }
    
    public void go(){
        // calls menubuilder to run the menubuilder method 
        MenuBuilder menuBuild = new MenuBuilder();
        this.setJMenuBar(menuBuild.buildMenu());
        
//        DirPanel drivePanel = new DirPanel();
        buildtoolBar();
        buildStatusBar();
        panel.add(desktopPane,BorderLayout.CENTER);
        
//        panel.add(tree);
//        this.add(panel);
//        drivePanel.loadTree();
        
        //creates the frame inside will later need to add an option in the drop down menu to create new panes each time
        FileFrame ff = new FileFrame();
        desktopPane.add(ff);
        
        this.add(panel);
        this.setTitle("CECS 277 File Manager");
        this.setSize(900,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tree.setPreferredSize(new Dimension(690,499));
        this.setVisible(true);
        
     
  
    }
    
    public void buildStatusBar(){
        // initializing the file object
        File file = new File(FileFrame.driveSelected);
        
        // getting the total space
        Long totalSpace = file.getTotalSpace();
        Long freeSpace = file.getFreeSpace();
        Long usableSpace = totalSpace - freeSpace;
        
        //creating labels for the status bar
        JLabel curDrive = new JLabel("Current Drive: " + FileFrame.driveSelected);
        JLabel curSize = new JLabel("     Free Space: " + freeSpace/1000000000 + " GBs");
        JLabel usedSize = new JLabel("     Used Space: " + usableSpace/1000000000 +" GBs");
        JLabel totalSize = new JLabel("     Total Space: " + totalSpace/1000000000 +" GBs");
        
        
        statusbar.add(curDrive);
        statusbar.add(curSize);
        statusbar.add(usedSize);
        statusbar.add(totalSize);
        panel.add(statusbar,BorderLayout.SOUTH);
        statusbar.repaint();
        statusbar.validate();
     }
    

    public void buildtoolBar(){

        // the buttons for the toolbar
        JButton detailsButton = new JButton("Details");
        JButton simpleButton = new JButton("Simple");
        toolBar.add(detailsButton);
        toolBar.add(simpleButton);
        
        // the dropdown box for the toolbar
        JComboBox comboBox = new JComboBox();
        File[] drive = drivereader.getCurrentDrives();
        for (File files : drive){
            comboBox.addItem(files.toString() + drivereader.getDriveNames(files));
        }
        ActionListener actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               JComboBox cb = (JComboBox)e.getSource();
               String driveName = (String)cb.getSelectedItem();
               // need to put drive name into fileframe.driveselected
               getDrive = driveName;
               
               panel.repaint();
               panel.validate();
               System.out.println(getDrive);
            }
        };
        
        comboBox.addActionListener(actionListener);
        toolBar.add(comboBox);
        toolBar.repaint();
        toolBar.validate();
        
        toolBar.setLayout(new GridBagLayout());
        panel.add(toolBar, BorderLayout.NORTH);
        /*TODO LIST
            implement details
                details should redraw the right scrollpane with file sizes and dates
            implement simple
                simples should redraw the right scrollpane without file sizes and dates
        */
       
    }
    
//    public class ComboBoxActionListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JComboBox cb = (JComboBox)e.getSource();
//            String driveName = (String)cb.getSelectedItem();
//            driveSelected = driveName;
//            
//        }
//       
//    }
    // do something with drive name
    // check how to use filenode 
    // make combobox repaint itself
   
       
}
