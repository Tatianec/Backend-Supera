package br.com.banco.repository;

import br.com.banco.model.Transferencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByContaId(Long contaId);
    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);
    List<Transferencia> findByContaIdAndDataTransferenciaBetween(Long contaId, LocalDateTime dataInicio, LocalDateTime dataFim);
}