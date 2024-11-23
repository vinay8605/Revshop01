package com.revshop1.serviceTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.revshop1.service.MailService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailService mailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendRegistrationEmail() {
        String toEmail = "test@example.com";
        String subject = "Welcome!";
        String body = "Thank you for registering.";

        mailService.sendRegistrationEmail(toEmail, subject, body);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage message = captor.getValue();

        assertEquals(toEmail, message.getTo()[0]);
        assertEquals(subject, message.getSubject());
        assertEquals(body, message.getText());
        assertEquals("vinay.mudapaka2002@gmail.com", message.getFrom());
    }

    @Test
    public void testSendPasswordResetEmail() {
        String toEmail = "test@example.com";
        String otp = "123456";

        mailService.sendPasswordResetEmail(toEmail, otp);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage message = captor.getValue();

        assertEquals(toEmail, message.getTo()[0]);
        assertEquals("Password Reset Request", message.getSubject());
        assertEquals("Your OTP for password reset is: " + otp, message.getText());
    }

    @Test
    public void testSendOrderConfirmationEmail() {
        String toEmail = "test@example.com";
        Integer orderId = 101;

        mailService.sendOrderConfirmationEmail(toEmail, orderId);
        
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage message = captor.getValue();

        assertEquals(toEmail, message.getTo()[0]);
        assertEquals("Order Confirmation", message.getSubject());
        assertEquals("Your order has been successfully placed. Your order ID is: " + orderId, message.getText());
    }
}