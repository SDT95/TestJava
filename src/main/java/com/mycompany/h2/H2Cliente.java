/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.h2;

import com.mycompany.entita.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class H2Cliente {
    
    public void addCostumer (Cliente cliente) throws SQLException{
        Connection conn = DriverManager.getConnection(
            "jdbc:h2:~/test", // database file
            "sa",             // username
            ""                // password
        );
        Statement stmt = conn.createStatement();
        
        stmt.execute("INSERT INTO cliente (nome, cognome, dataDiNascita, codiceFisdcale, email)" +
             "VALUES ( '" + cliente.nome +
             "', ' " + cliente.cognome +
             "', ' " + cliente.dataDiNascita +
             "', ' " + cliente.codiceFiscale +
             "', ' " + cliente.email +
             "')");
        conn.close();
    }
    
    public Cliente getCostumer (long idUSer) throws SQLException{
        Connection conn = DriverManager.getConnection(
            "jdbc:h2:~/test", // database file
            "sa",             // username
            ""                // password
        );
        Statement stmt = conn.createStatement();
        Cliente cliente = new Cliente();
        ResultSet rs =stmt.executeQuery("SELECT * FROM cliente where ID = " + idUSer);
        while (rs.next()) {
            cliente.setNome(rs.getString("nome"));
            cliente.setCognome(rs.getString("cognome"));
            cliente.setDataDiNascita(rs.getTimestamp("dataDiNascita"));
            cliente.setCodiceFiscale(rs.getString("codiceFiscale"));
            cliente.setEmail(rs.getString("email"));
            
        }
        conn.close();
        return cliente;
    }    
    public List<Cliente> getAllCostumers () throws SQLException{
        Connection conn = DriverManager.getConnection(
            "jdbc:h2:~/test", // database file
            "sa",             // username
            ""                // password
        );
        Statement stmt = conn.createStatement();
        List<Cliente> clienti = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNome(rs.getString("nome"));
            cliente.setCognome(rs.getString("cognome"));
            cliente.setDataDiNascita(rs.getTimestamp("dataDiNascita"));
            cliente.setCodiceFiscale(rs.getString("codiceFiscale"));
            cliente.setEmail(rs.getString("email"));
            clienti.add(cliente);
        }
        conn.close();
        return clienti;
    }    
}
