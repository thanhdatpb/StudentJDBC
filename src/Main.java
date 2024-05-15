import dao.StudentDao;
import entity.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDao dao = new StudentDao();
        int choose = 0;
        while (true) {
            System.out.println("\n\n\n1.List student");
            System.out.println("2.Add student");
            System.out.println("3.Update student");
            System.out.println("4.Delete student by id");
            System.out.println("5.Exit\n\n");
            System.out.println("Choose:");
            choose = Integer.parseInt(scanner.nextLine());
            if (choose == 5) {
                System.out.println("Bye Bye");
                break;
            }
            switch (choose) {
                case 1:
                    dao.findAll().forEach(student ->  System.out.println(student));
                    break;
                case 2:
                    Student student = new Student();

                    System.out.println("Name:");
                    student.setName(scanner.nextLine());
                    System.out.println("Age:");
                    student.setAge(Integer.parseInt(scanner.nextLine()));

                    if (dao.add(student)) {
                        System.out.println("Successfully");
                    }else {
                        System.out.println("Create failed! Try again");
                    }
                    break;
                case 3:
                    System.out.println("Enter id to update:");
                    Student s = dao.findById(Integer.parseInt(scanner.nextLine()));
                    if (s != null) {
                        System.out.println("Not Found");
                        break;
                    }
                    while (true){
                        System.out.println(s);
                        System.out.println("1.Update name");
                        System.out.println("2.Update age");
                        System.out.println("3.Save student");
                        int choise = Integer.parseInt(scanner.nextLine());
                        if (choise == 1) {
                            System.out.println("Name:");
                            s.setName(scanner.nextLine());
                        }
                        if (choise == 2) {
                            System.out.println("Age:");
                            s.setAge(Integer.parseInt(scanner.nextLine()));
                        }
                        if (choise == 3) {
                            dao.update(s);
                            System.out.println("Saved");
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter id to Delete");
                    if (dao.delete(Integer.parseInt(scanner.nextLine()))) {
                        System.out.print("Delete Successfully");
                    }else {
                        System.out.println("Delete failed because not find student this id");
                    }
                    break;
            }
        }


//      Student student = new Student();
//      student.setId(1);
//      student.setName("Thanh Dat");
        //       student.setAge(21);
        //       if (dao.add(student)) {
        //           System.out.println("Add Student Successfully");
        //       }else {
        //           System.out.println("Add failed");
        //       }

        //        System.out.println(dao.findById(20));
        //
        //        Student thanhdat = dao.findById(1);
        //        if (thanhdat != null) {
        //            thanhdat.setAge(22);
        //        }
        //        if (dao.update(thanhdat)) {
        //            System.out.println("Updated Successfully");
        //        }else {
        //            System.out.println("Update Failed");
        //        }

//        if (dao.delete(3)) {
//            System.out.println("Delete success");
//        } else {
//            System.out.println("Delete failed");
//        }
    }
}