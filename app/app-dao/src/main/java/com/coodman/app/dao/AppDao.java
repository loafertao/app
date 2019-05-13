package com.coodman.app.dao;

import java.util.List;
import java.util.Map;

import com.coodman.app.beans.entity.AppInfo;

/**
 * <b>app信息持久层接口</b>
 * @author Coodman
 * @version 1.0 2019-05-09
 * @since 2019-05-09
 */
public interface AppDao {

	
	/**
	 * *分页查询APP信息
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
   public List<AppInfo> findAppListByPageQuery(Map<String, Object> paramMap)throws Exception;


}
