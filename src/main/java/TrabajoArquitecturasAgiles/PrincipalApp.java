import java.util.*;

public class PrincipalApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Caja> supermercado = new ArrayList<>();
        int opcion;
        boolean salir = false;

        do {
            /*System.out.println("¿Que desea hacer? \n"
                    + "1.Crear Caja \n"
                    + "2.Eliminar Caja \n"
                    + "3.Ingresar Dinero \n"
                    + "4.Retirar Dinero \n"
                    + "5.Transferir Dinero de caja \n"
                    + "6.Ver saldo cajas \n"
                    + "7.Imprimir mayor Caja \n"
                    + "8.Salir");*/

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    crearCaja(supermercado);
                    break;
                case 2:
                    eliminarCaja(supermercado);
                    break;
                case 3:
                    ingresarDinero(supermercado);
                    break;
                case 4:
                    retirarDinero(supermercado);
                    break;
                case 5:
                    transferirDinero(supermercado);
                    break;
                case 6:
                    verSaldoCajas(supermercado);
                    break;
                case 7:
                    imprimirMayorCaja(supermercado);
                    break;
                case 8:
                    salir = salirPrograma();
                    break;
            }

        } while (!salir);
    }

    // ---------------- MÉTODOS ----------------

    public static void crearCaja(ArrayList<Caja> supermercado) {
        System.out.println("Dame el nombre de la caja:");
        String id = sc.next();
        System.out.println("Dame el saldo de la caja:");
        int saldo = sc.nextInt();

        supermercado.add(new Caja(id, saldo));
    }

    public static void eliminarCaja(ArrayList<Caja> supermercado) {
        if (supermercado.isEmpty()) {
            System.out.println("No hay cajas disponibles");
            return;
        }

        int seleccion = seleccionarCajasSupermercado(supermercado);
        supermercado.remove(seleccion);
    }

    public static void ingresarDinero(ArrayList<Caja> supermercado) {
        if (supermercado.isEmpty()) {
            System.out.println("No hay cajas disponibles");
            return;
        }

        int seleccion = seleccionarCajasSupermercado(supermercado);
        System.out.println("Dinero a ingresar:");
        int dinero = sc.nextInt();

        supermercado.get(seleccion).ingresar(dinero);
    }

    public static void retirarDinero(ArrayList<Caja> supermercado) {
        if (supermercado.isEmpty()) {
            System.out.println("No hay cajas disponibles");
            return;
        }

        int seleccion = seleccionarCajasSupermercado(supermercado);
        System.out.println("Dinero a retirar:");
        int dinero = sc.nextInt();

        if (dinero <= supermercado.get(seleccion).getSaldo()) {
            supermercado.get(seleccion).retirar(dinero);
        } else {
            System.out.println("Operacion denegada. No hay saldo");
        }
    }

    public static void transferirDinero(ArrayList<Caja> supermercado) {
        if (supermercado.isEmpty()) {
            System.out.println("No hay cajas disponibles");
            return;
        }

        System.out.println("Elige la caja a retirar:");
        int seleccion = seleccionarCajasSupermercado(supermercado);

        System.out.println("Elige la caja a ingresar:");
        int seleccion2 = seleccionarCajasSupermercado(supermercado);

        System.out.println("Dinero a transferir:");
        int dinero = sc.nextInt();

        if (dinero <= supermercado.get(seleccion).getSaldo()) {
            supermercado.get(seleccion).retirar(dinero);
            supermercado.get(seleccion2).ingresar(dinero);
        } else {
            System.out.println("Operacion denegada. No hay saldo");
        }
    }

    public static void verSaldoCajas(ArrayList<Caja> supermercado) {
        if (supermercado.isEmpty()) {
            System.out.println("No hay cajas disponibles");
            return;
        }

        for (Caja caja : supermercado) {
            System.out.println(caja.toString());
        }
    }

    public static void imprimirMayorCaja(ArrayList<Caja> supermercado) {
        if (supermercado.isEmpty()) {
            System.out.println("No hay cajas disponibles");
            return;
        }

        int max = supermercado.get(0).getSaldo();

        for (Caja caja : supermercado) {
            if (caja.getSaldo() > max) {
                max = caja.getSaldo();
            }
        }

        System.out.println("La caja con mayor saldo es:");

        for (Caja caja : supermercado) {
            if (caja.getSaldo() == max) {
                System.out.println(caja.toString());
            }
        }
    }

    public static boolean salirPrograma() {
        System.out.println("Adios!!");
        return true;
    }

    public static int seleccionarCajasSupermercado(ArrayList<Caja> supermercado) {
        if (supermercado.isEmpty()) {
            System.out.println("No hay cajas disponibles");
            return -1;
        }

        for (int i = 0; i < supermercado.size(); i++) {
            System.out.println(i + ". " + supermercado.get(i).getId());
        }

        int seleccion = sc.nextInt();
        return seleccion;
    }
}