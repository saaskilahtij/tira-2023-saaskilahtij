# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi. 

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää *lyhyitä* koodinpätkiä vaikkapa Java -formaatilla:

```Java
	@Override
	public int hashCode() {
		// Oma nerokas hajautufunktioni!
	}
```
Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n	| Fill	| Search	| Total |
|-----|--------|--------|-------|
| 500	 | 7	| 700	| 707 |
| 1000 |	9	| 288	| 297 | 

Alaluvut jokaisen tehtävän raportille löydät alta.


## 01-TASK

Tehtävässä vaikein oli visualisoida ja pitää mielessä suoritusajan tapahtumat. Kuitenkin debuggerin opettelu helpotti ja varsinkin, kun sain vaihdettua asetukset siten että muuttujat ja niiden arvot näytettiin livenä ajon aikana - joka ei jostain syystä ensin ollut päällä. Algoritmeistä ```insertionSort()``` oli hankalin. ```reverse()``` ei ollut yhtä hankala vaikka ensin lähdin väärille jäljille. Niistä ensimmäisen toteutin puhtaammin ja indeksistä indeksiin reversen toteutus jäi epäsiistiksi mutta kuitenkin toimivaksi ja se riittää tällä hetkellä.
Coder luokan ```compareTo(Coder another)``` ei tuottanut vaikeuksia ja oli helppo implementoida teorian pohjalta.

Arvioidaan nyt lisäyslajittelun aikakompleksisuutta. Parhaassa tapauksessa lista on jo oikeassa järjestyksessä,jolloin outerIndex ainoastaan tarkistaa luvut ja aikakompleksisuudeksi saadaan O(n). Pahimmassa tapauksessa taulukko on täysin käänteisessä järjestyksessä jolloin ensimmäisen silmukan jokaista operaatiota kohden tehdään O(n) operaatiota, eli yhdistettäessä saadaan O(n^2). Keskimääräinen lisäyslajittelun aikakompleksisuus on siis O(n^2).

Listan kääntämisessä aikakompleksisuus on vakio n suhteen. Koska ensimmäinen silmukka käy läpi arvot n/2, saadaan aikakompleksisuudeksi O(n/2) josta tippuu vielä vakio ja saadaan O(n).

***Jos taulukko on jo valmiiksi järjestyksessä nousevaan järjestykseen, ja se aiotaan lajitella laskevaan järjestykseen, kannattaako taulukko lajitella vai kääntää sen järjestys? Miksi, perustele?***

Kun taulukko voidaan kääntää kahdella eri algoritmilla, kannattaa sen tehdä algoritmilla joka hoitaa lajittelun nopeammin; olettaen ettei nopeamman algoritmin tilakompleksisuus ole järjetön. Kun vertaillaan algoritmien nopeutta, voidaan niitä vertailla aikakompleksisuuden avulla. Kun katsotaan lisäyslajittelua tapauksessa jossa esimerkiksi lista=[5,4,3,2,1] halutaan kääntää, voidaan aloittaa aikakompleksisuuden analysoinnilla.

Ensimmäinen ```for()``` silmukka iteroi jokatapauksessa listan läpi, jolloin sen aikakompleksisuus on ***O(n)***. Sen sisäisen ```while()``` silmukan toiminta tiedetään ja se suorittaa jokaiselle ***n***:lle ```int outerIndex``` arvon verran operaatioita. Koska ```int outerIndex``` arvot ovat suorituksen aikana ***1 - n***, saadaan silmukan aikakompleksisuudeksi ***O(n)*** vakion pudottua. Kun silmukat yhdistetään saadaan aikakompleksisuudeksi ***O(n^2)***, joka häviää taulukon kääntöalgoritmin ***O(n)*** aikakompleksisuudelle jokaisessa ***n***:n tapauksessa.


## 02-TASK

Tehtävässä opin syvemmin soveltamaan Comparator luokkaa. Opin myös lineaarisen haun algoritmin eri sovellutuksia. Tutustuin myös Javan Predicate luokkaan. Lineaarisen haun implementointi oli helppoa, mutta Comparatorin ja Predicaten käyttö oli haasteellisempaa.

Erot suoritusajassa johtuvat listan järjestyksestä kun sitä aletaan järjestelemään. Sorttaus koodarinimillä ja kokonimillä on suunnilleen yhtä nopea. Myös listan kääntämiset ovat noin yhtä nopeita molemmille vaihtoehdoille. Kun lista sortataan sekä käännetään, voi nähdä että aika on T(insertionSort) + T(reverse), eli ohjelma järjestelee listan ensin ja vasta sitten kääntää sen.

