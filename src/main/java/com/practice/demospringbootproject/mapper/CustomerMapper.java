package com.practice.demospringbootproject.mapper;

import com.practice.demospringbootproject.entity.Customer;
import com.practice.demospringbootproject.payload.CustomerRequest;
import com.practice.demospringbootproject.payload.CustomerResponse;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest customerRequest) {
        if(customerRequest == null) {
            return null;
        }
        return Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .customerEmail(customerRequest.getCustomerEmail())
                .customerPhone(customerRequest.getCustomerPhone())
                .customerAddress(customerRequest.getCustomerAddress())
                .isDeleted(false)
                .build();
    }

    public static CustomerResponse  toResponse(Customer customer){
        if(customer==null) return null;
    return CustomerResponse.builder()
            .id(customer.getId())
            .customerName(customer.getCustomerName())
            .customerEmail(customer.getCustomerEmail())
            .customerPhone(customer.getCustomerPhone())
            .customerAddress(customer.getCustomerAddress())
            .build();
    }

    public static void updateEntity(Customer customer, CustomerRequest customerRequest) {
        if(customer == null || customerRequest == null) {
            return;
        }
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setCustomerEmail(customerRequest.getCustomerEmail());
        customer.setCustomerPhone(customerRequest.getCustomerPhone());
        customer.setCustomerAddress(customerRequest.getCustomerAddress());
    }

}