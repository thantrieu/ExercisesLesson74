package net.braniumacademy.ex742;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // TODO: thêm menu chỗ này
    }

    /**
     * phương thức nhập vào và thực hiện thanh toán hóa đơn của một
     * tài khoản nào đó nếu đầu vào là hợp lệ
     *
     * @param account1 tài khoản nguồn
     * @param input    đối tượng của lớp Scanner
     * @throws InvalidAmountException ngoại lệ có thể xảy ra khi giao dịch
     */
    private static void payTheBill(BankAccount account1, Scanner input)
            throws InvalidAmountException {
        System.out.println("Nhập số tiền cần thanh toán: ");
        var amountStr = input.nextLine().trim(); // nhập vào loại bỏ dấu cách thừa
        if (amountStr.matches("\\d+")) {
            var amount = Long.parseLong(amountStr);
            System.out.println("Số dư ban đầu: " + account1.getBallance());
            try {
                account1.payBill(amount);
            } catch (InvalidAmountException e) {
                e.printStackTrace();
                System.out.println("Giao dịch thất bại");
            }
        } else { // nếu số tiền nhập vào không phải số
            throw new InvalidAmountException("Số tiền giao dịch không hợp lệ", 0);
        }
    }

    /**
     * phương thức này dùng để thực hiện việc chuyển khoản giữa hai tài khoản A, B
     * việc thực hiện sẽ thành công nếu số tiền hợp lệ và ngược lại
     *
     * @param account1 tài khoản nguồn
     * @param account2 tài khoản đích
     * @param input    đầu vào nhập liệu
     * @throws InvalidAmountException ngoại lệ có thể xảy ra khi giao dịch
     */
    private static void transfer(BankAccount account1,
                                 BankAccount account2, Scanner input)
            throws InvalidAmountException {
        System.out.println("Nhập số tiền cần chuyển: ");
        var amountStr = input.nextLine().trim(); // nhập vào loại bỏ dấu cách thừa
        if (amountStr.matches("\\d+")) {
            var amount = Long.parseLong(amountStr);
            try {
                System.out.println("Số dư ban đầu: " + account1.getBallance());
                account1.transfer(account2, amount);
            } catch (InvalidAmountException e) {
                e.printStackTrace();
                System.out.println("Giao dịch thất bại");
            }
        } else { // nếu số tiền nhập vào không phải số
            throw new InvalidAmountException("Số tiền giao dịch không hợp lệ", 0);
        }
    }

    /**
     * phương thức thực hiện việc nhập vào số tiền cần giao dịch
     * và rút số tiền này khỏi tài khoản cho trước
     *
     * @param account1 tài khoản cần rút tiền
     * @param input    đối tượng của lớp Scanner
     * @throws InvalidAmountException ngoại lệ xảy ra nếu số tiền không hợp lệ
     */
    private static void widthdraw(BankAccount account1, Scanner input)
            throws InvalidAmountException {
        System.out.println("Nhập số tiền cần rút: ");
        var amountStr = input.nextLine().trim(); // nhập vào loại bỏ dấu cách thừa
        if (amountStr.matches("\\d+")) {
            var amount = Long.parseLong(amountStr);
            try {
                System.out.println("Số dư ban đầu: " + account1.getBallance());
                account1.withdraw(amount);
            } catch (InvalidAmountException e) {
                e.printStackTrace();
                System.out.println("Giao dịch thất bại");
            }
        } else { // nếu số tiền nhập vào không phải số
            throw new InvalidAmountException("Số tiền giao dịch không hợp lệ", 0);
        }
    }

    /**
     * phương thức thực hiện việc nhập vào số tiền cần nạp
     * và nạp số tiền đó vào tài khoản nếu hợp lệ
     *
     * @param account1 tài khoản cần nạp
     * @param input    đối tượng của lớp Scanner
     * @throws InvalidAmountException ngoại lệ xảy ra nếu số tiền không hợp lệ
     */
    private static void deposit(BankAccount account1, Scanner input)
            throws InvalidAmountException {
        System.out.println("Nhập số tiền cần nạp vào tài khoản: ");
        var amountStr = input.nextLine().trim(); // nhập vào loại bỏ dấu cách thừa
        if (amountStr.matches("\\d+")) {
            var amount = Long.parseLong(amountStr);
            try {
                System.out.println("Số dư ban đầu: " + account1.getBallance());
                account1.deposit(amount);
            } catch (InvalidAmountException e) {
                e.printStackTrace();
                System.out.println("Giao dịch thất bại");
            }
        } else { // nếu số tiền nhập vào không phải số
            throw new InvalidAmountException("Số tiền giao dịch không hợp lệ", 0);
        }
    }
}
