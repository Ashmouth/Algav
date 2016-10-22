PatriciaTrie :
    char codechar[128]
    PatriciaTrie sous-arbres[128]
    List<String> préfixes

    PatriciaTrie :
        codechar[128] = a,b...
        sous-arbre[128] = NULL, NULL...
        préfixes = NULL

    Fonction rechercher(arbre,mot) :
        Si vide(mot) et finmot(arbre)  alors
            Renvoyer Vrai
        Sinon si vide(mot) ou finmot(arbre) alors
            Renvoyer Faux
        Sinon
            Pour prefixe dans prefixes(arbre) faire
                Si est_un_préfixe(préfixe, mot) alors
                    Renvoyer rechercher(sous_arbre(arbre,préfixe),
                            suffixe(mot,taille(préfixe)) )
                    Renvoyer Faux

    Fonction inserer(arbre,mot) :
        Si vide(mot) et finmot(arbre)  alors
            Renvoyer arbre
        Sinon si vide(mot) && non-finmot(arbre) alors
            Renvoyer arbre(finmot)
        Sinon
            Pour prefixe dans prefixes(arbre) faire
                Si est_un_préfixe(préfixe, mot) alors
                    Renvoyer rechercher(sous_arbre(arbre,préfixe),
                            suffixe(mot,taille(préfixe)) )
                    Renvoyer préfixes+=préfixe

    Fonction supprimer(arbre,mot) :
        Si vide(mot) et finmot(arbre)  alors
            Renvoyer arbre(non-finmot)
        Sinon si vide(mot) && non-finmot(arbre) alors
            Renvoyer arbre
        Sinon
            Pour prefixe dans prefixes(arbre) faire
                Si est_un_préfixe(préfixe, mot) alors
                    Renvoyer rechercher(sous_arbre(arbre,préfixe),
                            suffixe(mot,taille(préfixe)) )
                    Renvoyer préfixes-=préfixe

