/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztuser;
import flexjson.JSONSerializer;
import java.math.BigDecimal;
import java.util.List;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        criteriaQuery.select(root);
        TypedQuery<Uztuser> typedQuery = em.createQuery(criteriaQuery);
        List<Uztuser> resultList = typedQuery.getResultList();

        return resultList;
    }

    ////////////////////////
    //Metodo que devuelve el usuario segun el ID
    public List<Uztuser> findUserByID(String Id) {

        try {
            Query query = em.createQuery("Select u from Uztuser u Where u.uztuserId = :value");
            query.setParameter("value", Id);
            List<Uztuser> lista = query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el usuario segun el Pidm
    public List<Uztuser> findUserByPidm(BigDecimal PIDM) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztuser u WHERE u.uztuserPidm = :uztuserPidm");
            query.setParameter("uztuserPidm", PIDM);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el usuario segun el Nombre
    public List<Uztuser> findUserByNombres(String nombres) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztuser u WHERE UPPER(u.uztuserNombres) LIKE UPPER(:uztuserNombres)");
            query.setParameter("uztuserNombres", "%" + nombres + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el usuario segun el Cargo
    public List<Uztuser> findUserByCargo(String cargo) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztuser u WHERE UPPER(u.uztuserCargo) LIKE UPPER(:uztuserCargo)");
            query.setParameter("uztuserCargo", "%" + cargo + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el usuario segun el Cargo
    public List<Uztuser> findUserByEstado(Character estado) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztuser u WHERE u.uztuserEstado = :uztuserEstado");
            query.setParameter("uztuserEstado", estado);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el usuario segun el Campus
    public List<Uztuser> findUserByCampus(String campus) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztuser u WHERE UPPER(u.uztuserCampus) LIKE UPPER(:uztuserCampus)");
            query.setParameter("uztuserCampus", "%" + campus + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {
        JSONSerializer json = new JSONSerializer();
        return json.serialize(prueba);
    }
    
 // encripta un texto en base aes128 
    public String encriptar(String texto) throws NoSuchPaddingException {

        Aes128 aes128 = new Aes128();
        String input_encrypt = null;
        try {
            input_encrypt = aes128.bytesToHex(aes128.encrypt(texto));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input_encrypt;
    }
    
      // encripta un texto en base aes128 
    public String desencriptar(String texto) throws NoSuchPaddingException{

        Aes128 aes128 = new Aes128();
        byte input_decrypt[] = null;
        String encriptado=null;
        try {
            input_decrypt = aes128.decrypt(texto);
            encriptado=new String(input_decrypt,"UTF-8");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encriptado;
    }
}
