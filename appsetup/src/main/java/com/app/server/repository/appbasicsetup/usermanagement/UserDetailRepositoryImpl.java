package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import org.springframework.stereotype.Repository;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.server.interfaces.UserInterface;
import com.spartan.server.interfaces.UserRepositoryInterface;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import javax.persistence.Query;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;

@Repository
public class UserDetailRepositoryImpl extends CommonUtilRepositoryImpl implements UserDetailRepository<UserDetail> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    @Transactional
    @Override
    public void update(List<UserInterface> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (UserInterface userDetail : entity) {
            emanager.merge(userDetail);
        }
    }

    @Override
    @Transactional
    public UserInterface getByUserId(String userId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("UserDetail.findById");
        query.setParameter("userId", userId);
        return (UserInterface) query.getSingleResult();
    }

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserDetail> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<UserDetail> query = emanager.createNamedQuery("UserDetail.findAll").getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <UserDetail> object.
     * @params jpqlQuery
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount(String jpqlQuery) throws Exception {
        long pageCount = super.getTotalPageCount(jpqlQuery);
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
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
        List<CommonEntityInterface> listOfUserDetail = (List<CommonEntityInterface>) super.findPageWiseData(jpqlQuery, pageSize, pageNo);
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfUserDetail.size());
        return listOfUserDetail;
    }

    /**
     * Saves the new  <UserDetail> object.
     * @return UserDetail
     * @Params Object of UserDetail
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public UserDetail save(UserDetail entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <UserDetail> object.
     * @return java.util.List<UserDetail>
     * @Params list of UserDetail
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserDetail> save(List<UserDetail> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (UserDetail userDetail : entity) {
            emanager.persist(userDetail);
        }
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <UserDetail> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        UserDetail userDetail = emanager.find(com.app.shared.appbasicsetup.usermanagement.UserDetail.class, id);
        emanager.remove(userDetail);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deletePassRecovery(List<PassRecovery> passrecovery) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.appbasicsetup.usermanagement.PassRecovery _passrecovery : passrecovery) {
            com.app.shared.appbasicsetup.usermanagement.PassRecovery s = emanager.find(com.app.shared.appbasicsetup.usermanagement.PassRecovery.class, _passrecovery.getPassRecoveryId());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <UserDetail> object.
     * @Params Object of UserDetail
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(UserDetail entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "update", entity);
    }

    /**
     * Return list of UserDetail objects by filtering on refernce key <userAccessLevelId>
     * @return List<UserDetail>
     * @Params userAccessLevelId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<UserDetail> findByUserAccessLevelId(String userAccessLevelId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserDetail.findByUserAccessLevelId");
        query.setParameter("userAccessLevelId", userAccessLevelId);
        java.util.List<com.app.shared.appbasicsetup.usermanagement.UserDetail> listOfUserDetail = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "findByUserAccessLevelId", "Total Records Fetched = " + listOfUserDetail.size());
        return listOfUserDetail;
    }

    /**
     * Return list of UserDetail objects by filtering on refernce key <userAccessDomainId>
     * @return List<UserDetail>
     * @Params userAccessDomainId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<UserDetail> findByUserAccessDomainId(String userAccessDomainId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserDetail.findByUserAccessDomainId");
        query.setParameter("userAccessDomainId", userAccessDomainId);
        java.util.List<com.app.shared.appbasicsetup.usermanagement.UserDetail> listOfUserDetail = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "findByUserAccessDomainId", "Total Records Fetched = " + listOfUserDetail.size());
        return listOfUserDetail;
    }

    /**
     * Return UserDetail object by filtering on refernce key <userId>
     * @return UserDetail
     * @Params userId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public UserDetail findById(String userId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserDetail.findById");
        query.setParameter("userId", userId);
        UserDetail listOfUserDetail = (UserDetail) query.getSingleResult();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserDetailRepositoryImpl", "findById", "Total Records Fetched = " + listOfUserDetail);
        return listOfUserDetail;
    }
}
