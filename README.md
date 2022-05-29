# Webmegold
Repository a Webes megoldások tárgyhoz.

## Második projekt: SBSM
A Spring Boot Student Manager (SBSM) egy olyan webalkalmazás, melyben egy (kitalált) egyetem hallgatóit lehet kezelni. Az alkalmazás egy backendből és egy hozzá tartozó grafikus frontendből áll.

### Backend
A backend egy Java alapú Spring Boot projekt, mely az Apache Tomcat webszervert, illetve a h2 adatbázist használja. A backend támogatja a teljes körű C.R.U.D. adatbázis-kezelést különféle végpontokon keresztül.

##### Adatbázisba letárolt adatok
Minden hallgató egy Student objektumba kerül letárolásra. A program indulásakor 10 default Student objektum érhető el, melyeknek az alábbi attribútumai léteznek:
- firstName (string)
- lastName (string)
- age (int)
- university (string)
- faculty (string)
- isForeignStudent (boolean)

### Frontend
A frontend egy HTML / JavaScript alapú alkalmazás. A frontend segítségével grafikusan elérhetjük a hallgatók adatait, illetve különböző szűrési feltételek alapján kereshetjük is őket.

##### Lehetséges szűrési szempontok:
- ID alapján
- Keresztnév alapján
- Vezetéknév alapján
- Egyetem alapján
- Kar alapján
- Idősebb, mint
- Fiatalabb, mint

Lehetőség van továbbá csak külföldi / csak magyar hallgatók kilistázására is. A frontend további funkciója, hogy a könnyebb kereshetőség érdekében nem tesz különbséget kis/nagybetűk között, illetve helytelen adatformátum esetén (pl: ID keresés -> szám helyett szó) tájékoztatja a felhasználót a hibáról. Amennyiben a frontend használata közben a backend nem aktív, azt is képes jelezni a felhasználónak.

### Elérhető végpontok
A végpontokat én a Postman alkalmazással teszteltem, így a későbbi használathoz is azt javaslom. A végpontok a localhost:8080 címen érhetőek el.
#### /test
- HTTP-metódus: get
- Bekért adat: nincs
- Tesztelési célt szolgál, visszatérési értéke egy sztring: "Hello!"

#### /students (get esetén)
- HTTP-metódus: get
- Bekért adat: nincs
- Kilistázza az összes hallgatót, illetve az adataikat

#### /students (post esetén)
- HTTP-metódus: post
- Bekért adat: body részben JSON formátumban a hallgató adatai
- Új hallgatót ad hozzá az adatbázishoz, minden attribútumával együtt

#### /foreign
- HTTP-metódus: get
- Bekért adat: status={true|false}
- Kilistázza a csak magyar / csak külföldi hallgatókat a status értéktől függően

#### /youngerThan
- HTTP-metódus: get
- Bekért adat: age={num}
- Kilistázza azon hallgatókat, akik a megadott életkortól fiatalabbak
  
#### /olderThan
- HTTP-metódus: get
- Bekért adat: age={num}
- Kilistázza azon hallgatókat, akik a megadott életkortól idősebbek
  
#### /students/{id} (get esetén)
- HTTP-metódus: get
- Bekért adat: az ID megadása a {id} részbe
- Hallgató keresése ID alapján
  
#### /students/{id} (put esetén)
- HTTP-metódus: put
- Bekért adat: az ID megadása a {id} részbe & body részben JSON formátumban a hallgató adatai
- Meglévő hallgató adatainak frissítése ID alapján  

#### /students/{id} (delete esetén)
- HTTP-metódus: delete
- Bekért adat: az ID megadása a {id} részbe
- Hallgató törlése ID alapján
  
#### /firstname
- HTTP-metódus: get
- Bekért adat: fname={string}
- Hallgató keresése keresztnév alapján
  
#### /lastname
- HTTP-metódus: get
- Bekért adat: lname={string}
- Hallgató keresése vezetéknév alapján

#### /faculty
- HTTP-metódus: get
- Bekért adat: fac={string}
- Hallgató keresése kar alapján
  
#### /university
- HTTP-metódus: get
- Bekért adat: uni={string}
- Hallgató keresése egyetem alapján
  
#### /student_uni/{id}
- HTTP-metódus: patch
- Bekért adat: az ID megadása a {id} részbe & body részben JSON formátumban a hallgató adatai
- Csakis a hallgató egyetemének frissítése ID alapján
  
#### /student_fac/{id}
- HTTP-metódus: patch
- Bekért adat: az ID megadása a {id} részbe & body részben JSON formátumban a hallgató adatai
- Csakis a hallgató karának frissítése ID alapján
  
#### /deleteallstudents
- HTTP-metódus: delete
- Bekért adat: nincs
- Az összes hallgató törlése (teljes adatbázis törlés)

### JSON példa
Minden adat JSON formátumban érkezik meg a backendtől, illetve új hallgató / meglévő frissítése esetén a body részben is JSON adatokat kell átadnunk az alkalmazásnak.

Példa:
```
{
    "id": 1,
    "firstName": "Norbert",
    "lastName": "Nagy",
    "age": 20,
    "university": "DE",
    "faculty": "IK",
    "foreignStudent": false
}
```
