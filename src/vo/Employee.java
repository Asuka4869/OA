/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:15:12
 */
package vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author WCJ
 * @Date 2019年9月18日 上午10:15:12
 */
@Entity
@Table(name="SYS_Employee")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee {
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String sn;

	private String name;
	private String password;
	private String status;
	@Transient
	private int position_id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="position_id")
	private Position position;
	@Transient
	private int department_id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Department dept;
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getPosition_id() {
		return position_id;
	}
	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Employee [sn=" + sn + ", name=" + name + ", password=" + password + ", status=" + status
				+ ", position_id=" + position.getId() + ", department_id=" + dept.getId() + "]";
	}
	
	
}
