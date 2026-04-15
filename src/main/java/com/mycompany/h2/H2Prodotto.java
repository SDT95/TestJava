/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.h2;

import com.mycompany.entita.Prodotto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class H2Prodotto {
    
    private String userName = "sa";
    private String db = "jdbc:h2:~/test";
    private String pwd = "";
    
    public void addProduct (Prodotto prodotto) throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );        
        String sql = "INSERT INTO prodotto (codice, nome, stock) VALUES (?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, prodotto.codice);
        stmt.setString(2, prodotto.nome);
        stmt.setDouble(3, prodotto.stock);
        stmt.executeUpdate();
        conn.close();
    }
    
    public Prodotto getProduct (long idProduct) throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );
        Statement stmt = conn.createStatement();
        Prodotto prodotto = new Prodotto();
        ResultSet rs =stmt.executeQuery("SELECT * FROM prodotto where ID = " + idProduct);
        while (rs.next()) {
            prodotto.setCodice(rs.getString("codice"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setStock(rs.getInt("stock"));
            
        }
        conn.close();
        return prodotto;
    }   
    
    public List<Prodotto> getAllProducts () throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );
        Statement stmt = conn.createStatement();
        List<Prodotto> prodotti = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM prodotto");
        while (rs.next()) {
            Prodotto prodotto = new Prodotto();
            prodotto.setCodice(rs.getString("codice"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setStock(rs.getInt("stock"));
            prodotti.add(prodotto);
        }
        conn.close();
        return prodotti;
    }
     
    public void updateStock (int ordineQuantita, long idProdotto, boolean increase) throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd              // password
        );
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM prodotto where id = " + idProdotto);
        
        int stock = 0;
        int stockNew = 0;
        
        while (rs.next()) {
           stock = rs.getInt("stock");
        }
        if(increase){
            stockNew = stock + ordineQuantita;
        } else {
            stockNew = stock - ordineQuantita;
        }
        stmt.executeUpdate("UPDATE prodotto SET stock = " + stockNew + "  where id = " + idProdotto);
        conn.close();
    }
}
