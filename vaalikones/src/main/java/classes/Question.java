package classes;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Logger;


/**
 *
 * @author Bocceli
 */

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer questionId;
    private String question;

    public Question(Integer questionId) {
        this.questionId = questionId;
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

    /**
     * Save method of object
     * @param conn Connection to database
     * @return Saved object or null
     */
    public Question save(Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into QUESTIONS(QUESTION) values(?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                pstmt.setString(1, this.question);

                pstmt.executeUpdate();

                //getting last inserted id
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    this.setQuestionId((int) rs.getLong(1));
                }

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
