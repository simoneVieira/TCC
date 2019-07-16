/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
    private String valorParcela;
    private String matricula;
    private String banco;
    private String taxa;
    private Date dataInicio;
    private Date dataFim;
    private String numeroContrato;
    private String beneficio;
    private String valorSolicitado;
    private String valorLiberado;
    private String porcentagemComissao;
    private String valorComissao;
    private String financeira;
    private String formaContrato;
    private Cliente cliente;

   
    @OneToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "id")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    
    public String getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public String getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(String valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public String getValorLiberado() {
        return valorLiberado;
    }

    public void setValorLiberado(String valorLiberado) {
        this.valorLiberado = valorLiberado;
    }

    public String getPorcentagemComissao() {
        return porcentagemComissao;
    }

    public void setPorcentagemComissao(String porcentagemComissao) {
        this.porcentagemComissao = porcentagemComissao;
    }

    public String getValorComissao() {
        return valorComissao;
    }

    public void setValorComissao(String valorComissao) {
        this.valorComissao = valorComissao;
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
   
}
