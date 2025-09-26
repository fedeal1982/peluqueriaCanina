package com.mycompany.peluqueriacanina.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Duenio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_duenio;
    private String nombre;
    private String celDuenio;
    private String direccion;
    private String codigoPostal;

    public Duenio() {
    }

    public Duenio(int id_duenio, String nombre, String celDuenio, String direccion, String codigoPostal) {
        this.id_duenio = id_duenio;
        this.nombre = nombre;
        this.celDuenio = celDuenio;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    // Getters y Setters existentes
    public int getId_duenio() {
        return id_duenio;
    }

    public void setId_duenio(int id_duenio) {
        this.id_duenio = id_duenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelDuenio() {
        return celDuenio;
    }

    public void setCelDuenio(String celDuenio) {
        this.celDuenio = celDuenio;
    }

    // Nuevos getters y setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}