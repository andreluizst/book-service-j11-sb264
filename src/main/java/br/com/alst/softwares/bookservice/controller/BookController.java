package br.com.alst.softwares.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alst.softwares.bookservice.gateway.CambioProxy;
import br.com.alst.softwares.bookservice.gateway.response.Cambio;
import br.com.alst.softwares.bookservice.model.Book;
import br.com.alst.softwares.bookservice.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;

	@GetMapping("/{id}/{currency}")
	public Book getBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		var port = environment.getProperty("local.server.port");
		var book = repository.getById(id);
		if (book == null)
			throw new RuntimeException("Book not foud!");
		
		book.setCurrency(currency);
		
		/*
		//código utilizando RestTemplate
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		
		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
				Cambio.class, params);
		var cambio = response.getBody();
		*/
		
		var response = proxy.getCambio(book.getPrice().toString(), "USD", currency);
		book.setPrice(response.getConvertedValue());
		book.setEnvironment("Book port: " + port + " | Cambio port: " + response.getEnvironment());
		/*
		 * Por algum motivo desconhecido o quando o feign retorna uma classe os campos com valores decimais recebem nulo.
		 * mas quando retorna uma string o valor decimal está lá, depois é necessário usar o ObjectMapper para converter
		 * na classe Cambio ou criar uma classe como a CustomFeignClientConfig, atriuí-la na anotation @FeignClient e
		 * alterar o retorno para Cambio.
		 * OBS: Não houve problema usando o RestTemplate. No geral foi mais simples.
		 * */
//		ObjectMapper mapper = new ObjectMapper();
//		Cambio cambio = null;
//		try {
//			cambio = mapper.readValue(response, Cambio.class);
//			book.setPrice(cambio.getConvertedValue());
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(response);
//		System.out.println(cambio);

		
		return book;
	}
}
