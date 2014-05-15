/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw05.entity.bd;

import by.epam.lw05.constant.DbConst;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anna
 */
public class DbPool {
    private BlockingQueue<Connection> connections;
    
    private DbPool() {
        connections = new ArrayBlockingQueue<Connection>(DbConst.CONNECTIONS_QUEUE_SIZE);
        try {
            Class.forName(DbConst.DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            for(int i = 0; i < DbConst.CONNECTIONS_QUEUE_SIZE; ++i)
                connections.add(DriverManager.getConnection(DbConst.DB_URL, DbConst.DB_USER, DbConst.DB_PASSWORD));
        } catch (SQLException ex) {
            Logger.getLogger(DbPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() throws SQLException, InterruptedException{
        if(connections.isEmpty())
            return DriverManager.getConnection(DbConst.DB_URL, DbConst.DB_USER, DbConst.DB_PASSWORD);
        else
            return connections.take();
    }
    
    public void putConnection(Connection connection) throws InterruptedException{
        connections.put(connection);
    }
    
    public void closePool() throws InterruptedException, SQLException{
        while(!connections.isEmpty())
            connections.take().close();
    }
    
    public static DbPool getInstance() {
        return DbPoolHolder.INSTANCE;
    }
    
    private static class DbPoolHolder {

        private static final DbPool INSTANCE = new DbPool();
    }
}
