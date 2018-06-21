/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SetyadiR
 */
public class koneksi {
    private static Connection koneksi;
    
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost/db_kasir";
                String username = "root";
                String pass = "";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, username, pass);
            } catch (Exception e) {
                System.out.println(e);
            }
        }return koneksi;
    }
}
