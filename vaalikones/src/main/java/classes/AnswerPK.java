package classes;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Logger;


/**
 *
 * @author Bocceli
 */
public class AnswerPK implements Serializable {

    private int candidateId;
    private int questionId;

    public AnswerPK() {
    }

    public AnswerPK(int candidateId, int questionId) {
        this.candidateId = candidateId;
        this.questionId = questionId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += candidateId;
        hash += questionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswerPK)) {
            return false;
        }
        AnswerPK other = (AnswerPK) object;
        if (this.candidateId != other.candidateId) {
            return false;
        }
        return this.questionId == other.questionId;
    }

    @Override
    public String toString() {
        return "AnswerPK{" +
                "candidateId=" + candidateId +
                ", questionId=" + questionId +
                '}';
    }

    private static final Logger LOG = Logger.getLogger(AnswerPK.class.getName());

    /**
     * Save method of object
     * @param conn Connection to database
     * @return Saved object or null
     */
    public AnswerPK save(Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into ANSWERPK(candidateId, questionId) values(?,?)"
                );
                pstmt.setInt(1, this.candidateId);
                pstmt.setInt(2, this.questionId);

                pstmt.executeUpdate();

                conn.close();

                return this;
            } else {
                System.out.println("No connection!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
