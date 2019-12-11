/**
 * @Autor WCJ
 * @Date 2019年9月18日下午4:12:25
 */
package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import service.LeaveService;
import service.LoginSImpl;
import service.LoginService;
import vo.Employee;
import vo.Leave;

/**
 * @author WCJ
 * @Date 2019年9月18日 下午4:12:25
 */
@ParentPackage("json-default")
@Namespace("/")
@Action(value = "leave", results = { @Result(name = "addLeave", type = "json", params = { "root", "hm" }),
		@Result(name = "add", type = "json", params = { "root", "hm" }),
		@Result(name = "showLeave", type = "json", params = { "root", "hm" }),
		@Result(name = "findLeave", type = "json", params = { "root", "hm" }) })
@Controller("leaveAction")
//请假单管理
public class LeaveAction extends ActionSupport {
	@Autowired
	LeaveService leaveService;
	@Autowired
	LoginService loginService;
	//LeaveSImpl lsi = new LeaveSImpl();
	//LoginSImpl lsi = new LoginSImpl();
	private Map<String, Object> hm = new HashMap<String, Object>();

	public Map<String, Object> getHm() {
		return hm;
	}

	public void setHm(Map<String, Object> hm) {
		this.hm = hm;
	}

//	HttpServletRequest req = ServletActionContext.getRequest();
//	HttpServletResponse resp = ServletActionContext.getResponse();
	//
	public String addLeave() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		Employee e = (Employee) req.getSession().getAttribute("emp");
		e.setDepartment_id(e.getDept().getId());
		e.setPosition_id(2);
		List<Employee> mlist = loginService.findBy_Pid(e);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < mlist.size(); i++) {
			list.add(mlist.get(i).getName());
		}
		hm.put("mlist", list);
		System.out.println(hm);
		return "addLeave";
	}
	//添加请假单详情
	public String add() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
//		LoginSImpl lgsi = new LoginSImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sn = req.getParameter("sn");
		String next_sn = req.getParameter("next_sn");
		Date sTime = sdf.parse(req.getParameter("sTime"));
		Date eTime = sdf.parse(req.getParameter("eTime"));
		Date now = sdf2.parse(sdf2.format(new Date()));
		float leaveday = Float.parseFloat(req.getParameter("leaveday"));
		String reason = req.getParameter("reason");
		String leaveType = req.getParameter("leaveType");
		String n_sn = loginService.findEmpByName(next_sn);
		System.out.println(n_sn);
		Leave leave = new Leave(sTime, eTime, leaveday, reason, leaveType, "未审批", now, n_sn, sn);
		int n = leaveService.addLeave(leave);
		System.out.println(now);
		if (n == 0) {
			hm.put("msg", "添加失败");
		} else
			hm.put("msg", "添加成功");
		return "add";
		
	}
	//查看请假单列表
	public String showLeave() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		Employee e = (Employee) req.getSession().getAttribute("emp");

		List<Leave> list = new ArrayList<Leave>();
		if (e.getPosition().getId() == 1) {
			Leave leave = new Leave();
			leave.setEmployee_sn(e.getSn());
			list = leaveService.showLeaveByStaff(leave);
		} else {
			Leave leave = new Leave();
			leave.setNext_Deal_sn(e.getSn());
			list = leaveService.showLeaveByManager(leave);
		}
		hm.put("list", list);
		System.out.println(list);
		return "showLeave";
	}

	public String findLeave() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		int id = Integer.parseInt(req.getParameter("id"));
		req.getSession().setAttribute("id",id);
		Leave leave = new Leave();
		leave.setId(id);
		List<Leave> list = leaveService.findLeave(leave);
		System.out.println(list);
		hm.put("list", list);
		return "findLeave";
	}

	public String upLeave() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		int id = (int) req.getSession().getAttribute("id");
		String aOption = req.getParameter("aOption");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf2.parse(sdf2.format(new Date()));
		Leave leave = new Leave();
		leave.setApprove_Option(aOption);
		leave.setModifyTime(now);
		leave.setId(id);
		System.out.println(aOption);
		System.out.println(id);
		int n = leaveService.upLeave(leave);
		if (n == 0) {
			hm.put("msg", "审批失败");
		} else
			hm.put("msg", "审批成功");
		return "upLeave";
	}
}
