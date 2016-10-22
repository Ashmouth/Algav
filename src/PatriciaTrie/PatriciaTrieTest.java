package PatriciaTrie;
import java.util.Scanner;

public class PatriciaTrieTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PatriciaTrie pt = new PatriciaTrie();
        System.out.println("Patricia Trie Test\n");

        char ch;

        do {
            System.out.println("\nPatricia Trie Operations\n");
            System.out.println("1. inserer");
            System.out.println("2. chercher");
            int choice = scan.nextChar();

            switch (choice) {
                case 1 :
                    System.out.println("Entrée l'élément à inserer");
                    pt.insert( scan.nextLine(); );
                    break;

                case 2 :
                    System.out.println("Entrée le mot recherché");
                    System.out.println("Résultat : "+ pt.search( scan.nextLine(); ));
                    break;

                default :
                    System.out.println("Echec veuillez entrée un choix valide\n ");
                    break;
            }

            System.out.println("\nVoulez vous continuer ? (Entrée y ou n) \n");
            ch = scan.next().charAt(0);

        } while (ch == 'Y'|| ch == 'y');
    }
}