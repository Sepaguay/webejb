/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztuser;
import flexjson.JSONSerializer;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
import org.apache.commons.codec.binary.Base64;

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

    // encripta un texto en base 64 
    public String encriptar(String texto) {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

}
