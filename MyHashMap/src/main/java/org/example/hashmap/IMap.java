package org.example.hashmap;

/**
 * IMap.
 *
 * @author woonseok
 * @Date 2024-09-13
 * @since 1.0
 **/
public interface IMap<K, V> {
  void clear();
  boolean containsKey(K key);
  boolean containsValue(V value);
  V get(K key);
  boolean isEmpty();
  V put(K key, V value);
  void remove(K key);
  int size();
}
