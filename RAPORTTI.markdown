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

## 08-TASK

## 09-TASK