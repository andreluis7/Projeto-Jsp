package beans;

public class ProdutoBean {

	private Long codigo;
	private String nome;
	private Integer quantidade;
	private String valor;
	private Long categoria_id;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getCategoria_id() {
		return categoria_id;
	}
	
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}
	
}
