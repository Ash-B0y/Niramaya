package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.example.demo.model.Mail;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import com.example.demo.service.Security;

@PropertySource("classpath:mail.properties")
@RestController
public class PatientController {
	@Autowired
    private Environment env;
	@Autowired
	PatientService patientService;
	@Autowired
	Security security;
	Patient patient;
	Mail mail;
	@PostMapping("/savedata")
	 public RedirectView saveDetails(@RequestParam("name") String name,
             @RequestParam("mobileNumber") String mobileNumber, @RequestParam("concerns") String concerns,@RequestParam("baseurl") String baseurl) {
        security = new Security();
        patient = new Patient();
		patient.setName(security.encrypt(name,env.getProperty("security.secretKey")));
        patient.setMobileNumber(security.encrypt(mobileNumber,env.getProperty("security.secretKey")));
        patient.setConcerns(security.encrypt(concerns,env.getProperty("security.secretKey")));
        mail = new Mail();
        mail.setMailFrom(env.getProperty("mail.sender"));
        mail.setMailTo(env.getProperty("mail.receiver"));
        mail.setMailSubject(env.getProperty("mail.subject"));
        mail.setMailContent("Greetings Dr.H.M.Shruthi,\nThe Following individual has catered interest towards your services, Do find the details of the concerned individual below:\n 1.Name - "+security.decrypt(patient.getName(),env.getProperty("security.secretKey"))+"\n 2.Contact No - "+security.decrypt(patient.getMobileNumber(),env.getProperty("security.secretKey"))+"\n 3. Concern - "+security.decrypt(patient.getConcerns(),env.getProperty("security.secretKey"))+"\n Do Reach out to the concerned Individual at the earliest. \n Regards, \n NIRAMAYA HOMEO CARE.");
        patientService.setConfiguration();
        patientService.sendEmail(patient,mail,security);
        patientService.savePatientDetails(patient);
        RedirectView redirectView = new RedirectView(baseurl);
        return redirectView;
                 
}
	
	
	
}
