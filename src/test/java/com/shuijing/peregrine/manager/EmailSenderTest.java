package com.shuijing.peregrine.manager;

import com.shuijing.peregrine.manager.mail.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/09/08
 */
@SpringBootTest
public class EmailSenderTest {

	@Autowired
	private EmailSender emailSender;

	@Test
	public void sendSimpleMail() {
		emailSender.sendSimpleMail("简单邮件","测试");
	}
}