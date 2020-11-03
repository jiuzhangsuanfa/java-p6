package com.jzsf.tuitor.rpcDomain.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/15
 */
public class PageInfoReq implements Serializable {

    private static final long serialVersionUID = -5994404559402453133L;

    @Min(0)
    private Integer page;
    @Min(5)
    private Integer size;
    @NotBlank
    private String property;
    @NotNull
    private boolean isDESC;

    public boolean isDESC() {
        return isDESC;
    }

    public void setDESC(boolean DESC) {
        isDESC = DESC;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
