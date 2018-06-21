/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.view.FormKategori;
import java.sql.SQLException;

/**
 *
 * @author SetyadiR
 */
public interface controller_kategori {
    public void Tambah (FormKategori fki) throws SQLException;
    public void Edit (FormKategori fki) throws SQLException;
    public void Hapus (FormKategori fki) throws SQLException;
    public void Tampil (FormKategori fki) throws SQLException;
    public void IdKategori (FormKategori fki) throws SQLException;
}
