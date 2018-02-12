package com.revature.caliber.repository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimpleBatch;
/**
 * 
 * This is a custom implementation for repository methods
 * 
 * For Spring JPA 1.x you need to name it **RepositoryImpl
 * For Spring JPA 2.x you need to name it after your custom interface with Impl suffix
 * 
 * @author Tzu
 *
 */
@Repository
public class BatchRepositoryImpl implements BatchRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;
	
	private ZonedDateTime now =ZonedDateTime.now();
	private ZonedDateTime aMonthAgo= now.minusDays(30);
	@Override
	public List<SimpleBatch> findAllCurrent() {
		CriteriaBuilder cb= entityManager.getCriteriaBuilder();
		CriteriaQuery<SimpleBatch> q=cb.createQuery(SimpleBatch.class);
		Root<SimpleBatch> b = q.from(SimpleBatch.class);
		q.select(b).where(
			cb.and(
				cb.between(b.get("startDate"), Date.from(aMonthAgo.toInstant()), Date.from(now.toInstant())),
				cb.greaterThan(b.get("endDate"), Date.from(now.toInstant()))
			)
		);
		return entityManager.createQuery(q).getResultList();
	}

	@Override
	public List<SimpleBatch> findAllCurrent(int trainerId) {
		CriteriaBuilder cb= entityManager.getCriteriaBuilder();
		CriteriaQuery<SimpleBatch> q=cb.createQuery(SimpleBatch.class);
		Root<SimpleBatch> b = q.from(SimpleBatch.class);
		q.select(b).where(
			cb.and(
				cb.between(b.get("startDate"), Date.from(aMonthAgo.toInstant()), Date.from(now.toInstant())),
				cb.greaterThan(b.get("endDate"), Date.from(now.toInstant())),
				cb.or(
					cb.equal(b.get("trainerId"), trainerId),
					cb.equal(b.get("coTrainerId"), trainerId)
				)
			)
		);
		return entityManager.createQuery(q).getResultList();
	}

}
