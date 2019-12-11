/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:15:45
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
 * @Date 2019年9月18日 上午10:15:45
 */

@Entity
@Table(name = "BIZ_Claim_Vouyear_Statistics")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Voucher_Year {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float Total_count;
	private float s_count;
	private int year;
	private Date Modify_time;
	@Transient
	private int department_id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Department dept;
	
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	
	public Voucher_Year() {
	}
	
	public Voucher_Year(float total_count, int year, Date modify_time, int department_id) {
		super();
		Total_count = total_count;
		this.year = year;
		Modify_time = modify_time;
		this.department_id = department_id;
	}
	public Voucher_Year(int year) {
		super();
		this.year = year;
	}

	public float getS_count() {
		return s_count;
	}
	public void setS_count(float s_count) {
		this.s_count = s_count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTotal_count() {
		return Total_count;
	}
	public void setTotal_count(float total_count) {
		Total_count = total_count;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getModify_time() {
		return Modify_time;
	}
	public void setModify_time(Date modify_time) {
		Modify_time = modify_time;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	@Override
	public String toString() {
		return "BIZ_Claim_Vouyear_Statistics [id=" + id + ", Total_count=" + Total_count + ", year=" + year
				+ ", Modify_time=" + Modify_time + ", department_id=" + department_id + "]";
	}
	
}
