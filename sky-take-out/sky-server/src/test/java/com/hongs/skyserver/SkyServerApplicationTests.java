package com.hongs.skyserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class SkyServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPassword() {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

}
