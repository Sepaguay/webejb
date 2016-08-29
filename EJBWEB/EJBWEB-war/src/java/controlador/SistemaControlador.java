/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Uztsist;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UztsistFacade;

/**
 *
 * @author Sandra
 */
@ManagedBean
@SessionScoped
public class SistemaControlador implements Serializable {

    @EJB
    private UztsistFacade uztsistFacade;

    public SistemaControlador() {
    }

    public List<Uztsist> obtenerSistemas() {
        return this.uztsistFacade.findAll();
    }

    public List<Uztsist>  obtenerSistemaId() {
        return this.uztsistFacade.findSistemaById(new BigDecimal(1));
    }

    public List<Uztsist> obtenerSistemaNombre() {
        return this.uztsistFacade.findSistemaByNombre("WEBFLOW");
    }

    public List<Uztsist> obtenerSistemasEstado() {
        return this.uztsistFacade.findSistemasByEstado(new Character('A'));
    }

    public List<Uztsist> obtenerSistemasRuta() {
        return this.uztsistFacade.findSistemasByRuta("miespe");
    }

    public List<Uztsist> obtenerSistemasDescripción() {
        return this.uztsistFacade.findSistemasByDescripción("sistema");
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztsistFacade.toJson(prueba);
    }

}
