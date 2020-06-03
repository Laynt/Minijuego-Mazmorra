/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazmorrainfinita;

import java.util.Scanner;
import static mazmorrainfinita.MazmorraInfinita.parar;

/**
 *
 * @author mikim
 */
public abstract class Personaje {

    Scanner sca = new Scanner(System.in);
    //Atributos
    private String nombre;
    private int vida;
    private int fuerza;
    private int agilidad;
    private int decision;
    private int d1, d2, resultado1, resultado2, daño;

    //Métodos
    //Constructor
    public Personaje(String nombre) {
        this.nombre = nombre;
        vida = 100;
        fuerza = 1;
        agilidad = 4;
    }

    //Devuelve un valor aleatorio de 1-20
    public int tirarD20() {
        int tirada = (int) (Math.random() * 20) + 1;
        switch (tirada) {
            case 1:
                System.out.println("!Has sacado un: " + tirada + "! ¡Menudo desastre estás hecho!,¡Pífia¡");
                break;
            case 20:
                System.out.println("¡Has sacado un: " + tirada + "! ¡Golpe crítico!,¡Enséñales quien manda!");
                break;
            default:
                System.out.println("¡Has sacado un: " + tirada + "!");
                break;
        }
        return tirada;
    }

    /**
     *
     * @param fuerza_atacante fuerza del personaje
     * @param fuerza_defensor fuerza del enemigo
     * @param dado1 tirada de dado del personaje
     * @param dado2 tirada de dado del enemigo
     * @return devuelve true si ha sido exitosa y false si no. Además, si el
     * personaje saca un 1 o el enemigo un 20 pierde automáicamente y si el
     * personaje saca un 20 o el enemigo un 1 gana automáticamente
     */
    public boolean luchar(int fuerza_atacante, int fuerza_defensor, int dado1, int dado2) {
        boolean vence = false;
        if (dado1 == 1) {
            System.out.println("¿¡Enserio?!, ¿no lo podías haber hecho algo mejor?");
            return vence;
        }
        if (dado1 == 20) {
            vence = true;
            System.out.println("¡Le has cortado la cabeza!,¡Increible!");
            return vence;
        }
        if (dado2 == 1) {
            vence = true;
            System.out.println("El enemigo ha fallado su ataque,¡menuda suerte!");
            return vence;
        }
        if (dado2 == 20) {
            System.out.println("¡Dile adiós a uno de tus dedos!");
            return vence;
        }

        int ataque = fuerza_atacante * dado1;
        int defensa = fuerza_defensor * dado2;

        if (ataque > defensa) {
            System.out.println("¡Le has vencido!");
            vence = true;
        } else {
            System.out.println("¡Te ha hecho daño!");
        }

        return vence;
    }

    /**
     *
     * @param agilidad_atacante agilidad del personaje
     * @param agilidad_defensor agilidad del enemio
     * @param dado1 tirada del personaje
     * @param dado2 tirada del enemigo
     * @return true si consigue escapar, false si no lo consigue. Además si saca
     * un 20 pasa automáticamente y si saca un 1 no pasa automáticamente
     */
    public boolean escapar(int agilidad_atacante, int agilidad_defensor, int dado1, int dado2) {
        boolean pasa = false;
        if (dado1 == 1) {
            return pasa;
        }
        if (dado2 == 20) {
            pasa = true;
            return pasa;
        }

        int agi_atq = agilidad_atacante * dado1;
        int agi_def = agilidad_defensor * dado2;

        if (agi_atq > agi_def) {
            
            pasa = true;
        } 

        return pasa;
    }

    public void combate(Personaje personaje, Enemigo enemigo) {
        System.out.println("Te has topado con un/a " + enemigo.toString() + " \n"
                + "¿Qué quieres hacer?\n"
                + "1.Luchar\n"
                + "2.Intentar escapar a la siguiente sala");

        do {
            decision = sca.nextInt();
        } while (decision < 1 || decision > 2);
        
        //Luchas, si no ganas pierdes la diferencia
        switch (decision) {
            case 1:

                d1 = personaje.tirarD20();
                d2 = enemigo.tirarD15();
                resultado1 = d1 * personaje.getFuerza();
                resultado2 = d2 * enemigo.getFuerza();
                daño = resultado2 - resultado1;

                System.out.println("\nGolpeas con: \n"
                        + d1 + " (d20)  * " + personaje.getFuerza() + "(Fuerza)" + " = " + resultado1 + "\n");
                System.out.println("El enemigo golpea con: \n"
                        + d2 + " (d15)  * " + enemigo.getFuerza() + "(Fuerza)" + " = " + resultado2 + "\n");
                if (personaje.luchar(personaje.getFuerza(), enemigo.getFuerza(), d1, d2)) {

                    System.out.println("");
                    parar();
                    break;
                } else {
                    if (d1 == 1 || d2 == 20) {
                        daño = daño * 2;
                    }
                    System.out.println("El enemigo te quita " + daño + " puntos de vida");
                    personaje.setVida(personaje.getVida() - daño);

                    System.out.println("Tu vida ahora es: " + personaje.getVida() + "\n");
                    parar();
                    break;
                }

            case 2:
                int tiradaD20 = (int) (Math.random() * 20) + 1;
                int tiradaD15 = (int) (Math.random() * 15) + 1;
                if (personaje.escapar(personaje.getAgilidad(), enemigo.getAgilidad(), tiradaD20, tiradaD15)) {
                    if(tiradaD20 == 20 || tiradaD15 == 1){
                        System.out.println("Has conseguido pasar sin problemas");
                        break;
                    }
                    d1 = personaje.tirarD20();
                    d2 = enemigo.tirarD15();
                    resultado1 = d1 * personaje.getAgilidad();
                    resultado2 = d2 * enemigo.getAgilidad();
                    

                    System.out.println("\nIntentas escapar con: \n"
                            + d1 + " (d20)  * " + personaje.getAgilidad() + "(Agilidad)" + " = " + resultado1 + "\n");
                    System.out.println("El enemigo te intenta bloquear: \n"
                            + d2 + " (d15)  * " + enemigo.getAgilidad() + "(Agilidad)" + " = " + resultado2 + "\n");
                    System.out.println("¡Conseguiste escapar!");
                    parar();

                    break;
                } else {
                    int d3 = personaje.tirarD20();
                    int d4 = enemigo.tirarD15();
                    resultado1 = d3 * personaje.getAgilidad();
                    resultado2 = d4 * enemigo.getAgilidad();
                    

                    System.out.println("\nIntentas escapar con: \n"
                            + d3 + " (d20)  * " + personaje.getAgilidad() + "(Agilidad)" + " = " + resultado1 + "\n");
                    System.out.println("El enemigo te intenta bloquear: \n"
                            + d4 + " (d15)  * " + enemigo.getAgilidad() + "(Fuerza)" + " = " + resultado2 + "\n");
                    System.out.println("Conseguiste pasar, pero fuiste herido");
                    System.out.println("\nPierdes 40 de vida\n");
                    personaje.setVida(personaje.getVida() - 40);

                    System.out.println("Tu vida ahora es: " + personaje.getVida());
                    parar();

                    break;
                }
        }

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getAgilidad() {
        return agilidad;
    }

    @Override
    public String toString() {
        return "Personaje{" + "nombre=" + nombre + ", vida=" + vida + ", fuerza=" + fuerza + ", agilidad=" + agilidad + '}';
    }

}
