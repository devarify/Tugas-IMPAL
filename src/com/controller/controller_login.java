/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.view.FormLogin;
import java.sql.SQLException;

/**
 *
 * @author SetyadiR
 */
public interface controller_login {
    public void Login (FormLogin lgn) throws SQLException;
}
