package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.common.utils.BeanUtil;
import com.jzsf.tuitor.common.utils.UUIDUtil;
import com.jzsf.tuitor.dao.ArticleDao;
import com.jzsf.tuitor.dao.ArticleTagDao;
import com.jzsf.tuitor.pojo.Article;
import com.jzsf.tuitor.pojo.ArticleTag;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import com.jzsf.tuitor.rpcDomain.req.ArticleReq;
import com.jzsf.tuitor.rpcDomain.resp.ArticleResp;
import com.jzsf.tuitor.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, String>
        implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getRepository() {
        return articleDao;
    }

    @Override
    public RespResult publishArticle(ArticleReq req, String userId) {
        List<String> validateMsg = BeanUtil.validateProperty(req, "id");
        if (validateMsg.size() > 0) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, validateMsg);
        }

        Article article = new Article();
        BeanUtils.copyProperties(req, article);
        article.setUserId(userId);
        article.setId(UUIDUtil.getUUID());
        article.setPublishTime(new Timestamp(req.getPublishTime().getTime()));

        if (req.getArticleTagList() != null) {
            ArticleTag articleTag;
            for (String tagName : req.getArticleTagList()) {
                articleTag = new ArticleTag();
                articleTag.setId(UUIDUtil.getUUID());
                articleTag.setTagName(tagName);
                articleTag.setArticleId(article.getId());
                articleTagDao.save(articleTag);
            }
        }

        articleDao.save(article);
        return new RespResult(ResultCode.SUCCESS, article.getId());
    }

    @Override
    public RespResult deleteArticleByArticleId(String articleId) {
        if (StringUtils.isBlank(articleId)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "文章id不能为空");
        } else if (!articleDao.findById(articleId).isPresent()) {
            return new RespResult(ResultCode.RESULT_DATA_NONE, "该文章不存在");
        }
        articleDao.deleteById(articleId);
        if (articleTagDao.findByArticleId(articleId).size() > 0) {
            articleTagDao.deleteAllTagByArticleId(articleId);
        }
        return new RespResult(ResultCode.SUCCESS);
    }

    @Override
    public RespResult updateArticleByAuthor(ArticleReq req, String userId) {

        if (req.getId() == null) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "id 不能为空");
        }

        Article article = new Article();
        BeanUtils.copyProperties(req, article);
        article.setUserId(userId);
        article.setPublishTime(new Timestamp(req.getPublishTime().getTime()));

        // todo 对文章标签的更新抉择：全部删除，全部插入，还是部分更新

        // 选择全部删除，每次更新文章，都要携带原有的标签信息
        articleTagDao.deleteAllTagByArticleId(article.getId());

        if (req.getArticleTagList() != null) {
            ArticleTag articleTag;
            for (String tagName : req.getArticleTagList()) {
                articleTag = new ArticleTag();
                articleTag.setId(UUIDUtil.getUUID());
                articleTag.setArticleId(article.getId());
                articleTag.setTagName(tagName);
                articleTagDao.save(articleTag);
            }
        }

        articleDao.save(article);
        return new RespResult(ResultCode.SUCCESS);
    }

    @Override
    public RespResult showSingleArticle(ArticleReq req) {
        if (StringUtils.isBlank(req.getId())) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "id不能为空");
        }

        Optional<Article> optionalArticle = articleDao.findById(req.getId());

        if (optionalArticle.isPresent()) {
            ArticleResp articleResp = new ArticleResp();
            Article article = optionalArticle.get();
            BeanUtils.copyProperties(article, articleResp);
            articleResp.setArticleTagList(articleTagDao.findTagNameByArticleId(req.getId()));
            return new RespResult(ResultCode.SUCCESS, articleResp);
        } else {
            return new RespResult(ResultCode.RESULT_DATA_NONE);
        }
    }

    @Override
    public List<Article> getRecentPublishedArticles() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "publishTime"));
        List<Article> articleList = new ArrayList<>();
        articleDao.findAll(pageable).forEach(articleList::add);
        return articleList;
    }
}
