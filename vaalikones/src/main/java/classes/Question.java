package classes;

import java.io.Serializable;
import java.util.logging.Logger;


/**
 *
 * @author Bocceli
 */

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer questionId;
    private String question;

    public Question() {
    }

    public Question(Integer questionId, String question) {
        this.questionId = questionId;
        this.question = question;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        return !((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId)));
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                '}';
    }

    private static final Logger LOG = Logger.getLogger(Question.class.getName());
}
