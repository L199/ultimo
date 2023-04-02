package com.br.itau.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.itau.dto.ContaDTO;
import com.br.itau.dto.SaqueDTO;
import com.br.itau.exception.SaldoInsuficienteException;
import com.br.itau.model.Conta;
import com.br.itau.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	private ContaService contaService;

	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}

	@PostMapping
	public ResponseEntity<ContaDTO> criarConta(@RequestBody Conta conta) {
		Conta novaConta = contaService.criarConta(conta);
		ContaDTO responseDTO = contaService.toContaDTO(novaConta);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	 @PutMapping("/{id}/deposito")
	    public ResponseEntity<ContaDTO> depositar(@PathVariable Long id, @RequestBody Map<String, String> body) {
	        BigDecimal valor = new BigDecimal(body.get("valor"));
	        Conta contaAtualizada = contaService.depositar(id, valor);
	        ContaDTO responseDTO = contaService.toContaDTO(contaAtualizada);
	        return ResponseEntity.ok(responseDTO);
	    }

	 @PutMapping("/{id}/sacar")
	 public ResponseEntity<?> sacar(@PathVariable Long id, @RequestBody Map<String, String> body) {
	     BigDecimal valor = new BigDecimal(body.get("valor"));
	     try {
	         Conta contaAtualizada = contaService.sacar(id, valor);
	         SaqueDTO responseDTO = new SaqueDTO(contaAtualizada.getId(), contaAtualizada.getSaldo());
	         return ResponseEntity.ok(responseDTO);
	     } catch (SaldoInsuficienteException e) {
	         return ResponseEntity.badRequest().body("Saque não permitido - Saldo insuficiente");
	     }
	 }
}
