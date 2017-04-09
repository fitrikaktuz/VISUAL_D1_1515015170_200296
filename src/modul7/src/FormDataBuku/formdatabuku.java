/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormDataBuku;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 *@author
 */
public class formdatabuku extends javax.swing.JFrame {

    private DefaultTableModel model; //untuk membuat model pada tabel
    private final Connection con = koneksi.getConnection(); //untuk mengambil koneksi
    private Statement stt; // untuk eksekusi query database
    private ResultSet rss; //untuk menampung data dari database
    
    private int baris; 
    private boolean kebenaran;
    /**
     * Creates new form fromdatabuku
     */
    public formdatabuku() { //construktor formdatabuku
        initComponents();
    }
    
    private void InitTable(){ //method InitTable
        model = new DefaultTableModel(); //inisiasi model dgn nama table defaulttablemodel 
        model.addColumn("Id"); //membuat kolom Id pada tabel DefaultTableModel
        model.addColumn("Judul"); //membuat kolom Judul pada tabel DefaultTableModel
        model.addColumn("Penulis"); //membuat kolom Penulis pada tabel DefaultTableModel
        model.addColumn("Harga"); //membuat kolom Harga pada tabel DefaultTableModel
        
        tb.setModel(model); //tb nama dari jTable.setting model di save di parameter model
    }
    
