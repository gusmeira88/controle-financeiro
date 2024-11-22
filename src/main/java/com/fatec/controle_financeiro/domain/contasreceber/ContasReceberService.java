package com.fatec.controle_financeiro.domain.contasreceber;
import com.fatec.controle_financeiro.entities.ContasReceber;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ContasReceberService {

    private final ContasReceberRepository contasReceberRepository;

    public ContasReceberService(ContasReceberRepository contasReceberRepository) {
        this.contasReceberRepository = contasReceberRepository;
    }

    @Transactional
    public ContasReceber create(ContasReceber receber) {
        if(receber.getEmissao().isAfter(receber.getVencimento())){
            throw new IllegalArgumentException("A data de vencimento não pode ser menor que a data de emissão");
        }
        BigDecimal valorZero = BigDecimal.ZERO;
        if(receber.getValor().compareTo(valorZero) <= 0 ){
            throw new IllegalArgumentException("O valor não pode ser 0 ou menor.");
        }
        return contasReceberRepository.save(receber);
    }

    public List<ContasReceber> findAll() {
        return contasReceberRepository.findAll();
    }

    public Optional<ContasReceber> findById(Long id) {
        return contasReceberRepository.findById(id);
    }

    @Transactional
    public ContasReceber update(Long id, ContasReceber conta) {
        if (!contasReceberRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta a receber não encontrada para o ID: " + id);
        }
        conta.setId(id);
        return contasReceberRepository.save(conta);
    }

    @Transactional
    public void delete(Long id) {
        if (!contasReceberRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta a receber não encontrada para o ID: " + id);
        }
        contasReceberRepository.deleteById(id);
    }
}