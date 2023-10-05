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

# Analysoi nää!

### Puolitushaun aineiston (n) suhde täyttöaikaan (ms)
![Puolitushaun haku O(n) graafi](/src/main/resources/images/binary_search/binary_search_fill.png)

### Puolitushaun aineiston (n) suhde lajitteluaikaan (ms)
![Puolitushaun haku O(n) graafi](/src/main/resources/images/binary_search/binary_search_sort.png)

### Puolitushaun aineiston (n) suhde hakuaikaan (ms)
![Puolitushaun haku O(n) graafi](/src/main/resources/images/binary_search/binary_search_search.png)

### Puolitushaun aineiston (n) suhde operaatioiden aikaan (ms)
![Puolitushaun total O(n) graafi](/src/main/resources/images/binary_search/binary_search_graph.png)



## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK