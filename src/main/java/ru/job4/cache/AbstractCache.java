package ru.job4.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V rsl = cache.getOrDefault(key, new SoftReference<>(null)).get();
        System.out.println("\nrsl = " + rsl);
        System.out.println("размер кэша " + cache.size());
        if (rsl == null) {
            System.out.println("Загрузка данных из файла");
            rsl = load(key);
            put(key, rsl);
        } else {
            System.out.println("Загрузка данных из кэша");
        }
        return rsl;
    }

    protected abstract V load(K key);

}
