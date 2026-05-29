package br.com.estoque.veiculos.controller;

import br.com.estoque.veiculos.service.RegistroNaoEncontradoException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratarNaoEncontrado(RegistroNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .orElse("Dados invalidos.");

        return ResponseEntity.badRequest().body(erro(mensagem));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> tratarIntegridade() {
        return ResponseEntity.badRequest().body(erro("Operacao viola uma regra do banco de dados."));
    }

    private Map<String, String> erro(String mensagem) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro", mensagem);
        return resposta;
    }
}
