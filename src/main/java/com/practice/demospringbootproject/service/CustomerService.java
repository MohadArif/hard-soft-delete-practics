package com.practice.demospringbootproject.service;

import java.util.*;
import com.practice.demospringbootproject.payload.CustomerRequest;
import com.practice.demospringbootproject.payload.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> findAllCustomers();
    CustomerResponse findCustomerById(Long id);
    void updateCustomer(Long id, CustomerRequest customerRequest);
    void deleteCustomer(Long id);
    String deleteCustomerByIdSoftly(Long id);

}
