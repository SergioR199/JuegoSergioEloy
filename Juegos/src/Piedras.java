import java.util.*;

// JUEGO SERGIO


public class Piedras {
    // Scanner general
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int op = 0;

        /*Menu para seleccionar modo de juego o exit con comprobaciones de que los
        inputs sean válidos tanto en tipo como por valor.
         */
        do {
            try {
                System.out.println("Quieres jugar contra la IA o contra otra persona" +
                        "( 1 - IA | 2 - Jugador | 3 - Salir): ");
                op = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Por favor introduce un número (1 a 3).");
                sc.nextLine();
            }

            if (op < 1 || op > 3) {
                System.out.println("Introduce una opción válida(1-3)");
            }

            switch (op) {
                case 1:
                    jugarIA();
                    break;

                case 2:
                    jugar2Jugadores();
                    break;

                case 3:
                    break;
            }

        } while (op != 3);
    }

    public static void jugarIA() {
        //variables
        int piedrasJugador = 0;
        int apuestaJugador = 0;
        int piedrasIA = 0;
        int apuestaIA = 0;
        int piedrasTotales = 0;
        int ganadoJugador = 0;
        int ganadoIA = 0;

        int rondas = pedirRondas(sc);

        //bucle para realizar número de rondas introducidas
        for (int i = 0; i < rondas; i++) {
            mostrarRonda(i);

            //valores del jugador (por teclado)
            System.out.println("----- JUGADOR -----");
            piedrasJugador = pedirPiedras(sc);
            apuestaJugador = pedirApuesta(sc, piedrasJugador);

            //valores de la IA
            piedrasIA = (int) (Math.random() * 4);
            //comprobación de que la IA no haga una apuesta imposible
            apuestaIA = piedrasIA + (int) (Math.random() * 4);

            piedrasTotales = piedrasJugador + piedrasIA;

            //resumen de jugadas
            System.out.println(" La IA guardó: " + piedrasIA + " piedras.");
            System.out.println(" Tu apostaste: " + apuestaJugador + " | La IA apostó: " + apuestaIA);
            System.out.println(" El total real de piedras es: " + piedrasTotales);

            //comprobación y muestra de resultados por pantalla (incluye contador de rondas ganadas)
            if (apuestaJugador == piedrasTotales && apuestaIA == piedrasTotales) {
                System.out.println("El jugador y la IA han empatado. Las piedras totales eran: " + piedrasTotales);
            } else if (apuestaJugador == piedrasTotales) {
                System.out.println("El Jugador ganó la ronda.  Las piedras totales eran: " + piedrasTotales);
                ganadoJugador++;
            } else if (apuestaIA == piedrasTotales) {
                System.out.println("La IA ganó la ronda. Las piedras totales eran: " + piedrasTotales);
                ganadoIA++;
            } else {
                System.out.println("Nadie acertó en esta ronda. Las piedras totales eran: " + piedrasTotales);
            }
        }

        //resumen de fin de ronda
        System.out.println("----- FIN DE LA PARTIDA -----");
        System.out.println("Rondas ganadas por ti: " + ganadoJugador);
        System.out.println("Rondas ganadas por la IA: " + ganadoIA);

    }

    public static void jugar2Jugadores() {
        int piedrasJugador1 = 0;
        int apuestaJugador1 = 0;
        int piedrasJugador2 = 0;
        int apuestaJugador2 = 0;
        int piedrasTotales = 0;
        int ganadoJugador1 = 0;
        int ganadoJugador2 = 0;

        int rondas = pedirRondas(sc);

        for (int i = 0; i < rondas; i++) {
            mostrarRonda(i);

            //valores jugador 1
            System.out.println("----- JUGADOR 1 -----");
            piedrasJugador1 = pedirPiedras(sc);
            apuestaJugador1 = pedirApuesta(sc, piedrasJugador1);

            //creamos espacio vacía para que el jugador 2 no vea elecciones del 1
            System.out.println("\n\n\n\n\n\n");
            //valores jugador 2
            System.out.println("----- JUGADOR 2 -----");
            piedrasJugador2 = pedirPiedras(sc);
            apuestaJugador2 = pedirApuesta(sc, piedrasJugador2);

            piedrasTotales = piedrasJugador1 + piedrasJugador2;

            //resumen de jugadas
            System.out.println("El Jugador 1 se guardó " + piedrasJugador1 + " y apostó " + apuestaJugador1);
            System.out.println("El Jugador 2 se guardó " + piedrasJugador2 + " y apostó " + apuestaJugador2);
            System.out.println("El total real de piedras es: " + piedrasTotales);

            //comprobación y muestra de resultados por pantalla (incluye contador de rondas ganadas)
            if (apuestaJugador1 == piedrasTotales && apuestaJugador2 == piedrasTotales) {
                System.out.println("Ambos jugadores han ahcertado.");
            } else if (apuestaJugador1 == piedrasTotales) {
                System.out.println("El jugador 1 ganó esta ronda.");
                ganadoJugador1++;
            } else if (apuestaJugador2 == piedrasTotales) {
                System.out.println("El jugador 2 ganó esta ronda.");
                ganadoJugador2++;
            } else {
                System.out.println("Nadie acertó en esta ronda.");
            }
            System.out.println("\n");
        }

        //resumen de fin de ronda
        System.out.println("----- FIN DE LA PARTIDA -----");
        System.out.println("Rondas ganadas por Jugador1: " + ganadoJugador1);
        System.out.println("Rondas ganadas por Jugador2: " + ganadoJugador2);

    }

    //metodo para pedir las piedras por teclado a un jugador
    public static int pedirPiedras(Scanner sc) {

        int piedrasJugador = 0;

        /*Pedida de valores por pantalla con comprobación de que sea tipo
         * adecuado y en el rango aceptado */
        do {
            try {
                System.out.println(" Cuantas piedras quieres guardar (0 a 3): ");
                piedrasJugador = sc.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Has introducido un valor no válido. Introduce un número (0 a 3).");
                sc.nextLine();
            }
            if (piedrasJugador < 0 || piedrasJugador > 3) {
                System.out.println("Has introducido un número de piedras inválido (0 a 3).");
            }
        } while (piedrasJugador < 0 || piedrasJugador > 3);

        return piedrasJugador;
    }

    //metodo para pedir la apuesta por teclado a un jugador
    public static int pedirApuesta(Scanner sc, int piedrasJugador) {

        int apuestaJugador = 0;

        /*Pedida de valores por pantalla con comprobación de que sea tipo
         * adecuado y en el rango aceptado */
        do {
            try {
                System.out.println(" Cúal es tu apuesta al total de piedras (mínimo " + piedrasJugador + ", máximo " + (piedrasJugador + 3) + "): ");
                apuestaJugador = sc.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Has introcido un valor nó válido. Introduce uno (mínimo " + piedrasJugador +  ", máximo"  + (piedrasJugador + 3) + "):");
                sc.nextLine();
            }
            if (apuestaJugador < piedrasJugador || apuestaJugador > 3 + piedrasJugador) {
                System.out.println("Has introducido una apuesta imposible. Vuelve a intentarlo.");
            }
        //comprobación de que el jugador no haga una apuesta imposible
        } while (apuestaJugador < piedrasJugador || apuestaJugador > 3 + piedrasJugador);

        return apuestaJugador;
    }

    //metodo para pedir las rondas a jugar por teclado
    public static int pedirRondas(Scanner sc) {

        int rondas = 0;

        /*Pedida de valores por pantalla con comprobación de que sea tipo
        * adecuado y en el rango aceptado */
        do {
            try {
                System.out.println("Introduce el número de rondas a jugar(máx. 10): ");
                rondas = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Por favor introduce un número (1 a 10).");
                sc.nextLine();
            }

            if (rondas < 1 || rondas > 10) {
                System.out.println("Has introducido un número de rondas no válido.");
            }

        } while (rondas < 1 || rondas > 10);

        return rondas;
    }

    //metodo que muestra la ronda actual
    public static void mostrarRonda(int i){
        System.out.println(" ----- RONDA " + (i + 1) + " -----");
        System.out.println("\n");
    }

}

