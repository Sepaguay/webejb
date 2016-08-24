
package controlador;

import entidades.Uztuser;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UztuserFacade;
import java.util.*;

@ManagedBean
@SessionScoped
public class UsuarioControlador implements Serializable{
    @EJB
    private UztuserFacade uztuserFacade;
    private Uztuser usu;
    
    
    public UsuarioControlador() {
    }
    
    public List<Uztuser> listUsuarios()
    {
        return this.uztuserFacade.listUsuarios();
    }
    
    public List<Uztuser> obtenerUsuarioID()
    {
       
        return this.uztuserFacade.findUserByNombres("sAnDra");
         
    }
    
    
}
