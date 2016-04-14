package com.asker.springmvcjpa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.asker.springmvcjpa.dao.GenericDao;


public abstract  class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    @SuppressWarnings(value = "rawtypes")
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

	public long countAll(Map<String, Object> params) {
		final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(type.getSimpleName()).append(" o ");
//        queryString.append(type.getQueryClauses(params, null));

        final Query query = this.em.createQuery(queryString.toString());

        return (Long) query.getSingleResult();
	}

	public T create(T t) {
		 this.em.persist(t);
	        return t;
	}

	public void delete(Object id) {
		this.em.remove(this.em.getReference(type, id));
		
	}

	public T find(Object id) {
		return (T) this.em.find(type, id);
	}

	public T update(T t) {
		return this.em.merge(t); 
	}

}