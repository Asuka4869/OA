/**
 * @Autor WCJ
 * @Date 2019年9月20日下午2:59:57
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClaimDao;
import vo.Check_Result;
import vo.Claim_Voucher;
import vo.Claim_Voucher_Detail;

/**
 * @author WCJ
 * @Date 2019年9月20日 下午2:59:57
 */
@Service("claimService")
public class ClaimSImpl implements ClaimService {
	@Autowired
	private ClaimDao claimDao;
	//ClaimDaoImpl cdi=new ClaimDaoImpl();
	public int addClaim(Claim_Voucher claim) {
		int n=claimDao.addClaim(claim);
		return n;
	}
	public int addClaimDetail(Claim_Voucher_Detail claim_Detail) {
		int n=claimDao.addClaimDetail(claim_Detail);
		return n;
	}
	public List<Claim_Voucher> findClaimByTime(Claim_Voucher claim) {
		List<Claim_Voucher> list=claimDao.findClaimByTime(claim);
		return list;
	}
	@Override
	public List<Claim_Voucher> showClaim(Claim_Voucher claim, int p_id) {
		List<Claim_Voucher> list=claimDao.showClaim(claim,p_id);
		return list;
	}
	@Override
	public List<Claim_Voucher> findClaim(Claim_Voucher claim) {
		List<Claim_Voucher> list=claimDao.findClaim(claim);
		return list;
	}
	@Override
	public List<Claim_Voucher_Detail> findDetail(Claim_Voucher_Detail claim_detail) {
		List<Claim_Voucher_Detail> list=claimDao.findDetail(claim_detail);
		return list;
	}
	@Override
	public int upClaim(Claim_Voucher claim) {
		int n=claimDao.upClaim(claim);
		return n;
	}
	@Override
	public int addCheck(Check_Result check) {
		int n=claimDao.addCheck(check);
		return n;
	}
	public List<Check_Result> showCheck(Check_Result check){
		List<Check_Result> list=claimDao.showCheck(check);
		return list;
	}
}
