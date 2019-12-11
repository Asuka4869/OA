/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:15:27
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
 * @Date 2019年9月18日 上午10:15:27
 */
@Entity
@Table(name="BIZ_Claim_Voucher")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Claim_Voucher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Date Create_Time;
	private String Event;
	private float Total_Account;
	private String Status;
	private Date Modify_time;
	@Transient
	private String Next_Deal_sn;
	@Transient
	private String Create_sn;
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
	@JoinColumn(name="Create_sn")
	private Employee create;
	
	
	public Employee getCreate() {
		return create;
	}
	public void setCreate(Employee create) {
		this.create = create;
	}
	public Claim_Voucher(){
		
	}
	
	public Claim_Voucher(int id,Date Modify_time,String Next_Deal_sn,String status){
		this.id=id;
		this.Modify_time=Modify_time;
		this.Next_Deal_sn=Next_Deal_sn;
		this.Status=status;
	}
	public Claim_Voucher(String next_Deal_sn, String create_sn, Date create_Time, String event, float total_Account,
			String status) {
		super();
		Next_Deal_sn = next_Deal_sn;
		Create_sn = create_sn;
		Create_Time = create_Time;
		Event = event;
		Total_Account = total_Account;
		Status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNext_Deal_sn() {
		return Next_Deal_sn;
	}
	public void setNext_Deal_sn(String next_Deal_sn) {
		Next_Deal_sn = next_Deal_sn;
	}
	public String getCreate_sn() {
		return Create_sn;
	}
	public void setCreate_sn(String create_sn) {
		Create_sn = create_sn;
	}
	public Date getCreate_Time() {
		return Create_Time;
	}
	public void setCreate_Time(Date create_Time) {
		Create_Time = create_Time;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public float getTotal_Account() {
		return Total_Account;
	}
	public void setTotal_Account(float total_Account) {
		Total_Account = total_Account;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Date getModify_time() {
		return Modify_time;
	}
	public void setModify_time(Date modify_time) {
		Modify_time = modify_time;
	}
	@Override
	public String toString() {
		return "Claim_Voucher [id=" + id + ", Next_Deal_sn=" + next.getSn() + ", Create_sn=" + create.getSn()
				+ ", Create_Time=" + Create_Time + ", Event=" + Event + ", Total_Account=" + Total_Account + ", Status="
				+ Status + ", Modify_time=" + Modify_time + "]";
	}
	
}
