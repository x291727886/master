/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.xiajr.masterblog.modules.service;

import com.xiajr.masterblog.modules.entity.Options;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author xiajr
 *
 */
@Service
public interface OptionsService {
	/**
	 * 查询所有配置
	 * @return list
	 */
	List<Options> findAll();

	/**
	 * 添加或修改配置
	 * - 修改时根据key判断唯一性
	 * @param options
	 */
	void update(Map<String, String> options);

	void initSettings(Resource resource);
}
