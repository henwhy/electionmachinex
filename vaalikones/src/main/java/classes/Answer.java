package classes;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Bocceli
 */

public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    protected AnswerPK answerPk;
    private Integer answerId;
    private String comment;

    public Answer() {
    }

    public Answer(Integer answerId, String comment) {
        this.answerId = answerId;
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerPk != null ? answerPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        return !((this.answerPk == null && other.answerPk != null) || (this.answerPk != null && !this.answerPk.equals(other.answerPk)));
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerPk=" + answerPk +
                ", answerId=" + answerId +
                ", comment='" + comment + '\'' +
                '}';
    }

    private static final Logger LOG = Logger.getLogger(Answer.class.getName());

}
