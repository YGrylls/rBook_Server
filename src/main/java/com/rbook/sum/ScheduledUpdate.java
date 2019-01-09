package com.rbook.sum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdate {

	@Autowired
	private SumUpdateService sumService;

	@Scheduled(cron = "0 0 0 * * ?")
	public void update() {// executed 0:00AM everyday
		sumService.update();
	}

}
