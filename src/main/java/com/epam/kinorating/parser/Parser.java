package com.epam.kinorating.parser;

public interface Parser<T> {
    T parse(String value);
}
