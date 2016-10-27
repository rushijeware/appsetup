package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for Login Transaction table", complexity = Complexity.MEDIUM)
public interface LoginRepository<T> extends CommonUtilRepository {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByUserId(String userId) throws Exception;

    List<T> findByContactId(String contactId) throws Exception;

    T findById(String loginPk) throws Exception;

    List<T> FindUnMappedUser() throws Exception;

    List<T> FindMappedUser() throws Exception;
}
