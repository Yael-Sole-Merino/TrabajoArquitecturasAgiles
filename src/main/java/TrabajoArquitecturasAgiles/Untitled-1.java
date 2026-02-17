import java.util.*;

public class PrincipalApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Caja> supermercado = new ArrayList<>();
        int opcion;
        boolean salir = false;

        do {
            System.out.println("¿Que desea hacer? \n"
                    + "1.Crear Caja \n"
                    + "2.Eliminar Caja \n"
                    + "3.Ingresar Dinero \n"
                    + "4.Retirar Dinero \n"
                    + "5.Transferir Dinero de caja \n"
                    + "6.Ver saldo cajas \n"
                    + "7.Imprimir mayor Caja \n"
                    + "8.Salir");

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
