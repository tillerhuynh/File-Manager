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
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
/**
 *
 * 
 */
public class DirPanel extends JPanel{
    JScrollPane scrollpane= new JScrollPane();
    JTree dirtree;
    JPanel panel;
    FilePanel filepanel;
    String path;
    
    public DirPanel(String curDrive, FileFrame filef){
        this.setLayout(new BorderLayout());
        this.add(scrollpane, BorderLayout.CENTER);
        add(scrollpane);
        loadTree(curDrive);
        scrollpane.setViewportView(dirtree);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) dirtree.getCellRenderer();
        renderer.setLeafIcon(renderer.getClosedIcon()); // icons for the folders
        dirtree.addTreeExpansionListener(treeExpandListener);
        
        dirtree.addTreeSelectionListener(new TreeSelectionListener(){
        
            @Override
            public void valueChanged(TreeSelectionEvent e) {
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
//                    dirtree.getLastSelectedPathComponent();
                StringBuilder node = new StringBuilder();
                TreePath treePath = e.getPath();
                Object[] paths = treePath.getPath();
                for (Object part : paths){
                    node.append(part).append("\\"); // adding the \ to the drive path
                }
                path = node.toString();
                filef.setTitle(path); // setting title of drive path
                filepanel.fillList(new File(path));
                filepanel.path = path;
            }
        });
    }   
        
      TreeExpansionListener treeExpandListener = new TreeExpansionListener() {
 
          @Override
          public void treeExpanded(TreeExpansionEvent event) {
                System.out.println(event.getPath());
                DefaultTreeModel model = (DefaultTreeModel) dirtree.getModel();
                TreePath path = event.getPath();
                File file;
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                if (!node.isRoot()) {
                    StringBuilder nodePath = new StringBuilder();
                    System.out.println("Expanded: " + nodePath);
                    TreePath treepath = event.getPath();
                    Object[] paths = treepath.getPath();
                    for (Object element : paths)
                        nodePath.append(element).append("\\");
                    file = new File(nodePath.toString());
                    addChild(file, node);
                    model.reload(node);
                }
//                String data = node.getUserObject().toString();
            }
            
          @Override
          public void treeCollapsed(TreeExpansionEvent event) {
                TreePath path = event.getPath();
                DefaultTreeModel model = (DefaultTreeModel) dirtree.getModel();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                System.out.println("Collapsed: " + node);
                if (!node.isRoot()) {
                    System.out.println("child removed");
                    node.removeAllChildren();
                    model.reload(node);
                }
            }
        };
    
        
    public void loadTree(String curDrive){
        dirtree = new JTree();
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(curDrive);
        DefaultTreeModel treemodel = new DefaultTreeModel(root,true);
        File rootfile = new File (curDrive);
        File[] files = rootfile.listFiles();
        for (File file : files) {
            if (file.isDirectory() && !(file.isHidden()) && (file.canRead()) &&
                    (!(file.toString().equals("C:\\Documents and Settings")))) {

                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                root.add(node);
            }
        }
            dirtree.setModel(treemodel);
    }
    
    public void addChild(File mainfile, DefaultMutableTreeNode node){
        File[] files = mainfile.listFiles();
        if (files == null){
            return;
        }
        
        for (File file: files){
            if (file.canRead() && !(file.isHidden()) && (file.isDirectory())){
                DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(file.getName());
                node.add(node2);
                System.out.println("Adding child: " + node2);
            }

        }
    }
   

    public void setFilePanel(FilePanel fpanel){
        filepanel = fpanel;
    }
    
    
    
//    class MyTreeSelectionListener implements TreeSelectionListener{
//                
//        @Override
//        public void valueChanged(TreeSelectionEvent e) {
//            System.out.println(e.getPath());
//            DefaultMutableTreeNode node = (DefaultMutableTreeNode) dirtree.getLastSelectedPathComponent();
//            System.out.println(node.toString());
//            if(node.toString().equals("test")){
//                filepanel.fillList(new File("B:\\BlackDesert"));
//            }
//            //check last node i selected is it empty?
//            //if there are children > get them
//            // add to node
//            // loop while there are still 
//            }
//        }
        
        
 
//    class SampleTreeSelectionListener implements TreeSelectionListener {
//
//        @Override
//        public void valueChanged(TreeSelectionEvent e) {
//            
//            System.out.println("You selected a node in the tree");
//            System.out.println(dirtree.getMaxSelectionRow());
//            System.out.println(dirtree.getSelectionPath()); //C:\\Node2\\Subnode0
//            File file = new File("D:");
//            System.out.println(file.getAbsolutePath());
//            
//            DefaultMutableTreeNode node = (DefaultMutableTreeNode) dirtree.getLastSelectedPathComponent();
//            FileNode fn = (FileNode) node.getUserObject();
//            
//            System.out.println(fn.toString());
//        }
//        
//    }
}
