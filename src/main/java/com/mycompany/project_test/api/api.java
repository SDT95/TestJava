/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_test.api;

import com.mycompany.entita.Cliente;
import com.mycompany.entita.Ordine;
import com.mycompany.entita.Prodotto;
import com.mycompany.h2.H2Cliente;
import com.mycompany.h2.H2Ordine;
import com.mycompany.h2.H2Prodotto;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author User
 */

@RestController
@RequestMapping("/api")
public class api {
    
    @GetMapping("/test")
    public String test() {
        return "API OK";
    }
    
    @PostMapping("/addCostumer")
    public String addCostumer(@RequestBody Cliente cliente) throws SQLException {
        H2Cliente h2Cliente = new H2Cliente();
        h2Cliente.addCostumer(cliente);
        return "Utente Inserito";
    }
    
    @GetMapping("/getCostumer/{idCostumer}")
    public String getCostumer(@PathVariable int idCostumer) throws SQLException {
        H2Cliente h2Cliente = new H2Cliente();
        Cliente cliente = h2Cliente.getCostumer(idCostumer);
        return cliente.toString();
    }
    
    @GetMapping("/getAllCostumers")
    public String getAllCostumers() throws SQLException {
        H2Cliente h2Cliente = new H2Cliente();
        List<Cliente> clienti = h2Cliente.getAllCostumers();
        return clienti.toString();
    }
    
    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Prodotto prodotto) throws SQLException {
        H2Prodotto h2Prodotto = new H2Prodotto();
        h2Prodotto.addProduct(prodotto);
        return "Prodotto Inserito";
    }
    
    @GetMapping("/getProduct/{idProduct}")
    public String getProduct(@PathVariable long idProduct) throws SQLException {
        H2Prodotto h2Prodotto = new H2Prodotto();
        Prodotto prodotto = h2Prodotto.getProduct(idProduct);
        return prodotto.toString();
    }
    
    @GetMapping("/getAllProducts")
    public String getAllProducts() throws SQLException {
        H2Prodotto h2Prodotto = new H2Prodotto();
        List<Prodotto> prodotti = h2Prodotto.getAllProducts();
        return prodotti.toString();
    }
    
    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Ordine ordine) throws SQLException {
        H2Prodotto h2Prodotto = new H2Prodotto();
        if(ordine.quantita > h2Prodotto.getProduct(ordine.idProdotto).getStock()){
            return "Ordine non inserito stock non sufficiente";
        } else {
            H2Ordine h2Ordine = new H2Ordine();
            h2Ordine.addOrder(ordine);
            return "Ordine Inserito";
        }
    }
    
    @GetMapping("/getAllOrders")
    public String getAllOrders() throws SQLException {
        H2Ordine h2Ordine = new H2Ordine();
        List<Ordine> ordini = h2Ordine.getAllOrders();
        return ordini.toString();
    }
    
    
    
}
