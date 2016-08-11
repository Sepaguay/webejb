/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztuser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Sandra
 */
@Stateless
public class UztuserFacade extends AbstractFacade<Uztuser> {

    @PersistenceContext(unitName = "EJBWEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UztuserFacade() {
        super(Uztuser.class);
    }

    ///////////////////////////////
    public List<Uztuser> listUsuarios() {
         CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Uztuser> criteriaQuery = criteriaBuilder.createQuery(Uztuser.class);
        Root<Uztuser> root = criteriaQuery.from(Uztuser.class);
        //ParameterExpression<String> parameterExpression=criteriaBuilder.parameter(String.class);
        //criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("uztuserId"), parameterExpression));
        criteriaQuery.select(root);
        TypedQuery<Uztuser> typedQuery = em.createQuery(criteriaQuery);
        List<Uztuser> resultList = typedQuery.getResultList();

        return  resultList;
    }

}
