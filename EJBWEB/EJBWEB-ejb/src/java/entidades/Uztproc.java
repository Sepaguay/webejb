/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sandra
 */
@Entity
@Table(name = "UZTPROC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uztproc.findAll", query = "SELECT u FROM Uztproc u"),
    @NamedQuery(name = "Uztproc.findByUztsistId", query = "SELECT u FROM Uztproc u WHERE u.uztprocPK.uztsistId = :uztsistId"),
    @NamedQuery(name = "Uztproc.findByUztprocId", query = "SELECT u FROM Uztproc u WHERE u.uztprocPK.uztprocId = :uztprocId"),
    @NamedQuery(name = "Uztproc.findByUztprocNombre", query = "SELECT u FROM Uztproc u WHERE u.uztprocNombre = :uztprocNombre"),
    @NamedQuery(name = "Uztproc.findByUztprocEstado", query = "SELECT u FROM Uztproc u WHERE u.uztprocEstado = :uztprocEstado"),
    @NamedQuery(name = "Uztproc.findByUztprocFecha", query = "SELECT u FROM Uztproc u WHERE u.uztprocFecha = :uztprocFecha")})
public class Uztproc implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UztprocPK uztprocPK;
    @Size(max = 100)
    @Column(name = "UZTPROC_NOMBRE")
    private String uztprocNombre;
    @Lob
    @Column(name = "UZTPROC_EJECUCION")
    private String uztprocEjecucion;
    @Lob
    @Column(name = "UZTPROC_INICIO")
    private String uztprocInicio;
    @Lob
    @Column(name = "UZTPROC_SALIDA")
    private String uztprocSalida;
    @Column(name = "UZTPROC_ESTADO")
    private Character uztprocEstado;
    @Column(name = "UZTPROC_FECHA")
    @Temporal(TemporalType.DATE)
    private Date uztprocFecha;
    @Lob
    @Column(name = "UZTPROC_DESCRIPCION")
    private String uztprocDescripcion;
    @JoinColumn(name = "UZTSIST_ID", referencedColumnName = "UZTSIST_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Uztsist uztsist;
    @OneToMany(mappedBy = "uztproc")
    private List<Uztproc> uztprocList;
    @JoinColumns({
        @JoinColumn(name = "UZT_UZTSIST_ID", referencedColumnName = "UZTSIST_ID"),
        @JoinColumn(name = "UZT_UZTPROC_ID", referencedColumnName = "UZTPROC_ID")})
    @ManyToOne
    private Uztproc uztproc;

    public Uztproc() {
    }

    public Uztproc(UztprocPK uztprocPK) {
        this.uztprocPK = uztprocPK;
    }

    public Uztproc(BigInteger uztsistId, BigInteger uztprocId) {
        this.uztprocPK = new UztprocPK(uztsistId, uztprocId);
    }

    public UztprocPK getUztprocPK() {
        return uztprocPK;
    }

    public void setUztprocPK(UztprocPK uztprocPK) {
        this.uztprocPK = uztprocPK;
    }

    public String getUztprocNombre() {
        return uztprocNombre;
    }

    public void setUztprocNombre(String uztprocNombre) {
        this.uztprocNombre = uztprocNombre;
    }

    public String getUztprocEjecucion() {
        return uztprocEjecucion;
    }

    public void setUztprocEjecucion(String uztprocEjecucion) {
        this.uztprocEjecucion = uztprocEjecucion;
    }

    public String getUztprocInicio() {
        return uztprocInicio;
    }

    public void setUztprocInicio(String uztprocInicio) {
        this.uztprocInicio = uztprocInicio;
    }

    public String getUztprocSalida() {
        return uztprocSalida;
    }

    public void setUztprocSalida(String uztprocSalida) {
        this.uztprocSalida = uztprocSalida;
    }

    public Character getUztprocEstado() {
        return uztprocEstado;
    }

    public void setUztprocEstado(Character uztprocEstado) {
        this.uztprocEstado = uztprocEstado;
    }

    public Date getUztprocFecha() {
        return uztprocFecha;
    }

    public void setUztprocFecha(Date uztprocFecha) {
        this.uztprocFecha = uztprocFecha;
    }

    public String getUztprocDescripcion() {
        return uztprocDescripcion;
    }

    public void setUztprocDescripcion(String uztprocDescripcion) {
        this.uztprocDescripcion = uztprocDescripcion;
    }

    public Uztsist getUztsist() {
        return uztsist;
    }

    public void setUztsist(Uztsist uztsist) {
        this.uztsist = uztsist;
    }

    @XmlTransient
    public List<Uztproc> getUztprocList() {
        return uztprocList;
    }

    public void setUztprocList(List<Uztproc> uztprocList) {
        this.uztprocList = uztprocList;
    }

    public Uztproc getUztproc() {
        return uztproc;
    }

    public void setUztproc(Uztproc uztproc) {
        this.uztproc = uztproc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uztprocPK != null ? uztprocPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uztproc)) {
            return false;
        }
        Uztproc other = (Uztproc) object;
        if ((this.uztprocPK == null && other.uztprocPK != null) || (this.uztprocPK != null && !this.uztprocPK.equals(other.uztprocPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Uztproc[ uztprocPK=" + uztprocPK + " ]";
    }
    
}
