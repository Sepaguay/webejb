
package modelo;

import entidades.Uztrous;
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
public class UztrousFacade extends AbstractFacade<Uztrous> {

    @PersistenceContext(unitName = "EJBWEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UztrousFacade() {
        super(Uztrous.class);
    }

    //MÉTODO QUE DEVUELVE TODOS LOS REGISTROS DE ROLES Y USUARIOS
    public List<Uztrous> findAllRolUsu() {
        try {
            return em.createNamedQuery("Uztrous.findAll").getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    ///MÉTODO PARA ROUS POR ID_ROL
    public List<Uztrous> findRolById(BigDecimal uztrolId) {
        try {
            return em.createNamedQuery("Uztrous.findByUztrolId").setParameter("uztrolId", uztrolId).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    ///MÉTODO PARA ROUS POR PIDM  USUARIO
    public List<Uztrous> findRolByPidm(BigDecimal uztuserPidm) {
        try {
            return em.createNamedQuery("Uztrous.findByUztuserPidm").setParameter("uztuserPidm", uztuserPidm).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    ///MÉTODO PARA ROUS POR ESTADO
    public List<Uztrous> findRousByEstado(Character uztrousEstado) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrous u WHERE UPPER(u.uztrousEstado) LIKE UPPER(:uztrousEstado)");
            query.setParameter("uztrousEstado", "%" + uztrousEstado + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA  rous de acuerdo a id rol y pidm usuario
    public List<Uztrous> findRousIdRolPidmUsuario(BigDecimal uztrolId, BigDecimal uztuserPidm) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrous u WHERE u.uztrousPK.uztrolId = :uztrolId and  u.uztrousPK.uztuserPidm = :uztuserPidm");
            query.setParameter("uztrolId", uztrolId);
            query.setParameter("uztuserPidm", uztuserPidm);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA  rous de acuerdo a id rol y pidm usuario pero con nombres
    //MÉTODO PARA  rous de acuerdo a id rol y pidm usuario pero con nombres
    public List<Object[]> findRousIdRolPidmUsuarioNombre(BigDecimal uztrolId, BigDecimal uztuserPidm) {
        try {
            Query query = em.createQuery("SELECT r.uztrolNombre,u.uztuserNombres,ru.uztrousFcha,ru.uztrousEstado "
                    + "FROM Uztrol r, Uztuser u, Uztrous ru where ru.uztrousPK.uztrolId=r.uztrolId and ru.uztrousPK.uztuserPidm=u.uztuserPidm "
                    + "and ru.uztrousPK.uztrolId = :uztrolId and ru.uztrousPK.uztuserPidm = :uztuserPidm");
            query.setParameter("uztrolId", uztrolId);
            query.setParameter("uztuserPidm", uztuserPidm);
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
