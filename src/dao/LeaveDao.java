/**
 * @Autor WCJ
 * @Date 2019��9��19������11:14:54
 */
package dao;

import java.util.List;

import vo.Leave;

/**
 * @author WCJ
 * @Date 2019��9��19�� ����11:14:54
 */
public interface LeaveDao {
	public int addLeave(Leave leave);
	public List<Leave> showLeaveByStaff(Leave leave);
	public List<Leave> showLeaveByManager(Leave leave);
	public List<Leave> findLeave(Leave leave);
	public int upLeave(Leave leave);
}
