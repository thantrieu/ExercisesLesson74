package net.braniumacademy.ex742;

import java.io.Serializable;
import java.util.regex.Pattern;

public class BankAccount implements Serializable, Exchangeable {
    private String cardNumber; // số thẻ
    private String accNumber; // số tài khoản
    private String owner; // chủ sở hữu
    private long ballance; // số dư

    public BankAccount() {
    }

    public BankAccount(String accNumber) {
        this.accNumber = accNumber;
    }

    public BankAccount(String cardNumber, String accNumber,
                       String owner, long ballance)
            throws InvalidAmountException {
        this.cardNumber = cardNumber;
        this.accNumber = accNumber;
        this.owner = owner;
        this.setBallance(ballance);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getBallance() {
        return ballance;
    }

    public void setBallance(long ballance) throws InvalidAmountException {
        if (isValid(ballance)) {
            this.ballance = ballance;
        } else { // ngược lại ta văng ngoại lệ
            var msg = "Số tiền giao dịch không hợp lệ: " + ballance;
            throw new InvalidAmountException(msg, ballance);
        }
    }

    // nạp tiền
    public void deposit(long amount) throws InvalidAmountException {
        if (isValid(amount)) {
            ballance += amount;
            System.out.println("Giao dịch nạp tiền vào tài khoản \"" +
                    accNumber + "\" hoàn tất!");
        } else {
            var msg = "Số tiền giao dịch không hợp lệ: " + amount;
            throw new InvalidAmountException(msg, amount);
        }
    }

    // rút tiền
    public void withdraw(long amount) throws InvalidAmountException {
        // nếu số tiền cần chuyển hợp lệ và nhỏ hơn hoặc bằng số dư
        if (isValid(amount) && amount <= ballance) {
            ballance -= amount; // trừ tiền đã giao dịch
            System.out.println("Giao dịch rút tiền hoàn tất!");
        } else { // ngược lại ta văng ngoại lệ
            var msg = "Số tiền giao dịch không hợp lệ: " + amount;
            throw new InvalidAmountException(msg, amount);
        }
    }

    // chuyển tiền
    public void transfer(BankAccount acc, long amount)
            throws InvalidAmountException {
        // nếu số tiền cần chuyển hợp lệ và nhỏ hơn hoặc bằng số dư
        if (isValid(amount) && amount <= ballance) {
            ballance -= amount; // trừ tiền ở tài khoản gửi
            acc.deposit(amount); // thêm tiền vào tk thụ hưởng
            System.out.println("Giao dịch chuyển khoản hoàn tất!");
        } else { // ngược lại ta văng ngoại lệ
            var msg = "Số tiền giao dịch không hợp lệ: " + amount;
            throw new InvalidAmountException(msg, amount);
        }
    }

    // thanh toán hóa đơn
    public void payBill(long amount) throws InvalidAmountException {
        // nếu số tiền cần chuyển hợp lệ và nhỏ hơn hoặc bằng số dư
        if (isValid(amount) && amount <= ballance) {
            ballance -= amount; // trừ tiền đã giao dịch
            System.out.println("Thanh toán hóa đơn thành công!");
        } else { // ngược lại ta văng ngoại lệ
            var msg = "Số tiền giao dịch không hợp lệ: " + amount;
            throw new InvalidAmountException(msg, amount);
        }
    }

    // phương thức helper
    private boolean isValid(long amount) {
        var regex = "\\d{1,9}"; // tối đa 9 chữ số
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(amount + "");
        return matcher.matches();
    }
}
