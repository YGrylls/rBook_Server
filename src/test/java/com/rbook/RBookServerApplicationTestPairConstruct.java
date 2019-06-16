package com.rbook;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbook.DAO.TestDao;
import com.rbook.common.ConstructRequest;
import com.rbook.common.IfSuccessResponse;
import com.rbook.common.UserHeader;
import com.rbook.one2one.ConstructController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RBookServerApplicationTestPairConstruct {

	@Autowired
	private ConstructController ctrl;

	@Autowired
	private TestDao dao;

	public IfSuccessResponse res = null;

	@Before
	public void initBeforeClass() {
		dao.addUser("whitebox_", "t1lEGFNnF4E=", "222222222222222222", 0, 0, "whitey");
		dao.addUser("blackbox_", "t1lEGFNnF4E=", "222222222222222223", 0, 0, "blacky");
		dao.addUser("greybox_", "t1lEGFNnF4E=", "222222222222222224", 0, 0, "grenny");
		dao.createDebt("greybox_", "whitebox_", 3, "hello", LocalDate.now().toString(), 0, true, "testuuid_");
	}

	@After
	public void rollback() {
		dao.deleteUser("whitebox_");
		dao.deleteUser("blackbox_");
		dao.deleteUser("greybox_");
	}

	@Test
	public void ConstructorTest1() {
		ConstructRequest req = new ConstructRequest();
		UserHeader uh = new UserHeader();
		uh.setUsername("looooooooooooooooooooongusername");
		uh.setPassword("looooooooooooooooooooonpaswwrod");
		req.setUh(uh);
		req.setCounterName("loooooooooooooooooooncountername");
		req.setDesc("looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
				+ "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
				+ "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
				+ "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooodesc");
		res = ctrl.construct(req, null, null);
		System.out.println("---test1---\n" + res);
	}

	@Test
	public void ConstructorTest2() {
		ConstructRequest req = new ConstructRequest();
		UserHeader uh = new UserHeader();
		uh.setUsername("greyboxx_");
		uh.setPassword("wrongps");
		req.setUh(uh);
		req.setCounterName("dontmatter");
		req.setDesc("hello");
		res = ctrl.construct(req, null, null);
		System.out.println("---test2---\n" + res);

	}

	@Test
	public void ConstructorTest3() {
		ConstructRequest req = new ConstructRequest();
		UserHeader uh = new UserHeader();
		uh.setUsername("greybox_");
		uh.setPassword("111");
		req.setUh(uh);
		req.setCounterName("blackboxx_");
		req.setDesc("hello");
		res = ctrl.construct(req, null, null);
		System.out.println("---test3---\n" + res);
	}

	@Test
	public void ConstructorTest4() {
		ConstructRequest req = new ConstructRequest();
		UserHeader uh = new UserHeader();
		uh.setUsername("greybox_");
		uh.setPassword("111");
		req.setUh(uh);
		req.setCounterName("whitebox_");
		req.setDesc("hello");
		res = ctrl.construct(req, null, null);
		System.out.println("---test4---\n" + res);
	}

	@Test
	public void ConstructorTest5() {
		ConstructRequest req = new ConstructRequest();
		UserHeader uh = new UserHeader();
		uh.setUsername("greybox_");
		uh.setPassword("111");
		req.setUh(uh);
		req.setCounterName("blackbox_");
		req.setDesc("hello");
		res = ctrl.construct(req, null, null);
		System.out.println("---test5---\n" + res);
	}

}
