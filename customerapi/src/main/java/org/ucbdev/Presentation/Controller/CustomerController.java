package org.ucbdev.Presentation.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.ucbdev.Core.Application.Request.CreateRequest1;
import org.ucbdev.Core.Application.Request.CreateSingleCustomerRequest;
import org.ucbdev.Core.Application.Request.UpdateRequest1;
import org.ucbdev.Core.Application.Response.CreateSingleCustomerResponse;
import org.ucbdev.Core.Domain.Entities.Customer;
import org.ucbdev.Core.Domain.Entities.Order;

import java.text.MessageFormat;
import java.time.*;
import java.time.chrono.IsoEra;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;
import java.util.*;

@RestController
@RequestMapping("/api/v1/customerapi")
public class CustomerController {
    @PersistenceContext
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CustomerController(RestTemplate restTemplate, EntityManager entityManager, EntityManagerFactory entityManagerFactory, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.entityManager = entityManager;
        this.entityManagerFactory = entityManagerFactory;
        this.objectMapper = objectMapper;
    }
    @GetMapping(path = "get-single-customer")
    public String getSingleCustomer(){
        return this.restTemplate.getForObject(
                "http://PRODUCTAPI/api/v1/productapi/get-single-product",
                String.class
        );
    }
    @PostMapping(path="create-single-customer")
    public CreateSingleCustomerResponse createSingleCustomer(@RequestBody(required = true)CreateSingleCustomerRequest requestBody){
        return CreateSingleCustomerResponse.builder().id((long)1).name(requestBody.getName()).age(requestBody.getAge()).email(requestBody.getEmail()).build();
    }
    @GetMapping(path = "test1")
    public void Test1(){
        var x1 = "Ahmet1";
        var x2 = "Ali1";
        var x3 = String.format("String values: %s ve %s",x1,x2);
        var x6 = String.format("Boolean values: %b ve %b",x1,x2);
        var x7 = String.format("Char values: %c ve %c", 'a', 'b');
        var x8 = String.format("Integer values: %d ve %d",1,2);
        var x10 = String.format("Integer values: %03d ve %d",1,2);
        var x11 = String.format("Integer values: %05d ve %d",1,2);
        var x9 = String.format("Float values: %f", 12.12345);
        var x4 = new StringBuilder().append("Hello ").append(x1).append(" ").append("and ").append(x2).toString();
        var x5 = MessageFormat.format("Hello {0} and {1} and {1}",x1,x2);

        int x12 = 3;
        String x13 = String.valueOf(x12);
        String x14 = String.valueOf('a');
        long x15 = 5;
        String x16 = String.valueOf(x15);
        float x17 = 12.55F;
        String x18 = String.valueOf(x17);
        double x19 = 12.55d;
        String x20 = String.valueOf(x19);
        boolean x21 = true;
        String x22 = String.valueOf(x21);
        char[] x23 = new char[]{'a', 'h', 'm', 'e', 't', '1'};
        String x24 = String.valueOf(x23, 2, 3);
        var object1 = CreateSingleCustomerResponse.builder().name("ahmet1").email("ahmet1@gmail.com").build();
        String x25 = String.valueOf(object1);

        /*
        var x26 = LocalTime.MAX;
        var x27 = LocalTime.MIN;
        var x28 = LocalTime.now();
        var x29 = LocalTime.now(Clock.systemUTC());
        var x30 = LocalTime.now(ZoneId.of("Europe/Paris"));
        */


    }

    @GetMapping(path="test2")
    public String Test2(){
        char[] x1 = new char[]{'a', 'h', 'm', 'e', 't'};
        var x2 = String.copyValueOf(x1, 2, 3);
        String x3 = String.join(" ** ", "Ahmet1", "Mehmet1", "Ali1");
        return x3;
    }

