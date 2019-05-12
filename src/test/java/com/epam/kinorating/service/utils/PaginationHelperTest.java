package com.epam.kinorating.service.utils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PaginationHelperTest {
    private final PaginationHelper paginationHelper = new PaginationHelper();

    private static final int DEFAULT_PAGES_COUNT = 5;
    private static final int DEFAULT_PAGE = 1;

    @DataProvider
    public static Object[][] correctPagesProvider() {
        return new Object[][]{
                new Object[]{"1", DEFAULT_PAGES_COUNT, 1},
                new Object[]{"2", 3, 2},
                new Object[]{"5", DEFAULT_PAGES_COUNT, 5}
        };
    }

    @DataProvider
    public static Object[][] wrongPagesProvider() {
        return new Object[][]{
                new Object[]{null, DEFAULT_PAGES_COUNT},
                new Object[]{"-1", DEFAULT_PAGES_COUNT},
                new Object[]{"10", 7},
                new Object[]{"dasdfs", 1},
                new Object[]{"0", 10}
        };
    }

    @Test(dataProvider = "correctPagesProvider")
    public void parsePageShouldReturnPageNumber(String pageString, int pages, int expected) {
        int result = paginationHelper.parsePageOrDefault(pageString, pages);

        Assert.assertEquals(result, expected);
    }

    @Test(dataProvider = "wrongPagesProvider")
    public void parsePageShouldReturnDefaultPageNumber(String pageString, int pages) {
        int result = paginationHelper.parsePageOrDefault(pageString, pages);

        Assert.assertEquals(result, DEFAULT_PAGE);
    }
}