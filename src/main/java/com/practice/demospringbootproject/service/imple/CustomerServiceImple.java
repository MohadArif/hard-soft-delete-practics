package com.practice.demospringbootproject.service.imple;

import com.practice.demospringbootproject.entity.Customer;
import com.practice.demospringbootproject.mapper.CustomerMapper;
import com.practice.demospringbootproject.payload.CustomerRequest;
import com.practice.demospringbootproject.payload.CustomerResponse;
import com.practice.demospringbootproject.repository.CustomerRepository;
import com.practice.demospringbootproject.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImple implements CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerServiceImple(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerMapper.toEntity(customerRequest);
        Customer saveCustomer = customerRepository.save(customer);
        return CustomerMapper.toResponse(saveCustomer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
//        return customerRepository.findAll().stream().map(CustomerMapper::toResponse).toList();
        /**
          better approch is to use findByisDeletedFalse() instead of findAll() because findAll() will return all the customers including the deleted ones.
         */
        return customerRepository.findByIsDeletedFalse()
                .stream().map(CustomerMapper::toResponse).toList();
    }

    @Override
    public CustomerResponse findCustomerById(Long id) {
//        Customer customer = customerRepository.
//                findById(id).orElseThrow(
//                        () -> new RuntimeException("Customer not found with id: " + id));
//        if (customer.getIsDeleted()){
//            throw new RuntimeException("Customer not found with id: " + id);
//        }
        /*
        instead of using the above statement use this.
        Boolean.TRUE.equals()?
        Because Boolean is an object and can be null.
         */
//        if (Boolean.TRUE.equals(customer.getIsDeleted())) {
//            throw new RuntimeException("Customer not found with id: " + id);
//        }
//        return CustomerMapper.toResponse(customer);

        /**
         * better approach is to use findByIdAndIsDeletedFalse() instead of findById() because findById() will return the customer even if it is deleted. So we can use findByIdAndIsDeletedFalse() to get the customer only if it is not deleted.
         */
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public void updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + id));
        CustomerMapper.updateEntity(customer, customerRequest);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

    @Override
    public String deleteCustomerByIdSoftly(Long id) {
//        Customer customer = customerRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("Customer not found with id: " + id));

        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + id));
        customer.setIsDeleted(true);
        customerRepository.save(customer);
        return "customer deleted successfully with id: " + id;
    }
}
