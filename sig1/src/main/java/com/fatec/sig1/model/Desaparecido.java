package com.fatec.sig1.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import org.joda.time.format.DateTimeFormatter;

//import org.joda.time.format.DateTimeFormatter;

@Entity
public class Desaparecido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_desaparecido;
	@NotBlank(message = "Nome do desaparecido é requerido")	
    private String nomeCompletoDesaparecido;
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data do desaparecimento deve estar no formato dd/MM/YYYY")    	
	private String dataDeDesaparecimento;
	@Pattern(regexp = "^(0?[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$", message = "A hora do desaparecimento deve estar no formato HH:mm:ss")    
	private String horaDeDesaparecimento;
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data de nascimento deve estar no formato dd/MM/YYYY")    
    private String dataDeNascimento;
    private String dataDeCadastro;
    @CPF
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cpfDoDesaparecido;
	@NotBlank(message = "Recompensa é requerido")	
    private String recompensa;
    
    /** 
    Usar Blob - Possibilidade
    **/
	@NotBlank(message = "URL da Foto Principal é requerido")	
    private String urlFotoPrincipal;
	@NotBlank(message = "Alt Text da Foto Principal é requerido")	
    private String altTxtFotoPrincipal;
	@NotBlank(message = "Descrição do desaparecimento é requerido")	
    private String descricaoDesaparecimento;
	@NotBlank(message = "Doença é requerido")	
    private String doenca;
    @NotBlank(message = "Sexo M/F")
    private String sexo_desaparecido;
    @NotBlank(message = "Cor de Pele é requerido")
    private String corDePele;
    
	public Desaparecido(
				String nomeCompletoDesaparecido,
				String dataDeNascimento,
				String dataDeDesaparecimento,
				String horaDeDesaparecimento,
				String cpfDoDesaparecido,
				String recompensa,
				String urlFotoPrincipal,
				String altTxtFotoPrincipal,
				String descricaoDesaparecimento,
				String doenca,
				String sexo_desaparecido,
				String corDePele) {
		this.nomeCompletoDesaparecido = nomeCompletoDesaparecido;
		setDataDeNascimento(dataDeNascimento);
		setDataDeDesaparecimento(dataDeDesaparecimento);
		setHoraDeDesaparecimento(horaDeDesaparecimento);
		setDataDeCadastro(new DateTime());
		this.cpfDoDesaparecido = cpfDoDesaparecido;
        this.recompensa = recompensa;
		this.urlFotoPrincipal = urlFotoPrincipal;
		this.altTxtFotoPrincipal = altTxtFotoPrincipal;
		this.descricaoDesaparecimento = descricaoDesaparecimento;
		this.doenca = doenca;
		this.sexo_desaparecido = sexo_desaparecido;
		this.corDePele = corDePele;
	}
	
	public Desaparecido()
	{
}

	public Long getId_desaparecido() {
		return id_desaparecido;
	}

	public void setId_desaparecido(Long id_desaparecido) {
		this.id_desaparecido = id_desaparecido;
	}

	public String getNomeCompletoDesaparecido() {
		return nomeCompletoDesaparecido;
	}

	public void setNomeCompletoDesaparecido(String nomeCompletoDesaparecido) {
		this.nomeCompletoDesaparecido = nomeCompletoDesaparecido;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		if (validaData(dataDeNascimento) == true) {
			this.dataDeNascimento = dataDeNascimento;
		} else {
			throw new IllegalArgumentException("Data invalida");
			}
		}
		
	public String getCpfDoDesaparecido() {
		return cpfDoDesaparecido;
	}

	public void setCpfDoDesaparecido(String cpfDoDesaparecido) {
		this.cpfDoDesaparecido = cpfDoDesaparecido;
	}

	public String getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(String recompensa) {
		this.recompensa = recompensa;
	}

	public String getUrlFotoPrincipal() {
		return urlFotoPrincipal;
	}

	public void setUrlFotoPrincipal(String urlFotoPrincipal) {
		this.urlFotoPrincipal = urlFotoPrincipal;
	}

	public String getAltTxtFotoPrincipal() {
		return altTxtFotoPrincipal;
	}

	public void setAltTxtFotoPrincipal(String altTxtFotoPrincipal) {
		this.altTxtFotoPrincipal = altTxtFotoPrincipal;
	}

	public String getDescricaoDesaparecimento() {
		return descricaoDesaparecimento;
	}

	public void setDescricaoDesaparecimento(String descricaoDesaparecimento) {
		this.descricaoDesaparecimento = descricaoDesaparecimento;
	}

	public String getDoenca() {
		return doenca;
	}

	public void setDoenca(String doenca) {
		this.doenca = doenca;
	}

	public String getSexo_desaparecido() {
		return sexo_desaparecido;
	}

	public void setSexo_desaparecido(String sexo_desaparecido) {
		this.sexo_desaparecido = sexo_desaparecido;
	}

	public String getCorDePele() {
		return corDePele;
	}

	public void setCorDePele(String corDePele) {
		this.corDePele = corDePele;
	}
	
	public boolean validaData(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false); //
		try {
			df.parse(data); // data válida (exemplo 30 fev - 31 nov)
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public String obtemDataAtual(DateTime dataAtual) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		return dataAtual.toString(fmt);
	}

    public boolean validaHora(String hora) {
    // create a new SimpleDateFormat object with the specified time format
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    
    // set the lenient mode of the time format to false
    df.setLenient(false);
    
    try {
        // attempt to parse the input time string using the time format
        df.parse(hora);
        // if the parse is successful, the time is valid and return true
        return true;
    } catch (ParseException ex) {
        // if the parse fails, the time is invalid and return false
        return false;
    }
}


	public String getDataDeDesaparecimento() {
		return dataDeDesaparecimento;
	}

	public void setDataDeDesaparecimento(String dataDeDesaparecimento) {
		if (validaData(dataDeDesaparecimento) == true) {
			this.dataDeDesaparecimento = dataDeDesaparecimento;
		} else {
			throw new IllegalArgumentException("Data invalida");
			}
		}
	
	public String getHoraDeDesaparecimento() {
		return horaDeDesaparecimento;
	}

	public void setHoraDeDesaparecimento(String horaDeDesaparecimento) {
		if (validaHora(horaDeDesaparecimento) == true) {
			this.horaDeDesaparecimento = horaDeDesaparecimento;
		} else {
			throw new IllegalArgumentException("Hora invalida");
			}
		}

	public String getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(DateTime dataAtual) {
		this.dataDeCadastro = obtemDataAtual(dataAtual);
	}

	
}
