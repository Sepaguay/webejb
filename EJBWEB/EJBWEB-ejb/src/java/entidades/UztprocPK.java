/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sandra
 */
@Embeddable
public class UztprocPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "UZTSIST_ID")
    private BigInteger uztsistId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UZTPROC_ID")
    private BigInteger uztprocId;

    public UztprocPK() {
    }

    public UztprocPK(BigInteger uztsistId, BigInteger uztprocId) {
        this.uztsistId = uztsistId;
        this.uztprocId = uztprocId;
    }

    public BigInteger getUztsistId() {
        return uztsistId;
    }

    public void setUztsistId(BigInteger uztsistId) {
        this.uztsistId = uztsistId;
    }

    public BigInteger getUztprocId() {
        return uztprocId;
    }

    public void setUztprocId(BigInteger uztprocId) {
        this.uztprocId = uztprocId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uztsistId != null ? uztsistId.hashCode() : 0);
        hash += (uztprocId != null ? uztprocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UztprocPK)) {
            return false;
        }
        UztprocPK other = (UztprocPK) object;
        if ((this.uztsistId == null && other.uztsistId != null) || (this.uztsistId != null && !this.uztsistId.equals(other.uztsistId))) {
            return false;
        }
        if ((this.uztprocId == null && other.uztprocId != null) || (this.uztprocId != null && !this.uztprocId.equals(other.uztprocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UztprocPK[ uztsistId=" + uztsistId + ", uztprocId=" + uztprocId + " ]";
    }
    
}
