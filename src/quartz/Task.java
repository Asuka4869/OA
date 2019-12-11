/**
 * @Autor WCJ
 * @Date 2019年9月27日上午10:54:31
 */
package quartz;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author WCJ
 * @Date 2019年9月27日 上午10:54:31
 */
@Component("task")
public class Task {
	public void show(){
		Date d=new Date();
		System.out.println("现在时间是:"+d.toLocaleString());
	}
}
