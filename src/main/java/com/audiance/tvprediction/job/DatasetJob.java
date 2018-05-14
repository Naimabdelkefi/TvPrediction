package com.audiance.tvprediction.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimeZone;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.audiance.tvprediction.model.CronJob;
import com.audiance.tvprediction.service.CronJobService;

public class DatasetJob implements Runnable {

	CronJob cronJob;

	public DatasetJob(CronJobService cronJobService, TaskScheduler taskScheduler) {
		cronJob = cronJobService.getCronjobByName(DatasetJob.class.getSimpleName());
		taskScheduler.schedule(this, new CronTrigger(cronJob.getTimePattern(), TimeZone.getDefault()));
	}

	public void run() {
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cronJob.getCommand());
			BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = is.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}