package com.nbclass.enums;

public enum CacheKeyPrefix {
    ARTICLE_LOOK("ARTICLE_LOOK_"),ARTICLE_LOVE("ARTICLE_LOVE_"),COMMENT_LOVE("COMMENT_LOVE_"),
    COMMENT_FLOOR("COMMENT_FLOOR_"),CURRENT_THEME("CURRENT_THEME"),THEMES("THEMES"),THEME("THEME_")
    ;

    String prefix;

    CacheKeyPrefix(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
