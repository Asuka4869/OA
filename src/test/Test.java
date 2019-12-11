/**
 * @Autor WCJ
 * @Date 2019年9月18日下午4:26:44
 */
package test;

import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.LoginService;
import vo.Employee;

/**
 * @author WCJ
 * @Date 2019年9月18日 下午4:26:44
 */
public class Test {
	public static void main(String[] args) throws ParseException {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginService lsi=(LoginService) ac.getBean("loginService");
		Employee emp=new Employee();
		emp.setSn("001");
		emp.setPassword("123456");
		lsi.findEmp(emp);
		System.out.println(lsi.findEmp(emp));
//		LoginSImpl lsi=new LoginSImpl();
//		Employee emp =new Employee();
//		emp.setDepartment_id(1);
//		List<Employee> list=lsi.findManager(emp);
//		System.out.println(list);
//		ClaimSImpl csi=new ClaimSImpl();
		
//		VoucherDaoImpl vdi=new VoucherDaoImpl();
//		VoucherSImpl vsi=new VoucherSImpl();
////		Voucher_Month vm=new Voucher_Month();
////		List<Voucher_Month> list=vdi.showMonth(vm);
////		for(Voucher_Month v:list){
////			System.out.println(v);
////		}
////		Voucher_Month vm1=new Voucher_Month();
////		List<Voucher_Month> vmlist=vsi.showMonth(vm1);
////		System.out.println(vmlist);
//		V_M vm=new V_M(2019,6);
//		List<V_M> vmlist=vsi.findMonth(vm,1);
//		System.out.println(vmlist);
//		Claim_Voucher claim=new Claim_Voucher("019","001",new Date(),"出差",300,"新创键");
//		csi.addClaim(claim);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String a="2019-06-26T15:50:19".replace("T", " ");
//		Date t=sdf.parse(a);
////		Date t=new Date(a);
//		System.out.println(t);
	}
}
