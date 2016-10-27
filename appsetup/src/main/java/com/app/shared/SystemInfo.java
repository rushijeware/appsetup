package com.app.shared;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;

@Embeddable
public class SystemInfo implements Serializable {

    private static final long serialVersionUID = 1477391444192L;

    @Column(name = "activeStatus")
    @JsonProperty("activeStatus")
    private Integer activeStatus = 1;

    @Column(name = "txnAccessCode")
    @JsonProperty("txnAccessCode")
    private Integer txnAccessCode = 51000;

    @Transient
    @JsonIgnore
    private boolean isEntityValidated = false;

    @JsonIgnore
    public void setEntityValidated(boolean isEntityValidated) {
        this.isEntityValidated = isEntityValidated;
    }

    @JsonIgnore
    public boolean isEntityValidated() {
        return isEntityValidated;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getTxnAccessCode() {
        return txnAccessCode;
    }

    public void setTxnAccessCode(Integer txnAccessCode) {
        this.txnAccessCode = txnAccessCode;
    }
}
