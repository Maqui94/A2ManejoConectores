package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pasajero {
    private int id;
    private String nombre;
    private int edad;
    private double peso;

    public Pasajero(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }
    public void mostrarDatos(){
        System.out.println("id= " +id);
        System.out.println("nombre= " +nombre);
        System.out.println("edad= " +edad);
        System.out.println("peso= " +peso);
    }
}
