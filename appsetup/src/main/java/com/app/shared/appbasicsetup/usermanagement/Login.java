package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.interfaces.UserAuthentication;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import javax.persistence.NamedNativeQuery;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "Login")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Login", complexity = Complexity.LOW)
@NamedNativeQuery(name = "complexQuery", query = "SELECT login.multiTenantId FROM Login login , CoreContacts corecontact, UserDetail userinfo, UserData userdata WHERE loginId = ? AND login.contactId = corecontact.contactId AND login.userId = userinfo.userId AND userinfo.userId = userdata.userId")
@NamedQueries({ @javax.persistence.NamedQuery(name = "Login.findAll", query = " select u from Login u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Login.DefaultFinders", query = "select e from Login e where e.systemInfo.activeStatus=1 and e.serverAuthImage LIKE :serverAuthImage"), @javax.persistence.NamedQuery(name = "Login.findByUserId", query = "select e from Login e where e.systemInfo.activeStatus=1 and e.userDetail.userId=:userId"), @javax.persistence.NamedQuery(name = "Login.findByContactId", query = "select e from Login e where e.systemInfo.activeStatus=1 and e.coreContacts.contactId=:contactId"), @javax.persistence.NamedQuery(name = "Login.findById", query = "select e from Login e where e.systemInfo.activeStatus=1 and e.loginPk =:loginPk"), @javax.persistence.NamedQuery(name = "Login.FindUnMappedUser", query = "SELECT u FROM Login u WHERE u.systemInfo.activeStatus=1 AND u.userDetail.userId NOT IN (SELECT ub.userId FROM UserRoleBridge ub)"), @javax.persistence.NamedQuery(name = "Login.FindMappedUser", query = "SELECT u FROM Login u WHERE u.systemInfo.activeStatus=1 AND u.userDetail.userId IN (SELECT ub.userId FROM UserRoleBridge ub)") })
public class Login implements Serializable, CommonEntityInterface, UserAuthentication, Comparator<Login> {

    private static final long serialVersionUID = 1477391845943L;

    @Column(name = "loginId")
    @JsonProperty("loginId")
    @NotNull
    @Size(min = 0, max = 200)
    private String loginId;

    @Column(name = "serverAuthImage")
    @JsonProperty("serverAuthImage")
    @Size(max = 32)
    private String serverAuthImage;

    @Column(name = "serverAuthText")
    @JsonProperty("serverAuthText")
    @Size(max = 16)
    private String serverAuthText;

    @Column(name = "failedLoginAttempts")
    @JsonProperty("failedLoginAttempts")
    @Max(11)
    private Integer failedLoginAttempts;

