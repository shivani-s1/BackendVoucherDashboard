package com.va.voucher_request.exceptions;

public class VoucherNotFoundException extends RuntimeException {
	 public VoucherNotFoundException() {
	        super();
	    }
	 
	 public VoucherNotFoundException(String message) {
	        super(message);
	    }
}
