/**
 * @Autor WCJ
 * @Date 2019��9��18������10:52:46
 */
package test;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author WCJ
 * @Date 2019��9��18�� ����10:52:46
 */
public class TestExport {
	public static void main(String[] args) {
		AnnotationConfiguration cfg=new AnnotationConfiguration().configure();
		SchemaExport ex=new SchemaExport(cfg);
		ex.create(true,true);
	}
}
