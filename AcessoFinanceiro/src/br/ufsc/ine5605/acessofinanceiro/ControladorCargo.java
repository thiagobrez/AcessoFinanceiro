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
                incluiCargo();
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


    public Cargo incluiCargo() {
        this.telaCargo.mensagemNovoCargo();
        String nome = this.telaCargo.pedeNome();
        int codigo = verificaCodigoInserido();
        int tipoCargo = this.telaCargo.pedeTipoCargo();
        Cargo cargo = new Cargo(0, "", false, false);
        switch(tipoCargo){
            case 1:
                    cargo = criaCargoGerencial(nome, codigo);
                    break;
            case 2:
                    cargo = criaCargoComercial(nome, codigo);
                    break;
            case 3:
                    cargo = criaCargoEspecial(nome, codigo);
                    break;
            default:
                    this.telaCargo.exibeOpcaoInexistente();
                    incluiCargo();
        }
        System.out.println(cargo);
        return cargo;
    }

//    public void tipoDoCargo() {
//        int opcao = this.telaCargo.pedeOpcao();
//
//    }


    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu para deletar o cargo. Caso o usuario aperte 1: deleta o cargo.
     * Caso o usuario aperte 2: volta para o menu principal do cargo. Caso o
     * usuario aperte outra tecla qualquer: apresenta a mensagem de opcao
     * inexistente e exibe o menu de deletar um cargo novamente
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
        this.telaCargo.exibeCargo(cargo.getCodigo(), cargo.getNome(), cargo.ehGerencial(), cargo.temAcessoAoFinanceiro());
        this.telaCargo.exibeMenuConfirmacaoDeletarCargo();
        controlaMenuDeletarCargo(cargo);
    }

    private void deletaCargo(Cargo cargo) {
        if (cargo != null) {
            if (cargos.contains(cargo)) {
                cargos.remove(cargo);
                cargo = null;
                this.telaCargo.mensagemCargoDeletadoSucesso();
            }
        }
    }

    public int verificaCodigoInserido() {
        int codigo = this.telaCargo.pedeCodigo();
        for (Cargo cargoCadastrado : this.cargos) {
            if (cargoCadastrado.getCodigo() == codigo) {
                this.telaCargo.mensagemErroCodigoJaCadastrada();
                verificaCodigoInserido();
            }
        }
        return codigo;
    }
    
    public void editaCargo() {
        this.telaCargo.mensagemEditaCargo();
        Cargo cargo = pedeCargo();
        this.telaCargo.exibeMensagemCargoSelecionado();
        this.telaCargo.exibeCargo(cargo.getCodigo(), cargo.getNome(), cargo.ehGerencial(), cargo.temAcessoAoFinanceiro());
        menuEditaCargo(cargo);
    }
    
    public void menuEditaCargo(Cargo cargo) {
        this.telaCargo.exibeMenuEditaCargo();
        controlaMenuEditaCargo(cargo);
    }
    
    public void controlaMenuEditaCargo(Cargo cargo) {
        int opcao = this.telaCargo.pedeOpcao();
        
        switch (opcao) {
            case 1:
                String nome = this.telaCargo.pedeNome();
                cargo.setNome(nome);
                this.telaCargo.mensagemNomeEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 2:
                int codigo = this.telaCargo.pedeCodigo();
                cargo.setCodigo(codigo);
                this.telaCargo.mensagemCodigoEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 3: 
                boolean ehGerencial = this.telaCargo.pedeSeEhGerencial();
                cargo.setEhGerencial(ehGerencial);
                this.telaCargo.mensagemGerencialEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 4:
                boolean temAcessoAoFinanceiro = this.telaCargo.pedeTemAcessoAoFinanceiro();
                cargo.setTemAcessoAoFinanceiro(temAcessoAoFinanceiro);
                this.telaCargo.mensagemGerencialEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 5:
                exibeMenuCargo();
                break;
            default:
                this.telaCargo.opcaoInexistente();
                editaCargo();
                break;
        }
    }

    
    //não é mais necessario, cadastraCargo ja retorna cargo
    //@Override
    //public Cargo cadastraCargoParaFuncionario() {
    //    throw new UnsupportedOperationException("Not supported yet.");
    //}

    @Override
    public void listaCargos() {
        this.telaCargo.mensagemListaCargos();
        for (Cargo cargoCadastrado : cargos) {
            int codigo = cargoCadastrado.getCodigo();
            String nome = cargoCadastrado.getNome();
            boolean ehGerencial = cargoCadastrado.ehGerencial();
            boolean temAcessoAoFinanceiro = cargoCadastrado.temAcessoAoFinanceiro();
            this.telaCargo.exibeCargo(codigo, nome, ehGerencial, temAcessoAoFinanceiro);
        }
        exibeMenuCargo();
    }

	@Override
	public Cargo encontraCargoPorCodigo(int codigo) {
            for(Cargo cargoLista : this.cargos) {
                if(cargoLista.getCodigo() == codigo) return cargoLista;
            }
            return null;
	}


    public Cargo pedeCargo() {
        int codigo = this.telaCargo.pedeCodigo();
        Cargo cargo = encontraCargoPorCodigo(codigo);
        if (cargo == null) {
            this.telaCargo.mensagemCargoNaoEncontrado();
            cargo = pedeCargo();
        }
        return cargo;
    }

    public Cargo criaCargoGerencial(String nome, int codigo) {
        Cargo cargo = new Cargo(codigo, nome, true, true);
        this.cargos.add(cargo);
        return cargo;
    }

    private CargoHorarioComercial criaCargoComercial(String nome, int codigo) {
        CargoHorarioComercial cargo = new CargoHorarioComercial(codigo, nome);
        this.cargos.add(cargo);
        return cargo;
    }

    private CargoHorarioEspecial criaCargoEspecial(String nome, int codigo) {
        CargoHorarioEspecial cargo = new CargoHorarioEspecial(codigo, nome);
        this.cargos.add(cargo);
        return cargo;
    }

}
