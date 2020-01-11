package com.example.demo_jdk8.myMap;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyHashMap<K, V> {

    private MapEntry[] table;

    private static Integer CAPACITY = 8;

    private int size = 0;

    public MyHashMap() {
        this.table = new MapEntry[CAPACITY];
    }

    @Data
    class MapEntry<K, V> {
        private K key;

        private V val;

        private MapEntry<K, V> next;

        public MapEntry(K k, V v, MapEntry<K, V> next) {
            this.key = k;
            this.val = v;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public Object get(Object key) {
        int hash = key.hashCode();
        int i = hash % 8;
        for (MapEntry<K, V> entry = table[i]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.val;
            }
        }
        return null;
    }

    public Object put(K key, V val) {
        int hash = key.hashCode();
        int i = hash % 8;
        for (MapEntry<K, V> entry = table[i]; entry != null; entry = entry.next) {
            if (entry.key == key) {
                V oldVal = entry.val;
                entry.val = val;
                return oldVal;
            }
        }
        addEntry(key, val, i);
        return null;
    }

    private void addEntry(K key, V val, int i) {
        MapEntry entry = new MapEntry(key, val, table[i]);
        table[i] = entry;
        size++;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("abc", "abc");
        log.info("map：{}", map.get("abc"));

        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            myHashMap.put("abc" + i, "abc" + i);
        }
        log.info("myHashMap：{}", myHashMap.get("abc5"));
    }

}
