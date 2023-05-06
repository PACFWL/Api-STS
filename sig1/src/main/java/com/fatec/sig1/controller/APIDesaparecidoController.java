package com.fatec.sig1.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fatec.sig1.model.Desaparecido;

import com.fatec.sig1.model.DesaparecidoDTO;

//import com.fatec.sig1.model.Endereco;

import com.fatec.sig1.services.MantemDesaparecido;

@RestController

@RequestMapping("/api/v1/desaparecidos")

/*

 * Trata as requisicoes HTTP enviadas pelo usuario do servico

 */

public class APIDesaparecidoController {

	@Autowired

	MantemDesaparecidoI mantemDesaparecido;

	Desaparecido desaparecido;

	Logger logger = LogManager.getLogger(this.getClass());

	@CrossOrigin // desabilita o cors do spring security

	@PostMapping

	public ResponseEntity<Object> saveDs(@RequestBody @Valid DesaparecidoDTO desaparecidoDTO, BindingResult result) {

		desaparecido = new Desaparecido();

		if (result.hasErrors()) {

			logger.info(">>>>>> apicontroller validacao da entrada dados invalidos" + result.getFieldError());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");

		}

		if (mantemDesaparecido.consultaPorCpfdods(desaparecidoDTO.getCpfdods()).isPresent()) {

			logger.info(">>>>>> apicontroller consultaporcpf cpf ja cadastrado");

			return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado");

		}

		try {

			desaparecido.setDatadenascimento(desaparecidoDTO.getDatadenascimento());

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

		/**

        Optional<Endereco> endereco = Optional.ofNullable(mantemCliente.obtemEndereco(clienteDTO.getCep()));

		logger.info(">>>>>> apicontroller obtem endereco => " + clienteDTO.getCep());

		if (endereco.isEmpty()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CEP invalido");

		}

		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(mantemCliente.save(clienteDTO.retornaUmCliente()));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");

		} **/

	}

	@CrossOrigin // desabilita o cors do spring security

	@GetMapping

	public ResponseEntity<List<Desaparecido>> consultaTodosDs() {

		return ResponseEntity.status(HttpStatus.OK).body(mantemDesaparecido.consultaTodosDs());

	}

	@CrossOrigin // desabilita o cors do spring security

	@DeleteMapping("/{idds}")

	public ResponseEntity<Object> deletePorIdds(@PathVariable(value = "idds") Long idds) {

		Optional<Desaparecido> desaparecido = mantemDesaparecido.consultaPorIdds(idds);

		if (desaparecido.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");

		}

		mantemDesaparecido.delete(desaparecido.get().getIdds());

		return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido");

	}

	@CrossOrigin // desabilita o cors do spring security

	@PutMapping("/{idds}")

	public ResponseEntity<Object> atualizaDs(@PathVariable long idds, @RequestBody @Valid DesaparecidoDTO desaparecidoDTO,

			BindingResult result) {

		logger.info(">>>>>> api atualiza informações do desaparecido chamado");

		if (result.hasErrors()) {

			logger.info(">>>>>> apicontroller atualiza informações do desaparecido chamado dados invalidos");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");

		}

		Optional<Desaparecido> d = mantemDesaparecido.consultaPorIdds(idds);

		if (d.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");

		}

		/** Optional<Endereco> e = Optional.ofNullable(mantemCliente.obtemEndereco(clienteDTO.getCep()));

		if (e.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CEP não localizado.");

		} **/

		Optional<Desaparecido> desaparecido = mantemDesaparecido.atualizaDs(idds, desaparecidoDTO.retornaUmDesaparecido());

		return ResponseEntity.status(HttpStatus.OK).body(desaparecido.get());

	}

	@CrossOrigin // desabilita o cors do spring security

	@GetMapping("/{idds}")

	public ResponseEntity<Object> consultaPorIdds(@PathVariable Long idds) {

		logger.info(">>>>>> apicontroller consulta por id chamado");

		Optional<Desaparecido> desaparecido = mantemDesaparecido.consultaPorIdds(idds);

		if (desaparecido.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");

		}

		return ResponseEntity.status(HttpStatus.OK).body(desaparecido.get());

	}

}
