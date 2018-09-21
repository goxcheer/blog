package com.qgx.service.impl;

import com.qgx.dao.CommentDao;
import com.qgx.entity.Comment;
import com.qgx.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:20:09 2018/8/23
 *@email:604721660@qq.com
 *@decription: 评论Service实现类
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public Integer saveComment(Comment comment) {
        return commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> listComment(Map<String, Object> map) {
        return commentDao.listComment(map);
}

    @Override
    public Integer countComment(Map<String, Object> map) {
        return commentDao.countComment(map);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentDao.deleteComment(id);
    }
}
