package ui;


import domain.Prietenie;
import domain.Utilizator;
import service.Service0;

import java.util.List;
import java.util.Scanner;

public class Consola {
    private Service0 service;
    private Scanner cin;
    public Consola(Service0 service){
        this.service =service;
        cin = new Scanner(System.in);
    }

    private void showmenu() {
        System.out.println("");
        System.out.println("--------------------------------------------");
        System.out.println("1. Adauga un utilizator.");
        System.out.println("2. Sterge un utilizator. ");
        System.out.println("3. Afiseaza toti utilizatorii.");
        System.out.println("4. Adauga o prietenie.");
        System.out.println("5. Sterge o prietenie.");
        System.out.println("6. Afiseaza toate prieteniile existente.");
        System.out.println("0. Exit");
        System.out.println("--------------------------------------------");
        System.out.println("");
    }

    public void runUI(){
        Memoria_existenta();
        cin = new Scanner(System.in);
        while(true){

            showmenu();
            System.out.println("Introduceti optiunea dorita: ");
            int opt = cin.nextInt();

            switch (opt){
                case 0 :
                    cin.close();
                    return;

                case 1 :
                    Utilizator u = citireutilizator();
//                    try {
//
//                        if (u.getFirstName() == null)
//                            throw new IllegalArgumentException("Emailul nu trebuie sa fie null!");
//                        else if (service.getUtilizatorByEmail(u.getEmail()) != null)
//                            throw new IllegalArgumentException("Exista deja un cont cu acest email!");
                        service.addUtilizator(u);
//                    } catch (Exception e) {
//                        System.err.println(e);
//
//                    }
//                    if (u != null) {
//                        System.err.println("Exista un alt utilizator cu acest ID");
//                        break;
//                    }


                    break;


                case 2:
                    String email = citireaEmail();
                    service.deleteUtilizator(email);
                    break;

                case 3:
                    Iterable<Utilizator> users = service.getAllUtilizatori();
                    users.forEach(System.out::println);
                    break;

                case 4:
                    String email1 = citireaEmail();
                    String email2 = citireaEmail();
                    service.createPrietenie(email1, email2);
                    break;

                case 5:
                    email1 = citireaEmail();
                    email2 = citireaEmail();
                    service.deletePrietenie(email1, email2);
                    break;

                case 6:
                    Iterable<Prietenie> itf = service.getAllPrietenii();
                    itf.forEach(System.out::println);
                    break;

                default:
                    System.out.println("Optiune incorecta!");

            }
        }
    }

    public Utilizator citireutilizator() {
        System.out.println("Introduceti prenumele utilizatorului: ");
        String firstname = cin.next();
        System.out.println("Introduceti numele utilizatorului: ");
        String lastname = cin.next();
        System.out.println("Introduceti email-ul utilizatorului: ");
        String email = cin.next();

        Utilizator utilizator = new Utilizator(firstname, lastname, email);

        return utilizator;
    }

    public String citireaEmail() {
        System.out.print("Introduceti Email-ul dorit: ");
        String email = cin.next();

        return email;
    }

    private void Memoria_existenta(){

        Utilizator utilizator1 = new Utilizator("Andrei", "Ardelean", "aclaudiuandrei586@yahoo.com");
        Utilizator utilizator2 = new Utilizator("Alex", "Reitler", "utilizator2@yahoo.com");
        Utilizator utilizator3 = new Utilizator("Horea", "Turc", "utilizator3@yahoo.com");
        Utilizator utilizator4 = new Utilizator("Rafael", "Borbei", "utilizator4@yahoo.com");
        Utilizator utilizator5 = new Utilizator("Sergiu", "Turoczi", "utilizator5@yahoo.com");
        Utilizator utilizator6 = new Utilizator("Bagdi", "Aron", "utilizator6@yahoo.com");


        this.service.addUtilizator(utilizator1);
        this.service.addUtilizator(utilizator2);
        this.service.addUtilizator(utilizator3);
        this.service.addUtilizator(utilizator4);
        this.service.addUtilizator(utilizator5);
        this.service.addUtilizator(utilizator6);


        this.service.createPrietenie(utilizator1.getEmail(), utilizator2.getEmail());
        this.service.createPrietenie(utilizator1.getEmail(), utilizator3.getEmail());
        this.service.createPrietenie(utilizator4.getEmail(), utilizator6.getEmail());
        this.service.createPrietenie(utilizator5.getEmail(), utilizator3.getEmail());
        this.service.createPrietenie(utilizator2.getEmail(), utilizator5.getEmail());
    }
}

