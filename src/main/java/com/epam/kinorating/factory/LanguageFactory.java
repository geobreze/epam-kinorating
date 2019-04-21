package com.epam.kinorating.parser;

import com.epam.kinorating.model.entity.Language;

public class LanguageParser implements Parser<Language> {
    //TODO move to factory

    private static final Language DEFAULT_LANGUAGE = Language.EN;

    @Override
    public Language parse(String value) {
        for (Language language : Language.values()) {
            if (language.name().equals(value)) {
                return Language.valueOf(value);
            }
        }
        return DEFAULT_LANGUAGE;
    }
}
