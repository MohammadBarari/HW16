package org.example.service.Bill.Imp;

import org.example.domain.Bill;
import org.example.repository.bill.BillRepository;
import org.example.repository.bill.imp.BillRepositoryImp;
import org.example.service.Bill.BillService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BillServiceImp implements BillService {

    BillRepository billRepository;

    public BillServiceImp() {
        billRepository = new BillRepositoryImp();
    }

    public void payBill(Integer id) {
        Bill bill = findById(id);
        bill.setIsPaid(true);
        bill.setTimeOfPayment(LocalDateTime.now());
        updateBill(bill);
    }

    @Override
    public Bill findById(  Integer id) {
       return billRepository.findById(id);
    }

    @Override
    public Bill updateBill(Bill bill) {
        billRepository.update(bill);
        return bill;
    }

    @Override
    public Set<Bill> billsCalculator(Long price, LocalDate dateOfGet) {
        Long eachYear = price/5;
        Set<Bill> bills = new HashSet<>();
        List<Long> eachyears = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            eachyears.add(eachYear);
        }
        eachyears.add(eachYear + (price - eachYear * 5));
        //todo : it can goes into a method with long input and list<Long> output
        LocalDate localDateBill =dateOfGet;
        for (int i = 1; i <= 5; i++) {
            //todo: profit still has not been calculated
            Long eachMonth = eachyears.get(i)/12 * 2;
            for (int j = 0; j < 7; j++) {
                //it should be in method
                Bill bill = new Bill();
                bill.setAmount(eachMonth);
                localDateBill = localDateBill.plusDays(30);
                bill.setExpiresDate(localDateBill);
                bill.setIsPaid(false);
                bills.add(bill);
                //todo : should be in new method with List<bill> output and localdatestart and days input;
            }
            for (int j = 0; j < 4; j++) {
                Bill bill = new Bill();
                bill.setAmount(eachMonth);
                localDateBill = localDateBill.plusDays(31);
                bill.setExpiresDate(localDateBill);
                bill.setIsPaid(false);
                bills.add(bill);
            }
            {
                Bill bill = new Bill();
                bill.setAmount(eachMonth +(eachyears.get(i) - eachMonth*12));
                localDateBill = localDateBill.plusDays(31);
                bill.setExpiresDate(localDateBill);
                bills.add(bill);
            }
        }
        return bills;
    }
}
