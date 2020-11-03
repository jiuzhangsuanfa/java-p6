package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.ArticleTagDao;
import com.jzsf.tuitor.pojo.ArticleTag;
import com.jzsf.tuitor.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Service
public class ArticleTagServiceImpl extends BaseServiceImpl<ArticleTag, String>
        implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getRepository() {
        return articleTagDao;
    }
}
