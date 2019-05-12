package com.epam.kinorating.service.utils;

import org.apache.commons.validator.routines.IntegerValidator;

public class PaginationHelper {
    private static final int DEFAULT_PAGE = 1;

    private boolean isValidPageNumber(int pageCount, int page) {
        return page <= pageCount && page > 0;
    }

    public int parsePageOrDefault(String pageString, int pages) {
        Integer page = IntegerValidator.getInstance().validate(pageString);

        if (page == null || !isValidPageNumber(pages, page)) {
            page = DEFAULT_PAGE;
        }

        return page;
    }
}
