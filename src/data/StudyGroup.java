package data;

import java.util.Date;

public class StudyGroup {
    private Long id;

    private String name;

    private Coordinates coordinates;

    private java.util.Date creationDate;

    private Long studentsCount;

    private long transferredStudents;

    private FormOfEducation formOfEducation;

    private Semester semesterEnum;

    private Person groupAdmin;

    public StudyGroup(long id, String name, Coordinates coordinates, Date creationDate, Long studentsCount, long transferredStudents, FormOfEducation formOfEducation, Semester semesterEnum,Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    /**
     * @return ID of studyGroup
     */

    public Long getId() {return id;}

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
     * @return Date was created studyGroup.
     */

    public Date getCreationDate() {return creationDate;}

    /**
     * @return number students of studyGroup.
     */

    public Long getStudentsCount() {return studentsCount;}

    /**
     * @return the number of transfer students.
     */

    public long getTransferredStudents() {return transferredStudents;}


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
        info += "Солдат №" + id;
        info += " (добавлен " + creationDate;
        info += "\n Местоположение: " + coordinates;
        info += "\n количество студентов" + studentsCount;
        info += "\n количество переводных студентов" + transferredStudents;
        info += "\n форма образования" + formOfEducation;
        info += "\n имя лидера" + groupAdmin;
        return info;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + id.hashCode() + creationDate.hashCode() + coordinates.hashCode() + studentsCount.hashCode() + (int)transferredStudents
                +formOfEducation.hashCode() + groupAdmin.hashCode();
    }



}

