package com.example.demo_jdk8.sort;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Sort {

    private static final Integer len = 100000;

    private static final Integer startInclusive = 10000;

    private static final Integer endExclusive = 10000000;

    public static void main(String[] args) {
        List<Integer> numbers = RandomMath.random(len, startInclusive, endExclusive);
        log.info("numbers：{}", JSON.toJSONString(numbers));

        List<Integer> finalNumbers = numbers;
        List<Integer> bubble = new ArrayList<Integer>() { { addAll(finalNumbers); } };
//        bubbleSort(bubble);

        List<Integer> selection = new ArrayList<Integer>() { { addAll(finalNumbers); } };
//        selectionSort(selection);

        List<Integer> insertion = new ArrayList<Integer>() { { addAll(finalNumbers); } };
        insertionSort(insertion);

    }

    /**
     * 冒泡排序
     * @param numbers
     */
    public static void bubbleSort(List<Integer> numbers) {
//        log.info("numbers：{}", JSON.toJSONString(numbers));
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i+1; j < numbers.size() ; j++) {
                if (numbers.get(i) > numbers.get(j)) {
                    int num = numbers.get(i);
                    numbers.set(i, numbers.get(j));
                    numbers.set(j, num);
                }
            }
        }
        log.info("mp sort use：{}", (System.currentTimeMillis() - startTime));
//        log.info("numbers：{}", JSON.toJSONString(numbers));
    }

    /**
     * 选择排序
     * @param numbers
     */
    public static void selectionSort(List<Integer> numbers) {
//        log.info("numbers：{}", JSON.toJSONString(numbers));
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numbers.size(); i++) {
            int minIndex = i;
            for (int j = i+1; j < numbers.size() ; j++) {
                if (numbers.get(i) > numbers.get(j)) {
                    minIndex = j;
                }
            }
            int num = numbers.get(i);
            numbers.set(i, numbers.get(minIndex));
            numbers.set(minIndex, num);
        }
        log.info("selection sort use：{}", (System.currentTimeMillis() - startTime));
//        log.info("numbers：{}", JSON.toJSONString(numbers));
    }

    /**
     * 插入排序
     * @param numbers
     */
    public static void insertionSort(List<Integer> numbers) {
//        log.info("numbers：{}", JSON.toJSONString(numbers));
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numbers.size() - 1; i++) {
            // 默认第一个是有序的，每次拿后一个往前在排好序的list里面比对排序
            int current = numbers.get(i+1); // 当前需要比对的（当前index的下一个）
            int preIndex = i;
            // current < numbers.get(i) ：需要比对的 < 有序列表中的最后一个
            while (preIndex >= 0 && current < numbers.get(preIndex)) {
                numbers.set(preIndex + 1, numbers.get(preIndex));
                preIndex--;
            }
            numbers.set(preIndex + 1, current);
        }
        log.info("insertion sort use：{}", (System.currentTimeMillis() - startTime));
//        log.info("numbers：{}", JSON.toJSONString(numbers));
    }

    /**
     * 希尔排序
     * @param numbers
     */
    public static void shellSort(List<Integer> numbers) {
        long startTime = System.currentTimeMillis();
        log.info("insertion sort use：{}", (System.currentTimeMillis() - startTime));
//        log.info("numbers：{}", JSON.toJSONString(numbers));
    }

}
