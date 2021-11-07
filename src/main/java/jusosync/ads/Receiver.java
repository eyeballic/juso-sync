package jusosync.ads;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kr.go.ads.client.ADSReceiver;
import kr.go.ads.client.ADSUtils;
import kr.go.ads.client.ReceiveData;
import kr.go.ads.client.ReceiveDatas;

@Component
public class Receiver {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${appKey}")
	private String appKey;
	
	public void receiveAddrInfo() {
		receiveAddrInfo("", "");
	}
	public void receiveAddrInfo(String date) {
		receiveAddrInfo(date, "");
	}
	public void receiveAddrInfo(String fromDate, String toDate) {
		ADSReceiver ads = new ADSReceiver();
		String dateGb      = "D";			// 날짜 구분
		String retryIn     = "Y";			// 재반영 요청 여부
		String cntcCd      = "009000";		// 자료 요청 구분
		String reqDateFrom = fromDate == null ? "" : fromDate;	// 요청일자(From)
		String reqDateTo   = toDate == null ? "" : toDate;		// 요청일자(To)
		
		// 일변동 자료를 저장할 장소를 설정합니다.
		ads.setFilePath("addrInfo");
		ads.setCreateDateDirectory(ADSUtils.YYMM);
		
		try {
			// 변동자료 연계서비스 요청 및 응답데이터 확인
			logger.info("Request AddrInfo");
			ReceiveDatas receiveDatas = ads.receiveAddr(appKey, dateGb, cntcCd, retryIn, reqDateFrom, reqDateTo);
			
			/* --------------------------------- 응답 결과 확인 --------------------------------- */
			if(receiveDatas.getResult() != 0) {
				if(receiveDatas.getResult() == -1) {
					// 서버 접속 실패 : 잠시후 재 시도 하시기 바랍니다.
					logger.error("서버 접속 실패");	
				}
				// 서버 페이지 오류 사항 확인
				logger.error("Result code   : " + receiveDatas.getResult());
				logger.error("Response code : " + receiveDatas.getResCode());
				logger.error("Response Msg  : " + receiveDatas.getResMsg());
				logger.error("Total count   : " + receiveDatas.getResTotalCnt());
				logger.error("Normal count  : " + receiveDatas.getResNormalCnt());
				logger.error("Retry count   : " + receiveDatas.getResRetryCnt());
				return;
			}
			
			// 서버 응답 확인
			logger.info("Response code : " + receiveDatas.getResCode() + "("		// 응답코드  
			                               + receiveDatas.getResMsg() + ")");		// 응답메시지			
			logger.info("Total count   : " + receiveDatas.getResTotalCnt());		// 전체 자료수
			logger.debug("Normal count  : " + receiveDatas.getResNormalCnt());	// 변동 자료수
			logger.debug("Retry count   : " + receiveDatas.getResRetryCnt());		// 재반영 자료수
			
			if(!"P0000".equals(receiveDatas.getResCode())) {
				// 거절 응답
				return;
			}
			/* --------------------------------- 응답 결과 완료  --------------------------------- */
			
			// 결과 데이터 정렬
			ArrayList<ReceiveData> result = receiveDatas.getReceiveDatas(ADSUtils.UPDATE_ASC);
			
			for(ReceiveData receiveData : result) {
				String msg = "FileDate : " + receiveData.getFileDate()
				           + " / Result : " + receiveData.getResCode() + "(" + receiveData.getResMsg() + ")"
				           + " / FileName : " + receiveData.getFileName();
				
				logger.info(msg);
				
				if(!"P0000".equals(receiveData.getResCode())){
					// 해당 파일응답 에러. 특히 E1001 인경우, 해당 파일을 아직 생성하지 못한 응답으로 추후 재시도 필요.
					//logger.error("해당파일에 대한 응답이 정상이 아니기에 재 요청 필요");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
