/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sandra
 */
@Entity
@Table(name = "UZTROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uztrol.findAll", query = "SELECT u FROM Uztrol u"),
    @NamedQuery(name = "Uztrol.findByUztrolId", query = "SELECT u FROM Uztrol u WHERE u.uztrolId = :uztrolId"),
    @NamedQuery(name = "Uztrol.findByUztrolNombre", query = "SELECT u FROM Uztrol u WHERE u.uztrolNombre = :uztrolNombre"),
    @NamedQuery(name = "Uztrol.findByUztrolEstado", query = "SELECT u FROM Uztrol u WHERE u.uztrolEstado = :uztrolEstado"),
    @NamedQuery(name = "Uztrol.findByUztrolFecha", query = "SELECT u FROM Uztrol u WHERE u.uztrolFecha = :uztrolFecha")})
public class Uztrol implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UZTROL_ID")
    private BigDecimal uztrolId;
    @Size(max = 100)
    @Column(name = "UZTROL_NOMBRE")
    private String uztrolNombre;
    @Column(name = "UZTROL_ESTADO")
    private Character uztrolEstado;
    @Column(name = "UZTROL_FECHA")
    @Temporal(TemporalType.DATE)
    private Date uztrolFecha;
    @Column(name = "UZTSIST_ID")
    private BigDecimal uztsistId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uztrol")
    private List<Uztrous> uztrousList;

    public Uztrol() {
    }

    public Uztrol(BigDecimal uztrolId) {
        this.uztrolId = uztrolId;
    }

    public BigDecimal getUztrolId() {
        return uztrolId;
    }

    public void setUztrolId(BigDecimal uztrolId) {
        this.uztrolId = uztrolId;
    }

    public String getUztrolNombre() {
        return uztrolNombre;
    }

    public void setUztrolNombre(String uztrolNombre) {
        this.uztrolNombre = uztrolNombre;
    }

    public Character getUztrolEstado() {
        return uztrolEstado;
    }

    public void setUztrolEstado(Character uztrolEstado) {
        this.uztrolEstado = uztrolEstado;
    }

    public Date getUztrolFecha() {
        return uztrolFecha;
    }

    public void setUztrolFecha(Date uztrolFecha) {
        this.uztrolFecha = uztrolFecha;
    }

    @XmlTransient
    public List<Uztrous> getUztrousList() {
        return uztrousList;
    }

    public void setUztrousList(List<Uztrous> uztrousList) {
        this.uztrousList = uztrousList;
    }

    public BigDecimal getUztsistId() {
        return uztsistId;
    }

    public void setUztsistId(BigDecimal uztsistId) {
        this.uztsistId = uztsistId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uztrolId != null ? uztrolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uztrol)) {
            return false;
        }
        Uztrol other = (Uztrol) object;
        if ((this.uztrolId == null && other.uztrolId != null) || (this.uztrolId != null && !this.uztrolId.equals(other.uztrolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Uztrol[ uztrolId=" + uztrolId + " ]";
    }
    
}
