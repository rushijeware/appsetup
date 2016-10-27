package com.app.server.repository.appbasicsetup.aaa;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.aaa.JwtConfig;
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
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Repository for JwtConfig Master table Entity", complexity = Complexity.LOW)
public class JwtConfigRepositoryImpl extends CommonUtilRepositoryImpl implements JwtConfigRepository<JwtConfig> {

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
    public List<JwtConfig> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<JwtConfig> query = emanager.createNamedQuery("JwtConfig.findAll").getResultList();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <JwtConfig> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
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
        List<CommonEntityInterface> listOfJwtConfig = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfJwtConfig.size());
        return listOfJwtConfig;
    }

    /**
     * Saves the new  <JwtConfig> object.
     * @return JwtConfig
     * @Params Object of JwtConfig
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public JwtConfig save(JwtConfig entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <JwtConfig> object.
     * @return java.util.List<JwtConfig>
     * @Params list of JwtConfig
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<JwtConfig> save(List<JwtConfig> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (JwtConfig jwtConfig : entity) {
            emanager.persist(jwtConfig);
        }
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <JwtConfig> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        JwtConfig jwtConfig = emanager.find(com.app.shared.appbasicsetup.aaa.JwtConfig.class, id);
        emanager.remove(jwtConfig);
        Log.out.println("ABSAA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <JwtConfig> object.
     * @Params Object of JwtConfig
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(JwtConfig entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <JwtConfig> object.
     * @Params list of JwtConfig
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<JwtConfig> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (JwtConfig jwtConfig : entity) {
            emanager.merge(jwtConfig);
        }
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return JwtConfig object by filtering on refernce key <configId>
     * @return JwtConfig
     * @Params configId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public JwtConfig findById(String configId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("JwtConfig.findById");
        query.setParameter("configId", configId);
        JwtConfig listOfJwtConfig = (JwtConfig) query.getSingleResult();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "findById", "Total Records Fetched = " + listOfJwtConfig);
        return listOfJwtConfig;
    }
}
