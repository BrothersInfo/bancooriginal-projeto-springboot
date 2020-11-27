package br.com.bancooriginal.bancooriginal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import br.com.bancooriginal.BancoOriginalApplication;
import br.com.bancooriginal.model.Cliente;

/**
 * 
 * @author Cosme Ribeiro da Silva
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BancoOriginalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BancoOriginalApplicationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	private String getRootUrl() {
		return "http://localhost:8080/bancooriginal/api/v1/clientes";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testListarTodosClientes() throws URISyntaxException {
		URI uri = new URI(getRootUrl());

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertNotNull(result.getBody());
	}

	@Test
	public void testObterClientePorCodigo() {
		Cliente cliente = restTemplate.getForObject(getRootUrl() + "/1", Cliente.class);
		assertNotNull(cliente);
	}

	@Test
	public void testSalvarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Cosme Ribeiro da Silva");
		cliente.setEmail("fredekel@hotmail.com");
		cliente.setEndereco("Rua: Caapora, 116 - Guarulhos-SP");

		ResponseEntity<Cliente> postResponse = restTemplate.postForEntity(getRootUrl(), cliente, Cliente.class);
		assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdateEmployee() {
		Cliente cliente = restTemplate.getForObject(getRootUrl() + "/1", Cliente.class);
		cliente.setEmail("alterado@hotmail.com");
		cliente.setEndereco("Rua: Alterado, 116 - Guarulhos-SP");

		restTemplate.put(getRootUrl() + "/1", cliente);

		Cliente clienteAtualizado = restTemplate.getForObject(getRootUrl() + "/1", Cliente.class);
		assertNotNull(clienteAtualizado);
	}

	@Test
	public void testDeleteEmployee() {
		restTemplate.delete(getRootUrl() + "/1");

		try {
			Cliente cliente = restTemplate.getForObject(getRootUrl() + "/1", Cliente.class);
			assertEquals(cliente, null);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
