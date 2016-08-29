/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Uztproc;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UztprocFacade;

/**
 *
 * @author elvis
 */
@ManagedBean
@SessionScoped
public class ProcesoControlador implements Serializable {

    @EJB
    private UztprocFacade uztprocFacade;

    // metodos para la vista 
    public ProcesoControlador() {
    }

    public List<Uztproc> listaProcesos() {
        return this.uztprocFacade.findAll();
    }

    public List<Uztproc> obtenerProcesoPorID(BigInteger id) {
        return this.uztprocFacade.findProcByID(id);
    }

    public List<Uztproc> obtenerProcesoPorIdSistema(BigInteger id) {
        return this.uztprocFacade.findProcByIdSis(id);
    }

    public List<Uztproc> obtenerProcesoPorIdSistemaEIdProceso(BigInteger idsis, BigInteger idproc) {
        return this.uztprocFacade.findProcByIdSisAndIdProc(idsis, idproc);
    }

    public List<Uztproc> obtenerProcesoPorNombre(String nombre) {
        return this.uztprocFacade.findProcByName(nombre);
    }

    public List<Uztproc> obtenerProcesoPorEjecucion(String ejecucion) {
        return this.uztprocFacade.findProcByExecution(ejecucion);
    }

    public List<Uztproc> obtenerProcesoPorSalida(String salida) {
        return this.uztprocFacade.findProcByExit(salida);
    }

    public List<Uztproc> obtenerProcesoPorInicio(String inicio) {
        return this.uztprocFacade.findProcByStart(inicio);
    }

    public List<Uztproc> obtenerProcesoPorEstado(Character estado) {
        return this.uztprocFacade.findProcByEstado(estado);
    }

    public List<Uztproc> obtenerProcesoPorDescripcion(String descripcion) {
        return this.uztprocFacade.findProcByDescripcion(descripcion);
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztprocFacade.toJson(prueba);
    }
}
