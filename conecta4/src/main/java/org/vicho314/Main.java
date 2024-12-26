package org.vicho314;

import java.util.Scanner;
//import java.util.Stack;
import org.vicho314.tda.*;

/**
 * Maneja el estado del juego y la entrada del usuario para poder jugar.
 */
public class Main {
    /**
     * Muestra un menú en pantalla con todas las opciones, leyendo la entrada del usuario.
     * @param args String (no usado)
     */
    public static void main(String[] args) {
        System.out.println("\nBienvenid@ a Conecta4!\n");
        Game game = new Game(null,null,null,0);
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        while(opcion != 6) {
            game.printOpciones();
            System.out.printf("\nEscoge una opción: ");
            opcion = scan.nextInt();
            switch(opcion) {
                case 1:
                    game.newGame(game.getP1(),game.getP2());
                    break;
                case 2:
                    game.boardGetState();
                    break;
                case 3:
                    game.realizarMovimientoWrapper();
                    break;
                case 4:
                    game.printStats();
                    break;
                case 5:
                    game.history();
                    break;
                case 6:
                    System.out.println("\nSaliendo...\n");
                    break;
                default:
                    System.out.println("\nError: Opción no reconocida, intente de nuevo.\n");
                    break;
            }
        }
    }
}