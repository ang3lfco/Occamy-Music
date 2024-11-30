/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.precarga;

import daos.seed.DataSeeder;

/**
 *
 * @author martinez
 */
public class Precargador {
    private final DataSeeder cargador;
    
    public Precargador(){
        this.cargador = new DataSeeder();
    }
    
    public void insercionMasiva(){
        cargador.insercionMasiva();
    }
}
