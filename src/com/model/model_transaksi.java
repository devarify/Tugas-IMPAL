/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_transaksi;
import com.koneksi.koneksi;
import com.view.FormTransaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SetyadiR
 */
public class model_transaksi implements controller_transaksi{

    @Override
    public void Cari(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try {
            String sql = "select * from barang where namaBarang = '"+ftr.txt_cari.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                    ftr.lbl_hargaJual.setText(res.getString("hargaJual"));
                    ftr.lbl_quantity.setText(res.getString("quantity"));
            }
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Data yang anda cari tidak ditemukan");
        }
    }

    @Override
    public void Tambah(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
         if (ftr.txt_cari.getText().equals("")){
              JOptionPane.showMessageDialog(null, "nama Barang tidak boleh kosong");
         }else if (ftr.txt_jmlBeli.equals("")){
              JOptionPane.showMessageDialog(null, "Jumlah Beli tidak boleh kosong");
         }else {
            Connection con = koneksi.getKoneksi();
            String sql = "insert transaksi (namaKasir,tgl,namaBrg,jmlBeli,harga,total) "
                    + "values ('"+ftr.txt_nama.getText()+"','"+ftr.txt_tgl.getText()+"'"
                    + ",'"+ftr.txt_cari.getText()+"','"+ftr.txt_jmlBeli.getText()+"'"
                    + ",'"+ftr.lbl_hargaJual.getText()+"','"+ftr.lbl_total.getText()+"')";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
           ftr.txt_cari.setText("");
           //ftr.cmb_kategori.setSelectedItem("Kategori");
           ftr.txt_jmlBeli.setText("");
           ftr.lbl_hargaJual.setText("");
           ftr.lbl_quantity.setText("");
           ftr.txt_cari.requestFocus();
         }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void Hapus(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String sql ="delete from transaksi where namaBrg='"+ftr.txt_cari.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
          JOptionPane.showMessageDialog(null, "data barang berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal hapus data");
        }
    }

    @Override
    public void Tampil(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kasir");
        model.addColumn("Tanggal");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga ");
        model.addColumn("Total");
   
        
        try {
              int no = 1;
            String sql = "select * from transaksi where namaKasir = '"+ftr.txt_nama.getText()+"'";
             Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)});
            }
            ftr.tbl_trf.setModel(model);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void HapusSemua(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String sql ="delete from transaksi ";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
          //JOptionPane.showMessageDialog(null, "data barang berhasil di hapus");
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, "gagal hapus data");
        }
    }

    @Override
    public void TanggalHariIni(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Date now = new Date();
        SimpleDateFormat simpleDateFormat = 
                new SimpleDateFormat("dd MMMM yyyy");
        String tgl = simpleDateFormat.format(now);
        ftr.txt_tgl.setText(tgl);
    }

    @Override
    public void JumlahBeli(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int a = Integer.parseInt(ftr.lbl_quantity.getText());
        int b = Integer.parseInt(ftr.txt_jmlBeli.getText()); 
        if (b > a){
            JOptionPane.showMessageDialog(null,"Jumlah yang anda minta melebihi Stok yang tersedia");
        }else {
            a = a - b;
            ftr.lbl_updateStok.setText(""+a);
            Total(ftr);
            UpdateStok(ftr);
            try {
                Tambah(ftr);
                Tampil(ftr);
           
        } catch (Exception e) {
        }
             HitungTotal(ftr);
        }
    }

    @Override
    public void Total(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int a = Integer.parseInt(ftr.lbl_hargaJual.getText());
        int b = Integer.parseInt(ftr.txt_jmlBeli.getText()); 
        int c = 0; 
        
        c = a * b;
        ftr.lbl_total.setText(""+c);
    }

    @Override
    public void UpdateStok(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String sql ="UPDATE barang SET quantity = '"+ftr.lbl_updateStok.getText()+"' WHERE namaBarang = '"+ftr.txt_cari.getText()+"'";
             Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
           
        } catch (Exception e) {
          
        }

    }

    @Override
    public void HitungTotal(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       int total = 0;
       for (int i =0; i< ftr.tbl_trf.getRowCount(); i++){
       int amount = Integer.parseInt((String)ftr.tbl_trf.getValueAt(i, 6));
       total += amount;
    }
    ftr.txt_totalBlnj.setText(""+total);
    }

    @Override
    public void TidakJadiBeli(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int a = Integer.parseInt(ftr.lbl_updateStok.getText());
        int b = Integer.parseInt(ftr.txt_jmlBeli.getText()); 
        
        a = a + b;
        ftr.lbl_quantity.setText(""+a);
         try {
            String sql ="UPDATE barang SET quantity = '"+ftr.lbl_quantity.getText()+"' WHERE namaBarang = '"+ftr.txt_cari.getText()+"'";
             Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
           
        } catch (Exception e) {
          
        }
    }

    @Override
    public void Bayar(FormTransaksi ftr) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int a = Integer.parseInt(ftr.txt_totalBlnj.getText());
        int b = Integer.parseInt(ftr.txt_bayar.getText()); 
        if (a > b ){
        JOptionPane.showMessageDialog(null,"Uang yang anda masukkan kurang!!");
         //txt_totalBlnj.setText("");
        ftr.txt_bayar.setText("");
        }else {
         a = b - a;
         ftr.txt_kembalian.setText(""+a);
            HapusSemua(ftr);
            Tampil(ftr);
        ftr.txt_totalBlnj.setText("");
        ftr.txt_bayar.setText("");
        }
    }
    
}
