package br.com.ms.communication.bank.gateway.repository;

import br.com.ms.communication.bank.domain.Pagamento;
import org.springframework.data.repository.CrudRepository;

public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {
}
