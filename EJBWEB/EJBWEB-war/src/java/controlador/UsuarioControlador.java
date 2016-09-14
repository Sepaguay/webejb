package controlador;

import entidades.Uztuser;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UztuserFacade;
import java.util.*;
import javax.crypto.NoSuchPaddingException;

@ManagedBean
@SessionScoped
public class UsuarioControlador implements Serializable {

    @EJB
    private UztuserFacade uztuserFacade;

    public UsuarioControlador() {
    }

    public List<Uztuser> listUsuarios() {
        return this.uztuserFacade.listUsuarios();
    }

    public List<Uztuser> obtenerUsuarioID(String id) {

        return this.uztuserFacade.findUserByID(id);
    }

    public List<Uztuser> obtenerUsuarioPorPidm(BigDecimal pidm) {
        return this.uztuserFacade.findUserByPidm(pidm);
    }

    public List<Uztuser> obtenerUsuarioPorNombres(String nombres) {
        return this.uztuserFacade.findUserByNombres(nombres);
    }

    public List<Uztuser> obtenerUsuarioPorCargo(String cargo) {
        return this.uztuserFacade.findUserByCargo(cargo);
    }

    public List<Uztuser> obtenerUsuarioPorEstado(Character estado) {
        return this.uztuserFacade.findUserByEstado(estado);
    }

    public List<Uztuser> obtenerUsuarioPorCampus(String campus) {
        return this.uztuserFacade.findUserByCampus(campus);
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztuserFacade.toJson(prueba);
    }
    //encriptar datos
    public String encriptarC(String texto) throws NoSuchPaddingException
    {
        return this.uztuserFacade.encriptar(texto);
    }

}
