package controller;

import DAO.CocheDAO;
import DAO.GestionDAO;
import DAO.PasajeroDAO;
import model.Coche;
import model.Gestion;
import model.Pasajero;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Concesionario2 {
    private CocheDAO cocheDAO;
    private PasajeroDAO pasajeroDAO;
    private GestionDAO gestionDAO;

    public Concesionario2() {
        cocheDAO = new CocheDAO();
        pasajeroDAO = new PasajeroDAO();
        gestionDAO = new GestionDAO();
    }

    public void agregarCoche() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce tu  matricula");
        String matricula = scanner.next();
        //si me dicen una marca y ya tengo 8 coches de esa marca, no lo quiero comprar
        try {
            System.out.println("Introduce tu marca");
            String marca = scanner.next();
            System.out.println("Introduce modelo");
            String modelo = scanner.next();
            System.out.println("Introduce color");
            String color = scanner.next();

            cocheDAO.añadirCoche(new Coche(matricula, marca, modelo, color));
            System.out.println("Coche agregado con exito");
        } catch (SQLException e) {
            System.out.println("La base de datos no esta disponible, quieres guardar en fichero");
            //guardar el dato en un fichero ->DAO
        }
    }

    public void eliminarCoche() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el Id del coche para borrar");
        int id = scanner.nextInt();
        cocheDAO.borrarCoche(id);
    }

    public void consultarCoche() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introducir el Id del coche");
        int id = scanner.nextInt();
        cocheDAO.consultarCoche(id).mostrarDatos();
    }

    public void modificarCoche() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID del coche a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nueva matricula: ");
        String matricula = scanner.nextLine();
        System.out.print("Nuevo marca: ");
        String marca = scanner.nextLine();
        System.out.print("Nuevo modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Nuevo color ");
        String color = scanner.nextLine();
        Coche coche = new Coche(id, matricula, marca, modelo, color);
        cocheDAO.modificarCoche(coche);
        System.out.println("Coche modificado correctamente.");
    }

    public void mostrarFlota() {
        ArrayList<Coche> coches = cocheDAO.flota();
        for (Coche coche : coches) {
            System.out.printf(" ID: %d, Matrícula: %s, Marca: %s, Modelo: %s, Color: %s%n ",
                    coche.getId(), coche.getMatricula(), coche.getMarca(), coche.getModelo(), coche.getColor());
        }
    }

    public void menuCoche() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("===== MENÚ COCHE =====");
            System.out.println("1. Agregar Coche");
            System.out.println("2. Eliminar Coche");
            System.out.println("3. Consultar Coche");
            System.out.println("4. Modificar Coche");
            System.out.println("5. Mostrar Flota");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            switch (opcion) {
                case 1:
                    agregarCoche();
                    break;
                case 2:
                    eliminarCoche();
                    break;
                case 3:
                    consultarCoche();
                    break;
                case 4:
                    modificarCoche();
                    break;
                case 5:
                    mostrarFlota();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    //PASAJEROS
    public void agregarPasajero() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Introduce un pasajero");
            String nombre = scanner.next();
            System.out.println("Introduce su edad");
            int edad = scanner.nextInt();
            System.out.println("Introduce su peso");
            double peso = scanner.nextDouble();

            pasajeroDAO.añadirPasajero(new Pasajero(nombre, edad, peso));
            System.out.println("Pasajero agregado con exito");
        } catch (SQLException e) {
            System.out.println("La base de datos no esta disponible, quieres guardar en fichero");

        }
    }

    public void eliminarPasajero() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el Id del pasajero para borrar");
        int id = scanner.nextInt();
        pasajeroDAO.borrarPasajero(id);
    }

    public void consultarPasajero() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introducir el Id del pasajero");
        int id = scanner.nextInt();
        pasajeroDAO.consultarPasajero(id).mostrarDatos();
    }

    public void mostrarPasajerosTotales() {
        ArrayList<Pasajero> pasajeros = pasajeroDAO.listaPasajeros();
        for (Pasajero pasajero : pasajeros) {
            System.out.printf(" ID: %d, nombre: %s, edad: %s, peso: %s ",
                    pasajero.getId(), pasajero.getNombre(), pasajero.getEdad(), pasajero.getPeso());
        }
    }

    public void agregarPasajeroACoche() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Introduce id del coche al que va");
            int id_coche = scanner.nextInt();
            System.out.println("Introduce id de un pasajero");
            int id_pasajero = scanner.nextInt();
            gestionDAO.añadirPasajeroACoche(new Gestion(id_coche, id_pasajero));
            System.out.println("Pasajero agregado con exito");

        } catch (SQLException e) {
            System.out.println("error con la DB");
        }
    }

    public void eliminarPasajeroCoche() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el Id del coche");
        int id_coche = scanner.nextInt();
        System.out.println("Introduce el Id del pasajero a borrar");
        int id_pasajero = scanner.nextInt();
        gestionDAO.quitarPasajeroDeCoche(id_coche, id_pasajero);
    }

    public void mostrarPasajerosEnCoche() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el Id del coche");
        int idCoche= scanner.nextInt();
        int pasajeros = gestionDAO.PasajerosEnCoche(idCoche);
        System.out.println("el coche con id "+idCoche+" tiene "+pasajeros);
        }

        public void menuPasajero() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("===== MENÚ PASAJEROS =====");
            System.out.println("1. Agregar Pasajero");
            System.out.println("2. Eliminar Pasajero");
            System.out.println("3. Consultar Pasajero");
            System.out.println("4. Mostrar Pasajeros Totales");
            System.out.println("5. Agregar Pasajero a Coche");
            System.out.println("6. Eliminar Pasajero de Coche");
            System.out.println("7. Mostrar Pasajeros en Coche");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarPasajero();
                    break;
                case 2:

                 eliminarPasajero();
                    break;
                case 3:
                   consultarPasajero();
                    break;
                case 4:
                    mostrarPasajerosTotales();
                    break;
                case 5:
                    agregarPasajeroACoche();
                    break;
                case 6:
                    eliminarPasajeroCoche();
                    break;
                case 7:
                   mostrarPasajerosEnCoche();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

            System.out.println();
        } while (opcion != 0);
    }
    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Menú Coche");
            System.out.println("2. Menú Pasajero");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    menuCoche();
                    break;
                case 2:
                    menuPasajero();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }
}



