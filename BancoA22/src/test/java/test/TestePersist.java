package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import me.bancoa22.CrudPessoa;
import me.bancoa22.CrudProduto;
import me.bancoa22.Endereco;
import me.bancoa22.ItemNota;
import static me.bancoa22.Main.close;
import static me.bancoa22.Main.setup;
import me.bancoa22.Pessoa;
import me.bancoa22.Produto;
import me.bancoa22.NotaFiscal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lucas
 */
public class TestePersist
{

    Pessoa pessoa;
    Produto produto;

    public TestePersist()
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
        setup();

        pessoa = new Pessoa("Fulano", "Sr", "estudante", 23, 'M');
        Endereco endereco = new Endereco("Rua teste", 1, "Guarapuava");

        pessoa.setEndereco(endereco);

        CrudPessoa.salvar(pessoa);

        produto = new Produto("Lapis", 2.00, "Faber Castel", new Date(System.currentTimeMillis()));

        ItemNota in = new ItemNota(produto, 5);

        NotaFiscal nf = new NotaFiscal(new Date(System.currentTimeMillis()));
        nf.addItemNota(in);

        CrudProduto.salvar(nf);

    }

    @After
    public void tearDown()
    {
        close();
    }

    @Test
    public void testSave()
    {
        assertNotEquals(pessoa.getId(), -1);
    }

    @Test
    public void testConsultaNome()
    {
        Pessoa p1 = CrudPessoa.getByNome("Fulano");
        assertNotNull(p1);
        assertNotNull(p1.getEndereco().getCidade());
    }

    @Test
    public void testConsultaId()
    {
        assertNotNull(CrudPessoa.getByID(1L));
    }

    @Test
    public void testConsultaParameter()
    {
        assertNotNull(CrudPessoa.getByParameter(pessoa));
    }

    @Test
    public void testProcuraCidade()
    {
        assertNotNull(CrudPessoa.getByCidade("Guarapuava"));
    }

    @Test
    public void testMediaCidade()
    {
        assertNotNull(CrudPessoa.getMediaCidade());
    }

    @Test
    public void testDeletePessoa()
    {
        long id = pessoa.getId();

        assertNotNull(pessoa.getNome());
        CrudPessoa.delete(id);

        pessoa = CrudPessoa.getByID(id);
        assertNull(pessoa);
    }

    @Test
    public void testUpdatePessoa()
    {
        assertEquals(pessoa.getNome(), "Fulano");

        pessoa.setNome("Cicrano");
        CrudPessoa.update(pessoa);

        produto = CrudProduto.getProduto(pessoa.getId());
        assertEquals(pessoa.getNome(), "Cicrano");
    }
    /*
     @Test
     public void testProdutoVencido()
     {
     assertNotNull(CrudProduto.getVencidos());
     }
     */

    @Test
    public void testSaveProduto()
    {
        assertNotEquals(produto.getCodigoBarra(), -1);
    }

    @Test
    public void testDeleteProduto()
    {
        long id = produto.getCodigoBarra();

        assertNotNull(produto.getNome());
        CrudProduto.delete(id);

        produto = CrudProduto.getProduto(id);
        assertNull(produto);
    }

    @Test
    public void testUpdateProduto()
    {
        assertEquals(produto.getNome(), "Lapis");

        produto.setNome("Borracha");
        CrudProduto.update(produto);

        produto = CrudProduto.getProduto(produto.getCodigoBarra());
        assertEquals(produto.getNome(), "Borracha");
    }

    @Test
    public void testProdutoSoma()
    {
        assertEquals(CrudPessoa.getProdutoSoma().get(0)[0], "Lapis");
    }
}
