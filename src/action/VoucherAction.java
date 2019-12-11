/**
 * @Autor WCJ
 * @Date 2019年9月21日下午4:14:09
 */
package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import service.VoucherSImpl;
import service.VoucherService;
import vo.V_M;
import vo.V_Y;
import vo.Voucher_Month;
import vo.Voucher_Year;

/**
 * @author WCJ
 * @Date 2019年9月21日 下午4:14:09
 */
@ParentPackage("json-default")
@Namespace("/")
@Action(value="voucher",results={
		@Result(name="saveOrUpMonth",type="json",params={"root","hm"}),
		@Result(name="saveOrUpYear",type="json",params={"root","hm"}),
		@Result(name="showYear",type="json",params={"root","hm"}),
		@Result(name="showMonth",type="json",params={"root","hm"}),
		@Result(name="findYear",type="json",params={"root","hm"}),
		@Result(name="findMonth",type="json",params={"root","hm"})
})
@Controller("voucherAction")
public class VoucherAction extends ActionSupport{
	private Map<String, Object> hm = new HashMap<String, Object>();

	public Map<String, Object> getHm() {
		return hm;
	}

	public void setHm(Map<String, Object> hm) {
		this.hm = hm;
	}
//	HttpServletRequest req = ServletActionContext.getRequest();
//	HttpServletResponse resp = ServletActionContext.getResponse();
	@Autowired
	VoucherService voucherService;
	//VoucherSImpl vsi=new VoucherSImpl();
	public String saveOrUpMonth() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String a=req.getParameter("claim_date");
		Date t=sdf.parse(a.replace("T", " "));
		Calendar c=Calendar.getInstance();
		c.setTime(t);
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH);
		System.out.println("year==="+year);
		System.out.println("MONTH==="+month);
		int d_id=Integer.parseInt(req.getParameter("d_id"));
		Date now=sdf.parse(sdf.format(new Date()));
		float t_count=Float.parseFloat(req.getParameter("t_count"));
		V_M vm=new V_M(t_count,year,month,d_id,now);
		System.out.println("list="+voucherService.findMonth(vm,0));
		int n=0;
		if(voucherService.findMonth(vm,0).size()==0){
			System.out.println("执行save");
			n=voucherService.saveOrUpMonth(vm, "save");
		}else{
			System.out.println("执行up");
			n=voucherService.saveOrUpMonth(vm, "up");
		}
		hm.put("n", n);
		return "saveOrUpMonth";
	}
	public String showMonth() throws Exception{
		Voucher_Month vm=new Voucher_Month();
		List<Voucher_Month> vmlist=voucherService.showMonth(vm);
		hm.put("vmlist", vmlist);
		return "showMonth";
	}
	public String saveOrUpYear() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		//获取参数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		String a=req.getParameter("claim_date").replace("T", " ");
		c.setTime(sdf.parse(a));
		int year=c.get(Calendar.YEAR);
		System.out.println("year="+year);
		int d_id=Integer.parseInt(req.getParameter("d_id"));
		Date now=sdf.parse(sdf.format(new Date()));
		float t_count=Float.parseFloat(req.getParameter("t_count"));
		//
		V_Y vy=new V_Y(t_count,year,now,d_id);
		int n=0;
		if(voucherService.findYear(vy,0).size()==0){
			n=voucherService.saveOrUpYear(vy, "save");
		}else{
			n=voucherService.saveOrUpYear(vy, "up");
		}
		hm.put("n", n);
		return "saveOrUpYear";
	}
	public String showYear() throws Exception{
		
		Voucher_Year vy=new Voucher_Year();
		List<Voucher_Year> vylist=voucherService.showYear(vy);
		hm.put("vylist", vylist);
		return "showYear";
	}
	public String findYear() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		int year=Integer.parseInt(req.getParameter("year"));
		V_Y vy=new V_Y(year);
		List<V_Y> vylist=voucherService.findYear(vy, 1);
		List<Object> l=new ArrayList<Object>();
		for(int i=0;i<vylist.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name",vylist.get(i).getDept().getName());
			map.put("y", vylist.get(i).getTotal_count());
			l.add(map);
		}
		hm.put("l", l);
		hm.put("vylist", vylist);
		return "findYear";
	}
	public String findMonth() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		//获取参数
		int year=Integer.parseInt(req.getParameter("year"));
		int month=Integer.parseInt(req.getParameter("month"));
		V_M vm=new V_M(year,month);
		List<V_M> vmlist=voucherService.findMonth(vm,1);
		List<Object> l=new ArrayList<Object>();
		//
		for(int i=0;i<vmlist.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name",vmlist.get(i).getDept().getName());
			map.put("y", vmlist.get(i).getTotal_Count());
			l.add(map);
		}
		hm.put("l", l);
		hm.put("vmlist", vmlist);
		return "findMonth";
	}
}
