/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import entidades.Uztproc;
import flexjson.JSONSerializer;
import java.math.BigInteger;
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

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocPK.uztprocId = :uztprocId");
            query.setParameter("uztprocId", uztprocId);
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }
        

    }
    ////////////////////////////.
    
    //Metodo que devuelve el Proceso segun el ID del Sistema 
    public List<Uztproc> findProcByIdSis(BigInteger uztsistId) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocPK.uztsistId = :uztsistId");
            query.setParameter("uztsistId", uztsistId);
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }    

    }
    ////////////////////////////.
    
     //Metodo que devuelve el Proceso segun el ID del Sistema y el ID del proceso
    public List<Uztproc> findProcByIdSisAndIdProc(BigInteger uztsistId,BigInteger uztprocId) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocPK.uztsistId = :uztsistId "
                    + "AND u.uztprocPK.uztprocId = :uztprocId");
            query.setParameter("uztsistId", uztsistId);
            query.setParameter("uztprocId", uztprocId);
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }    

    }
    ////////////////////////////.
    
    //Metodo que devuelve el Proceso segun el nombre
    public List<Uztproc> findProcByName(String uztprocNombre) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocNombre) LIKE :uztprocNombre");
            query.setParameter("uztprocNombre", "%"+uztprocNombre.toUpperCase()+"%");
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }    

    }
    ////////////////////////////.
    
     //Metodo que devuelve el Proceso segun el Ejecucion
    public List<Uztproc> findProcByExecution(String uztprocEjecucion) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocEjecucion) LIKE :uztprocEjecucion");
            query.setParameter("uztprocEjecucion", "%"+uztprocEjecucion.toUpperCase()+"%");
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }    

    }
    ////////////////////////////.
    
     //Metodo que devuelve el Proceso segun Inicio
    public List<Uztproc> findProcByStart(String uztprocInicio) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocInicio) LIKE :uztprocInicio");
            query.setParameter("uztprocInicio", "%"+uztprocInicio.toUpperCase()+"%");
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }    

    }
    ////////////////////////////.
    
      //Metodo que devuelve el Proceso segun Salida
    public List<Uztproc> findProcByExit(String uztprocSalida) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocSalida) LIKE :uztprocSalida");
            query.setParameter("uztprocSalida", "%"+uztprocSalida.toUpperCase()+"%");
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }    

    }
    ////////////////////////////.
    
          //Metodo que devuelve el Proceso segun el estado
    public List<Uztproc> findProcByEstado(Character uztprocEstado) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE u.uztprocEstado = :uztprocEstado");
            query.setParameter("uztprocEstado", uztprocEstado);
            return query.getResultList();
        }catch(NoResultException e)
        {
            return null;
        }    

    }
    ////////////////////////////.
    
      //Metodo que devuelve el Proceso segun la Descripcion
    public List<Uztproc> findProcByDescripcion(String uztprocDescripcion) {

        try{
            Query query = em.createQuery("SELECT u FROM Uztproc u WHERE UPPER(u.uztprocDescripcion) LIKE :uztprocDescripcion");
            query.setParameter("uztprocDescripcion", "%"+uztprocDescripcion.toUpperCase()+"%");
            return query.getResultList();
        }catch(NoResultException e)
        {
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
