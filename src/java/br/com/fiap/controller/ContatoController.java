/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fiap.controller;


import br.com.fiap.bean.Contato;
import br.com.fiap.rest.AgendaRestClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author fsantiago
 */
@ManagedBean(name = "contatoC")
@SessionScoped
public class ContatoController {

    private Contato contato;
    private DataModel listaContatos;

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public DataModel getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(DataModel listaContatos) {
        this.listaContatos = listaContatos;
    }

    public DataModel getListarContatos() {
        
        AgendaRestClient ar = new AgendaRestClient();
        
        Contato[] contatos = ar.listar();
        List<Contato> contact = new ArrayList<Contato>();
        contact.addAll(Arrays.asList(contatos));
        listaContatos = new ListDataModel(contact);

        return listaContatos;
    }

    public Contato getContato(Contato contato) {
        AgendaRestClient ar = new AgendaRestClient();
        Contato c;
        c = ar.consultar(contato.getEmail());
        return c;
    }

    public String excluirContato() {
        Contato contatoTemp = (Contato) (listaContatos.getRowData());
        AgendaRestClient ar = new AgendaRestClient();
        ar.excluir(contatoTemp.getEmail());
        return "contato";
    }

    public void adicionarContato(ActionEvent actionEvent) {
        AgendaRestClient ar = new AgendaRestClient();
        ar.inserir(contato);
    }

    public void prepararAdicionarContato(ActionEvent actionEvent) {
        contato = new Contato();
    }

    public void prepararAlterarContato(ActionEvent actionEvent) {
        contato = (Contato) (listaContatos.getRowData());
    }

    public void alterarContato(ActionEvent actionEvent) {
        AgendaRestClient ar = new AgendaRestClient();
      
    }
}