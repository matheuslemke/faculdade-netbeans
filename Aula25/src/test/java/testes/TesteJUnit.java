/*
 * To change this license header, choose License Headers item Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template item the editor.
 */
package testes;

import com.mycompany.aula25.*;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author matheus
 */
public class TesteJUnit
{

    public TesteJUnit()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        Main.setup();
    }

    @After
    public void tearDown()
    {
        Main.close();
    }

    public PessoaFisica criarPessoaFisica()
    {
        PessoaFisica pessoaFisica = new PessoaFisica(1, Sexo.MASCULINO,
                new Date(2000, 1, 1), "Estudante", "Fulano",
                new Endereco("Avenida A", "Guarapuava", "Brasil"));
        CRUDPessoa.create(pessoaFisica);
        return pessoaFisica;
    }

    public PessoaJuridica criarPessoaJuridica()
    {
        PessoaJuridica pessoaJuridica = new PessoaJuridica(new Date(1995, 1, 1),
                "Engenharia", "Dalba", new Endereco("Avenida B", "Guarapuava", "Brasil"));

        CRUDPessoa.create(pessoaJuridica);
        return pessoaJuridica;
    }

    public Produto criarProduto()
    {
        Produto produto = new Produto("Nescau", new BigDecimal(5), null);
        CRUDProduto.create(produto);
        return produto;
    }

    public NotaFiscal criarNota()
    {
        NotaFiscal nf = new NotaFiscal(null, new Date(System.currentTimeMillis()));

        CRUDNota.create(nf);

        return nf;
    }

    @Test
    public void testCriarPessoaFisica()
    {
        assertNotEquals(criarPessoaFisica().getRg(), -1);
    }

    @Test
    public void testReadPessoaFisica()
    {
        assertNotNull(CRUDPessoa.getFisicaByRG(criarPessoaFisica().getRg()));
    }

    @Test
    public void testUpdatePessoaFisica()
    {
        PessoaFisica pessoaFisica = criarPessoaFisica();

        pessoaFisica.setNome("Cicrano");

        CRUDPessoa.update(pessoaFisica);

        assertEquals(CRUDPessoa.getFisicaByRG(pessoaFisica.getRg()).getNome(), "Cicrano");
    }

    @Test
    public void testDeletePessoaFisica()
    {
        PessoaFisica pessoaFisica = criarPessoaFisica();

        long rg = pessoaFisica.getRg();
        CRUDPessoa.deleteFisica(pessoaFisica);
        assertNull(CRUDPessoa.getFisicaByRG(rg));
    }

    @Test
    public void testCriarPessoaJuridica()
    {
        assertNotEquals(criarPessoaJuridica().getCnpj(), -1);
    }

    @Test
    public void testReadPessoaJuridica()
    {
        assertNotNull(CRUDPessoa.getJuridicaByCNPJ(criarPessoaJuridica().getCnpj()));
    }

    @Test
    public void testUpdatePessoaJuridica()
    {
        PessoaJuridica pessoaJuridica = criarPessoaJuridica();

        pessoaJuridica.setNome("Unimed");

        CRUDPessoa.update(pessoaJuridica);

        assertEquals(CRUDPessoa.getJuridicaByCNPJ(pessoaJuridica.getCnpj()).getNome(), "Unimed");
    }

    @Test
    public void testDeletePessoaJuridica()
    {
        PessoaJuridica pj = criarPessoaJuridica();

        long cnpj = pj.getCnpj();
        CRUDPessoa.deleteJuridica(pj);

        assertNull(CRUDPessoa.getJuridicaByCNPJ(cnpj));

    }

    @Test
    public void testCreateProduto()
    {
        assertNotEquals(criarProduto().getCodigo_de_barra(), -1);
    }

    @Test
    public void testReadProduto()
    {
        assertNotNull(CRUDProduto.getByCodigo(criarProduto().getCodigo_de_barra()));
    }

    @Test
    public void testUpdateProduto()
    {
        Produto produto = criarProduto();

        produto.setNome("Nesquik");

        CRUDProduto.update(produto);

        assertEquals(CRUDProduto.getByCodigo(produto.getCodigo_de_barra()).getNome(), "Nesquik");
    }

    @Test
    public void testDeleteProduto()
    {
        Produto produto = criarProduto();

        long id = produto.getCodigo_de_barra();
        CRUDProduto.delete(produto);

        assertNull(CRUDProduto.getByCodigo(produto.getCodigo_de_barra()));
    }

    @Test
    public void testCreateNota()
    {
        assertNotEquals(criarNota().getId(), -1);
    }

    @Test
    public void testReadNota()
    {
        assertNotNull(CRUDNota.getNotaById(criarNota().getId()));
    }

    @Test
    public void testUpdateNota()
    {
        NotaFiscal nf = criarNota();
        Date data = new Date(2005, 1, 1);
        nf.setData_emissao(data);
        CRUDNota.update(nf);
        assertEquals(CRUDNota.getNotaById(nf.getId()).getData_emissao(), data);
    }

    @Test
    public void testDeleteNota()
    {
        NotaFiscal nf = criarNota();
        long id = nf.getId();

        CRUDNota.delete(nf);

        assertNull(CRUDNota.getNotaById(id));
    }

    @Test
    public void testUpdateNotaIncludeItem()
    {
        NotaFiscal nf = criarNota();
        ItemNota item = new ItemNota(nf, null, 3);
        nf.addItem(item);

        CRUDNota.update(nf);

        assertTrue(nf.getItens().size() == 1);
    }

    @Test
    public void testDeleteItemNota()
    {
        NotaFiscal nf = criarNota();

        ItemNota item = new ItemNota(nf, null, 3);
        nf.addItem(item);
        CRUDNota.update(nf);

        nf.getItens().remove(0);

        CRUDNota.update(nf);

        assertTrue(nf.getItens().isEmpty());
    }

    @Test
    public void testMaisCompradosPessoaFisica()
    {
        PessoaFisica pf = new PessoaFisica(3, Sexo.FEMININO, new Date(1999, 9, 9), "Aa", "Nome", null);

        CRUDPessoa.create(pf);

        Produto nescau = new Produto("Nescau", new BigDecimal(4), null);
        Produto leite = new Produto("Leite", new BigDecimal(2), null);
        Produto acucar = new Produto("Acucar", new BigDecimal(7), null);

        CRUDProduto.create(nescau);
        CRUDProduto.create(leite);
        CRUDProduto.create(acucar);

        NotaFiscal nf = new NotaFiscal(pf, new Date(System.currentTimeMillis()));

        ItemNota nescauIt = new ItemNota(nf, nescau, 2);
        ItemNota leiteIt = new ItemNota(nf, leite, 5);
        ItemNota acucarIt = new ItemNota(nf, acucar, 1);

        nf.addItem(nescauIt);
        nf.addItem(leiteIt);
        nf.addItem(acucarIt);

        CRUDNota.create(nf);

        assertEquals(CRUDPessoa.produtosMaisCompradosPessoaFisica(pf).size(), 3);
    }

    @Test
    public void testMaisVendidosPessoaJuridica()
    {
        PessoaJuridica pj = new PessoaJuridica(new Date(1999, 9, 9), "A", "Nome", new Endereco("B", "c", "D"));

        CRUDPessoa.create(pj);

        Produto nescau = new Produto("Nescau", new BigDecimal(4), pj);
        Produto leite = new Produto("Leite", new BigDecimal(2), pj);
        Produto acucar = new Produto("Acucar", new BigDecimal(7), pj);

        CRUDProduto.create(nescau);
        CRUDProduto.create(leite);
        CRUDProduto.create(acucar);

        NotaFiscal nf = new NotaFiscal(null, new Date(System.currentTimeMillis()));

        ItemNota nescauIt = new ItemNota(nf, nescau, 2);
        ItemNota leiteIt = new ItemNota(nf, leite, 5);
        ItemNota acucarIt = new ItemNota(nf, acucar, 1);

        nf.addItem(nescauIt);
        nf.addItem(leiteIt);
        nf.addItem(acucarIt);

        CRUDNota.create(nf);

        assertEquals(CRUDPessoa.produtosMaisVendidosPessoaJuridica(pj).size(), 3);
    }

    @Test
    public void testValorNota()
    {
        Produto nescau = new Produto("Nescau", new BigDecimal(4), null);
        Produto leite = new Produto("Leite", new BigDecimal(2), null);
        Produto acucar = new Produto("Acucar", new BigDecimal(7), null);

        CRUDProduto.create(nescau);
        CRUDProduto.create(leite);
        CRUDProduto.create(acucar);

        NotaFiscal nf = new NotaFiscal(null, new Date(System.currentTimeMillis()));

        ItemNota nescauIt = new ItemNota(nf, nescau, 2);
        ItemNota leiteIt = new ItemNota(nf, leite, 5);
        ItemNota acucarIt = new ItemNota(nf, acucar, 1);

        nf.addItem(nescauIt);
        nf.addItem(leiteIt);
        nf.addItem(acucarIt);

        CRUDNota.create(nf);

        assertEquals(CRUDNota.valorTotal(nf).intValue(), 25);
    }
}
