package io.alchemystudio.leetcode.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class MaxSubArray {

    public static int process(int[] nums) {
        int maxSum = Integer.MIN_VALUE, curSum = 0;
        for (int num : nums) {
            curSum = Math.max(curSum + num, num);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    public static int processWithBiList(int[] nums) {
        BiList<Integer> list = new BiListImpl<>();
        for (int num : nums) {
            list.push(num);
        }
        Just<Integer> maxSum = new Just<>();
        maxSum.setObj(Integer.MIN_VALUE);

        if (list.every(n -> (Integer) n <= 0))
            return (int) list.reduce((p, c) -> Math.max((int) p, (int) c)).get();

        while (list.length() > 1) {
            list = loop(list, maxSum);
        }

        return Math.max(list.get(0), maxSum.getObj());
    }

    static BiList<Integer> loop(BiList<Integer> nums, Just<Integer> maxSum) {
        int num = nums.shift();
        while (num <= 0 && nums.length() > 0) {
            num = nums.shift();
        }
        nums.unshift(num);
        // step 2: trim all negative numbers in tail
        num = nums.pop();
        while (num <= 0 && nums.length() > 0) {
            num = nums.pop();
        }
        nums.push(num);

        Integer left = 0, n = nums.shift(), right = 0;
        // step 3: sum left nums
        while (n != null && n >= 0) {
            left += n;
            n = nums.shift();
        }
        if (n != null) nums.unshift(n);
        if (left > 0) nums.unshift(left);

        maxSum.setObj(Math.max(left, maxSum.getObj()));

        if (n != null) {
            // System.out.println("// n must be < 0: " + n);
            if (left + n > 0) {
                nums = new BiListImpl<>(left + n, nums.slice(2));
            } else {
                nums = nums.slice(2);
            }
        }
        // step 4: sum right nums
        n = nums.pop();
        while (n != null && n >= 0) {
            right += n;
            n = nums.pop();
        }
        if (n != null) nums.push(n);
        if (right > 0) nums.push(right);
        maxSum.setObj(Math.max(right, maxSum.getObj()));
        if (n != null) {
            // System.out.println("// n must be < 0: " + n);
            if (right + n > 0) {
                nums = new BiListImpl<>(nums.slice(0, nums.length() - 2), right + n);
            } else {
                nums = nums.slice(0, nums.length() - 2);
            }
        }
        return nums;
    }

    static class Just<T> {
        private T obj;

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }
    }

    interface BiList<T> {
        void lpush(T obj);

        T lpop();

        void rpush(T obj);

        T rpop();

        T shift();

        void unshift(T obj);

        T pop();

        void push(T obj);

        int length();

        Optional reduce(BinaryOperator bifunc);

        BiList<T> slice(int start, int end);

        BiList<T> slice(int start);

        BiList<T> slice();

        boolean every(Predicate p);

        List copyStore();

        void dump();

        T get(int idx);
    }

    static class BiListImpl<T> implements BiList<T> {


        @Override
        public T get(int idx) {
            if (store.size() == 0)
                return null;
            if (idx < 0 || idx > store.size() - 1)
                return null;
            return (T) store.get(idx);
        }

        @Override
        public List copyStore() {
//            return List.of(store.toArray());
            List lst = new ArrayList();
            Collections.addAll(lst, store.toArray());
            return lst;
        }

        private List<Object> store = new ArrayList<>();

        BiListImpl(T obj, BiList<T> subList) {
            store = subList.copyStore();
            this.lpush(obj);
        }

        BiListImpl(BiList<T> subList, T obj) {
            store = subList.copyStore();
            this.rpush(obj);
        }

        BiListImpl(List<Object> subList) {
            store = subList;
        }

        BiListImpl() {

        }

        @Override
        public void lpush(T obj) {
            store.add(0, obj);
        }

        @Override
        public T lpop() {
            if (store.size() == 0)
                return null;
            T obj = (T) store.get(0);
            store.remove(0);
            return obj;
        }

        @Override
        public void rpush(T obj) {
            try {
                store.add(obj);
            } catch (UnsupportedOperationException e) {
                // System.out.println(e);
            }
        }

        @Override
        public T rpop() {
            if (store.size() == 0)
                return null;
            T obj = (T) store.get(store.size() - 1);
            store.remove(store.size() - 1);
            return obj;
        }

        @Override
        public T shift() {
            return lpop();
        }

        @Override
        public void unshift(T obj) {
            lpush(obj);
        }


        @Override
        public T pop() {
            return rpop();
        }

        @Override
        public void push(T obj) {
            rpush(obj);
        }

        @Override
        public int length() {
            return store.size();
        }

        @Override
        public Optional reduce(BinaryOperator biFunc) {
            return Arrays.stream(store.toArray()).reduce(biFunc);
        }

        @Override
        public BiList<T> slice(int start, int end) {
            if (store.size() == 0) {
                return new BiListImpl<T>();
            }
            if (start > store.size()) {
                return new BiListImpl<T>();
            }
            if (end > store.size()) {
                end = store.size();
            }
            return new BiListImpl(store.subList(start, end));
        }

        @Override
        public BiList<T> slice(int start) {
            return slice(start, store.size());
        }

        @Override
        public BiList<T> slice() {
            return slice(0);
        }

        @Override
        public boolean every(Predicate p) {
            return Arrays.stream(store.toArray()).allMatch(p);
        }

        @Override
        public void dump() {
            System.out.print(":::DUMP::: ");
            for (Object obj : store) {
                System.out.print(obj.toString());
                System.out.print(", ");
            }
        }
    }

}
