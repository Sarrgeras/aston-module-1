package org.example;

public class Main {
    public static void main(String[] args) {
        HashMapSample<Object, Object> hashMap = new HashMapSample<>();

        hashMap.put("Vlad", 18);
        hashMap.put("Misha", 22);
        hashMap.put("Vladimir", 43);
        hashMap.put("Gleb", 65);
        hashMap.put("Albert", 1);

        System.out.println(hashMap.get("Vlad"));
        System.out.println(hashMap.get("Albert"));
        System.out.println(hashMap.get("Misha"));


        hashMap.remove("Vlad");
        hashMap.remove("Albert");
        hashMap.remove("Misha");

        System.out.println(hashMap.get("Vlad"));
        System.out.println(hashMap.get("Albert"));
        System.out.println(hashMap.get("Misha"));
    }
}