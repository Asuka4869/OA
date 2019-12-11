/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:15:05
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author WCJ
 * @Date 2019年9月18日 上午10:15:05
 */
@Entity
@Table(name="SYS_Department")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(mappedBy="dept",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private Set<Employee> emp=new HashSet<Employee>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Employee> getEmp() {
		return emp;
	}
	public void setEmp(Set<Employee> emp) {
		this.emp = emp;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", emp=" + emp + "]";
	}
	
}
