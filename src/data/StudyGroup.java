package data;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class StudyGroup {

    private Long id;

    private String name;

    private Coordinates coordinates;

    private java.time.LocalDateTime creationDate;

    private Long studentsCount;

    private int transferredStudents;

    private FormOfEducation formOfEducation;

    private Semester semesterEnum;

    private Person groupAdmin;

    public StudyGroup(String name, Coordinates coordinates, Long studentsCount,int transferredStudents, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
        this.id = IDprovider.getInstance().getID();
    }

    /**
     * @return ID of studyGroup
     */

    public Long getId() {return id;}

    public void setId(Long id){
        this.id = id;
    }

    /**
     * @return name of studyGroup.
     */

    public String getName() {return name;}

    /**
     * @return coordinates of studyGroup.
     */

    public Coordinates getCoordinates() {return coordinates;}

    public String getCoordinatesString() {return coordinates.getX() + "," + coordinates.getY();}



    /**
     * @return number students of studyGroup.
     */

    public Long getStudentsCount() {return studentsCount;}

    /**
     * @return the number of transfer students.
     */

    public int getTransferredStudents() {return transferredStudents;}


    /**
     * @return Form of education of studyGroup.
     */

    public FormOfEducation getFormOfEducation() {return formOfEducation;}

    /**
     * @return Semester.
     */

    public Semester getSemesterEnum(){return semesterEnum;}

    /**
     * @return Name of group admin.
     */

    public Person getGroupAdmin() {return groupAdmin;}


    public int compareTo(StudyGroup groupObj) {
        return studentsCount.compareTo(groupObj.getId());
    }

    @Override
    public String toString() {
        String info = "";
        info += "\nСолдат № " + id;
        info += "\n(добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n количество студентов: " + studentsCount;
        info += "\n количество переводных студентов: " + transferredStudents;
        info += "\n форма образования: " + formOfEducation;
        info += "\n имя лидера: " + groupAdmin;
        return info;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + id.hashCode() + creationDate.hashCode() + coordinates.hashCode() + studentsCount.hashCode() + (int)transferredStudents
                +formOfEducation.hashCode() + groupAdmin.hashCode();
    }



}

