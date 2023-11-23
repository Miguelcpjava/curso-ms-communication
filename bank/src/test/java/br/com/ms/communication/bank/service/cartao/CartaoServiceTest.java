package br.com.ms.communication.bank.service.cartao;

import br.com.ms.communication.bank.domain.Cartao;
import br.com.ms.communication.bank.gateway.repository.CartaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
 class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @Test
    @DisplayName("Testar se o cartão é valido")
    void deveOCartaoSerValido() {
        when(cartaoRepository.findCartaoValido(anyInt(), anyInt())).thenReturn(1);

        boolean resultado = cartaoService.isValido(123, 456);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Testar com sucesso se o saldo é suficiente")
    void testarSeSaldoSuficiente() {

        when(cartaoRepository.isSaldoSuficiente(anyInt(), anyInt(), any(BigDecimal.class))).thenReturn(1000);

        boolean resultado = cartaoService.isSaldoSuficiente(123, 456, BigDecimal.TEN);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deverá encontrar o cartao com sucesso")
    void testarComSucessoABuscaPeloCartao() {
        Cartao cartaoMock = Cartao.builder()
                .codigoSegurancaCartao(123)
                .nroCartao(65)
                .valorCredito(BigDecimal.valueOf(1000))
                .id(1L)
        .build();

        when(cartaoRepository.findCartao(anyInt(), anyInt())).thenReturn(cartaoMock);

        Cartao resultado = cartaoService.getCartao(123, 65);

        assertNotNull(resultado);
        assertEquals(cartaoMock, resultado);
        assertEquals(1L,resultado.getId());
    }

    @Test
    @DisplayName("Deverá atualizar o saldo")
    void testarAAtualizacaoSaldo() {

        assertDoesNotThrow(() -> cartaoService.atualizarSaldo(123, 456, BigDecimal.TEN));

        verify(cartaoRepository, times(1)).atualizarSaldo(eq(123), eq(456), any(BigDecimal.class));
    }
}

