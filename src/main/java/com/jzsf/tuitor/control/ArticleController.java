package com.jzsf.tuitor.control;

import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import com.jzsf.tuitor.rpcDomain.req.ArticleReq;
import com.jzsf.tuitor.rpcDomain.req.PageInfoReq;
import com.jzsf.tuitor.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * @author by plain yuan
 * @since 2020/04/14
 * 文章操作控制器
 */
@CrossOrigin
@RequestMapping("/article")
@Controller
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(AccountSettingController.class);

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/publish", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult publish(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                              @RequestBody ArticleReq req) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return articleService.publishArticle(req, userId);
    }

    @RequestMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult delete(@RequestBody @NotNull ArticleReq req) {
        return articleService.deleteArticleByArticleId(req.getId());
    }

    @RequestMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult update(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                             @RequestBody ArticleReq req) {
        return articleService.updateArticleByAuthor(req, JwtTokenUtil.getUserIdByAuthorHead(headerValue));
    }

    @RequestMapping(value = "/show/detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult show(@RequestBody ArticleReq req) {
        return articleService.showSingleArticle(req);
    }

    @RequestMapping(value = "/show/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult getArticleListByPageAble(@RequestBody @Validated PageInfoReq req) {
        Sort sort = Sort.by(req.isDESC() ? Sort.Direction.DESC : Sort.Direction.ASC, req.getProperty());
        Pageable pageable = PageRequest.of(req.getPage(),req.getSize() ,sort);
        return new RespResult(ResultCode.SUCCESS, articleService.findAll(pageable));
    }

}
