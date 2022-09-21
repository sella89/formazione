package com.onlineshop;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.onlineshop.entities.Customer;
import com.onlineshop.entities.Order;
import com.onlineshop.items.Item;

public class CheckoutHandler {

    private LocalDate deliveryWindowStart;
    private LocalDate deliveryWindowEnd;
    
    public double addDeliveryFee(Customer customer, double total) {
    	 // handle delivery fee
        if(isEligibleForFreeDelivery(customer.getMembership())){
            // do nothing
        } else {
            if(isUsAddress(customer.getAddress())){
                System.out.println("Adding flat delivery fee of 5 USD");
                total = total + 5;
            } else {
                System.out.println("Adding flat global delivery fee of 10 USD");
                total = total + 10;
            }
        }
        return total;
    }
    
    private boolean isUsAddress(String address) {
    	return address.contains("US");
    }
    
    private boolean isEligibleForFreeDelivery(String membership) {
    	return membership.equalsIgnoreCase("GOLD");
    }
    
    public double applyVoucher(String voucher, double price) {
    	// check if voucher is valid
        if(isValidVoucher(voucher)){
             price = BigDecimal.valueOf(price * 0.95).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            System.out.println("Voucher invalid");
        }
        return price;

    }
    
    private boolean isValidVoucher(String voucher) {
    	return voucher.equals("GIMME_DISCOUNT") || voucher.equals("CHEAPER_PLEASE");
    }

    private double sumItemPrices(List<Item> items) {
    	  double baseTotal = 0;

          // sum up the prices
          List<Double> prices = new ArrayList<>();
          for(Item item : items){
              baseTotal = baseTotal + item.price();              
          }
          
          return baseTotal;
    }
    
    public double calculateTotal(Order order, Customer customer){

    	double baseTotal = sumItemPrices(order.getItems());
    	baseTotal = applyVoucher(order.getVoucher(), baseTotal);
        return addDeliveryFee(customer, baseTotal);
        
    }

    public void setDeliveryTimeWindow(LocalDate deliveryStart, LocalDate deliveryEnd){
        this.deliveryWindowStart = deliveryStart;
        this.deliveryWindowEnd = deliveryEnd;

        System.out.println(String.format("Your Order will delivered some time between %s and %s", deliveryWindowStart, deliveryWindowEnd));
    }

}
