/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_login;
import com.koneksi.UserID;
import com.koneksi.koneksi;
import com.view.FormHome;
import com.view.FormHomeKasir;
import com.view.FormLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author SetyadiR
 */
public class model_login implements controller_login{

    @Override
    public void Login(FormLogin lgn) throws SQLException {
         try {
           Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM user where username='"+lgn.txt_username.getText()+"' and password = '"+lgn.txt_password.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            if (lgn.txt_username.getText().equals("")){
                     JOptionPane.showMessageDialog(null, "username tidak boleh kosong");
            }else if (lgn.txt_password.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "password tidak boleh kosong");
            }else if (rs.next()) {
                UserID.setUserLogin(lgn.txt_username.getText());
                if(rs.getString(4).equals("admin")){
                    JOptionPane.showMessageDialog(null, "Berhasil Login sebagai Admin");
                    lgn.dispose();
                     new FormHome().setVisible(true);
                }else if(rs.getString(4).equals("kasir")){
                   JOptionPane.showMessageDialog(null, "Berhasil Login sebagai Kasir");
                    lgn.dispose();
                    new FormHomeKasir().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(lgn, "Password Salah");
                  
                }
            } else {
                JOptionPane.showMessageDialog(lgn, "Username/Password Belum Terdaftar");
              
                lgn.txt_username.requestFocus();
            }
        } catch (Exception e) {
        }
    }
    
}
