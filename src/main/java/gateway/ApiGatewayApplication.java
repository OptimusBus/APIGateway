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
	
	public ApiGatewayApplication() {}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		
		String vehicleEndpoint = "http://vehicle-optimusbus.router.default.svc.cluster.local/";
		String passengerEndpoint = "http://passenger-optimusbus.router.default.svc.cluster.local/";
		String routeEndpoint = "http://route-optimusbus.router.default.svc.cluster.local/";
		String roadnetworkEndpoint = "http://roadnetwork-optimusbus.router.default.svc.cluster.local/";
		String securityEndpoint = "http://security-optimusbus.router.default.svc.cluster.local/";
		String bookingsEndpoint = "http://bookings-optimusbus.router.default.svc.cluster.local/";
		String clientEndpoint = "http://client-optimusbus.router.default.svc.cluster.local/";
		
		RouteLocator rl = builder.routes()
			.route(p -> p
				.path("/optimusbus/vehicles/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSVehicle/Vehicle-1.0/"))
				.uri(vehicleEndpoint))
			.route(p -> p
				.path("/optimusbus/passengers/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSPassenger/Passenger-1.0/"))
				.uri(passengerEndpoint))
			.route(p -> p
				.path("/optimusbus/route/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSRoute/Route-1.0/"))
				.uri(routeEndpoint))
			.route(p -> p
				.path("/optimusbus/roadnetwork/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSRoadNetwork/RoadNetwork-1.0/"))
				.uri(roadnetworkEndpoint))
			.route(p -> p
				.path("/optimusbus/security/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSSecurity/Security-1.0/"))
				.uri(securityEndpoint))
			.route(p -> p
				.path("/optimusbus/bookings/**")
				.filters(f -> f.rewritePath("/optimusbus/","/MSBookings/Bookings-1.0/"))
				.uri(bookingsEndpoint))
			.route(p -> p
				.path("/optimusbus/VehicleClient/**")
				.filters(f -> f.rewritePath("/optimusbus/VehicleClient","/VehicleClient/"))
				.uri(clientEndpoint))
			.build();
		return rl;
	}
	
}
