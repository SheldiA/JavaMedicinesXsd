/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.entity;

import by.epam.lw05.entity.medicines.Consistense;
import by.epam.lw05.entity.medicines.Dosage;
import by.epam.lw05.entity.medicines.Frequency;
import by.epam.lw05.entity.medicines.Medicine;
import java.math.BigInteger;

/**
 *
 * @author Anna
 */
public class MedicineXmlWriter {
    public Medicine getMedicineByTo(MedicineXmlTO med){
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
}
