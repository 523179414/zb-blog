package com.nbclass.enums;

public enum TemplateType {
    RegisterSuccess("registerSuccess", "注册成功"),
    
    ;
    private String templateName;
    private String desc;

    TemplateType(String templateName, String desc){
        this.templateName = templateName;
        this.desc = desc;
    }


    public String getName() {
        return templateName;
    }
    public String getDesc() {
        return desc;
    }

}
