package com.example.demo_jdk8.idcard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdCardInfo {

    String idCard;

    String cipherText;

    String sm3Secret;
}
