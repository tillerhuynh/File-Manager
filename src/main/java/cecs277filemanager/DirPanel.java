/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs277filemanager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author apocryphon
 */
public class DirPanel extends JPanel{
    JScrollPane scrollpane;
    JTree dirtree;
    JPanel panel;
    DefaultTreeModel treemodel;
    FilePanel f;
    
    public DirPanel(){
        scrollpane = new JScrollPane();
        dirtree = new JTree();
        dirtree.addTreeSelectionListener(new MyTreeSelectionListener());
        this.setLayout(new BorderLayout());
        this.add(scrollpane, BorderLayout.CENTER);
        scrollpane.setViewportView(dirtree);
        add(scrollpane);
//        dirtree.setPreferredSize(new Dimension(400, 400));
        loadTree();
    }
    
    public void loadTree(){
        

        File rootfile = new File (FileFrame.driveSelected);
        treemodel = (DefaultTreeModel)dirtree.getModel();
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootfile);
        
        treemodel.reload();
        treemodel = new DefaultTreeModel(root);
        treemodel.setRoot(root);
        
        // lists files and directories in this directory
        File[] subfile = rootfile.listFiles();
        
        for (File file : subfile) {
            FileNode fNode = new FileNode(file.toString());
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fNode);
            root.add(node);

            File directory = new File(file.toString());
            if (directory.isDirectory() && directory.listFiles() != null) {
                File[] subfolder = directory.listFiles();
                for(File file2 : subfolder){
                    FileNode fNode2 = new FileNode(file2.toString());
                    DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(fNode2);
                    node.add(node2);
                }
            }
        }
        dirtree.setModel(treemodel);
    }
    
    public void setFilePanel(FilePanel fpanel){
        f = fpanel;
    }
    
    class MyTreeSelectionListener implements TreeSelectionListener{

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            System.out.println(e.getPath());
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
                    dirtree.getLastSelectedPathComponent();
            System.out.println(node.toString());
            if(node.toString().equals("PerfLogs")){
                f.fillList(new File (FileFrame.driveSelected));
            }
        }
        
        
    }
    class SampleTreeSelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            
            System.out.println("You selected a node in the tree");
            System.out.println(dirtree.getMaxSelectionRow());
            System.out.println(dirtree.getSelectionPath()); //C:\\Node2\\Subnode0
            File file = new File("D:");
            System.out.println(file.getAbsolutePath());
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) dirtree.getLastSelectedPathComponent();
            FileNode fn = (FileNode) node.getUserObject();
            
            System.out.println(fn.toString());
        }
        
    }
}
