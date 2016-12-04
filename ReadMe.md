# Devoir de Programmation : Tries

## 1.1 Structure 1 : Patricia-Tries

Question 1.1 : Le caractère pour le fin d'un mot est '@'

Question 1.2 : Primitive insertion et recherche implémenter

Question 1.3 :
Nombre de mots = 36
Mots dans l'arbre = 
a
appel
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

Benchmark )

```
PatriciaTrie Benchmark (en nanoseconde)
________________________________________________________________________________
file                 | build    | insert | search | delete | fusion | nbword | deep  
lll.txt              | 62665389 | 8553   | 25896  | 13779  | 27060  | 3637   | 9     
comedy_errors.txt    | 32407013 | 8334   | 7759   | 6010   | 19735  | 2438   | 9     
richardii.txt        | 43973267 | 35479  | 21824  | 17732  | 25043  | 3513   | 9     
twelfth_night.txt    | 37187100 | 7419   | 9766   | 7102   | 17352  | 3030   | 10    
merchant.txt         | 34073605 | 23944  | 20964  | 403569 | 31033  | 3160   | 9     
merry_wives.txt      | 36838517 | 8976   | 8954   | 8583   | 14301  | 3188   | 9     
henryv.txt           | 49361323 | 11580  | 10501  | 6835   | 15757  | 4393   | 10    
1henryiv.txt         | 39817201 | 29950  | 18845  | 14393  | 16542  | 3723   | 9     
2henryiv.txt         | 36465126 | 23284  | 8785   | 5910   | 13754  | 3963   | 10    
1henryvi.txt         | 35250137 | 25172  | 23074  | 14843  | 18822  | 3725   | 9     
3henryvi.txt         | 26169784 | 23315  | 8546   | 6118   | 13959  | 3448   | 9     
measure.txt          | 17587919 | 23396  | 8795   | 5986   | 19820  | 3229   | 9     
othello.txt          | 21828583 | 6350   | 10097  | 6243   | 14438  | 3665   | 9     
taming_shrew.txt     | 21208092 | 4952   | 10492  | 7139   | 15674  | 3149   | 9     
tempest.txt          | 12562521 | 5113   | 9082   | 6022   | 17810  | 3084   | 9     
macbeth.txt          | 16124139 | 3521   | 8733   | 5728   | 14113  | 3204   | 9     
asyoulikeit.txt      | 12547031 | 3900   | 7539   | 4797   | 11231  | 3170   | 10    
allswell.txt         | 10263842 | 4996   | 7201   | 4686   | 10566  | 3388   | 9     
hamlet.txt           | 17526836 | 3995   | 23282  | 5129   | 16411  | 4554   | 9     
julius_caesar.txt    | 11017823 | 2608   | 41848  | 5355   | 12624  | 2793   | 9     
much_ado.txt         | 10703281 | 6841   | 5526   | 5163   | 16687  | 2907   | 10    
coriolanus.txt       | 13489614 | 4983   | 5135   | 5798   | 18061  | 3883   | 10    
romeo_juliet.txt     | 11912962 | 3979   | 5067   | 5095   | 17634  | 3545   | 9     
timon.txt            | 8143238  | 4699   | 4757   | 4910   | 11417  | 3187   | 10    
winters_tale.txt     | 12751308 | 5364   | 5452   | 5096   | 11326  | 3714   | 9     
pericles.txt         | 7983369  | 2865   | 3444   | 4486   | 8815   | 3144   | 9     
john.txt             | 7715019  | 2211   | 3519   | 6876   | 26934  | 3437   | 10    
2henryvi.txt         | 12046090 | 2391   | 3835   | 5713   | 24372  | 3935   | 10    
richardiii.txt       | 14138694 | 4640   | 4779   | 5291   | 56979  | 3896   | 9     
henryviii.txt        | 11125763 | 4514   | 4548   | 4968   | 9409   | 3515   | 10    
troilus_cressida.txt | 12247460 | 8158   | 7901   | 6688   | 15792  | 4119   | 9     
midsummer.txt        | 6677614  | 3189   | 3222   | 4188   | 7805   | 2914   | 9     
cymbeline.txt        | 10226891 | 2921   | 3543   | 5892   | 24722  | 4057   | 10    
lear.txt             | 9745218  | 3225   | 3869   | 4955   | 8919   | 4007   | 9     
cleopatra.txt        | 9424157  | 3067   | 3510   | 4391   | 7853   | 3782   | 9     
titus.txt            | 9900795  | 1781   | 3344   | 5511   | 25331  | 3306   | 9     
two_gentlemen.txt    | 8321354  | 3450   | 5317   | 5495   | 29259  | 2638   | 9     
________________________________________________________________________________
```
```

Tries Hybrides Benchmark (en nanoseconde)
_____________________________________________________
file                 | build    | insert | delete | nbword |deep  
lll.txt              | 59166961 | 2320   | 24899  | 3640   | 33    
comedy_errors.txt    | 30805931 | 3412   | 7866   | 2441   | 24    
richardii.txt        | 51216749 | 7594   | 14143  | 3516   | 26    
twelfth_night.txt    | 30102027 | 2493   | 24664  | 3033   | 31    
merchant.txt         | 32750354 | 6108   | 12635  | 3163   | 28    
merry_wives.txt      | 30835418 | 2120   | 6882   | 3191   | 27    
henryv.txt           | 51601572 | 3167   | 459309 | 4396   | 30    
1henryiv.txt         | 56602944 | 4763   | 6351   | 3726   | 27    
2henryiv.txt         | 47101330 | 3960   | 5192   | 3966   | 32    
1henryvi.txt         | 34133626 | 1863   | 3360   | 3727   | 25    
3henryvi.txt         | 55901151 | 15178  | 3834   | 3451   | 29    
measure.txt          | 42948994 | 17728  | 3476   | 3232   | 24    
othello.txt          | 49352443 | 2022   | 4027   | 3668   | 26    
taming_shrew.txt     | 41019566 | 1933   | 4145   | 3152   | 25    
tempest.txt          | 35851632 | 2825   | 4911   | 3087   | 27    
macbeth.txt          | 51957842 | 4428   | 4187   | 3207   | 26    
asyoulikeit.txt      | 48627768 | 2282   | 4716   | 3173   | 28    
allswell.txt         | 49541001 | 2531   | 5143   | 3391   | 27    
hamlet.txt           | 60671141 | 3190   | 4489   | 4557   | 25    
julius_caesar.txt    | 37114704 | 11149  | 16116  | 2796   | 26    
much_ado.txt         | 43100367 | 2423   | 2896   | 2910   | 26    
coriolanus.txt       | 49966101 | 14410  | 4497   | 3886   | 25    
romeo_juliet.txt     | 20408237 | 21288  | 4693   | 3548   | 26    
timon.txt            | 15538697 | 1466   | 3926   | 3190   | 26    
winters_tale.txt     | 30449108 | 1615   | 4250   | 3717   | 28    
pericles.txt         | 22301478 | 1784   | 4557   | 3147   | 24    
john.txt             | 29998409 | 1639   | 4003   | 3440   | 27    
2henryvi.txt         | 39923689 | 6710   | 5403   | 3938   | 32    
richardiii.txt       | 41987965 | 1588   | 4123   | 3899   | 25    
henryviii.txt        | 26391065 | 3333   | 7570   | 3518   | 26    
troilus_cressida.txt | 30336500 | 1616   | 4559   | 4122   | 27    
midsummer.txt        | 18357216 | 1646   | 8450   | 2917   | 25    
cymbeline.txt        | 30775933 | 1875   | 4732   | 4060   | 26    
lear.txt             | 31704536 | 1703   | 3802   | 4010   | 26    
cleopatra.txt        | 31751727 | 1351   | 4743   | 3785   | 28    
titus.txt            | 23000790 | 1246   | 4175   | 3309   | 26    
two_gentlemen.txt    | 18053381 | 1425   | 3519   | 2641   | 25    
_____________________________________________________
```

Calcule des complexitées :

getPrefix = Θ (longeur du mot)

displayPtree = Θ 26 * (deep)

CloneAll = Θ 26

search = Θ 1 + (4 * (longeur du mot))

delete = Θ 1 + (4 * (longeur du mot))

insert = Θ 5 + cloneAll * (longeur du mot)

CountWord = Θ