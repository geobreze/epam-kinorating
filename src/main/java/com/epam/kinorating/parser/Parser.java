package com.epam.kinorating.parser;

public interface Parser<T> {
    public T parse(String value);
}
