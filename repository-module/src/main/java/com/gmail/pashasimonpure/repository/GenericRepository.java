package com.gmail.pashasimonpure.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<I, T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    List<T> findAll();

}