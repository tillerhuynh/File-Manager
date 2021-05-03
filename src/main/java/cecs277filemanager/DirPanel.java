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
    private JScrollPane scrollpane;
    private JTree dirtree;
    JTree tree;
    JPanel panel;
    DefaultTreeModel treemodel;
    
    public DirPanel(){
        
        this.scrollpane = new JScrollPane();

        dirtree = new JTree();
        scrollpane.setViewportView(dirtree);
        this.setLayout(new BorderLayout());
        this.add(scrollpane, BorderLayout.CENTER);
        add(scrollpane);
        
        dirtree.setPreferredSize(new Dimension(400, 400));
        loadTree();
    }
    
    public void loadTree(){
//        File drive = new File(FileFrame.driveSelected);
//        dirReader drives = new dirReader();
//        File[] f1 = drives.getCurrentDrives();
        File d = new File (FileFrame.driveSelected);
        treemodel = (DefaultTreeModel)dirtree.getModel();
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(d);
        
        treemodel.reload();
        treemodel = new DefaultTreeModel(root);
        treemodel.setRoot(root);
        
        // lists files and directories in this directory
        File[] f = d.listFiles();
        
        for (File file : f) {
            //FileNode fNode = new FileNode(file.toString());
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(d);
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
    }
    
    class SampleTreeSelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            System.out.println("You selected a node in the tree");
            System.out.println(tree.getMaxSelectionRow());
            System.out.println(tree.getSelectionPath()); //C:\\Node2\\Subnode0
            File file = new File("D:");
            System.out.println(file.getAbsolutePath());
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            FileNode fn = (FileNode) node.getUserObject();
            
            System.out.println(fn.toString());
        }
        
    }
}
    
//    public void 
//
//}
