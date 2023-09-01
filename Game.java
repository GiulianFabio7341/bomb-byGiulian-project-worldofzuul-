import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;


public class Game
{

    private int armazenamentonorth;

    private int armazenamentosouth;

    private int armazenamentoeast;

    private int armazenamentowest;
    private int aleatorio;
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    //Item mesa=new Item("Mesa para dois",10);
    //String descriçãoObjeto= mesa.getInformação();//

    /**
     * Create all the rooms and link their exits together.
     *
     *
     */


    private void createRooms()
    {
        Room estacionamento_a, estacionamento_b, sala_geologia, lab_fisica,fora_universidade;
        // create the rooms

        fora_universidade = new Room("Fora da universidade",new ArrayList<>());
        estacionamento_a = new Room("No estacionamento A",new ArrayList<>());
        estacionamento_b = new Room("No estacionamento B",new ArrayList<>());
        sala_geologia= new Room("Na sala de geologia",new ArrayList<>());
        lab_fisica= new Room("No laboratório de física",new ArrayList<>());
        Room parque=new Room("No parque",new ArrayList<>());
        Room cantina=new Room("Na cantina",new ArrayList<>());
        Room sala_artes=new Room("Na sala de artes",new ArrayList<>());

        // initialise room exits
        sala_geologia.setExit("back",fora_universidade);
        lab_fisica.setExit("east",parque);
        lab_fisica.setExit("back",fora_universidade);
        parque.setExit("back",lab_fisica);
        cantina.setExit("back",fora_universidade);
        cantina.setExit("west",sala_artes);
        sala_artes.setExit("back",cantina);
        fora_universidade.setExit("north",sala_geologia);
        fora_universidade.setExit("east", lab_fisica);
        fora_universidade.setExit("south",estacionamento_a);
        fora_universidade.setExit("west",cantina);
        estacionamento_a.setExit("back",fora_universidade);
        estacionamento_a.setExit("south",estacionamento_b);
        estacionamento_b.setExit("back",estacionamento_a);
fora_universidade.addItem(new Item("mesa",20));
        fora_universidade.addItem(new Item("bolsa",5));
        estacionamento_a.addItem(new Item("carro",50));
        estacionamento_a.addItem(new Item("Van",100));
        estacionamento_a.addItem(new Item("Van2",150));
        estacionamento_b.addItem(new Item("moto",10));


        currentRoom = fora_universidade;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        Random gerador=new Random();
        this.aleatorio=gerador.nextInt(7);

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Desarme a bomba na universidade!");
        System.out.println("Desarme a bomba na universidade is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        if (direction.equals("south")){
           armazenamentosouth++;

        }
        if (direction.equals("east")){
            armazenamentoeast++;

        }
        if (direction.equals("west")){
            armazenamentowest++;
        }
        if (direction.equals("north")&&aleatorio==0){
            System.out.println("Já é um grande passo...você achou a bomba, mas pra salvar a vida dos estudantes, digite:desarmar");
            Scanner a=new Scanner(System.in);
            String escolha=a.nextLine();
            if (escolha.equals("desarmar")){
                System.out.println("Parabéns, você desarmou a bomba e salvou a vida de vários estudantes!!! :)");
                exit(0);
            }
            else{
                System.out.println("Você não salvou a vida de vários estudantes :(");
                exit(0);
            }
        }
        if (direction.equals("south")&&aleatorio==1){
            System.out.println("Já é um grande passo...você achou a bomba, mas pra salvar a vida dos estudantes, digite:desarmar");
            Scanner a=new Scanner(System.in);
            String escolha=a.nextLine();
            if (escolha.equals("desarmar")){
                System.out.println("Parabéns, você desarmou a bomba e salvou a vida de vários estudantes!!! :)");
                exit(0);}
            else{
                System.out.println("Você não salvou a vida de vários estudantes :(");
                exit(0);
            }
        }
        if (direction.equals("west")&&aleatorio==2){
            System.out.println("Já é um grande passo...você achou a bomba, mas pra salvar a vida dos estudantes, digite:desarmar");
            Scanner a=new Scanner(System.in);
            String escolha=a.nextLine();
            if (escolha.equals("desarmar")){
                System.out.println("Parabéns, você desarmou a bomba e salvou a vida de vários estudantes!!! :)");
                exit(0);}
            else{
                System.out.println("Você não salvou a vida de vários estudantes :(");
                exit(0);
            }
        }
        if (direction.equals("east")&&aleatorio==3){
            System.out.println("Já é um grande passo...você achou a bomba, mas pra salvar a vida dos estudantes, digite:desarmar");
            Scanner a=new Scanner(System.in);
            String escolha=a.nextLine();
            if (escolha.equals("desarmar")){
                System.out.println("Parabéns, você desarmou a bomba e salvou a vida de vários estudantes!!! :)");
                exit(0);}
            else{
                System.out.println("Você não salvou a vida de vários estudantes :(");
                exit(0);
            }
        }
        if (direction.equals("south")&&aleatorio==4&&armazenamentosouth>1){
            System.out.println("Já é um grande passo...você achou a bomba, mas pra salvar a vida dos estudantes, digite:desarmar");
            Scanner a=new Scanner(System.in);
            String escolha=a.nextLine();
            if (escolha.equals("desarmar")){
                System.out.println("Parabéns, você desarmou a bomba e salvou a vida de vários estudantes!!! :)");
                exit(0);}
            else{
                System.out.println("Você não salvou a vida de vários estudantes :(");
                exit(0);
            }
        }
        if (direction.equals("east")&&aleatorio==5&&armazenamentoeast>1){
            System.out.println("Já é um grande passo...você achou a bomba, mas pra salvar a vida dos estudantes, digite:desarmar");
            Scanner a=new Scanner(System.in);
            String escolha=a.nextLine();
            if (escolha.equals("desarmar")){
                System.out.println("Parabéns, você desarmou a bomba e salvou a vida de vários estudantes!!! :)");
                exit(0);}
            else{
                System.out.println("Você não salvou a vida de vários estudantes :(");
                exit(0);
            }
        }
        if (direction.equals("west")&&aleatorio==6&&armazenamentowest>1){
            System.out.println("Já é um grande passo...você achou a bomba, mas pra salvar a vida dos estudantes, digite:desarmar");
            Scanner a=new Scanner(System.in);
            String escolha=a.nextLine();
            if (escolha.equals("desarmar")){
                System.out.println("Parabéns, você desarmou a bomba e salvou a vida de vários estudantes!!! :)");
                exit(0);}
            else{
                System.out.println("Você não salvou a vida de vários estudantes :(");
                exit(0);
            }
        }

        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}