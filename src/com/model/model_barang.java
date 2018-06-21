/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_barang;
import com.koneksi.koneksi;
import com.view.FormBarang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SetyadiR
 */
public class model_barang implements controller_barang{

    @Override
    public void TampilkeComboBox(FormBarang fbr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {

            String sql = "select namaKategori from kategori";
            Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                fbr.cmb_kategori.addItem(res.getString("namaKategori"));
               
            }
            res.last();
            int jumlahData = res.getRow();
            res.first();
        } catch (Exception e) {
            
        }
    }

    @Override
    public void Tambah(FormBarang fbr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
         if (fbr.txt_namaBarang.getText().equals("")){
              JOptionPane.showMessageDialog(null, "nama Barang tidak boleh kosong");
         }else if (fbr.cmb_kategori.getSelectedItem().equals("Kategori")){
              JOptionPane.showMessageDialog(null, "Kategori harus di isi");
         }else if (fbr.txt_hargaBeli.getText().equals("")){
              JOptionPane.showMessageDialog(null, "Harga Beli harus di isi");
         }else if (fbr.txt_hargaJual.getText().equals("")){
              JOptionPane.showMessageDialog(null, "Harga Jual harus di isi");
         }else if (fbr.txt_quantity.getText().equals("")){
              JOptionPane.showMessageDialog(null, "Quantity harus di isi");
         }else {
            Connection con = koneksi.getKoneksi();
            String sql = "insert barang (kodeBarang,namaBarang,kategoriBarang,hargaBeli,hargaJual,quantity) "
                    + "values ('"+fbr.txt_kodeBarang.getText()+"','"+fbr.txt_namaBarang.getText()+"'"
                    + ",'"+fbr.cmb_kategori.getSelectedItem()+"','"+fbr.txt_hargaBeli.getText()+"'"
                    + ",'"+fbr.txt_hargaJual.getText()+"','"+fbr.txt_quantity.getText()+"')";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
           fbr.txt_namaBarang.setText("");
           fbr.cmb_kategori.setSelectedItem("Kategori");
           fbr.txt_hargaBeli.setText("");
           fbr.txt_hargaJual.setText("");
           fbr.txt_quantity.setText("");
           fbr.txt_namaBarang.requestFocus();
         }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void Edit(FormBarang fbr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try {
            String sql ="UPDATE barang SET "
                    + "namaBarang = '"+fbr.txt_namaBarang.getText()+"',kategoribarang = '"+fbr.cmb_kategori.getSelectedItem()+"'"
                    + ",hargaBeli = '"+fbr.txt_hargaBeli.getText()+"', hargaJual = '"+fbr.txt_hargaJual.getText()+"'"
                    + ",quantity = '"+fbr.txt_quantity.getText()+"' WHERE kodeBarang = '"+fbr.txt_kodeBarang.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data barang berhasil di update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }

    @Override
    public void Hapus(FormBarang fbr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String sql ="delete from barang where kodeBarang='"+fbr.txt_kodeBarang.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
          JOptionPane.showMessageDialog(null, "data barang berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal hapus data");
        }
    }

    @Override
    public void Tampil(FormBarang fbr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode");
        model.addColumn("Nama");
        model.addColumn("Kategori");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        model.addColumn("Quantity");
   
        
        try {
              int no = 1;
            String sql = "select * from barang";
             Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)});
            }
            fbr.tbl_brg.setModel(model);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void Cari(FormBarang fbr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             try {
            String sql = "select * from barang where namaBarang = '"+fbr.txt_cari.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                    fbr.txt_kodeBarang.setText(res.getString("kodeBarang"));
                    fbr.txt_namaBarang.setText(res.getString("namaBarang"));
                    fbr.cmb_kategori.setSelectedItem(res.getString("kategoriBarang"));
                    fbr.txt_hargaBeli.setText(res.getString("hargaBeli"));
                    fbr.txt_hargaJual.setText(res.getString("hargaJual"));
                    fbr.txt_quantity.setText(res.getString("quantity"));
            }
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Data yang anda cari tidak ditemukan");
        }
    }

    @Override
    public void KodeBarang(FormBarang fbr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try {
            String sql = "select * from barang order by kodeBarang desc";
          Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
             if (res.next()) {
                String kdBrg = res.getString("kodeBarang").substring(1);
                String AN = "" + (Integer.parseInt(kdBrg) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}

               fbr.txt_kodeBarang.setText("B" + Nol + AN);
            } else {
               fbr.txt_kodeBarang.setText("B0001");
            }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"");
        }
    }
    
}
