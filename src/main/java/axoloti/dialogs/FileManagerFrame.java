/**
 * Copyright (C) 2013, 2014, 2015 Johannes Taelman
 *
 * This file is part of Axoloti.
 *
 * Axoloti is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Axoloti is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Axoloti. If not, see <http://www.gnu.org/licenses/>.
 */
package axoloti.dialogs;

import axoloti.ConnectionStatusListener;
import axoloti.MainFrame;
import static axoloti.MainFrame.prefs;
import axoloti.SDCardInfo;
import axoloti.USBBulkConnection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.AbstractTableModel;
import qcmds.QCmdProcessor;
import qcmds.QCmdUploadFile;

/**
 *
 * @author Johannes Taelman
 */
public class FileManagerFrame extends javax.swing.JFrame implements ConnectionStatusListener {

    /**
     * Creates new form FileManagerFrame
     */
    public FileManagerFrame() {
        initComponents();
        USBBulkConnection.GetConnection().addConnectionStatusListener(this);
        setIconImage(new ImageIcon(getClass().getResource("/resources/axoloti_icon.png")).getImage());
        jLabelSDInfo.setText("");

        jFileTable.setModel(new AbstractTableModel() {
            private String[] columnNames = {"Name", "Type", "Size", "Date"};

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int column) {
                return columnNames[column];
            }

            @Override
            public Class getColumnClass(int column) {
                return String.class;
            }

            @Override
            public int getRowCount() {
                return SDCardInfo.getInstance().getFiles().size();
            }

            @Override
            public void setValueAt(Object value, int rowIndex, int columnIndex) {
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Object returnValue = null;

                switch (columnIndex) {
                    case 0: {
                        String fname = SDCardInfo.getInstance().getFiles().get(rowIndex).getFilename();
                        int i = fname.lastIndexOf('.');
                        if (i > 0) {
                            returnValue = fname.substring(0, i);
                        } else {
                            returnValue = fname;
                        }
                    }
                    break;
                    case 1: {
                        String fname = SDCardInfo.getInstance().getFiles().get(rowIndex).getFilename();
                        int i = fname.lastIndexOf('.');
                        if (i > 0) {
                            returnValue = fname.substring(i + 1);
                        } else {
                            returnValue = fname;
                        }
                    }
                    break;
                    case 2: {
                        String s;
                        int size = SDCardInfo.getInstance().getFiles().get(rowIndex).getSize();
                        if (size < 10240) {
                            s = "" + size + "  bytes";
                        } else if (size < 10240 * 1024) {
                            s = "" + (size / 1024) + " kB";
                        } else {
                            s = "" + (size / (1024 * 1024)) + " MB";
                        }
                        returnValue = s;
                    }
                    break;
                    case 3: {
                        returnValue = SDCardInfo.getInstance().getFiles().get(rowIndex).getTimestamp().getTime().toString();
                    }
                    break;
                }

                return returnValue;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex == 1);
            }

        });

        jFileTable.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    QCmdProcessor processor = MainFrame.mainframe.getQcmdprocessor();
                    if (USBBulkConnection.GetConnection().isConnected()) {
                        for (File f : droppedFiles) {
                            System.out.println(f.getName());
                            if (!f.canRead()) {
                                Logger.getLogger(FileManagerFrame.class.getName()).log(Level.SEVERE, "Can't read file");
                            } else {
                                processor.AppendToQueue(new QCmdUploadFile(f, f.getName()));
                            }
                        }
                        RequestRefresh();
                    }
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(FileManagerFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FileManagerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jFileTable = new javax.swing.JTable();
        jButton1Refresh = new javax.swing.JButton();
        jLabelSDInfo = new javax.swing.JLabel();
        jButtonUpload = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        windowMenu1 = new axoloti.menus.WindowMenu();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jFileTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Size", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jFileTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jFileTable);
        if (jFileTable.getColumnModel().getColumnCount() > 0) {
            jFileTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        jButton1Refresh.setText("Refresh");
        jButton1Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1RefreshActionPerformed(evt);
            }
        });

        jLabelSDInfo.setText("jLabel1");

        jButtonUpload.setText("Upload...");
        jButtonUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(windowMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1Refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUpload)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addComponent(jLabelSDInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSDInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void RequestRefresh() {
        if (USBBulkConnection.GetConnection().isConnected()) {
            USBBulkConnection.GetConnection().TransmitStop();
            USBBulkConnection.GetConnection().TransmitGetFileList();
        }
    }

    private void jButton1RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1RefreshActionPerformed
        RequestRefresh();
    }//GEN-LAST:event_jButton1RefreshActionPerformed

    private void jButtonUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadActionPerformed
        QCmdProcessor processor = MainFrame.mainframe.getQcmdprocessor();
        if (USBBulkConnection.GetConnection().isConnected()) {
            final JFileChooser fc = new JFileChooser(prefs.getCurrentFileDirectory());
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                prefs.setCurrentFileDirectory(fc.getCurrentDirectory().getPath());
                File f = fc.getSelectedFile();
                if (f != null) {
                    if (!f.canRead()) {
                        Logger.getLogger(FileManagerFrame.class.getName()).log(Level.SEVERE, "Can't read file");
                        return;
                    }
                    processor.AppendToQueue(new QCmdUploadFile(f, f.getName()));
                }
            }
        }
    }//GEN-LAST:event_jButtonUploadActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // RequestRefresh();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        USBBulkConnection.GetConnection().removeConnectionStatusListener(this);
    }//GEN-LAST:event_formWindowClosing

    public void refresh() {
        int clusters = SDCardInfo.getInstance().getClusters();
        int clustersize = SDCardInfo.getInstance().getClustersize();
        int sectorsize = SDCardInfo.getInstance().getSectorsize();
        jLabelSDInfo.setText("Free : " + ((long) clusters * (long) clustersize * (long) sectorsize / (1024 * 1024)) + "MB, Cluster size = " + clustersize * sectorsize);
        jFileTable.revalidate();
        jFileTable.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1Refresh;
    private javax.swing.JButton jButtonUpload;
    private javax.swing.JTable jFileTable;
    private javax.swing.JLabel jLabelSDInfo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private axoloti.menus.WindowMenu windowMenu1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ShowConnect() {
        jButton1Refresh.setEnabled(true);
        jButtonUpload.setEnabled(true);
        jFileTable.setEnabled(true);
        jLabelSDInfo.setText("");
    }

    @Override
    public void ShowDisconnect() {
        jButton1Refresh.setEnabled(false);
        jButtonUpload.setEnabled(false);
        jFileTable.setEnabled(false);
        jLabelSDInfo.setText("");
    }
}
