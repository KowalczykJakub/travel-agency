package model;

import static util.RandomUtils.randomLang;
import static util.RandomUtils.randomLevel;

public class Language {
    private String language;
    private String level;

    public Language() {
        language = randomLang();
        level = randomLevel();
    }

    public String getLanguage() {
        return language;
    }

    public String getLevel() {
        return level;
    }
}
