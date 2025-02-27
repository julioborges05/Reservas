package br.com.fiap.reservas.infra.repository.mesa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MesaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false, name = "id_restaurante")
	private Long restauranteId;

	@Column(name = "numero_mesa")
	private Integer numeroMesa;

	public MesaPK() {}

	public MesaPK(Long restauranteId, Integer numeroMesa) {
		this.restauranteId = restauranteId;
		this.numeroMesa = numeroMesa;
	}

	public MesaPK(Integer numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public Long getRestauranteId() {
		return restauranteId;
	}

	public void setRestauranteId(Long restauranteId) {
		this.restauranteId = restauranteId;
	}

	public Integer getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(Integer numeroMesa) {
		this.numeroMesa = numeroMesa;
	}
}