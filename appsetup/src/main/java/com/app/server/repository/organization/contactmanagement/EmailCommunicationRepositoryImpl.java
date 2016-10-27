package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.EmailCommunication;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for EmailCommunication Transaction table", complexity = Complexity.MEDIUM)
public class EmailCommunicationRepositoryImpl extends CommonUtilRepositoryImpl implements EmailCommunicationRepository<EmailCommunication> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<EmailCommunication> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<EmailCommunication> query = emanager.createNamedQuery("EmailCommunication.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <EmailCommunication> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <CommonEntityInterface>
     * @return List<CommonEntityInterface>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<CommonEntityInterface> findPageWiseData(String jpqlQuery, int pageSize, int pageNo) throws Exception {
        List<CommonEntityInterface> listOfEmailCommunication = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfEmailCommunication.size());
        return listOfEmailCommunication;
    }

    /**
     * Saves the new  <EmailCommunication> object.
     * @return EmailCommunication
     * @Params Object of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public EmailCommunication save(EmailCommunication entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <EmailCommunication> object.
     * @return java.util.List<EmailCommunication>
     * @Params list of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<EmailCommunication> save(List<EmailCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (EmailCommunication emailCommunication : entity) {
            emanager.persist(emailCommunication);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <EmailCommunication> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        EmailCommunication emailCommunication = emanager.find(com.app.shared.organization.contactmanagement.EmailCommunication.class, id);
        emanager.remove(emailCommunication);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <EmailCommunication> object.
     * @Params Object of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(EmailCommunication entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <EmailCommunication> object.
     * @Params list of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<EmailCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (EmailCommunication emailCommunication : entity) {
            emanager.merge(emailCommunication);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of EmailCommunication objects by filtering on refernce key <commType>
     * @return List<EmailCommunication>
     * @Params commType of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<EmailCommunication> findByCommType(String commType) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("EmailCommunication.findByCommType");
        query.setParameter("commType", commType);
        java.util.List<com.app.shared.organization.contactmanagement.EmailCommunication> listOfEmailCommunication = query.getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "findByCommType", "Total Records Fetched = " + listOfEmailCommunication.size());
        return listOfEmailCommunication;
    }

    /**
     * Return EmailCommunication object by filtering on refernce key <emailCommId>
     * @return EmailCommunication
     * @Params emailCommId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public EmailCommunication findById(String emailCommId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("EmailCommunication.findById");
        query.setParameter("emailCommId", emailCommId);
        EmailCommunication listOfEmailCommunication = (EmailCommunication) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "findById", "Total Records Fetched = " + listOfEmailCommunication);
        return listOfEmailCommunication;
    }
}
