package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String vehicleEndpoint = "http://msvehicleservice-challenge1.router.default.svc.cluster.local";
		String passengerEndpoint = "http://mspassengerservice-challenge1.router.default.svc.cluster.local";
		System.out.println(vehicleEndpoint);
		System.out.println(passengerEndpoint);
		RouteLocator rl = builder.routes()
			.route(p -> p
				.path("/optimusbus/vehicles/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSVehicle/Vehicle-1.0/"))
				.uri(vehicleEndpoint))
			.route(p -> p
				.path("/optimusbus/passengers/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSPassenger/Passenger-1.0/"))
				.uri(passengerEndpoint))
			.build();
		return rl;
	}
	
}
