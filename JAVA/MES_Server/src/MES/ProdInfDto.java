package MES;

import java.sql.Date;

public class ProdInfDto {
	private String fac, prod;
	Date crt_tm, chg_tm;
	private String crt_user, chg_user;

	public String getFac() {
		return fac;
	}

	public void setFac(String fac) {
		this.fac = fac;
	}

	public String getProd() {
		return prod;
	}

	public void setProd(String prod) {
		this.prod = prod;
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
