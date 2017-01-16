/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MostrarMetadades;


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
public class metadades  {
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
    
    

        
            
         String query = "SELECT * FROM productes WHERE clau=1;";

            pst = connection.prepareStatement(query);
            result =  pst.executeQuery();


            System.out.println("*************Resultat************");
            
            
            
             ResultSetMetaData meta = result.getMetaData();

            String colname1 = meta.getColumnName(1);
            String colname2 = meta.getColumnName(2);

            Formatter fmt1 = new Formatter();
            fmt1.format("%-21s%s", colname1, colname2);
            System.out.println(fmt1);
           
            while (result.next()) {
                
                Formatter fmt2 = new Formatter();
                fmt2.format("%-21s", result.getString(1));
                System.out.print(fmt2);
                System.out.println(result.getString(2));
//                System.out.println(result.getString(1)+": "+result.getString(2));
//                                System.out.print("\n");
               
                       
        
            
        }   
    
        }catch (SQLException ex){
              Logger lgr = Logger.getLogger( metadades.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            System.out.println("Error en la sentencia SQL");
            
        
        }finally{
           try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
        }catch (SQLException ex) {
            System.out.println("fallo2");
         
               
            }
        }
    
    }
}