    @Transient
    @JsonIgnore
    private Boolean isAuthenticated;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "loginPk")
    @JsonProperty("loginPk")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String loginPk;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserDetail userDetail;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private CoreContacts coreContacts;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        if (loginId != null) {
            this.loginId = loginId;
        }
    }

    public String getServerAuthImage() {
        return serverAuthImage;
    }

    public void setServerAuthImage(String serverAuthImage) {
        this.serverAuthImage = serverAuthImage;
    }

    public String getServerAuthText() {
        return serverAuthText;
    }

    public void setServerAuthText(String serverAuthText) {
        this.serverAuthText = serverAuthText;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getPrimaryKey() {
        return loginPk;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return loginPk;
    }

    public String getLoginPk() {
        return loginPk;
    }

    public void setLoginPk(String loginPk) {
        this.loginPk = loginPk;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public void setPrimaryDisplay(String primaryDisplay) {
        this.primaryDisplay = primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public CoreContacts getCoreContacts() {
        return coreContacts;
    }

    public void setCoreContacts(CoreContacts coreContacts) {
        this.coreContacts = coreContacts;
    }

    /**
     * Returns boolean value if System information's active status =-1
     * @return boolean
     */
    @JsonIgnore
    public boolean isHardDelete() {
        boolean isHardDelete = false;
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            isHardDelete = true;
        } else {
            isHardDelete = false;
        }
        return isHardDelete;
    }

    /**
     * Validates the entity fields based on java.validation.constraints annotaions and sets isEntityValided value in System information object
     * @return boolean
     * @throws java.lang.SecurityException
     */
    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    /**
     * Sets EntityValidator object
     * @param validateFactory
     */
    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> validateFactory) {
        this.entityValidator = validateFactory;
    }

    /**
     * Creates a new entity audit object and  if primarykey fields are null then sets created by, updated by, active status values in the entity audit field.
     * @param customerId
     * @param userId
     */
    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
        if (this.userDetail != null) {
            userDetail.setEntityAudit(customerId, userId, recordType);
            userDetail.setSystemInformation(recordType);
        }
        if (this.userDetail != null) {
            userDetail.setEntityAudit(customerId, userId, recordType);
            userDetail.setSystemInformation(recordType);
        }
        if (this.coreContacts != null) {
            coreContacts.setEntityAudit(customerId, userId, recordType);
            coreContacts.setSystemInformation(recordType);
        }
    }

    /**
     * Creates a new entity audit object and System Information object and based on @Params RECORD_TYPE sets created by and updated by values in the entity audit field.
     * @param CustomerId
     * @param userId
     * @param RECORD_TYPE
     */
    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        if (this.userDetail != null) {
            userDetail.setEntityAudit(customerId, userId);
        }
        if (this.userDetail != null) {
            userDetail.setEntityAudit(customerId, userId);
        }
        if (this.coreContacts != null) {
            coreContacts.setEntityAudit(customerId, userId);
        }
    }

    /**
     * Returns Logged in user informatio.
     * @return auditInfo
     */
    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    /**
     * Creates new System Information object.
     * @param RECORD_TYPE
     */
    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(-1);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    /**
     * Sets active status in System Information object.
     * @param activeStatus
     */
    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    /**
     * Returns system information object.
     * @return systemInfo
     */
    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    /**
     * Creates System information obect if null and sets transaction access code in that object.
     * @param transactionAccessCode
     */
    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    /**
     * Compares 2 objects and returns integer value
     * @param Login
     * @param Login
     */
    @Override
    public int compare(Login object1, Login object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((serverAuthImage == null ? " " : serverAuthImage));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (loginPk == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = loginPk.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.usermanagement.Login other = (com.app.shared.appbasicsetup.usermanagement.Login) obj;
            if (loginPk == null) {
                isEquals = false;
            } else if (!loginPk.equals(other.loginPk)) {
                isEquals = false;
            }
        } catch (java.lang.Exception ignore) {
            isEquals = false;
        }
        return isEquals;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }

    public String toJsonString() {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transient
    private String sessionId;

    @Transient
    private String userHash;

    @Transient
    private String qKeHash;

    @Transient
    private boolean isCheckCookie;

    @Override
    public int getSessionTimeout() {
        return this.getUserDetail().getSessionTimeout();
    }

    @Override
    public String getQKeHash() {
        return this.qKeHash;
    }

    @Override
    public boolean isCheckCookie() {
        return true;
    }

    @Override
    public void setContainerSessionId(String _sessionId) {
        this.sessionId = _sessionId;
    }

    @Override
    public void setUserHash(String _userHash) {
        this.userHash = _userHash;
    }

    @Override
    public void setQKeHash(String _qKeHash) {
        this.qKeHash = _qKeHash;
    }

    @JsonIgnore
    @Override
    public String getCredential() {
        return userDetail.getUserData().getPassword();
    }

    @Override
    public boolean isDisabled() {
        return false;
    }

    @Override
    public boolean isPasswordExpired() {
        return false;
    }

    @Override
    public String getUserId() {
        return userDetail.getUserId();
    }

    @Override
    public int getuserAccessCode() {
        return this.userDetail.getUserAccessCode();
    }
}
