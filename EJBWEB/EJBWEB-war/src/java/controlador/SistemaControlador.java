/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Uztsist;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.crypto.NoSuchPaddingException;
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

    public boolean insertarSistema() throws NoSuchPaddingException {
        Uztsist nuevo = new Uztsist();
        nuevo.setUztsistId(new BigDecimal(611));
        nuevo.setUztsistNombre("prueba 611");
        nuevo.setUztsistDescripcion("descripcion sistema prueba 66");
        nuevo.setUztsistRuta("miespe");
        nuevo.setUztsistEstado('A');
        nuevo.setUztsistFecha(new Date());

        return this.uztsistFacade.insertSistema(nuevo);
    }

    public List<Uztsist> obtenerSistemas() {
        return this.uztsistFacade.findAll();
    }

    public String obtenerSistemaId(BigDecimal idS) throws Exception {
        return this.uztsistFacade.findSistemaById(idS).getUztsistNombre();
    }

    public List<Uztsist> obtenerSistemaNombre(String nombreSis) {
        return this.uztsistFacade.findSistemaByNombre(nombreSis);
    }

    public List<Uztsist> obtenerSistemasEstado(Character estSis) {
        return this.uztsistFacade.findSistemasByEstado(estSis);
    }

    public List<Uztsist> obtenerSistemasRuta(String rutaSis) {
        return this.uztsistFacade.findSistemasByRuta(rutaSis);
    }

    public List<Uztsist> obtenerSistemasDescripción(String desSis) {
        return this.uztsistFacade.findSistemasByDescripción(desSis);
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztsistFacade.toJson(prueba);
    }

    // Transforma una List a Json en formato String
    public Uztsist jsonSistema(String prueba) {

        return this.uztsistFacade.jsonSystem(prueba);
    }

    //encriptar datos
    public String encriptarC(String texto) throws NoSuchPaddingException {
        return this.uztsistFacade.encriptar(texto);
    }

}
