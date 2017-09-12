package io.spring.cloud.sleuth.docs.service4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
@RestController
public class Application {

	//private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static Logger log = org.slf4j.LoggerFactory.getLogger(Application.class);

	@RequestMapping("/startOfService4")
	public String service4Controller() throws InterruptedException {
		//int sleep = 2000;
		log.info("Adding sleep in service4= "+ 2000 +"ms");
		log.debug("Adding sleep in service4= "+ 2000 +"ms");
		Thread.sleep(2000);
		return "Hello from Service4" ;
	}

//	public String accountMicroServiceHelper() throws InterruptedException {
//		int sleep = 2000;
//		log.info("Adding latency of = "+ sleep +"ms");
//		Thread.sleep(sleep);
//		log.info("Added latency of = "+ sleep +"ms");
//		log.info("Hello from Acme Financial's Account Microservice");
//		return "Hello from Acme Financial's Account Microservice";
//	}

	@RequestMapping("/")
	public String frontPage() throws InterruptedException {
		log.info("Front Page");
		return "Front Page";
	}

	public static void main(String... args) {
		new SpringApplication(Application.class).run(args);
	}
}












//package io.spring.cloud.sleuth.docs.service4;
//
//import java.lang.invoke.MethodHandles;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.sleuth.Tracer;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@SpringBootApplication
//@RestController
//public class Application {
//
//	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
//
//	@Autowired Tracer tracer;
//
//	@RequestMapping("/baz")
//	public String service4MethodInController() throws InterruptedException {
//		Thread.sleep(400);
//		log.info("Hello from service4");
//		log.info("Service4: Baggage for [foo] is [" + tracer.getCurrentSpan().getBaggageItem("foo") + "]");
//		return "Hello from service4";
//	}
//
//	public static void main(String... args) {
//		new SpringApplication(Application.class).run(args);
//	}
//}
