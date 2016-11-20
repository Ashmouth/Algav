PatriciaTrie :
    data;
    sous_arbres;
    List<String> préfixes
    finmot = " "

    PatriciaTrie(mot) :
        data = mot
        sous_arbres = vide

    fonction inserer(arbre, mot) :
        prefixe = première data de sous_arbres
        si mot = vide et préfixe = finmot alors
            renvoi arbre;
        sinon si mot = vide et prefixe != finmot alors
            pt = PatriciaTrie(mot);
            ajouter_au_début(sous_arbres, pt)
            renvoi arbre;
        sinon
            pour chaque sous_arbre dans sous_arbres faire
                prefixe = data de sous_arbre
                si mot contient prefixe alors
                    renvoi inserer(sous_arbre, reste_de(mot - longueur(prefixe)))
                sinon
                    prf = prefixe_mot(mot, prefixe);
                    si prf != vide alors
                        len = longueur(prefixe)
                        racine = prefixe_moins_fin(len - longueur(prf))
                        suffix = prefixe_moins_début(len - longueur(prf))
                        sous_arbre = ajoute_à_chaque_suffix(sous_arbre, suffix);
                        sous_arbre.data = racine
                        renvoi inserer(sous_arbre, reste_de(mot - longueur(prefixe)))
                    FinSi
                FinSinon
            FinPour
            pt = PatriciaTrie(mot);
            ajouter_au_début(sous_arbres, pt)
            renvoi arbre;
        FinSinon
    FinFonction