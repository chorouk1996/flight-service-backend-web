package com.service.backend.web.repositories;


import com.service.backend.web.models.entities.AuditLog;
import com.service.backend.web.models.requests.SearchAuditRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.service.backend.web.services.helper.DateHelper.convertStringToEndDateTime;
import static com.service.backend.web.services.helper.DateHelper.convertStringToStartDateTime;


@Repository
public class AuditLogCustomRepository {

    private static final String TIMESTAMP = "timestamp";

    @PersistenceContext
    EntityManager entityManager;

    public List<AuditLog> findByCriteria(SearchAuditRequest criteria) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditLog> criteriaQuery = builder.createQuery(AuditLog.class);
        Root<AuditLog> auditLog = criteriaQuery.from(AuditLog.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getEntityType() != null)
            predicates.add(builder.equal(auditLog.get("entityType"), criteria.getEntityType()));
        if (criteria.getAction() != null)
            predicates.add(builder.equal(auditLog.get("action"), criteria.getAction()));
        if (criteria.getStartDate() != null && criteria.getEndDate() == null) {
            LocalDateTime start = convertStringToStartDateTime(criteria.getStartDate());
            predicates.add(builder.greaterThanOrEqualTo(auditLog.get(TIMESTAMP), start));
        } else if (criteria.getEndDate() != null && criteria.getStartDate() == null) {
            LocalDateTime end = convertStringToEndDateTime(criteria.getEndDate());
            predicates.add(builder.lessThanOrEqualTo(auditLog.get(TIMESTAMP), end));
        } else if (criteria.getEndDate() != null && criteria.getStartDate() != null) {
            LocalDateTime end = convertStringToEndDateTime(criteria.getEndDate());
            LocalDateTime start = convertStringToStartDateTime(criteria.getStartDate());

            predicates.add(builder.between(auditLog.get(TIMESTAMP), end, start));
        }

        criteriaQuery.where(predicates.toArray(predicates.toArray(new Predicate[0])));


        return entityManager.createQuery(criteriaQuery).setFirstResult((criteria.getPage()) * criteria.getSize()).setMaxResults(criteria.getSize()).getResultList();
    }
}
