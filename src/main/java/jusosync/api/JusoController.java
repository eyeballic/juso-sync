package jusosync.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jusosync.api.util.SyncStatus;

@RestController
@RequestMapping("/jusosync")
public class JusoController {
	@Autowired
	SyncStatus syncStatus;
	
	@RequestMapping(value = "/daily/{sysId}", method = RequestMethod.GET)
	public String getDailyChangeAddr(@PathVariable String sysId) throws Exception {
		String result;
		String date = syncStatus.getSyncDate(sysId);
		
		if("".equals(date)) {
			result = "등록되지 않은 시스템";
		} else {
			result = date;
			syncStatus.setSyncDate(sysId, "20200205");
		}
		
		return result;
	}
}
