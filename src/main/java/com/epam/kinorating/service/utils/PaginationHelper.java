package com.epam.kinorating.service.utils;

public class PaginationHelper {
    public boolean isValidPageNumber(int pageCount, int page) {
        return page <= pageCount && page > 0;
    }
}
