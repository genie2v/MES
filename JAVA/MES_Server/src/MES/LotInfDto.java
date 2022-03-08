package MES;

public class LotInfDto {
	private String fac, lot, last_timekey;
	private String oper, flow, prod;
	private int prod_qty;
	private String crt_tm, crt_user;
	private String chg_tm, chg_user;

	public String getFac() {
		return fac;
	}

	public void setFac(String fac) {
		this.fac = fac;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getLastTimkey() {
		return last_timekey;
	}

	public void setLastTimekey(String last_timekey) {
		this.last_timekey = last_timekey;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getProd() {
		return prod;
	}

	public void setProd(String prod) {
		this.prod = prod;
	}

	public int getProdQty() {
		return prod_qty;
	}

	public void setProdQty(int prod_qty) {
		this.prod_qty = prod_qty;
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
