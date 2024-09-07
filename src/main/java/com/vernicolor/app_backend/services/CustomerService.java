package com.vernicolor.app_backend.services;

import com.vernicolor.app_backend.models.Customer;
import com.vernicolor.app_backend.models.ProductFamily;
import com.vernicolor.app_backend.repositories.CustomerRepository;
import com.vernicolor.app_backend.repositories.ProductFamilyRepository;
import com.vernicolor.app_backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductFamilyRepository productFamilyRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ProductFamilyRepository productFamilyRepository) {
        this.customerRepository = customerRepository;
        this.productFamilyRepository = productFamilyRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        customer.setCustumerName(customerDetails.getCustumerName());
        customer.setCustumerEmail(customerDetails.getCustumerEmail());
        customer.setCustumerSerialNumber(customerDetails.getCustumerSerialNumber());
        return customerRepository.saveAndFlush(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
        public ProductFamily associateProductFamilyWithCustomer(Long customerId, Long productFamilyId) {
            Optional<Customer> customerOpt = customerRepository.findById(customerId);
            Optional<ProductFamily> productFamilyOpt = productFamilyRepository.findById(productFamilyId);

            if (customerOpt.isPresent() && productFamilyOpt.isPresent()) {
                Customer customer = customerOpt.get();
                ProductFamily productFamily = productFamilyOpt.get();

                productFamily.setCustomer(customer);
                customer.getProductFamilies().add(productFamily);

                productFamilyRepository.save(productFamily);
                customerRepository.save(customer);

                return productFamily;
            } else {
                throw new RuntimeException("Customer or ProductFamily not found");
            }
        }
    }

