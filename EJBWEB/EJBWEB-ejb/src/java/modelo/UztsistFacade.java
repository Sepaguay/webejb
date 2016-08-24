/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztsist;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sandra
 */
@Stateless
public class UztsistFacade extends AbstractFacade<Uztsist> {

    @PersistenceContext(unitName = "EJBWEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UztsistFacade() {
        super(Uztsist.class);
    }

    ///MÉTODO PARA ENCONTRAR SISTEMA POR ID
    public Uztsist findSistemaById(BigDecimal uztsistId) {
        try {
            return (Uztsist) em.createNamedQuery("Uztsist.findByUztsistId").setParameter("uztsistId", uztsistId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER SISTEMA POR NOMBRE
    public Uztsist findSistemaByNombre(String uztsistNombre) {
        try {
            return (Uztsist) em.createNamedQuery("Uztsist.findByUztsistNombre").setParameter("uztsistNombre", uztsistNombre).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER SISTEMAS DE ACUERDO Al ESTADO 
    public List<Uztsist> findSistemasByEstado(Character uztsistEstado) {
        try {
            return em.createNamedQuery("Uztsist.findByUztsistEstado").setParameter("uztsistEstado", uztsistEstado).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    //MÉTODO PARA OBTENER SISTEMAS POR DESCRIPCIÓN 
    public List<Uztsist> findSistemasByDescripción(String uztsistNombre) {
        try {
            return em.createNamedQuery("Uztsist.findByUztsistEstado").setParameter("uztsistEstado", uztsistNombre).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
