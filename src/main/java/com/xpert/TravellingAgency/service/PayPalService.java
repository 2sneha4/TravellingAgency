package com.xpert.TravellingAgency.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PayPalService {
	
    private APIContext apiContext;
	
	public PayPalService() {
		
		apiContext = new APIContext(
				"AcToWExzIerTyjDPVl-wSQrw-8Zd48K9qhUSZxUAfU--NF0bwqmjGQKQMpKeUDS_xFN5-PMrdkzBME2F",
				"ED5CSY2AdtdnpPLqHUnicoT-zFRmzGLT7CwSfvgoBk3zHtE_4vWUNpdc4j9-gYHrSSr-NZG3c7hOV_H3",
				"sandbox");
	}

    public Payment createPayment(String total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(total);

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(new Payer().setPaymentMethod(method));
        payment.setTransactions(List.of(transaction));
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = null;
		try {
			createdPayment = payment.create(apiContext);
		} catch (PayPalRESTException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

        return createdPayment;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }

}
