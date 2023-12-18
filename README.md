# Kurs: Testowanie i Jakość Oprogramowania (Projekt)
#UWAGA - najaktualniejsza wersja znajduje się w gałęzi `develop`

### Autorzy: Damian Majka, Dominik Kiwior, Anita Zych
### Temat: Aplikacja do zarządzania wirtualną biblioteką
### Opis projektu:
Nasza aplikacja ma na celu zarządzanie zasobami wirtualnej biblioteki, umożliwiając użytkownikom wyszukiwanie, rezerwację, wypożyczanie oraz sprawdzenie dostępności książek. Można utworzyć nowe konto użytkownika, zalogować się do istniejacego konta. Z panelu zarządzania biblioteką można dodąc do bazy nowe konto (rejestracja).

## Uruchomienie testów jednostkowych i integracyjnych
- sklonuj repozytorium do folderu na swoim urządzeniu
- przejdź do scieżki [twoja_ścieżka_do_projektu]\tijoLibrary
- uruchom komendę `mvn test`

## Scenariusze testowe dla testera manualnego 


### 1. Scenariusz testowy: Proces dodawania nowej książki  

- Krok 1: Zaloguj się do systemu biblioteki.  

- Krok 2: Przejdź do panelu administratora.  

- Krok 3: Dodaj nową książkę, wprowadzając poprawne informacje.  

- Krok 4: Zweryfikuj obecność nowej pozycji w katalogu.  


### 2. Scenariusz testowy: Proces usuwania książki  

- Krok 1: Zaloguj się do systemu biblioteki.  

- Krok 2: Przejdź do panelu administratora.  

- Krok 3: Wybierz książkę do usunięcia i potwierdź operację.  

- Krok 4: Sprawdź, czy książka została usunięta z katalogu i czy wszelkie rezerwacje zostały anulowane.  


### 3 .Scenarusz testowy: Proces rezerwacji książki  

- Krok 1: Zaloguj się na konto użytkownika.  

- Krok 2: Przeszukaj katalog i wybierz dostępną książkę.  

- Krok 3: Rezerwuj książkę i sprawdź potwierdzenie rezerwacji.  

- Krok 4: Zweryfikuj, czy status książki zmienił się na "zarezerwowana".  

 
### 4.Scenariusz testowy :  Usuwanie użytkownika z bazy danych 

- Krok 1 : Logowanie od systemu ( admin ) 

- Krok 2 : Wyszukanie użytkownika 

- Krok 3 : Wybór opcji usuwania 

- Krok 4 : Potwierdzenie usunięcia 


### 5.Scenariusz testowy: Sprawdzanie historii wypożyczeń  

- Krok 1: Zaloguj się na konto użytkownika.  

- Krok 2: Przejdź do sekcji historii wypożyczeń.  

- Krok 3: Zweryfikuj, czy wyświetlane są wszystkie poprzednie wypożyczenia i rezerwacje, zarówno udane, jak i anulowane.  

 
### 6.Scenariusz testowy: Logowanie z błędnym loginem  

- Krok 1: Wpisz błędny login  

- Krok 2: Wpisz hasło  

- Krok 3: Zweryfikuj, czy nie ma możliwości logowania przy podaniu złego loginu  

 
### 7.Scenariusz testowy: Logowanie z błędnym hasłem  

- Krok 1: Wpisz login  

- Krok 2: Wpisz błędne hasło  

- Krok 3: Zweryfikuj, czy nie ma możliwości logowania przy podaniu złego hasła  

 
### 8.Scenariusz testowy: Wyszukanie książki, której nie ma  

- Krok 1: Zaloguj się na konto użytkownika  

- Krok 2: W panelu wyszukiwania wpisz nazwę książki, której nie ma w bibliotece  

- Krok 3: Zweryfikuj czy wyświetla się informacja o braku książki w bibliotece  

 
### 9.Scenariusz testowy: Rezerwacja książki, która jest już zarezerwowana  

- Krok 1: Zaloguj się na konto użytkownika  

- Krok 2: Wyszukaj książkę , która jest już zarezerwowana  

- Krok 3: Zweryfikuj, czy nie masz możliwości zarezerwowania tej książki      

 
### 10.Scenariusz testowy : Zmiana nazwy użytkownika 

- Krok 1 : Logowanie do systemu 

- Krok 2 : Przejście do ustawień konta 

- Krok 3 : Zmiana nazwy 

- Krok 4 : Zapisanie zmian 


### 11. Scenariusz testowy : Zmiana hasła 

- Krok 1 : Logowanie do systemu 

- Krok 2 : Przejście do ustawień konta 

- Krok 3 : Zmiana hasła 

- Krok 4 : Zapisanie zmian 

 
### 12.Scenariusz testowy: Test wyszukiwania książki, która jest w bibliotece 

- Krok 1: Zaloguj się na konto użytkownika 

- Krok 2: W panelu wyszukiwania wpisz nazwę książki, która jest w bibliotece 

- Krok 3: Zweryfikuj, czy wyświetla się informacja o książce 

 
### 13.Scenariusz testowy: Test sprawdzający poprawność adresu e-mail: 

