package net.braniumacademy.ex742;

/**
 * Interface chứa các hành động có thể thực hiện với một tài khoản ngân hàng
 */
public interface Exchangeable {
    // chuyển tiền
    void transfer(BankAccount acc, long amount) throws InvalidAmountException;
    // trả hóa đơn
    void payBill(long amount) throws InvalidAmountException;
    // nạp tiền
    void deposit(long amount) throws InvalidAmountException;
    // rút tiền
    void withdraw(long amount) throws InvalidAmountException;
}
