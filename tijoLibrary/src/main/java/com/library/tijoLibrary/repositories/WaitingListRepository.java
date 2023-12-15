package com.library.tijoLibrary.repositories;

import com.library.tijoLibrary.models.WaitingList;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingListRepository {
    void save(WaitingList waitingListEntry);
}
