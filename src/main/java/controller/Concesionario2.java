package controller;

import DAO.CocheDAO;
import DAO.GestionDAO;
import DAO.PasajeroDAO;

public class Concesionario2 {
    private CocheDAO cocheDAO;
    private PasajeroDAO pasajeroDAO;
    private GestionDAO gestionDAO;

    public Concesionario2(){
        cocheDAO=new CocheDAO();
        pasajeroDAO=new PasajeroDAO();
        gestionDAO=new GestionDAO();
    }

}
