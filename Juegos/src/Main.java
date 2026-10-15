import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        System.out.println("Que juego quieres jugar?");
        System.out.println("1- Piedra, Papel, Tijera, Lagarto, Spock");
        System.out.println("2- Piedras");
        System.out.println("3- Salir");

        do {
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Valor no valido, introduce un numero");
                sc.nextLine();
            }

            if (opcion < 1 || opcion > 3) {
                System.out.println("Opcion no valida, elige 1, 2 o 3");
            }

        } while (opcion < 1 || opcion > 3);

        // llamamos al juego elegido anteriormente o salimos
        if (opcion == 1) {
            PiedraPapelTijera.main(args);
        } else if (opcion == 2) {
            Piedras.main(args);
        } else {
            System.out.println("Saliendo... ");
        }
    }
}