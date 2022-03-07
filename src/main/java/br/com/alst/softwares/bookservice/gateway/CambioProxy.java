package br.com.alst.softwares.bookservice.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.alst.softwares.bookservice.config.CustomFeignClientConfig;
import br.com.alst.softwares.bookservice.gateway.response.Cambio;


@FeignClient(name = "cambio-service", url = "http://localhost:8000", configuration = CustomFeignClientConfig.class)
public interface CambioProxy {

	@GetMapping(value = "/cambio-service/{value}/{from}/{to}")
	public Cambio getCambio(@PathVariable("value") String value, @PathVariable("from") String from, @PathVariable("to") String to);
}
