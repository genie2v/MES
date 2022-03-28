package MES;

import java.sql.Date;

public class LotHisDto {
	private String fac, lot;
	private String timekey, txn_cd;
	private String oper, flow, prod, proc;
	private int prod_qty;
	Date crt_tm, chg_tm;
	private String crt_user, chg_user;

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

	public String getTimkey() {
		return timekey;
	}

	public void setTimekey(String timekey) {
		this.timekey = timekey;
	}

	public String getTxnCd() {
		return txn_cd;
	}

	public void setTxnCd(String txn_cd) {
		this.txn_cd = txn_cd;
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

	public String getProc() {
		return proc;
	}

	public void setProc(String proc) {
		this.proc = proc;
	}

	public int getProdQty() {
		return prod_qty;
	}

	public void setProdQty(int prod_qty) {
		this.prod_qty = prod_qty;
	}

	public Date getCrtTm() {
		return crt_tm;
	}

	public void setCrtTm(Date crt_tm) {
		this.crt_tm = crt_tm;
	}

	public String getCrtUser() {
		return crt_user;
	}

	public void setCrtUser(String crt_user) {
		this.crt_user = crt_user;
	}

	public Date getChgTm() {
		return chg_tm;
	}

	public void setChgTm(Date chg_tm) {
		this.chg_tm = chg_tm;
	}

	public String getChgUser() {
		return chg_user;
	}

	public void setChgUser(String chg_user) {
		this.chg_user = chg_user;
	}
}
