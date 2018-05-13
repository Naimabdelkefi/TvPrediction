package com.audiance.tvprediction.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import com.audiance.tvprediction.dao.AudienceDataDao;
import com.audiance.tvprediction.model.AudienceData;
import com.audiance.tvprediction.model.User;

public class AudienceDataDaoImpl implements AudienceDataDao {

	private SessionFactory sessionFactory;

	public List<AudienceData> findAll() {
		return getSessionFactory().getCurrentSession().createQuery("from AudienceData").list();
	}

	public void save(AudienceData arg0) {
		getSessionFactory().getCurrentSession().save(arg0);		
	}

	public void delete(AudienceData arg0) {
		getSessionFactory().getCurrentSession().delete(arg0);		
	}

	public AudienceData findById(Long arg0) {
		return (AudienceData) getSessionFactory().getCurrentSession().get(AudienceData.class, arg0);
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<AudienceData> findBy(String type, String pram) {
		if (type.equals("date"))
		{
			List<AudienceData> audienceDatas= new ArrayList<AudienceData>();

			audienceDatas = getSessionFactory().getCurrentSession().createQuery("from AudienceData where date=:date").setString("date", pram).list();

			if (audienceDatas.size() > 0) {
				return audienceDatas;
			} else {
				return null;
			}
		}
		else if(type.equals("emission")) {
			List<AudienceData> audienceDatas= new ArrayList<AudienceData>();

			audienceDatas = getSessionFactory().getCurrentSession().createQuery("from AudienceData where emission=?")
					.setParameter(0, pram).list();

			if (audienceDatas.size() > 0) {
				return audienceDatas;
			} else {
				return null;
			}
		}
		else if (type.equals("chaine")) {
			List<AudienceData> audienceDatas= new ArrayList<AudienceData>();

			audienceDatas = getSessionFactory().getCurrentSession().createQuery("from AudienceData where chaine=?")
					.setParameter(0, pram).list();

			if (audienceDatas.size() > 0) {
				return audienceDatas;
			} else {
				return null;
			}
		}
	return null;
	}

	public List<AudienceData> findByDateIntAndChaine(String date1, String date2, String chiane) {
		List<AudienceData> audienceDatas= new ArrayList<AudienceData>();

		audienceDatas = getSessionFactory().getCurrentSession().createQuery("from AudienceData where ((date BETWEEN :date1 AND :date2) AND chaine=:chaine)").setString("date1", date1).setString("chaine", chiane).setString("date2", date2).list();

		if (audienceDatas.size() > 0) {
			return audienceDatas;
		} else {
			return null;
		}
	}
		public List<AudienceData> findByDateInt(String date1, String date2) {
			List<AudienceData> audienceDatas= new ArrayList<AudienceData>();

			audienceDatas = getSessionFactory().getCurrentSession().createQuery("from AudienceData where ((date BETWEEN :date1 AND :date2))").setString("date1", date1).setString("date2", date2).list();

			if (audienceDatas.size() > 0) {
				return audienceDatas;
			} else {
				return null;
			}
	}
	


}
