/**
 * @Autor WCJ
 * @Date 2019��9��18������1:43:00
 */
package service;

import java.util.List;

import vo.Employee;

/**
 * @author WCJ
 * @Date 2019��9��18�� ����1:43:00
 */
public interface LoginService {
	public List<Employee> findEmp(Employee emp);
	public List<Employee> findBy_Pid(Employee emp);
	public String findEmpByName(String name);
}
