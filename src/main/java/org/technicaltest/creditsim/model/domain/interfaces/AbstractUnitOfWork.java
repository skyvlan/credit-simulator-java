package org.technicaltest.creditsim.model.domain.interfaces;

public interface AbstractUnitOfWork {
    void commit();
    void rollback();
}
