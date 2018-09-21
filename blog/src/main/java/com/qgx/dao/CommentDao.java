package com.qgx.dao;

import com.qgx.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:19:51 2018/8/23
 *@email:604721660@qq.com
 *@decription: 评论Dao层
 */
public interface CommentDao {

    /**
     * 用户添加评论
     * @param comment
     * @return
     */
    Integer saveComment(Comment comment);
    /**
     * 带条件查询评论信息
     * @param map
     * @return
     */
    List<Comment> listComment(Map<String, Object> map);

    /**
     * 条件查询评论数量
     * @param map
     * @return
     */
    Integer countComment(Map<String, Object> map);

    /**
     * 更新评论
     * @param comment
     */
    void updateComment(Comment comment);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(int id);
}
