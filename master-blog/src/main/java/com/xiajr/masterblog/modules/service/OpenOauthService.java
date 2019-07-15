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


import com.xiajr.masterblog.modules.data.OpenOauthVO;
import com.xiajr.masterblog.modules.data.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author xiajr
 *
 */
@Service
public interface OpenOauthService {
    //通过 oauth_token 查询 user
    UserVO getUserByOauthToken(String oauth_token);

    OpenOauthVO getOauthByToken(String oauth_token);
    
    OpenOauthVO getOauthByOauthUserId(String oauthUserId);

    OpenOauthVO getOauthByUid(long userId);

    boolean checkIsOriginalPassword(long userId);

    void saveOauthToken(OpenOauthVO oauth);

}
