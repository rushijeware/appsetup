package com.app.shared.organization.locationmanagement;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import java.io.Serializable;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "2", comments = "TransactionResponse", complexity = Complexity.MEDIUM)
@XmlRootElement
public class TransactionResponse implements EntityValidatorInterface, Serializable {

    private static final long serialVersionUID = 1477479291636L;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    private String message;

    private Boolean status;

    private String transactionId;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> validateFactory) {
        this.dtoValidator = validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}
