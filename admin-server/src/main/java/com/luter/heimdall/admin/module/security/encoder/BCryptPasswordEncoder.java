package com.luter.heimdall.admin.module.security.encoder;

import cn.hutool.crypto.digest.BCrypt;
import com.luter.heimdall.core.exception.HeimdallException;
import com.luter.heimdall.core.utils.StrUtils;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        if (StrUtils.isEmpty(rawPassword)) {
            throw new HeimdallException("密码不能为空");
        }
        return BCrypt.hashpw(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (StrUtils.isEmpty(rawPassword) || StrUtils.isEmpty(encodedPassword)) {
            throw new HeimdallException("明文密码或者密文密码均不能为空");
        }
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }


}