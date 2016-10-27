package com.app.server.repository.appbasicsetup.userrolemanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for UserRoleBridge Transaction table", complexity = Complexity.MEDIUM)
public interface UserRoleBridgeRepository<T> extends CommonUtilRepository {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByRoleId(String roleId) throws Exception;

    List<T> findByUserId(String userId) throws Exception;

    T findById(String roleUserMapId) throws Exception;
}
