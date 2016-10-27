package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.spartan.server.password.interfaces.PasswordAlgoRepositoryIntefrace;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.spartan.server.password.interfaces.PasswordAlgoInterface;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for PasswordAlgo Master table Entity", complexity = Complexity.LOW)
public interface PasswordAlgoRepository<T> extends CommonUtilRepository, PasswordAlgoRepositoryIntefrace {

    List<PasswordAlgoInterface> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String algoId) throws Exception;
}
