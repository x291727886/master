package com.xiajr.masterblog.modules.service.impl;

import com.xiajr.masterblog.common.lang.Consts;
import com.xiajr.masterblog.common.utils.BeanMapUtils;
import com.xiajr.masterblog.modules.data.MessageVO;
import com.xiajr.masterblog.modules.data.PostVO;
import com.xiajr.masterblog.modules.data.UserVO;
import com.xiajr.masterblog.modules.entity.Message;
import com.xiajr.masterblog.modules.repository.MessageRepository;
import com.xiajr.masterblog.modules.service.MessageService;
import com.xiajr.masterblog.modules.service.PostService;
import com.xiajr.masterblog.modules.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author langhsu
 */
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Override
    public Page<MessageVO> pagingByUserId(Pageable pageable, long userId) {
        Page<Message> page = messageRepository.findAllByUserId(pageable, userId);
        List<MessageVO> rets = new ArrayList<>();

        Set<Long> postIds = new HashSet<>();
        Set<Long> fromUserIds = new HashSet<>();

        // 筛选
        page.getContent().forEach(po -> {
            MessageVO no = BeanMapUtils.copy(po);

            rets.add(no);

            if (no.getPostId() > 0) {
                postIds.add(no.getPostId());
            }
            if (no.getFromId() > 0) {
                fromUserIds.add(no.getFromId());
            }

        });

        // 加载
        Map<Long, PostVO> posts = postService.findMapByIds(postIds);
        Map<Long, UserVO> fromUsers = userService.findMapByIds(fromUserIds);

        rets.forEach(n -> {
            if (n.getPostId() > 0) {
                n.setPost(posts.get(n.getPostId()));
            }
            if (n.getFromId() > 0) {
                n.setFrom(fromUsers.get(n.getFromId()));
            }
        });

        return new PageImpl<>(rets, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public void send(MessageVO message) {
        if (message == null || message.getUserId() <=0 || message.getFromId() <= 0) {
            return;
        }

        Message po = new Message();
        BeanUtils.copyProperties(message, po);
        po.setCreated(new Date());

        messageRepository.save(po);
    }

    @Override
    public int unread4Me(long userId) {
        return messageRepository.countByUserIdAndStatus(userId, Consts.UNREAD);
    }

    @Override
    @Transactional
    public void readed4Me(long userId) {
        messageRepository.updateReadedByUserId(userId);
    }

    @Override
    @Transactional
    public int deleteByPostId(long postId) {
        return messageRepository.deleteByPostId(postId);
    }
}
