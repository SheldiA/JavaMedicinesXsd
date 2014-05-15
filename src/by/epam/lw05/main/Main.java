/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.main;

import by.epam.lw05.constant.DbConst;
import by.epam.lw05.entity.MedicineXmlTO;
import by.epam.lw05.entity.MedicineXmlWriter;
import by.epam.lw05.entity.bd.DbPool;
import by.epam.lw05.entity.bd.MySQLReader;
import by.epam.lw05.entity.medicines.Analogs;
import by.epam.lw05.entity.medicines.Consistense;
import by.epam.lw05.entity.medicines.Dosage;
import by.epam.lw05.entity.medicines.Frequency;
import by.epam.lw05.entity.medicines.Medicine;
import by.epam.lw05.entity.medicines.Medicines;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Anna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, SQLException {
        // TODO code application logic here
        Medicines medicines= new Medicines();     
        MySQLReader ms = new MySQLReader();
        MedicineXmlWriter mxw = new MedicineXmlWriter();
        Iterator<MedicineXmlTO> med = ms.read();
        while(med.hasNext()){
            MedicineXmlTO to = med.next();
            Medicine medicine = mxw.getMedicineByTo(to);
            Analogs an = new Analogs();
            for(int j = 0 ; j < to.analogs.size();++j)
                an.getAnalog().add(mxw.getMedicineByTo(to.analogs.get(j)));
            
            medicine.setAnalogs(an);
            medicines.getMedicine().add(medicine);
        }
        try {
            JAXBContext context = JAXBContext.newInstance(Medicines.class);
            Marshaller m = context.createMarshaller();
            try {
                m.marshal(medicines, new FileOutputStream(DbConst.OUTPUT_FILE));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DbPool.getInstance().closePool();
    }
    
}
