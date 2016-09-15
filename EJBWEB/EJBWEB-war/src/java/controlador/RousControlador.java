package controlador;

import entidades.Uztrous;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UztrousFacade;

/**
 *
 * @author Sandra
 */
@ManagedBean
@SessionScoped
public class RousControlador implements Serializable {

    @EJB
    private UztrousFacade uztrousFacade;

    public RousControlador() {
    }

    public List<Uztrous> listaRous() {
        return this.uztrousFacade.findAll();
    }

    public List<Uztrous> rousPorIdRol(BigDecimal idRol) {
        return this.uztrousFacade.findRolById(idRol);
    }

    public List<Uztrous> rousPorPidmUsuario(BigDecimal pidmUsuario) {
        return this.uztrousFacade.findRolByPidm(pidmUsuario);
    }

    public List<Uztrous> rousPorEstado(Character estado) {
        return this.uztrousFacade.findRousByEstado(estado);
    }

    public List<Uztrous> rousPorIdRolPidmUsuario(BigDecimal idRol, BigDecimal pidmUsuario) {
        return this.uztrousFacade.findRousIdRolPidmUsuario(idRol,pidmUsuario);
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztrousFacade.toJson(prueba);
    }


 //encriptar datos
    public String encriptarC(String texto) throws NoSuchPaddingException
    {
        return this.uztrousFacade.encriptar(texto);
    }
}
