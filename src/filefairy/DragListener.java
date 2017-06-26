/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filefairy;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author gabri
 */
public class DragListener implements DropTargetListener
{
    JLabel dragArea = new JLabel();
    
    public DragListener(JLabel area )
    {
        dragArea = area;        
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde)
    {
        dragArea.setBackground(new Color(39,43,54));
        dragArea.setForeground(new Color(153,153,153));
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde)
    {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde)
    {
    }

    @Override
    public void dragExit(DropTargetEvent dte)
    {
        dragArea.setBackground(new Color(153,153,153));
        dragArea.setForeground(new Color(0,0,0));
    }

    // Get dropped file and add to label
    @Override
    public void drop(DropTargetDropEvent ev)
    {
        dragArea.setBackground(new Color(153,153,153));
        dragArea.setForeground(new Color(0,0,0));
        // Accept drop items
        ev.acceptDrop(DnDConstants.ACTION_COPY);
       
        //We want the dropped items
        Transferable t = ev.getTransferable();
        
        //Get data formats of the items
        DataFlavor[] df = t.getTransferDataFlavors();
        
        //Loop through flavours
        for (DataFlavor d : df)
        {
            try
            {
                //Check if items are file type
                if (d.isFlavorJavaFileListType())
                {
                    //Get list of them
                    List<File> files = (List<File>) t.getTransferData(d);
                    
                    //Loop through them
                    for (File file : files)
                    {
                        dragArea.setText(file.getPath());
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
    } 
    
}
