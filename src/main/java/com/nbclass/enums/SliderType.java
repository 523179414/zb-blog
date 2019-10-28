package com.nbclass.enums;

/**
 * ResponseStatus
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
public enum SliderType {

    NOTIFY(1, "网站公告"),
    SLIDER(2, "轮播");

    private Integer type;
    private String desc;

    SliderType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

}
