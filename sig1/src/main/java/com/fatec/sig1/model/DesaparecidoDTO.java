		
	package com.fatec.sig1.model;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public class DesaparecidoDTO {
	
 @NotBlank(message = "Nome do desaparecido é requerido")	
    private String nomeds;
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data do desaparecimento deve estar no formato dd/MM/YYYY")    	
	private String datadedst;
	@Pattern(regexp = "^(0?[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$", message = "A hora do desaparecimento deve estar no formato HH:mm:ss")    
	private String horadedst;
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data de nascimento deve estar no formato dd/MM/YYYY")    
    private String datadenascimento;
    @CPF
	private String cpfdods;
	@NotBlank(message = "Recompensa é requerido")	
    private String recompensadods;
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
    
    private List<String> imagens;
    
	public DesaparecidoDTO(String nomeds,
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
                List<String> imagens) {
		this.nomeds = nomeds;
		setDatadedst(datadedst);
		setHoradedst(horadedst);
		setDatadenascimento(datadenascimento);
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
			this.datadedst = datadedst;
}

public String getHoradedst() {
    return horadedst;
}

public void setHoradedst(String horadedst) {
			this.horadedst = horadedst;
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

public void setDatadecadastrods(String datadecadastrods) {
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

public List<String> getImagens() {
        return imagens;
    }
    
    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

	public Desaparecido retornaUmDesaparecido() {
		return new Desaparecido(nomeds,
				datadedst,
				horadedst,
				datadenascimento,
				cpfdods,
				recompensadods,
				alttxtfotoprincipal,
				descricaododst,
				doencadods,
				sexodods,
				cordepeledods,
				pesodods,
				tatuagemdods,
                cicatrizdods,
                imagens);
	}
}

	
