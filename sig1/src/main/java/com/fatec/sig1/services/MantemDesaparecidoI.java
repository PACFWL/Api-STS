package com.fatec.sig1.services;

import java.util.List;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.joda.time.DateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.client.ResourceAccessException;

import org.springframework.web.client.RestTemplate;

import com.fatec.sig1.model.Desaparecido;

import com.fatec.sig1.model.DesaparecidoRepository;

//import com.fatec.sig1.model.Endereco;

/**

 * A classe mantem cliente implementa o padrao Service. Servce eh um padrao que

 * basicamente encapsula o processo de obtencao de serviços(objetos). O Service

 * cria uma camada de abstracao neste processo. Ao inves da classe dependente

 * instanciar suas dependencias diretamente, eles são solicitados a partir de um

 * objeto centralizado que atua como localizador de serviços.

 * Marcamos beans com @Service para indicar que ele está mantendo a lógica de negócios. 

 * @author

 */

@Service

public class MantemDesaparecidoI implements MantemDesaparecido {

	Logger logger = LogManager.getLogger(this.getClass());	@Autowired

	DesaparecidoRepository repositoryDs;

	public List<Desaparecido> consultaTodosDs() {

		logger.info(">>>>>> servico consultaTodos chamado");

		return repositoryDs.findAll();

	}

	@Override

	public Optional<Desaparecido> consultaPorCpfdods(String cpfdods) {

		logger.info(">>>>>> servico consultaPorCpf chamado");

		return repositoryDs.findByCpfdods(cpfdods);

	}

	@Override

	public Optional<Desaparecido> consultaPorIdds(Long idds) {

		logger.info(">>>>>> servico consultaPorId chamado");

		return repositoryDs.findByIdds(idds);

	}

	@Override

	public Optional<Desaparecido> saveDs(Desaparecido desaparecido) {

		logger.info(">>>>>> servico save chamado ");

		desaparecido.setDatadecadastrods(new DateTime());

		/****/

		/**

        List<byte[]> images = new ArrayList<>();

		for (MultipartFile file : files) {

			images.add(file.getBytes());

		}

		desaparecido.setImagens(images);

		**/

		/****/

		//Endereco endereco = obtemEndereco(cliente.getCep());

		//desaparecido.setEndereco(endereco.getLogradouro());

		return Optional.ofNullable(repositoryDs.saveDs(desaparecido));

	}

	@Override

	public void delete(Long idds) {

		logger.info(">>>>>> servico delete por id chamado");

		repositoryDs.deleteByIdds(idds);

	}

	@Override

	public Optional<Desaparecido> atualizaDs(Long idds, Desaparecido desaparecido) {

		logger.info(">>>>>> 1.servico atualiza informações de desaparecido chamado");

		//Endereco endereco = obtemEndereco(cliente.getCep());

		Desaparecido desaparecidoModificado = new Desaparecido(desaparecido.getNomeCompletoDesaparecido(), desaparecido.getDataDeNascimento(), desaparecido.getCpfDoDesaparecido(),

				desaparecido.getRecompensa(), desaparecido.getUrlFotoPrincipal(), desaparecido.getAltTxtFotoPrincipal(), desaparecido.getDescricaoDesaparecimento(), desaparecido.getDoenca(), desaparecido.getSexo_desaparecido(), desaparecido.getCorDePele(), desaparecido.getDataDeDesaparecimento(), desaparecido.getHoraDeDesaparecimento());

		desaparecidoModificado.setIdds(idds);

		desaparecidoModificado.obtemDataAtual(new DateTime());

		/****/

		

		

		/****/

		//desaparecidoModificado.setEndereco(endereco.getLogradouro());

		logger.info(">>>>>> 2. servico atualiza informacoes de cliente cep valido para o id => "

				+ desaparecidoModificado.getIdds());

		return Optional.ofNullable(repositoryDs.saveDs(desaparecidoModificado));

	}

	/** public Endereco obtemEndereco(String cep) {

		RestTemplate template = new RestTemplate();

		String url = "https://viacep.com.br/ws/{cep}/json/";

		logger.info(">>>>>> servico consultaCep - " + cep);

		ResponseEntity<Endereco> resposta = null;

		try {

			resposta = template.getForEntity(url, Endereco.class, cep);

			return resposta.getBody();

		} catch (ResourceAccessException e) {

			logger.info(">>>>>> consulta CEP erro nao esperado ");

			return null;

		} catch (HttpClientErrorException e) {

			logger.info(">>>>>> consulta CEP inválido erro HttpClientErrorException =>" + e.getMessage());

			return null;

		}

	} 

    **/

}
