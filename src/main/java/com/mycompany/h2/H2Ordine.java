/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.h2;

import com.mycompany.entita.Ordine;
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
public class H2Ordine {
    
    private String userName = "sa";
    private String db = "jdbc:h2:~/test";
    private String pwd = "";
    
    public void addOrder (Ordine ordine) throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );
        
        String sql = "INSERT INTO ordine (idOrdine, idCliente, idProdotto, quantita, stato) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setLong(1, (getMaxOrderNumber() + 1));
        stmt.setLong(2, ordine.idCliente);
        stmt.setDouble(3, ordine.idProdotto);
        stmt.setDouble(4, ordine.quantita);
        stmt.setString(5, ordine.stato);
        stmt.executeUpdate();
        H2Prodotto h2Prodotto = new H2Prodotto();
        h2Prodotto.updateStock(ordine.quantita, ordine.idProdotto, false);
        conn.close();
    }
    
    public List<Ordine> getOrder (long idOrdine) throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );
        Statement stmt = conn.createStatement();
        List<Ordine> righeOrdine = new ArrayList<>();
        ResultSet rs =stmt.executeQuery("SELECT * FROM ordine where idOrdine = " + idOrdine);
        while (rs.next()) {
            Ordine ordine = new Ordine();
            ordine.setIdCliente(rs.getLong("idCliente"));
            ordine.setIdProdotto(rs.getLong("idProdotto"));
            ordine.setIdOrdine(rs.getLong("idOrdine"));
            ordine.setQuantita(rs.getInt("quantita"));
            ordine.setStato(rs.getString("stato"));
            righeOrdine.add(ordine);
        }
        conn.close();
        return righeOrdine;
    }
    
    public boolean deleteOrder (long idOrdine) throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );
        Statement stmt = conn.createStatement();
        List<Ordine> righeOrdine = getOrder(idOrdine);
        if(righeOrdine != null || righeOrdine.get(0).getStato().equals("Ordinato") || righeOrdine.get(0).getStato() == null){
            stmt.executeUpdate("DELETE FROM ordine WHERE idOrdine = " + idOrdine);
            H2Prodotto h2Prodotto = new H2Prodotto();
            for (Ordine ordine : righeOrdine) {
                h2Prodotto.updateStock(ordine.quantita, ordine.idProdotto, true);
            }
            conn.close();
            return true;
        } else {
            conn.close();
            return false;
        }
    }    
    
    
    public List<Ordine> getAllOrders () throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );
        Statement stmt = conn.createStatement();
        List<Ordine> righeOrdine = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ordine");
        while (rs.next()) {
            Ordine ordine = new Ordine();
            ordine.setId(rs.getLong("id"));
            ordine.setIdCliente(rs.getLong("idCliente"));
            ordine.setIdProdotto(rs.getLong("idProdotto"));
            ordine.setIdOrdine(rs.getLong("idOrdine"));
            ordine.setQuantita(rs.getInt("quantita"));
            ordine.setStato(rs.getString("stato"));
            righeOrdine.add(ordine);
        }
        conn.close();
        return righeOrdine;
    }
    
    public Long getMaxOrderNumber () throws SQLException{
        Connection conn = DriverManager.getConnection(
            db, // database file
            userName,             // username
            pwd                // password
        );
        Statement stmt = conn.createStatement();
        Long maxOrderId = null;
        ResultSet rs = stmt.executeQuery("SELECT MAX(idOrdine) as idOrdine FROM ordine ");
        while (rs.next()) {
            try {
                 maxOrderId = rs.getLong("idOrdine");
            } catch (Exception e) {
                System.out.println("idOrdine nullo o non presente " + e);
                maxOrderId = 0l;
            }
        }
        conn.close();
        return maxOrderId;
    }    
}
