package jusosync.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * 건물정보
 */
@Getter
@Builder
@SuppressWarnings("unused")
public class TiSpbdBuld {
	private String emdCd;           // 법정동코드
	private String ctpKorNm;        // 시도명
	private String sigKorNm;        // 시군구명
	private String emdKorNm;        // 법정읍면동명
	private String liKorNm;         // 법정리명
	private String mntnYn;          // 산여부
	private String lnbrMnnm;        // 지번본번(번지)
	private String lnbrSlno;        // 지번부번(호)
	private String sigRnCd;         // 도로명코드
	private String rn;              // 도로명
	private String undgrndSe;       // 지하여부
	private String buldMnnm;        // 건물본번
	private String buldSlno;        // 건물부번
	private String buldNm;          // 건축물대장 건물명
	private String buldNmDc;        // 상세건물명
	private String bdMgtSn;         // 건물관리번호
	private String emdNo;           // 읍면동일련번호
	private String haCd;            // 행정동코드
	private String adsKorNm;        // 행정동명
	private String zip;             // 우편번호
	private String zipNo;           // 우편일련번호
	private String zipBulNm;        // 다량배달처명
	private String mvmResCd;        // 이동사유코드
	private String updde;           // 변동일자
	private String bfchgRdnmadr;    // 변동전도로명주소
	private String sigBulNm;        // 시군구용 건물명
	private String aphusYn;         // 공동주택여부
	private String bsiZonNo;        // 기초구역번호
	private String adrDcYn;         // 상세주소여부
	private String rm1;             // 비고1
	private String rm2;             // 비고2
}
