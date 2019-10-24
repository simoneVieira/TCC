/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static org.hibernate.engine.internal.Cascade.cascade;

/**
 *
 * @author SimoneBarbosa
 */
@Entity
public class Emprestimo {

    private Long id_Emprestimo;
    private String convenio;
    private String status;
    private String observacao;
    private String formaPagamento;
    private float valorParcela;
    private int matricula;
    private String banco;
    private float taxa;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int numeroContrato;
    private String beneficio;
    private Double valorSolicitado;
    private Double valorLiberado;
    private float porcentagemComissao;
    private float valorComissao;
    private String financeira;
    private String formaContrato;
    private int quantidadeParcela;
    private String gerarNotificacao;
    private Cliente cliente;
    private Usuario login;
    private Notificacao notificacao;
  

    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "id_Notificacao")
    public Notificacao getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    public String getGerarNotificacao() {
        return gerarNotificacao;
    }

    public void setGerarNotificacao(String gerarNotificacao) {
        this.gerarNotificacao = gerarNotificacao;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(int numeroContrato) {
        this.numeroContrato = numeroContrato;
    }
    public int getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(int quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public Double getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(Double valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public Double getValorLiberado() {
        return valorLiberado;
    }

    public void setValorLiberado(Double valorLiberado) {
        this.valorLiberado = valorLiberado;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }
    public float getPorcentagemComissao() {
        return porcentagemComissao;
    }

    public void setPorcentagemComissao(float porcentagemComissao) {
        this.porcentagemComissao = porcentagemComissao;
    }

    public float getValorComissao() {
        return valorComissao;
    }

    public void setValorComissao(float valorComissao) {
        this.valorComissao = valorComissao;
    }

    public Emprestimo() {

    }

    public float getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne
    @JoinColumn(name = "id_Login")
    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId_Emprestimo() {
        return id_Emprestimo;
    }

    public void setId_Emprestimo(Long id_Emprestimo) {
        this.id_Emprestimo = id_Emprestimo;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getFinanceira() {
        return financeira;
    }

    public void setFinanceira(String financeira) {
        this.financeira = financeira;
    }

    public String getFormaContrato() {
        return formaContrato;
    }

    public void setFormaContrato(String formaContrato) {
        this.formaContrato = formaContrato;
    }

    public float getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(float valorParcela) {
        this.valorParcela = valorParcela;
    }

    @Override
    public String toString() {
        return getFormaContrato();

    }

}
