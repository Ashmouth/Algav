package PatriciaTrie;
import java.util.Scanner;

public class PatriciaTrieTest {

    private static Scanner scan;
	private static boolean endsoft;

	public static void main(String[] args) {
        scan = new Scanner(System.in);
        PatriciaTrie pt = new PatriciaTrie("@");
        System.out.println("Patricia Trie Test\n");
        
		setEndsoft(false);
		
		do {
            System.out.println("\nPatricia Trie Operations\n");
            System.out.println("n. pour arreter");
            System.out.println("1. inserer");
            System.out.println("2. chercher");
            System.out.println("3. supprimer");
            System.out.println("4. auto-test");
            System.out.println("5. Question 1.3)");
            String choice = scan.next();

            switch (choice) {
                case "1" :
                    System.out.println("Entrée l'élément à inserer");
                    choice = scan.next();
                    System.out.println("Résultat : "+ pt.insert(pt, choice));
                    break;

                case "2" :
                    System.out.println("Entrée le mot recherché");
                    System.out.println("Résultat : "+ pt.search(pt, scan.next()));
                    break;

                case "3" :
                    System.out.println("Entrée le mot à supprimé");
                    System.out.println("Résultat : "+ pt.delete(pt, scan.nextLine()));
                    break;

                case "4" :
                    System.out.println("Lancement de l'auto-test simple");
                    pt.insert(pt, "arbre");
                    pt.insert(pt, "arc");
                    pt.insert(pt, "arbuste");
                    pt.printPtree(pt);
                    System.out.println("Résultat : " + pt.search(pt, "arbre"));
                    break;
                    
                case "5" :
                    System.out.println("Question 1.3");
                    String chaine = 
                    		"A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
                    String[] tmp = chaine.split(" ");
                    for(String s : tmp){
                    	pt.insert(pt, s);
                    }
                    System.out.println("Nombre de mots = " + pt.CountWord(pt));
                    System.out.println("Mots dans l'arbre = ");
                    pt.AllWord(pt);
                    break;
                    
                case "n" :
                    setEndsoft(true);
                    break;
                    
			default :
                    System.out.println("Echec veuillez entrée un choix valide\n ");
                    break;
            }
            
        } while (!isEndsoft());
    }

	public static boolean isEndsoft() {
		return endsoft;
	}

	public static boolean setEndsoft(boolean endsoft) {
		PatriciaTrieTest.endsoft = endsoft;
		return endsoft;
	}
}