/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazmorrainfinita;

import static mazmorrainfinita.MazmorraInfinita.parar;

/**
 *
 * @author mikim
 */
public class Mago extends Personaje {

    //Atributos
    private String[] hechizos = {"Llamas etereas", "Congelar", "Ruptura Mental"};
    private int decision;
    private int d1, d2, resultado1, resultado2, daño, hechizo;
    

    //Métodos
    //Constructor
    public Mago(String nombre) {
        super(nombre);
        setVida(getVida() - 10);
        setAgilidad(getAgilidad() + 1);

    }
    /**
     * Fase de combate a base de hechizos
     * @param conjuro hechizo que va a ser lanzado
     * @param malvado enemigo
     * @param numero será el contador de habitaones para indicar el efecto que tendrá el hechizo congelar
     */
    public void lanzarHechizo(String conjuro, Enemigo malvado, int numero) {

        switch (conjuro) {
            case "Llamas etereas":
                System.out.println("Has calcinado al enemigo a un alto precio");
                setVida(getVida() - 50);
                malvado.setFuerza(0);
                break;

            case "Congelar":
                if (numero % 2 == 0) {
                    System.out.printf("El enemigo ha quedado petrificado, puedes pasar");
                } else {
                    System.out.println("Al no ser una habitación par, tu hechizo no funciona. Te ves obligado a usar la"
                            + "fuerza");
                   
                    
                }
            case "Ruptura Mental":
                System.out.println(malvado.getNombre() + " no puede hacer nada."
                                   + " Te has colado en su mente a cambio de un poco de vida"
                                   + " y reduces sus capacidades físicas a la mitad");
                setVida(getVida() - 10);                   
                    //Código de un combate pero fuerza del enemigo/2             
        }
    }
    
    public void combate(Personaje personaje, Enemigo enemigo, int numero) {
        System.out.println("Te has topado con un/a " + enemigo.toString() + " \n"
                + "¿Qué quieres hacer?\n"
                + "1.Luchar\n"
                + "2.Intentar escapar a la siguiente sala\n"
                + "3.Lanzar hechizo");

        do {
            decision = sca.nextInt();
        } while (decision < 1 || decision > 3);

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
            case 3:
                    System.out.println("¿Qué hechizo quieres lanzar?\n");
                    System.out.println("1.Llamas etéreas(Convierte la fuerza del enemigo a 0, pero pierdes 50 de vida)\n"
                                     + "2.Congelar(Si la habitación es par, congela al enemigo y te permite pasar automáticamente)\n"
                                     + "3.Ruptura mental(Pierdes 10 de vida a cambio de reducir la fuerza del enemigo a la mitad)\n");
                    do{
                    hechizo = sca.nextInt();
                    }while(hechizo < 1 || hechizo > 3);
                    switch(hechizo){
                        case 1:
                            lanzarHechizo("Llamas etereas", enemigo , numero);
                            break;
                        case 2:
                            lanzarHechizo("Congelar", enemigo , numero);
                            break;
                        case 3:
                            lanzarHechizo("Ruptura mental", enemigo , numero);
                            break;
                    }
        
        }

    }

}
