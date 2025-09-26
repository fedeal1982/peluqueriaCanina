package com.mycompany.peluqueriacanina.logica;

import com.mycompany.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void guardar(String nombreMasco, int idRaza, String color, String alergico, String atenEsp, String nombreDuenio, String celDuenio, String direccion, String codigoPostal, String observaciones) {
        
        // Crear el dueño con los nuevos campos
        Duenio duenio = new Duenio();
        duenio.setNombre(nombreDuenio);
        duenio.setCelDuenio(celDuenio);
        duenio.setDireccion(direccion);
        duenio.setCodigoPostal(codigoPostal);
        
        // Obtener la raza por ID
        Raza raza = controlPersis.traerRaza(idRaza);
        
        // Crear la mascota
        Mascota masco = new Mascota();
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setAtencion_especial(atenEsp);
        masco.setObservaciones(observaciones);
        masco.setUnduenio(duenio);
        
        controlPersis.guardar(duenio, masco);
    }
    
    public void crearRaza(String nombreRaza, String descripcion) {
        Raza raza = new Raza();
        raza.setNombre_raza(nombreRaza);
        raza.setDescripcion(descripcion);
        controlPersis.guardarRaza(raza);
    }

    public List<Mascota> traerMascotas() {
        return controlPersis.traerMascotas();
    }
    
    public List<Raza> traerRazas() {
        return controlPersis.traerRazas();
    }

    public void borrarMascota(int num_cliente) {
        controlPersis.borrarMascota(num_cliente);
    }

    public Mascota traerMascota(int num_cliente) {
        return controlPersis.traerMascota(num_cliente);
    }

    public void modificarMascota(Mascota masco, String nombreMasco, int idRaza, String color, String observaciones, String alergico, String atenEsp, String nombreDuenio, String celDuenio, String direccion, String codigoPostal) {
       
        // Obtener la raza
        Raza raza = controlPersis.traerRaza(idRaza);
        
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setObservaciones(observaciones);
        masco.setAlergico(alergico);
        masco.setAtencion_especial(atenEsp);
        
        controlPersis.modificarMascota(masco);
        
        // Modificar datos del dueño
        Duenio dueno = this.buscarDuenio(masco.getUnduenio().getId_duenio());
        dueno.setNombre(nombreDuenio);
        dueno.setCelDuenio(celDuenio);
        dueno.setDireccion(direccion);
        dueno.setCodigoPostal(codigoPostal);
        
        this.modificarDuenio(dueno);
    }

    private Duenio buscarDuenio(int id_duenio) {
       return controlPersis.traerDuenio(id_duenio);
    }

    private void modificarDuenio(Duenio dueno) {
        controlPersis.modificarDuenio(dueno);
    }
    
    public void inicializarRazas() {
    List<Raza> razasExistentes = traerRazas();
    if (razasExistentes.isEmpty()) {
        crearRaza("Labrador", "Perro grande, amigable y enérgico");
        crearRaza("Golden Retriever", "Perro grande, gentil y confiable");
        crearRaza("Pastor Alemán", "Perro grande, inteligente y versátil");
        crearRaza("Bulldog Francés", "Perro pequeño, adaptable y juguetón");
        crearRaza("Chihuahua", "Perro muy pequeño, alerta y vivaz");
        crearRaza("Poodle", "Perro inteligente, activo y elegante");
        crearRaza("Beagle", "Perro mediano, amigable y curioso");
        crearRaza("Rottweiler", "Perro grande, robusto y confiado");
        crearRaza("Yorkshire Terrier", "Perro pequeño, valiente y enérgico");
        crearRaza("Mestizo", "Mezcla de razas");
    }
}
}