package com.revshop1.service;

import com.revshop1.model.Buyer;
import com.revshop1.repository.BuyerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    
    @Autowired
    private MailService mailService;  // Inject MailService
    
    private String generatedOtp;

    public String saveBuyer(Buyer buyer) {
    	if (buyerRepository.findByEmail(buyer.getEmail()) != null) {
            return "Email already in use"; // Return a message for a duplicate email
        }
        buyerRepository.save(buyer);
        sendRegistrationEmail(buyer);
        return "Success";
    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Buyer getBuyerById(int id) {
        return buyerRepository.findById(id).orElse(null);
    }

    
    public Buyer updateBuyer(int id, Buyer updatedBuyer) {
        Buyer existingBuyer = buyerRepository.findById(id).orElse(null);
        if (existingBuyer != null) {
            existingBuyer.setFirstName(updatedBuyer.getFirstName());
            existingBuyer.setLastName(updatedBuyer.getLastName());
            existingBuyer.setEmail(updatedBuyer.getEmail());
            existingBuyer.setPassword(updatedBuyer.getPassword());  
            existingBuyer.setUpdatedAt(updatedBuyer.getUpdatedAt());
            return buyerRepository.save(existingBuyer);
        }
        return null;
    }

    public void deleteBuyer(int id) {
        buyerRepository.deleteById(id);
    }
    
    public Buyer findByEmailAndPassword(String email, String password) {
        return buyerRepository.findByEmailAndPassword(email, password);
    }
    
    private void sendRegistrationEmail(Buyer buyer) {
        // Prepare the email content
        String subject = "Welcome to RevShop!";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Thank you for registering with RevShop.\n" +
            "Login Email: %s\n"+
            "Password: %s\n\n" +
            "We hope you enjoy shopping with us!\n\n" +
            "Best Regards,\nRevShop Team",
            buyer.getFirstName(), buyer.getLastName(),
            buyer.getEmail(), buyer.getPassword() 
        );

        // Send registration email
        mailService.sendRegistrationEmail(buyer.getEmail(), subject, body);
    }
    
 // Method to send OTP to buyer's email
    public boolean sendOtpToEmail(String email) {
        if (!buyerRepository.existsByEmail(email)) {
            return false; // Email does not exist
        }
        // Generate OTP
        generatedOtp = String.valueOf((int) (Math.random() * 9000) + 1000);
        // Send OTP email using the new method
        mailService.sendPasswordResetEmail(email, generatedOtp);
        return true;
    }

    // Method to verify the OTP
    public boolean verifyOtp(String email, String otp) {
        return generatedOtp != null && generatedOtp.equals(otp);
    }

    public void updatePassword(String email, String newPassword) {
        Optional<Buyer> optionalBuyer = buyerRepository.findOptionalByEmail(email);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = optionalBuyer.get();
            buyer.setPassword(newPassword);
            buyerRepository.save(buyer);

            // Construct a detailed message
            String subject = "Your Password Has Been Successfully Updated!";
            String body = "Dear " + buyer.getFirstName() + " "+buyer.getLastName()+ ",\n\n" +
                          "Your password has been successfully updated.\n" +
                          "Here are your updated credentials:\n\n" +
                          "Email: " + email + "\n" +
                          "Password: " + newPassword + "\n\n" +
                          "If you didn't request this change, please contact our support team immediately.\n\n" +
                          "Thank you for shopping with us!\n" +
                          "Best regards,\n" +
                          "The RevShop Team";

            // Send the detailed email
            mailService.sendEmail(email, subject, body);
        } else {
            // Handle case where buyer does not exist
            throw new IllegalArgumentException("Buyer not found with email: " + email);
        }
    }

    // Method to get a buyer by email
    public Optional<Buyer> getBuyerByEmail(String email) {
        return buyerRepository.findOptionalByEmail(email); // Return the Optional directly
    }
    

    public void updateBuyer(Buyer buyer) {
        Buyer existingBuyer = getBuyerById(buyer.getBuyerId());
        existingBuyer.setFirstName(buyer.getFirstName());
        existingBuyer.setLastName(buyer.getLastName());
        existingBuyer.setEmail(buyer.getEmail());
        buyerRepository.save(existingBuyer);
    }

	

	
	


}