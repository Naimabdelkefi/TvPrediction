package com.audiance.tvprediction.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.audiance.tvprediction.dao.CronJobDAO;
import com.audiance.tvprediction.model.CronJob;

public class CronJobDAOImpl implements CronJobDAO {

	private SessionFactory sessionFactory;

	public CronJobDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public CronJob findByCronjobName(String name) {

		List<CronJob> cronJobs = new ArrayList<CronJob>();

		cronJobs = getSessionFactory().getCurrentSession().createQuery("from CronJob where CronjobName=?")
				.setParameter(0, name).list();

		if (cronJobs.size() > 0) {
			return cronJobs.get(0);
		} else {
			return null;
		}
	}

	public List<CronJob> findAll() {
		return getSessionFactory().getCurrentSession().createQuery("from CronJob").list();
	}

	public void save(CronJob arg0) {
		getSessionFactory().getCurrentSession().save(arg0);

	}

	public void delete(CronJob arg0) {
		getSessionFactory().getCurrentSession().delete(arg0);

	}

	public CronJob findById(Long arg0) {
		return (CronJob) getSessionFactory().getCurrentSession().get(CronJob.class, arg0);
	}

	public CronJob findByIdAdmin(Long arg0) {
		List<CronJob> cronJobs = new ArrayList<CronJob>();

		cronJobs = getSessionFactory().getCurrentSession().createQuery("from CronJob where user=?")
				.setParameter(0, arg0).list();

		if (cronJobs.size() > 0) {
			return cronJobs.get(0);
		} else {
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


}
