/**
 * @Autor WCJ
 * @Date 2019年9月18日上午11:39:57
 */
package dao;

import java.util.List;

import vo.Employee;

/**
 * @author WCJ
 * @Date 2019年9月18日 上午11:39:57
 */
public interface LoginDao {
	public List<Employee> findEmp(Employee emp);
	public List<Employee> findBy_Pid(Employee emp);
	public List<Employee> findAll();
	public String findEmpByName(String name);
}
