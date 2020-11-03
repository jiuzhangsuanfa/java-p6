package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, String> {

}
