/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazmorrainfinita;

/**
 *
 * @author mikim
 */
public class Habitacion {

    //Métodos
    public void escogerSala(){
    int salas = (int) (Math.random() * 3) + 1;
    switch (salas) {
        case 1:
            System.out.println("No hay nada");
            break;

        case 2:
            System.out.println("¡Aparece un enemigo!");

            break;

        case 3:
            System.out.println("¡Encontraste una pocion!");
            
            break;

    }
    }
    
}
