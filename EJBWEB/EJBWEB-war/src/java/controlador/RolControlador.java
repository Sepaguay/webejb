package controlador;

import entidades.Uztrol;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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

    public List<Uztrol> obtenerRolId() {
        return this.uztrolFacade.findRolById(new BigDecimal(1));
    }

    public List<Uztrol> obtenerRolNombre() {
        return this.uztrolFacade.findRolByNombre("UTIC");
    }

    public List<Uztrol> obtenerRolesEstado() {
        return this.uztrolFacade.findRolByEstado(new Character('A'));
    }

    public List<Uztrol> obtenerRolIdSistema() {
        return this.uztrolFacade.findRolByIdSistema(new BigDecimal(1));
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztrolFacade.toJson(prueba);
    }

}