Käytetyt algoritmit ovat ```insertionSort()``` ja ```reverse()``` joiden aikakompleksisuutta analysoin jo ensimmäisen tehtävän raportissa. Insertion sortin aikakompleksisuus O(n^2) ja reversen O(n) ja jos käyttäjä valitsee toiminnan jossa lista järjestellään sekä käännetään, saadaan kahden algoritmin yhdistelmäksi O(n^2) ja O(n), jossa O(n) ei paina sen vertaa että sitä kannattaisi huomioida. Täten koko operaation aikakompleksisuudeksi saadaan O(n^2).
 
Vastaus kysymykseen mitä algoritmia käyttäisin jos aineisto olisi jo lajiteltu ja se täytyisi kääntää. Vastaus tähän kysymykseen on: sitä algoritmia joka on aika- ja tilakompleksisuudeltaan optimaalisin. Jo implementoiduista algoritmeistä käyttäisin ```reverse()``` algoritmia, sillä sen aikakompleksisuudeksi saadaan tehokas O(n).

Algoritmeja kutsutaan lineaarisiksi, koska niiden aikakompleksisuus on lineaarinen O(n). Sen voi huomata, kun hakee sovelluksessa ensimmäistä koodaria 
**"Search took 0 ms"** ja viimeistä koodaria **"Search took 30 ms"** jossa aika vaihtelee aineiston suuruuden mukaan.


![Lineaarisen haun O(n) graafi](/src/main/resources/images/linear_search_graph.png)



## 03-TASK

Tehtävässä opin puolitushaun algoritmin. En kokenut vaikeuksia implementoidessani algoritmia tai integroidessani sitä sovellukseen.

Kun ohjelmalla haetaan lineaarihaulla 50 000 objektin aineistosta henkilöitä *Sukunimellä*, kun aloitetaan laskevassa järjestyksessä ja testataan "Öörni" saadaan vertailuarvoksi noin ***50ms***. Vastaavasti "Aakula" etsitään ***0ms*** ja noin keskivälistä "Mellas" palauttaa noin ***30ms*** joka kertoo, että ollaan jo listan puolivälin alapuolella. Konkreettinen todiste O(n) toiminnasta, joka kalpeuttaa kivenkovimmankin empiristi-skeptikon. 

Puolitushaun testit eroavat siinä, että lista täytyy järjestellä algoritmin toimintaa varten. Järjestelyyn vaadittava aika on kuitenkin suhteellisen minimaalinen. Puolitushaku on merkittävästi lineaarista hakua nopeampi suuremmilla tietomäärillä. Kuitenkin järjestely vie aikaa ja järjestelemättömille pienille tietomäärille käyttäisin lineaarista hakua. Puolitushakua kannattaa käyttää suuremmille ja järjestetyille tietomäärille.

```
Pohdi ja kokeile:
Mikä on tässä nopeassa haussa keskimäärin suoritusaika suhteessa ensimmäiseen hakuun?
Kokeile hakea molemmilla tavoilla koodareita myös listan alusta. Mitä huomaat?
Kokeile ladata pieniä koodaritiedostoja ja kokeile molempia hakutapoja niillä, sekä lopusta listaa että alusta listaa.
Kirjoita havainnoistasi ja analyysistäsi miksi näin käy, raportiisi.
Miksi jompi kumpi haku on nopeampi, ja onko sillä väliä haetaanko aineiston alusta vai lopusta? Pohdi ja perustele.
```

### Puolitushaun aineiston (n) suhde täyttöaikaan (ms)
![Puolitushaun täyttöaika O(n) graafi](/src/main/resources/images/binary_search/binary_search_fill.png)
- Puolitushaun testiä ajettaessa täyttöaika ei ole ihmeellinen. Täyttöalgoritmin O(n) graafi alkaa näyttämään lätkämailalta, joka kertoo aikatehokkuudeksi O(n^2). 

### Puolitushaun aineiston (n) suhde lajitteluaikaan (ms)
![Puolitushaun lajittelu O(n) graafi](/src/main/resources/images/binary_search/binary_search_sort.png)
- Koska lajittelualgoritmi käyttää ```InsertionSort()``` algoritmi, sen aikakompleksisuus on O(n^2); kuten myös voidaan tulkita graafista.

### Puolitushaun aineiston (n) suhde hakuaikaan (ms)
![Puolitushaun haku O(n) graafi](/src/main/resources/images/binary_search/binary_search_search.png)
- Puolitushaun hakuaika on mielenkiintoisin. Siinä voi huomata alun piikin, josta voidaan päätellä, että n:n ollessa < 1000, algoritmi on todella hidas. Kuitenkin n > 1000 tapauksessa puolitushaku on todella tehokas, sillä se jakaa n:n puoliksi jokaisella askeleella ja se vähentää vertailuoperaatioita merkittävästi. Kun listassa on n alkioita, etsintäväli jaetaan puoliksi log2(n) kertaa, eli aikakompleksisuudeksi saadaan O(log2 n).

