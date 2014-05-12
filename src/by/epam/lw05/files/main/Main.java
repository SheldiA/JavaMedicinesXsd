/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.files.main;

import by.epam.lw05.entity.MedicineXmlTO;
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
import java.util.ArrayList;
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
    
    private static Medicine getMedicineByTo(MedicineXmlTO med){
            Medicine medicine = new Medicine();
            medicine.setName(med.medicineName);
            medicine.setCompany(med.medicineCompany);
            medicine.setId(med.medicineId);
            medicine.setGroup(med.medicineGroup);
            Consistense consistence = Consistense.fromValue(med.consistence);
            medicine.setConsistence(consistence);
            medicine.setAnalogs(null);
            Dosage dosage = new Dosage();
            dosage.setId(med.dosageId);
            dosage.setIsAfterFood(med.isAfterFood);
            dosage.setFrequency(Frequency.fromValue(med.frequency));
            dosage.setNumber(BigInteger.valueOf(med.numberPerPeriod));
            medicine.setDosage(dosage);
            by.epam.lw05.entity.medicines.Package pack = new by.epam.lw05.entity.medicines.Package();
            pack.setId(med.packageId);
            pack.setNumber(BigInteger.valueOf(med.numberPerPackage));
            pack.setPrice(Float.valueOf(med.packagePrice));
            pack.setType(med.packageType);
            medicine.setPackage(pack);
            return medicine;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Medicines medicines= new Medicines();
        
        
        MySQLReader ms = new MySQLReader();
        ArrayList<MedicineXmlTO> med = ms.read();
        for(int i = 0; i < med.size(); ++i){
            Medicine medicine = new Medicine();
            medicine.setName(med.get(i).medicineName);
            medicine.setCompany(med.get(i).medicineCompany);
            medicine.setId(med.get(i).medicineId);
            medicine.setGroup(med.get(i).medicineGroup);
            Consistense consistence = Consistense.fromValue(med.get(i).consistence);
            medicine.setConsistence(consistence);
            Analogs an = new Analogs();
            for(int j = 0 ; j < med.get(i).analogs.size();++j)
                an.getAnalog().add(getMedicineByTo( med.get(i).analogs.get(j)));
            
            medicine.setAnalogs(an);
            Dosage dosage = new Dosage();
            dosage.setId(med.get(i).dosageId);
            dosage.setIsAfterFood(med.get(i).isAfterFood);
            dosage.setFrequency(Frequency.fromValue(med.get(i).frequency));
            dosage.setNumber(BigInteger.valueOf(med.get(i).numberPerPeriod));
            medicine.setDosage(dosage);
            by.epam.lw05.entity.medicines.Package pack = new by.epam.lw05.entity.medicines.Package();
            pack.setId(med.get(i).packageId);
            pack.setNumber(BigInteger.valueOf(med.get(i).numberPerPackage));
            pack.setPrice(Float.valueOf(med.get(i).packagePrice));
            pack.setType(med.get(i).packageType);
            medicine.setPackage(pack);
            medicines.getMedicine().add(medicine);
        }
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
