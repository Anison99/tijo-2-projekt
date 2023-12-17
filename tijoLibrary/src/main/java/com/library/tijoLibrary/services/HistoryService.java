package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.History;
import com.library.tijoLibrary.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    // Wyświetlenie historii wypożyczeń użytkownika
    public List<History> getUserLoanHistory(Long userId) {
        return historyRepository.findByUserId(userId);
    }

    // Usunięcie pozycji z historii
    public void deleteHistoryEntry(Long historyId) {
        historyRepository.deleteById(historyId);
    }
}