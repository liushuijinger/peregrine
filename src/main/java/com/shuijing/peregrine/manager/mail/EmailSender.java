package com.shuijing.peregrine.manager.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/09/07
 */
@Slf4j
@Component
public class EmailSender {

	@Value("${mail.from}")
	private String from;

	@Value("${mail.to}")
	private String to;

	@Autowired
	private JavaMailSender javaMailSender;

	static {
		System.setProperty("mail.mime.splitlongparameters","false");
	}

	/**
	 * 发送简单文本邮件
	 *
	 * @param subject 主题
	 * @param text    正文
	 */
	public void sendSimpleMail(String subject, String text) {
		sendSimpleMail(to, subject, text);
	}

	/**
	 * 发送简单文本邮件
	 *
	 * @param to      收件人
	 * @param subject 主题
	 * @param text    正文
	 */
	public void sendSimpleMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		try {
			message.setFrom(from);
			message.setTo(to.split(","));
			message.setSubject(subject);
			message.setText(text);
			javaMailSender.send(message);
			log.info("{}给{}发送邮件成功", from, to);
		} catch (MailException e) {
			log.error("{}给{}发送邮件出错", from, to, e);
		}
	}

	/**
	 * 发送 Html 邮件
	 *
	 * @param subject 主题
	 * @param text    正文
	 */
	public void sendHtmlMail(String subject, String text) {
		sendHtmlMail(to, subject, text);
	}

	/**
	 * 发送 Html 邮件
	 *
	 * @param to      收件人
	 * @param subject 主题
	 * @param text    正文
	 */
	public void sendHtmlMail(String to, String subject, String text) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to.split(","));
			helper.setSubject(subject);
			helper.setText(text,true);
			javaMailSender.send(message);
			log.info("{}给{}发送邮件成功", from, to);
		} catch (MessagingException e) {
			log.error("{}给{}发送邮件出错", from, to, e);
		}
	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param subject 主题
	 * @param text 正文
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsMail(String subject, String text, String filePath) {
		sendAttachmentsMail(to, subject, text, filePath);
	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param to 收件人
	 * @param subject 主题
	 * @param text 正文
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsMail(String to, String subject, String text, String filePath) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
			helper.setFrom(from);
			helper.setTo(to.split(","));
			helper.setSubject(subject);
			helper.setText(text,Boolean.TRUE);

			FileSystemResource fileSystemResource = new FileSystemResource(filePath);
			String filename = fileSystemResource.getFilename();
			helper.addAttachment(filename,fileSystemResource);
			javaMailSender.send(message);
			log.info("{}给{}发送邮件成功", from, to);
		} catch (MessagingException e) {
			log.error("{}给{}发送邮件出错", from, to, e);
		}
	}
}
