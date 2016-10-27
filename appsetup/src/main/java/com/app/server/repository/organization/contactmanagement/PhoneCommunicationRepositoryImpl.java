package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.PhoneCommunication;
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
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for PhoneCommunication Transaction table", complexity = Complexity.MEDIUM)
public class PhoneCommunicationRepositoryImpl extends CommonUtilRepositoryImpl implements PhoneCommunicationRepository<PhoneCommunication> {

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
    public List<PhoneCommunication> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<PhoneCommunication> query = emanager.createNamedQuery("PhoneCommunication.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <PhoneCommunication> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
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
        List<CommonEntityInterface> listOfPhoneCommunication = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfPhoneCommunication.size());
        return listOfPhoneCommunication;
    }

    /**
     * Saves the new  <PhoneCommunication> object.
     * @return PhoneCommunication
     * @Params Object of PhoneCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public PhoneCommunication save(PhoneCommunication entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <PhoneCommunication> object.
     * @return java.util.List<PhoneCommunication>
     * @Params list of PhoneCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<PhoneCommunication> save(List<PhoneCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (PhoneCommunication phoneCommunication : entity) {
            emanager.persist(phoneCommunication);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <PhoneCommunication> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        PhoneCommunication phoneCommunication = emanager.find(com.app.shared.organization.contactmanagement.PhoneCommunication.class, id);
        emanager.remove(phoneCommunication);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <PhoneCommunication> object.
     * @Params Object of PhoneCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(PhoneCommunication entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <PhoneCommunication> object.
     * @Params list of PhoneCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<PhoneCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (PhoneCommunication phoneCommunication : entity) {
            emanager.merge(phoneCommunication);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of PhoneCommunication objects by filtering on refernce key <commType>
     * @return List<PhoneCommunication>
     * @Params commType of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<PhoneCommunication> findByCommType(String commType) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("PhoneCommunication.findByCommType");
        query.setParameter("commType", commType);
        java.util.List<com.app.shared.organization.contactmanagement.PhoneCommunication> listOfPhoneCommunication = query.getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "findByCommType", "Total Records Fetched = " + listOfPhoneCommunication.size());
        return listOfPhoneCommunication;
    }

    /**
     * Return PhoneCommunication object by filtering on refernce key <phoneId>
     * @return PhoneCommunication
     * @Params phoneId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public PhoneCommunication findById(String phoneId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("PhoneCommunication.findById");
        query.setParameter("phoneId", phoneId);
        PhoneCommunication listOfPhoneCommunication = (PhoneCommunication) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationRepositoryImpl", "findById", "Total Records Fetched = " + listOfPhoneCommunication);
        return listOfPhoneCommunication;
    }
}
