package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.PhoneCategory;
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
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "2", comments = "Repository for PhoneCategory Master table Entity", complexity = Complexity.LOW)
public class PhoneCategoryRepositoryImpl extends CommonUtilRepositoryImpl implements PhoneCategoryRepository<PhoneCategory> {

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
    public List<PhoneCategory> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<PhoneCategory> query = emanager.createNamedQuery("PhoneCategory.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <PhoneCategory> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
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
        List<CommonEntityInterface> listOfPhoneCategory = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfPhoneCategory.size());
        return listOfPhoneCategory;
    }

    /**
     * Saves the new  <PhoneCategory> object.
     * @return PhoneCategory
     * @Params Object of PhoneCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public PhoneCategory save(PhoneCategory entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <PhoneCategory> object.
     * @return java.util.List<PhoneCategory>
     * @Params list of PhoneCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<PhoneCategory> save(List<PhoneCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (PhoneCategory phoneCategory : entity) {
            emanager.persist(phoneCategory);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <PhoneCategory> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        PhoneCategory phoneCategory = emanager.find(com.app.shared.organization.contactmanagement.PhoneCategory.class, id);
        emanager.remove(phoneCategory);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <PhoneCategory> object.
     * @Params Object of PhoneCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(PhoneCategory entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <PhoneCategory> object.
     * @Params list of PhoneCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<PhoneCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (PhoneCategory phoneCategory : entity) {
            emanager.merge(phoneCategory);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return PhoneCategory object by filtering on refernce key <phoneCatId>
     * @return PhoneCategory
     * @Params phoneCatId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public PhoneCategory findById(String phoneCatId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("PhoneCategory.findById");
        query.setParameter("phoneCatId", phoneCatId);
        PhoneCategory listOfPhoneCategory = (PhoneCategory) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCategoryRepositoryImpl", "findById", "Total Records Fetched = " + listOfPhoneCategory);
        return listOfPhoneCategory;
    }
}
