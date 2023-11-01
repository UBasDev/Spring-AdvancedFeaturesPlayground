package org.ucbdev.Core.Application.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.metamodel.RepresentationMode;
import org.hibernate.metamodel.spi.EntityRepresentationStrategy;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;
import org.ucbdev.Core.Domain.Entities.Customer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;

@Slf4j
@Component
public class HibernateInterceptor1 implements Interceptor, Serializable {
    public HibernateInterceptor1(){
        log.info("HibernateInterceptor1 has been added to IOC");
    }
    private static void findPropertyIndex1(String columnToSearch, String[] propertyNames, Object[] currentState) {
        for (short i = 0; i < propertyNames.length; i++) {
            if (columnToSearch.equals(propertyNames[i])) {
                currentState[i] = LocalDateTime.now(); //İstediğimiz columnu bulacak ve şuanın zamanını set edecek.
                break;
            }
        }
    }
    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        var x1 = "";
        return Interceptor.super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        var x1 = "";
        return Interceptor.super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        var x1 = "";
        if(entity instanceof Customer){
            var customerEntity = (Customer)entity;
            log.info("New customer has been created: {} - {} - {}", customerEntity.getName(), customerEntity.getLastname(), customerEntity.getAge());
        }
        return Interceptor.super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        var x1 = "";
        Interceptor.super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public void onCollectionRecreate(Object collection, Object key) throws CallbackException {
        Interceptor.super.onCollectionRecreate(collection, key);
    }

    @Override
    public void onCollectionRemove(Object collection, Object key) throws CallbackException {
        Interceptor.super.onCollectionRemove(collection, key);
    }

    @Override
    public void onCollectionUpdate(Object collection, Object key) throws CallbackException {
        Interceptor.super.onCollectionUpdate(collection, key);
    }

    @Override
    public void preFlush(Iterator<Object> entities) throws CallbackException {
        Interceptor.super.preFlush(entities);
    }

    @Override
    public void postFlush(Iterator<Object> entities) throws CallbackException {
        Interceptor.super.postFlush(entities);
    }

    @Override
    public Boolean isTransient(Object entity) {
        return Interceptor.super.isTransient(entity);
    }

    @Override
    public int[] findDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        return Interceptor.super.findDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public Object instantiate(String entityName, EntityRepresentationStrategy representationStrategy, Object id) throws CallbackException {
        return Interceptor.super.instantiate(entityName, representationStrategy, id);
    }

    @Override
    public Object instantiate(String entityName, RepresentationMode representationMode, Object id) throws CallbackException {
        return Interceptor.super.instantiate(entityName, representationMode, id);
    }

    @Override
    public String getEntityName(Object object) throws CallbackException {
        return Interceptor.super.getEntityName(object);
    }

    @Override
    public Object getEntity(String entityName, Object id) throws CallbackException {
        return Interceptor.super.getEntity(entityName, id);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        Interceptor.super.afterTransactionBegin(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        Interceptor.super.beforeTransactionCompletion(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        Interceptor.super.afterTransactionCompletion(tx);
    }
}
