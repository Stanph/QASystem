package com.j2ee.test;

import com.auth0.jwt.interfaces.Claim;
import com.j2ee.util.JwtUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JwtTest {
    @Test
    public void JwtTest() throws Exception {
        String jwt = JwtUtil.createToken("31601112");
        System.out.println(jwt);
        Map<String, Claim> a = JwtUtil.verifyToken(jwt);
        System.out.println(a);
        System.out.println(JwtUtil.getAppUID(jwt));
    }

}
