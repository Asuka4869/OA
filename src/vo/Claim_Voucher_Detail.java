/**
 * @Autor WCJ
 * @Date 2019年9月18日上午10:15:33
 */
package vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author WCJ
 * @Date 2019年9月18日 上午10:15:33
 */
@Entity
@Table(name="BIZ_Claim_Voucher_Detail")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Claim_Voucher_Detail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int main_id;
	private String item;
	private float Account;
	private String Des;
	@Override
	public String toString() {
		return "BIZ_Claim_Voucher_Detail [id=" + id + ", main_id=" + main_id + ", item=" + item + ", Account=" + Account
				+ ", Des=" + Des + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMain_id() {
		return main_id;
	}
	public void setMain_id(int main_id) {
		this.main_id = main_id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public float getAccount() {
		return Account;
	}
	public void setAccount(float account) {
		Account = account;
	}
	public String getDes() {
		return Des;
	}
	public void setDes(String des) {
		Des = des;
	}
	

}
