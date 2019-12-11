/**
 * @Autor WCJ
 * @Date 2019年9月19日上午11:20:23
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LeaveDao;
import vo.Leave;

/**
 * @author WCJ
 * @Date 2019年9月19日 上午11:20:23
 */
@Service("leaveService")
public class LeaveSImpl implements LeaveService {
	@Autowired
	private LeaveDao leaveDao;
	//LeaveDaoImpl ldi=new LeaveDaoImpl();
	public int addLeave(Leave leave) {
		int n=leaveDao.addLeave(leave);
		return n;
	}
	public List<Leave> showLeaveByStaff(Leave leave) {
		List<Leave> list=leaveDao.showLeaveByStaff(leave);
		return list;
	}
	@Override
	public List<Leave> showLeaveByManager(Leave leave) {
		List<Leave> list=leaveDao.showLeaveByManager(leave);
		return list;
	}
	@Override
	public List<Leave> findLeave(Leave leave) {
		List<Leave> list=leaveDao.findLeave(leave);
		return list;
	}
	@Override
	public int upLeave(Leave leave) {
		int n=leaveDao.upLeave(leave);
		return n;
	}
	
}
