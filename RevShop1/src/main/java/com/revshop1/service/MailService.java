package com.revshop1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinay.mudapaka2002@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
 // New method for sending password reset emails
    public void sendPasswordResetEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinay.mudapaka2002@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Password Reset Request");
        message.setText("Your OTP for password reset is: " + otp);
        mailSender.send(message);
    }
    
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("vinay.mudapaka2002@gmail.com");
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
    public void sendOrderConfirmationEmail(String toEmail, Integer orderId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinay.mudapaka2002@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Order Confirmation");
        message.setText("Your order has been successfully placed. Your order ID is: " + orderId);
        mailSender.send(message);
    }
}