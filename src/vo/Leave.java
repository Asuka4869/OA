/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:15:18
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
 * @Date 2019年9月18日 上午10:15:18
 */
@Entity
@Table(name="BIZ_Leave")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Leave {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date StartTime;
	private Date EndTime;
	private float leaveday;
	private String reason;
	private String leaveType;
	
	private String Approve_Option;
	private Date CreateTime;
	private Date ModifyTime;
	
	@Transient
	private String Next_Deal_sn;
	@Transient
	private String employee_sn;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Next_Deal_sn")
	private Employee next;
	
	public Employee getNext() {
		return next;
	}
	public void setNext(Employee next) {
		this.next = next;
	}
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="employee_sn")
	private Employee emps;
	public Employee getEmps() {
		return emps;
	}
	public void setEmp(Employee emps) {
		this.emps = emps;
	}
	
	public Leave(){
		
	}
	public Leave(Date startTime, Date endTime, float leaveday, String reason, String leaveType, String approve_Option,
			Date createTime, String next_Deal_sn, String employee_sn) {
		super();
		StartTime = startTime;
		EndTime = endTime;
		this.leaveday = leaveday;
		this.reason = reason;
		this.leaveType = leaveType;
		Approve_Option = approve_Option;
		CreateTime = createTime;
		Next_Deal_sn = next_Deal_sn;
		this.employee_sn = employee_sn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployee_sn() {
		return employee_sn;
	}
	public void setEmployee_sn(String employee_sn) {
		this.employee_sn = employee_sn;
	}
	public Date getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}
	public float getLeaveday() {
		return leaveday;
	}
	public void setLeaveday(float leaveday) {
		this.leaveday = leaveday;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getNext_Deal_sn() {
		return Next_Deal_sn;
	}
	public void setNext_Deal_sn(String next_Deal_sn) {
		Next_Deal_sn = next_Deal_sn;
	}
	public String getApprove_Option() {
		return Approve_Option;
	}
	public void setApprove_Option(String approve_Option) {
		Approve_Option = approve_Option;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Date getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		ModifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "Leave [id=" + id + ", employee_sn=" + employee_sn + ", StartTime=" + StartTime + ", EndTime=" + EndTime
				+ ", leaveday=" + leaveday + ", reason=" + reason + ", leaveType=" + leaveType + ", Next_Deal_sn="
				+ Next_Deal_sn + ", Approve_Option=" + Approve_Option + ", CreateTime=" + CreateTime + ", ModifyTime="
				+ ModifyTime + "]";
	}
	
}
