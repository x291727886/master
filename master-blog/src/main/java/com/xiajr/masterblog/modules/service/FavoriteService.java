package com.xiajr.masterblog.modules.service;

import com.xiajr.masterblog.modules.data.FavoriteVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 收藏记录
 * @author xiajr
 *
 */
@Service
public interface FavoriteService {
    /**
     * 查询用户收藏记录
     * @param pageable
     * @param userId
     * @return
     */
    Page<FavoriteVO> pagingByUserId(Pageable pageable, long userId);

    void add(long userId, long postId);
    void delete(long userId, long postId);
    void deleteByPostId(long postId);
}
