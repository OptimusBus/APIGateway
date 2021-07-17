
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

// tag::code[]
@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// tag::route-locator[]
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String vehicleEndpoint = "http://" + System.getenv("VEHICLE_HOST") + ":8080";
		String passengerEndpoint = "http://" + System.getenv("PASSENGER_HOST") + ":8080";
		System.out.println(vehicleEndpoint);
		System.out.println(passengerEndpoint);
		RouteLocator rl = builder.routes()
			.route(p -> p
				.path("/api-v1/ob/vehicles/**")
				.filters(f -> f.rewritePath("/api-v1/ob/","/Vehicle-1.0/api-v1/"))
				.uri(vehicleEndpoint))
			.route(p -> p
				.path("/api-v1/ob/passengers/**")
				.filters(f -> f.rewritePath("/api-v1/ob/","/Passenger-1.0/api-v1/"))
				.uri(passengerEndpoint))
			.build();
		return rl;
	}
	// end::route-locator[]
}