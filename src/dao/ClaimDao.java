/**
 * @Autor WCJ
 * @Date 2019年9月20日下午2:51:05
 */
package dao;

import java.util.List;

import vo.Check_Result;
import vo.Claim_Voucher;
import vo.Claim_Voucher_Detail;

/**
 * @author WCJ
 * @Date 2019年9月20日 下午2:51:05
 */
public interface ClaimDao {
	public int addClaim(Claim_Voucher claim);
	public int addClaimDetail(Claim_Voucher_Detail claim_Detail);
	public List<Claim_Voucher> findClaimByTime(Claim_Voucher claim);
	public List<Claim_Voucher> showClaim(Claim_Voucher claim,int p_id);
	public List<Claim_Voucher> findClaim(Claim_Voucher claim);
	public List<Claim_Voucher_Detail> findDetail(Claim_Voucher_Detail claim_detail);
	public int upClaim(Claim_Voucher claim);
	public int addCheck(Check_Result check);
	public List<Check_Result> showCheck(Check_Result check);
}
