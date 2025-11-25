public class App {
    public static void main(String[] args) {

        ControleTeleSena controle = new ControleTeleSena();

        controle.iniciarVendas();
        controle.realizarSorteio();
        controle.mostrarResultado();
    }
}
