/**
 * @Autor WCJ
 * @Date 2019年9月18日上午11:46:53
 */
package daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import condb.HibernateUtil;
import dao.LoginDao;
import vo.Employee;

/**
 * @author WCJ
 * @Date 2019年9月18日 上午11:46:53
 */
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {
	HibernateUtil hb = new HibernateUtil();

	@Override
	public List<Employee> findAll() {
		return null;
	}

	@Override
	public List<Employee> findEmp(Employee emp) {
		String hql = "from Employee where sn=:sn and password=md5(:password) ";
		List<Employee> list = hb.executeSelect(hql, emp);
		return list;
	}

	@Override
	public List<Employee> findBy_Pid(Employee emp) {
		String hql = "from Employee where department_id=:department_id and position_id=:position_id and status='在职'";
		List<Employee> list = hb.executeSelect(hql, emp);
		return list;
	}

	@Override
	public String findEmpByName(String name) {
		Employee emp = new Employee();
		emp.setName(name);
		String sql = "select * from sys_employee where name=:name";
		List<Employee> list = hb.executeSQLSelect(sql, emp);
		if (list.size() == 0) {
			return null;
		} else
			return list.get(0).getSn();
	}

}
