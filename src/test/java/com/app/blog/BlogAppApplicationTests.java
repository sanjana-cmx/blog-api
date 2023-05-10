package com.app.blog;

import com.app.blog.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest(){
		String className = userRepo.getClass().getName();
		String packageName = userRepo.getClass().getPackageName();
		System.out.println("Class Name: " +className);
		System.out.println("Package Name: "+packageName);

	}
}
