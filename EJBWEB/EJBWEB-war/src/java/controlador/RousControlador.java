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

    public List<Uztrous> rousPorIdRol() {
        return this.uztrousFacade.findRolById(new BigDecimal(7));
    }

    public List<Uztrous> rousPorPidmUsuario() {
        return this.uztrousFacade.findRolByPidm(new BigDecimal(336254));
    }

    public List<Uztrous> rousPorEstado() {
        return this.uztrousFacade.findRousByEstado('A');
    }

    public List<Uztrous> rousPorIdRolPidmUsuario() {
        return this.uztrousFacade.findRousIdRolPidmUsuario(new BigDecimal(7), new BigDecimal(336254));
    }

    // Transforma una List a Json en formato String
    public String toJson(List prueba) {

        return this.uztrousFacade.toJson(prueba);
    }

    public String rousPorIdRolPidmUsuarioNombre() {
        return this.uztrousFacade.toJson(this.uztrousFacade.findRousIdRolPidmUsuarioNombres(new BigDecimal(8), new BigDecimal(336254)));
    }
 //encriptar datos
    public String encriptarC(String texto) throws NoSuchPaddingException
    {
        return this.uztrousFacade.encriptar(texto);
    }
}
