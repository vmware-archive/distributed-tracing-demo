//package io.spring.cloud.sleuth.docs.service3;
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
//	@RequestMapping("/bar")
//	public String service3MethodInController() throws InterruptedException {
//		Thread.sleep(300);
//		log.info("Hello from service3");
//		log.info("Service3: Baggage for [foo] is [" + tracer.getCurrentSpan().getBaggageItem("foo") + "]");
//		return "Hello from service3";
//	}
//
//	public static void main(String... args) {
//		new SpringApplication(Application.class).run(args);
//	}
//}
package io.spring.cloud.sleuth.docs.service3;

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


	@RequestMapping("/startOfService3")
	public String service3Controller() throws InterruptedException {
		//int sleep = 2000;
		log.info("Adding sleep in service3 = "+ 2000 +"ms");
		Thread.sleep(2000);
		log.info("Hello from Service3");
		return "Hello from Service3";
	}

	@RequestMapping("/")
	public String frontPage() throws InterruptedException {
		log.info("Front Page");
		return "Front Page";
	}

	public static void main(String... args) {
		new SpringApplication(Application.class).run(args);
	}
}
