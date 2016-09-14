package modelo;

import entidades.Uztrous;
import flexjson.JSONSerializer;
import java.math.BigDecimal;
import java.util.List;
import javax.crypto.NoSuchPaddingException;
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

    public List<Object[]> findRousIdRolPidmUsuarioNombres(BigDecimal uztrolId, BigDecimal uztuserPidm) {
        try {
            Query query = em.createQuery("SELECT r.uztrolNombre,u.uztuserNombres,ru.uztrousFcha,ru.uztrousEstado "
                    + "FROM Uztrol r, Uztuser u, Uztrous ru where ru.uztrousPK.uztrolId=r.uztrolId and ru.uztrousPK.uztuserPidm=u.uztuserPidm "
                    + "and ru.uztrousPK.uztrolId = :uztrolId and ru.uztrousPK.uztuserPidm = :uztuserPidm");
            query.setParameter("uztrolId", uztrolId);
            query.setParameter("uztuserPidm", uztuserPidm);
            return query.getResultList();

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    /////////////////////////
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
