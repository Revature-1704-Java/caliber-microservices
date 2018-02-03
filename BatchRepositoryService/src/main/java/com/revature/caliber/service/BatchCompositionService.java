package com.revature.caliber.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Note;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleNote;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.BatchRepository;
@Service
public class BatchCompositionService {
	@Autowired
	private BatchRepository repo;
	@Autowired
	private BatchCompositionMessageService BCMS;
	
	public void save(Batch batch) {
		
	}
	
	public void update(Batch batch) {
		
	}
	
	public void delete(Batch batch) {
		
	}
	
	public Batch findOne(Integer batchId) {
		return composeBatch(repo.findOne(batchId), false);
		
	}
	public Batch findOneWithDroppedTrainees(Integer batchId) {
		return composeBatch(repo.findOne(batchId), true);
	}
	//needs refactoring;
	public Batch findOneWithTraineesAndGrades(Integer batchId) {
		return composeBatch(repo.findOne(batchId), false);
	}
	public List<Batch> findAll() {
		return composeListOfBatch(repo.findAll(), false, false, false);
	}
	public List<Batch> findAllByTrainer(Integer trainerId) {
		return composeListOfBatch(repo.findAllByTrainerId(trainerId), false, false, false);
	}
	
	public List<Batch> findAllCurrent() {
		return composeListOfBatch(repo.findAllCurrent(),false, true ,false);	
	}
	
	public List<Batch> findAllCurrent(Integer trainerId) {
		return composeListOfBatch(repo.findAllCurrent(trainerId),false, true ,true);	
	}
	public List<Batch> findAllCurrentWithNotes() {
		return composeListOfBatch(repo.findAllCurrent(),false, true, true);	
	}
	
	public List<Batch> findAllCurrentWithTrainees() {
		return composeListOfBatch(repo.findAllCurrent(),false, true, true);
	}
	
	public List<Batch> findAllCurrentWithNotesAndTrainees() {
		return composeListOfBatch(repo.findAllCurrent(),false, true, true);
	}
	
	public List<Batch> findAllAfterDate(Integer month, Integer day, Integer year) {
		Date date=Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
		return composeListOfBatch(repo.findAllByStartDateAfter(date), false, true, true);
	}
	
	private Set<Trainee> getBatchTrainees(int batchId, boolean includeDropped){
		List<SimpleTrainee> simpleTList = BCMS.sendListSimpleTraineeRequest(batchId);
		return simpleTList.stream()
				.filter(s->includeDropped||s.getTrainingStatus()!=TrainingStatus.Dropped)
				.map(t->new Trainee(t))
				.collect(Collectors.toSet());
	}
	
	public Set<Note> getBatchNotes(int batchId){
		List<SimpleNote> simpleNList = BCMS.sendListSimpleNoteRequest(batchId);
		return simpleNList.stream()
				.map(n-> new Note(n))
				.collect(Collectors.toSet());
	}
	
