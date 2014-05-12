/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.entity.bd;

import by.epam.lw05.entity.constant.DbConst;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anna
 */
public class MySQLReader {
    private Connection getConnection() throws SQLException{
        try {
            Class.forName(DbConst.dBDriver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(DbConst.dBUrl, DbConst.dBUser, DbConst.dBPassword);
    }
    
    public void read(){
        try {
            Connection connection = getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM medicine ORDER BY name");
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
