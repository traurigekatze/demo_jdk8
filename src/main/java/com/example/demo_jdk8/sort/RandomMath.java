package com.example.demo_jdk8.sort;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class RandomMath {

    public static void main(String[] args) {
        List<Integer> numbers = random(100, 1, 100000);

        numbers.forEach(e -> {
            System.out.println(e);
        });

    }

    /**
     * 根据入参生成随机数
     * @param len 生成个数
     * @param startInclusive 开始区间
     * @param endExclusive 结束区间
     * @return
     */
    public static List<Integer> random(int len, int startInclusive, int endExclusive) {
        List<Integer> numbers = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            int number = RandomUtils.nextInt(startInclusive, endExclusive);
            numbers.add(number);
        }
        return numbers;
    }

}
