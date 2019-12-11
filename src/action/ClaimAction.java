/**
 * @Autor WCJ
 * @Date 2019年9月19日下午4:56:11
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

import service.ClaimService;
import service.LoginService;
import vo.Check_Result;
import vo.Claim_Voucher;
import vo.Claim_Voucher_Detail;
import vo.Employee;

/**
 * @author WCJ
 * @Date 2019年9月19日 下午4:56:11
 */
@ParentPackage("json-default")
@Namespace("/")
@Action(value = "claim", results = { @Result(name = "addClaim", type = "json", params = { "root", "hm" }),
		@Result(name = "add", type = "json", params = { "root", "hm" }),
		@Result(name = "addDetail", type = "json", params = { "root", "hm" }),
		@Result(name = "showClaim", type = "json", params = { "root", "hm" }),
		@Result(name = "showDetail", type = "json", params = { "root", "hm" }),
		@Result(name = "upClaim", type = "json", params = { "root", "hm" }),
		@Result(name = "addCheck", type = "json", params = { "root", "hm" })})
@Controller("claimAction")
//报销单管理
public class ClaimAction extends ActionSupport {

	private Map<String, Object> hm = new HashMap<String, Object>();

	public Map<String, Object> getHm() {
		return hm;
	}

	public void setHm(Map<String, Object> hm) {
		this.hm = hm;
	}

	@Autowired
	ClaimService claimService;
	@Autowired
	LoginService loginService;
	//ClaimSImpl csi = new ClaimSImpl();
	//LoginSImpl lsi = new LoginSImpl();

	
	public String addClaim() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf2.parse(sdf2.format(new Date()));
		req.getSession().setAttribute("nowT", now);

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
		System.out.println(now);
		System.out.println(sdf2.format(new Date()));
		return "addClaim";
	}
	//添加报销单
	public String add() throws Exception {
		// 获取参数
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		Date now = (Date) req.getSession().getAttribute("nowT");
		String c_sn = req.getParameter("c_sn");
		String n_sn = req.getParameter("n_sn");
		float all_account = Float.parseFloat(req.getParameter("all_account"));
		String status = req.getParameter("status");
		String event = req.getParameter("event");
		c_sn = loginService.findEmpByName(c_sn);
		n_sn = loginService.findEmpByName(n_sn);
		Claim_Voucher claim = new Claim_Voucher(n_sn, c_sn, now, event, all_account, status);
		int n = claimService.addClaim(claim);
		Claim_Voucher cla = new Claim_Voucher();
		cla.setCreate_sn(c_sn);
		List<Claim_Voucher> clist = claimService.findClaimByTime(cla);
		System.out.println(clist.get(0));
		int id = clist.get(0).getId();
		req.getSession().setAttribute("c_id", id);
		// 获取参数

		if (n == 0)
			hm.put("msg", "添加失败");
		else
			hm.put("msg", "添加成功");
		return "add";
	}
	//添加报销单详情
	public String addDetail() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		Claim_Voucher_Detail claim_detail = new Claim_Voucher_Detail();
		int id = (int) req.getSession().getAttribute("c_id");

		float account = Float.parseFloat(req.getParameter("account"));
		String item = req.getParameter("item");
		String des = req.getParameter("des");
		// 添加参数
		claim_detail.setAccount(account);
		claim_detail.setItem(item);
		claim_detail.setDes(des);
		claim_detail.setMain_id(id);
		//
		int n = claimService.addClaimDetail(claim_detail);
		if (n == 0) {
			hm.put("msg", "添加失败");
		} else
			hm.put("msg", "添加成功");
		return "addDetail";
	}
	//显示报销单列表
	public String showClaim() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		Employee e = (Employee) req.getSession().getAttribute("emp");
		int p_id = e.getPosition().getId();
		List<Claim_Voucher> list = new ArrayList<Claim_Voucher>();
		Claim_Voucher claim = new Claim_Voucher();
		if (p_id == 1) {
			claim.setCreate_sn(e.getSn());
			System.out.println(claim.getCreate_sn());
		} else {
			claim.setNext_Deal_sn(e.getSn());
		}
		list = claimService.showClaim(claim, p_id);
		hm.put("list", list);
		req.getSession().setAttribute("c_list", list);
		return "showClaim";
	}
	//显示报销单审批详情
	public String showDetail() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		int id = Integer.parseInt(req.getParameter("id"));
		req.getSession().setAttribute("cla_id", id);
		//
		Claim_Voucher claim = new Claim_Voucher();
		Claim_Voucher_Detail claim_detail = new Claim_Voucher_Detail();
		claim.setId(id);
		claim_detail.setMain_id(id);
		List<Claim_Voucher> clist = claimService.findClaim(claim);
		//
		float cost=clist.get(0).getTotal_Account();
		req.getSession().setAttribute("cost", cost);
		List<Claim_Voucher_Detail> dlist = claimService.findDetail(claim_detail);
		List<Employee> mlist = new ArrayList<Employee>();
		Employee emp = (Employee) req.getSession().getAttribute("emp");
		Employee e = new Employee();
		e.setDepartment_id(emp.getDept().getId());
		//
		if (emp.getPosition().getId() == 1) {
			e.setPosition_id(2);
		}else if(emp.getPosition().getId() == 2 && clist.get(0).getTotal_Account()<5000 ||emp.getPosition().getId() == 3){
			e.setPosition_id(4);
		}else if(emp.getPosition().getId() == 2 && clist.get(0).getTotal_Account()>=5000){
			e.setPosition_id(3);
		}
		Check_Result check=new Check_Result();
		check.setClaim_id(id);
		List<Check_Result> rlist=claimService.showCheck(check);
		mlist = loginService.findBy_Pid(e);
		hm.put("clist", clist);
		hm.put("dlist", dlist);
		hm.put("nlist", mlist);
		hm.put("rlist", rlist);
		return "showDetail";
	}
	//审批报销单
	public String upClaim() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		int p_id=Integer.parseInt(req.getParameter("p_id"));
		int c_id=(int) req.getSession().getAttribute("cla_id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf.parse(sdf.format(new Date()));
		req.getSession().setAttribute("check_time", now);
		String n_sn=req.getParameter("n_sn");
		if(n_sn.equals("")||n_sn==null){
			n_sn=null;
		}else{
			n_sn = loginService.findEmpByName(n_sn);
			System.out.println(n_sn);
		}
		
		float cost=(float) req.getSession().getAttribute("cost");
		String status=null;
		if(p_id==2 &&cost>=5000)
			status="未审批";
		else{ 
			status=req.getParameter("status");
			if(status.equals("付款"))
				status="已付款";
		}
		Claim_Voucher claim=new Claim_Voucher(c_id,now,n_sn,status);
		int n=claimService.upClaim(claim);
		if(n==0)
			hm.put("msg", "审核失败");
		else
			hm.put("msg", "审核成功");
		return "upClaim";
	}
	//修改报销单
	public String addCheck() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		int c_id=(int) req.getSession().getAttribute("cla_id");
		String sn=req.getParameter("sn");
		String comm=req.getParameter("comm");
			
		Date c_time=(Date) req.getSession().getAttribute("check_time");
		System.out.println(c_time);
		String result=req.getParameter("status");
		if(result.equals("付款"))
			result="已付款";
		Check_Result check=new Check_Result(c_id,c_time,sn,result,comm);
		int n=claimService.addCheck(check);
		if(n==0)
			hm.put("msg", "修改失败");
		else
			hm.put("msg", "修改成功");
		return "addCheck";
		
	}
}
