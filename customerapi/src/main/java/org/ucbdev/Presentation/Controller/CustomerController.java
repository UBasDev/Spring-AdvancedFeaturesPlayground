package org.ucbdev.Presentation.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.*;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletConnection;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.ucbdev.Core.Application.Exception.CustomException1;
import org.ucbdev.Core.Application.Request.CreateRequest1;
import org.ucbdev.Core.Application.Request.CreateSingleCustomerRequest;
import org.ucbdev.Core.Application.Request.TestRequest1;
import org.ucbdev.Core.Application.Request.UpdateRequest1;
import org.ucbdev.Core.Application.Response.CreateSingleCustomerResponse;
import org.ucbdev.Core.Domain.Entities.Customer;
import org.ucbdev.Core.Domain.Entities.Order;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
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
@Slf4j
public class CustomerController {
    @PersistenceContext
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ServletContext servletContext;

    public CustomerController(RestTemplate restTemplate, EntityManager entityManager, EntityManagerFactory entityManagerFactory, ObjectMapper objectMapper, ServletContext servletContext) {
        this.restTemplate = restTemplate;
        this.entityManager = entityManager;
        this.entityManagerFactory = entityManagerFactory;
        this.objectMapper = objectMapper;
        this.servletContext = servletContext;
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
    @PostMapping(path = "test10_1")
    @Transactional
    public void test10_1(@RequestBody CreateRequest1 requestBody){
        this.entityManager.persist(Customer.builder().name(requestBody.getCustomerName()).lastname(requestBody.getCustomerLastname()).age(requestBody.getCustomerAge()).build());
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


    @PostMapping(path = "test16")
    public ResponseEntity test16 (HttpServletResponse response1, HttpServletRequest request1)throws Exception{
        var x9 = request1.authenticate(response1);
        var x8 = request1.changeSessionId();
        var x1 = request1.getContextPath();
        var x2 = request1.getAuthType();
        Enumeration<String> x3 = request1.getHeaderNames();
        while(x3.hasMoreElements()){
            System.out.println(x3.nextElement());
        }
        //AsyncContext x26 = request1.getAsyncContext();
        var x27 = request1.getAttribute("attr1");
        Enumeration<String> x28 = request1.getAttributeNames();
        while(x28.hasMoreElements()){
            System.out.println(x28.nextElement());
        }
        var x29 = request1.getCharacterEncoding();
        var x30 = request1.getContentLength();
        var x31 = request1.getContentLengthLong();
        var x32 = request1.getContentType();
        var x33 = request1.getDispatcherType();
        HttpServletMapping x10 = request1.getHttpServletMapping();
        //var st1 = request1.getInputStream();
        /*
        byte[] buffer = new byte[512];
        StringBuilder sb = new StringBuilder();
        while (st1.read(buffer) != -1) {
            sb.append(new String(buffer));
        }
        System.out.println(sb.toString());
        */

        /*
        byte[] bytes = st1.readAllBytes();
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
        */
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(st1));
        String line;
        StringBuilder requestBody = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println(requestBody.toString());
        st1.close();
        reader.close();
        */

        var x35 = request1.getLocalAddr();
        var x36 = request1.getLocale();
        Enumeration<Locale> x37 = request1.getLocales();
        while(x37.hasMoreElements()){
            Locale current1 = x37.nextElement();
        }
        var x38 = request1.getLocalName();
        var x39 = request1.getLocalPort();


        var x4 = request1.getPathInfo();
        var x5 = request1.getQueryString();
        var x6 = request1.getRequestedSessionId();

        /*
        Collection<Part> x11 = request1.getParts();
        for (var currentPart1:x11) {
            var n2 = currentPart1.getContentType();
            var n1 = currentPart1.getName();
            var n3 = currentPart1.getHeaderNames();
            for(var y1: n3){
                System.out.println(y1.toString());
            }
            var n6 = currentPart1.getHeader("header1");
            var n7 = currentPart1.getHeaders("header1");
            var n4 = currentPart1.getSize();
            var n5 = currentPart1.getSubmittedFileName();

            if(n1.equals("file1")){
                var p1 = System.getProperty("user.dir");
                var p2 = Paths.get("");
                var p3 = new File("").getAbsoluteFile();
                var stream1 = currentPart1.getInputStream();
                byte[] fileBytes1 = stream1.readAllBytes();
                Path filePath1 = Paths.get(p1 + "/" + currentPart1.getName() + ".jpg");
                Files.write(filePath1, fileBytes1);
            }

            currentPart1.write("");

            currentPart1.delete();
        }
        */
        var x12 = request1.getPathTranslated();
        var x13 = request1.getRemoteUser();
        var x14 = request1.getRequestURL();
        var x15 = request1.getServletPath();
        var x16 = request1.getTrailerFields();
        var x17 = request1.getUserPrincipal();
        //x17.getName();
        ServletContext x25 = request1.getServletContext();
        var x40 = request1.getParameterMap();

        Enumeration<String> x41 = request1.getParameterNames();
        while(x41.hasMoreElements()){
            System.out.println(x41.nextElement());
        }
        var x42 = request1.getParameterValues("query1");
        var x43 = request1.getProtocol();
        var x44 = request1.getProtocolRequestId();
        //var x45 = request1.getReader();
        var x46 = request1.getRemoteAddr();
        var x47 = request1.getRemoteHost();
        var x48 = request1.getRemotePort();
        var x49 = request1.getRequestId();
        var x50 = request1.getScheme();
        var x51 = request1.getServerName();
        var x52 = request1.getServerPort();
        ServletConnection x53 = request1.getServletConnection();
        var x53_1 = x53.getProtocol();
        var x53_2 = x53.getConnectionId();
        var x53_3 = x53.isSecure();
        var x53_4 = x53.getProtocolConnectionId();
        var x7 = request1.isUserInRole("role1");
        var x18 = request1.isRequestedSessionIdFromCookie();


        var x19 = request1.isRequestedSessionIdFromURL();
        var x20 = request1.isRequestedSessionIdValid();
        var x21 = request1.isTrailerFieldsReady();
        //request1.login("username1", "password1");
        request1.logout();
        //request1.newPushBuilder().path("resources/logo1.jpeg").addHeader("Content-Type", "image/jpeg").push(); //HTTP2 server push requestleri göndermemizi sağlar. Böylece clientın mevcut sayfasındaki spesific resourceların clientın cacheine requestten önce gönderilmesini ve bu resourceların yüklenme hızının artırılmasını sağlarız.
        //request1.setCharacterEncoding(StandardCharsets.UTF_8.name());
        request1.setAttribute("attr1", "value1");
        request1.removeAttribute("attr2");
        var x22 = request1.isSecure();
        var x23 = request1.isAsyncSupported();
        var x24 = request1.isAsyncStarted();

        request1.startAsync(request1, response1);
        return ResponseEntity.ok().body("OKAY");
    }
    @GetMapping(path = "test17")
    public ResponseEntity test17(HttpServletResponse response1){
        Cookie cookie1 = new Cookie("cookie1", "value1");
        cookie1.setDomain("localhost");
        cookie1.setMaxAge(600); // 600 secondss
        response1.addCookie(cookie1);

        response1.addDateHeader("header1", 11);
        response1.addHeader("header2", "headerValue2");
        response1.addIntHeader("header3", 12);
        boolean x1 = response1.containsHeader("header1");
        String x2 = response1.encodeRedirectURL("test1");
        String x3 = response1.encodeURL("test2");
        String x4 = response1.getHeader("header1");
        Collection<String> x5 = response1.getHeaderNames();
        x5.forEach(System.out::println);
        Collection<String> x6 = response1.getHeaders("header1");
        x6.forEach(System.out::println);
        int x7 = response1.getStatus();
        /*
        Supplier<Map<String, String>> x8 = response1.getTrailerFields();
        x8.get().forEach((key, value) -> {
            System.out.printf("KEY: %s", key);
            System.out.printf("VALUE: %s", value);
        });
        */
        response1.getTrailerFields();
        //response1.sendError(400, "error1");
        //response1.sendRedirect("redirect1");
        response1.setDateHeader("date1", 13);
        response1.setHeader("header4", "headerValue4");
        response1.setIntHeader("header5", 14);
        response1.setStatus(HttpStatus.OK.value());
        //response1.setTrailerFields(); //???
        //response1.flushBuffer();
        var x8 = response1.getBufferSize();
        var x9 = response1.getCharacterEncoding();
        var x10 = response1.getContentType();
        var x11 = response1.getLocale();
        //ServletOutputStream x12 = response1.getOutputStream();
        //PrintWriter x13 =  response1.getWriter();
        //x13.append("");
        //x13.write("sa");
        var x14 = response1.isCommitted();
        response1.reset();
        response1.resetBuffer();
        response1.setBufferSize(500);
        response1.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response1.setContentLength(300);
        response1.setContentLengthLong(500);
        response1.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        response1.setLocale(Locale.ENGLISH);

        return ResponseEntity.ok().body("OKAY");
    }
    @GetMapping(path = "test18")
    public ResponseEntity tet18(){
        if(true) throw new CustomException1("test error1", HttpStatus.EXPECTATION_FAILED);
        return ResponseEntity.ok().body("Tet18 works!");
    }
    @GetMapping(path = "test19")
    public ResponseEntity<String> test19(){
        var sb1 = new StringBuilder();
        var sb2 = new StringBuilder();
        var x1 = sb1.append("text1", 2, 3);
        var x1_1 = sb1.appendCodePoint(30);
        sb1.append(" ");
        int x2 = sb1.compareTo(sb2);
        sb1.append("test2");
        var x3 = sb1.delete(3, 5);
        var x4 = sb1.deleteCharAt(2);
        int x5 = sb1.indexOf("te", 1);
        var x6 = sb1.insert(2, "text3");
        int x7 = sb1.lastIndexOf("te", 2);
        var x8 = sb1.repeat("cs", 4);
        var x9 = sb1.replace(2, 5, "newText2");
        var x10 = sb1.reverse();
        String x11 = sb1.toString();
        int x12 = sb1.capacity();
        char x13 = sb1.charAt(4);
        int x14 = sb1.codePointAt(4);
        int x15 = sb1.codePointBefore(5);
        int x16 = sb1.codePointCount(4, 8);
        sb1.codePoints().forEach(System.out::println);
        sb1.chars().forEach(System.out::println);
        sb1.ensureCapacity(20);
        var arr1 = new char[]{'a', 'b', 'c', 'd', 'e'};
        sb1.getChars(3, 5, arr1, 1);
        boolean x17 = sb1.isEmpty();
        int x18 = sb1.length();
        int x19 = sb1.offsetByCodePoints(4, 2);
        sb1.setCharAt(2, 'a');
        sb1.setLength(7);
        CharSequence x20 = sb1.subSequence(3, 6);
        String x21 = sb1.substring(3, 6);

        sb1.trimToSize();
        boolean x22 = sb1.equals(sb2);

        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(sb1.toString());
    }
    @GetMapping(path = "test20")
    public ResponseEntity test20() throws SQLException {
        final String DB_URL1 = "jdbc:postgresql://localhost:5432/customer";
        final String USER1 = "postgres";
        final String PASS1 = "admin";
        final String QUERY1 = "select * from customers";
        final Connection conn1 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        final Statement st1 = conn1.createStatement();
        final ResultSet rs1 = st1.executeQuery(QUERY1);
        try{
                st1.setQueryTimeout(500);
                while(rs1.next()){
                    System.out.printf("ID: %s - NAME: %s - LASTNAME: %s - AGE: %s \n", rs1.getInt("customer_id"), (rs1.getString("name")==null || rs1.getString("name").isEmpty() ? "YOKTUR" : rs1.getString("name")), rs1.getString("lastname"), rs1.getInt("age"));
                }
        }catch (SQLException ex){
            log.error(ex.getMessage());
            conn1.rollback();
        }finally {
            rs1.close();
            st1.close();
            conn1.close();
        }
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Test20 works!");
    }

    @GetMapping(path="test21")
    public ResponseEntity test21() throws SQLException {
        final String DB_URL1 = "jdbc:postgresql://localhost:5432/customer";
        final String USER1 = "postgres";
        final String PASS1 = "admin";
        final String COMMAND1 = "update customers set name='customer1_4', lastname='customerlast1_4' where customer_id=9";
        final Connection conn1 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        conn1.setAutoCommit(false);
        final Statement st1 = conn1.createStatement();
        try{
            st1.setQueryTimeout(500);
            var updatedRows = st1.executeUpdate(COMMAND1);
            conn1.commit();
            log.info("UPDATED ROW COUNT: {}", updatedRows);
        }catch (SQLException ex){
            log.error(ex.getMessage());
            conn1.rollback();
        }finally {
            st1.close();
            conn1.close();
        }
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Test21 works!");
    }
    @GetMapping(path="test22")
    public ResponseEntity test22() throws SQLException {
        final String DB_URL1 = "jdbc:postgresql://localhost:5432/customer";
        final String USER1 = "postgres";
        final String PASS1 = "admin";
        final String COMMAND1 = "update customers set name='customer1_5', lastname='customerlast1_5' where customer_id=9";
        final String COMMAND2 = "update customers set name='customer2_5', lastname='customerlast2_5' where customer_id=10";
        final Connection conn1 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        conn1.setAutoCommit (false);
        final Statement st1 = conn1.createStatement();
        try{
            st1.setQueryTimeout(500);
            st1.addBatch(COMMAND1);
            st1.addBatch(COMMAND2);
            var updatedRows = st1.executeBatch();
            conn1.commit();
            log.info("UPDATED ROW COUNT: {}", updatedRows);
        }catch (SQLException ex){
            log.error(ex.getMessage());
            conn1.rollback();
        }finally {
            st1.close();
            conn1.close();
        }
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Test22 works!");
    }

    @GetMapping(path = "test23")
    public ResponseEntity test23() throws SQLException{
        final String DB_URL1 = "jdbc:postgresql://localhost:5432/customer";
        final String USER1 = "postgres";
        final String PASS1 = "admin";
        final String COMMAND1 = "delete from customers where customer_id=21";
        final Connection conn1 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        conn1.setAutoCommit (false);
        final Statement st1 = conn1.createStatement();
        try{
            st1.setQueryTimeout(500);
            var updatedRows = st1.executeUpdate(COMMAND1);
            conn1.commit();
            log.info("DELETED ROW COUNT: {}", updatedRows);
        }catch (SQLException ex){
            log.error(ex.getMessage());
            conn1.rollback();
        }finally {
            st1.close();
            conn1.close();
        }
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Test23 works!");
    }
    @GetMapping(path = "test24")
    public ResponseEntity test24() throws SQLException{
        final String DB_URL1 = "jdbc:postgresql://localhost:5432/customer";
        final String USER1 = "postgres";
        final String PASS1 = "admin";
        final String COMMAND1 = "delete from customers where customer_id=20";
        final String COMMAND2 = "delete from customers where customer_id=19";
        final Connection conn1 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        conn1.setAutoCommit (false);
        final Statement st1 = conn1.createStatement();
        try{
            st1.setQueryTimeout(500);
            st1.addBatch(COMMAND1);
            st1.addBatch(COMMAND2);
            var updatedRows = st1.executeBatch();
            conn1.commit();
            log.info("DELETED ROW COUNT: {}", updatedRows);
        }catch (SQLException ex){
            log.error(ex.getMessage());
            conn1.rollback();
        }finally {
            st1.close();
            conn1.close();
        }
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Test24 works!");
    }
    @GetMapping(path = "test25")
    public ResponseEntity test25() throws SQLException{
        final String DB_URL1 = "jdbc:postgresql://localhost:5432/customer";
        final String USER1 = "postgres";
        final String PASS1 = "admin";
        final String COMMAND1 = "insert into customers (name, lastname, age) values ('customer11', 'customerlast11', 11)";
        final Connection conn1 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        conn1.setAutoCommit (false);
        final Statement st1 = conn1.createStatement();
        try{
            st1.setQueryTimeout(500);
            var updatedRows = st1.executeUpdate(COMMAND1);
            conn1.commit();
            log.info("INSERTED ROW COUNT: {}", updatedRows);
        }catch (SQLException ex){
            log.error(ex.getMessage());
            conn1.rollback();
        }finally {
            st1.close();
            conn1.close();
        }
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Test25 works!");
    }
    @GetMapping(path = "test26")
    public ResponseEntity test26() throws SQLException{
        final String DB_URL1 = "jdbc:postgresql://localhost:5432/customer";
        final String USER1 = "postgres";
        final String PASS1 = "admin";
        final String COMMAND1 = "insert into customers (name, lastname, age) values ('customer12', 'customerlast12', 12)";
        final String COMMAND2 = "insert into customers (name, lastname, age) values ('customer13', 'customerlast13', 13)";
        final Connection conn1 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        conn1.setAutoCommit (false);
        final Statement st1 = conn1.createStatement();
        try{
            st1.setQueryTimeout(500);
            st1.addBatch(COMMAND1);
            st1.addBatch(COMMAND2);
            var updatedRows = st1.executeBatch();
            conn1.commit();
            log.info("INSERTED ROW COUNT: {}", updatedRows);
        }catch (SQLException ex){
            log.error(ex.getMessage());
            conn1.rollback();
        }finally {
            st1.close();
            conn1.close();
        }
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Test26 works!");
    }
}
