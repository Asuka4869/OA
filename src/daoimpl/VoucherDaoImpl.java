/**
 * @Autor WCJ
 * @Date 2019年9月21日下午4:24:54
 */
package daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import condb.HibernateUtil;
import dao.VoucherDao;
import vo.V_M;
import vo.V_Y;
import vo.Voucher_Month;
import vo.Voucher_Year;

/**
 * @author WCJ
 * @Date 2019年9月21日 下午4:24:54
 */
@Repository("voucherDao")
public class VoucherDaoImpl implements VoucherDao {
	HibernateUtil hb = new HibernateUtil();

	@Override
	public int saveOrUpMonth(V_M vm, String key) {
		String sql = "";
		if (key.equals("save")) {
			sql = "insert into biz_claim_voucher_statistics (Modify_Time,Total_Count,department_id,month,year) values(:Modify_Time,:Total_Count,:department_id,:month,:year) ";
		} else if (key.equals("up")) {
			sql = "update  biz_claim_voucher_Statistics set Modify_Time=:Modify_Time,Total_Count=:Total_Count  where id=:id";
		}
		int n = hb.executeSQLUpdate(sql, vm);
		return n;
	}

	@Override
	public int saveOrUpYear(V_Y vy, String key) {
		String sql = "";
		if (key.equals("save")) {
			sql = "insert into biz_claim_vouyear_statistics (Modify_time,Total_count,department_id,year) values(:Modify_time,:Total_count,:department_id,:year) ";
		} else if (key.equals("up")) {
			sql = "update  biz_claim_vouyear_Statistics set Modify_time=:Modify_time,Total_count=:Total_count  where id=:id";
		}
		int n = hb.executeSQLUpdate(sql, vy);
		return n;
	}

	@Override
	public List<Voucher_Month> showMonth(Voucher_Month vm) {
		String sql = " select id,Modify_Time,sum(Total_Count) s_count ,Total_Count,year,month,department_id from biz_claim_voucher_statistics GROUP BY month ";
		List<Voucher_Month> list = hb.executeSQLSelect(sql, vm);
		return list;
	}

	@Override
	public List<Voucher_Year> showYear(Voucher_Year vy) {
		String sql = "select id,Modify_Time,sum(Total_Count) s_count ,Total_Count,year,department_id from biz_claim_vouyear_statistics GROUP BY month ";
		List<Voucher_Year> list = hb.executeSQLSelect(sql, vy);
		return list;
	}

	public List<V_M> findMonth(V_M vm, int key) {
		String hql = "";
		if (key == 0) {
			hql = "from V_M where month=:month and year=:year and department_id=:department_id";
		} else
			hql = "from V_M where month=:month and year=:year ";
		List<V_M> list = hb.executeSelect(hql, vm);
		return list;
	}

	public List<V_Y> findYear(V_Y vy, int key) {
		String hql = "";
		if(key==0)
			hql = "from V_Y where year=:year and department_id=:department_id";
		else
			hql = "from V_Y where year=:year ";
		List<V_Y> list = hb.executeSelect(hql, vy);
		return list;
	}

}
