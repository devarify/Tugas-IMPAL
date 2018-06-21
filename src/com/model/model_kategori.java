/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_kategori;
import com.koneksi.koneksi;
import com.view.FormKategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SetyadiR
 */
public class model_kategori implements controller_kategori{

    @Override
    public void Tambah(FormKategori fki) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
         if (fki.txt_namaKategori.getText().equals("")){
              JOptionPane.showMessageDialog(null, "username tidak boleh kosong");
         }else {
            Connection con = koneksi.getKoneksi();
            String sql = "insert kategori (idKategori,namaKategori) values ('"+fki.txt_idKategori.getText()+"','"+fki.txt_namaKategori.getText()+"')";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
           fki.txt_namaKategori.setText("");
           fki.txt_namaKategori.requestFocus();
         }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void Edit(FormKategori fki) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String sql ="UPDATE kategori SET "
                    + "namaKategori = '"+fki.txt_namaKategori.getText()+"' WHERE idKategori = '"+fki.txt_idKategori.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data barang berhasil di update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }

    @Override
    public void Hapus(FormKategori fki) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String sql ="delete from kategori where idKategori='"+fki.txt_idKategori.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
          JOptionPane.showMessageDialog(null, "data barang berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal hapus data");
        }
    }

    @Override
    public void Tampil(FormKategori fki) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID Kategori");
        model.addColumn("Nama Kategori");
        
        try {
              int no = 1;
            String sql = "select * from kategori";
             Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2)});
            }
            fki.tbl_ktgr.setModel(model);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void IdKategori(FormKategori fki) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String sql = "select * from kategori order by idKategori desc";
          Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
             if (res.next()) {
                String noTrf = res.getString("idKategori").substring(1);
                String AN = "" + (Integer.parseInt(noTrf) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}

               fki.txt_idKategori.setText("K" + Nol + AN);
            } else {
               fki.txt_idKategori.setText("K0001");
            }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"");
        }
    }
    
}
