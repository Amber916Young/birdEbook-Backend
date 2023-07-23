package com.bird.common.enums;

/**
 * @ClassName:ArticleType
 * @Auther: yyj
 * @Description:
 * @Date: 17/07/2023 16:42
 * @Version: v1.0
 */
public enum ArticleType {
    WIKI("Wiki"),
    BLOG("Blog"),
    DRAFTWIKI("DraftWiki");

    private String label;

    ArticleType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
