package com.example.demo.service;



import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MailService;
import com.example.demo.dao.PatientRepository;
import com.example.demo.model.Mail;
import com.example.demo.model.Patient;

@Service
public class PatientService implements MailService{
	@Autowired
	JavaMailSender mailSender;
	@Autowired
    private Environment env;
	@Autowired
	PatientRepository patientRepository;
	public void sendEmail(Patient patient,Mail mail, Security security)
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		 
        try {
         
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), security.decrypt(patient.getName(),env.getProperty("security.secretKey"))));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
 
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

	}
	
	public void setConfiguration()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		 
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));
 
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
 
        mailSender.setJavaMailProperties(javaMailProperties);	
	}
	
	public void savePatientDetails(Patient patient)
	{
		patientRepository.save(patient);
	}

}
