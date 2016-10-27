package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.SocialCommunication;
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
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for SocialCommunication Transaction table", complexity = Complexity.MEDIUM)
public class SocialCommunicationRepositoryImpl extends CommonUtilRepositoryImpl implements SocialCommunicationRepository<SocialCommunication> {

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
    public List<SocialCommunication> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<SocialCommunication> query = emanager.createNamedQuery("SocialCommunication.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <SocialCommunication> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
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
        List<CommonEntityInterface> listOfSocialCommunication = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfSocialCommunication.size());
        return listOfSocialCommunication;
    }

    /**
     * Saves the new  <SocialCommunication> object.
     * @return SocialCommunication
     * @Params Object of SocialCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public SocialCommunication save(SocialCommunication entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <SocialCommunication> object.
     * @return java.util.List<SocialCommunication>
     * @Params list of SocialCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<SocialCommunication> save(List<SocialCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (SocialCommunication socialCommunication : entity) {
            emanager.persist(socialCommunication);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <SocialCommunication> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        SocialCommunication socialCommunication = emanager.find(com.app.shared.organization.contactmanagement.SocialCommunication.class, id);
        emanager.remove(socialCommunication);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <SocialCommunication> object.
     * @Params Object of SocialCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(SocialCommunication entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <SocialCommunication> object.
     * @Params list of SocialCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<SocialCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (SocialCommunication socialCommunication : entity) {
            emanager.merge(socialCommunication);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of SocialCommunication objects by filtering on refernce key <commType>
     * @return List<SocialCommunication>
     * @Params commType of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<SocialCommunication> findByCommType(String commType) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("SocialCommunication.findByCommType");
        query.setParameter("commType", commType);
        java.util.List<com.app.shared.organization.contactmanagement.SocialCommunication> listOfSocialCommunication = query.getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "findByCommType", "Total Records Fetched = " + listOfSocialCommunication.size());
        return listOfSocialCommunication;
    }

    /**
     * Return SocialCommunication object by filtering on refernce key <socialId>
     * @return SocialCommunication
     * @Params socialId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public SocialCommunication findById(String socialId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("SocialCommunication.findById");
        query.setParameter("socialId", socialId);
        SocialCommunication listOfSocialCommunication = (SocialCommunication) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCommunicationRepositoryImpl", "findById", "Total Records Fetched = " + listOfSocialCommunication);
        return listOfSocialCommunication;
    }
}
