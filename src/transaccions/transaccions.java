/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccions;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spineda
 */
public class transaccions {
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
           Connection  connection = null;
        Statement statement= null;
        ResultSet result = null;
        PreparedStatement pst = null;
        boolean insert = false;
        
        
        Properties props = new Properties();
        FileInputStream in = null;
        
        Class.forName("org.postgresql.Driver");
        
        try{
               
            in = new FileInputStream("database.properties");
            props.load(in);
            
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        
    connection = DriverManager.getConnection(url, user, password);
    
    

        
        connection.setAutoCommit(false);

          
            
            statement.executeUpdate("UPDATE productes SET especificacio = '64 bits' "
                    + "WHERE clau = 2");

            connection.commit();
            
        }catch (SQLException ex){
 if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger lgr = Logger.getLogger(transaccions.class.getName());
                    lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                }
            }

            Logger lgr = Logger.getLogger(transaccions.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            
        
        }finally{
           try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(transaccions.class.getName());
            }
        }
        }
    
    }
    

