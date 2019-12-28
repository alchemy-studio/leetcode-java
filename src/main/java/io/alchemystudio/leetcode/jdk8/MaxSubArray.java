package io.alchemystudio.leetcode.jdk8;

import java.util.Arrays;
import java.util.OptionalInt;

public class MaxSubArray {

    public int process(int[] nums) {
        int maxSum = Integer.MIN_VALUE, length = 0;
        while (length < nums.length) {
            for (int i = 0; i <= nums.length - length; i++) {
                int[] subarray = Arrays.copyOfRange(nums, i, i + length);
                OptionalInt sum = Arrays.stream(subarray).reduce((a, c) -> a + c);
                if (sum.isPresent()) {
                    maxSum = Math.max(maxSum, sum.getAsInt());
                }
            }
            length++;
        }
        return maxSum;
    }
}
