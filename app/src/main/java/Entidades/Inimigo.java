package Entidades;

import EfeitosDeStatus.Efeito;
import Handlers.RNGHandler;
//import EfeitosDeStatus.Efeito;
//import Cartas.CartaAtaqueComEfeito;


public class Inimigo extends Entidade{

    private int dano;
    private int nextAcao;
    boolean usaEscudo;
    //private ArrayList<Efeito> sangramentoAcumulado;

    public Inimigo(String nome, int vida, int dano){
        super(nome, vida);
        this.dano = dano;
        //this.sangramentoAcumulado = new ArrayList<Efeito>(); // isso aqui é uma lista dos danos de bleeding q o inimigo ainda tem pra tomar a cada turno, pq ele pode ter mais de 1 bleeding

    }

//fazer add do efeito(add sangramento) e o sangrar (receber dANO e verificar duracao)

    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano);
    }

    /*public void addSangramento(){
        int dur = Efeito.getDur();
    }

    public void sangrar(){

    }*/

    public void escolheAcao(){ // no momento simplificado para 0 = ataque 1 = buff
        nextAcao = RNGHandler.getGen().nextInt(2);
    }
    
    @Override
    public String status(){
        return (getEscudo() != 0) 
        ?""+this.getNome()+"("+getVida() +"/"+this.getVidaMax()+" de vida) ("+this.getEscudo()+" de escudo)" 
        : ""+this.getNome()+" ("+this.getVida()+"/"+this.getVidaMax()+" de vida)";
    }

    public void anunciarAtaque(){
        System.out.print("o Inimigo ");
        System.out.println((nextAcao == 0) 
        ? "irá te atacar causando "+this.dano+" pontos de dano" 
        : "irá se fortalacer, recebendo dano extra"); 
    }

    public int getNextAcao() {
        return nextAcao;
    }

    @Override
    public void passaTurno(){
        for(int i = getEfeitosAplicados().size() - 1; i >= 0; i--){
            Efeito efeito = getEfeitosAplicados().get(i);
            if (efeito.getDur() > 0){
                efeito.aplicar(this);
                System.out.println();
                System.out.println(efeito.getDesc());
                System.out.println();
                System.out.println("Efeito aplicado por mais "+ efeito.getDur()+ " turnos");
                System.out.println();
            } else removeEfeito(efeito);
        }
        this.resetarEscudo();
        if (usaEscudo == true) {
            this.ganharEscudo(3);
            usaEscudo = false;
        }
    }

    public void setUsaEscudo(boolean usaEscudo) {
        this.usaEscudo = usaEscudo;
    }
}
