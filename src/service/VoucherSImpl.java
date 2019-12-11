/**
 * @Autor WCJ
 * @Date 2019年9月22日上午9:07:29
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VoucherDao;
import vo.V_M;
import vo.V_Y;
import vo.Voucher_Month;
import vo.Voucher_Year;

/**
 * @author WCJ
 * @Date 2019年9月22日 上午9:07:29
 */
@Service("voucherService")
public class VoucherSImpl implements VoucherService {
	@Autowired
	private VoucherDao voucherDao;
	//VoucherDaoImpl vdi=new VoucherDaoImpl();
	@Override
	public int saveOrUpMonth(V_M vm, String key) {
		int n=voucherDao.saveOrUpMonth(vm, key);
		return n;
	}

	@Override
	public int saveOrUpYear(V_Y vy, String key) {
		int n=voucherDao.saveOrUpYear(vy, key);
		return n;
	}

	@Override
	public List<Voucher_Month> showMonth(Voucher_Month vm) {
		List<Voucher_Month> list=voucherDao.showMonth(vm);
		return list;
	}

	@Override
	public List<Voucher_Year> showYear(Voucher_Year vy) {
		List<Voucher_Year> list=voucherDao.showYear(vy);
		return list;
	}

	@Override
	public List<V_M> findMonth(V_M vm,int key) {
		List<V_M> list=voucherDao.findMonth(vm,key);
		return list;
	}

	@Override
	public List<V_Y> findYear(V_Y vy,int key) {
		List<V_Y> list=voucherDao.findYear(vy,key);
		return list;
	}

}
