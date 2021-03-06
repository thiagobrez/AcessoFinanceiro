/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vladimir
 */
public class FuncionarioDAO {

    private HashMap<Integer, Funcionario> cacheFuncionarios = new HashMap<>();

    private final String nomeArquivo = "funcionarios.dat"; //essa extencao é qualquer coisa;

    public FuncionarioDAO() {
        load();
    }

    public void put(Funcionario funcionario) {
        cacheFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }

    public Funcionario get(Integer matricula) {
        return cacheFuncionarios.get(matricula);
    }

    public Collection<Funcionario> getList() {
        return cacheFuncionarios.values(); //retorna todos os valores, é como se retornasse o ArrayList;
    }

    public void remove(Funcionario funcionario) {
        cacheFuncionarios.remove(funcionario.getMatricula());
        persist();
    }

    private void persist() {

        try {
            FileOutputStream fOS = new FileOutputStream(nomeArquivo);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);

            oOS.writeObject(cacheFuncionarios);

            oOS.flush();
            fOS.flush();

            oOS.close();
            fOS.close();

        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void load() {

        try {
            FileInputStream fIS = new FileInputStream(nomeArquivo);
            ObjectInputStream oIS = new ObjectInputStream(fIS);

            cacheFuncionarios = (HashMap<Integer, Funcionario>) oIS.readObject();

            fIS.close();
            oIS.close();

        } catch (FileNotFoundException ex) {
            persist();
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
