package jusosync.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * 관련지번
 */
@Getter
@Builder
@SuppressWarnings("unused")
public class TiSccoMvmn {
	private String emdCd;        // 법정동코드
	private String ctpKorNm;     // 시도명
	private String sigKorNm;     // 시군구명
	private String emdKorNm;     // 법정읍면동명
	private String liKorNm;      // 법정리명
	private String mntnYn;       // 산여부
	private String lnbrMnnm;     // 지번본번(번지)
	private String lnbrSlno;     // 지번부번(호)
	private String sigRnCd;      // 도로명코드
	private String undgrndSe;    // 지하여부
	private String buldMnnm;     // 건물본번
	private String buldSlno;     // 건물부번
	private String bulHisSn;     // 지번일련번호
	private String mvmResCd;     // 이동사유코드
}
