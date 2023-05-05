package com.fatec.sig1.services;

import java.util.List;
import java.util.Optional;
import com.fatec.sig1.model.Desaparecido;

public interface MantemDesaparecido {
	List<Desaparecido> consultaTodosDesaparecido();

	Optional<Desaparecido> consultaPorCpfDesaparecido(String cpfDoDesaparecido);

	Optional<Desaparecido> consultaPorIdDesaparecido(Long id_desaparecido);

	Optional<Desaparecido> saveDesaparecido(Desaparecido desaparecido);

	void delete(Long id_desaparecido);

	Optional<Desaparecido> atualizaDesaparecido(Long id_desaparecido, Desaparecido desaparecido);

	//Endereco obtemEndereco(String cep);
}
