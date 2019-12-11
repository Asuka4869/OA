/**
 * @Autor WCJ
 * @Date 2019年9月19日上午11:20:01
 */
package service;

import java.util.List;

import vo.Leave;

/**
 * @author WCJ
 * @Date 2019年9月19日 上午11:20:01
 */
public interface LeaveService {
	public int addLeave(Leave leave);
	public List<Leave> showLeaveByStaff(Leave leave);
	public List<Leave> showLeaveByManager(Leave leave);
	public List<Leave> findLeave(Leave leave);
	public int upLeave(Leave leave);
}
