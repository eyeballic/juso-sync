package jusosync.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jusosync.ads.Receiver;

@Component
public class Scheduler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Receiver adsReceiver;
	
	@Scheduled(cron = "${scheduler.cycle.receiveAddrInfo}")
	public void receiveAddrInfo() {
		logger.debug("called receiveAddrInfo()");
		adsReceiver.receiveAddrInfo();
	}
}
