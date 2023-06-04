package ru.job4.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DirFileCache extends  AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public String getCachingDir() {
        return cachingDir;
    }

    @Override
    public void put(String key, String value) {
        super.put(key, value);
    }

    @Override
    public String get(String key) {
        return super.get(key);
    }

    @Override
    protected String load(String key) {
        StringBuilder rsl = new StringBuilder();
        Path file = Paths.get(new StringBuilder().append(cachingDir).append("/").append(key).append(".txt").toString());
        try (BufferedReader read = new BufferedReader(new FileReader(file.toFile(), StandardCharsets.UTF_8))) {
            while (read.ready()) {
                rsl.append(read.readLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = rsl.toString();
        return str;
    }

}
