/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package axoloti.objecteditor;

import axoloti.MainFrame;
import static axoloti.MainFrame.axoObjects;
import axoloti.object.AxoObject;
import axoloti.utils.AxolotiLibrary;
import generatedobjects.gentools;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kodiak
 */
public class AddToLibraryDlg extends javax.swing.JDialog {

    private final AxoObject obj_;

    public AddToLibraryDlg(AxoObjectEditor parent, boolean modal, AxoObject obj) {
        super(parent, modal);
        initComponents();
        obj_ = obj;
        populateFields();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jObjectName = new javax.swing.JTextField();
        jPath = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLibrary = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jObjectNameTxt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jOK = new javax.swing.JButton();
        jCancel = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jFileTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name");

        jObjectName.setText("jTextField1");
        jObjectName.setPreferredSize(new java.awt.Dimension(120, 28));
        jObjectName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jObjectNameFocusLost(evt);
            }
        });

        jPath.setText("jTextField1");
        jPath.setPreferredSize(new java.awt.Dimension(150, 28));
        jPath.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPathFocusLost(evt);
            }
        });

        jLabel2.setText("Path");

        jLabel3.setText("Library");

        jLibrary.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLibraryFocusLost(evt);
            }
        });

        jLabel6.setText("Full name");

        jObjectNameTxt.setText("object name");

        jLabel9.setText("File");

        jLabel4.setText("Information");

        jOK.setText("OK");
        jOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOKActionPerformed(evt);
            }
        });

        jCancel.setText("Cancel");
        jCancel.setDefaultCapable(false);
        jCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelActionPerformed(evt);
            }
        });

        jFileTxt.setEditable(false);
        jFileTxt.setText("jTextField1");
        jFileTxt.setEnabled(false);
        jFileTxt.setFocusTraversalKeysEnabled(false);
        jFileTxt.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jObjectNameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFileTxt)
                                .addGap(14, 14, 14))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 124, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLibrary, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jObjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(344, Short.MAX_VALUE)
                .addComponent(jCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jOK, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jPath, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(266, Short.MAX_VALUE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(266, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLibrary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jObjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jObjectNameTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCancel)
                    .addComponent(jOK))
                .addGap(15, 15, 15))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(134, Short.MAX_VALUE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(135, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOKActionPerformed
        modifiedData();

        AxoObject obj;
        try {
            obj = obj_.clone();
            obj.shortId = jObjectName.getText();
            obj.id = jObjectName.getText();
            obj.setSHA(obj.GenerateSHA());
            obj.id = jPath.getText() + "/" + jObjectName.getText();
            obj.sPath = jFileTxt.getText();
            obj.setUUID(obj.GenerateUUID());
            obj.addUpgradeSHA(null);
            File f = new File(obj.sPath);
            if (!f.exists()) {
                File dir = f.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
            }
            gentools.WriteAxoObject(obj.sPath, obj);
            axoObjects.LoadAxoObjects();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AddToLibraryDlg.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(false);
        dispose();
    }//GEN-LAST:event_jOKActionPerformed

    private void jCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jCancelActionPerformed

    private void jPathFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPathFocusLost
        modifiedData();
    }//GEN-LAST:event_jPathFocusLost

    private void jObjectNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jObjectNameFocusLost
        modifiedData();
    }//GEN-LAST:event_jObjectNameFocusLost

    private void jLibraryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLibraryFocusLost
        modifiedData();
    }//GEN-LAST:event_jLibraryFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jCancel;
    private javax.swing.JTextField jFileTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox jLibrary;
    private javax.swing.JButton jOK;
    private javax.swing.JTextField jObjectName;
    private javax.swing.JLabel jObjectNameTxt;
    private javax.swing.JTextField jPath;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    private void populateFields() {
        String objid = obj_.id;
        String objpath = "";
        int ididx = obj_.id.lastIndexOf('/');
        if (ididx > 0) {
            objid = obj_.id.substring(ididx + 1);
            //default, will use lib path if we find it
            objpath = obj_.id.substring(0, ididx);
        }

        jObjectName.setText(objid);

        AxolotiLibrary sellib = null;
        for (AxolotiLibrary lib : MainFrame.prefs.getLibraries()) {
            if (!lib.isReadOnly()) {
                jLibrary.addItem(lib.getId());
            }
            if (obj_.sPath != null && obj_.sPath.startsWith(lib.getLocalLocation())) {

                if (sellib == null || sellib.getLocalLocation().length() < lib.getLocalLocation().length()) {
                    sellib = lib;
                }
            }
        }

        if (sellib == null || sellib.isReadOnly()) {
            jLibrary.setSelectedItem(AxolotiLibrary.USER_LIBRARY_ID);
        } else {
            jLibrary.setSelectedItem(sellib.getId());
        }
        if (sellib != null) {
            String cp = sellib.getContributorPrefix();
            int cplen = (cp != null && cp.length() > 0 ? cp.length() + 1 : 0);
            // allow for 'objects'
            String tmp = obj_.sPath.substring(sellib.getLocalLocation().length());
            int lidx = tmp.lastIndexOf(File.separator);
            tmp = tmp.substring(8 + cplen, lidx);
            objpath = tmp.replace(File.separatorChar, '/');
        }
        jPath.setText(objpath);
        modifiedData();
    }

    private void modifiedData() {
        jObjectName.setText(jObjectName.getText().trim());
        jPath.setText(jPath.getText().trim());
        if (jLibrary.getSelectedIndex() >= 0) {
            AxolotiLibrary lib = MainFrame.prefs.getLibrary((String) jLibrary.getSelectedObjects()[0]);
            StringBuilder file = new StringBuilder();
            StringBuilder objname = new StringBuilder();

            file.append(lib.getLocalLocation());
            file.append("objects").append(File.separator);
            String cp = lib.getContributorPrefix();
            if (cp != null && cp.length() > 0) {
                file.append(cp).append(File.separator);
                objname.append(cp).append(File.separator);
            }
            objname.append(jPath.getText()).append(File.separator);
            file.append(jPath.getText()).append(File.separator);
            objname.append(jObjectName.getText());
            file.append(jObjectName.getText());
            jFileTxt.setText(file.toString() + ".axo");
            jObjectNameTxt.setText(objname.toString());
        }
    }
}