/**
 * @Autor WCJ
 * @Date 2019��9��27������10:54:31
 */
package quartz;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author WCJ
 * @Date 2019��9��27�� ����10:54:31
 */
@Component("task")
public class Task {
	public void show(){
		Date d=new Date();
		System.out.println("����ʱ����:"+d.toLocaleString());
	}
}
