/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author atd43
 */
public class RSSI_Mapping extends javax.swing.JFrame {

    /**
     * Generated serializable version ID - required when class 
     * is serializable
     */
    private static final long serialVersionUID = 1083808740418053965L;
    /** Stores the current running directory */
    private final String root_directory;
    /** Stores the directory for the saved settings file*/
    private final String save_settings;
	
    /** Stores the operating mode of the program */
    private String program_mode;
    
    
    private String rssi_filename, loc_filename, lookup_filename, map_filename;
    
    private RSSI_Data rssi;
    private NodeLocations locs;
    private int num_nodes;
    
    private csvLoader loader;
    private Resources resources;

    /**
     * Creates new form RSSI_Mapping
     */
    public RSSI_Mapping() {
        this.root_directory = (new File(".").getAbsolutePath()).replace(".", "");
        this.save_settings = root_directory + "Utilities" + File.separator + "RssiMappingSettings.save";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(save_settings));
            rssi_filename = root_directory + "CSVs" + File.separator + reader.readLine();            
            loc_filename = root_directory + "CSVs" + File.separator + reader.readLine();
            lookup_filename = root_directory + "CSVs" + File.separator + reader.readLine();
            map_filename = root_directory + "images" + File.separator + reader.readLine();
            reader.close();
        } catch (IOException ex) {
            rssi_filename = "";
            loc_filename = "";
            lookup_filename = "";
            map_filename = "";
        }
        
        initComponents();
        
        //Initialize the default program mode
        this.program_mode = mode_combobox.getSelectedItem().toString();
        
        try {
            RoomLookUpMap map = new RoomLookUpMap(map_filename);
        } catch (IOException ex) {
            Error_text.setText("Error: Room map not found");
            JOptionPane.showMessageDialog(null, "Error: Room map not found", "Alert", JOptionPane.ERROR_MESSAGE);
        }

        fileChooser.setCurrentDirectory(new java.io.File("."));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        filename_label = new javax.swing.JLabel();
        rssi_filename_text = new javax.swing.JTextField();
        rssiFileBrowse_button = new javax.swing.JButton();
        filename_label2 = new javax.swing.JLabel();
        loc_filename_text = new javax.swing.JTextField();
        locFileBrowse_button = new javax.swing.JButton();
        run_button = new javax.swing.JButton();
        Error_text = new javax.swing.JLabel();
        KnownNodes_label = new javax.swing.JLabel();
        UnknownNode_label = new javax.swing.JLabel();
        knownNodes_text = new javax.swing.JTextField();
        nodesToFind_text = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        mode_combobox = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RSSI Mapping");
        setBounds(new java.awt.Rectangle(100, 100, 500, 500));
        setMinimumSize(new java.awt.Dimension(500, 200));

        filename_label.setText("File containing the RSSI data:");

        rssi_filename_text.setText(rssi_filename);

        rssiFileBrowse_button.setText("Browse");
        rssiFileBrowse_button.setMaximumSize(new java.awt.Dimension(60, 23));
        rssiFileBrowse_button.setMinimumSize(new java.awt.Dimension(60, 23));
        rssiFileBrowse_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rssiFileBrowse_buttonActionPerformed(evt);
            }
        });

        filename_label2.setText("File containing the node location data:");

        loc_filename_text.setText(loc_filename);
        load_locs();
        loc_filename_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loc_filename_textActionPerformed(evt);
            }
        });
        loc_filename_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                loc_filename_textKeyReleased(evt);
            }
        });

        locFileBrowse_button.setText("Browse");
        locFileBrowse_button.setMaximumSize(new java.awt.Dimension(60, 23));
        locFileBrowse_button.setMinimumSize(new java.awt.Dimension(60, 23));
        locFileBrowse_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locFileBrowse_buttonActionPerformed(evt);
            }
        });

        run_button.setText("Run Program");
        run_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run_buttonActionPerformed(evt);
            }
        });

        KnownNodes_label.setText("Known Node Locations:");

        UnknownNode_label.setText("Node Locations to Find:");

        jLabel1.setText("Program Mode: ");

        mode_combobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Test Mode", "Operational Mode", "Algorithm Test" }));
        mode_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mode_comboboxActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem6.setText("Open Building Map");
        jMenu1.add(jMenuItem6);

        jMenuItem3.setText("Open Lookup File");
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Run Operational");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Run Test");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tools");

        jMenuItem5.setText("Run Distance Test");
        jMenu2.add(jMenuItem5);

        jMenuItem4.setText("Change Default File Locations");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(run_button, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Error_text))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rssi_filename_text)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rssiFileBrowse_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loc_filename_text)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(locFileBrowse_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(filename_label)
                                            .addComponent(filename_label2)
                                            .addComponent(KnownNodes_label)
                                            .addComponent(knownNodes_text, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nodesToFind_text, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(UnknownNode_label)))
                                    .addComponent(jLabel1)
                                    .addComponent(mode_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 247, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filename_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rssi_filename_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rssiFileBrowse_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filename_label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loc_filename_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locFileBrowse_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KnownNodes_label)
                    .addComponent(UnknownNode_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(knownNodes_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nodesToFind_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mode_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(run_button, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(Error_text))
        );

        filename_label.getAccessibleContext().setAccessibleName("filename_label");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void run_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_run_buttonActionPerformed
        //make sure the locations were entered correctly
        if (loader == null){
            load_locs();
            return;
        }
        
        //Load in the resources
        try {
            Error_text.setText("");
            resources = new Resources(lookup_filename);
        } catch (FileNotFoundException ex) {
            Error_text.setText("Error: File Not Found - Lookup");
            JOptionPane.showMessageDialog(null, "Error: File not found \n" + lookup_filename, "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            Error_text.setText("Error: Could not read lookup data");            
            JOptionPane.showMessageDialog(null, "Error: could not read lookup data", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Load in the RSSI data
        try {
            Error_text.setText("");
            rssi = new RSSI_Data(num_nodes);
            loader.ReadRSSI(rssi_filename_text.getText(), rssi);
            //System.out.println(rssi.toString());
        } catch (FileNotFoundException ex) {
            Error_text.setText("Error: File Not Found - RSSI");
            JOptionPane.showMessageDialog(null, "Error: File not found \n" + rssi_filename, "Alert", JOptionPane.ERROR_MESSAGE);
            
            return;
        } catch (IOException ex) {
            Error_text.setText("Error: Could not read RSSI data");
            JOptionPane.showMessageDialog(null, "Error: Could not read RSSI data", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //read in which nodes to find
        String[] nodesToFind = nodesToFind_text.getText().split(",");
        if (nodesToFind_text.getText().equals("")) nodesToFind = new String[0];
        int[] nodes_to_find = new int[nodesToFind.length];
        for (int i = 0; i < nodesToFind.length; i++){
            try {
                nodes_to_find[i] = Integer.parseInt(nodesToFind[i].trim());
            } catch(Exception e) {
                Error_text.setText("Error: Nodes must be integer values");
                JOptionPane.showMessageDialog(null, "Error: Nodes must be integer values", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        //output error if no nodes to find
        if (nodes_to_find.length <= 0) {
            Error_text.setText("Error: Must enter at least one node to find");
            JOptionPane.showMessageDialog(null, "Error: Must enter at least one node to find","Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //output error if nodes to find is not a valid node number
        for (int i = 0; i < nodes_to_find.length; i++){
            if (nodes_to_find[i] < 0 || nodes_to_find[i] > num_nodes){
                Error_text.setText("Error: Must enter a valid node number");
                JOptionPane.showMessageDialog(null, "Error: Must enter a valid node number","Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        //output error if operational mode and invalid input
        if (program_mode.equals("Operational Mode")){
            String[] knownNodes = knownNodes_text.getText().split(",");
            int[] known_nodes = new int[knownNodes.length];
            for (int i = 0; i < known_nodes.length; i++)
                known_nodes[i] = Integer.parseInt(knownNodes[i].trim());

            for (int find = 0; find < nodes_to_find.length; find++){
                for (int known = 0; known < known_nodes.length; known++){
                    if (nodes_to_find[find] == known_nodes[known]){
                        Error_text.setText("Error: Cannot find a node that is already known in operational mode");
                        JOptionPane.showMessageDialog(null, "Error: Cannot find a node that is already known in operational mode","Alert", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            
            Run_Operational(nodes_to_find);
        } else
            Run_Test(nodes_to_find);
        
        Save_Settings();
        
    }//GEN-LAST:event_run_buttonActionPerformed

    //browse for the rssi data file and put the location in the text field
    private void rssiFileBrowse_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rssiFileBrowse_buttonActionPerformed
        //open the file chooser dialog
        int confirm = fileChooser.showOpenDialog(RSSI_Mapping.this);
        
        //confirm it was completed successfully
        if (confirm == JFileChooser.APPROVE_OPTION){
            //set the text in the text field to the file name
            rssi_filename_text.setText(fileChooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_rssiFileBrowse_buttonActionPerformed

    private void locFileBrowse_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locFileBrowse_buttonActionPerformed
        //open the file chooser dialog
        int confirm = fileChooser.showOpenDialog(RSSI_Mapping.this);
        
        //confirm it was completed successfully
        if (confirm == JFileChooser.APPROVE_OPTION){
            //set the text in the text field to the file name
            loc_filename_text.setText(fileChooser.getSelectedFile().getPath());
            load_locs();
        }
    }//GEN-LAST:event_locFileBrowse_buttonActionPerformed

    private void load_locs(){
        knownNodes_text.setText(null);
        loader = null;
        
        try {
            Error_text.setText("");
            loader = new csvLoader(loc_filename_text.getText());
            num_nodes = loader.ReadNodes();
            locs = new NodeLocations(num_nodes);
            loader.ReadLocs(locs);
            knownNodes_text.setText(locs.getKnown());
        } catch (FileNotFoundException ex) {
            Error_text.setText("Error: File Not Found - Locations");
            JOptionPane.showMessageDialog(null, "Error: File not found \n" + loc_filename, "Alert", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Error_text.setText("Error: Could not read location data");
            JOptionPane.showMessageDialog(null, "Error: Could not read location data", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loc_filename_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loc_filename_textActionPerformed
        load_locs();
    }//GEN-LAST:event_loc_filename_textActionPerformed

    private void loc_filename_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loc_filename_textKeyReleased
        load_locs();
    }//GEN-LAST:event_loc_filename_textKeyReleased

    private void mode_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mode_comboboxActionPerformed
        //Set the program mode variable equal to the selection
        this.program_mode = mode_combobox.getSelectedItem().toString();
    }//GEN-LAST:event_mode_comboboxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RSSI_Mapping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RSSI_Mapping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RSSI_Mapping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RSSI_Mapping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RSSI_Mapping().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Error_text;
    private javax.swing.JLabel KnownNodes_label;
    private javax.swing.JLabel UnknownNode_label;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel filename_label;
    private javax.swing.JLabel filename_label2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField knownNodes_text;
    private javax.swing.JButton locFileBrowse_button;
    private javax.swing.JTextField loc_filename_text;
    private javax.swing.JComboBox mode_combobox;
    private javax.swing.JTextField nodesToFind_text;
    private javax.swing.JButton rssiFileBrowse_button;
    private javax.swing.JTextField rssi_filename_text;
    private javax.swing.JButton run_button;
    // End of variables declaration//GEN-END:variables
    
    private void Run_Operational(int[] nodes_to_find) {  
        
        JOptionPane.showMessageDialog(null, "Operational Mode not yet supported","Alert", JOptionPane.ERROR_MESSAGE);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void Run_Test(int[] nodes_to_find) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void Run_Algorithm_Test(int[] nodes_to_find) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void Save_Settings() {
        String settings = rssi_filename +"\n"+ loc_filename +"\n"+ lookup_filename +"\n"+ map_filename;
        try {
          File file = new File(save_settings);
          BufferedWriter output = new BufferedWriter(new FileWriter(file));
          output.write(settings);
          output.close();
        } catch ( IOException e ) {
          Error_text.setText("Error: Could not write to file");
          JOptionPane.showMessageDialog(null, "Error: Could not write to file", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }

}
