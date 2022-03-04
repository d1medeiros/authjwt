package com.example.demo;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

public abstract class TestUtil {
    public static String readJsonFile(String file) throws IOException {
        String path = "__files/" + file;
        return new String(Files.readAllBytes(new ClassPathResource(path).getFile().toPath()));
    }
}