    private void TampilData(){ //method TampilData
        try { // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan
            String sql = "SELECT * FROM buku"; //Eksekusi query sql untuk melihat isi dari table buku
            stt = con.createStatement();// eksekusi query database membuat Statement dgn koneksi 
            rss = stt.executeQuery(sql); //Menampung hasi dari database dari eksekusi query sql
            while(rss.next()){ //jika data yg di masukkan sesuai query ,lanjut
                Object[] o = new Object[4]; //membuat inisialisasi objek dgn index array 4 sesuai dgn jumlah kolom yg di buat di table
                o[0] = rss.getInt("id"); //objek o parameter [0] memberikan id dgn tipe data integer
                o[1] = rss.getString("judul"); //objek o parameter [1] memberikan judul dgn tipe data string
                o[2] = rss.getString("penulis"); //objek o parameter [2] memberikan penulis dgn tipe data string
                o[3] = rss.getString("harga"); //objek o parameter [3] sam dgn memberikan harga dgn tipe data string
                model.addRow(o); //model tambah baris dgn parameter objek o
            }
        } catch (SQLException e) { // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }
     
    private void TambahData(String judul, String penulis, String harga){ // method TambahData dgn parameter judul dgn tipe data string,
                                                                        //penulis dgn tipe data String,dan harga dgn tipe data integer
        try { // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan
            String sql = 
                    "INSERT INTO buku VALUES (NULL,'"+judul+"','"+penulis+"',"+harga+")"; //query mengisi data di tabel buku
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            stt.executeUpdate(sql); //Menampung hasi dari database dari eksekusi query sql
            model.addRow(new Object[]{judul,penulis,harga}); //model tambah baris simpan di objek baru dgn isi judul,penulis dan harga 
            
        } catch (SQLException e) { // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }
    
    private void HapusData(String id,int baris){ //method HapusData dgn parameter id dgn tipe data String dan baris dgn tipe data integer
        try { // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan
            
            String sqldelete = //query untuk menghapus suatu data tertentu dimana  kondisi berdasarkan id nya
                    "DELETE FROM buku WHERE id='"+id+"'";
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            stt.executeUpdate(sqldelete); //Menampung hasi dari database dari eksekusi query sql
            model.removeRow(baris); //model akan menghapus baris
        } catch (SQLException e) { // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }
    
     private void UbahData(String judul,String penulis, String harga, String id){ //method Ubahdata dgn parameter judul dgn tipe data string,
                                                                        //penulis dgn tipe data String,harga dgn tipe data integer dan id dgn tipe data String
        try { // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan
            
            String sql = "UPDATE buku SET " //Mngubah data dari judul,penulis,harga dimana kondisi nya berdasarkan id nya
                         + "judul='"+judul+"',"
                         + "penulis='"+penulis+"',"
                         + "harga='"+harga+"'"
                         + "WHERE id='"+id+"'";
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            stt.executeUpdate(sql); //Menampung hasi dari database dari eksekusi query sql
            
        } catch (SQLException e) { // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }
     
    private void validasi (String judul, String penulis, String harga){ //method validasi dgn parameter judul dgn tipe data string,
                                                                        //penulis dgn tipe data String,dan harga dgn tipe data integer
        try { // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan
            String sql = "SELECT * FROM judul"; //query untuk menampilkan kolom judul
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            rss = stt.executeQuery(sql); //Menampung hasi dari database dari eksekusi query sql
            while(rss.next()){ //maka data yg di masukkan sesuai query ,lanjut
                Object[] o = new Object[2]; //membuat inisialisasi objek dgn index array 2 sesuai dgn jumlah kolom yg di buat di table
                o[0] = rss.getString("judul").toLowerCase(); //objek o dgn index array 0 memberikan nilai judul dgn tipe data String
                o[1] = rss.getString("penulis").toLowerCase(); //objek o dgn index array 1 memberikan nilai penulis dgn tipe data String
                
                if( o[0].equals(judul.toLowerCase()) && o[1].equals(penulis.toLowerCase())){ //jika objek index 0 sama judul toLowerCase dan objek o index 1 sama penulis toLowerCase 
                    JOptionPane.showMessageDialog(null,"Data telah ada"); //akan muncul psan JOptionPane 'Data telah ada'
                    kebenaran=false; //algoritma boolean dgn nilai salah
                    break; //berhenti
                }
            }
            if(kebenaran==true){ //jika tabel kebenaran dgn nilai benar
                TambahData(judul, penulis, harga);//tambah data dgn parameter judul,penulis dan harga
            }
        } catch (SQLException e) { // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
  
    }
    private void CariId(){ //method CariId
        try{  // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan
            String sql = "SELECT*from buku where id='"+src.getText()+"'"; //query melihat isi dari tabel buku dimana kondisinya berdasarkan Id  cari dgn memberikan inputan teks
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            rss = stt.executeQuery(sql); //Menampung hasi dari database dari eksekusi query sql
            while(rss.next()){ //maka data yg di masukkan sesuai query ,lanjut
              Object[] o = new Object[4]; //membuat inisialisasi objek dgn index array 4 sesuai dgn jumlah kolom yg di buat di table  
              o[0] = rss.getInt("id"); //objek o parameter [0] memberikan id dgn tipe data integer 
              o[1] = rss.getString("judul");//objek o parameter [1] memberikan judul dgn tipe data string
              o[2] = rss.getString("penulis"); //objek o parameter [2] memberikan penulis dgn tipe data string
              o[3] = rss.getString("harga");  //objek o parameter [3] memberikan harga dgn tipe data string
              model.addRow(o); //model tambah baris dgn parameter objek o
            }
        }catch(SQLException e){ // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }
    private void CariJudul(){ //method CariJudul 
        try{ // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan 
            String sql = "SELECT*from buku where judul='"+src.getText()+"'"; //query melihat isi tabel buku dimanakondsinya berdasarkan judul,cari dgn memberikan inputan teks 
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            rss = stt.executeQuery(sql);  //Menampung hasi dari database dari eksekusi query sql
            while(rss.next()){  //maka data yg di masukkan sesuai query ,lanjut
              Object[] o = new Object[4]; //membuat inisialisasi objek dgn index array 4 sesuai dgn jumlah kolom yg di buat di table    
              o[0] = rss.getInt("id"); //objek o parameter [0] memberikan id dgn tipe data integer 
              o[1] = rss.getString("judul");  //objek o parameter [1] memberikan judul dgn tipe data string
              o[2] = rss.getString("penulis"); //objek o parameter [2] memberikan penulis dgn tipe data string
              o[3] = rss.getString("harga"); //memberikan harga dgn tipe data string
              model.addRow(o); //model tambah baris dgn parameter objek o
            }
        }catch(SQLException e){ // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }
    private void CariPenulis(){//method CariPenulis
        try{ // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan 
            String sql = "SELECT*from buku where penulis='"+src.getText()+"'"; //query melihan isi dari tabel buku dimana kondisi berdasarkan kolom penulis,cari dgn memberikan iputan text
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            rss = stt.executeQuery(sql); //Menampung hasi dari database dari eksekusi query sql
            while(rss.next()){ //maka data yg di masukkan sesuai query ,lanjut
              Object[] o = new Object[4]; //membuat inisialisasi objek dgn index array 4 sesuai dgn jumlah kolom yg di buat di table    
              o[0] = rss.getInt("id"); //objek o parameter [0] memberikan id dgn tipe data integer 
              o[1] = rss.getString("judul"); //objek o parameter [1] memberikan judul dgn tipe data string
              o[2] = rss.getString("penulis"); //objek o parameter [2] memberikan penulis dgn tipe data string
              o[3] = rss.getString("harga");  //objek o parameter [2] memberikan penulis dgn tipe data string
              model.addRow(o); //model tambah baris dgn parameter objek o
            }
        }catch(SQLException e){ // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }
    private void CariHarga(){//method CariHarga
        try{  // di dalam blok try adalah untuk perintah" yg akan di coba untuk di lakukan 
            String sql = "SELECT*from buku where harga='"+src.getText()+"'"; //query melihat isi dari table buku dimana kondisi berdasarkan harga,cari dgn memberikan  inputan teks
            stt = con.createStatement(); // eksekusi query database membuat Statement dgn koneksi
            rss = stt.executeQuery(sql); //Menampung hasi dari database dari eksekusi query sql
            while(rss.next()){ //maka data yg di masukkan sesuai query ,lanjut
              Object[] o = new Object[4]; //membuat inisialisasi objek dgn index array 4 sesuai dgn jumlah kolom yg di buat di table    
              o[0] = rss.getInt("id"); //objek o parameter [0] memberikan id dgn tipe data integer 
              o[1] = rss.getString("judul"); //objek o parameter [1] memberikan judul dgn tipe data string
              o[2] = rss.getString("penulis"); //objek o parameter [2] memberikan penulis dgn tipe data string
              o[3] = rss.getString("harga"); //objek o parameter [2] memberikan penulis dgn tipe data string
              model.addRow(o); //model tambah baris dgn parameter objek o
            }
        }catch(SQLException e){ // di dalam blok catch adalah perintah" yg akan di lakukan bila terjadi eror
            System.out.println(e.getMessage()); //sistem akan menampilkan pesan error
        }
    }

     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jdl = new javax.swing.JTextField();
        hrg = new javax.swing.JTextField();
        pnl = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        src = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        cr = new javax.swing.JButton();
        kmb = new javax.swing.JButton();
        id = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        ch = new javax.swing.JButton();
        del = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("FORM DATA BUKU");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(146, 146, 146))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("Judul");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setText("Penulis");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("Harga");

        pnl.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        pnl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tere Liye", "W.S Rendra", "Felix Siauw", "Asma Nadia", "Dewi Lestari" }));
        pnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hrg)
                    .addComponent(jdl)
                    .addComponent(pnl, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jdl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(hrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Search");

        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        cr.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cr.setText("Cari");
        cr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crActionPerformed(evt);
            }
        });
        jPanel6.add(cr);

        kmb.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        kmb.setText("Kembali");
        kmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmbActionPerformed(evt);
            }
        });
        jPanel6.add(kmb);

        id.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        id.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Judul", "Penulis", "Harga" }));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel6.setText("By  :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(src, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(src, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        save.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        ch.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        ch.setText("Ubah");
        ch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chActionPerformed(evt);
            }
        });

        del.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        del.setText("Hapus");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        exit.setText("Keluar");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        tb.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Judul", "Penulis", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(ch)
                    .addComponent(del)
                    .addComponent(exit))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlActionPerformed

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown
        // TODO add your handling code here:
         InitTable(); //untuk menampilkan data dari tabel
        TampilData();
    }//GEN-LAST:event_jPanel1ComponentShown

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        if(jdl.getText().equals("") && hrg.getText().equals("")) //jika isi dari kolom judul sama dan kolom harga sama
     {
           JOptionPane.showMessageDialog(null, "Data Belum Lengkap","Warning !!!!",JOptionPane.INFORMATION_MESSAGE); //akan muncul kalimat data belum legkap
           jdl.requestFocus();//judul meminta
     } else{ //maka
        String judul = jdl.getText(); //memberikan nilai pada judul
        String penulis = pnl.getSelectedItem().toString(); //memebrikan nilai pada penulis
        String harga = hrg.getText(); // memberikan nilai pada harga
        TambahData(judul,penulis,harga); //tambah data dgn isi judul,penulis dan harga
        validasi(judul, penulis, harga); //validasi dgn isi judul,penulis dan harga 
        
        InitTable(); 
        TampilData();
    }//GEN-LAST:event_saveActionPerformed
    }
    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
         System.exit(0); //keluar dari sistem
    }//GEN-LAST:event_exitMouseClicked

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        // TODO add your handling code here:
        String id = tb.getValueAt(baris, 0).toString(); //menghapus data berdasarkan kolom id
        HapusData( id, baris);
    }//GEN-LAST:event_delActionPerformed

    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
        // TODO add your handling code here:
        int baris = tb.getSelectedRow();
        
        String judul=tb.getValueAt(baris,1).toString();
        String penulis=tb.getValueAt(baris,2).toString();
        String harga=tb.getValueAt(baris,3).toString();
        String id=tb.getValueAt(baris,0).toString();
        
        
        jdl.setText(judul);
        pnl.setSelectedItem(penulis);
        hrg.setText(harga);
    }//GEN-LAST:event_tbMouseClicked

    private void chActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chActionPerformed
        // TODO add your handling code here:
        int baris = tb.getSelectedRow();
        
        
        tb.setValueAt(jdl.getText(),baris,1);
        tb.setValueAt(pnl.getSelectedItem(),baris,2);
        tb.setValueAt(hrg.getText(),baris,3); 
        
        String judul=tb.getValueAt(baris,1).toString();
        String penulis=tb.getValueAt(baris,2).toString();
        String harga=tb.getValueAt(baris,3).toString();
        String id=tb.getValueAt(baris,0).toString();
        
        
        jdl.setText(judul);
        pnl.setSelectedItem(penulis);
        hrg.setText(harga);
        
        UbahData(judul,penulis,harga,id);
    }//GEN-LAST:event_chActionPerformed

    private void crActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crActionPerformed
        // TODO add your handling code here:
       if(src.getText().equals("")){
            JOptionPane.showMessageDialog(null,"isi dulu guys!!!");
        }
        else{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            if(id.getSelectedItem().equals("Id")){
             CariId();  
            }
            else if(id.getSelectedItem().equals("Judul")){
             CariJudul();
            }
            else if(id.getSelectedItem().equals("Penulis")){
             CariPenulis();
            }
            else if(id.getSelectedItem().equals("Harga")){
             CariHarga();
             }
        }
    }//GEN-LAST:event_crActionPerformed

    private void kmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmbActionPerformed
        // TODO add your handling code here:
        InitTable(); //untuk menampilkan data keseluruhan
        TampilData();
    }//GEN-LAST:event_kmbActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
       InitTable(); //untuk menampilkan data
       TampilData(); 
    }//GEN-LAST:event_formComponentShown

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0); //keluardari sistem
    }//GEN-LAST:event_exitActionPerformed

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
            java.util.logging.Logger.getLogger(formdatabuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formdatabuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formdatabuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formdatabuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formdatabuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ch;
    private javax.swing.JButton cr;
    private javax.swing.JButton del;
    private javax.swing.JButton exit;
    private javax.swing.JTextField hrg;
    private javax.swing.JComboBox id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jdl;
    private javax.swing.JButton kmb;
    private javax.swing.JComboBox pnl;
    private javax.swing.JButton save;
    private javax.swing.JTextField src;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables

   
}
