package io.spring.cloud.sleuth.docs.service2;

//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.lang.invoke.MethodHandles;


@SpringBootApplication
@RestController
public class Application {

	private static Logger log = org.slf4j.LoggerFactory.getLogger(Application.class);

	@Autowired RestTemplate restTemplate;
	@Autowired Tracer tracer;


	@Value("${service3.address:localhost:8083}") String service3Address;
	@Value("${service4.address:localhost:8084}") String service4Address;

	@RequestMapping("/")
	public String frontPage() throws InterruptedException {
		log.info("Front Page");
		return "Front Page";
	}

	@RequestMapping("/startOfService2")
	public String service2Controller() throws InterruptedException {
		log.info("Hello from Service2. Calling Service3 and then Service4");
		String service3 = restTemplate.getForObject("http://" + service3Address + "/startOfService3", String.class);
		log.info("Got response from Service3 [{}]", service3);
		String service4 = restTemplate.getForObject("http://" + service4Address + "/startOfService4", String.class);
		log.info("Got response from Service4 [{}]", service4);
		return String.format("Hello from Service2. Calling Service3 [%s] and then Service4 [%s]", service3, service4);
	}



	@Bean
	RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(2000);
		clientHttpRequestFactory.setReadTimeout(3000);
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override public boolean hasError(ClientHttpResponse response)
					throws IOException {
				try {
					return super.hasError(response);
				} catch (Exception e) {
					return true;
				}
			}

			@Override public void handleError(ClientHttpResponse response)
					throws IOException {
				try {
					super.handleError(response);
				} catch (Exception e) {
					log.error("Exception [" + e.getMessage() + "] occurred while trying to send the request", e);
					throw e;
				}
			}
		});
		return restTemplate;
	}

	public static void main(String... args) {
		new SpringApplication(Application.class).run(args);
	}
}
