/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztproc;
import flexjson.JSONSerializer;
import java.math.BigInteger;
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
public class UztprocFacade extends AbstractFacade<Uztproc> {

    @PersistenceContext(unitName = "EJBWEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UztprocFacade() {
        super(Uztproc.class);
    }

    //Metodo que devuelve el Proceso segun el ID del proceso 
    public List<Uztproc> findProcByID(BigInteger uztprocId) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocPK.uztprocId = :uztprocId");
            query.setParameter("uztprocId", uztprocId);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun el ID del Sistema 
    public List<Uztproc> findProcByIdSis(BigInteger uztsistId) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocPK.uztsistId = :uztsistId");
            query.setParameter("uztsistId", uztsistId);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun el ID del Sistema y el ID del proceso
    public List<Uztproc> findProcByIdSisAndIdProc(BigInteger uztsistId, BigInteger uztprocId) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocPK.uztsistId = :uztsistId "
                    + "AND u.uztprocPK.uztprocId = :uztprocId");
            query.setParameter("uztsistId", uztsistId);
            query.setParameter("uztprocId", uztprocId);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun el nombre
    public List<Uztproc> findProcByName(String uztprocNombre) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocNombre) LIKE :uztprocNombre");
            query.setParameter("uztprocNombre", "%" + uztprocNombre.toUpperCase() + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun el Ejecucion
    public List<Uztproc> findProcByExecution(String uztprocEjecucion) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocEjecucion) LIKE :uztprocEjecucion");
            query.setParameter("uztprocEjecucion", "%" + uztprocEjecucion.toUpperCase() + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun Inicio
    public List<Uztproc> findProcByStart(String uztprocInicio) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocInicio) LIKE :uztprocInicio");
            query.setParameter("uztprocInicio", "%" + uztprocInicio.toUpperCase() + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun Salida
    public List<Uztproc> findProcByExit(String uztprocSalida) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocSalida) LIKE :uztprocSalida");
            query.setParameter("uztprocSalida", "%" + uztprocSalida.toUpperCase() + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun el estado
    public List<Uztproc> findProcByEstado(Character uztprocEstado) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocEstado = :uztprocEstado");
            query.setParameter("uztprocEstado", uztprocEstado);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }
    ////////////////////////////.

    //Metodo que devuelve el Proceso segun la Descripcion
    public List<Uztproc> findProcByDescripcion(String uztprocDescripcion) {

        try {
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocDescripcion) LIKE :uztprocDescripcion");
            query.setParameter("uztprocDescripcion", "%" + uztprocDescripcion.toUpperCase() + "%");
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
