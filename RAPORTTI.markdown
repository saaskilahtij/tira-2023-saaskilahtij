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


| n | Fill | Search | Total |
|---|------|--------|-------|
|                           |
|                           |


## 02-TASK

## 03-TASK

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK