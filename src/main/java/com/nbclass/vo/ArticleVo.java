package com.nbclass.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ArticleVo
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVo extends PageVo{
    private Integer isTop;
    private Integer isRec;
    private Integer isHot;
    private Integer isRandom;
    private Integer isPublic;
    private Integer isRecent;
    private Integer status;
    private Integer origin;
    private String keyword;
    private String category;
    private Integer tagId;

    public ArticleVo withIsTop(Integer isTop) {
        this.isTop = isTop;
        return this;
    }

    public ArticleVo withIsRec(Integer isRec) {
        this.isRec = isRec;
        return this;
    }

    public ArticleVo withIsHot(Integer isHot) {
        this.isHot = isHot;
        return this;
    }

    public ArticleVo withIsRandom(Integer isRandom) {
        this.isRandom = isRandom;
        return this;
    }

    public ArticleVo withIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public ArticleVo withIsRecent(Integer isRecent) {
        this.isRecent = isRecent;
        return this;
    }

    public ArticleVo withStatus(Integer status) {
        this.status = status;
        return this;
    }

}
