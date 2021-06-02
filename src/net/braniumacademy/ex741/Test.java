package net.braniumacademy.ex741;

import net.braniumacademy.ex741.sorting.AgeSortingASC;
import net.braniumacademy.ex741.sorting.AgeSortingDESC;
import net.braniumacademy.ex741.sorting.NameSortingASC;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        var fileName = "CAT.DAT";
        var cats = new ArrayList<Cat>();
        cats.addAll(readCatFromFile(fileName));
        var input = new Scanner(System.in);
        var choice = 0;
        do {
            System.out.println("============================ MENU ============================");
            System.out.println("1. Thêm một mèo mới vào danh sách.");
            System.out.println("2. Sắp xếp mèo trong danh sách theo tuổi tăng dần.");
            System.out.println("3. Sắp xếp mèo trong danh sách theo tuổi giảm dần.");
            System.out.println("4. Sắp xếp mèo trong danh sách theo tên a-z.");
            System.out.println("5. Tìm kiếm mèo theo tên gần đúng.");
            System.out.println("6. Hiển thị danh sách mèo ra màn hình.");
            System.out.println("7. Ghi danh sách mèo vào file.");
            System.out.println("0. Thoat chương trình.");
            System.out.println("Bạn chọn? ");
            choice = input.nextInt();
            input.nextLine(); // đọc bỏ các kí tự thừa sau khi đọc số
            switch (choice) {
                case 0:
                    System.out.println("<== Xin chào và hẹn gặp lại quý khách ==>");
                    break;
                case 1:
                    var cat = createNewCat(input);
                    cats.add(cat);
                    break;
                case 2:
                    showHeader();
                    sortCatsByAgeASC(cats);
                    showCatsOnScreen(cats);
                    break;
                case 3:
                    showHeader();
                    sortCatsByAgeDESC(cats);
                    showCatsOnScreen(cats);
                    break;
                case 4:
                    showHeader();
                    sortCatsByNameAZ(cats);
                    showCatsOnScreen(cats);
                    break;
                case 5:
                    var name = input.nextLine();
                    searchCatByName(cats, name);
                    break;
                case 6:
                    showCatsOnScreen(cats);
                    break;
                case 7:
                    writeEmpToFile(cats, fileName);
                    break;
            }
        } while (choice != 0);
    }

    private static void showHeader() {
        System.out.println("================== DANH SÁCH MÈO SAU KHI SẮP XẾP ==================");
    }

    private static void showCatsOnScreen(ArrayList<Cat> cats) {
        System.out.printf("%-25s%-20s%-20s%-25s",
                "Tên gọi", "Tuổi", "Màu lông", "Thức ăn yêu thích");
        for (var item : cats) {
            showPetInfo(item);
        }
    }

    private static void searchCatByName(ArrayList<Cat> cats, String name) {
        System.out.println("<== KẾT QUẢ TÌM KIẾM ==>");
        var counter = 0; // đếm xem tìm thấy bao nhiêu kết quả
        for (var item : cats) {
            if (item.getPetName().matches("%" + name + "%")) {
                showPetInfo(item);
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("<== KHÔNG TÌM THẤY KẾT QUẢ NÀO ==>");
        }
    }

    /**
     * Phương thức này dùng để ghi thông tin mèo vào file nhị phân
     *
     * @param cats     chứa danh sách mèo cần ghi file
     * @param fileName tên file đích
     * @return kết quả ghi file thành công: true hoặc thất bại false
     */
    private static boolean writeEmpToFile(List<Cat> cats, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cats);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Phương thức dùng để đọc dữ liệu của mèo từ file
     *
     * @param fileName là tên file cần đọc
     * @return danh sách mèo đọc được hoặc rỗng nếu file rỗng
     */
    private static List<Cat> readCatFromFile(String fileName) {
        List<Cat> cats = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            var mObj = ois.readObject();
            if (mObj != null) {
                cats = (ArrayList<Cat>) mObj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cats;
    }

    private static void sortCatsByNameAZ(ArrayList<Cat> cats) {
        cats.sort(new NameSortingASC());
    }

    private static void sortCatsByAgeDESC(ArrayList<Cat> cats) {
        cats.sort(new AgeSortingDESC());
    }

    private static void sortCatsByAgeASC(ArrayList<Cat> cats) {
        cats.sort(new AgeSortingASC());
    }

    private static Cat createNewCat(Scanner input) {
        System.out.println("Nhập tên của mèo: ");
        var name = input.nextLine();
        Cat myCat = new Cat(name);
        System.out.println("Nhập tuổi của mèo: ");
        var ageStr = input.nextLine().trim();
        var regex = "\\d+";
        if (ageStr.matches(regex)) {
            var age = Integer.parseInt(ageStr);
            try {
                myCat.setAge(age);
            } catch (InvalidAgeException e) {
                e.printStackTrace();
                System.out.println("Tuổi hợp lệ phải từ 0-50.");
            }
        } else {
            try {
                myCat.setAge(0);
            } catch (InvalidAgeException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Màu lông: ");
        var color = input.nextLine();
        System.out.println("Màu mắt: ");
        var eyesColor = input.nextLine();
        System.out.println("Thức ăn yêu thích: ");
        var favFood = input.nextLine();
        myCat.setColor(color);
        myCat.setEyesColor(eyesColor);
        myCat.setFavoriteFood(favFood);
        return myCat;
    }

    private static void showPetInfo(Cat myCat) {
        System.out.printf("%-25s%-20d%-20s%-25s",
                myCat.getPetName(), myCat.getAge(),
                myCat.getEyesColor(), myCat.getFavoriteFood());
    }
}