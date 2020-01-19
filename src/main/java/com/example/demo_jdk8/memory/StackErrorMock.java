package com.example.demo_jdk8.memory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackErrorMock {

    private static int cnt = 1;

    public void call() {
        cnt++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        } catch (Throwable e) {
            log.info("stack deep：{}", cnt);
            log.info("error：", e);
        }
    }

}
