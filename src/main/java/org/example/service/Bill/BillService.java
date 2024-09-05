package org.example.service.Bill;

import org.example.domain.Bill;
import org.example.exeptions.PayTheBillError;
import org.example.service.Bill.Imp.BillServiceImp;

import java.time.LocalDate;
import java.util.Set;

public interface BillService {
    void payBill(Integer id) throws PayTheBillError;
    Bill findById(Integer id);
    Bill updateBill(Bill bill);
    Set<Bill> billsCalculator(Long price , LocalDate dateOfGet);
}
