package com.example.demo.dao;


import com.example.demo.service.Security;
import com.example.demo.model.Mail;
import com.example.demo.model.Patient;

public interface MailService {
	
	public void sendEmail(Patient patient,Mail mail, Security security);
	public void setConfiguration();
}
