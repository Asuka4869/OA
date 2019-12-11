/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:15:39
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
 * @Date 2019年9月18日 上午10:15:39
 */
@Entity
@Table(name="BIZ_Claim_Voucher_Statistics")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Voucher_Month {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private float Total_Count;
	private float s_count;
	private int year;
	private int month;
	
	
	private Date Modify_Time;
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
	public Voucher_Month(){
		
	}
	public Voucher_Month(int year, int month) {
		super();
		this.year = year;
		this.month = month;
	}

	public Voucher_Month(float total_Count, int year, int month, int department_id, Date modify_Time) {
		super();
		Total_Count = total_Count;
		this.year = year;
		this.month = month;
		this.department_id = department_id;
		Modify_Time = modify_Time;
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
	public float getTotal_Count() {
		return Total_Count;
	}
	public void setTotal_Count(float total_Count) {
		Total_Count = total_Count;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public Date getModify_Time() {
		return Modify_Time;
	}
	public void setModify_Time(Date modify_Time) {
		Modify_Time = modify_Time;
	}
	
//	public String toString() {
//		return "Claim_Voucher_Statistics [id=" + id + ", Total_Count=" + Total_Count + ", year=" + year + ", month="
//				+ month + ", department_id=" + department_id + ", Modify_Time=" + Modify_Time + "]";
//	}
	@Override
	public String toString(){
		return "Claim_Voucher_Statistics [id=" + id + ", s_count="+s_count+",year="+ year + ", month="
				+ month;
	}
}
