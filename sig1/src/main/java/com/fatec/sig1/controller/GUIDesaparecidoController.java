package com.fatec.sig1.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.sig1.model.Desaparecido;
import com.fatec.sig1.services.MantemDesaparecido;
@Controller
@RequestMapping(path = "/sig")
public class GUIDesaparecidoController {
	Logger logger = LogManager.getLogger(GUIDesaparecidoController.class);
	@Autowired
	MantemDesaparecido servico_desaparecido;
	@GetMapping("/desaparecidos")
	public ModelAndView retornaFormDeConsultaTodosDesaparecidos() {
		logger.info(">>>>>> controller consulta todos chamado" );
		ModelAndView modelAndView_desaparecido = new ModelAndView("consultarDesaparecido");
		modelAndView_desaparecido.addObject("desaparecidos", servico_desaparecido.consultaTodosDesaparecido());
		return modelAndView_desaparecido;
	}
	@GetMapping("/desaparecido")
	public ModelAndView retornaFormDeCadastroDe(Desaparecido desaparecido) {
		ModelAndView mv_desaparecido = new ModelAndView("cadastrarDesaparecido");
		mv_desaparecido.addObject("desaparecido", desaparecido);
		return mv_desaparecido;
	}
	@GetMapping("/desaparecidos/{cpf}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarDesaparecido(@PathVariable("cpf") String cpfDoDesaparecido) {
		ModelAndView modelAndView_desaparecido = new ModelAndView("atualizarDesaparecido");
		modelAndView_desaparecido.addObject("desaparecido", servico_desaparecido.consultaPorCpf(cpfDoDesaparecido).get()); // retorna um objeto do tipo cliente
		return modelAndView_desaparecido; // addObject adiciona objetos para view
	}
	@GetMapping("/desaparecidos/id/{id_desaparecido}")
	public ModelAndView excluirNoFormDeConsultaCliente(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id =>  " + id);
		ModelAndView modelAndView_desaparecido = new ModelAndView("consultarCliente");
		modelAndView_desaparecido.addObject("clientes", servico.consultaTodos());
		return modelAndView;
	}
	@PostMapping("/clientes")
	public ModelAndView save(@Valid Cliente cliente, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarCliente");
		} else {
			if (servico.save(cliente).isPresent()) {
				logger.info(">>>>>> controller chamou adastrar e consulta todos");
				modelAndView.addObject("clientes", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("cadastrarCliente");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		return modelAndView;
	}
	@PostMapping("/clientes/id/{id}")
	public ModelAndView atualizaCliente(@PathVariable("id") Long id, @Valid Cliente cliente, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		logger.info(">>>>>> servico para atualizacao de dados chamado");
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro");
			cliente.setId(id);
			return new ModelAndView("atualizarCliente");
		} else {
			servico.atualiza(id,cliente);
			modelAndView.addObject("clientes", servico.consultaTodos());
		}

		return modelAndView;
	}
}

