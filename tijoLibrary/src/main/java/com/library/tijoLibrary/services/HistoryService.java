package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.History;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    // Wyświetlenie historii wypożyczeń użytkownika
    public List<History> getUserLoanHistory(Long userId) {
        return null;
    }

    // Usunięcie pozycji z historii
    public void deleteHistoryEntry(Long historyId) {
    }
}