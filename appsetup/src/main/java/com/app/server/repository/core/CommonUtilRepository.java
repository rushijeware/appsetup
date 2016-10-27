package com.app.server.repository.core;
import java.util.List;
import java.util.Map;

import com.athena.server.pluggable.interfaces.CommonEntityInterface;

public interface CommonUtilRepository {

	List<Object> search(String repositoryName, Map<String, Object> fields, Map<String, String> fieldMetaData) throws Exception;

	long getTotalPageCount(String jpqlQuery) throws Exception;

	List<CommonEntityInterface> findPageWiseData(String jpqlQuery, int pageSize, int pageNo) throws Exception;
}
