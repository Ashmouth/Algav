package PatriciaTrie;
import java.util.Scanner;

public class PatriciaTrieTest {

    private static Scanner scan;

	public static void main(String[] args) {
        scan = new Scanner(System.in);
        PatriciaTrie pt = new PatriciaTrie();
        System.out.println("Patricia Trie Test\n");

        char ch;

        do {
            System.out.println("\nPatricia Trie Operations\n");
            System.out.println("1. inserer");
            System.out.println("2. chercher");
            System.out.println("3. supprimer");
            System.out.println("4. auto-test");
            String choice = scan.next();

            switch (choice) {
                case "1" :
                    System.out.println("Entrée l'élément à inserer");
                    pt.inserer( pt, scan.nextLine());
                    break;

                case "2" :
                    System.out.println("Entrée le mot recherché");
                    System.out.println("Résultat : "+ pt.rechercher(pt, scan.nextLine()));
                    break;
                    
                case "3" :
                    System.out.println("Entrée le mot à supprimé");
                    System.out.println("Résultat : "+ pt.supprimer(pt, scan.nextLine()));
                    break;
                    
                case "4" :
                    System.out.println("Lancement de l'auto test");
                    System.out.println("Résultat : TODO");
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
