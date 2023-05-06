
package com.fatec.sig1.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*

//import javax.persistence.Lob;
//import javax.persistence.ElementCollection;
import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import org.joda.time.format.DateTimeFormatter;

import org.joda.time.DateTime;

import org.joda.time.format.DateTimeFormat;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

// ...ds → ... desaparecido

// ...dst → ... desaparecimento

// (Opinião pessoal e creio válida não usar o isto "_" ) por causa findBy...

@Entity

public class Desaparecido {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idds;

	@NotBlank(message = "Nome do desaparecido é requerido")	
    private String nomeds;

	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data do desaparecimento deve estar no formato dd/MM/YYYY")    	
	private String datadedst;

	@Pattern(regexp = "^(0?[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$", message = "A hora do desaparecimento deve estar no formato HH:mm:ss")    
	private String horadedst;

	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data de nascimento deve estar no formato dd/MM/YYYY")    
    private String datadenascimento;

    private String datadecadastrods;

    @CPF
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cpfdods;

	@NotBlank(message = "Recompensa é requerido")	
    private String recompensadods;
 
    /** 
    Usar Blob - Possibilidade
    **/
	//@NotBlank(message = "URL da Foto Principal é requerido")	
    //private String urlfotoprincipal;

	@NotBlank(message = "Alt Text da Foto Principal é requerido")	
    private String alttxtfotoprincipal;

	@NotBlank(message = "Descrição do desaparecimento é requerido")	
    private String descricaododst;

	@NotBlank(message = "Doença é requerido")	
    private String doencadods;

    @NotBlank(message = "Sexo M/F")
    private String sexodods;

    @NotBlank(message = "Cor de Pele é requerido")
    private String cordepeledods;

    @NotBlank(message = "Peso é requerido")
    private double pesodods;

    @NotBlank(message = "Tatuagem é requerido")
    private boolean tatuagemdods;

    @NotBlank(message = "Cicatriz é requerido")
    private boolean cicatrizdods;

    @ElementCollection
	@Lob
	private List<byte[]> imagens;

	public Desaparecido(
				String nomeds,
				String datadedst,
				String horadedst,
				String datadenascimento,
				String cpfdods,
				String recompensadods,
				String alttxtfotoprincipal,
				String descricaododst,
				String doencadods,
				String sexodods,
				String cordepeledods,
				double pesodods,
				boolean tatuagemdods,
                boolean cicatrizdods,
                List<byte[]> imagens) {
		this.nomeds = nomeds;
		setDatadedst(datadedst);
		setHoradedst(horadedst);
		setDatadenascimento(datadenascimento);
		setDatadecadastrods(new DateTime());
		this.cpfdods = cpfdods;
        this.recompensadods = recompensadods;
		this.alttxtfotoprincipal = alttxtfotoprincipal;
		this.descricaododst = descricaododst;
   	 	this.doencadods = doencadods;
    	this.sexodods = sexodods;
    	this.cordepeledods = cordepeledods;
    	this.pesodods = pesodods;
   	 	this.tatuagemdods = tatuagemdods;
   	 	this.cicatrizdods = cicatrizdods;
   	 	this.imagens = imagens;
	}

	

	public Desaparecido()
	{
		this.imagens = new ArrayList<>();
}

public Long getIdds() {

    return idds;

}

public void setIdds(Long idds) {

    this.idds = idds;

}

public String getNomeds() {

    return nomeds;

}

public void setNomeds(String nomeds) {

    this.nomeds = nomeds;

}

public String getDatadedst() {

    return datadedst;

}

public void setDatadedst(String datadedst) {

    if (validaData(datadedst) == true) {

			this.datadedst = datadedst;

		} else {

			throw new IllegalArgumentException("Data de desaparecimento inválida");

			}

}

public String getHoradedst() {

    return horadedst;

}

public void setHoradedst(String horadedst) {

    if (validaHora(horadedst) == true) {

			this.horadedst = horadedst;

		} else {

			throw new IllegalArgumentException("Hora de desaparecimento inválida");

			}

}

public String getDatadenascimento() {

    return datadenascimento;

}

public void setDatadenascimento(String datadenascimento) {

    this.datadenascimento = datadenascimento;

}

public String getDatadecadastrods() {

    return datadecadastrods;

}

public void setDatadecadastrods(DateTime dataAtual) {

    this.datadecadastrods = obtemDataAtual(dataAtual);

}

public String getCpfdods() {

    return cpfdods;

}

public void setCpfdods(String cpfdods) {

    this.cpfdods = cpfdods;

}

public String getRecompensadods() {

    return recompensadods;

}

public void setRecompensadods(String recompensadods) {

    this.recompensadods = recompensadods;

}

public String getAlttxtfotoprincipal() {

    return alttxtfotoprincipal;

}

public void setAlttxtfotoprincipal(String alttxtfotoprincipal) {

    this.alttxtfotoprincipal = alttxtfotoprincipal;

}

public String getDescricaododst() {

    return descricaododst;

}

public void setDescricaododst(String descricaododst) {

    this.descricaododst = descricaododst;

}

public String getDoencadods() {

    return doencadods;

}

public void setDoencadods(String doencadods) {

    this.doencadods = doencadods;

}

public String getSexodods() {

    return sexodods;

}

public void setSexodods(String sexodods) {

    this.sexodods = sexodods;

}

public String getCordepeledods() {

    return cordepeledods;

}

public void setCordepeledods(String cordepeledods) {

    this.cordepeledods = cordepeledods;

}

public double getPesodods() {

    return pesodods;

}

public void setPesodods(double pesodods) {

    this.pesodods = pesodods;

}

public boolean isTatuagemdods() {

    return tatuagemdods;

}

public void setTatuagemdods(boolean tatuagemdods) {

    this.tatuagemdods = tatuagemdods;

}

public boolean isCicatrizdods() {

    return cicatrizdods;

}

public void setCicatrizdods(boolean cicatrizdods) {

    this.cicatrizdods = cicatrizdods;

}

public List<byte[]> getImagens() {

    return imagens;

}

public void setImagens(List<byte[]> imagens) {
    this.imagens = imagens;
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

}
