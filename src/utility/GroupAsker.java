package utility;


import data.Coordinates;
import data.FormOfEducation;
import data.Person;
import data.Semester;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import run.App;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Asks a user a group's value.
 */

public class GroupAsker {

    private final long MIN_HEIGHT = 0;

    private final double MIN_WEIGHT = 0;

    private final int MAX_LENGTH = 23;

    private final int MIN_LENGTH = 5;

    private final long MIN_GROUP = 1;

    private final long MAX_GROUP = 1000;

    private final long MIN_STUDENTSCOUNT = 0;

    private final long MAX_X = 989;

    private Scanner userScanner;

    private boolean fileMode;

    public GroupAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * @param userScanner Scanner to set.
     */

    public void setUserScanner(Scanner userScanner) {this.userScanner = userScanner; }

    /**
     * @return Scanner, which uses for user input.
     */

    public Scanner getUserScanner() {return userScanner;}

    /**
     * Sets group asker mode to 'File Mode'.
     */

    public void setFileMode() {fileMode = true;}

    /**
     * Sets group asker mode to 'User Mode'.
     */

    public void setUserMode() {fileMode = false;}

    /**
     * Asks a user the group's name.
     * @return Group's name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите имя:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if(name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user the group's X coordinate.
     * @return Group's X coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Integer askX() throws IncorrectInputInScriptException {
        String strX;
        Integer x;
        while (true) {
            try {
                Console.println("Введите координату X:");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Integer.parseInt(strX);
                if (x > MAX_X) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Координата Y не может превышать " + MAX_X + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;

    }

    /**
     * Asks a user the group's Y coordinate.
     * @return Group's Y coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Long askY() throws IncorrectInputInScriptException {
        String strY;
        Long y;
        while (true) {
            try {
                Console.println("Введите координату Y:");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                y = Long.parseLong(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the group's coordinates.
     * @return group's coordinates.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        Integer x;
        Long y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }


    /**
     * Asks a user the number of students in this group.
     * @return Group's studentsCount.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Long askStudentsCount() throws IncorrectInputInScriptException {
        String strStudentsCount;
        Long studentsCount;
        while (true) {
            try {
                Console.println("Введите количество студентов группа");
                Console.print(App.PS2);
                strStudentsCount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strStudentsCount);
                studentsCount = Long.parseLong(strStudentsCount);
                if (studentsCount <= 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("количество студентов не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("количество студентов должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("количество студентов должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return studentsCount;
    }

    /**
     * Asks a user the transferred students.
     * @return Number students transferred.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public int askTransferredStudents() throws IncorrectInputInScriptException {
        String strTransferredStudents;
        int transferredStudents;
        while (true) {
            try {
                Console.println("Введите количество переведенных студентов");
                Console.print(App.PS2);
                strTransferredStudents = userScanner.nextLine().trim();
                if (fileMode) Console.println(strTransferredStudents);
                transferredStudents = Integer.parseInt(strTransferredStudents);
                if (transferredStudents <= 0 ) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("количество переведенных студентов не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("количество переведенных студентов должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("количество переведенных студентов должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return transferredStudents;
    }


    /**
     * Asks a user the group's form of education.
     * @return Group's form of education.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public FormOfEducation askFormOfEducation() throws IncorrectInputInScriptException {
        String strFormOfEducation;
        FormOfEducation formOfEducation;
        while (true) {
            try {
                Console.println("Введите форма обучения");
                Console.print(App.PS2);
                strFormOfEducation = userScanner.nextLine().trim();
                if (fileMode) Console.println(strFormOfEducation);
                formOfEducation = FormOfEducation.valueOf(strFormOfEducation.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("форма обучения не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("форма обучения нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return formOfEducation;
    }

    /**
     * Asks a user the group's semester.
     * @return Group's semester.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Semester askSemester() throws IncorrectInputInScriptException {
        String strSemester;
        Semester semester;
        while (true) {
            try {
                Console.println("Введите семестр");
                Console.print(App.PS2);
                strSemester = userScanner.nextLine().trim();
                if (fileMode) Console.println(strSemester);
                semester = Semester.valueOf(strSemester.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Семестр не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Семестр нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return semester;
    }

    /**
     * Asks a user the name of group admin.
     * @return group admin name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public String askGroupAdminName() throws IncorrectInputInScriptException {
        String groupAdminName;
        while (true) {
            try {
                Console.println("Введите имя администратора:");
                Console.print(App.PS2);
                groupAdminName = userScanner.nextLine().trim();
                if  (fileMode) Console.println(groupAdminName);
                if (groupAdminName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя администратора не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя администратора не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return groupAdminName;
    }


    /**
     * Asks a user the birthday of admin group.
     * @return birthday of admin group.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public LocalDate askBirthday() throws IncorrectInputInScriptException {
        String strBirthday;
        LocalDate birthday;
        while (true) {
            try {
                Console.println("Введите день рождения");
                Console.print(App.PS2);
                strBirthday = userScanner.nextLine().trim();
                if (fileMode) Console.println(strBirthday);
                birthday = LocalDate.parse(strBirthday);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("день рождения не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return birthday;
    }


    /**
     * Asks a user the height of group admin.
     * @return group admin height.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Long askHeight() throws IncorrectInputInScriptException {
        String strHeight;
        Long height;
        while (true) {
            try {
                Console.println("Введите высота администратора");
                Console.print(App.PS2);
                strHeight = userScanner.nextLine().trim();
                height = Long.parseLong(strHeight);
                if (height <= 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("высота не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("высота должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("высота должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return height;
    }

    /**
     * Asks a user the weight of group admin.
     * @return group admin weight.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Double asWeight() throws IncorrectInputInScriptException {
        String strWeight;
        Double weight;
        while (true) {
            try {
                Console.println("Введите масса администратора");
                Console.print(App.PS2);
                strWeight = userScanner.nextLine().trim();
                weight = Double.parseDouble(strWeight);
                if (weight <= 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("масса не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("масса должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("масса должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weight;
    }

    /**
     * Asks a user the passport ID of group admin.
     * @return group admin passport ID.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public String askPassportID() throws IncorrectInputInScriptException {
        String passportID;
        while (true) {
            try {
                Console.println("введите номер паспорта c длиной < " + MAX_LENGTH + "и > " + MIN_LENGTH);
                Console.print(App.PS2);
                passportID = userScanner.nextLine().trim();
                if (fileMode) Console.println(passportID);
                if (passportID.equals("")) throw new MustBeNotEmptyException();
                if (passportID.length() < 5 || passportID.length() > 23) throw  new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Номер паспорта не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Номер паспорта не может превышать " + MAX_LENGTH + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("номер паспорта может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }
        }
        return passportID;
    }

    /**
     * Asks a user the group admin.
     * @return group admin.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Person askGroupAdmin() throws IncorrectInputInScriptException {
        String name;
        LocalDate birthday;
        Long height;
        Double weight;
        String passportID;

        name = askGroupAdminName();
        height = askHeight();
        weight = asWeight();
        passportID = askPassportID();
        birthday = askBirthday();

        return  new Person(name,birthday,height,weight,passportID);
    }

    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString() {return "GroupAsker (вспомогательный класс для запросов пользователю)";}

}
