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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

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
    FileFrame filef;
    JDesktopPane desktopPane;
    JTree tree;
    String currentDrive;
    
    public GUI(){
        panel = new JPanel();
        statusbar = new JMenuBar();
        menubar = new JMenuBar();
        toolBar = new JMenuBar();
        desktopPane = new JDesktopPane();
        panel.setLayout(new BorderLayout());
//        drivereader = new dirReader();
        tree = new JTree();
    }
    
    public void go(){
        JComboBox combo = new JComboBox();
        currentDrive = "C:\\";
        // calls menubuilder to run the menubuilder method 
        this.setJMenuBar(buildMenu());
        
        //creates statusbar
        
        buildStatusBar(currentDrive);
        
        //creates toolbar
        buildtoolBar(currentDrive, combo);
        
        panel.add(desktopPane,BorderLayout.CENTER);

        //creates the frame inside will later need to add an option in the drop down menu to create new panes each time
        filef = new FileFrame(currentDrive);
        desktopPane.add(filef);
        
        //adding panel to frame
        this.add(panel);
        // setting frame title
        this.setTitle("CECS 277 File Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tree.setPreferredSize(new Dimension(690,499));
        this.pack();
        this.setSize(900,900);
        this.setVisible(true);
  
    }
    
    public JMenuBar buildMenu(){
        // this creates the top bar 
        JMenu fileMenu,helpMenu, windowMenu, treeMenu;
        fileMenu = new JMenu("File");
        treeMenu = new JMenu("Tree");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");
        
        // File Menu Item
        JMenuItem rename = new JMenuItem("Rename");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem run = new JMenuItem("Run");
        JMenuItem exit = new JMenuItem("Exit");
        
        // adding File Menu Items
        fileMenu.add(rename);
        fileMenu.add(copy);
        fileMenu.add(delete);
        fileMenu.add(run);
        fileMenu.add(exit);
        
        //File Menu Action Listeners
//        rename.addActionListener(new RenameActionListener());
//        copy.addActionListener(new CopyActionListener());
//        delete.addActionListener(new DeleteActionListener());
//        run.addActionListener(new RunActionListener());
        exit.addActionListener(new ExitActionListener());
 
        
        // Tree Menu Items
        JMenuItem expandBr = new JMenuItem("Expand Branch");
        JMenuItem collapseBr = new JMenuItem("Collapse Branch");
        
        // adding Tree Menu Items
        treeMenu.add(expandBr);
        treeMenu.add(collapseBr);
        
        // Tree Menu Action Listeners
//        expandBr.addActionListener(new ActionListener());
//        collapseBr.addActionListener(new ActionListener());
        

        
        // Window Menu Items
        
        JMenuItem newWindow = new JMenuItem("New");
        JMenuItem cascade = new JMenuItem("Cascade");
        
        windowMenu.add(newWindow);
        windowMenu.add(cascade);
        
        // Window Menu Action Listeners
        newWindow.addActionListener(new newWindowActionListener());
        cascade.addActionListener(new cascadeActionListener());

        
        // Help Menu Items
        
        JMenuItem help = new JMenuItem("Help");
        JMenuItem about = new JMenuItem("About");
        helpMenu.add(help);
        helpMenu.add(about);
        
        // help menu action listeners
        about.addActionListener(new AboutActionListener());
        

        
        // adds the menubar choices
        menubar.add(fileMenu);
        menubar.add(treeMenu);
        menubar.add(windowMenu);
        menubar.add(helpMenu);
        
        // adds menubar to the frame
        this.setJMenuBar(menubar);
        return menubar;
        
    }

            
    public void buildStatusBar(String dir){
        statusbar.removeAll();
        dirReader dirRead = new dirReader();
        
        
        //creating labels for the status bar
        JLabel curDrive = new JLabel("Current Drive: " + dir);
        JLabel curSize = new JLabel("     Free Space: " + dirRead.getFreeSpace() + " GBs");
        JLabel usedSize = new JLabel("     Used Space: " + dirRead.getUsableSpace()+" GBs");
        JLabel totalSize = new JLabel("     Total Space: " + dirRead.getTotalSpace() +" GBs");
        
        //adding labels to status bar
        statusbar.add(curDrive);
        statusbar.add(curSize);
        statusbar.add(usedSize);
        statusbar.add(totalSize);
        
        
        panel.add(statusbar,BorderLayout.SOUTH);
        statusbar.repaint();
        statusbar.validate();
        
     }
    

    public void buildtoolBar(String drive, JComboBox comboBox){
        dirReader dirRead = new dirReader();
        
        // the buttons for the toolbar
        JButton detailsButton = new JButton("Details");
        JButton simpleButton = new JButton("Simple");
       
        
        // the dropdown box for the toolbar
        File[] drives = dirRead.getCurrentDrives();
        comboBox = new JComboBox(drives);
        JComboBox cb = comboBox;
        
        
        ActionListener actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
               String driveName = (String)cb.getSelectedItem().toString() ;
               dirRead.setDirect(driveName);
               buildStatusBar(driveName);
               currentDrive = driveName;

               panel.repaint();
               
               System.out.println(currentDrive);
            }
        };
        
        comboBox.addActionListener(actionListener);
        toolBar.add(comboBox);
        toolBar.add(detailsButton);
        toolBar.add(simpleButton);
        toolBar.repaint();
        toolBar.revalidate();
        
        toolBar.setLayout(new GridBagLayout());
        panel.add(toolBar, BorderLayout.NORTH);
        /*TODO LIST
            implement details
                details should redraw the right scrollpane with file sizes and dates
            implement simple
                simples should redraw the right scrollpane without file sizes and dates
        */
       
    }

    public class ExitActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    public class cascadeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }

    public class AboutActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AboutDig dig = new AboutDig(null, true);
            dig.setVisible(true);
        }
    }

    public class newWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileFrame ff = new FileFrame(currentDrive);
            desktopPane.add(ff);
            
            ff.addInternalFrameListener(new InternalFrameAdapter(){
                public void internalFrame(InternalFrameEvent e){
                    String str = ff.getTitle();
                    str = str.substring(0,3);
                    buildStatusBar(str);
                }
            });
        }
    }   
        
    /*  TODO
            New creates a new internal frame DONE
            places it at location 0,100 within the desktop pane. 
            It defaults to C:. Any number of new frames can be created.
            */ 
    // do something with drive name
    // check how to use filenode 
    // make combobox repaint itself
   
       
}
