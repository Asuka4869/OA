/**
 * @Autor WCJ
 * @Date 2019年9月18日下午1:44:19
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LoginDao;
import vo.Employee;

/**
 * @author WCJ
 * @Date 2019年9月18日 下午1:44:19
 */
@Service("loginService")
public class LoginSImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
	//LoginDaoImpl ldi=new LoginDaoImpl();
	@Override
	public List<Employee> findEmp(Employee emp) {
		// TODO Auto-generated method stub
		List<Employee> list=loginDao.findEmp(emp);
		return list;
	}
	@Override
	public List<Employee> findBy_Pid(Employee emp) {
		List<Employee> list=loginDao.findBy_Pid(emp);
		return list;
	}
	@Override
	public String findEmpByName(String name) {
		String n=loginDao.findEmpByName(name);
		return n;
	}
	
}
