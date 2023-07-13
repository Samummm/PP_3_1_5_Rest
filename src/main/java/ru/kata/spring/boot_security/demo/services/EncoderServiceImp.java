package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncoderServiceImp implements EncoderService{

    private PasswordEncoder passwordEncoder;

    @Autowired
    public EncoderServiceImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encoder(String unencoded) {
        return passwordEncoder.encode(unencoded);
    }
}
