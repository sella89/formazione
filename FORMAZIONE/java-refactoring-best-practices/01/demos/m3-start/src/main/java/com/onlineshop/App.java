package com.onlineshop;


import static java.time.LocalDate.now;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.onlineshop.entities.Customer;
import com.onlineshop.entities.Order;
import com.onlineshop.items.Cheese;
import com.onlineshop.items.Chocolate;
import com.onlineshop.items.Item;

public class App {


    public static void main(String[] args){

        // Create customer
        Customer customer1 = new Customer("GOLD", "MyStreet 123, US");

        // add items to list
        List<Item> shoppingList = Arrays.asList(new Chocolate(), new Chocolate(), new Cheese());
        Order order = new Order(shoppingList,"DummyVoucher");
        CheckoutHandler checkout = new CheckoutHandler();
        // calculate total
        double total1 = checkout.calculateTotal(order, customer1);
        System.out.println("Total price for goods: " + total1);



        




        LocalDate start = now().plusDays(1);
        LocalDate end = now().plusDays(2);

        checkout.setDeliveryTimeWindow(start, end);



    }
}
