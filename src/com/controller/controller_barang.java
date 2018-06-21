/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.view.FormBarang;
import java.sql.SQLException;

/**
 *
 * @author SetyadiR
 */
public interface controller_barang {
    public void TampilkeComboBox (FormBarang fbr) throws SQLException;
    public void Tambah (FormBarang fbr) throws SQLException;
    public void Edit (FormBarang fbr) throws SQLException;
    public void Hapus (FormBarang fbr) throws SQLException;
    public void Tampil (FormBarang fbr) throws SQLException;
    public void Cari (FormBarang fbr) throws SQLException;
    public void KodeBarang (FormBarang fbr) throws SQLException;
}
