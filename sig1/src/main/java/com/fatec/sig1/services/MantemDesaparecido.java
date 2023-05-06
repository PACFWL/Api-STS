package com.fatec.sig1.services;

import java.util.List;

import java.util.Optional;

import com.fatec.sig1.model.Desaparecido;

public interface MantemDesaparecido {

	List<Desaparecido> consultaTodosDs();

	Optional<Desaparecido> consultaPorCpfdods(String cpfdods);

	Optional<Desaparecido> consultaPorIdds(Long idds);

	Optional<Desaparecido> saveDs(Desaparecido desaparecido);

	void delete(Long idds);

	Optional<Desaparecido> atualizaDs(Long idds, Desaparecido desaparecido);

	//Endereco obtemEndereco(String cep);

}
