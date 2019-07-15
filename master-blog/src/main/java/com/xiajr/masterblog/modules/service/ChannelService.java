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

import com.xiajr.masterblog.modules.entity.Channel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 栏目管理
 *
 * @author xiajr
 *
 */
@Service
public interface ChannelService {
	List<Channel> findAll(int status);
	Map<Integer, Channel> findMapByIds(Collection<Integer> ids);
	Channel getById(int id);
	void update(Channel channel);
	void updateWeight(int id, int weighted);
	void delete(int id);
	long count();
}
