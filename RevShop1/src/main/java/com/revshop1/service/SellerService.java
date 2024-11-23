package com.revshop1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revshop1.model.Seller;
import com.revshop1.repository.SellerRepository;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    
    @Autowired
    private MailService mailService;
    
    private String generatedOtp;

    public Seller authenticateSeller(String email, String password) {
        // Find seller by email
        Optional<Seller> sellerOptional = sellerRepository.findByEmail(email);
        // Check if seller exists and passwords match
        if (sellerOptional.isPresent() && sellerOptional.get().getPassword().equals(password)) {
            return sellerOptional.get();
        }
        return null; // Authentication failed
    }

    public Seller findSellerById(int sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }

    public Seller updateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public void deleteSeller(int sellerId) {
        sellerRepository.deleteById(sellerId);
    }

    public Seller registerSeller(Seller seller) {
        // Save the seller in the database
        Seller registeredSeller = sellerRepository.save(seller);

        // Prepare the email content
        String subject = "Welcome to RevShop!";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Thank you for registering with RevShop.\n" +
            "Your business name: %s\n" +
            "Login Email: %s\n" +
            "Password: %s\n\n" +
            "We hope you enjoy selling with us!\n\n" +
            "Best Regards,\nRevShop Team",
            seller.getOwnerFirstName(), seller.getOwnerLastName(),
            seller.getBusinessName(), seller.getEmail(), seller.getPassword()
        );

        // Send registration email
        mailService.sendRegistrationEmail(seller.getEmail(), subject, body);

        return registeredSeller;
    }

    public boolean sendOtpToEmail(String email) {
        if (!sellerRepository.existsByEmail(email)) {
            return false; // Email does not exist
        }
        // Generate OTP
        generatedOtp = String.valueOf((int)(Math.random() * 9000) + 1000);
        // Send OTP email using the new method
        mailService.sendPasswordResetEmail(email, generatedOtp);
        return true;
    }
    
    public boolean verifyOtp(String email, String otp) {
        return generatedOtp != null && generatedOtp.equals(otp);
    }

    public void updatePassword(String email, String newPassword) {
        Optional<Seller> optionalSeller = sellerRepository.findByEmail(email);
        if (optionalSeller.isPresent()) {
            Seller seller = optionalSeller.get();
            seller.setPassword(newPassword);
            sellerRepository.save(seller);

            // Construct a detailed message for the seller
            String subject = "Your Password Has Been Successfully Updated!";
            String body = "Dear " + seller.getOwnerFirstName()+" "+seller.getOwnerLastName() + ",\n\n" +
                          "Your password has been successfully updated.\n" +
                          "Here are your updated credentials:\n\n" +
                          "Email: " + email + "\n" +
                          "Password: " + newPassword + "\n\n" +
                          "If you did not request this change, please contact our support team immediately.\n\n" +
                          "Thank you for being a valued seller on RevShop!\n" +
                          "Best regards,\n" +
                          "The RevShop Seller Support Team";

            // Send the email using the MailService
            mailService.sendEmail(email, subject, body);
        } else {
            // Handle case where the seller does not exist
            throw new IllegalArgumentException("Seller not found with email: " + email);
        }
    }



    
    public Optional<Seller> getSellerByEmail(String email) {
        return sellerRepository.findByEmail(email); // Return the Optional directly
    }

	
}