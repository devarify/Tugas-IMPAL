/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_akun;
import com.koneksi.koneksi;
import com.view.FormTambahAkun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SetyadiR
 */
public class model_akun implements controller_akun{

    @Override
    public void Tambah(FormTambahAkun fta) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try {
         if (fta.txt_usernameBaru.getText().equals("")){
              JOptionPane.showMessageDialog(null, "username tidak boleh kosong");
         }else if (fta.txt_passwordBaru.getText().equals("")){
              JOptionPane.showMessageDialog(null, "password tidak boleh kosong");
         }else if (fta.cmb_role.getSelectedItem().equals("Role")){
              JOptionPane.showMessageDialog(null, "Role harus di isi");
         }else {
            Connection con = koneksi.getKoneksi();
            String sql = "insert user (username,password,role) values ('"+fta.txt_usernameBaru.getText()+"','"+fta.txt_passwordBaru.getText()+"'"
                    + ",'"+fta.cmb_role.getSelectedItem()+"')";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
           fta.txt_usernameBaru.setText("");
           fta.txt_passwordBaru.setText("");
           fta.cmb_role.setSelectedItem("Role");
         }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void Edit(FormTambahAkun fta) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String sql ="UPDATE user SET "
                    + "password = '"+fta.txt_passwordBaru.getText()+"'"
                    + ", role = '"+fta.cmb_role.getSelectedItem()+"' WHERE username = '"+fta.txt_usernameBaru.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data barang berhasil di update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }

    @Override
    public void Hapus(FormTambahAkun fta) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String sql ="delete from user where username='"+fta.txt_usernameBaru.getText()+"'";
            Connection con = koneksi.getKoneksi();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
          JOptionPane.showMessageDialog(null, "data barang berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal hapus data");
        }
    }

    @Override
    public void Tampil(FormTambahAkun fta) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID User");
        model.addColumn("Username");
        model.addColumn("Role");
   
        
        try {
            String sql = "select * from user";
             Connection con = koneksi.getKoneksi();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(4)});
            }
            fta.tbl_akun.setModel(model);
        } catch (Exception e) {
            
        }
    }
    
}
