/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import entidades.Uztuser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UztuserFacade;
import java.util.*;

/**
 *
 * @author Sandra
 */
@ManagedBean
@SessionScoped
public class UsuarioControlador implements Serializable{
    @EJB
    private UztuserFacade uztuserFacade;
    
    
    public UsuarioControlador() {
    }
    
    public List<Uztuser> USU()
    {
        return this.uztuserFacade.findAll();
    }
}