### Puolitushaun aineiston (n) suhde operaatioiden aikaan (ms)
![Puolitushaun total O(n) graafi](/src/main/resources/images/binary_search/binary_search_graph.png)
- Kokonaisaika ei ole myöskään yllättävä, sillä siinä kolme ylemmäistä graafia lisätään yhdeksi graafiksi. Siinä voidaan nähdä O(n^2) aikakompleksisuus, jota kuitenkin loiventaa puolitushaun O(log2 n) aikatehokkuus. Myös puolitushaun tehottomuuden pienemmille tietomäärille voi nähdä graafista.

### Toteutin puolitushaun myös rekursiivisesti :)

## 04-TASK

Opin tehtävän tekemisessä pinotietorakenteen ja mihin sitä voidaan soveltaa. Pinotietorakenne ei ollut turhan vaikea, mutta ParenthesisChecker vaatii ponnisteluja.

## Tarkastellaan pinotietorakenteen aikakompleksisuusluokkia:

```capacity()``` metodi palauttaa jokaisessa tapauksessa pinon kapasiteetin ja suorittaa ainoastaan yhden operaation. Tästä aikakompleksisuusluokaksi saadaan **O(1)**.

```push()``` on taas monimutkaisempi. Sen aikakompleksisuusluokka ilman lisäallokointia on **O(1)**, sillä se ainoastaan lisää elementin pinon päälle. Lisäallokoinnin tapauksessa metodi luo uuden suuremman listan ja sen jälkeen lisää vanhan listan muuttujat siihen, josta saadaan aikakompleksisuus **O(n)**. Koska algoritmin aikakompleksisuus on aina huonoin tapaus, saadaan sen luokaksi **O(n)**. 

```pop()``` poistaa ainoastaan pinon ylimmän elementin, jolloin sen aikakompleksisuus on **O(1)**.

```peek()``` palauttaa ainoastaan pinon ylimmän objektin ja sen aikakompleksisuus on **O(1)**.

```size()``` palauttaa ainoastaan pinon koon ja sen aikakompleksisuus on O(1).

```ìsEmpty()``` testaa onko pino tyhjä ja palauttaa totuusarvon - sen aikakompleksisuus on **O(1)**.

```clear()``` nollaa pinon luomalla uuden tyhjän pinon ja vaihtamalla sen alkuperäisen pinon päälle - sen aikakompleksisuus on **O(1)**'.

```toString()``` muodostaa merkkijonon pinosta. Metodissa hyödynnetään stringBuilderia ja se iteroi pinon läpi lisäämällä ne palautettavaan merkkijonoon. Koska jokainen pinon arvo iteroidaan, saadaan aikakompleksisuudeksi **O(n)**.

## ParenthesisChecker algoritmin analyysi:
Algoritmin logiikka perustuu pinotietorakenteen hyödyntämiseen. Algoritmi alkaa käymään läpi tuotua tiedostoa merkkijonona. Algoritmi testaa ensiksi onko kyseessä '\n' ja jos on, se tarkoittaa rivin loppumista. Tämän jälkeen algoritmi testaa onko kyseessä heittomerkki ja jos on, se asettaa inQuote totuusarvon todeksi. Päälogiikka tapahtuu ehtohaarassa jos ei olla heittomerkkien sisällä. Siinä algoritmi ensin testaa onko kyseessä avaava sulku ja jos on, se lisää sen pinoon. Jos lisäyksessä tapahtuu virhe, ohjelma heittää ```Stack_Failure``` errorin. Kun silmukka iteroi sulkevan sulkumerkin se poistaa avaavan sulkumerkin pinosta. Jos tämä operaatio epäonnistuu, toisin sanoen pino on tyhjä, ohjelma heittää ```TOO_MANY_CLOSING_PARENTHESES``` errorin. Se tarkoittaa, että vastaavaa aukaisevaa sulkua ei ole merkkijonossa ollut ja että pino on tyhjä. Jos poistaminen onnistuu, ohjelma testaa ovatko sulut toisiaan vastaavia kutsumalla ```isMatcing()``` funktiota. Jos sulut vastaavat toisiaan, ohjelma jatkaa suoritusta. Jos sulut eivät vastaa toisia, tiedetään että aloittavan sulkeen sulkeva sulku on väärä ja tässä tapauksessa ohjelma heittää ```PARENTHESES_IN_WRONG_ORDER``` errorin. Silmukan jälkeen algoritmi testaa onko pino tyhjä. Jos pino ei ole tyhjä, se tarkoittaa ettei avattua sulkua ole poistettu listasta, toisin sanoen vastaavaa sulkevaa sulkua ei ole löydetty merkkijonosta. Algoritmin aikakompleksisuusluokka on **O(n)**, sillä se iteroi tasan kerran jokaisen merkkijonon merkin *n*.

## 05-TASK


