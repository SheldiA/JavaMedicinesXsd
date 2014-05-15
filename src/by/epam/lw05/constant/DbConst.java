/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.constant;

/**
 *
 * @author Anna
 */
public final class DbConst {
    public static String DB_URL="jdbc:mysql://localhost/medicine";
    public static String DB_DRIVER="com.mysql.jdbc.Driver";
    public static String DB_USER="root";
    public static String DB_PASSWORD="Lkz_VfqCRK_14";
    public static int CONNECTIONS_QUEUE_SIZE = 5;
    public static String OUTPUT_FILE = "src\\by\\epam\\lw05\\files\\medicines.xml";
    private DbConst(){}
}
