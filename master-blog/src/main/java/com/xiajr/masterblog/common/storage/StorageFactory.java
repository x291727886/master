/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.xiajr.masterblog.common.storage;


import com.xiajr.masterblog.common.storage.impl.AliyunStorageImpl;
import com.xiajr.masterblog.common.storage.impl.NativeStorageImpl;
import com.xiajr.masterblog.common.storage.impl.QiniuStorageImpl;
import com.xiajr.masterblog.common.storage.impl.UpYunStorageImpl;
import com.xiajr.masterblog.config.SiteOptions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * created by langhsu
 * on 2019/1/21
 */
@Component
public class StorageFactory {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SiteOptions siteOptions;

    private Map<String, Storage> fileRepoMap = new HashMap<>();

    @PostConstruct
    public void init() {
        fileRepoMap.put("native", applicationContext.getBean(NativeStorageImpl.class));
        fileRepoMap.put("upyun", applicationContext.getBean(UpYunStorageImpl.class));
        fileRepoMap.put("aliyun", applicationContext.getBean(AliyunStorageImpl.class));
        fileRepoMap.put("qiniu", applicationContext.getBean(QiniuStorageImpl.class));
    }

    public Storage get() {
        String scheme = siteOptions.getValue("storage_scheme");
        if (StringUtils.isBlank(scheme)) {
            scheme = "native";
        }
        return fileRepoMap.get(scheme);
    }
}
