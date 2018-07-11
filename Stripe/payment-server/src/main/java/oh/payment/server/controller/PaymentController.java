/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.payment.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.Plan;
import com.stripe.model.Product;
import com.stripe.model.Subscription;
import com.stripe.net.APIResource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author trungchanh
 */
@Controller
public class PaymentController {

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/payment2")
    public String payment2() {
        return "payment2";
    }

    @PostMapping("/payment")
    @ResponseBody
    public Object paymentHandle(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_Zyf3eFu08S48AQrwI9J6r64z";

        // Token is created using Checkout or Elements!
        // Get the payment token ID submitted by the form:
        String token = request.getParameter("stripeToken");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", 999);
        params.put("currency", "usd");
        params.put("description", "Example charge");
        params.put("source", token);
        try {
            Charge charge = Charge.create(params);
            System.out.println(charge.toJson());
            return charge.toJson();
        } catch (AuthenticationException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRequestException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIConnectionException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @PostMapping("/register")
    @ResponseBody
    public Object registerHandle(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_Zyf3eFu08S48AQrwI9J6r64z";

        // Token is created using Checkout or Elements!
        // Get the payment token ID submitted by the form:
        String token = request.getParameter("stripeToken");
        String email = request.getParameter("stripeEmail");

        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("source", token);
        
        try {
            Customer customer = Customer.create(params);
            System.out.println(customer.toJson());
            return customer.toJson();
        } catch (AuthenticationException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRequestException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIConnectionException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
    @GetMapping("/make-plan")
    public String makePlan() {
        return "make-plan";
    }
    
    @PostMapping("/make-plan")
    @ResponseBody
    public Object planHandle(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_Zyf3eFu08S48AQrwI9J6r64z";

        // Token is created using Checkout or Elements!
        // Get the payment token ID submitted by the form:
        String productName = request.getParameter("product-name");
        String planName = request.getParameter("plan-name");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", productName);
        params.put("type", "service");
        
        try {
            Product product = Product.create(params);
            
            params = new HashMap<String, Object>();
            params.put("product", product.getId());
            params.put("nickname", planName);
            params.put("interval", "month");
            params.put("currency", "usd");
            params.put("amount", 10000);
            Plan plan = Plan.create(params);
            System.out.println(plan.toJson());
            return plan.toJson();
        } catch (AuthenticationException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRequestException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIConnectionException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
    
    @GetMapping("/subscription")
    public String subscription() {
        return "subscription";
    }
    
    
    @PostMapping("/subscription")
    @ResponseBody
    public Object subscriptionHandle(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_Zyf3eFu08S48AQrwI9J6r64z";
        
        String customerId = request.getParameter("customer-id");
        String planId = request.getParameter("plan-id");

        Map<String, Object> item = new HashMap<>();
        item.put("plan", planId);
        Map<String, Object> items = new HashMap<>();
        items.put("0", item);
        Map<String, Object> params = new HashMap<>();
        params.put("customer", customerId);
        params.put("items", items);
        
        try {
           Subscription subscription = Subscription.create(params);
            System.out.println(subscription.toJson());
            return subscription.toJson();
        } catch (AuthenticationException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRequestException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIConnectionException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
     @GetMapping("/charge-by-customer-id")
    public String chargeByCustomerId() {
        return "charge-by-customer-id";
    }
    
    @PostMapping("/charge-by-customer-id")
    @ResponseBody
    public Object chargeByCustomerIdHandle(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_Zyf3eFu08S48AQrwI9J6r64z";

        // Token is created using Checkout or Elements!
        // Get the payment token ID submitted by the form:
        String customerId = request.getParameter("customer-id");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", 1000);
        params.put("currency", "jpy");
        params.put("description", "Charge by Stripe Customer ID");
        params.put("customer", customerId);
        try {
            Charge charge = Charge.create(params);
            System.out.println(charge.toJson());
            return charge.toJson();
        } catch (AuthenticationException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRequestException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIConnectionException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
    
    @PostMapping("/stripe/webhook")
    @ResponseBody
    public Object stripeWebhook(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
         // Retrieve the request's body and parse it as JSON:
        Event eventJson = APIResource.GSON.fromJson(json, Event.class);
        // Do something with eventJson
        response.setStatus(HttpStatus.OK.value());
        
        System.out.println(eventJson.toJson());
        
        return "OK";
    }
    
    
     @GetMapping("/create-product")
    public String createProduct() {
        return "create-product";
    }
    
    @PostMapping("/create-product")
    @ResponseBody
    public Object createProductHandle(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_Zyf3eFu08S48AQrwI9J6r64z";

        // Token is created using Checkout or Elements!
        // Get the payment token ID submitted by the form:
        String productName = request.getParameter("product-name");

        Map<String, Object> params = new HashMap<>();
        params.put("type", "service");
        params.put("name", productName);
        try {
            Product product = Product.create(params);
            System.out.println(product.toJson());
            return product.toJson();
        } catch (AuthenticationException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRequestException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIConnectionException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
}
