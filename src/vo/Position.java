/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:14:54
 */
package vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author WCJ
 * @Date 2019年9月18日 上午10:14:54
 */
@Entity
@Table(name="SYS_Position")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Position {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name_CN;
	private String name_EN;
	@OneToOne(mappedBy="position")
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_CN() {
		return name_CN;
	}

	public void setName_CN(String name_CN) {
		this.name_CN = name_CN;
	}

	public String getName_EN() {
		return name_EN;
	}

	public void setName_EN(String name_EN) {
		this.name_EN = name_EN;
	}

	@Override
	public String toString() {
		return "SYS_Position [id=" + id + ", name_CN=" + name_CN + ", name_EN=" + name_EN + "]";
	}
	
}
