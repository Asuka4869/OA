/**
 * @Autor WCJ
 * @Date 2019年9月20日下午2:52:32
 */
package daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import condb.HibernateUtil;
import dao.ClaimDao;
import vo.Check_Result;
import vo.Claim_Voucher;
import vo.Claim_Voucher_Detail;

/**
 * @author WCJ
 * @Date 2019年9月20日 下午2:52:32
 */
@Repository("cliamDao")
public class ClaimDaoImpl implements ClaimDao {

	HibernateUtil hb = new HibernateUtil();
	public List<Claim_Voucher> findClaimByTime(Claim_Voucher claim) {
		String hql="from Claim_Voucher where Create_sn=:Create_sn order by Create_Time Desc";
		List<Claim_Voucher> list=hb.executeSelect(hql, claim);
		return list;
	}
	public int addClaim(Claim_Voucher claim) {
		String sql="insert into biz_claim_voucher (Create_Time,Create_sn,Event,Next_Deal_sn,Status,Total_Account) "
				+ "values(:Create_Time,:Create_sn,:Event,:Next_Deal_sn,:Status,:Total_Account) ";
		int n=hb.executeSQLUpdate(sql, claim);
		return n;
	}
	public int addClaimDetail(Claim_Voucher_Detail claim_Detail) {
		String sql="insert into biz_claim_voucher_detail (Account,Des,item,main_id)"
				+ "values(:Account,:Des,:item,:main_id)";
		int n=hb.executeSQLUpdate(sql, claim_Detail);
		return n;
	}
	@Override
	public List<Claim_Voucher> showClaim(Claim_Voucher claim, int p_id) {
		String hql=null;
		if (p_id == 1) {
			 hql= "from Claim_Voucher where Create_sn=:Create_sn ";
		}else if(p_id==2){
			hql="from Claim_Voucher  where Next_Deal_sn=:Next_Deal_sn and Status='未审批' ";
		}else if(p_id==3){
			hql="from Claim_Voucher  where Next_Deal_sn=:Next_Deal_sn and Status='未审批' ";
		}else if(p_id==4){
			hql="from Claim_Voucher  where Next_Deal_sn=:Next_Deal_sn and Status='同意' ";
		}
		List<Claim_Voucher> list = hb.executeSelect(hql, claim);
		return list;
	}
	@Override
	public List<Claim_Voucher> findClaim(Claim_Voucher claim) {
		String hql="from Claim_Voucher where id=:id ";
		List<Claim_Voucher> list=hb.executeSelect(hql, claim);
		return list;
	}
	@Override
	public List<Claim_Voucher_Detail> findDetail(Claim_Voucher_Detail claim_detail) {
		String hql="from Claim_Voucher_Detail where main_id=:main_id ";
		List<Claim_Voucher_Detail> list=hb.executeSelect(hql,claim_detail);
		return list;
	}
	public int upClaim(Claim_Voucher claim) {
		String sql="update biz_Claim_Voucher set Modify_time=:Modify_time,Status=:Status,Next_Deal_sn=:Next_Deal_sn where id=:id";
		int n=hb.executeSQLUpdate(sql, claim);
		return n;
	}
	public int addCheck(Check_Result check) {
		String sql="insert into biz_check_result (check_time,checker_sn,claim_id,comm,result) values (:check_time,:checker_sn,:claim_id,:comm,:result) ";
		int n=hb.executeSQLUpdate(sql, check);
		return n;
	}
	public List<Check_Result> showCheck(Check_Result check) {
		String hql="from Check_Result where claim_id=:claim_id";
		List<Check_Result> list=hb.executeSelect(hql, check);
		return list;
	}

}
