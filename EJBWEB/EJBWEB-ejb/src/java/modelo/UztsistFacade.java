/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztsist;
import flexjson.JSONSerializer;
import java.math.BigDecimal;
import java.security.MessageDigest;
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
import org.apache.commons.codec.binary.Base64;

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

    public boolean insertSistema(Uztsist sistema) {
        sistema.setUztsistNombre(encriptar(sistema.getUztsistNombre()));
        
        return em.merge(sistema) != null;
    }

    ///MÉTODO PARA ENCONTRAR SISTEMA POR ID
    public Uztsist findSistemaById(BigDecimal uztsistId) throws Exception {
        try {
            Uztsist sisD = (Uztsist) em.createNamedQuery("Uztsist.findByUztsistId").setParameter("uztsistId", uztsistId).getSingleResult();
           Uztsist sisD1=sisD;
            sisD.setUztsistNombre(Desencriptar(sisD1.getUztsistNombre()));
            return sisD;
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
