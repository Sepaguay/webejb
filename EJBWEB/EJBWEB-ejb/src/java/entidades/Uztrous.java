/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sandra
 */
@Entity
@Table(name = "UZTROUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uztrous.findAll", query = "SELECT u FROM Uztrous u"),
    @NamedQuery(name = "Uztrous.findByUztrolId", query = "SELECT u FROM Uztrous u WHERE u.uztrousPK.uztrolId = :uztrolId"),
    @NamedQuery(name = "Uztrous.findByUztuserPidm", query = "SELECT u FROM Uztrous u WHERE u.uztrousPK.uztuserPidm = :uztuserPidm"),
    @NamedQuery(name = "Uztrous.findByUztrousFcha", query = "SELECT u FROM Uztrous u WHERE u.uztrousFcha = :uztrousFcha"),
    @NamedQuery(name = "Uztrous.findByUztrousEstado", query = "SELECT u FROM Uztrous u WHERE u.uztrousEstado = :uztrousEstado")})
public class Uztrous implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UztrousPK uztrousPK;
    @Column(name = "UZTROUS_FCHA")
    @Temporal(TemporalType.DATE)
    private Date uztrousFcha;
    @Column(name = "UZTROUS_ESTADO")
    private Character uztrousEstado;
    @JoinColumn(name = "UZTUSER_PIDM", referencedColumnName = "UZTUSER_PIDM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Uztuser uztuser;
    @JoinColumn(name = "UZTROL_ID", referencedColumnName = "UZTROL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Uztrol uztrol;

    public Uztrous() {
    }

    public Uztrous(UztrousPK uztrousPK) {
        this.uztrousPK = uztrousPK;
    }

    public Uztrous(BigInteger uztrolId, BigInteger uztuserPidm) {
        this.uztrousPK = new UztrousPK(uztrolId, uztuserPidm);
    }

    public UztrousPK getUztrousPK() {
        return uztrousPK;
    }

    public void setUztrousPK(UztrousPK uztrousPK) {
        this.uztrousPK = uztrousPK;
    }

    public Date getUztrousFcha() {
        return uztrousFcha;
    }

    public void setUztrousFcha(Date uztrousFcha) {
        this.uztrousFcha = uztrousFcha;
    }

    public Character getUztrousEstado() {
        return uztrousEstado;
    }

    public void setUztrousEstado(Character uztrousEstado) {
        this.uztrousEstado = uztrousEstado;
    }

    public Uztuser getUztuser() {
        return uztuser;
    }

    public void setUztuser(Uztuser uztuser) {
        this.uztuser = uztuser;
    }

    public Uztrol getUztrol() {
        return uztrol;
    }

    public void setUztrol(Uztrol uztrol) {
        this.uztrol = uztrol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uztrousPK != null ? uztrousPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uztrous)) {
            return false;
        }
        Uztrous other = (Uztrous) object;
        if ((this.uztrousPK == null && other.uztrousPK != null) || (this.uztrousPK != null && !this.uztrousPK.equals(other.uztrousPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Uztrous[ uztrousPK=" + uztrousPK + " ]";
    }
    
}
