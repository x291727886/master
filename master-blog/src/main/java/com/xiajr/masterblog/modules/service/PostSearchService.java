package com.xiajr.masterblog.modules.service;

import com.xiajr.masterblog.modules.data.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @version : 1.0
 * @date : 2019/1/18
 * @author xiajr
 *
 */
@Service
public interface PostSearchService {
    /**
     * 根据关键字搜索
     * @param pageable 分页
     * @param term 关键字
     * @throws Exception
     */
    Page<PostVO> search(Pageable pageable, String term) throws Exception;

    /**
     * 重建
     */
    void resetIndexes();
}
