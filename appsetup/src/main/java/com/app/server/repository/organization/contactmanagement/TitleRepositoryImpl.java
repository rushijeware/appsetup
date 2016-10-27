package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.Title;
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
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for Title Master table Entity", complexity = Complexity.LOW)
public class TitleRepositoryImpl extends CommonUtilRepositoryImpl implements TitleRepository<Title> {

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
    public List<Title> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Title> query = emanager.createNamedQuery("Title.findAll").getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Title> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
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
        List<CommonEntityInterface> listOfTitle = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfTitle.size());
        return listOfTitle;
    }

    /**
     * Saves the new  <Title> object.
     * @return Title
     * @Params Object of Title
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Title save(Title entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Title> object.
     * @return java.util.List<Title>
     * @Params list of Title
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Title> save(List<Title> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Title title : entity) {
            emanager.persist(title);
        }
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Title> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Title title = emanager.find(com.app.shared.organization.contactmanagement.Title.class, id);
        emanager.remove(title);
        Log.out.println("ORGCM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Title> object.
     * @Params Object of Title
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Title entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Title> object.
     * @Params list of Title
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Title> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Title title : entity) {
            emanager.merge(title);
        }
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Title object by filtering on refernce key <titleId>
     * @return Title
     * @Params titleId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Title findById(String titleId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Title.findById");
        query.setParameter("titleId", titleId);
        Title listOfTitle = (Title) query.getSingleResult();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "TitleRepositoryImpl", "findById", "Total Records Fetched = " + listOfTitle);
        return listOfTitle;
    }
}
