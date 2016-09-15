package controlador;

import entidades.Uztrol;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UztrolFacade;

/**
 *
 * @author Sandra
 */
@ManagedBean
@SessionScoped
public class RolControlador implements Serializable {

    @EJB
    private UztrolFacade uztrolFacade;

    public RolControlador() {
    }

    public List<Uztrol> obtenerRoles() {
        return this.uztrolFacade.findAll();
    }

    public List<Uztrol> obtenerRolId(BigDecimal rolid) {
        return this.uztrolFacade.findRolById(rolid);
    }

    public List<Uztrol> obtenerRolNombre(String nombre) {
        return this.uztrolFacade.findRolByNombre(nombre);
    }

    public List<Uztrol> obtenerRolesEstado(Character est) {
        return this.uztrolFacade.findRolByEstado(est);
    }

    public List<Uztrol> obtenerRolIdSistema() {
        return this.uztrolFacade.findRolByIdSistema(new BigDecimal(1));
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztrolFacade.toJson(prueba);
    }
    //encriptar datos
    public String encriptarC(String texto) throws NoSuchPaddingException
    {
        return this.uztrolFacade.encriptar(texto);
    }

}
