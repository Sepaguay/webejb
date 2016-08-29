/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztrol;
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
public class UztrolFacade extends AbstractFacade<Uztrol> {

    @PersistenceContext(unitName = "EJBWEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UztrolFacade() {
        super(Uztrol.class);
    }

    //MÉTODO QUE DEVUELVE TODOS LOS ROLES
    public List<Uztrol> findAllRoles() {
        try {
            return em.createNamedQuery("Uztrol.findAll").getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    ///MÉTODO PARA ENCONTRAR ROL POR ID
    public List<Uztrol> findRolById(BigDecimal uztrolId) {
        try {
            return  em.createNamedQuery("Uztrol.findByUztrolId").setParameter("uztrolId", uztrolId).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER ROL POR NOMBRE
    public List<Uztrol> findRolByNombre(String uztrolNombre) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrol u WHERE UPPER(u.uztrolNombre) LIKE UPPER(:uztrolNombre)");
            query.setParameter("uztrolNombre", "%" + uztrolNombre + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER ROL POR ESTADO 
    public List<Uztrol> findRolByEstado(Character uztrolEstado) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrol u WHERE UPPER(u.uztrolEstado) LIKE UPPER(:uztrolEstado)");
            query.setParameter("uztrolEstado", "%" + uztrolEstado + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER ROLES  POR ID DEL SISTEMA  
    public List<Uztrol> findRolByIdSistema(BigDecimal uztsistId) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrol u WHERE u.uztsistId = :uztsistId");
            query.setParameter("uztsistId", uztsistId);
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
