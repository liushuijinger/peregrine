package com.shuijing.peregrine.manager;

import com.shuijing.peregrine.common.config.MailSenderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 多邮件源发送
 * @author liushuijing (shuijing@shanshu.ai)
 * @date 2020/02/21
 */
@Slf4j
@Component
public class MultiEMailSender {

	@Value("${mail.from}")
	private String from;

	@Value("${mail.to}")
	private String to;

	@Autowired
	private MailSenderBuilder mailSenderBuilder;

	@Autowired
	private TemplateEngine templateEngine;

	static {
		System.setProperty("mail.mime.splitlongparameters", "false");
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
		sendSimpleMail(from, to, subject, text);
	}
	/**
	 * 发送简单文本邮件
	 *
	 * @param from    发件人
	 * @param to      收件人
	 * @param subject 主题
	 * @param text    正文
	 */
	public void sendSimpleMail(String from, String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		try {
			message.setFrom(from);
			message.setTo(to.split(","));
			message.setSubject(subject);
			message.setText(text);
			JavaMailSenderImpl mailSender = mailSenderBuilder.getSender(from);
			mailSender.send(message);
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
		sendHtmlMail(from, to, subject, text);
	}

	/**
	 * 发送 Html 邮件
	 *
	 * @param to      收件人
	 * @param subject 主题
	 * @param text    正文
	 */
	public void sendHtmlMail(String from, String to, String subject, String text) {
		JavaMailSenderImpl mailSender = mailSenderBuilder.getSender(from);
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
			helper.setFrom(from);
			helper.setTo(to.split(","));
			helper.setSubject(subject);
			helper.setText(text, Boolean.TRUE);
			mailSender.send(message);
			log.info("{}给{}发送邮件成功", from, to);
		} catch (MessagingException e) {
			log.error("{}给{}发送邮件出错", from, to, e);
		}
	}

	/**
	 * 发送 模板 邮件
	 *
	 * @param subject      主题
	 * @param context      正文
	 * @param templateName 模板名
	 */
	public void sendTemplateMail(String subject, Context context, String templateName) {
		sendTemplateMail(from, to, subject, context, templateName);
	}

	/**
	 * 发送 模板 邮件
	 *
	 * @param to           收件人
	 * @param subject      主题
	 * @param context      正文
	 * @param templateName 模板名
	 */
	public void sendTemplateMail(String from, String to, String subject, Context context, String templateName) {
		String mailContext = templateEngine.process(templateName, context);
		sendHtmlMail(from, to, subject, mailContext);
	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param subject  主题
	 * @param text     正文
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsMail(String subject, String text, String filePath) {
		sendAttachmentsMail(to, subject, text, filePath);
	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param to       收件人
	 * @param subject  主题
	 * @param text     正文
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsMail(String to, String subject, String text, String filePath) {
		JavaMailSenderImpl mailSender = mailSenderBuilder.getSender(from);
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
			helper.setFrom(from);
			helper.setTo(to.split(","));
			helper.setSubject(subject);
			helper.setText(text, Boolean.TRUE);

			FileSystemResource fileSystemResource = new FileSystemResource(filePath);
			String filename = fileSystemResource.getFilename();
			helper.addAttachment(filename, fileSystemResource);
			mailSender.send(message);
			log.info("{}给{}发送邮件成功", from, to);
		} catch (MessagingException e) {
			log.error("{}给{}发送邮件出错", from, to, e);
		}
	}
}
