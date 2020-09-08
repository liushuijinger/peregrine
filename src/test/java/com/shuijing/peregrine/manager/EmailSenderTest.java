package com.shuijing.peregrine.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/09/08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailSenderTest {

	@Autowired
	private EmailSender emailSender;

	@Test
	public void sendSimpleMail() {
		emailSender.sendSimpleMail("简单邮件","测试");
	}
}