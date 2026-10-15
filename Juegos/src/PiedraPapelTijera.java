import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

//JUEGO ELOY


public class PiedraPapelTijera {


    static String[] nombres = {"", "Piedra", "Papel", "Tijera", "Lagarto", "Spock"};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();


        int jugador1 = 0;
        int jugador2 = 0;


        System.out.println("Contra quien quieres jugar? ");
        System.out.println("1- Contra la CPU");
        System.out.println("2- Contra otro jugador");
        System.out.println("3- Salir");
        int modo = leerNumero(sc);


        // valores validos solo 1, 2 y 3 si no se vuelve a pedir
        while (modo < 1 || modo > 3) {
            System.out.println("Opcion no valida");
            System.out.println("1- Contra la CPU");
            System.out.println("2- Contra otro jugador");
            System.out.println("3- Salir");
            modo = leerNumero(sc);
        }


        if (modo == 3) {
            System.out.println("Saliendo...");
            return;
        }


        System.out.println("Cuantas rondas quieres jugar? ");
        int rondas = leerNumero(sc);


        System.out.println("\nCOMIENZA EL JUEGO");
        esperar(500);


        for (int i = 0; i < rondas; i++) {
            System.out.println("RONDA " + (i + 1));


            int opc1 = pedirJugada(sc, "jugador 1");
            int opc2;


            if (modo == 1) {
                opc2 = jugadaCpu(random);
                System.out.println("\nEl jugador ha sacado: " + nombres[opc1]);
                esperar(500);
                System.out.println("La CPU ha sacado: " + nombres[opc2]);
            } else {
                opc2 = pedirJugada(sc, "jugador 2");
                System.out.println("\nEl jugador 1 ha sacado: " + nombres[opc1]);
                esperar(500);
                System.out.println("El jugador 2 ha sacado: " + nombres[opc2]);
            }


            esperar(500);
            mostrarMotivo(opc1, opc2);


            esperar(500);
            int ganador = calcularGanador(opc1, opc2);


            if (ganador == 0) {
                System.out.println("Ambos habeis sacado lo mismo! Empate");
            } else if (ganador == 1) {
                System.out.println(modo == 1 ? "El jugador gana!" : "El jugador 1 gana!");
                jugador1++;
            } else {
                System.out.println(modo == 1 ? "La CPU gana!" : "El jugador 2 gana!");
                jugador2++;
            }


            esperar(500);
        }


        System.out.println("\nEl jugador 1 ha ganado " + jugador1 + " ronda/rondas");
        esperar(500);
        System.out.println("\nEl " + (modo == 1 ? "cpu" : "jugador 2") + " ha ganado " + jugador2 + " ronda/rondas");
        esperar(500);


        if (jugador1 == jugador2) {
            System.out.println("Ambos han ganado las mismas rondas! Empate");
        } else if (jugador1 > jugador2) {
            System.out.println("El jugador 1 ha ganado más rondas!");
        } else {
            System.out.println((modo == 1 ? "La CPU" : "El jugador 2") + " ha ganado más rondas!");
        }


    }




    // Pide una jugada por el teclado o las reglas introduciendo el 6
    public static int pedirJugada(Scanner sc, String nombreJugador) {
        int opc = 0;
        do {
            System.out.println("\nQue quiere sacar el " + nombreJugador + "? ");
            System.out.println("1- Piedra");
            System.out.println("2- Papel");
            System.out.println("3- Tijera");
            System.out.println("4- Lagarto");
            System.out.println("5- Spock");
            System.out.println("6- Reglas");


            opc = leerNumero(sc);


            if (opc == 6) {
                mostrarReglas();
            } else if (opc < 1 || opc > 6) {
                System.out.println("Opcion no valida");
            }


        } while (opc < 1 || opc > 5);


        return opc;
    }


    // Lee el valor introducido y si no es un numero lo vuelve a pedir
    public static int leerNumero(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Valor no valido, introduce un numero");
                sc.nextLine();
            }
        }
    }


    // pequeño tiempo entre mensaje y mensaje
    public static void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Jugada aleatoria de la cpu
    public static int jugadaCpu(Random random) {
        return random.nextInt(5) + 1;
    }


    // Mostrar reglas
    public static void mostrarReglas() {
        System.out.println("\nREGLAS");
        System.out.println(" Tijera  -> corta a papel y decapita a lagarto");
        System.out.println(" Papel   -> envuelve a piedra y desautoriza a Spock");
        System.out.println(" Piedra  -> aplasta a lagarto y aplasta a tijera");
        System.out.println(" Lagarto -> envenena a Spock y se come el papel");
        System.out.println(" Spock   -> rompe la tijera y vaporiza la piedra");
    }


    // Devuelve 0 si hay empate, 1 si gana opc1, 2 si gana opc2
    // 1=PIEDRA 2=PAPEL 3=TIJERA 4=LAGARTO 5=SPOCK
    public static int calcularGanador(int opc1, int opc2) {
        if (opc1 == opc2) {
            return 0;
        }


        boolean gana1 = false;


        if (opc1 == 1 && (opc2 == 3 || opc2 == 4)) {
            gana1 = true;   // Piedra > tijera e lagarto
        } else if (opc1 == 2 && (opc2 == 1 || opc2 == 5)) {
            gana1 = true;   // Papel > piedra e Spock
        } else if (opc1 == 3 && (opc2 == 2 || opc2 == 4)) {
            gana1 = true;   // Tijera > papel e lagarto
        } else if (opc1 == 4 && (opc2 == 5 || opc2 == 2)) {
            gana1 = true;   // Lagarto > Spock e papel
        } else if (opc1 == 5 && (opc2 == 3 || opc2 == 1)) {
            gana1 = true;   // Spock > tijera e piedra
        }


        return gana1 ? 1 : 2;
    }


    // Muestra una frase del ganador dependiendo de opc1 y opc2
    public static void mostrarMotivo(int opc1, int opc2) {
        if ((opc1 == 3 && opc2 == 2) || (opc1 == 2 && opc2 == 3)) {
            System.out.println("La tijera corta el papel");
        } else if ((opc1 == 3 && opc2 == 4) || (opc1 == 4 && opc2 == 3)) {
            System.out.println("La tijera decapita al lagarto");
        } else if ((opc1 == 2 && opc2 == 1) || (opc1 == 1 && opc2 == 2)) {
            System.out.println("El papel envuelve la piedra");
        } else if ((opc1 == 2 && opc2 == 5) || (opc1 == 5 && opc2 == 2)) {
            System.out.println("El papel desautoriza a Spock");
        } else if ((opc1 == 1 && opc2 == 4) || (opc1 == 4 && opc2 == 1)) {
            System.out.println("La piedra aplasta al lagarto");
        } else if ((opc1 == 1 && opc2 == 3) || (opc1 == 3 && opc2 == 1)) {
            System.out.println("La piedra rompe las tijeras");
        } else if ((opc1 == 4 && opc2 == 5) || (opc1 == 5 && opc2 == 4)) {
            System.out.println("El lagarto envenena a Spock");
        } else if ((opc1 == 4 && opc2 == 2) || (opc1 == 2 && opc2 == 4)) {
            System.out.println("El lagarto se come el papel");
        } else if ((opc1 == 5 && opc2 == 3) || (opc1 == 3 && opc2 == 5)) {
            System.out.println("Spock rompe las tijeras");
        } else if ((opc1 == 5 && opc2 == 1) || (opc1 == 1 && opc2 == 5)) {
            System.out.println("Spock vaporiza la piedra");
        }
    }


}