/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.files.main;

import by.epam.lw05.files.medicines.Consistense;
import by.epam.lw05.files.medicines.Dosage;
import by.epam.lw05.files.medicines.Frequency;
import by.epam.lw05.files.medicines.Medicine;
import by.epam.lw05.files.medicines.Medicines;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
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
    public static void main(String[] args) {
        // TODO code application logic here
        Medicines medicines= new Medicines();
        Medicine medicine = new Medicine();
        medicine.setName("Valakordin");
        medicine.setCompany("ValCompany");
        medicine.setId("1");
        medicine.setGroup("Cardio");
        Consistense consistence = Consistense.PILL;
        medicine.setConsistence(consistence);
        medicine.setAnalogs(null);
        Dosage dosage = new Dosage();
        dosage.setId("1");
        dosage.setIsAfterFood(false);
        dosage.setFrequency(Frequency.PER_DAY);
        dosage.setNumber(BigInteger.ONE);
        medicine.setDosage(dosage);
        by.epam.lw05.files.medicines.Package pack = new by.epam.lw05.files.medicines.Package();
        pack.setId("1");
        pack.setNumber(BigInteger.valueOf(30));
        pack.setPrice(Float.valueOf(30));
        pack.setType("vakuum");
        medicine.setPackage(pack);
        medicines.getMedicine().add(medicine);
        try {
            JAXBContext context = JAXBContext.newInstance(Medicines.class);
            Marshaller m = context.createMarshaller();
            try {
                m.marshal(medicines, new FileOutputStream("medicines.xml"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
