package MES;

public class OperInfDto {
	private String fac, oper;
	private String crt_tm, crt_user;
	private String chg_tm, chg_user;
	private String move_inout_yn;

	public String getFac() {
		return fac;
	}

	public void setFac(String fac) {
		this.fac = fac;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getCrtTm() {
		return crt_tm;
	}

	public void setCrtTm(String crt_tm) {
		this.crt_tm = crt_tm;
	}

	public String getCrtUser() {
		return crt_user;
	}

	public void setCrtUser(String crt_user) {
		this.chg_user = crt_user;
	}

	public String getChgTm() {
		return chg_tm;
	}

	public void setChgTm(String chg_tm) {
		this.chg_tm = chg_tm;
	}

	public String getChgUser() {
		return chg_user;
	}

	public void setChgUser(String chg_user) {
		this.chg_user = chg_user;
	}

	public String getMoveInOutYn() {
		return move_inout_yn;
	}

	public void setMoveInOutYn(String move_inout_yn) {
		this.move_inout_yn = move_inout_yn;
	}

}
