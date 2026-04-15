/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entita;

import jakarta.persistence.*;
/**
 *
 * @author User
 */
@Entity
@Table(name = "prodotto")
public class Prodotto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public String codice;
    public String nome;
    public int stock;

    public Prodotto(String codice, String nome, int stock) {
        this.codice = codice;
        this.nome = nome;
        this.stock = stock;
    }

    public Prodotto() {
        
    }

    public Long getId() {
        return id;
    }
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "prodotto{" +
                "codice=" + codice + 
                ", nome=" + nome + 
                ", stock=" + stock + '}';
    }
    
    
}
