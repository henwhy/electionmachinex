package classes;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Logger;

/**
 * @author Bocceli
 */

public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    protected AnswerPK answerPk;
    private Integer answer;
    private String comment;

    public Answer() {
    }

    public Answer(Integer answer, String comment) {
        this.answer = answer;
        this.comment = comment;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
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
                ", answerId=" + answer +
                ", comment='" + comment + '\'' +
                '}';
    }

    private static final Logger LOG = Logger.getLogger(Answer.class.getName());

    /**
     * Save method of object
     * @param conn Connection to database
     * @return Saved object or null
     */
    public Answer save(Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into ANSWERS(COMMENT) values(?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                pstmt.setString(1, this.comment);

                pstmt.executeUpdate();

                //getting last inserted id
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    this.setAnswer((int) rs.getLong(1));
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