	private List<Batch> composeListOfBatch(List<SimpleBatch> src, boolean includeDropped, boolean withTrainees, boolean withNotes){
		if(withTrainees&&withNotes){
			return src.stream().map(b->composeBatch(b, includeDropped)).collect(Collectors.toList());
		}else{
			List<Batch> batches = src.stream().map(b-> new Batch(b)).collect(Collectors.toList());
			if(withTrainees){
				return batches.stream()
				.map(b-> {
					b.setTrainees(getBatchTrainees(b.getBatchId(), includeDropped));
					return b;
				}).collect(Collectors.toList());
			}else if(withNotes) {
				return batches.stream().map(b->{
					b.setNotes(getBatchNotes(b.getBatchId()));
					return b;
				}).collect(Collectors.toList());
			}else{
				return batches;
			}
		}
	}
	private Batch composeBatch(SimpleBatch src, boolean includeDropped) {
		
		Batch b= new Batch(src);
		b.setTrainees(getBatchTrainees(src.getBatchId(), includeDropped));
		b.setNotes(getBatchNotes(src.getBatchId()));
		return b;	
	}
//	/**
//	 * Save a batch to the database.
//	 * 
//	 * @param batch
//	 */
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void save(Batch batch) {
//		log.info("Saving Batch " + batch);
//		sessionFactory.getCurrentSession().save(batch);
//	}
//
//	/**
//	 * Looks for all batches without any restriction. Likely to only be useful for
//	 * calculating reports.
//	 * 
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAll() {
//		log.info("Fetching all batches");
//		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.add(Restrictions.or(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped),
//						Restrictions.isNull(T_TRAINING_STATUS)))
//				.addOrder(Order.desc(START_DATE))
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//	}
//
//	/**
//	 * Looks for all batches where the user was the trainer or co-trainer.
//	 * 
//	 * @param auth
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAllByTrainer(Integer trainerId) {
//		log.info("Fetching all batches for trainer: " + trainerId);
//		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.add(Restrictions.or(Restrictions.eq("trainer.trainerId", trainerId),
//						Restrictions.eq("coTrainer.trainerId", trainerId)))
//				.addOrder(Order.desc(START_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//		batches.parallelStream()
//				.forEach(b -> b.getTrainees().removeIf(t -> t.getTrainingStatus().equals(TrainingStatus.Dropped)));
//		return batches;
//	}
//
//	/**
//	 * Looks for all batches where the user was the trainer or co-trainer. Batches
//	 * returned are currently actively in training.
//	 * 
//	 * @param auth
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAllCurrent(Integer trainerId) {
//		log.info("Fetching all current batches for trainer: " + trainerId);
//		Calendar endDateLimit = Calendar.getInstance();
//		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
//		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.add(Restrictions.or(Restrictions.eq("trainer.trainerId", trainerId),
//						Restrictions.eq("coTrainer.trainerId", trainerId)))
//				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
//				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).addOrder(Order.desc(START_DATE))
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//		batches.parallelStream()
//				.forEach(b -> b.getTrainees().removeIf(t -> t.getTrainingStatus().equals(TrainingStatus.Dropped)));
//		return batches;
//	}
//
//	/**
//	 * Looks for all batches that are currently actively in training including
//	 * trainees, notes and grades
//	 * 
//	 * @param auth
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAllCurrentWithNotesAndTrainees() {
//		log.info("Fetching all current batches with trainees, grades and notes");
//		Calendar endDateLimit = Calendar.getInstance();
//		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
//		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.createAlias("trainees.notes", "n", JoinType.LEFT_OUTER_JOIN)
//				.createAlias(T_GRADES, "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
//				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
//				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
//				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).add(Restrictions.eq("n.qcFeedback", true))
//				.addOrder(Order.desc(START_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAllCurrentWithNotes() {
//		log.info("Fetching all current batches with trainees, and notes");
//		Calendar endDateLimit = Calendar.getInstance();
//		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
//		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.createAlias("trainees.notes", "n", JoinType.LEFT_OUTER_JOIN)
//				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
//				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
//				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).add(Restrictions.eq("n.qcFeedback", true))
//				.addOrder(Order.desc(START_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAllCurrentWithTrainees() {
//		log.info("Fetching all current batches with trainees, grades");
//		Calendar endDateLimit = Calendar.getInstance();
//		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
//		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.createAlias(T_GRADES, "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
//				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
//				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
//				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).addOrder(Order.desc(START_DATE))
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//	}
//
//	/**
//	 * Looks for all batches that are currently actively in training. Useful for VP
//	 * and QC to get snapshots of currently operating batches.
//	 * 
//	 * @param auth
//	 * @returnF
//	 */
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAllCurrent() {
//		log.info("Fetching all current batches with active trainees");
//		Calendar endDateLimit = Calendar.getInstance();
//		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
//		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
//				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).addOrder(Order.desc(START_DATE))
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//		batches.parallelStream()
//				.forEach(b -> b.getTrainees().removeIf(t -> t.getTrainingStatus().equals(TrainingStatus.Dropped)));
//		return batches;
//	}
//
//	/**
//	 * Find a batch by its given identifier
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public Batch findOne(Integer batchId) {
//		log.info("Fetching batch: " + batchId);
//		return (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq(BATCH_ID, batchId))
//				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped)).uniqueResult();
//	}
//
//	/**
//	 * Find a batch by its given identifier
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public Batch findOneWithDroppedTrainees(Integer batchId) {
//		log.info("Fetching batch: " + batchId);
//		return (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq(BATCH_ID, batchId))
//				.uniqueResult();
//	}
//
//	/**
//	 * Find a batch by its given identifier, all trainees, and all their grades
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public Batch findOneWithTraineesAndGrades(Integer batchId) {
//		log.info("Fetching batch with trainees: " + batchId);
//		return (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.createAlias("t.grades", "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
//				.add(Restrictions.eq(BATCH_ID, batchId)).add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
//				.uniqueResult();
//	}
//
//	/**
//	 * Update details for a batch
//	 * 
//	 * @param batch
//	 */
//	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void update(Batch batch) {
//		log.info("Updating batch: " + batch);
//		batch.setStartDate(new Date(batch.getStartDate().getTime() + TimeUnit.DAYS.toMillis(1)));
//		batch.setEndDate(new Date(batch.getEndDate().getTime() + TimeUnit.DAYS.toMillis(1)));
//		sessionFactory.getCurrentSession().saveOrUpdate(batch);
//	}
//
//	/**
//	 * Delete a batch from the database
//	 * 
//	 * @param batch
//	 */
//	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void delete(Batch batch) {
//		log.info("Deleting batch: " + batch);
//		sessionFactory.getCurrentSession().delete(batch);
//	}
//
//	/**
//	 * Looks for all batches that whose starting date is after the given year,
//	 * month, and day. Month is 0-indexed Return all batches, trainees for that
//	 * batch, and the grades for each trainee
//	 * 
//	 * @param auth
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<Batch> findAllAfterDate(Integer month, Integer day, Integer year) {
//		Calendar startDate = Calendar.getInstance();
//		startDate.set(year, month, day);
//		log.info("Fetching all current batches since: " + startDate.getTime().toString());
//		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
//				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
//				.createAlias(T_GRADES, "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
//				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
//				.add(Restrictions.ge(START_DATE, startDate.getTime())).addOrder(Order.desc(START_DATE))
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//	}
}
