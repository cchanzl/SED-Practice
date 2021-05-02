package com.worldpay;

// DO NOT CHANGE THE CODE IN THIS FILE

import ic.doc.PaymentMethod;

public class CardNumber implements PaymentMethod {

  private CardNumber(String cardnumber) {
    if (cardnumber.length() != 16) {
      throw new IllegalArgumentException("card number must be 16 digits");
    }
  }

  @Override
  public PaymentMethod createPayment(String number){
    return new CardNumber(number);
  };

    // imagine more code here - not implemented for the exam

}
