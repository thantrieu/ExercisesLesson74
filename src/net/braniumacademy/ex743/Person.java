package net.braniumacademy.ex743;

import net.braniumacademy.ex743.exceptions.InvalidEmailException;
import net.braniumacademy.ex743.exceptions.InvalidIdentityNumberException;
import net.braniumacademy.ex743.exceptions.InvalidNameException;
import net.braniumacademy.ex743.exceptions.InvalidPhoneNumberException;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * lớp mô tả thông tin người
 */
public abstract class Person implements Actionable,
        Serializable, Comparable<Person> {
    private String id; // số chứng minh thư/căn cước
    private FullName fullName; // họ tên đầy đủ
    private String address; // địa chỉ
    private Date dateOfBirth; // ngày tháng năm sinh
    private String email; // email
    private String phoneNumber; // số điện thoại

    public Person() {
    }

    public Person(String id, String fullName, String address,
                  Date dateOfBirth, String email, String phoneNumber)
            throws InvalidNameException, InvalidPhoneNumberException,
            InvalidEmailException, InvalidIdentityNumberException {
        this.setId(id);
        this.setFullName(fullName);
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    // sắp xếp theo mã chứng minh thư tăng dần
    @Override
    public int compareTo(Person other) {
        return id.compareTo(other.id);
    }

    public void eat(String food) {
        System.out.println("Người đang ăn " + food);
    }

    public void sleep() {
        System.out.println("Người đang ngủ");
    }

    public void speak() {
        System.out.println("Người đang nói chuyện");
    }

    public void relax(String thing) {
        System.out.println("Người đang giải trí bằng " + thing);
    }

    public final String getId() {
        return id;
    }

    public final void setId(String id) throws InvalidIdentityNumberException {
        var regex = "^([A-Z0-9]){9,13}$";
        if (id.matches(regex)) {
            this.id = id;
        } else {
            var msg = "Số chứng minh/căn cước không hợp lệ: " + id;
            throw new InvalidIdentityNumberException(msg, id);
        }
    }

    /**
     * phương thức này trả về một fullname đầy đủ
     * ở dạng String
     *
     * @return một string tên-đệm-họ
     */
    public final String getFullNameString() {
        return fullName.last + " " + fullName.mid + fullName.first;
    }

    /**
     * phương thức lấy đối tượng của FullName
     *
     * @return đối tượng fullName
     */
    public final FullName getFullName() {
        return fullName;
    }

    // thiết lập họ và tên
    public final void setFullName(String fullName) throws InvalidNameException {
        this.fullName = new FullName();
        if (fullName != null && fullName.length() > 0) {
            var regex = "^(\\w+.*[^0-9].*){2,30}$";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(fullName);
            if (!matcher.matches()) {
                var msg = "Họ tên không hợp lệ: " + fullName;
                throw new InvalidNameException(msg, fullName);
            }
            var words = fullName.split("\\s+"); // tách tại vị trí có dấu cách
            this.fullName.first = words[words.length - 1];
            this.fullName.last = words[0];
            this.fullName.mid = "";
            for (int i = 1; i < words.length - 1; i++) {
                this.fullName.mid += words[i] + " ";
            }
        }
    }

    public final String getAddress() {
        return address;
    }

    public final void setAddress(String address) {
        this.address = address;
    }

    public final Date getDateOfBirth() {
        return dateOfBirth;
    }

    public final void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) throws InvalidEmailException {
        var regex = "^[a-z]+[a-z0-9._]*@gmail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            this.email = email;
        } else {
            var msg = "Email không hợp lệ: " + email;
            throw new InvalidEmailException(msg, email);
        }
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final void setPhoneNumber(String phoneNumber)
            throws InvalidPhoneNumberException {
        // định dạng số điện thoại
        var regex = "^((08|09)\\d{7})|((03|04|07)\\d{8})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            this.phoneNumber = phoneNumber;
        } else {
            var msg = "Số điện thoại không hợp lệ: " + phoneNumber;
            throw new InvalidPhoneNumberException(msg, phoneNumber);
        }
    }

    public class FullName implements Serializable {
        private String first;
        private String last;
        private String mid;

        public FullName() {
        }

        public FullName(String first, String last, String mid) {
            this.first = first;
            this.last = last;
            this.mid = mid;
        }

        public final String getFirst() {
            return first;
        }

        public final void setFirst(String first) {
            this.first = first;
        }

        public final String getLast() {
            return last;
        }

        public final void setLast(String last) {
            this.last = last;
        }

        public final String getMid() {
            return mid;
        }

        public final void setMid(String mid) {
            this.mid = mid;
        }
    }
}
