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
public class Enemigo extends Personaje {
    //Atributos
    
    //Métodos
    
    //Constructor
    public Enemigo(String nombre) {
        super(nombre);
        setFuerza(asignarValor());
        setAgilidad(asignarValor());
        
    }
    /**
     * 
     * @return Genera un valor aleatorio entre 1-20
     */
    public int asignarValor(){
    int valorRand = (int) (Math.random() * 20) + 1;
    return valorRand;
    
    }
    
    /**
     * 
     * @return características necesarias a saber del enemigo 
     */
    @Override
    public String toString() {
        return getNombre() + "\n "
               + "Fuerza: " + getFuerza() +"\n"
               + " Agilidad: " + getAgilidad() + "\n";
    }
    
    public  int tirarD15() {
        int tirada = (int) (Math.random() * 15) + 1;
        switch(tirada){
            case 1:
                System.out.println("¡El enemigo ha sacado un: " + tirada + "!, ¡Pífia!");
                    break;
            case 15:
                System.out.println("¡El enemigo ha sacado un: " + tirada + "!, ¡Golpe crítico!. ¡Eso seguro que duele!");
                    break;
            default:
                System.out.println("¡El enemigo ha sacado un: " + tirada + "!");
                break;
        }
        return tirada;
    }
    
    
}
