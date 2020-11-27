package br.com.bancooriginal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bancooriginal.exception.ClienteNaoEncontradoException;
import br.com.bancooriginal.model.Cliente;
import br.com.bancooriginal.repository.ClienteRepository;

/**
 * 
 * @author Cosme Ribeiro da Silva
 *
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public List<Cliente> obterTodosClientes() {
		return clienteRepository.findAll();
	}

	@GetMapping("/clientes/{codigo}")
	public ResponseEntity<Cliente> obterClientePorID(@PathVariable(value = "codigo") Long codigoCliente)
			throws ClienteNaoEncontradoException {
		Cliente cliente = clienteRepository.findById(codigoCliente).orElseThrow(
				() -> new ClienteNaoEncontradoException("Cliente não encontrado com o código: " + codigoCliente));
		return ResponseEntity.ok(cliente);
	}

	@PostMapping("/clientes")
	public Cliente salvarCliente(@Validated @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@PutMapping("/clientes/{codigo}")
	public ResponseEntity<Cliente> editarCliente(@PathVariable(value = "codigo") Long codigoCliente,
			@Validated @RequestBody Cliente clienteInfo) throws ClienteNaoEncontradoException {
		Cliente cliente = clienteRepository.findById(codigoCliente).orElseThrow(
				() -> new ClienteNaoEncontradoException("Cliente não encontrado com o código: " + codigoCliente));

		cliente.setEmail(clienteInfo.getEmail());
		cliente.setEndereco(clienteInfo.getEndereco());
		cliente.setNome(clienteInfo.getNome());
		cliente.setTelefone(clienteInfo.getTelefone());
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/clientes/{codigo}")
	public Map<String, Boolean> excluirCliente(@PathVariable(value = "codigo") Long codigoCliente)
			throws ClienteNaoEncontradoException {
		Cliente cliente = clienteRepository.findById(codigoCliente).orElseThrow(
				() -> new ClienteNaoEncontradoException("Cliente não encontrado com o código: " + codigoCliente));

		clienteRepository.delete(cliente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Cliente com o codigo: " + codigoCliente + " excluído com sucesso!", Boolean.TRUE);
		return response;
	}
}
