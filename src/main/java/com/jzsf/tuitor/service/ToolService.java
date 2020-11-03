/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jzsf.tuitor.service;

import com.jzsf.tuitor.rpcDomain.req.RegisterReq;

/**
 * @author senyang
 * @version $Id: ToolService.java, v 0.1 2020年04月24日 12:30 AM senyang Exp $
 */
public interface ToolService {

    String getCaptcha();

    boolean sendRegisterMail(RegisterReq req);

}