- Krok 1: Spróbuj dodać błędny adres e-mail do otrzymywania powiadomień. 

- Krok 2: Zweryfikuj, czy system odpowiednio reaguje i nie zapisuje niepoprawnego adresu e-mail. 

 
### 14.Scenariusz testowy: Test oceny bez dodawania recenzji: 

- Krok 1: Oceń książkę, nie dodając przy tym recenzji. 

- Krok 2: Sprawdź, czy system poprawnie obsługuje ocenę bez recenzji. 

 
### 15. Scenariusz testowy : Zmiana adresu e-mail 

- Krok 1 : Logowanie do systemu 

- Krok 2 : Przejście do ustawień konta 

- Krok 3 : Zmiana adresu e-mail 

- Krok 4 : Zapisanie zmian 


### 16.Scenariusz testowy: Test dodawania kategorii do książki: 

- Krok 1: Dodaj nową kategorię do książki. 

- Krok 2: Zweryfikuj, czy kategoria została dodana poprawnie. 

 
### 17.Scenariusz testowy: Test dodawania nowej kategorii: 

- Krok 1: Dodaj nową kategorię do systemu. 

- Krok 2: Przyporządkuj książkę do nowej kategorii. 

- Krok 3: Zweryfikuj, czy kategoria została dodana i przyporządkowana poprawnie. 

 
### 18. Scenariusz testowy : Zmiana tytułu książki 

- Krok 1 : Przejście do zarządzania książkami 

- Krok 2 : Wybór danej książki 

- Krok 3 : Zmiana tytułu książki  

- Krok 4 : Zatwierdzenie zmian 

 
### 19.Scenariusz testowy: Test śledzenia ilości wypożyczonych książek: 

- Krok 1: Wypożycz kilka książek. 

- Krok 2: Sprawdź, czy historia aktywności pokazuje poprawną ilość wypożyczonych książek. 

 
### 20. Scenariusz testowy : Test Zmiana autora książki 

- Krok 1 : Przejście do zarządzania książkami 

- Krok 2 : Wybór danej książki 

- Krok 3 : Zmiana autora książki 

- Krok 4 : Zatwierdzenie zmian 

 
### 21.Scenariusz testowy: Test usuwania recenzji: 

- Krok 1: Dodaj recenzję do książki. 

- Krok 2: Usuń swoją recenzję. 

- Krok 3: Zweryfikuj, czy recenzja została poprawnie usunięta. 

### 22. Scenariusz testowy : Usunięcie pozycji z historii 

- Krok 1 : Przejście do historii 

- Krok 2 : Wybór pozycji do usunięcia 

- Krok 3 : Usunięcie danej pozycji 

- Krok 4 : Zatwierdzenie zmian 

### 23. Scenariusz testowy : Usuń ocenę 

- Krok 1 : Przejście do ocen 

- Krok 2 : Wybór pozycji do usunięcia 

- Krok 3 : Usunięcie danej pozycji 

- Krok 4 : Zatwierdzenie zmian 

### 24. Scenariusz testowy : Zmień ocenę 

- Krok 1 : Przejście do ocen 

- Krok 2 : Wybór oceny, którą chcemy zmienić  

- Krok 3 : Zmiana oceny 

- Krok 4 : Zatwierdzenie zmian 

### 25. Scenariusz testowy : Dodaj recenzje 

- Krok 1 : Przejście do recenzji 

- Krok 2 : Wybór pozycji, którą chcemy zrecenzować 

- Krok 3 : Dodanie recenzji 

- Krok 4 : Zatwierdzenie recenzji 

### 26. Scenariusz testowy : Zmień recenzje 

- Krok 1 : Przejście do recenzji 

- Krok 2 : Wybór recenzji, którą chcemy zmienić  

- Krok 3 : Zmiana recenzji 

- Krok 4 : Zatwierdzenie zmian 

### 27. Scenariusz testowy : Wyszukanie książki o konkretnej kategorii 

- Krok 1 : Przejście do wyszukiwania 

- Krok 2 : Wyprowadzenie do kryteriów wyszukiwania konkretnej kategorii 

- Krok 3 : Zastosowanie filtra i wyszukanie pozycji 

### 28. Scenariusz testowy : Dodawanie się do listy oczekujących: 

- Krok 1: Znajdź popularną książkę. 

- Krok 2: Dodaj się do listy oczekujących. 

- Krok 3: Zweryfikuj, czy użytkownik jest dodany na listę oczekujących. 

 
### 29. Scenariusz testowy : Usuwanie z listy oczekujących: 
  

- Krok 1: Wejdź na listę oczekujących. 

- Krok 2: Usuń się z listy oczekujących. 

- Krok 3: Zweryfikuj, czy użytkownik nie otrzymuje powiadomienia o dostępności książki. 

 
### 30. Scenariusz testowy : Wyświetlanie wszystkich wypożyczonych 

 
- Krok 1 : Wejdź na swój profil 

- Krok 2 : Przejdź do zakładki wypożyczone 

- Krok 3 : Sprawdź czy w zakładce wyświetlają się wszystkie pozycje, które są aktualnie przez
