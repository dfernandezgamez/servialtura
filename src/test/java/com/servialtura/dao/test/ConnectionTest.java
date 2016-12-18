package com.servialtura.dao.test;
import java.util.List;

import javax.transaction.SystemException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.servialtura.contabilidad.base.model.Usuario;
import org.servialtura.contabilidad.base.service.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContextTest.xml")  

public class ConnectionTest {


	@Test
	public void testWrite() {
		// Just a write, verify id set
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");
		UsersService service=(UsersService) ctx.getBean("UsersService");
		
		try {
			List<Usuario> list=service.listUsuarios();
			for(Usuario u:list){
				System.out.println(u.getUsuario());
			}
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
