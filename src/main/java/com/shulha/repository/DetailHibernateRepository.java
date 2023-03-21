package com.shulha.repository;

import com.shulha.builder.DetailBuilder;
import com.shulha.config.FlywayUtil;
import com.shulha.config.HibernateFactoryUtil;
import com.shulha.model.Detail;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DetailHibernateRepository implements Repository<String, Detail> {
    private static final EntityManager ENTITY_MANAGER = HibernateFactoryUtil.getEntityManager();
    private static volatile DetailHibernateRepository instance;

    private DetailHibernateRepository() {
    }

    public static DetailHibernateRepository getInstance() {
        DetailHibernateRepository localInstance = instance;

        if (localInstance == null) {
            synchronized (DetailHibernateRepository.class) {
                localInstance = instance;

                if (localInstance == null) {
                    instance = localInstance = new DetailHibernateRepository();
                }
            }
        }

        FlywayUtil.getFlyway()
                .migrate();

        return instance;
    }

    @Override
    public void save(final Detail detail) {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.persist(detail);
        ENTITY_MANAGER.flush();
        ENTITY_MANAGER.getTransaction().commit();
    }

    @Override
    public List<Detail> getAll() {
        return ENTITY_MANAGER.createQuery("select d from Detail d", Detail.class)
                .getResultList();
    }

    @Override
    public Optional<Detail> getById(final String id) {
        final Detail detail = ENTITY_MANAGER.find(Detail.class, id);

        return Optional.ofNullable(detail);
    }

    @Override
    public void delete(final String id) {
        getById(id).ifPresent(detail -> {
            ENTITY_MANAGER.getTransaction().begin();
            ENTITY_MANAGER.remove(detail);
            ENTITY_MANAGER.getTransaction().commit();
        });
    }

    @Override
    public void removeAll() {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.createNativeQuery("delete from details;")
                .executeUpdate();
        ENTITY_MANAGER.getTransaction().commit();
    }
}
