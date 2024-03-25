package com.jinius.architecture.clean.apply.datasource;

import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.repository.ApplyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepository {

    @PersistenceContext
    private final EntityManager em;

    /**
     * @param userId
     * @return
     */
    @Override
    public List<Apply> findAllByUserId(Long userId) {
        return findAllByUserId(userId);
    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends Apply> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Apply> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Apply> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Apply getOne(Long aLong) {
        return null;
    }

    @Override
    public Apply getById(Long aLong) {
        return null;
    }

    @Override
    public Apply getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Apply> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Apply> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Apply> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Apply> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Apply> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Apply> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Apply, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Apply> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Apply> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Apply> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Apply> findAll() {
        return null;
    }

    @Override
    public List<Apply> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Apply entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Apply> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Apply> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Apply> findAll(Pageable pageable) {
        return null;
    }
}
