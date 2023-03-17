package com.shulha.repository;

import java.util.List;

public interface Repository<K, V> {
    void save(V object);
    List<V> getAll();
    V getById(K identifier);
    void delete(K identifier);
    void removeAll();
}
