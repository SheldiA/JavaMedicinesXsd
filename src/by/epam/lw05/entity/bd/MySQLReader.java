/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.entity.bd;

import by.epam.lw05.constant.DbConst;
import by.epam.lw05.entity.MedicineXmlTO;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        ArrayList<MedicineXmlTO> medTo = new ArrayList<MedicineXmlTO>();
        try {
            Connection connection = getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM medicine ORDER BY name");
            while(rs.next()){
                MedicineXmlTO to = new MedicineXmlTO();
                ResultSet temp;
                to.medicineId = rs.getString("id");
                to.medicineName = rs.getString("name");
                to.medicineGroup = rs.getString("groupp");
                to.medicineCompany = rs.getString("company");
                to.dosageId = rs.getString("dosage_id");
                String packId = rs.getString("package_id");
                String q = "SELECT * FROM consistence WHERE id=" + rs.getString("consistence_id");
                temp = st.executeQuery(q);
                if(temp.next()){
                    String ddd = temp.getString(1);
                    to.consistence = temp.getString("name");
                }
                
                temp = st.executeQuery("SELECT * FROM dosage WHERE id=" + to.dosageId);
                if(temp.next()){
                    to.isAfterFood = temp.getBoolean("is_after_food");
                    to.numberPerPeriod = temp.getInt("number");
                }
                temp = st.executeQuery("SELECT * FROM frequency WHERE id=" + temp.getString("frequency_id"));
                if(temp.next())
                    to.frequency = temp.getString("name");
                temp = st.executeQuery("SELECT * FROM package WHERE id=" + packId);
                if(temp.next()){
                    to.packageId = temp.getString("id");
                    to.packageType = temp.getString("type");
                    to.numberPerPackage = temp.getInt("number");
                    to.packagePrice = temp.getFloat("price");
                }
                medTo.add(to);
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
