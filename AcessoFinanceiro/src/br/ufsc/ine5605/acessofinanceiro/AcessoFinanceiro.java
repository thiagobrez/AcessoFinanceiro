/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro;

import java.util.Scanner;

/**
 *
 * @author thiagobrezinski
 */
public class AcessoFinanceiro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
        ControladorDataSistema controladorDataSistema = new ControladorDataSistema();
        controladorDataSistema.menuDataHoraSistema();

    }

}
