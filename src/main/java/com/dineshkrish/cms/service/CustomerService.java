package com.dineshkrish.cms.service;

import com.dineshkrish.cms.dao.CustomerDAO;
import com.dineshkrish.cms.exception.CustomerNotFoundException;
import com.dineshkrish.cms.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;


    //private int customerIdCount=1;
   // private List<Customer> customerList=new CopyOnWriteArrayList<>();

    public Customer addCustomer(Customer customer){
        /*customer.setCustomerId(customerIdCount);
        customerList.add(customer);
        customerIdCount++;*/
        return customerDAO.save(customer);
        //return customer;
    }

    public List<Customer> getCustomers(){

        return customerDAO.findAll();

        //return customerList;
    }

    public Customer getCustomer(int customerId){

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);

        if(!optionalCustomer.isPresent())
            throw new CustomerNotFoundException("Customer record is not available");
        /*return customerList
                .stream()
                .filter(c-> c.getCustomerId() == customerId)
                .findFirst()
                .get();*/
        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId,Customer customer) {

        customer.setCustomerId(customerId);
        /*customerList
                .stream()
                .forEach(c -> {
                    if (c.getCustomerId() == customerId) {
                        c.setCustomerFirstName(customer.getCustomerFirstName());
                        c.setCustomerLastName(customer.getCustomerLastName());
                        c.setCustomerEmail(customer.getCustomerEmail());
                    }
                });
        return customerList
                .stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .get();*/
        return customerDAO.save(customer);
    }

    public void deleteCustomer(int customerId){

        /*customerList
                .stream()
                .forEach(c->{
                    if(c.getCustomerId()==customerId)
                        customerList.remove(c);
                });*/
        customerDAO.deleteById(customerId);
    }
}
