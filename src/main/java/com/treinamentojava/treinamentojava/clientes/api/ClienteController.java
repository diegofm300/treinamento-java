package com.treinamentojava.treinamentojava.clientes.api;

import com.treinamentojava.treinamentojava.clientes.api.dto.AtualizarClienteDto;
import com.treinamentojava.treinamentojava.clientes.exception.RestricaoCpfUnicoViolationException;
import com.treinamentojava.treinamentojava.clientes.api.dto.CadastroClienteDto;
import com.treinamentojava.treinamentojava.clientes.api.dto.ClientePorCpfDto;
import com.treinamentojava.treinamentojava.clientes.model.Cliente;
import com.treinamentojava.treinamentojava.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<CadastroClienteDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable).map(CadastroClienteDto::from));
    }

    @GetMapping("/porCpf/{cpf}")
    public ResponseEntity consultarPorCpf(@PathVariable String cpf) {

        return ResponseEntity.ok(ClientePorCpfDto.from(service.buscarPorCpf(cpf)));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity consultarCpf(@PathVariable String cpf) {

        return ResponseEntity.ok(ClientePorCpfDto.from(service.buscarPorCpf(cpf)));
    }


    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroClienteDto dto, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Cliente cliente = service.salvar(Cliente.from(dto));

            URI uri = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

            return ResponseEntity.created(uri).body(CadastroClienteDto.from(cliente));
        } catch (RestricaoCpfUnicoViolationException ve) {
            return ResponseEntity.internalServerError().body(ve.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity atualizar(@PathVariable String cpf, @RequestBody AtualizarClienteDto dto) {
        Cliente clienteAtual = service.atualizar(cpf, dto);

        return ResponseEntity.ok(CadastroClienteDto.from(clienteAtual));
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String cpf) {
        Cliente cliente = service.buscarPorCpf(cpf);
        service.deletar(cliente);
    }
}
