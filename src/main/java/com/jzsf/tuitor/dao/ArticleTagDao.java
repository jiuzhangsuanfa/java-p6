package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Repository
public interface ArticleTagDao extends JpaRepository<ArticleTag, String> {

    /**
     * 根据文章id，获取该文章所有标签
     *
     * @param articleId
     * @return
     */
    @Query("select tagName from ArticleTag where articleId =?1")
    List<String> findTagNameByArticleId(@Param("articleId") String articleId);

    /**
     * 根据作者id删除文章的所有tag
     *
     * @param articleId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("delete from ArticleTag  where articleId = ?1")
    void deleteAllTagByArticleId(@Param("articleId") String articleId);

    /**
     * 查询文章id对应的该文章tag
     *
     * @param articleId
     * @return
     */
    List<ArticleTag> findByArticleId(String articleId);
}
