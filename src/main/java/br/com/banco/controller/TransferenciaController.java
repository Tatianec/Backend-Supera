package br.com.banco.controller;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = "http://localhost:3000")
public class TransferenciaController {
    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscarTransferencias(
            @RequestParam(required = false) Long idConta,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(required = false) String nomeOperadorTransacao
    ) {
        List<Transferencia> transferencias = transferenciaService.buscarTransferencias(idConta, dataInicio, dataFim, nomeOperadorTransacao);
        BigDecimal saldoTotal = transferenciaService.calcularSaldoTotal();

        Map<String, Object> response = new HashMap<>();
        response.put("transferencias", transferencias);
        response.put("saldoTotal", saldoTotal);

        return ResponseEntity.ok(response);
    }
}