package MES;

public class FlowOperInfDto {
	private String fac, flow, oper;
	private int oper_seq;
	private String crt_tm, crt_user;
	private String chg_tm, chg_user;

	public String getFac() {
		return fac;
	}

	public void setFac(String fac) {
		this.fac = fac;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public int getOperSeq() {
		return oper_seq;
	}

	public void setOperSeq(int oper_seq) {
		this.oper_seq = oper_seq;
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
		this.crt_user = crt_user;
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
}
