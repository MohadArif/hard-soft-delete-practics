package com.practice.demospringbootproject.controller;

import com.practice.demospringbootproject.payload.CustomerRequest;
import com.practice.demospringbootproject.payload.CustomerResponse;
import com.practice.demospringbootproject.service.CustomerService;
import org.springframework.http.HttpStatus;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

        private final CustomerService customerService;

        public CustomerController(CustomerService customerService) {
            this.customerService = customerService;
        }

        @PostMapping("/create")
        ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
            CustomerResponse customer = customerService.createCustomer(customerRequest);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        }

        @GetMapping("/all")
        ResponseEntity<List<CustomerResponse>> getAllCustomers(){
            List<CustomerResponse> customers = customerService.findAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id){
            CustomerResponse customer = customerService.findCustomerById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }

        @PutMapping("/update/{id}")
        ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest){
            customerService.updateCustomer(id, customerRequest);
            return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        ResponseEntity<String> deleteCustomer(@PathVariable Long id){
            customerService.deleteCustomer(id);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        }

        @DeleteMapping("/delete-softly/{id}")
        ResponseEntity<String> deleteCustomerSoftly(@PathVariable Long id){
            String message = customerService.deleteCustomerByIdSoftly(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }


}
