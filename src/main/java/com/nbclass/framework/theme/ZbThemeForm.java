package com.nbclass.framework.theme;

import lombok.Data;

import java.util.List;

@Data
public class ZbThemeForm {

    /**
     * 属性name
     */
    private String name;

    /**
     * 属性label
     */
    private String label;

    /**
     * 属性类型
     */
    private String type;

    /**
     * 属性placeholder
     */
    private String placeholder;

    /**
     * 属性值
     */
    private String value;

    /**
     * 属性默认值
     */
    private String defaultValue;

    private List<ZbThemeOption> options;

}