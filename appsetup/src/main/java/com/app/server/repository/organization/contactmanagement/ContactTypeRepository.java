package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for ContactType Master table Entity", complexity = Complexity.LOW)
public interface ContactTypeRepository<T> extends CommonUtilRepository {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String contactTypeId) throws Exception;
}
