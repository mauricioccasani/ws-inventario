package pe.com.ccasani.api;


import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.com.ccasani.api.controller.bean.ProductoRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String nullName = "abc";
		String name = Optional.ofNullable(nullName).orElse("john");

		log.info("Data: {}",name);
		log.info("==================================");
		List<ProductoRequest>p= this.list().stream().map(l->{
			if (l.getNombreProducto()==null|| l.getNombreProducto().isBlank()){
				l.setNombreProducto("data1");
			}

			return l;
		}).collect(Collectors.toList());
		log.info("List: {}",p);


	}

	private List<ProductoRequest>list(){
		return Arrays.asList(ProductoRequest.builder().nombreProducto("Papa").unidadTotal(20).precioUnidad(1.0).build(),
				ProductoRequest.builder().unidadTotal(1).precioUnidad(2.4).build(),
				ProductoRequest.builder().nombreProducto("Col").unidadTotal(7).precioUnidad(7.0).build());
	}
}
