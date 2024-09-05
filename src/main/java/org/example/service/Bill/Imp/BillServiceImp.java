package org.example.service.Bill.Imp;

import org.example.domain.Bill;
import org.example.exeptions.PayTheBillError;
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

    public void payBill(Integer id) throws PayTheBillError {
        try {
            Bill bill = findById(id);
            bill.setIsPaid(true);
            bill.setTimeOfPayment(LocalDateTime.now());
            updateBill(bill);
        }catch (Exception e){
            throw new PayTheBillError();
        }
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
    public Set<Bill > billsCalculator(Long price, LocalDate timeOfGet) {
        long total = 0L;
        Long eachYear = price/5;
        Set<Bill> bills = new HashSet<>();
        List<Long> eachyears = calculateProfit(price);
        //todo : it can goes into a method with long input and list<Long> output
        LocalDate localDateBill =timeOfGet;
        for (int i = 0; i < 5; i++) {
            Long eachMounth = eachyears.get(i)/12;
            for (int j = 0; j < 7; j++) {
                //it should be in method
                Bill bill = new Bill();
                total += eachMounth;
                bill.setAmount(eachMounth);
                localDateBill = localDateBill.plusDays(30);
                bill.setExpiresDate(localDateBill);
                bill.setIsPaid(false);
                bills.add(bill);
                //todo : should be in new method with List<bill> output and localdatestart and days input;
            }
            for (int j = 0; j < 4; j++) {
                Bill bill = new Bill();
                total += eachMounth;
                bill.setAmount(eachMounth);
                localDateBill = localDateBill.plusDays(31);
                bill.setExpiresDate(localDateBill);
                bill.setIsPaid(false);
                bills.add(bill);
            }
            {
                Bill bill = new Bill();
                total += eachMounth +(eachyears.get(i) - eachMounth*12);
                bill.setAmount(eachMounth +(eachyears.get(i) - eachMounth*12));
                localDateBill = localDateBill.plusDays(31);
                bill.setExpiresDate(localDateBill);
                bill.setIsPaid(false);
                bills.add(bill);
            }
        }
        System.out.println(total + " is felan :::");
        return bills;
    }
    private List<Long> calculateProfit(Long input){
        long withProfit = (long) (input + (input * 0.04));
        long x = withProfit/31;
        long total = 0L;
        List<Long> eachyears = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            total += (x * (long) Math.pow(2,i));
            eachyears.add(x * (long) Math.pow(2,i));
        }
        Long lastYear = withProfit - total;
        eachyears.add(lastYear);
        return eachyears;
    }
}
