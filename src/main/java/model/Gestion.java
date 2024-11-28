package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gestion {
    private int id, id_coche,id_pasajero;

    public Gestion(int id_coche, int id_pasajero) {
        this.id_coche = id_coche;
        this.id_pasajero = id_pasajero;
    }
}
