package org.example.hashmap.impl;

import org.junit.jupiter.api.*;

import java.util.Objects;

/**
 * MyHashMap test.
 *
 * @author woonseok
 * @Date 2024-09-13
 * @since 1.0
 **/
class MyHashMapTest {




    //Todo 2. 구현한 MyHashMap을 적절히 테스트합니다.
    static class Temp{
        String name;
        int age;

        Temp(String name, int age){
            this.name = name;
            this.age = age;
        }


        @Override
        public boolean equals(Object obj) {
            if(Objects.isNull(obj)){
                return false;
            }

            Temp temp = (Temp)obj;

            return this.name.equals(temp.name) && this.age == temp.age;
        }

        @Override
        public int hashCode() {
            int result= age;
            result = result * 31 + name.hashCode();
            return result;
        }
    }
    MyHashMap<Temp, Integer> myHashMap;

    @BeforeEach
    void setUp() {
        myHashMap = new MyHashMap<>();

    }

    @AfterEach
    void claer(){
        myHashMap.clear();
    }

    @Test
    @DisplayName("put 1")
    void put() {
        Temp temp = new Temp("yh", 25);
        myHashMap.put(temp, 25);

        Assertions.assertEquals(1, myHashMap.size());


    }

    @Test
    void put2(){
        Temp temp = new Temp("yh", 25);
        myHashMap.put(temp, 25);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myHashMap.put(temp, 25);
        });

//    Assertions.assertEquals(2, myHashMap.size());
    }

    @Test

    void containsKey() {
        Temp temp = new Temp("yh", 25);
        myHashMap.put(temp, 25);

        boolean actual = myHashMap.containsKey(temp);
        Assertions.assertTrue(actual);
    }

    @Test
    void containValue(){
        Temp temp = new Temp("yh", 25);
        myHashMap.put(temp, 25);

        boolean actual = myHashMap.containsValue(25);
        Assertions.assertTrue(actual);


    }


    @Test
    void remove(){
        Temp temp = new Temp("yh", 25);
        myHashMap.put(temp, 25);
        myHashMap.remove(temp);

        Assertions.assertEquals(0, myHashMap.size() );
    }
    @Test
    void clear() {

        Assertions.assertEquals(0, myHashMap.size());

    }

    @Test
    void size() {
        Temp temp = new Temp("yh", 25);
        myHashMap.put(temp, 25);

        Assertions.assertEquals(1, myHashMap.size());
    }

    @Test
    void get(){
        Temp temp = new Temp("yh", 25);
        myHashMap.put(temp, 25);

        Assertions.assertEquals(25, myHashMap.get(temp));
    }



}