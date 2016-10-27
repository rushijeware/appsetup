package com.app.server.service.organization.contactmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organization.contactmanagement.EmailCategory;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Service for EmailCategory Master table Entity", complexity = Complexity.LOW)
public abstract class EmailCategoryService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(EmailCategory entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<EmailCategory> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(EmailCategory entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<EmailCategory> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
