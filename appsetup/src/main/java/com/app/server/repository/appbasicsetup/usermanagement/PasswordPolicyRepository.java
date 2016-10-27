package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.spartan.server.password.interfaces.PasswordPolicyRepositoryInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.spartan.server.password.interfaces.PasswordPolicyInterface;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for PasswordPolicy Master table Entity", complexity = Complexity.LOW)
public interface PasswordPolicyRepository<T> extends CommonUtilRepository, PasswordPolicyRepositoryInterface {

    List<PasswordPolicyInterface> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String policyId) throws Exception;
}
