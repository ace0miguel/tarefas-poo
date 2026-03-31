package Entidades;

import EfeitosDeStatus.DanoConstante;
import EfeitosDeStatus.Efeito;
import Handlers.RNGHandler;
import Telas.Batalha;


public class Inimigo extends Entidade{

    private int dano;
    private int nextAcao;
    boolean usaEscudo;
    Efeito sangramento = new DanoConstante("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);
    //private ArrayList<Efeito> sangramentoAcumulado;

    public Inimigo(String nome, int vida, int dano){
        super(nome, vida);
        this.dano = dano;
        //this.sangramentoAcumulado = new ArrayList<Efeito>(); // isso aqui é uma lista dos danos de bleeding q o inimigo ainda tem pra tomar a cada turno, pq ele pode ter mais de 1 bleeding

    }


    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano + this.getDanoExtra());
    }

    public void atacarEfeito(Heroi alvo, Batalha batalha){ // falta generalizar, no momento ele só aplica sangramento
        alvo.receberDano(this.dano - 2 + this.getDanoExtra());
        Efeito e = sangramento.criaCopia();
        e.setAlvo(alvo);
        batalha.adicionarEfeito(e);
    }

    /*public void addSangramento(){
        int dur = Efeito.getDur();
    }

    public void sangrar(){

    }*/

    public void escolheAcao(){ // no momento simplificado para 0 = ataque 1 = ataque com efeito(menos dano mas aplica um status)
        nextAcao = RNGHandler.getGen().nextInt(2);
    }
    
    @Override
    public String status(){
        return (getEscudo() != 0) 
        ?""+this.getNome()+"("+getVida() +"/"+this.getVidaMax()+" de vida) ("+this.getEscudo()+" de escudo)" 
        : ""+this.getNome()+" ("+this.getVida()+"/"+this.getVidaMax()+" de vida)";
    }

    public void anunciarAtaque(){
        System.out.print(" "+this.getNome()+" ");
        System.out.println((nextAcao == 0) 
        ? "irá te atacar causando "+this.dano+" pontos de dano" 
        : "irá te atacar causando 1 ponto de dano e te deixar sangrando"); 
    }

    public int getNextAcao() {
        return nextAcao;
    }

    // @Override
    // public void passaTurno(){
    //     for(int i = getEfeitosAplicados().size() - 1; i >= 0; i--){
    //         Efeito efeito = getEfeitosAplicados().get(i);
    //         if (efeito.getDur() > 0){
    //             efeito.aplicar(this);
    //             System.out.println();
    //             System.out.println(efeito.getDesc());
    //             System.out.println();
    //             System.out.println("Efeito aplicado por mais "+ efeito.getDur()+ " turnos");
    //             System.out.println();
    //         } else removeEfeito(efeito);
    //     }
    //     this.resetarEscudo();
    //     if (usaEscudo == true) {
    //         this.ganharEscudo(3);
    //         usaEscudo = false;
    //     }
    // }

    public void setUsaEscudo(boolean usaEscudo) {
        this.usaEscudo = usaEscudo;
    }
}
