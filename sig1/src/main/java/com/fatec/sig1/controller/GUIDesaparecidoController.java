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

	MantemDesaparecido servicods;

	@GetMapping("/desaparecidos")

	public ModelAndView retornaFormDeConsultaTodosDs() {

		logger.info(">>>>>> controller consulta todos chamado" );

		ModelAndView modelAndViewds = new ModelAndView("consultarDesaparecido");

		modelAndViewds.addObject("desaparecidos", servicods.consultaTodosDs());

		return modelAndViewds;

	}

	@GetMapping("/desaparecido")

	public ModelAndView retornaFormDeCadastroDeDs(Desaparecido desaparecido) {

		ModelAndView mvds = new ModelAndView("cadastrarDesaparecido");

		mvds.addObject("desaparecido", desaparecido);

		return mvds;

	}

	@GetMapping("/desaparecidos/{cpfdods}") // diz ao metodo que ira responder a uma requisicao do tipo get

	public ModelAndView retornaFormParaEditarDs(@PathVariable("cpfdods") String cpfdods) {

		ModelAndView modelAndViewds = new ModelAndView("atualizarDesaparecido");

		modelAndViewds.addObject("desaparecido", servicods.consultaPorCpfdods(cpfdods).get()); // retorna um objeto do tipo cliente

		return modelAndViewds; // addObject adiciona objetos para view

	}

	@GetMapping("/desaparecidos/idds/{idds}")

	public ModelAndView excluirNoFormDeConsultaDs(@PathVariable("idds") Long idds) {

		servicods.delete(idds);

		logger.info(">>>>>> 1. servico de exclusao chamado para o id =>  " + idds);

		ModelAndView modelAndViewds = new ModelAndView("consultarDesaparecido");

		modelAndViewds.addObject("desaparecidos", servicods.consultaTodosDs());

		return modelAndViewds;

	}

	@PostMapping("/desaparecidos")

	public ModelAndView saveDs(@Valid Desaparecido desaparecido, BindingResult result) {

		ModelAndView modelAndViewds = new ModelAndView("consultarDesaparecido");

		if (result.hasErrors()) {

			modelAndViewds.setViewName("cadastrarDesaparecido");

		} else {

			if (servicods.saveDs(desaparecido).isPresent()) {

				logger.info(">>>>>> controller chamou adastrar e consulta todos");

				modelAndViewds.addObject("desaparecidos", servicods.consultaTodosDs());

			} else {

				logger.info(">>>>>> controller cadastrar com dados invalidos");

				modelAndViewds.setViewName("cadastrarDesaparecido");

				modelAndViewds.addObject("message", "Dados invalidos");

			}

		}

		return modelAndViewds;

	}

	/****/

	@PostMapping("/disappeared/new")

	public String saveNewDisappeared(@ModelAttribute("disappeared") @Valid DisappearedDTO disappearedDTO,

	                                 BindingResult result, Model model,

	                                 @RequestParam("files") MultipartFile[] files) throws IOException {

		if (result.hasErrors()) {

			return "newDisappeared";

		}

		Desaparecido desaparecido = new Desaparecido();

		desaparecido.setNomeds(disappearedDTO.getNomeds());

		desaparecido.setDatadedst(disappearedDTO.getDatadedst());

		List<byte[]> images = new ArrayList<>();

		for (MultipartFile file : files) {

			images.add(file.getBytes());

		}

		desaparecido.setImagens(images);

		keepDisappeared.save(desaparecido);

		return "redirect:/disappeared";

	}

	

	/****/

	

	

	@PostMapping("/desaparecidos/idds/{idds}")

	public ModelAndView atualizaDs(@PathVariable("idds") Long idds, @Valid Desaparecido desaparecido, BindingResult result) {

		ModelAndView modelAndViewds = new ModelAndView("consultarDesaparecido");

		logger.info(">>>>>> servico para atualizacao de dados chamado");

		if (result.hasErrors()) {

			logger.info(">>>>>> servico para atualizacao de dados com erro");

			desaparecido.setIdds(idds);

			return new ModelAndView("atualizarDesaparecido");

		} else {

			servicods.atualizaDs(idds, desaparecido);

			modelAndViewds.addObject("desaparecidos", servicods.consultaTodosDs());

		}

		return modelAndViewds;

	}

}
