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

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "2", comments = "PaymentDetails", complexity = Complexity.MEDIUM)
@XmlRootElement
public class PaymentDetails implements EntityValidatorInterface, Serializable {

    private static final long serialVersionUID = 1477479291497L;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    private Double amount;

    private String ccHolderName;

    private String creditCardNo;

    private String customerId;

    private String cvvNo;

    private Integer expiryMonth;

    private Integer expiryYear;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCcHolderName() {
        return ccHolderName;
    }

    public void setCcHolderName(String ccHolderName) {
        this.ccHolderName = ccHolderName;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCvvNo() {
        return cvvNo;
    }

    public void setCvvNo(String cvvNo) {
        this.cvvNo = cvvNo;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
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
