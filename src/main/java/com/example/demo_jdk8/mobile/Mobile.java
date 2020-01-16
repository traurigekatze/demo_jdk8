package com.example.demo_jdk8.mobile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Mobile {

    String mobile;

    String cipherText;

    String sm3Secret;
}
