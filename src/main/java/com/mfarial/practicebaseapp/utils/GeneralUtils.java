package com.mfarial.practicebaseapp.utils;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class GeneralUtils {

    public String generateToken(){

        return UUID.randomUUID().toString();
    }
}
