package com.br.itau.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.itau.dto.ContaDTO;
import com.br.itau.exception.SaldoInsuficienteException;
import com.br.itau.model.Conta;
import com.br.itau.repository.ContaRepository;

@Service
public class ContaService {

	private ContaRepository contaRepository;

	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	public Conta criarConta(Conta conta) {
        conta.setId(this.gerarIdConta());
        return contaRepository.save(conta);
    }

    public ContaDTO toContaDTO(Conta conta) {
        return new ContaDTO(conta.getId(), conta.getSaldo());
    }
    
	public Conta depositar(Long id, BigDecimal valor) {
        Conta conta = this.buscarContaPorId(id);
        conta.setSaldo(conta.getSaldo().add(valor));
        return contaRepository.save(conta);
    }

    public Conta sacar(Long id, BigDecimal valor) {
        Conta conta = this.buscarContaPorId(id);
        BigDecimal novoSaldo = conta.getSaldo().subtract(valor);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
        	throw new SaldoInsuficienteException(null);
        }
        conta.setSaldo(novoSaldo);
        return contaRepository.save(conta);
    }

    private Conta buscarContaPorId(Long id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isEmpty()) {
            return null;
        }
        return conta.get();
    }

	private Long gerarIdConta() {
		return null;
		// Lógica para gerar o id da conta
	}
}
