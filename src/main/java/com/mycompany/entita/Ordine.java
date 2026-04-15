/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entita;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
@Entity
@Table(name = "ordine")
public class Ordine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public long idOrdine;
    public long idCliente;
    public long idProdotto;
    public int quantita;
    public String stato;

    public Ordine(int idOrdine, long idCliente, long idProdotto, int quantita, String stato) {
        this.idOrdine = idOrdine;
        this.idCliente = idCliente;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
        this.stato = stato;
    }

    public Ordine() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public long getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(long idOrdine) {
        this.idOrdine = idOrdine;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(long idProdotto) {
        this.idProdotto = idProdotto;
    }

    

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    
    @Override
    public String toString() {
        return "Ordine{" + "id=" + id +
                ", idOrdine=" + idOrdine +
                ", idCliente=" + idCliente +
                ", idProdotto=" + idProdotto +
                ", quantita=" + quantita + 
                ", stato=" + stato + '}';
    }

       
    
}
