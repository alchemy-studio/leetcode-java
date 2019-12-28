package io.alchemystudio.leetcode.jdk6;

import java.util.Arrays;

public class MaxSubArray {
    public int process(int[] nums) {
        int maxSum = Integer.MIN_VALUE, length = 0;
        while (length <= nums.length) {
            for (int i = 0; i <= nums.length - length; i++) {
                int[] subarray = Arrays.copyOfRange(nums, i, i + length);
                if (subarray.length > 0) {
                    int sum = 0;
                    for (int j = 0; j < subarray.length; j++) {
                        sum += subarray[j];
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
            }
            length++;
        }
        return maxSum;
    }
}
