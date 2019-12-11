/**
 * @Autor WCJ
 * @Date 2019年9月9日下午2:24:39
 */
package action;

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
import com.opensymphony.xwork2.ModelDriven;

import service.LoginService;
import vo.Employee;

/**
 * @author WCJ
 * @Date 2019年9月9日 下午2:24:39
 */
@ParentPackage("json-default")
@Namespace("/")
@Action(value="emp",results={@Result(name="register",type="json",params={"root","hm"})
,@Result(name="exit",location="/login.html"),@Result(name="login",type="json",params={"root","hm"})
})
@Controller("loginAction")
public class LoginAction extends ActionSupport implements ModelDriven<Employee> {
	private Employee emp = new Employee();

	private Map<String, Object> hm = new HashMap<String, Object>();

	public Map<String, Object> getHm() {
		return hm;
	}

	public void setHm(Map<String, Object> hm) {
		this.hm = hm;
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return emp;
	}
	@Autowired
	LoginService loginService;
	//LoginSImpl lsi = new LoginSImpl();
//	HttpServletRequest req = ServletActionContext.getRequest();
//	HttpServletResponse resp = ServletActionContext.getResponse();

	public String login() throws Exception {

//		HashMap<String, String> hm = new HashMap<String, String>();
		// 登陆
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		Employee emp = new Employee();
		resp.setContentType("text/html; charset=UTF-8");
		String sn = req.getParameter("name");
		String password = req.getParameter("password");
		emp.setSn(sn);
		emp.setPassword(password);
		System.out.println("正在执行登陆");
		List<Employee> list = loginService.findEmp(emp);
		System.out.println(list);
		req.getSession().removeAttribute("emp");
		
		if (list.size()==0 ||list==null) {
			hm.put("msg", "登录失败！");
			System.out.println("登陆失败");
			return "login";
		} else {
			req.getSession().setAttribute("emp", list.get(0));
			hm.put("msg", "登录成功！");
			System.out.println("登陆成功");
		}
		return "login";

	}



	public String exit() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		req.getSession().removeAttribute("emp");
		return "exit";
	}

}
