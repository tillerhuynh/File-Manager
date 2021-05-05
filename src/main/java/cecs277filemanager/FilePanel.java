/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cecs277filemanager;


import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;


/**
 *
 * @author Dr. Hoffman
 */

public class FilePanel extends JPanel {
    JList list = new JList();
    DefaultListModel model = new DefaultListModel();
    boolean display;
    JList list2 = new JList();
    DefaultListModel model2 = new DefaultListModel();
    Path currentDirectory;
    
    public FilePanel(FileFrame filef){
        list.setPreferredSize(new Dimension(500,500));
        this.setDropTarget(new MyDropTarget());
        list.setDragEnabled(true);
        list.setModel(model);
        add(list);
    }
    
    public void fillList(File dir, boolean display) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        File[] files;
        String details;
        this.display = display;
        int counter = 0;

        files = dir.listFiles();
        model.clear();
        model2.clear();
        list.removeAll();
        list2.removeAll();
        for(int i = 0; i < files.length; i++){
            if (!files[i].isHidden() ){ //&& files[i].isDirectory()
                if (display) {
                    // two variables.
                    String test = files[i].getName();
                    details = String.format("%-50s %10s %20s", test,
                            sdf.format(files[i].lastModified()), files[i].length() + "B");
                    model.addElement(details);
                    model2.addElement(test);
                }
                else{
                    model.addElement(files[i].getName());
                    model2.addElement(files[i].getName());
                }
            if(counter == 0){
                this.currentDirectory = Paths.get(files[i].getParent());
                counter++;
            }
            }
        }
        list.setModel(model);
        list2.setModel(model2);

    }
    /*************************************************************************
     * class MyDropTarget handles the dropping of files onto its owner
     * (whatever JList it is assigned to). As written, it can process two
     * types: strings and files (String, File). The String type is necessary
     * to process internal source drops from another FilePanel object. The
     * File type is necessary to process drops from external sources such 
     * as Windows Explorer or IOS.
     * 
     * Note: no code is provided that actually copies files to the target
     * directory. Also, you may need to adjust this code if your list model
     * is not the default model. JList assumes a toString operation is
     * defined for whatever class is used.
     */
    class MyDropTarget extends DropTarget {
    /**************************************************************************
     * 
     * @param evt the event that caused this drop operation to be invoked
     */    
        public void drop(DropTargetDropEvent evt){
            try {
                //types of events accepted
                evt.acceptDrop(DnDConstants.ACTION_COPY);
                //storage to hold the drop data for processing
                List result = new ArrayList();
                //what is being dropped? First, Strings are processed
                if(evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)){     
                    String temp = (String)evt.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    //String events are concatenated if more than one list item 
                    //selected in the source. The strings are separated by
                    //newline characters. Use split to break the string into
                    //individual file names and store in String[]
                    String[] next = temp.split("\\n");
                    //add the strings to the listmodel
                    for(int i=0; i<next.length;i++)
                        model.addElement(next[i]); 
                }
                else{ //then if not String, Files are assumed
                    result =(List)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    //process the input
                    for(Object o : result){
                        System.out.println(o.toString());
                        model.addElement(o.toString());
                    }
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
    }

}
