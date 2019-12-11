/**
 * @Autor WCJ
 * @Date 2019年9月18日上午11:17:29
 */
package vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author WCJ
 * @Date 2019年9月18日 上午11:17:29
 */
@Entity
@Table(name="BIZ_Check_Result")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Check_Result {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int claim_id;
	private Date check_time;

	private String result;
	private String comm;
	@Transient
	private String checker_sn;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="checker_sn")
	private Employee check_emp;
	
	public Employee getCheck_emp() {
		return check_emp;
	}

	public void setCheck_emp(Employee check_emp) {
		this.check_emp = check_emp;
	}

	public Check_Result(){
		
	}
	
	public Check_Result(int claim_id, Date check_time, String checker_sn, String result, String comm) {
		super();
		this.claim_id = claim_id;
		this.check_time = check_time;
		this.checker_sn = checker_sn;
		this.result = result;
		this.comm = comm;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}
	public Date getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	public String getChecker_sn() {
		return checker_sn;
	}
	public void setChecker_sn(String checker_sn) {
		this.checker_sn = checker_sn;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	@Override
	public String toString() {
		return "Check_Result [id=" + id + ", claim_id=" + claim_id + ", check_time=" + check_time + ", checker_sn="
				+ checker_sn + ", result=" + result + ", comm=" + comm + "]";
	}
	
}
