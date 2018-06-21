/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.view.FormTambahAkun;
import java.sql.SQLException;

/**
 *
 * @author SetyadiR
 */
public interface controller_akun {
    public void Tambah (FormTambahAkun fta) throws SQLException;
    public void Edit (FormTambahAkun fta) throws SQLException;
    public void Hapus (FormTambahAkun fta) throws SQLException;
    public void Tampil (FormTambahAkun fta) throws SQLException;
}
