package io.alchemystudio.leetcode.test.jdk8;

import io.alchemystudio.leetcode.jdk8.MaxSubArray;
import io.alchemystudio.leetcode.util.Helper;
import org.junit.jupiter.api.Test;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSubArrayTest {

    private MaxSubArray maxSubArray = new MaxSubArray();

    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    private String getResourceFile(String filename) {
        return Objects.requireNonNull(loader.getResource(filename)).getFile();
    }

    @Test
    public void testSmallArray() {
        assertEquals(1, maxSubArray.process(new int[]{1}));
        assertEquals(3, maxSubArray.process(new int[]{1, 2}));
        assertEquals(1, maxSubArray.process(new int[]{1, -1}));
        assertEquals(6, maxSubArray.process(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    public void test10000Array() {

        int[] nums10000 = Helper.ReadLargeIntArray(getResourceFile("10000-ints-array.txt"));
        long start = System.currentTimeMillis();
        assertEquals(11081, maxSubArray.process(nums10000));
        long end = System.currentTimeMillis();
        System.out.println("1000 length ints : " + (end - start) + "ms");
    }

    @Test
    public void test15000Array() {
        int[] nums10000 = Helper.ReadLargeIntArray(getResourceFile("15000-ints-array.txt"));
        long start = System.currentTimeMillis();
        assertEquals(9096, maxSubArray.process(nums10000));
        long end = System.currentTimeMillis();
        System.out.println("1000 length ints : " + (end - start) + "ms");
    }
}
