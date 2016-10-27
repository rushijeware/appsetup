package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.SocialCategory;
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
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "2", comments = "Repository for SocialCategory Master table Entity", complexity = Complexity.LOW)
public class SocialCategoryRepositoryImpl extends CommonUtilRepositoryImpl implements SocialCategoryRepository<SocialCategory> {

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
    public List<SocialCategory> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<SocialCategory> query = emanager.createNamedQuery("SocialCategory.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <SocialCategory> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
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
        List<CommonEntityInterface> listOfSocialCategory = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfSocialCategory.size());
        return listOfSocialCategory;
    }

    /**
     * Saves the new  <SocialCategory> object.
     * @return SocialCategory
     * @Params Object of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public SocialCategory save(SocialCategory entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <SocialCategory> object.
     * @return java.util.List<SocialCategory>
     * @Params list of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<SocialCategory> save(List<SocialCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (SocialCategory socialCategory : entity) {
            emanager.persist(socialCategory);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <SocialCategory> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        SocialCategory socialCategory = emanager.find(com.app.shared.organization.contactmanagement.SocialCategory.class, id);
        emanager.remove(socialCategory);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <SocialCategory> object.
     * @Params Object of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(SocialCategory entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <SocialCategory> object.
     * @Params list of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<SocialCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (SocialCategory socialCategory : entity) {
            emanager.merge(socialCategory);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return SocialCategory object by filtering on refernce key <socialCatId>
     * @return SocialCategory
     * @Params socialCatId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public SocialCategory findById(String socialCatId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("SocialCategory.findById");
        query.setParameter("socialCatId", socialCatId);
        SocialCategory listOfSocialCategory = (SocialCategory) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "findById", "Total Records Fetched = " + listOfSocialCategory);
        return listOfSocialCategory;
    }
}
