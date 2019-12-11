/**
 * @Autor WCJ
 * @Date 2019年9月22日上午9:07:03
 */
package service;

import java.util.List;

import vo.V_M;
import vo.V_Y;
import vo.Voucher_Month;
import vo.Voucher_Year;

/**
 * @author WCJ
 * @Date 2019年9月22日 上午9:07:03
 */
public interface VoucherService {
	public int saveOrUpMonth(V_M vm,String key);
	public int saveOrUpYear(V_Y vy,String key);
	public List<Voucher_Month> showMonth(Voucher_Month vm);
	public List<Voucher_Year> showYear(Voucher_Year vy);
	public List<V_M> findMonth(V_M vm,int key);
	public List<V_Y> findYear(V_Y vy,int key);
}
