/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author User
 */
@SpringBootApplication
public class Project_Test {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        try{
        Connection conn = DriverManager.getConnection(
            "jdbc:h2:~/test", // database file
            "sa",             // username
            ""                // password
        );
        
        Statement stmt = conn.createStatement();
        
        
        //per pulizia
        /*stmt.execute("DROP TABLE IF EXISTS cliente");
        stmt.execute("DROP TABLE IF EXISTS prodotto");
        stmt.execute("DROP TABLE IF EXISTS ordine");*/
        
        stmt.execute("CREATE TABLE IF NOT EXISTS cliente (" +
             "id INT AUTO_INCREMENT PRIMARY KEY, " +
             "nome VARCHAR(255), " +
             "cognome VARCHAR(255), " +
             "dataDiNascita TIMESTAMP, " +
             "codiceFiscale VARCHAR(255 ), " +
             "email VARCHAR(255)" +
             ")");
        
        stmt.execute("CREATE TABLE IF NOT EXISTS prodotto (" +
             "id INT AUTO_INCREMENT PRIMARY KEY, " +
             "codice VARCHAR(255), " +
             "nome VARCHAR(255), " +
             "stock INTEGER " +
             ")");
        
        stmt.execute("CREATE TABLE IF NOT EXISTS ordine (" +
             "id INT AUTO_INCREMENT PRIMARY KEY, " +
             "idOrdine INTEGER, " +
             "idCliente INTEGER, " +
             "idProdotto INTEGER, " +
             "quantita INTEGER, " +
             "stato VARCHAR(255) " +
             ")");
        
        /*stmt.execute("INSERT INTO cliente (nome, cognome, dataDiNascita, codiceFiscale, email)" +
             "VALUES ( 'Test Nome" +
             "', 'Test Cognome" +
             "', '2017-07-23" +
             "', 'FDF4DF45DSF54DSF4" +
             "', 'Test Email" +
             "')");*/
        conn.close();
        SpringApplication.run(Project_Test.class, args);
        System.out.println("TUTTO OK");
        
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
