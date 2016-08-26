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
import javax.persistence.Query;

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
//MÉTODO QUE DEVUELVE TODOS LOS SISTEMAS

    public List<Uztsist> findAllSistemas() {
        try {
            return em.createNamedQuery("Uztsist.findAll").getResultList();
        } catch (NoResultException e) {
            return null;
        }
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
    public List<Uztsist> findSistemaByNombre(String uztsistNombre) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztsist u WHERE UPPER(u.uztsistNombre) LIKE UPPER(:uztsistNombre)");
            query.setParameter("uztsistNombre", "%" + uztsistNombre + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER SISTEMAS DE ACUERDO Al ESTADO 
    public List<Uztsist> findSistemasByEstado(Character uztsistEstado) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztsist u WHERE UPPER(u.uztsistEstado) LIKE UPPER(:uztsistEstado)");
            query.setParameter("uztsistEstado", "%" + uztsistEstado + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER SISTEMAS POR DESCRIPCIÓN 
    public List<Uztsist> findSistemasByDescripción(String uztsistDescripcion) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztsist u WHERE UPPER(u.uztsistDescripcion) LIKE UPPER(:uztsistDescripcion)");
            query.setParameter("uztsistDescripcion", "%" + uztsistDescripcion + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER SISTEMAS POR RUTA 
    public List<Uztsist> findSistemasByRuta(String uztsistRuta) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztsist u WHERE UPPER(u.uztsistRuta) LIKE UPPER(:uztsistRuta)");
            query.setParameter("uztsistRuta", "%" + uztsistRuta + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
