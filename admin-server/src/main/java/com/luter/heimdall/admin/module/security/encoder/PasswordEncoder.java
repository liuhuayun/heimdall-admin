package com.luter.heimdall.admin.module.security.encoder;

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);


    boolean matches(CharSequence rawPassword, String encodedPassword);
}
