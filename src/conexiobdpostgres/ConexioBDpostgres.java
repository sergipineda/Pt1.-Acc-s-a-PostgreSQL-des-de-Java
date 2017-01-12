/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiobdpostgres;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author spineda
 */
public class ConexioBDpostgres {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws   java.lang.ClassNotFoundException, FileNotFoundException, IOException {
      
        Connection  connection = null;
        Statement statement= null;
        ResultSet result = null;
        PreparedStatement pst = null;
        boolean insert = false;
        
        
        Properties props = new Properties();
        FileInputStream in = null;
        
        Class.forName("org.postgresql.Driver");
//        String url= "jdbc:postgresql://localhost/world";
//        String user = "postgres";
//        String password = "postgres";
        
        
        try{
            
            in = new FileInputStream("database.properties");
            props.load(in);
            
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        
    connection = DriverManager.getConnection(url, user, password);
    
//    if(pst == null){
//    pst = connection.prepareStatement("INSERT INTO prova VALUES (?, ?) ");
//            
//            
//            pst.setString(1, "pepe2");
//            pst.setString(2, "goteras");
//           pst.executeUpdate();
//           
//           insert = true;
//    }      
//        if(insert){
            
           statement = connection.createStatement();
           result = statement.executeQuery("SELECT * FROM prova;");

            System.out.println("*************Resultat************");
           
            while (result.next()) {
                System.out.println(result.getString(1)+": "+result.getString(2));
                                System.out.print("\n");
               
            }           
        
            
//        }   
           
          
        
        
        }catch (SQLException ex){
            
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

