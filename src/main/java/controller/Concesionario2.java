package controller;

import DAO.CocheDAO;
import DAO.GestionDAO;
import DAO.PasajeroDAO;
import model.Coche;

import java.sql.SQLException;
import java.util.Scanner;

public class Concesionario2 {
    private CocheDAO cocheDAO;
    private PasajeroDAO pasajeroDAO;
    private GestionDAO gestionDAO;

    public Concesionario2(){
        cocheDAO=new CocheDAO();
        pasajeroDAO=new PasajeroDAO();
        gestionDAO=new GestionDAO();
    }
    public void agregarCoche(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduce tu  matricula");
        String matricula= scanner.next();
        //si me dicen una marca y ya tengo 8 coches de esa marca, no lo quiero comprar
        try {
                System.out.println("Introduce tu marca");
                String marca= scanner.next();
                System.out.println("Introduce modelo");
                String modelo= scanner.next();
                System.out.println("Introduce color");
                String color= scanner.next();

            cocheDAO.añadirCoche(new Coche(matricula,marca,modelo,color));
                System.out.println("Coche agregado con exito");
        } catch (SQLException e) {
            System.out.println("La base de datos no esta disponible, quieres guardar en fichero");
            //guardar el dato en un fichero ->DAO
        }
    }
    public void eliminarCoche(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduce el Id del coche para borrar");
        int id=scanner.nextInt();
        cocheDAO.borrarCoche(id);
    }
}
