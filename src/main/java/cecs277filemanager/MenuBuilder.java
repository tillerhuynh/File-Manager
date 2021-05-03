/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs277filemanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author glitc
 */
public class MenuBuilder extends GUI{
    
    
    public MenuBuilder(){
        menubar = new JMenuBar();
    }
    
    public JMenuBar buildMenu(){
        // this creates the top bar 
        JMenu fileMenu,helpMenu, windowMenu, treeMenu;
        
        /* drop down menus items */ 
        
        // File Menu Item
        fileMenu = new JMenu("File");
        JMenuItem rename = new JMenuItem("Rename");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem run = new JMenuItem("Run");
        JMenuItem exit = new JMenuItem("Exit");
        
        fileMenu.add(rename);
        fileMenu.add(copy);
        fileMenu.add(delete);
        fileMenu.add(run);
        fileMenu.add(exit);
        
        exit.addActionListener(new ExitActionListener());
        menubar.add(fileMenu);
        //TODO implement file menu items
        
        // Tree Menu Items
        treeMenu = new JMenu("Tree");
        JMenuItem expandBr = new JMenuItem("Expand Branch");
        JMenuItem collapseBr = new JMenuItem("Collapse Branch");
        treeMenu.add(expandBr);
        treeMenu.add(collapseBr);
        menubar.add(treeMenu);
        //TODO implement TREE menu items
        
        // Window Menu Items
        windowMenu = new JMenu("Window");
        JMenuItem newWindow = new JMenuItem("New");
        JMenuItem cascade = new JMenuItem("Cascade");
        windowMenu.add(newWindow);
        newWindow.addActionListener(new newWindowActionListener());
        windowMenu.add(cascade);
        cascade.addActionListener(new cascadeActionListener());
        menubar.add(windowMenu);
        //TODO implement window menu items
        
        // Help Menu Items
        helpMenu = new JMenu("Help");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem about = new JMenuItem("About");
        helpMenu.add(help);
        helpMenu.add(about);
        about.addActionListener(new AboutActionListener());
        menubar.add(helpMenu);
        //TODO implement help menu items
        

        // the location of the menubar
        this.setJMenuBar(menubar);
        
        return menubar;
        
    }
    private class ExitActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private static class cascadeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
//            f.pack();
//            f.setLocationByPlatform(true);
        }
    }

    private class AboutActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AboutDig dig = new AboutDig(null, true);
            dig.setVisible(true);
        }
    }

    public class newWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileFrame ff = new FileFrame();
            ff.setLocation(0,100);
            desktopPane.add(ff);
            
            /*  TODO
            New creates a new internal frame DONE
            places it at location 0,100 within the desktop pane. 
            It defaults to C:. Any number of new frames can be created.
            */ 
            
        }
    } 
}
