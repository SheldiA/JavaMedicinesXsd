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
    
    public ArrayList<MedicineXmlTO> read(){
        ArrayList<MedicineXmlTO> medTo = new ArrayList<MedicineXmlTO>();
        try {
            Connection connection = getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM medicine m JOIN consistence c ON c.id=m.consistence_id JOIN dosage d ON d.id=dosage_id JOIN frequency f ON f.id = d.frequency_id JOIN package p ON p.id=m.package_id");
            while(rs.next()){
                MedicineXmlTO to = new MedicineXmlTO();
                to.medicineId = rs.getString("m.id");
                to.medicineName = rs.getString("m.name");
                to.medicineGroup = rs.getString("m.groupp");
                to.medicineCompany = rs.getString("m.company");
                to.dosageId = rs.getString("m.dosage_id");
                to.consistence = rs.getString("c.name");
                to.isAfterFood = rs.getBoolean("d.is_after_food");
                to.numberPerPeriod = rs.getInt("d.number");
                
                to.frequency = rs.getString("f.name");
                to.packageId = rs.getString("p.id");
                to.packageType = rs.getString("p.type");
                to.numberPerPackage = rs.getInt("p.number");
                to.packagePrice = rs.getFloat("p.price");

                medTo.add(to);
            }
            for(int i = 0; i < medTo.size(); ++i){
                MedicineXmlTO to = medTo.get(i);
                to.analogs = new ArrayList<MedicineXmlTO>();
                rs = st.executeQuery("SELECT * FROM analog WHERE analog_medicine_id="+i+" OR medicine_id="+i);
                while(rs.next()){
                    int n = rs.getInt("analog_medicine_id");
                    if(n != i)
                        to.analogs.add(medTo.get(n));
                    else
                        to.analogs.add(medTo.get(rs.getInt("medicine_id")));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return medTo;
    }
}
