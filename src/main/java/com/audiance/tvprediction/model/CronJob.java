package com.audiance.tvprediction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cronjob")
public class CronJob {

	public long Id;
	private Admin admin;
	private String CronjobName;
	private String Command;
	private String TimePattern;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	@Column(name = "cronjobname", nullable = false, unique = true)
	public String getCronjobName() {
		return CronjobName;
	}

	public void setCronjobName(String cronjobName) {
		CronjobName = cronjobName;
	}

	@Column(name = "command", nullable = false)
	public String getCommand() {
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
	}

	@Column(name = "timepattern", nullable = false)
	public String getTimePattern() {
		return TimePattern;
	}

	public void setTimePattern(String timePattern) {
		TimePattern = timePattern;
	}

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "id")
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		admin = admin;
	}

}
