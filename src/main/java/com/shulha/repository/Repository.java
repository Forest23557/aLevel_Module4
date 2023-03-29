package com.shulha.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<K, V> {
    void save(V object);
    List<V> getAll();
    Optional<V> getById(K identifier);
    void delete(K identifier);
    void removeAll();
}
