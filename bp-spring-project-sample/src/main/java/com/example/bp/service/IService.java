package com.example.bp.service;

public interface IService<D> {

  D save(D model) throws Exception;

  D update(D model, Long id);

  D findById(Long id) throws Exception;

  Long deleteById(Long id);


}
