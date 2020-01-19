package com.example.demo_jdk8.memory;

import java.util.ArrayList;
import java.util.List;

public class HeapOomMock {
    public static void main(String[] args) {
        String str = "abc";
        String ss = str.intern();

        List<byte[]> list = new ArrayList<byte[]>();
        Integer i = 0;
        boolean flag = true;
        while (flag){
            try {
                i++;
                list.add(new byte[1024 * 1024]);//每次增加一个1M大小的数组对象
            }catch (Throwable e){
                e.printStackTrace();
                flag = false;
                System.out.println("count="+i);//记录运行的次数
            }
        }
    }
}