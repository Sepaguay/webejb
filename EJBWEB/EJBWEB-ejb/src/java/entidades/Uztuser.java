/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sandra
 */
@Entity
@Table(name = "UZTUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uztuser.findAll", query = "SELECT u FROM Uztuser u"),
    @NamedQuery(name = "Uztuser.findByUztuserPidm", query = "SELECT u FROM Uztuser u WHERE u.uztuserPidm = :uztuserPidm"),
    @NamedQuery(name = "Uztuser.findByUztuserId", query = "SELECT u FROM Uztuser u WHERE u.uztuserId = :uztuserId"),
    @NamedQuery(name = "Uztuser.findByUztuserNombres", query = "SELECT u FROM Uztuser u WHERE u.uztuserNombres = :uztuserNombres"),
    @NamedQuery(name = "Uztuser.findByUztuserCargo", query = "SELECT u FROM Uztuser u WHERE u.uztuserCargo = :uztuserCargo"),
    @NamedQuery(name = "Uztuser.findByUztuserEstado", query = "SELECT u FROM Uztuser u WHERE u.uztuserEstado = :uztuserEstado"),
    @NamedQuery(name = "Uztuser.findByUztuserCampus", query = "SELECT u FROM Uztuser u WHERE u.uztuserCampus = :uztuserCampus")})
public class Uztuser implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UZTUSER_PIDM")
    private BigDecimal uztuserPidm;
    @Size(max = 15)
    @Column(name = "UZTUSER_ID")
    private String uztuserId;
    @Size(max = 100)
    @Column(name = "UZTUSER_NOMBRES")
    private String uztuserNombres;
    @Size(max = 100)
    @Column(name = "UZTUSER_CARGO")
    private String uztuserCargo;
    @Column(name = "UZTUSER_ESTADO")
    private Character uztuserEstado;
    @Size(max = 3)
    @Column(name = "UZTUSER_CAMPUS")
    private String uztuserCampus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uztuser")
    private List<Uztrous> uztrousList;

    public Uztuser() {
    }

    public Uztuser(BigDecimal uztuserPidm) {
        this.uztuserPidm = uztuserPidm;
    }

    public BigDecimal getUztuserPidm() {
        return uztuserPidm;
    }

    public void setUztuserPidm(BigDecimal uztuserPidm) {
        this.uztuserPidm = uztuserPidm;
    }

    public String getUztuserId() {
        return uztuserId;
    }

    public void setUztuserId(String uztuserId) {
        this.uztuserId = uztuserId;
    }

    public String getUztuserNombres() {
        return uztuserNombres;
    }

    public void setUztuserNombres(String uztuserNombres) {
        this.uztuserNombres = uztuserNombres;
    }

    public String getUztuserCargo() {
        return uztuserCargo;
    }

    public void setUztuserCargo(String uztuserCargo) {
        this.uztuserCargo = uztuserCargo;
    }

    public Character getUztuserEstado() {
        return uztuserEstado;
    }

    public void setUztuserEstado(Character uztuserEstado) {
        this.uztuserEstado = uztuserEstado;
    }

    public String getUztuserCampus() {
        return uztuserCampus;
    }

    public void setUztuserCampus(String uztuserCampus) {
        this.uztuserCampus = uztuserCampus;
    }

    @XmlTransient
    public List<Uztrous> getUztrousList() {
        return uztrousList;
    }

    public void setUztrousList(List<Uztrous> uztrousList) {
        this.uztrousList = uztrousList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uztuserPidm != null ? uztuserPidm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uztuser)) {
            return false;
        }
        Uztuser other = (Uztuser) object;
        if ((this.uztuserPidm == null && other.uztuserPidm != null) || (this.uztuserPidm != null && !this.uztuserPidm.equals(other.uztuserPidm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Uztuser[ uztuserPidm=" + uztuserPidm + " ]";
    }
    
}
