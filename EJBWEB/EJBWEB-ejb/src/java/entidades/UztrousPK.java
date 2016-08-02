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
public class UztrousPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "UZTROL_ID")
    private BigInteger uztrolId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UZTUSER_PIDM")
    private BigInteger uztuserPidm;

    public UztrousPK() {
    }

    public UztrousPK(BigInteger uztrolId, BigInteger uztuserPidm) {
        this.uztrolId = uztrolId;
        this.uztuserPidm = uztuserPidm;
    }

    public BigInteger getUztrolId() {
        return uztrolId;
    }

    public void setUztrolId(BigInteger uztrolId) {
        this.uztrolId = uztrolId;
    }

    public BigInteger getUztuserPidm() {
        return uztuserPidm;
    }

    public void setUztuserPidm(BigInteger uztuserPidm) {
        this.uztuserPidm = uztuserPidm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uztrolId != null ? uztrolId.hashCode() : 0);
        hash += (uztuserPidm != null ? uztuserPidm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UztrousPK)) {
            return false;
        }
        UztrousPK other = (UztrousPK) object;
        if ((this.uztrolId == null && other.uztrolId != null) || (this.uztrolId != null && !this.uztrolId.equals(other.uztrolId))) {
            return false;
        }
        if ((this.uztuserPidm == null && other.uztuserPidm != null) || (this.uztuserPidm != null && !this.uztuserPidm.equals(other.uztuserPidm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UztrousPK[ uztrolId=" + uztrolId + ", uztuserPidm=" + uztuserPidm + " ]";
    }
    
}
