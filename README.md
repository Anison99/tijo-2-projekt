# Kurs: Testowanie i Jakość Oprogramowania (Projekt)

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
Krok 1: Zaloguj się do systemu biblioteki. 
Krok 2: Przejdź do panelu administratora. 
Krok 3: Wybierz książkę do usunięcia i potwierdź operację. 
Krok 4: Sprawdź, czy książka została usunięta z katalogu i czy wszelkie rezerwacje zostały anulowane. 

### 3 .Scenarusz testowy: Proces rezerwacji książki 
Krok 1: Zaloguj się na konto użytkownika. 
Krok 2: Przeszukaj katalog i wybierz dostępną książkę. 
Krok 3: Rezerwuj książkę i sprawdź potwierdzenie rezerwacji. 
Krok 4: Zweryfikuj, czy status książki zmienił się na "zarezerwowana". 

### 4.Scenariusz testowy: Proces przedłużenia rezerwacji 
Krok 1: Zaloguj się na konto użytkownika z zarezerwowaną książką. 
Krok 2: Przejdź do sekcji historii wypożyczeń. 
Krok 3: Wybierz opcję przedłużenia rezerwacji i sprawdź, czy nowa data zwrotu została zaktualizowana. 

### 5.Scenariusz testowy: Sprawdzanie historii wypożyczeń 
Krok 1: Zaloguj się na konto użytkownika. 
Krok 2: Przejdź do sekcji historii wypożyczeń. 
Krok 3: Zweryfikuj, czy wyświetlane są wszystkie poprzednie wypożyczenia i rezerwacje, zarówno udane, jak i anulowane. 

### 6.Scenariusz testowy: Logowanie z błędnym loginem 
Krok 1: Wpisz błędny login 
Krok 2: Wpisz hasło 
Krok 3: Zweryfikuj, czy nie ma możliwości logowania przy podaniu złego loginu 

### 7.Scenariusz testowy: Logowanie z błędnym hasłem 
Krok 1: Wpisz login 
Krok 2: Wpisz błędne hasło 
Krok 3: Zweryfikuj, czy nie ma możliwości logowania przy podaniu złego hasła 

### 8.Scenariusz testowy: Wyszukanie książki, której nie ma 
Krok 1: Zaloguj się na konto użytkownika 
Krok 2: W panelu wyszukiwania wpisz nazwę książki, której nie ma w bibliotece 
Krok 3: Zweryfikuj czy wyświetla się informacja o braku książki w bibliotece 

### 9.Scenariusz testowy: Rezerwacja książki, która jest już zarezerwowana 
Krok 1: Zaloguj się na konto użytkownika 
Krok 2: Wyszukaj książkę , która jest już zarezerwowana 
Krok 3: Zweryfikuj, czy nie masz możliwości zarezerwowania tej książki	 

### 10.Scenariusz testowy: Rejestracja już istniejącym mailem 
Krok 1: Przejdź do zakładki rejestracji 
Krok 2: Wypełnij pola e-mail istniejącym już w bazie e-mailem 
Krok 3: Wypełnij pozostałe pola formularza 
Krok 4: Zweryfikuj czy po próbie stworzenia konta wyświetla się informacja o tym, że konto z 	podanym adresem już istnieje	 

### 11.Scenariusz testowy: Przypominanie o terminie zwrotu
Krok 1: Zaloguj się na konto użytkownika.
Krok 2: Sprawdź listę wypożyczonych książek.
Krok 3: Upewnij się, że system wyświetla powiadomienie o zbliżającym się terminie zwrotu książki.
Krok 4: Zweryfikuj, czy powiadomienie jest wysyłane z odpowiednim wyprzedzeniem.
