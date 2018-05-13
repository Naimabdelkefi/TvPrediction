package com.audiance.tvprediction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AudienceData")
public class AudienceData {
	private String Chaine;
	private String Emission;
	private float Ratio;
	private String Date;
	private String Heure;
	private long Id;

	@Column(name = "chaine", nullable = false)
	public String getChaine() {
		return Chaine;
	}

	public void setChaine(String chaine) {
		Chaine = chaine;
	}

	@Column(name = "emission", nullable = false)
	public String getEmission() {
		return Emission;
	}

	public void setEmission(String emission) {
		Emission = emission;
	}

	@Column(name = "ratio", nullable = false)
	public float getRatio() {
		return Ratio;
	}

	public void setRatio(float ratio) {
		Ratio = ratio;
	}

	@Column(name = "date", nullable = false)
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	@Column(name = "heure", nullable = false)
	public String getHeure() {
		return Heure;
	}

	public void setHeure(String heure) {
		Heure = heure;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

}
