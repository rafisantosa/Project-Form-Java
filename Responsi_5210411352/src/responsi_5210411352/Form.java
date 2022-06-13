package responsi_5210411352;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Form extends javax.swing.JFrame {

    public Form() {
        initComponents();
        initKomponenTambahan();
        txtGaji.setEditable(false);
    }
    private DefaultTableModel model;
    private void initKomponenTambahan() {
                
        // Inisiasi model tabel Pegawai
       model = (DefaultTableModel) tblPegawai.getModel();
       this.loadDataToTabel();
    }
    private void loadDataToTabel() {
        //Agar tidak terisi semua data di dalam tabel dengan data yang ada di dalam database( terduplikat )
        //untuk membersihkan tabel 
        model.setRowCount(0);

        try {
            Connection db = KoneksiDB.getKoneksi();
            Statement st = db.createStatement();

            String sql = "SELECT * FROM pegawai";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                Object[] data = new Object[4];
                data[0] = rs.getString("Nama");
                data[1] = rs.getString("No_Pegawai");
                data[2] = rs.getString("Posisi");
                data[3] = rs.getInt("Gaji");
                model.addRow(data);
            }

            rs.close();
            st.close();
        }
        catch (SQLException e) {
            System.err.println("Load data eror: " + e.getMessage());
        }
    } 
    public void printAll()throws NoPegawaiHarusAngka,NamaHarusAda,NoPegawaiHarusAda,NoPegawaiHarus10Digit,NoPegawaiHarusPositif{
        Long noPegawai;
        //Untuk mngecek texfield nama kosong atau tidak 
        if(txtNama.getText().isEmpty()){
            throw  new NamaHarusAda();
        }
        //untuk mengecek textfield no pegawai kosong atau tidak 
        if(txtNoPegawai.getText().length()<1){
                throw new NoPegawaiHarusAda();
            }
        
        //untuk mengecek txtfield no pegawai harus angka
        try {
            noPegawai=Long.parseLong(txtNoPegawai.getText());
        } catch (NumberFormatException e) {
            throw new NoPegawaiHarusAngka();
        }
        //untuk membabatasi isian txfield no pegawai maksimal 10
        if (txtNoPegawai.getText().length()>10){
            throw new NoPegawaiHarus10Digit();
        }
        //untuk mngecek ntxtfield no pegawai tidak negatif
        if(noPegawai<0){
            throw new NoPegawaiHarusPositif();
        }
        
        //untuk memasukan data kedalam database
        try {
            Connection db = KoneksiDB.getKoneksi();

            String sql = "INSERT INTO pegawai "
                    + "(Nama,No_Pegawai,Posisi,Gaji) "
                    + "VALUES (?,?,?,?)";

            PreparedStatement ps = db.prepareStatement(sql);

            ps.setString(1, txtNama.getText());
            ps.setString(2, txtNoPegawai.getText());
            ps.setString(3, cmbPosisi.getSelectedItem().toString());
            ps.setString(4, txtGaji.getText());

            int rowInsert = ps.executeUpdate();
            if (rowInsert > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Insert data eror: " + e.getMessage());
        }
        finally {
            // Load tabel Pegawai untuk melihat perubahan
            this.loadDataToTabel();
        }   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnExit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNoPegawai = new javax.swing.JTextField();
        cmbPosisi = new javax.swing.JComboBox<>();
        txtGaji = new javax.swing.JTextField();
        btnInstert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPegawai = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(679, 544));

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(679, 544));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 26)); // NOI18N
        jLabel1.setText("IT Worker Form ");

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("No.Pegawai   :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama            :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Posisi             :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Gaji  (IDR )    : ");

        cmbPosisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Senior Programmer", "Bussiness Analyst", "Data Center Officer", "Junior Programmer" }));
        cmbPosisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPosisiActionPerformed(evt);
            }
        });

        txtGaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGajiActionPerformed(evt);
            }
        });

        btnInstert.setBackground(new java.awt.Color(51, 51, 255));
        btnInstert.setText("Insert");
        btnInstert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstertActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(102, 255, 102));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tblPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama", "No.Pegawai", "Posisi", "Gaji"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
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
        tblPegawai.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblPegawaiAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPegawaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPegawai);

        btnDelete.setBackground(new java.awt.Color(255, 255, 102));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnInstert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate))
                    .addComponent(txtNama)
                    .addComponent(txtNoPegawai)
                    .addComponent(cmbPosisi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGaji))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
                    .addComponent(jSeparator2))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExit)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbPosisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInstert)
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        //untuk menampilkan konfirmasi keluar program
        int aksi=JOptionPane.showConfirmDialog(rootPane, "Ingin Keluar Program ?","Keluar",JOptionPane.YES_NO_OPTION);
        if(aksi==JOptionPane.YES_OPTION){
           System.exit(0); 
        }  
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGajiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGajiActionPerformed

    private void btnInstertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstertActionPerformed
        //membuar objek pgw (dari kelas pegawai )
        Pegawai pgw= new Pegawai(
                txtNama.getText(),
                cmbPosisi.getSelectedItem().toString(),
                txtNoPegawai.getText(),
                Integer.parseInt(txtGaji.getText()));
        
        try {
            this.printAll();
        } catch (NoPegawaiHarusAngka e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (NamaHarusAda e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch(NoPegawaiHarusAda e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }catch(NoPegawaiHarus10Digit e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }catch(NoPegawaiHarusPositif e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnInstertActionPerformed

    private void cmbPosisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPosisiActionPerformed
        //auto generate gaji berdasarkan posisi
        String posisi=cmbPosisi.getSelectedItem().toString();
        switch (posisi){
            case "Senior Programmer":
                txtGaji.setText("15000000");
                break;
            case "Bussiness Analyst":
                txtGaji.setText("21000000");
                break;
            case "Data Center Officer":
                txtGaji.setText("18000000");
                break;
            case "Junior Programmer":
                txtGaji.setText("8000000");
                break;
        }
    }//GEN-LAST:event_cmbPosisiActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //untuk meng-update data yang ada didalam database berdasarkan no pegawai
        try {
            
            Connection db = KoneksiDB.getKoneksi();

            String sql = "UPDATE pegawai "
                    + "SET Nama=?, No_Pegawai=?, Posisi=?, Gaji=? "
                    + "WHERE No_Pegawai=?;";

            PreparedStatement ps = db.prepareStatement(sql);

            ps.setString(1, txtNama.getText());
            ps.setString(2, txtNoPegawai.getText());
            ps.setString(3, cmbPosisi.getSelectedItem().toString());
            ps.setString(4, txtGaji.getText());
            ps.setString(4, txtGaji.getText());
            ps.setString(5, txtNoPegawai.getText());         

            int rowInsert = ps.executeUpdate();
            if (rowInsert > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Update data eror: " + e.getMessage());
        }
        finally {
            // Load tabel Pegawai untuk melihat perubahan
            this.loadDataToTabel();
        }                                   
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblPegawaiAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblPegawaiAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPegawaiAncestorAdded

    private void tblPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPegawaiMouseClicked
        //Menampilkan Data ke Form Saat di Klik 
        int rowIdx = tblPegawai.getSelectedRow();
        
        String nama = tblPegawai.getValueAt(rowIdx, 0).toString();
        txtNama.setText(nama);
        
        String noPegawai = tblPegawai.getValueAt(rowIdx, 1).toString();
        txtNoPegawai.setText(noPegawai);
        
        String posisi= tblPegawai.getValueAt(rowIdx, 2).toString();
        switch (posisi){
            case "Senior Programmer":
                txtGaji.setText("15000000");
                break;
            case "Bussiness Analyst":
                txtGaji.setText("21000000");
                break;
            case "Data Center Officer":
                txtGaji.setText("18000000");
                break;
            case "Junior Programmer":
                txtGaji.setText("8000000");
                break;
        }
    }//GEN-LAST:event_tblPegawaiMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //untuk menghapus data berdasarkan id yang dsembunyikan
        try {
            Connection db = KoneksiDB.getKoneksi();

            String sql = "DELETE FROM pegawai "
                    + "WHERE No_Pegawai=?;";

            PreparedStatement ps = db.prepareStatement(sql);

            ps.setString(1, txtNoPegawai.getText());
            
            //untuk menampilkan pesan konfirmasi penghapusan data 
            int aksi=JOptionPane.showConfirmDialog(rootPane, "Anda yakin ?","Hapus",JOptionPane.YES_NO_OPTION);
            if (aksi==JOptionPane.YES_OPTION){
                int rowInsert = ps.executeUpdate();
                if (rowInsert >= 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            

            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Delete data eror: " + e.getMessage());
        }
        finally {
            // Load tabel Pegawai untuk melihat perubahan
            this.loadDataToTabel();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInstert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbPosisi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblPegawai;
    private javax.swing.JTextField txtGaji;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoPegawai;
    // End of variables declaration//GEN-END:variables
}
