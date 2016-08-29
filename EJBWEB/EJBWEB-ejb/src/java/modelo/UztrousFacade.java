
package modelo;

import entidades.Uztrous;
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

;
}
