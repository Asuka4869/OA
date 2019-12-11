/**
 * @Autor WCJ
 * @Date 2019年9月18日下午1:43:00
 */
package service;

import java.util.List;

import vo.Employee;

/**
 * @author WCJ
 * @Date 2019年9月18日 下午1:43:00
 */
public interface LoginService {
	public List<Employee> findEmp(Employee emp);
	public List<Employee> findBy_Pid(Employee emp);
	public String findEmpByName(String name);
}
