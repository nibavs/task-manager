package com.github.nibavs.task_manager_server.controller;

import com.github.nibavs.task_manager_server.entity.User;
import com.github.nibavs.task_manager_server.exception.UserAlreadyExistsException;
import com.github.nibavs.task_manager_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @Test
    void testSignUp_ThrowsUserAlreadyExistsException() {

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("userUno");
        mockUser.setPassword("passwordUno");
        mockUser.setCreatedAt(LocalDateTime.now());
        userRepository.save(mockUser);

        when(userRepository.findByUsername("userUno")).thenReturn(Optional.of(mockUser));

        UserAlreadyExistsException exception = assertThrows(
                UserAlreadyExistsException.class,
                () -> authController.signUp(mockUser)
        );

        assertEquals("User \"userUno\" already exists", exception.getMessage());

//        verify(sellerRepository, times(1)).findById(sellerId);
    }

//    @Test
//    void testGetAllSellers_ReturnsAllSellers() {
//        Seller seller1 = new Seller();
//        seller1.setId(1L);
//
//        Seller seller2 = new Seller();
//        seller2.setId(2L);
//
//        List<Seller> sellers = Arrays.asList(seller1, seller2);
//        when(sellerRepository.findAll()).thenReturn(sellers);
//
//        List<Seller> response = sellerController.getAllSellers();
//
//        assertNotNull(response);
//        assertEquals(2, response.size());
//        verify(sellerRepository, times(1)).findAll();
//    }
//    @Test
//    void testGetSellerById_ReturnsSeller() {
//        Long sellerId = 1L;
//        Seller mockSeller = new Seller();
//        mockSeller.setId(sellerId);
//        mockSeller.setName("Продавец 1");
//
//        when(sellerRepository.findById(sellerId)).thenReturn(Optional.of(mockSeller));
//
//        ResponseEntity<Seller> response = sellerController.getSellerById(sellerId);
//
//        assertNotNull(response.getBody());
//        assertEquals("Продавец 1", response.getBody().getName());
//        verify(sellerRepository, times(1)).findById(sellerId);
//    }


}
