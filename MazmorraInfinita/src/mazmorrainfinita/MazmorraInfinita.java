/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazmorrainfinita;

import java.util.Scanner;

/**
 *
 * @author mikim
 */
public class MazmorraInfinita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int clase;
        int contador = 0;
        int decision;
        int valorPocion;

        Personaje p1;
        Guerrero g1;
        Mago m1;
        Enemigo e1 = new Enemigo("Araña");
        Enemigo e2 = new Enemigo("Esqueleto");
        Enemigo e3 = new Enemigo("Guardian oscuro");
        Enemigo e4 = new Enemigo("Brujo");
        Enemigo[] tipoEnemigo = {e1, e2, e3, e4};

        System.out.println("Bienvenido a la mazmorra infinita\n ¿Cuántas salas serás capaz de sobrevivir?");

        System.out.println("¿Cómo se va a llamar tu personaje?");
        String nombre = sca.nextLine();
        do {
            System.out.println("Selecciona la clase de tu personaje: "
                    + "\n 1.Guerrero: Tiene un poco más de vida y un poco más de fuerza"
                    + "\n 2.Mago: Maneja diferentes hechizos poderosos,pero tienes menos vida"
                    + "\n 3.Ladrón: Tiene mayor facilidad para escapar de als habitaciones");

            clase = sca.nextInt();
        } while (clase > 3 || clase < 1);

        switch (clase) {
            case 1:
                g1 = new Guerrero(nombre);
                
                for (contador = 0; contador < 100;) {

                    contador += 1;
                    System.out.println("Habitación: " + contador + "\n");
                    int habitacion = (int) (Math.random() * 4) ;
                    switch (habitacion) {
                        case 1: //Encuentra enemigo
                            int enemigoRand = (int) (Math.random() * 3) + 1;
                            g1.combate(g1, tipoEnemigo[enemigoRand]);
                            if (g1.getVida() <= 0) {
                                            System.out.println("¡HASTA AQUÍ LLEGÓ TU VIAJE!, " + g1.getNombre() + " MURIÓ...");
                                            return;
                                        }
                        case 2: //Aumenta vida
                            valorPocion = (int) (Math.random() * 20) + 1;
                            System.out.println("¡Has encontrado una poción de: " + valorPocion + " de vida");
                            g1.setVida(g1.getVida() + valorPocion);
                            System.out.println("Vida actual: " + g1.getVida());
                            parar();
                            break;
                        default:
                            int fraseRand = (int) (Math.random() * 5);
                            String[] frasesVacia = {"Esta habitación parece estar vacia",
                                "Nada por aquí",
                                "Nada",
                                "Ni monstruos, ni pociones, sólo el viento",
                                "El frío recorre tu cuerpo, no hay nada"};
                            System.out.println(frasesVacia[fraseRand] + "\n");
                            parar();
                            break;

                    }
                }

                break;
            case 2:
                m1 = new Mago(nombre);
                for (contador = 0; contador < 100;) {

                    contador += 1;
                    System.out.println("Habitación: " + contador + "\n");
                    int habitacion = (int) (Math.random() * 4) ;
                    switch (habitacion) {
                        case 1: //Encuentra enemigo
                            int enemigoRand = (int) (Math.random() * 3) + 1;
                            m1.combate(m1, tipoEnemigo[enemigoRand]);
                            if (m1.getVida() <= 0) {
                                            System.out.println("¡HASTA AQUÍ LLEGÓ TU VIAJE!, " + m1.getNombre() + " MURIÓ...");
                                            return;
                                        }
                        case 2: //Aumenta vida
                            valorPocion = (int) (Math.random() * 20) + 1;
                            System.out.println("¡Has encontrado una poción de: " + valorPocion + " de vida");
                            m1.setVida(m1.getVida() + valorPocion);
                            System.out.println("Vida actual: " + m1.getVida());
                            parar();
                            break;
                        default:
                            int fraseRand = (int) (Math.random() * 5);
                            String[] frasesVacia = {"Esta habitación parece estar vacia",
                                "Nada por aquí",
                                "Nada",
                                "Ni monstruos, ni pociones, sólo el viento",
                                "El frío recorre tu cuerpo, no hay nada"};
                            System.out.println(frasesVacia[fraseRand] + "\n");
                            parar();
                            break;

                    }
                }
                break;
            case 3:
                //L1 = new Ladron(nombre);
                break;
            default:
                System.out.println("Número no válido, por favor seleccione 1,2 o 3\n\n");
                break;
        }

    }

    public static void parar() {
        Scanner sca = new Scanner(System.in);
        String parar;
        do {
            System.out.println("Pulsa enter para continuar");
            parar = sca.nextLine();
        } while (!parar.equalsIgnoreCase(""));
    }
}
