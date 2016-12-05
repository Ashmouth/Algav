# Devoir de Programmation : Tries

## 1.1 Structure 1 : Patricia-Tries

Question 1.1 : Le caractère pour le fin d'un mot est '@'

Question 1.2 : Primitive insertion et recherche implémenter

Question 1.3 :
Nombre de mots = 36
Mots dans l'arbre = 
A
appel
a
chacune
ci
clavier
coeur
connait
dactylo
dactylographie
de
des
dessous
du
ecrire
elle
fait
genre
genial
la
machine
modele
nous
par
professeur
puisque
phrase
que
quel
redevables
superbe
sommes
touches
toute
un
?

Question 1.5 :
Le nombres de mots est de : 36
Liste : [?, A, a, appel, chacune, ci, clavier, coeur, connait, dactylo, dactylographie, 
de, des, dessous, du, ecrire, elle, fait, genial, genre, la, machine, 
modele, nous, par, phrase, professeur, puisque, que, quel, redevables, 
sommes, superbe, touches, toute, un]

Question 4.10 :
Calcule des complexitées PatriciaTrie :

findPrefix = O (longeur du mot)

displayPtree = O (nb_max_de_caractère * profondeur)
(Parcours d'une Hashmap au pire des cas contenant tout les caratères possible)

cloneAll = O (nb_max_de_caractère)
(Parcours d'une Hashmap au pire des cas contenant tout les caratères possible)

search = O (4 * (longeur du mot))
(Une comparaison puis lancement de la sous fonction
4 comparaisons pour chaque caractère du mot aux pire cas)

delete = O (4 * (longeur du mot))
(Une comparaison puis lancement de la sous fonction
4 comparaisons pour chaque caractère du mot aux pire cas)

insert = O ((5 * cloneAll) * longeur du mot)
(5 comparaison puis lancement de la fonction cloneAll, au pire cas pour chaque
caractères du mot)

countWord = O ((1 + nb_max_de_caractère) * profondeur)
(Une comparaisons et parcours d'une Hashmap au pire des cas contenant tout 
les caratères possible pour toute la profondeur de l'arbre)

countDeep = O ((1 + nb_max_de_caractère) * profondeur)
(Une comparaisons et parcours d'une Hashmap au pire des cas contenant tout 
les caratères possible pour toute la profondeur de l'arbre)

arrayWord = O ((1 + nb_max_de_caractère) * profondeur)
(Une comparaisons et parcours d'une Hashmap au pire des cas contenant tout 
les caratères possible pour toute la profondeur de l'arbre)

allWord = O ((1 + nb_max_de_caractère) * profondeur)
(Une comparaisons et parcours d'une Hashmap au pire des cas contenant tout 
les caratères possible pour toute la profondeur de l'arbre)

copy = O (nb_max_de_caractère * profondeur)
(Parcours d'une Hashmap au pire des cas contenant tout les caratères possible 
pour toute la profondeur de l'arbre)

split = O (2 + cloneAll)
(Deux comparaisons et lancement de la fonction cloneAll)

fusion = O (2 * (nb_max_de_caractère * profondeur_min-d'un_des_deux_arbres))
(Deux comparaisons puis, parcours d'une Hashmap au pire des cas contenant tout 
les caratères possible pour toute la profondeur de l'arbre le plus court)

getDeep = O ((1 + nb_max_de_caractère) * profondeur)
(Une comparaisons et parcours d'une Hashmap au pire des cas contenant tout 
les caratères possible pour toute la profondeur de l'arbre le plus court)

mediumDeep = O (getDeep * nb_elem_getDeep)
(Appel getDeep puis Parcours la liste obtenu)

getPrefix = O (3 * longeur du mot)
(Trois comparaisons pour chaque caractère du mot)

convert = O ((nb_caractère_du_préfix + nb_max_de_caractère) * profondeur)
(Parcours du préfixe et parcours de la Hashmap des fils au pire des cas 
contenant tout les caratères possible pour toute la profondeur de l'arbre)


Calcule des complexitées au pire Tries Hybrides : (nombre de comparaison)

ajouterMot = Θ 5 * (longeur du mot)

recherche = Θ 4 * (longeur du mot)

comptageMots = Θ 2 * nb de noeud

listeMots = Θ 1 + (4 * nb de noeud) 

comptageNil = Θ nb de noeud

hauteur = Θ nb de noeud

profondeurMoyenne = Θ 1 + (4 * nb de noeud)

prefixe = Θ (2 + (4 * longeur du prefixe)) + (2 + (2 * nb de noeud))
prefixe = Θ 4 + (4 * longeur du prefixe) + (2 * nb de noeud)

suppression = Θ 1 + (4 * (longeur du mot)) + (hauteur de l'arbre) + (4 * longeur du mot * hauteur de l'arbre)

Conversion Hybrides => Patricia = 1 + (4 * nb de noeud) + (nb de mot * (5 + cloneAll * (longeur du mot)))




Question 5.11 et 5.12 :
Benchmark )

```
PatriciaTrie Benchmark (en nanoseconde)
________________________________________________________________________________
file                 | build    | insert | search | delete | fusion | nbword | deep  
1henryiv.txt         | 6941749  | 1173   | 1920   | 2666   | 2240   | 3723   | 9     
1henryvi.txt         | 6334072  | 746    | 1600   | 2240   | 2133   | 3725   | 9     
2henryiv.txt         | 7198393  | 960    | 1706   | 2346   | 2133   | 3963   | 10    
2henryvi.txt         | 6960640  | 746    | 1600   | 2240   | 2026   | 3935   | 10    
3henryvi.txt         | 6784615  | 853    | 1920   | 2453   | 2346   | 3448   | 9     
allswell.txt         | 6151363  | 853    | 4800   | 5333   | 2346   | 3388   | 9     
asyoulikeit.txt      | 6246725  | 746    | 1386   | 2133   | 2026   | 3170   | 10    
cleopatra.txt        | 7999646  | 853    | 1600   | 2346   | 2133   | 3782   | 9     
comedy_errors.txt    | 4401948  | 746    | 1493   | 2240   | 2026   | 2438   | 9     
coriolanus.txt       | 9190381  | 960    | 1813   | 2666   | 2240   | 3883   | 10    
cymbeline.txt        | 8632926  | 1066   | 1493   | 2346   | 2240   | 4057   | 10    
hamlet.txt           | 9561544  | 853    | 1706   | 7680   | 2026   | 4554   | 9     
henryv.txt           | 9030036  | 1066   | 1920   | 14293  | 2240   | 4393   | 10    
henryviii.txt        | 8303628  | 853    | 9493   | 6933   | 2240   | 3515   | 10    
john.txt             | 6625583  | 959    | 2986   | 2027   | 2239   | 3437   | 10    
julius_caesar.txt    | 6278078  | 640    | 11306  | 5013   | 2453   | 2793   | 9     
lear.txt             | 8325103  | 853    | 960    | 1066   | 2240   | 4007   | 9     
lll.txt              | 5965119  | 1173   | 853    | 1279   | 2773   | 3637   | 9     
macbeth.txt          | 4883188  | 639    | 2773   | 1173   | 2133   | 3204   | 9     
measure.txt          | 5881927  | 853    | 960    | 1173   | 2453   | 3229   | 9     
merchant.txt         | 5697591  | 853    | 853    | 960    | 2133   | 3160   | 9     
merry_wives.txt      | 5834901  | 640    | 853    | 1066   | 2133   | 3188   | 9     
midsummer.txt        | 4299202  | 746    | 960    | 960    | 2026   | 2914   | 9     
much_ado.txt         | 6285089  | 853    | 960    | 1173   | 2133   | 2907   | 10    
othello.txt          | 7218516  | 853    | 960    | 1173   | 2346   | 3665   | 9     
pericles.txt         | 5037078  | 960    | 1173   | 1066   | 2133   | 3144   | 9     
richardii.txt        | 6102382  | 746    | 853    | 1173   | 2133   | 3513   | 9     
richardiii.txt       | 7995469  | 1280   | 1066   | 1066   | 2026   | 3896   | 9     
romeo_juliet.txt     | 6606632  | 853    | 1066   | 1173   | 2453   | 3545   | 9     
taming_shrew.txt     | 6152624  | 746    | 1066   | 1066   | 2240   | 3149   | 9     
tempest.txt          | 4731822  | 853    | 960    | 1066   | 3626   | 3084   | 9     
timon.txt            | 5187179  | 853    | 960    | 1173   | 2240   | 3187   | 10    
titus.txt            | 5681894  | 640    | 960    | 960    | 2240   | 3306   | 9     
troilus_cressida.txt | 7818564  | 960    | 1066   | 1280   | 2346   | 4119   | 9     
twelfth_night.txt    | 5327678  | 853    | 1066   | 1066   | 2346   | 3030   | 10    
two_gentlemen.txt    | 4487653  | 533    | 960    | 1066   | 2240   | 2638   | 9     
winters_tale.txt     | 6835816  | 853    | 960    | 1066   | 2240   | 3714   | 9     
________________________________________________________________________________

```
```

Tries Hybrides Benchmark (en nanoseconde)
_____________________________________________________
file                 | build    | insert | search | delete | nbword | deep  
1henryiv.txt         | 11145929 | 746    | 1386   | 2026   | 3723   | 27    
1henryvi.txt         | 10056634 | 746    | 1280   | 1280   | 3724   | 25    
2henryiv.txt         | 11891485 | 853    | 1493   | 2026   | 3963   | 32    
2henryvi.txt         | 11831390 | 747    | 7040   | 4906   | 3935   | 32    
3henryvi.txt         | 10651407 | 853    | 1493   | 1706   | 3448   | 29    
allswell.txt         | 12636135 | 746    | 1280   | 1600   | 3388   | 27    
asyoulikeit.txt      | 12902079 | 746    | 1173   | 1386   | 3170   | 28    
cleopatra.txt        | 13653387 | 640    | 1066   | 3199   | 3782   | 28    
comedy_errors.txt    | 6671970  | 746    | 1280   | 1173   | 2438   | 24    
coriolanus.txt       | 11643527 | 853    | 1280   | 1813   | 3883   | 25    
cymbeline.txt        | 12075230 | 746    | 1173   | 1493   | 4057   | 26    
hamlet.txt           | 13002817 | 960    | 1386   | 1706   | 4554   | 25    
henryv.txt           | 11718029 | 746    | 1386   | 1813   | 4393   | 30    
henryviii.txt        | 10847664 | 746    | 1386   | 1600   | 3515   | 26    
john.txt             | 9003487  | 640    | 1173   | 1280   | 3437   | 27    
julius_caesar.txt    | 9774991  | 746    | 2773   | 1173   | 2793   | 26    
lear.txt             | 12550992 | 853    | 1066   | 1386   | 4007   | 26    
lll.txt              | 9973405  | 640    | 960    | 1493   | 3637   | 33    
macbeth.txt          | 7783319  | 960    | 1813   | 1600   | 3204   | 26    
measure.txt          | 9062716  | 746    | 1386   | 1386   | 3229   | 24    
merchant.txt         | 8807327  | 640    | 1173   | 1386   | 3160   | 28    
merry_wives.txt      | 9380118  | 640    | 1280   | 1280   | 3188   | 27    
midsummer.txt        | 8001905  | 746    | 1173   | 1386   | 2914   | 25    
much_ado.txt         | 9008922  | 853    | 1280   | 1066   | 2907   | 26    
othello.txt          | 11397082 | 640    | 1280   | 1706   | 3665   | 26    
pericles.txt         | 8269094  | 746    | 1173   | 1386   | 3144   | 24    
richardii.txt        | 10902701 | 746    | 1280   | 1173   | 3513   | 26    
richardiii.txt       | 12837746 | 853    | 1386   | 1600   | 3896   | 25    
romeo_juliet.txt     | 10953277 | 640    | 1173   | 1173   | 3545   | 26    
taming_shrew.txt     | 8367364  | 640    | 8533   | 1493   | 3149   | 25    
tempest.txt          | 7926711  | 747    | 1386   | 1599   | 3084   | 27    
timon.txt            | 8548197  | 853    | 1280   | 1600   | 3187   | 26    
titus.txt            | 9575298  | 746    | 1066   | 1600   | 3306   | 26    
troilus_cressida.txt | 12505239 | 746    | 1280   | 1706   | 4119   | 27    
twelfth_night.txt    | 9236125  | 853    | 16426  | 960    | 3030   | 31    
two_gentlemen.txt    | 7587473  | 640    | 8000   | 853    | 2638   | 25    
winters_tale.txt     | 12732129 | 533    | 960    | 1066   | 3714   | 28    
_____________________________________________________
```