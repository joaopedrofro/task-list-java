package model.dao;

import java.util.List;

public interface Dao<E> {

	void insert(E e);

	void update(E e);

	void delete(E e);

	List<E> getAll();

	E getById();

}
