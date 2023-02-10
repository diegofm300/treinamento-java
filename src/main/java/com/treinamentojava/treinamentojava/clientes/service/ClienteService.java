package com.treinamentojava.treinamentojava.clientes.service;

import com.treinamentojava.treinamentojava.clientes.exception.ClienteNaoEncontradoException;
import com.treinamentojava.treinamentojava.clientes.exception.RestricaoCpfUnicoViolationException;
import com.treinamentojava.treinamentojava.clientes.model.Cliente;
import com.treinamentojava.treinamentojava.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public static final String MSG_RESTRICAO_CPF_VIOLADA = "O cpf %s já foi cadastrado anteriormente";
    public static final String MSG_CLIENTE_NAO_ENCONTRADO_POR_CPF = "Cliente não encontrado para o cpf %s";

    public Page<Cliente> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Cliente buscarPorCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(() -> new ClienteNaoEncontradoException(String.format(MSG_CLIENTE_NAO_ENCONTRADO_POR_CPF, cpf)));
    }

    public Cliente salvar(Cliente cliente) throws RestricaoCpfUnicoViolationException {
        try {
            return repository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new RestricaoCpfUnicoViolationException(String.format(MSG_RESTRICAO_CPF_VIOLADA, cliente.getCpf()));
        }
    }

    public void deletar(Cliente cliente){
        repository.delete(cliente);
    }
}