Tehtävässä opin taulukkopohjaisen jonotietorakenteen toteutuksen. Opiskelin myös linkitetyn listan idean. Taulukkopohjaisessa tietorakenteessa ```toString()``` oli helposti mutkikkain metodi. Myös ```enqueue()``` sekä ```dequeue()``` tuottivat haastetta. Viimeisiä ```toString()``` testejä debuggasin hiki otsalla ja ongelman juurisyy löytyikin salapoliisityöllä ```enqueue()``` metodista. 

Kun linkitettyä listaa ja taulukkopohjaista jonoa vertailee, niitä kannattaa vertailla käyttötarkoitusten mukaan. Jos tietorakennetta pidetään enimmäkseen staattisena, ei paljon lisäilyjä ja poistoja, kannattaa tässä tapauksessa suosia taulukkopohjaista jonoa - koska linkitetty lista kirjoittaa pitkin muistia ja jos muistipaikat ovat kaukana toisistaan voi se aiheuttaa hitautta. Taulukkopohjaisen tietorakenteen muistinvarauksessa se varaa perättäisiä, lähekkäisiä muistipaikkoja. Missä linkitetty lista sitten loistaa, on sen vahvuus lisätä tietoja nopeasti kunhan tietomäärät pysyvät suhteellisen pieninä, sen aikakompleksisuuden pysyven vakiona **O(1)**. Taulukkopohjaisen jonon reallokoinnin tapauksessa **O(n)** häviää aina linkitetyn listan aikakompleksisuudelle **O(1)**.

Kaikki jonon metodit täyttävät tehtävän aikavaatimukset. Hitaimmat aikakompleksisuudet löytyvät metodeista ```toString()``` joka iteroi listan jokaisen jäsenen O(n), sekä ```enqueue()``` jonka aikakompleksisuus on reallokoinnin tapauksessa **O(n)** - muuten sen ollessa **O(1)**.


## 06-TASK

