package com.app.server.service.organization.contactmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organization.contactmanagement.PhoneCommunication;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Service for PhoneCommunication Transaction table", complexity = Complexity.MEDIUM)
public abstract class PhoneCommunicationService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(PhoneCommunication entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<PhoneCommunication> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(PhoneCommunication entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<PhoneCommunication> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByCommType(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