    @GetMapping(path="test3")
    public void Test3(){
        var x1 = LocalDateTime.now();
        var x2 = x1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss:SSSS a G"));
        var x3 = x1.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        var x4 = x1.format(DateTimeFormatter.ofPattern("yyyy / MM / dd"));
        var x5 = x1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss", new Locale("EN", "INDIA", "WIN")));
        var x6 = x1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        var x7 = x1.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT));
        var x8 = x1.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        var x9 = new Locale("tr-TR", "TURKEY");
        var x10 = Locale.ENGLISH;
        Locale[] x11 = Locale.getAvailableLocales();
        Locale x12 = Locale.ENGLISH;
        Locale x13 = (Locale) x12.clone();
        String x14 = x12.getCountry();
        String x15 = x12.getLanguage();
        Locale x16 = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
        String x17 = x12.getDisplayCountry();
        String x18 = x12.getDisplayName();
        String x19 = x12.getDisplayLanguage();
        String x20 = x12.getDisplayVariant();
        String x21 = x12.getVariant();
        String x22 = x12.toString();
        Locale x23 = Locale.of("EN", "US", "WIN");
        var x24 = LocalDate.now();
        Clock x25 = Clock.systemDefaultZone();
        Clock x26 = Clock.systemUTC();
        Instant instant = Instant.now(Clock.systemUTC());
        // create ZoneId object for Asia/Calcutta zone
        ZoneId zoneId = ZoneId.systemDefault();
        Clock x27 = Clock.fixed(instant, zoneId);
        Clock x28 = Clock.system(zoneId);
        Clock x29 = Clock.tickMillis(zoneId);
        Clock x30 = Clock.tickMinutes(zoneId);
        Clock x31 = Clock.tickSeconds(zoneId);
        long now = System.currentTimeMillis();
        Instant x32 = Instant.ofEpochMilli(now);
        Instant x33 = Instant.ofEpochSecond(now, 345000000);
        Instant x34 = Instant.parse("1980-04-09T10:15:30.00Z");
        var x35 = Instant.EPOCH;
        var x36 = Instant.MAX;
        var x37 = Instant.MIN;
        LocalDate x38 = LocalDate.parse("2011-09-02", DateTimeFormatter.ISO_DATE);
        var x39 = x1.getDayOfMonth();
        var x40 = x1.getYear();
        var x41 = LocalDate.now();
        IsoEra x42 = x41.getEra();
        Set<String> x43 = ZoneId.getAvailableZoneIds();
        ZoneId x44 = ZoneId.of("Europe/London");
        var x45 = LocalDate.now();
        var x46 = LocalDate.now().plusDays(55);
        var x47 = x45.compareTo(x46);
        boolean x48 = x45.isAfter(x46);
        boolean x49 = x45.isBefore(x46);
        long x50 = x45.toEpochDay();
        long x51 = x45.toEpochSecond(LocalTime.now(), ZoneOffset.UTC);
        ZoneOffset x52 = ZoneOffset.ofHoursMinutes(7,15);
        ZoneOffset x53 = ZoneOffset.ofHours(4);
        ZoneOffset x54= ZoneOffset.of("+02:00");
        ZoneOffset x55 = ZoneOffset.ofTotalSeconds(500);
        Set<String> x56 = ZoneOffset.getAvailableZoneIds();
        String x57 = x45.toString();
        long x58 = x45.until(LocalDate.now().plusDays(5), ChronoUnit.MONTHS);
        LocalDate x59 = x45.withDayOfMonth(25);
        LocalDate x60 = x45.withDayOfYear(100);
        int x61 = x45.get(ChronoField.MONTH_OF_YEAR);
        LocalDate x62 = x45.with(ChronoField.MONTH_OF_YEAR, 6);
        boolean x63 = x45.isSupported(ChronoField.MONTH_OF_YEAR);
        ValueRange x64 = x45.range(ChronoField.MONTH_OF_YEAR);
        LocalTime x65 = LocalTime.MAX;
        LocalTime x66 = LocalTime.MIN;
        LocalTime x67 = LocalTime.MIDNIGHT;
        LocalTime x68 = LocalTime.NOON;
        var x69 = LocalTime.of(2, 25, 45, 99999);
        LocalDate x70 = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalTime x71 = LocalTime.ofSecondOfDay(500);
        LocalTime x72 = LocalTime.parse("10:15:45", DateTimeFormatter.ISO_TIME);
    }
    @GetMapping(path = "test4")
    public void test4(){
        var x1 = LocalTime.now();
        String x2 = x1.format(DateTimeFormatter.ISO_TIME);
        int x3 = x1.get(ChronoField.HOUR_OF_DAY);
        LocalDateTime x4 = x1.atDate(LocalDate.now());
        OffsetTime x5 = x1.atOffset(ZoneOffset.UTC);
        int x6 = x1.getHour();
        int x7 = x1.getMinute();
        int x8 = x1.getNano();
        int x9 = x1.getSecond();
        long x10 = x1.getLong(ChronoField.HOUR_OF_DAY);
        LocalTime x11 = x1.minusHours(5);
        LocalTime x12 = x1.minusMinutes(5);
        LocalTime x13 = x1.minusNanos(99999);
        LocalTime x14 = x1.minusSeconds(25);
        LocalTime x15 = x1.minus(5, ChronoUnit.HOURS);
        long x16 = x1.toNanoOfDay();
        long x17 = x1.toSecondOfDay();
        long x18 = x1.toEpochSecond(LocalDate.now(), ZoneOffset.UTC);
        long x19 = x1.until(LocalTime.now().plusHours(5), ChronoUnit.HOURS);
        LocalTime x20 = x1.truncatedTo(ChronoUnit.HOURS);

    }
    @GetMapping(path = "test5")
    public void test5(){
        var x1 = new Customer();

    }
    @GetMapping(path = "test6")
    public Customer test6(@RequestParam("customerId") Integer query1){
        TypedQuery<Customer> x1 = this.entityManager.createQuery("SELECT C FROM Customer C FULL JOIN C.orders O ON C.customerId=O.customer.customerId WHERE C.customerId=:customerId ORDER BY C.customerId LIMIT 5", Customer.class);
        x1.setParameter("customerId", query1);
        List<Customer> x1_1 = x1.getResultList();
        var x2 = this.entityManager.createQuery("SELECT C FROM Customer C FULL JOIN C.orders O ON C.customerId=O.customer.customerId WHERE C.customerId=:customerId", Customer.class).setParameter("customerId", query1).getSingleResult();
        var x3 = this.entityManager.find(Customer.class, query1);
        var x4 = this.entityManager.getReference(Customer.class, query1);
        return x2;
    }

    @PostMapping(path = "test7")
    @Transactional(rollbackOn = {Exception.class}, dontRollbackOn = {})
    public void test7(@RequestBody CreateRequest1 requestBody){
        var customerToCreate = Customer.builder().name(requestBody.getCustomerName()).age(requestBody.getCustomerAge()).lastname(requestBody.getCustomerLastname()).build();
        this.entityManager.persist(customerToCreate);
        var orderToCreate = Order.builder().customer(customerToCreate).totalPrice(requestBody.getOrderPrice()).build();
        var addedOrders = customerToCreate.getOrders();
        if(addedOrders == null) addedOrders = new HashSet<Order>();
        addedOrders.add(orderToCreate);
        customerToCreate.setOrders(addedOrders);
        //this.entityManager.flush();
    }
    @PostMapping(path = "test8")
    @Transactional
    public void test8(@RequestBody UpdateRequest1 requestBody){
        var x1 = this.entityManager.find(Customer.class, requestBody.getCustomerId());
        x1.setName(requestBody.getCustomerName());
        x1.setAge(requestBody.getCustomerAge());
        x1.setLastname(requestBody.getCustomerLastname());
        x1.getOrders().forEach((currentOrder)->{
            currentOrder.setTotalPrice(requestBody.getOrderPrice());
        });
        this.entityManager.refresh(x1);
        this.entityManager.merge(x1);
        var x2 = x1;
        var x3 = this.entityManager.find(Customer.class, requestBody.getCustomerId());
        var x4 = this.entityManager.getReference(Customer.class, requestBody.getCustomerId());
    }

    @GetMapping(path = "test9")
    @Transactional
    public void test9(@RequestParam("query1") Integer query1){
        var x1 = this.entityManager.find(Customer.class, query1);
        this.entityManager.remove(x1);
        this.entityManager.detach(x1);
        this.entityManager.clear();
        boolean x2 = this.entityManager.contains(x1);
    }

    @PostMapping(path = "test10")
    public void test10(@RequestBody CreateRequest1 requestBody){
        EntityManager entityManager1 = this.entityManagerFactory.createEntityManager();
        EntityTransaction newTransaction1 = entityManager1.getTransaction();
        try{
            newTransaction1.begin();
            entityManager1.createQuery("INSERT INTO Customer C (C.name, C.age, C.lastname) VALUES (:customerName, :customerAge, :customerLastname)").setParameter("customerName", requestBody.getCustomerName()).setParameter("customerAge", requestBody.getCustomerAge()).setParameter("customerLastname", requestBody.getCustomerLastname()).executeUpdate();
            newTransaction1.commit();
        }catch (Exception ex){
            System.out.printf("Hata alındı: %s", ex.getMessage());
            if(newTransaction1.isActive()) newTransaction1.rollback();
        }finally {
            entityManager1.close();
        }
    }

    @PostMapping(path = "test11")
    public void test11(@RequestBody UpdateRequest1 requestBody){
        EntityManager entityManager1 = this.entityManagerFactory.createEntityManager();
        EntityTransaction newTransaction1 = entityManager1.getTransaction();
        try{
            newTransaction1.begin();
            entityManager1.createQuery("UPDATE Customer C SET C.name=:customerName, C.age=:customerAge, C.lastname=:customerLastname WHERE C.customerId=:customerId").setParameter("customerId", requestBody.getCustomerId()).setParameter("customerName", requestBody.getCustomerName()).setParameter("customerAge", requestBody.getCustomerAge()).setParameter("customerLastname", requestBody.getCustomerLastname()).executeUpdate();
            newTransaction1.commit();
        }catch (Exception ex){
            System.out.printf("Hata alındı: %s", ex.getMessage());
            if(newTransaction1.isActive()) newTransaction1.rollback();
        }finally {
            entityManager1.close();
        }
    }
    @GetMapping(path = "test12")
    public void test12(@RequestParam("query1") Integer query1){
        EntityManager entityManager1 = this.entityManagerFactory.createEntityManager();
        EntityTransaction newTransaction1 = entityManager1.getTransaction();
        try{
            newTransaction1.begin();
            entityManager1.createQuery("DELETE FROM Customer C where C.customerId=:customerId").setParameter("customerId", query1).executeUpdate();
            newTransaction1.commit();
        }catch (Exception ex){
            System.out.printf("Hata alındı: %s", ex.getMessage());
            if(newTransaction1.isActive()) newTransaction1.rollback();
        }finally {
            entityManager1.close();
        }
    }

    @GetMapping(path = "test13")
    public void test13(){
        EntityManager entityManager1 = this.entityManagerFactory.createEntityManager();
        EntityTransaction newTransaction1 = entityManager1.getTransaction();
        try{
            List<Customer> x1 = entityManager1.createQuery("SELECT C FROM Customer C").getResultList();
            Object x2 = entityManager1.createQuery("SELECT C FROM Customer C WHERE C.customerId=9").getSingleResult();
            Object x3 = entityManager1.createQuery("SELECT C FROM Customer C FULL JOIN C.orders O ON C.customerId=O.customer.customerId WHERE C.customerId=:customerId ORDER BY C.customerId").setParameter("customerId", 9).getSingleResult();
            List<Customer> x4 = entityManager1.createQuery("SELECT C FROM Customer C FULL JOIN C.orders O ON C.customerId=O.customer.customerId ORDER BY C.customerId LIMIT 5").getResultList();
            var x5 = "";
        }catch (Exception ex){
            System.out.printf("Hata alındı: %s", ex.getMessage());
            if(newTransaction1.isActive()) newTransaction1.rollback();
        }finally {
            entityManager1.close();
        }
    }
    @GetMapping(path="test14")
    public String test14() throws Exception{
        var x1 = CreateSingleCustomerRequest.builder().build();
        //this.objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        //this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String serializedObject1 = this.objectMapper.writeValueAsString(x1);
        CreateSingleCustomerRequest x2 = this.objectMapper.readValue(serializedObject1, CreateSingleCustomerRequest.class);
        return serializedObject1;
    }
    @GetMapping(path="test15")
    public ResponseEntity test15(HttpServletResponse response){
        Cookie cookie = new Cookie("cookie1", "value1");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(600); // 600 secondss
        response.addCookie(cookie);
        return ResponseEntity.ok().body("OKAY");
    }

}
