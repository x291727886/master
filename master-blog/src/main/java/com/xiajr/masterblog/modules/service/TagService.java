package com.xiajr.masterblog.modules.service;

import com.xiajr.masterblog.modules.data.PostTagVO;
import com.xiajr.masterblog.modules.data.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author xiajr
 *
 */
@Service
public interface TagService {
    Page<TagVO> pagingQueryTags(Pageable pageable);
    Page<PostTagVO> pagingQueryPosts(Pageable pageable, String tagName);
    void batchUpdate(String names, long latestPostId);
    void deteleMappingByPostId(long postId);
}