Tehtävässä toteutin ja opin merge sort, sekä heap sort lajittelualgoritmin. Mutkikkainta tehtävässä oli listan kokoaminen takaisin. Törmäsin tehtävässä myös suuren tilakompleksisuuden ongelmiin - oli pelottavaa ensimmäistä kertaa törmätä stack overflow ja heap space erroriin. Erityisen vaikeaa oli yrittää toteuttaa fromIndex toIndex comparatorilla, enkä jostain syystä saanut kummankaan algoritmin toteutusta kahdesta testistä läpi. :( Debuggasin tehtävää hulluuden partaalle, mutta en millään löytänyt bugia. Onkojan vika omassa comparator toteutuksessa? SimpleContainer luokassa kutsuin ```fastSort(E[] array, Comparator<E> comparator)``` metodia ```fastSort(E[] array, fromIndex, toIndex, Comparator<E> comparator)``` sijasta. Onko näitä tehtäviä mahdollista myöhemmin korjata? Algoritmien toimintaperiaatteet tulivat hyvin tutuiksi debuggerilla niissä ryömiessä.

TODO: Analysoi testien CodersSlowComparatorTests ja CodersFastComparatorTests avulla hitaan lisäyslajittelun ja toteuttamasi nopean (tai nopeiden) algoritmien nopeuseroja. Arvioi algoritmien aikakompleksisuusluokkaa testin tulostamien aikamittausten, koodin ja kurssin teorian perusteella. Raportoi näistä havaintosi ja näkemyksesi perustellen analyysisi tulokset.

```mergeSort()``` aikakompleksisuutta tarkasteltaessa algoritmin voi jakaa kahteen eri osaan: 1. jakaminen, 2. vertailu ja 3. yhdistäminen muodostaen kauniin timanttisen puun <3. Jakamisvaiheessa algoritmi jakaa aineistoa puoliksi niin kauan, että jaetuista aineistoista löytyy ainoastaan yksi elementti. Koska algoritmi jakaa aineistoa binäärisesti, saadaan jakamisen aikakompleksisuudeksi **O(log n)**. Vertailussa ja yhdistämisessä jaettujen aineistojen elementtejä vertaillaan jonka jälkeen ne yhdistetään taas järjestetyksi isommaksi listaksi. Koska tässä operaatiossa jaettu aineisto A ja jaettu aineisto B ovat jo lajiteltuja, algoritmi iteroi ainoastaan listan B läpi etsiessään vertailtavalle listan A elementille paikkaa. Algoritmi loistaa, koska molemmat listat ovat jo valmiiksi lajiteltuja ja tästä saadaan operaation aikakompleksisuudeksi **O(n)**. Kun yhdistetään jakamisen aikakompleksisuus **O(log n)** vertailun ja yhdistämisen **O(n)** aikakompleksisuuden kanssa, saadaan kokonais aikakompleksisuudeksi **O(n log n)**, joka on merkittävästi nopeampi kuin ```insertionSort()``` algoritmin aikakompleksisuus **O(n^2)**. Perspektiivinä miljoonan **log** on kuusi, joka tarkoittaa ```miljoona * 6```, kun taas ```insertionSort()``` algoritmista saadaan ```miljoona * miljoona```. 

Kun tarkastellaan algoritmia ```heapSort()```, voidaan nähdä kuinka se ensin rakentaa binäärisen puun (keko) jaettuja listoja ja lähtee sitten vertailemaan binääristen jaettujen listojen elementtejä. Jaetun listan suurempi elementti "nostetaan puussa korkeammalle" vaihtamalla sen paikka vanhemman elementin kanssa, jos se on vanhempaa elementtiä suurempi. Tätä toistetaan niin kauan, että suurin elementti on nostettu listan vanhimmaksi elementiksi. Algoritmi varmistaa, että vanhin elementti on suurin ja poistaa sen sitten puusta, toisin sanoen lisää sen listan viimeiseksi jäseneksi. Tämän jälkeen keko järjestetään uudelleen kunnes suurin elementti löydetään taas vanhimman paikalta. Kun analysoidaan algoritmin aikakompleksisuutta, voidaan nähdä kuinka binäärisen puun teossa ensimmäinen ```for``` silmukka jakaa aineiston noin **n/2** kertaa jaetuiksi aineistoiksi, jolloin tämän operaation aikakompleksisuudeksi saadaan **O(n)** vakion 2 pudottua. Algoritmin vertaillessa ja hakiessa suurinta arvoa, sen aikakompleksisuus on **O(log n)** ja kun molempien operaatioiden aikakompleksisuudet yhdistetään, saadaan aikakompleksisuudeksi **O(n log n)**. Tämä päihittää myös ```insertionSort()``` algoritmin **O(n)** aikakompleksisuuden.

Miten ´´´mergeSort()``` ja ```heapSort()``` eroavat toisistaan, on ```heapSort()``` algoritmin tilaystävällisyys, joka hoitaa lajittelun käyttämättä "ylimääräistä" muistia, kun taas ```mergeSort()``` vaatii ylimääräisiä taulukkoja aineiston jakoon. Jos tila tulee olemaan ongelma, kannattaa käyttää ```heapSort()``` algoritmia.



### Kaavio ```insertionSort()``` suorituskyvystä:
![Lisäyslajittelun O(n) graafi](/src/main/resources/images/insertion_sort/insertion_sort_slow_test.png)


### Kaavio ```mergeSort()``` suorituskyvystä:
![Merge sort O(n) graafi](/src/main/resources/images/merge_sort/merge_sort_fast_test.png)

Funktiokutsut voivat olla hieman miten sattuu, sillä en ehtinyt korjata bugeja ennen määräaikaa. Jos algoritmejä millään voi korjata deadlinen jälkeen, korjaisin ne mielelläni. :)

## 07-TASK

Tämä tehtävä oli ylivoimaisesti vaikein, mutta debuggauksen ansiosta opin sen melko yksityiskohtaisesti. Vaikeinta ei ollut itse puu, vaan pienet yksityiskohdat sekä verrattain sekavat testit. 

Root solmu määrittää paljon binäärisen hakupuun syvyyttä. Näyttää siltä, että ideaalisessa binäärisessä hakupuussa keskimmäiseksi suurimman arvon ollessa root solmu, puu olisi kaikista tasapainoisin. Puun optimoimiseksi kannattaisi aineisto järjestää ja keskimmäisestä arvosta tehdä root solmu(?). Täysin tasapainoisen binäärisen hakupuun syvyys on logaritminen suhteessa solmujen määrään **D = log2(n)**. Jos puu ei ole tasapainoinen, siihen tehtävät toiminnot hidastuvat - pahin case on puu linkitettynä listana, kun rootiksi laitetaan aineiston pienin tai suurin arvo.


Mikä oli sinun toteutuksellasi puun maksimisyvyys (syvimmän haaran syvyys/korkeus) kullakin eri kokoisella aineistolla?


Algoritmit toteutin pääosin rekursiivisesti, sillä rekursiiviset rakenteet tuntuivat luonnollisemmalta käsitellessä hakupuuta ja sen solmuja; varsinkin kun abstrahoin solmun TreeNode erilliseksi luokaksi. Ainoa metodi jonka toteutin lapsisolmujen lukumäärän avulla oli ```getIndex()```. Jos tekisin tehtävän uudelleen, todennnäköisesti käyttäisin silti rekursiota. Metodin ```getIndex()``` toteuttaisin lapsisolmujen avulla, sillä tällä algoritmilla solmuja ei tarvitse indeksoida yksitellen, vaan lasten indeksit tiedetään jo.

| Elements (n) | Add Time | Add Time/Item | To Sorted Array Time | Search Time | Search Time/Item | Get(Index) Time | Get(Index) Time/Item | Testfile                     |
|--------------|----------|---------------|-----------------------|-------------|-------------------|------------------|----------------------|------------------------------|
| 100          | 1        | 0.0100        | 0                     | 1           | 0.0100            | 1                | 0.0100               | 100-city-coders.json         |
| 1000         | 1        | 0.0010        | 1                     | 1           | 0.0010            | 0                | 0.0000               | 1000-area-coders.json        |
| 5000         | 4        | 0.0008        | 0                     | 4           | 0.0008            | 1                | 0.0002               | 5000-town-coders.json        |
| 10000        | 6        | 0.0006        | 0                     | 6           | 0.0006            | 2                | 0.0002               | 10000-large-city-coders.json |
| 50000        | 38       | 0.0008        | 1                     | 41          | 0.0008            | 6                | 0.0001               | 50000-country-coders.json    |
| 100000       | 97       | 0.0010        | 5                     | 100         | 0.0010            | 13               | 0.0001               | 100000-europe-coders.json    |

Puun täyttöaika pysyy vakaana, riippuen puun tasapainosta. Oman puuni aikakompleksisuus lisätessä näytti toteuttavan **log2(n)** aikakompleksisuusluokan - joka on myös puun syvyys. 

Hakualgoritmi käyttää käytännössä samaa logiikkaa lisäämisen kanssa, menemällä syvemmälle puuhun vertailemalla solmuja. Tässäkin aikakompleksisuudeksi saadaan puun syvyys **log2(n)**.




## 08-TASK

Hajautusfunktiossa duplikaattien, sekä törmäysten hallinta oli hankalinta. Mielikuvitusta oli myös vaikea löytää hyvään hajautusfunktioon. Optimointi oli myös hankalaa. Toteutustapani wrappaamalla elementit ```LinkedListNode<T,R>``` luokaksi ei varmastikaan ollut optimaalinen. Kuitenkin heti alussa halusin, että hajautustaulu tukee törmäyshallintaa linkitetyllä listalla. 

Ensimmäisenä toteutin hajautusfunktion. Tässä tavoitteena oli hajauttaa merkkijono kokonaisluvulliseksi arvoksi mahdollisimman hyvällä entropialla. Olen jo pitkään halunnut oppia bittioperaatioita, joten tehtävä tuntui täydelliseltä haasteelta oppia asia. Opiskelin ja kokeilin ensin yksinkertaisia operaatioita, kuten XOR **^**, AND **&**, OR **|**, sekä bittisiirrot vasemmalle **<<** ja oikealle **>>**. Kuulin että hyvässä hajautusfunktiossa kannattaa käyttää alkulukuja joten valitsin tiivisteen alustukseksi 23 ja kertoimeksi 31. Kun olin jo aika pitkällä hajautusfunktiossa, huomasin, että bittioperaatiot antavat joka kerta eri arvon samalle merkkijonolle. Hikisestä tiedonkeruusta huolimatta en löytänyt vastausta sille miksi. Hieman tämän jälkeen silmäni osui Coder konstruktorin riviin ```id = UUID.randomUUID().toString();```. Tämän jälkeen testailin hajautusfunktion tehokkuutta. Testiaineistolle:
```Java
coder1.id = "123456";
coder2.id = "123457";
coder3.id = "123458";
```
arvoiksi sain:
```Java
330884602
330884603
330884596
```
hajautusfunktio ei ollut tarpeeksi hyvä. Kokeilin eri kombinaatioita ja opiskeltuani hajautusfunktioita törmäsin netissä vertailuun, jossa vertailtiin Bernsteinin, sekä Kernighanin ja Ritchien hajautusfunktioita. Myös näissä ammattimaisissa hajautusfunktioissa oli vain pieniä eroja loppukirjaimen muutoksessa. Tässä vaiheessa älysin hakea hajautusfunktion testidemon ja kokeilemalla päädyin tähän toteutukseen:
```Java
@Override
	public int hashCode() {
    int hash = 5381;
    int length = id.length();
    for (int index = 0; index < length; index++) {
      hash = (31 * hash) ^ id.charAt(index);
    }
    return hash;
	}
```
Hajautusfunktio ei ollut vielä tarpeeksi hyvä, joten ajattelin optimoida sen myöhemmin. Nyt kuitenkin siirryin implementoimaan itse hajautustaulua.

### Hajautustaulun aikatehokkuuksien analyysi


Hajautustauluun lisäämisen aikatehokkuus on vakio ***O(1)***, paitsi allokoinnin tapauksessa ***O(n)***, sekä törmäysten tai duplikaattien tapauksessa.

Haun aikatehokkuus taulusta on myös ***O(1)***, paitsi tehottomassa taulussani siihen liittyy linkitetyissä listoissa uiminen, jolloin tehokkuus ei ole optimaalinen.

Kun hajautustaulusta tehdään palautettava taulukko, aikakompleksisuudeksi saadaan ***O(n + taulukon koko)*** koska myös nullit loopataan läpi. Lopulliseksi aikakompleksisuudeksi saadaan ***O(n)***.

### Puolitushaun taulukko vs puu

Taulukkopohjaisessa, kuten puupohjaisessa puolitushaussa haun aikakompleksisuus on aina ***O(log2(n))***, mutta missä nämä tietorakenteet eroavat on lisäyksen sekä poistamisen aikatehokkuudet. Taulukkotietorakenteessa ne ovat yleensä (riippuen toteutuksesta) **O(n)**. Puutietorakenteessa, jos puu on tasapainoinen, nämä molemmat operaatiot ovat ***O(log2(n))***. Tämä on todella merkittävä parannus suurille tietomäärille - esimerkkinä jo pelkästään *2 000 000* kokoiselle aineistolle, jossa taulukosta poistamisen tai lisäämisen suurin mahdollinen operaatioden määrä on satatuhatta kertaa pienempi, vrt. *2 000 000* vs *20*.



### Hajautustaulu vs normaali taulu

Hajautustauluun lisäämisen aikakompleksisuus on vakio ***O(1)*** operaatio, kun taas normaalin taulukon aikakompleksisuus (riippuen toteutuksesta) on yleensä ***O(n)***; jos taulukon viimeisen indeksin paikka pidetään muistissa, voidaan lisäämisessä saavuttaa ***O(1)***. Hakuaika taulukossa voi vaihdella, mutta siinä ei koskaan päästä ***O(1)*** aikakompleksisuuteen(?). Binäärihaulla haku voidaan suorittaa ***O(log2(n))*** aikakompleksisuudella jos taulukko on järjestetty, muutoin ***O(n)***. Hajautustaulussa haku on usein vakio-operaatio ***O(1)*** laskemalla avaimen tiivisteen avulla taulukon avainta vastaava indeksi. Jos hajautustaulu on epäoptimaalinen, hajautusfunktio hajauttaa arvot huonosti samaan indeksiin, voi aikakompleksisuus pahimmillaan olla ***O(n)***. Hajautustaulun poistaminen käyttää samaa logiikkaa kuin lisääminen ja sen aikakompleksisuus on hyvässä taulussa ***O(1)***.

Hajautustaulu sopii paremmin epäjärjestetyille suuremmille tietomäärille, kun taas taulukko on sopivampi pienemmille järjestetyille tietomäärille. Hajautustaulun järjesteleminen perinteisellä tavalla rikkoo sen logiikkaa, mutta toisaalta voidaan sanoa että hajautustaulu on järjestyksessä hajautusfunktiolla tapahtuvan haun ansiosta. Kuitenkin eri kriteereillä tapahtuvassa järjestelyssä hajautustaulu häviää perinteiselle taulukolle. Perinteistä taulukko kannattaa suosia jos tietoa halutaan järjestellä ja esimerkiksi lukea järjestellysti ohjelmaan suoraan.


## 09-TASK

Graafi tietorakenteena ei ollut yhtä vaikea mitä binäärinen hakupuu. Johtui ehkä siitä, että solmujen linkittäminen oli tullut niin tutuksi kurssin aikana. Algoritmeista BFS ja DFS olivat suunnilleen yhtä vaikeita ja oli mielenkiintoista hyödyntää jonoa sekä pinoa algoritmien toteutuksessa. Tehtävässä päätin käyttää reunalistan hyödyntämistä ```Hashtable``` tietorakenteessa, jossa avaimena toimivat solmut, ja arvoina sen reunat Javan lista ```List<Edge<T>>``` tietorakenteessa. Päätin toteuttaa suuntaamattoman verkon.

Testien ja oman analyysiini perusteella toteutukseni on oikeellinen. Se luo suuntamattoman verkon ja sen algoritmit toimivat.

Reunuslista eroaa matriisista merkittävästi. Reunuslista on hyvin paljon muistiystävällisempi ***O(n+m)*** vs matriisin tilakompleksisuus ***O(n^2)***. Matriisi on parempi tiheämmille graafeille (enemmän reunoja), koska sen avulla reunojen ja niiden painojen käsittely on tehokaampaa kaksiulotteisten indeksien avulla. Harvassa graafissa (vähän reunoja) matriisi olisi pääosin tyhjä, jolloin kannattaa suosia reunuslistaa. Tärkeä myös huomauttaa, että solmun lisääminen reunuslistaan on aikakompleksisuudeltaan kaunis ***O(1)*** kun taas matriisissa se on ***O(n^2)***. Reunuslistassa reitin löytäminen painon kanssa on matriisia hitaampi aikakompleksisuudeltaan ***O(n)***, kun se on matriisissa nopeat ***O(1)***. Kun käytetään DFS ja BFS algoritmeja, ne toimivat tehokkaammin reunuslistassa sillä tässä iteroidaan ainoastaan olemassaolevat reunat, toisin kuin matriisin tapauksessa, jossa käydään läpi myös olemattomat reunat.

Kun analysiodaan verkon tiheyttä, lasketaan ensin kaikki verkon mahdolliset reunat, joka saadaan kaavalla ```P = V^2```. Eri aineistoille voidaan laskea:
```
P = 10^2 = 100
P = 100^2 = 10000
P = 1000^2 = 1000000
P = 5000^2 = 25000000
P = 10000^2 = 100000000
...
```
Kun saadaan selville reunojen määrä, voidaan suuntaamattoman verkon tiheys laskea kaavalla ```D = iE / pE```, jossa D tiheys, iE reunojen määrä, sekä pE reunojen maksimimäärä:
```
D = 40 / 100 = 0.4
D = 532 / 10000 = 0.0532
D = 5613 / 1000000 = 0.005613
D = 27602 / 25000000 = 0.00110408
D = 56551 / 100000000 = 0.00056551
...
```
Tästä voidaan nähdä, että graafi on hyvin harva, varsinkin graafin koon kasvaessa ja siksi reunuslista on sille optimaalisempi vaihtoehto. Eli reunuslista on matriisia parempi vaihtoehto kuin matriisi.

```Hashtable``` ja ```HashMap``` erikoisuuksista sen verran, että ```Hashtable``` on synkroninen sen ottaessa haltuun ainoastaan yhden säikeen, kun taas ```HashMap``` on epäsynkroninen ja se kykenee monisäikeisyyteen. Testeissä kykeni selvästi näkemään, että ```HashMap``` oli nopeampi. Tämä alkoi näkymään 50 000 koodarin aineistolla, jossa esimerkiksi ```Hashtable``` suoritti BFS haun lähes puolet hitaammin 68929 ms, jossa ```HashMap``` ylsi 39479 millisekuntiin. 100 000 koodarin aineistolla tämä vasta olikin näkyvää - siinä missä ```Hastable``` täytti verkon 3199269 ms (53.32115 minuuttia), ```HashMap``` hoiti sen 2244938 millisekunnissa (37.4156333 minuuttia). 

### Aikatehokkuudet graafeina

### Kaavio verkkoon lisättäessä:
![Verkon lisäämisen graafi](src/main/resources/images/graph/graph_fill_time_hashtable.png)

Kun taulukko täytetään reunalistatoteutuksessa, on reunan sekä solmun lisäyksellä sama aikatehokkuus ***O(1)***. Graafi ainoastaan näyttää eksponentiaaliselta, koska testiainestojen koko nousee dramaattisesti.

Tässä alla olevassa koodissa tapahtuu reunan lisääminen reunuslistaan. 
```Java
public void addEdge(Edge.EdgeType type, Vertex<T> source, Vertex<T> destination, double weight) {
  if (source == null || destination == null) {
    return;
  }
  List<Edge <T>> edges = edgeList.get(source);
  edges.add(new Edge<T>(source, destination, weight));
  edgeList.put(source, edges);
  edgeCount++;
  List <Edge<T>> edges2 = edgeList.get(destination);
  edges2.add(new Edge<T>(destination, source, weight));
  edgeList.put(destination, edges2);
  edgeCount++;
}
```
Kun solmun täytyy jo olla olemassa ennen funktiokutsua ```put()``` metodi ainoastaan päivittää sitä. Kuten koodista näkee, reunan lisääminen on vakio. Solmu luodaan jo ennen ```addEdge()``` funktiokutsua kutsumalla ```createVertexFor()``` funktiota ja senkin aikakompleksisuusluokka on vakio ***O(1)***
```Java
public Vertex<T> createVertexFor(T element) {
  Vertex<T> vertex = new Vertex<T>(element);
  edgeList.put(vertex, new ArrayList<Edge<T>>());
  vertexCount++;
  return vertex;
}
```

### Kaavio verkosta haettaessa:
![Verkon hakemisen graafi](src/main/resources/images/graph/graph_search_times_hashtable.png)

Aloitetaan BFS algoritmin analyysista. Sen aikakompleksisuus riippuu vahvasti solmuista ja reunoista sekä niiden rakenteesta. Reunalistan toteutuksessa aikakompleksisuudeksi saadaan ***O(n+m)***. Tässä algoritmissa jokainen solmu ja jokainen niiden reuna käydään läpi huonoimmassa tapauksessa. 

DFS algoritmin aikakompleksisuus riippuu samankaltaisesti BFS algoritmin kanssa verkon solmuista ja reunoista sekä niiden rakenteesta. Sen aikakompleksisuus on BFS algoritmin kanssa samaa luokkaa ***O(n+m)***.

BFS ja DFS näyttävät olevan yhtä nopeita. Jos kattoo tarkasti, voi oranssin viivan nähdä harmaan alla.