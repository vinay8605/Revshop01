package com.revshop1.controllerTesting;
import com.revshop1.controller.BuyerController;
import com.revshop1.model.Buyer;
import com.revshop1.service.BuyerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuyerControllerTest {

    @InjectMocks
    private BuyerController buyerController;

    @Mock
    private BuyerService buyerService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testRegisterBuyer() {
        Buyer buyer = new Buyer();
        buyer.setEmail("test@example.com"); // Set necessary fields for testing

        String viewName = buyerController.registerBuyer(buyer, model);
        verify(buyerService, times(1)).saveBuyer(buyer);
        assertEquals("redirect:/buyer/success", viewName);
    }

    @Test
    void testRegistrationSuccess() {
        String viewName = buyerController.registrationSuccess();
        assertEquals("success", viewName);
    }
}
