/**
 * @Autor WCJ
 * @Date 2019年9月19日上午11:15:27
 */
package daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import condb.HibernateUtil;
import dao.LeaveDao;
import vo.Leave;

/**
 * @author WCJ
 * @Date 2019年9月19日 上午11:15:27
 */
@Repository("leaveDao")
public class LeaveDaoImpl implements LeaveDao {
	HibernateUtil hb = new HibernateUtil();
	@Override
	public int addLeave(Leave leave) {
		String sql="insert into biz_leave (employee_sn,leaveday,reason,StartTime,EndTime,leaveType,Next_Deal_sn,Approve_Option,CreateTime) "
				+ " values (:employee_sn,:leaveday,:reason,:StartTime,:EndTime,:leaveType,:Next_Deal_sn,:Approve_Option,:CreateTime) ";
		int n=hb.executeSQLUpdate(sql, leave);
		return n;
	}
	@Override
	public List<Leave> showLeaveByStaff(Leave leave) {
		String hql="from Leave where employee_sn=:employee_sn";
		List<Leave> list=hb.executeSelect(hql,leave);
		return list;
	}
	@Override
	public List<Leave> showLeaveByManager(Leave leave) {
		String hql="from Leave where Next_Deal_sn=:Next_Deal_sn and Approve_Option='未审批'";
		List<Leave> list=hb.executeSelect(hql, leave);
		return list;
	}
	@Override
	public List<Leave> findLeave(Leave leave) {
		String hql="from Leave where id=:id";
		List<Leave> list=hb.executeSelect(hql, leave);
		return list;
	}
	@Override
	public int upLeave(Leave leave) {
		String sql="update biz_Leave set ModifyTime=:ModifyTime,Approve_Option=:Approve_Option  where id=:id";
		int n = hb.executeSQLUpdate(sql, leave);
		return n;
	}

}
