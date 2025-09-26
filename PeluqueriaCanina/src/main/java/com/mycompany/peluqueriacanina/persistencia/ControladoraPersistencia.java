package com.mycompany.peluqueriacanina.persistencia;

import com.mycompany.peluqueriacanina.logica.Duenio;
import com.mycompany.peluqueriacanina.logica.Mascota;
import com.mycompany.peluqueriacanina.logica.Raza;
import com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotaJpaController mascoJpa = new MascotaJpaController();
    RazaJpaController razaJpa = new RazaJpaController();

    public void guardar(Duenio duenio, Mascota masco) {
        duenioJpa.create(duenio);
        mascoJpa.create(masco);
    }
    
    public void guardarRaza(Raza raza) {
        razaJpa.create(raza);
    }

    public List<Mascota> traerMascotas() {
        return mascoJpa.findMascotaEntities();
    }
    
    public List<Raza> traerRazas() {
        return razaJpa.findRazaEntities();
    }

    public void borrarMascota(int num_cliente) {
        try {
            mascoJpa.destroy(num_cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mascota traerMascota(int num_cliente) {
        return mascoJpa.findMascota(num_cliente);
    }
    
    public Raza traerRaza(int id_raza) {
        return razaJpa.findRaza(id_raza);
    }

    public void modificarMascota(Mascota masco) {
        try {
            mascoJpa.edit(masco);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Duenio traerDuenio(int id_duenio) {
        return duenioJpa.findDuenio(id_duenio);
    }

    public void modificarDuenio(Duenio dueno) {
        try {
            duenioJpa.edit(dueno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}