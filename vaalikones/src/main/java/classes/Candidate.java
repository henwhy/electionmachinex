
package classes;

import java.io.Serializable;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * @author Bocceli
 */

public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer candidateId;
    private String lastName;
    private String firstName;
    private String politicalParty;
    private String city;
    private Integer age;
    private String whyQuestion;
    private String improveQuestion;
    private String profession;

    /**
     *
     */
    public Candidate() {
    }

    public Candidate(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Candidate(Integer candidateId, String lastName, String firstName, String politicalParty, String city, Integer age, String whyQuestion, String improveQuestion, String profession) {
        this.candidateId = candidateId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.politicalParty = politicalParty;
        this.city = city;
        this.age = age;
        this.whyQuestion = whyQuestion;
        this.improveQuestion = improveQuestion;
        this.profession = profession;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWhyQuestion() {
        return whyQuestion;
    }

    public void setWhyQuestion(String whyQuestion) {
        this.whyQuestion = whyQuestion;
    }

    public String getImproveQuestion() {
        return improveQuestion;
    }

    public void setImproveQuestion(String improveQuestion) {
        this.improveQuestion = improveQuestion;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (candidateId != null ? candidateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidate)) {
            return false;
        }
        Candidate other = (Candidate) object;
        return !((this.candidateId == null && other.candidateId != null) || (this.candidateId != null && !this.candidateId.equals(other.candidateId)));
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId=" + candidateId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", politicalParty='" + politicalParty + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", whyQuestion='" + whyQuestion + '\'' +
                ", improveQuestion='" + improveQuestion + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }

    private static final Logger LOG = Logger.getLogger(Candidate.class.getName());


    public Candidate saveObject(Connection conn) {
    //todo saving object
         return new Candidate();
    }
}
