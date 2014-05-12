/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.entity;

import java.util.ArrayList;

/**
 *
 * @author Anna
 */
public class MedicineXmlTO {
    public String medicineId;
    public String medicineName;
    public String medicineCompany;
    public String medicineGroup;

    public String consistence;

    public ArrayList<MedicineXmlTO> analogs;

    public String dosageId;
    public boolean isAfterFood;
    public String frequency;
    public int numberPerPeriod;
    
    public String packageId;
    public int numberPerPackage;
    public String packageType;
    public float packagePrice;

    public Package _package;

    
}
