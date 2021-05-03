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
    
    public GUI(){
        panel = new JPanel();
        statusbar = new JMenuBar();
        toolBar = new JMenuBar();
        desktopPane = new JDesktopPane();
        panel.setLayout(new BorderLayout());
        drivereader = new dirReader();
        
    }
    
    public void go(){
        MenuBuilder menuBuild = new MenuBuilder();
        
//        statBar status = new statBar();
        this.setJMenuBar(menuBuild.buildMenu());
        buildStatusBar();
        buildtoolBar();
        
        panel.add(desktopPane,BorderLayout.CENTER);
        
        //creates the frame inside will later need to add an option in the drop down menu to create new panes each time
        FileFrame ff = new FileFrame();
        desktopPane.add(ff);
        
        this.add(panel);
        this.setTitle("CECS 277 File Manager");
        this.setSize(900,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

     
  
    }
  
    public void buildStatusBar(){
        JLabel size = new JLabel("Size in GB:");
        statusbar.add(size);
        panel.add(statusbar, BorderLayout.SOUTH);
        //TODO update the status bar
    }
    

    public void buildtoolBar(){

        // the buttons for the toolbar
        JButton detailsButton = new JButton("Details");
        JButton simpleButton = new JButton("Simple");
        toolBar.add(detailsButton);
        toolBar.add(simpleButton);
        
        JComboBox comboBox = new JComboBox();
        File[] drive = drivereader.getCurrentDrives();
        for (File files : drive){
            comboBox.addItem(files.toString() + drivereader.getDriveNames(files));
        }
        comboBox.addActionListener(new ComboBoxActionListener());
        
        toolBar.add(comboBox);
        
        toolBar.setLayout(new GridBagLayout());
        panel.add(toolBar, BorderLayout.NORTH);
        /*TODO LIST
            implement details
                details should redraw the right scrollpane with file sizes and dates
            implement simple
                simples should redraw the right scrollpane without file sizes and dates
        */
    }
    
    public class ComboBoxActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            String driveName = (String)cb.getSelectedItem();
            
       
        }
       
    }
    
   
       
}
