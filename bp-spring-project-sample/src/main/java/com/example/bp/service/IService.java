package com.example.bp.service;

import java.util.List;

public interface IService<D> {

  D save(D model) throws Exception;

  D update(D model, Long id);

  D findById(Long id) throws Exception;
  
  List<D> findAll();

  Long deleteById(Long id);


}
