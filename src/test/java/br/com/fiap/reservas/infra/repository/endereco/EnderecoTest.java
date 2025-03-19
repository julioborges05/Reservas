package br.com.fiap.reservas.infra.repository.endereco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnderecoTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    void validaSalvarEndereco() {
        Endereco endereco = new Endereco("89670000", "logradouro","bairro","cidade","numero","complemento");
        Endereco savedEndereco = enderecoRepository.save(endereco);

        assertNotNull(savedEndereco.getId());
        assertEquals("89670000", savedEndereco.getCep());
        assertEquals("logradouro", savedEndereco.getLogradouro());
        assertEquals("bairro", savedEndereco.getBairro());
        assertEquals("cidade", savedEndereco.getCidade());
        assertEquals("numero", savedEndereco.getNumero());
        assertEquals("complemento", savedEndereco.getComplemento());
    }

    @Test
    void validaUpdateEndereco(){
        Endereco endereco = new Endereco("89670000", "logradouro","bairro","cidade","numero","complemento");
        Endereco savedEndereco = enderecoRepository.save(endereco);

        savedEndereco.setCep("89600000");
        savedEndereco.setLogradouro("novo logradouro");
        savedEndereco.setBairro("novo bairro");
        savedEndereco.setCidade("nova cidade");
        savedEndereco.setNumero("novo numero");
        savedEndereco.setComplemento("novo complemento");
        Endereco savedNewEndereco = enderecoRepository.save(savedEndereco);

        assertEquals("89600000", savedNewEndereco.getCep());
        assertEquals("novo logradouro", savedNewEndereco.getLogradouro());
        assertEquals("novo bairro", savedNewEndereco.getBairro());
        assertEquals("nova cidade", savedNewEndereco.getCidade());
        assertEquals("novo numero", savedNewEndereco.getNumero());
        assertEquals("novo complemento", savedNewEndereco.getComplemento());
    }
}
