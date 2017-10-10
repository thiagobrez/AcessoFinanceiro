/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class ControladorCargo implements IControladorCargo {

    private ArrayList<Cargo> cargos;
    private TelaCargo telaCargo;

    public ControladorCargo() {
        this.cargos = new ArrayList<>();
        this.telaCargo = new TelaCargo(this);
    }

    public void exibeMenuCargo() {
        telaCargo.exibeMenuCargo();
        controlaMenuCargo();
    }

    public void controlaMenuCargo() {

        int opcao = this.telaCargo.pedeOpcao();

        switch (opcao) {
            case 1:
//                incluiCargo();
                break;
            case 2:
                editaCargo();
                break;
            case 3:
                listaCargos();
                break;
            case 4:
                menuDeletarCargo();
                break;
            case 5:
                ControladorPrincipal.getInstance().exibeMenuPrincipal();
                break;
            default:
                this.telaCargo.opcaoInexistente();
                exibeMenuCargo();
                break;
        }
    }

    public void voltaMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }

    //COMENTAR ISSO SE PRECISAR TESTAR.
//    @Override
//    public Cargo encontraCargoPorCodigo(int codigo) {
//        Cargo cargo = new Cargo();
//        return cargo;
//    }

    //CONTINUAR DAQUI
   //    private void incluiCargo() {
//        this.telaCargo.mensagemNovoCargo();
//
//        String nome = this.telaCargo.pedeNome();
//        int codigo = verificaCodigoInserido();
//        this.telaCargo.pedeTipoCargo();
//        //switch pra ver se é gerencial, comercial e especial
//        boolean ehGerencial = this.telaCargo.pedeEhGerencial();
//        boolean temAcessoAoFinanceiro = this.telaCargo.pedeTemAcesso();
//        pedeHorario
//        
//    }
//    
//    public void tipoDoCargo() {
//        int opcao = this.telaCargo.pedeOpcao();
//        
//    }

    private void editaCargo() {
    }
    
    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu para deletar o cargo. Caso o usuario aperte 1: deleta o
     * cargo. Caso o usuario aperte 2: volta para o menu principal do
     * cargo. Caso o usuario aperte outra tecla qualquer: apresenta a
     * mensagem de opcao inexistente e exibe o menu de deletar um cargo
     * novamente
     *
     * @param cargo a ser deletado
     */
    private void controlaMenuDeletarCargo(Cargo cargo) {
        int opcao = this.telaCargo.pedeOpcao();
        switch (opcao) {
            case 1:
                deletaCargo(cargo);
                exibeMenuCargo();
                break;
            case 2:
                exibeMenuCargo();
                break;
            default:
                this.telaCargo.opcaoInexistente();
                controlaMenuDeletarCargo(cargo);
                break;
        }
    }
    
    private void menuDeletarCargo() {
        this.telaCargo.mensagemDeletaCargo();
        Cargo cargo = pedeCargo();
        this.telaCargo.exibeMensagemCargoSelecionado();
        this.telaCargo.exibeCargo(cargo.getCodigo(), cargo.getNome(), cargo.ehGerente(), cargo.temAcessoAoFinanceiro());
        this.telaCargo.exibeMenuConfirmacaoDeletarCargo();
        controlaMenuDeletarCargo(cargo);
        
        
    }
    
    private void deletaCargo(Cargo cargo) {
        if(cargo != null) {
            if (cargos.contains(cargo)) {
                cargos.remove(cargo);
                cargo = null;
                this.telaCargo.mensagemCargoDeletadoSucesso();
            }
        }
    }
    
    public int verificaCodigoInserido() {
        int codigo = this.telaCargo.pedeCodigo();
        for (Cargo cargoCadastrado : cargos) {
            if (cargoCadastrado.getCodigo() == codigo) {
                this.telaCargo.mensagemErroCodigoJaCadastrada();
            }
            verificaCodigoInserido();
        }
        return codigo; 
    }
    
    //COMENTAR ISSO SE PRECISAR TESTAR.
    @Override
    public Cargo cadastraCargoParaFuncionario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void listaCargos() {
        this.telaCargo.mensagemListaCargos();
        for (Cargo cargoCadastrado : cargos) {
            int codigo = cargoCadastrado.getCodigo();
            String nome = cargoCadastrado.getNome();
            boolean ehGerente = cargoCadastrado.ehGerente();
            boolean temAcessoAoFinanceiro = cargoCadastrado.temAcessoAoFinanceiro();
            this.telaCargo.exibeCargo(codigo, nome, ehGerente, temAcessoAoFinanceiro);
        }
        exibeMenuCargo();
    }

	@Override
	public Cargo encontraCargoPorCodigo(int codigo) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

    

    private Cargo pedeCargo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
