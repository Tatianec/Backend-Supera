package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;

    public TransferenciaService(
            TransferenciaRepository transferenciaRepository,
            ContaRepository contaRepository
    ) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaRepository = contaRepository;
    }

    public List<Transferencia> buscarTransferencias(Long idConta, LocalDateTime dataInicio, LocalDateTime dataFim, String nomeOperadorTransacao) {
        if (idConta != null && dataInicio != null && dataFim != null && nomeOperadorTransacao != null) {
            return transferenciaRepository.findByContaIdAndDataTransferenciaBetween(idConta, dataInicio, dataFim);
        } else if (idConta != null) {
            return transferenciaRepository.findByContaId(idConta);
        } else if (dataInicio != null && dataFim != null) {
            return transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim);
        } else if (nomeOperadorTransacao != null) {
            return transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);
        } else {
            return transferenciaRepository.findAll();
        }
    }

    public BigDecimal calcularSaldoTotal() {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        BigDecimal saldoTotal = BigDecimal.ZERO;
        for (Transferencia transferencia : transferencias) {
            saldoTotal = saldoTotal.add(transferencia.getValor());
        }
        return saldoTotal;
    }
}