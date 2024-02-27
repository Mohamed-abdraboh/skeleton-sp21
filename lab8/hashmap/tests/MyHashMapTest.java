package hashmap.tests;

import hashmap.Map61B;
import hashmap.MyHashMap;

public class MyHashMapTest {

    public static void main(String[] args) {
        Map61B<String, Integer> nameSalaryMap = new MyHashMap<>(2);

        nameSalaryMap.put("hi" + 1, 1);
        nameSalaryMap.clear();
        System.out.println(nameSalaryMap.get("hi" + 1));

    }
}
