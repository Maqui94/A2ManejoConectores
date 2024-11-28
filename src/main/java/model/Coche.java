package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Coche {
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

    public Coche( String matricula,String marca, String modelo, String color) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.color = color;
    }
    public void mostrarDatos(){
        System.out.println("id= " +id);
        System.out.println("marca= " +marca);
        System.out.println("modelo= " +modelo);
        System.out.println("matricula= " +matricula);
        System.out.println("color= " +color);
    }

}
