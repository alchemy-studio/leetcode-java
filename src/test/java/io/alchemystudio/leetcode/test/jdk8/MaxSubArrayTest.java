package io.alchemystudio.leetcode.test.jdk8;

import io.alchemystudio.leetcode.jdk8.MaxSubArray;
import io.alchemystudio.leetcode.util.Helper;
import org.junit.jupiter.api.Test;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSubArrayTest {

    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    private String getResourceFile(String filename) {
        return Objects.requireNonNull(loader.getResource(filename)).getFile();
    }

    @Test
    public void testSmallArray() {

        assertEquals(1, MaxSubArray.process(new int[]{1}));
        assertEquals(-1, MaxSubArray.process(new int[]{-1}));
        assertEquals(-1, MaxSubArray.process(new int[]{-1, -2}));
        assertEquals(3, MaxSubArray.process(new int[]{1, 2}));
        assertEquals(28, MaxSubArray.process(new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(1, MaxSubArray.process(new int[]{1, -1}));
        assertEquals(0, MaxSubArray.process(new int[]{-1, -2, 0, -3, -4}));
        assertEquals(6, MaxSubArray.process(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(87, MaxSubArray.process(new int[]{-57, 9, -72, -72, -62, 45, -97, 24, -39, 35, -82, -4, -63, 1, -93, 42, 44, 1, -75, -25, -87, -16, 9, -59, 20}));
    }

    @Test
    public void test10000Array() {
        int[] nums10000 = Helper.ReadLargeIntArray(getResourceFile("10000-ints-array.txt"));
        long start = System.currentTimeMillis();
        assertEquals(11081, MaxSubArray.process(nums10000));
        long end = System.currentTimeMillis();
        System.out.println("10000 length ints : " + (end - start) + "ms");
    }

    @Test
    public void test15000Array() {
        int[] nums15000 = Helper.ReadLargeIntArray(getResourceFile("15000-ints-array.txt"));
        long start = System.currentTimeMillis();
        assertEquals(9096, MaxSubArray.process(nums15000));
        long end = System.currentTimeMillis();
        System.out.println("15000 length ints : " + (end - start) + "ms");
    }

    @Test
    public void testBiList(){
        assertEquals(1, MaxSubArray.processWithBiList(new int[]{1}));
        assertEquals(-1, MaxSubArray.processWithBiList(new int[]{-1}));
        assertEquals(-1, MaxSubArray.processWithBiList(new int[]{-1, -2}));
        assertEquals(3, MaxSubArray.processWithBiList(new int[]{1, 2}));
        assertEquals(28, MaxSubArray.processWithBiList(new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(1, MaxSubArray.processWithBiList(new int[]{1, -1}));
        assertEquals(0, MaxSubArray.processWithBiList(new int[]{-1, -2, 0, -3, -4}));
        assertEquals(6, MaxSubArray.processWithBiList(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(87, MaxSubArray.processWithBiList(new int[]{-57, 9, -72, -72, -62, 45, -97, 24, -39, 35, -82, -4, -63, 1, -93, 42, 44, 1, -75, -25, -87, -16, 9, -59, 20}));

        int[] nums10000 = Helper.ReadLargeIntArray(getResourceFile("10000-ints-array.txt"));
        long start = System.currentTimeMillis();
        assertEquals(11081, MaxSubArray.processWithBiList(nums10000));
        long end = System.currentTimeMillis();
        System.out.println("10000 length ints : " + (end - start) + "ms");

        int[] nums15000 = Helper.ReadLargeIntArray(getResourceFile("15000-ints-array.txt"));
        start = System.currentTimeMillis();
        assertEquals(9096, MaxSubArray.processWithBiList(nums15000));
        end = System.currentTimeMillis();
        System.out.println("15000 length ints : " + (end - start) + "ms");
    }
}
