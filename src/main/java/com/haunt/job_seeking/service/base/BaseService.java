package com.haunt.job_seeking.service.base;

import java.util.List;

public interface BaseService<T> {
  T create(T t);
  T update(T t);
  void delete(String id);
  T get(String id);
  List<T> list();
}
