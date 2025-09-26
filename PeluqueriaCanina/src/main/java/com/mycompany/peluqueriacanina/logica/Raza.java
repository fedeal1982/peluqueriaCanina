package com.mycompany.peluqueriacanina.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Raza implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_raza;
    private String nombre_raza;
    private String descripcion;
    
    @OneToMany(mappedBy = "raza")
    private List<Mascota> mascotas;

    public Raza() {
    }

    public Raza(int id_raza, String nombre_raza, String descripcion) {
        this.id_raza = id_raza;
        this.nombre_raza = nombre_raza;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId_raza() {
        return id_raza;
    }

    public void setId_raza(int id_raza) {
        this.id_raza = id_raza;
    }

    public String getNombre_raza() {
        return nombre_raza;
    }

    public void setNombre_raza(String nombre_raza) {
        this.nombre_raza = nombre_raza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
    @Override
    public String toString() {
        return this.nombre_raza; // Esto es lo que se mostrará en el ComboBox
}
}
