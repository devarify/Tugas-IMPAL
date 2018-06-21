/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.view.FormTransaksi;
import java.sql.SQLException;

/**
 *
 * @author SetyadiR
 */
public interface controller_transaksi {
     public void Cari (FormTransaksi ftr) throws SQLException;
     public void Tambah (FormTransaksi ftr) throws SQLException;
     public void Hapus (FormTransaksi ftr) throws SQLException;
     public void Tampil (FormTransaksi ftr) throws SQLException;
     public void HapusSemua (FormTransaksi ftr) throws SQLException;
     public void TanggalHariIni (FormTransaksi ftr) throws SQLException;
     public void JumlahBeli (FormTransaksi ftr) throws SQLException;
     public void Total (FormTransaksi ftr) throws SQLException;
     public void UpdateStok (FormTransaksi ftr) throws SQLException;
     public void HitungTotal (FormTransaksi ftr) throws SQLException;
     public void TidakJadiBeli (FormTransaksi ftr) throws SQLException;
     public void Bayar (FormTransaksi ftr) throws SQLException;
}
