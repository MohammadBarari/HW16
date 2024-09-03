package org.example.repository.bill;

import org.example.domain.Bill;

public interface BillRepository {
    Bill findById(Integer id);
    Bill update(Bill bill);
}
