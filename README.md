# System zarządzania biblioteką

## Autor
Mateusz Gieroba (nr indeksu: 322072) \
322072@uwr.edu.pl, gierobamateusz@gmail.com \
Data złożenia projektu: 23/06/2021

## Opis projektu

### Założenia

W założeniu projekt miał być systemem umożliwiającym zarządzanie biblioteką dla pracownika, tj. umożliwiać zarządzanie księgozbiorem, bazą czytelników oraz umożliwiać zapisywanie wypożyczeń i zwracanie książek.

### Realizacja

Projekt realizuje swoje założenia, tj. udostępnia funkcjonalną bazę książek oraz czytelników, umożliwia wypożyczanie i zwracanie książek. Ponadto można wyśiwetlać listę dłużników (osób, które zalegają ze zwrotem książki) oraz filtrować czytelników i książki po każdym z pól.

## Obsługa projektu

### Kompilacja i uruchomienie

W głównym katalogu załączony został plik makefile umozliwiający kompilację (`make compile`) i uruchomienie (`make run`) oraz usunięcie plików binarnych (`make clean`).

### Poruszanie się w GUI

Główne okno GUI prezentuje 4 możliwe opcje. Aby przejść do zarządzania księgozbiorem/wypożyczeń/zwrotów należy wybrać opcję `Books`. Podobnie, aby zarządzać zbiorem czytelników wybiera się opcję `Redaers`. Celem sprawdzenia listy dłużników należy wybrać `Debtors list`. Za zakończenie programu oraz zapis bazy danych odpowiada przycisk `Exit`. Każde z następnych okienek zawiera przycisk `Back`, który pozwala cofnąć się o krok.

#### Books

Po wybraniu tej opcji otrzymujemy listę wszystkich obecnych w księgozbiorze książek (książki wycofane nie wyświetlają się). 
- Mamy możliwość filtrowania po dowolnej frazie z dowolnej kolumny (należy wprowadzić tekst u góry i kliknąć `Filter`).
- Aby dodać książkę do księgozbioru należy kliknąć `Add` i w nowym okienku wpisać wszystkie dane
- `Remove` wyświetla okno, które po wpisaniu ID książki i zatwierdzeniu usuwa daną książkę z księgozbioru
- `Rent/Return` wyświetla okno, które pozwala wypożyczać (podajemy ID książki, wypożyczającego oraz datę wypożyczenia w formacie `DD/MM/YYYY`) oraz zwracać ksiązki (podajemy ID książki i wypożyczającego)

#### Readers

Podobnie jak dla książek otrzymamy filtrowalną tablicę czytelników oraz analogiczną do książek możliwość dodania czytelnika.

#### Debtors list

Tak jak dla czytelników otrzymujemy filtrowalną tabelę osób, które zalegają z co najmniej jednym wypożyczeniem.

## Specyfikacja programistyczna

Dokumentację wygenerować programem Doxygen przy użyciu polecenia `doxygen Doxyfile` w katalogu głównym. W katalogu doc dostarczono wersję PDF oraz HTML, podstawowe wersje znajdują się w katalogu głównym. GUI nie posiada rozbudowanej dokumentacji, gdyż jest realizacją interfejsu dla dostarczonego API bazy danych. Program korzysta głównie z biblioteki Swing oraz bibliotek podstawowych, `LocalDate` i rozszerza `Exception`. Swing pozwala uruchamiać program na różnych platformach, back-end w żaden sposób nie zależy od systemu. Jedynym wymaganiem jest zainstalowanie podstawowej maszyny wirtualnej Java.

### Diagram klas

![](https://i.imgur.com/w2hKyFj.png)

### Dokumentacja

[Link do PDFa](https://pdfhost.io/v/cvS0x0uGx_refmanpdf.pdf)

## Referencje

Korzystałem z nastepujących źródeł:
- [Swing tutorial](https://www.youtube.com/watch?v=Kmgo00avvEw)
- Wiele tematów ze StackOverflow rozwiązujących pomniejsze problemy, których linkowanie mijałoby się z celem
- "Java. Podstawy. Wydanie XI" Cay S. Horstmann
