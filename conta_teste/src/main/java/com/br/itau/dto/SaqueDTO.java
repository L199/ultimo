package com.br.itau.dto;

import java.math.BigDecimal;

public class SaqueDTO {
	private Long id;
	private BigDecimal novoSaldo;


	public SaqueDTO(Long id, BigDecimal novoSaldo) {
		this.id = id;
		this.novoSaldo = novoSaldo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getNovoSaldo() {
		return novoSaldo;
	}

	public void setNovoSaldo(BigDecimal novoSaldo) {
		this.novoSaldo = novoSaldo;
	}

}
