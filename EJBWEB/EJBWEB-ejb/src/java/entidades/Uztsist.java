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
import javax.persistence.Lob;
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
@Table(name = "UZTSIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uztsist.findAll", query = "SELECT u FROM Uztsist u"),
    @NamedQuery(name = "Uztsist.findByUztsistId", query = "SELECT u FROM Uztsist u WHERE u.uztsistId = :uztsistId"),
    @NamedQuery(name = "Uztsist.findByUztsistNombre", query = "SELECT u FROM Uztsist u WHERE u.uztsistNombre = :uztsistNombre"),
    @NamedQuery(name = "Uztsist.findByUztsistEstado", query = "SELECT u FROM Uztsist u WHERE u.uztsistEstado = :uztsistEstado"),
    @NamedQuery(name = "Uztsist.findByUztsistFecha", query = "SELECT u FROM Uztsist u WHERE u.uztsistFecha = :uztsistFecha")})
public class Uztsist implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UZTSIST_ID")
    private BigDecimal uztsistId;
    @Size(max = 100)
    @Column(name = "UZTSIST_NOMBRE")
    private String uztsistNombre;
    @Lob
    @Column(name = "UZTSIST_DESCRIPCION")
    private String uztsistDescripcion;
    @Lob
    @Column(name = "UZTSIST_RUTA")
    private String uztsistRuta;
    @Column(name = "UZTSIST_ESTADO")
    private Character uztsistEstado;
    @Column(name = "UZTSIST_FECHA")
    @Temporal(TemporalType.DATE)
    private Date uztsistFecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uztsist")
    private List<Uztproc> uztprocList;

    public Uztsist() {
    }

    public Uztsist(BigDecimal uztsistId) {
        this.uztsistId = uztsistId;
    }

    public BigDecimal getUztsistId() {
        return uztsistId;
    }

    public void setUztsistId(BigDecimal uztsistId) {
        this.uztsistId = uztsistId;
    }

    public String getUztsistNombre() {
        return uztsistNombre;
    }

    public void setUztsistNombre(String uztsistNombre) {
        this.uztsistNombre = uztsistNombre;
    }

    public String getUztsistDescripcion() {
        return uztsistDescripcion;
    }

    public void setUztsistDescripcion(String uztsistDescripcion) {
        this.uztsistDescripcion = uztsistDescripcion;
    }

    public String getUztsistRuta() {
        return uztsistRuta;
    }

    public void setUztsistRuta(String uztsistRuta) {
        this.uztsistRuta = uztsistRuta;
    }

    public Character getUztsistEstado() {
        return uztsistEstado;
    }

    public void setUztsistEstado(Character uztsistEstado) {
        this.uztsistEstado = uztsistEstado;
    }

    public Date getUztsistFecha() {
        return uztsistFecha;
    }

    public void setUztsistFecha(Date uztsistFecha) {
        this.uztsistFecha = uztsistFecha;
    }

    @XmlTransient
    public List<Uztproc> getUztprocList() {
        return uztprocList;
    }

    public void setUztprocList(List<Uztproc> uztprocList) {
        this.uztprocList = uztprocList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uztsistId != null ? uztsistId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uztsist)) {
            return false;
        }
        Uztsist other = (Uztsist) object;
        if ((this.uztsistId == null && other.uztsistId != null) || (this.uztsistId != null && !this.uztsistId.equals(other.uztsistId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Uztsist[ uztsistId=" + uztsistId + " ]";
    }
    
}
