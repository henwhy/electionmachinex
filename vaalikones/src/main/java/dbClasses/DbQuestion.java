package dbClasses;

import classes.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbQuestion {
    /**
     * Save method of object
     * @param conn Connection to database
     * @return Saved object or null
     */
    public static Question save(Question question, Connection conn) {
        try {
            if (conn != null) {

                PreparedStatement pstmt = null;

                if (question.getQuestionId() == null) {
                    pstmt = conn.prepareStatement(
                            "insert into QUESTIONS(QUESTION) values(?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    pstmt.setString(1, question.getQuestion());
                } else {
                    pstmt = conn.prepareStatement(
                            "UPDATE QUESTIONS SET QUESTION=? WHERE QUESTION_ID=?",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    pstmt.setString(1, question.getQuestion());
                    pstmt.setInt(2, question.getQuestionId());
                }

                pstmt.executeUpdate();

                //getting last inserted id
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    question.setQuestionId((int) rs.getLong(1));
                }

                return question;
            } else {
                System.out.println("No connection!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Question> getQuestionById (int id, Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM QUESTIONS WHERE QUESTION_ID=?"
                );
                pstmt.setInt(1, id);

                ResultSet rs = pstmt.executeQuery();

                ArrayList<Question> arrayList = new ArrayList<>();

                while (rs.next()) {
                    Question question = new Question(
                            rs.getInt("QUESTION_ID"),
                            rs.getString("QUESTION")
                    );
                    arrayList.add(question);
                }

                return arrayList;
            } else {
                System.out.println("No connection!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Question> getQuestions (Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM QUESTIONS"
                );

                ResultSet rs = pstmt.executeQuery();

                ArrayList<Question> arrayList = new ArrayList<>();

                while (rs.next()) {
                    Question question = new Question(
                            rs.getInt("QUESTION_ID"),
                            rs.getString("QUESTION")

                    );
                    arrayList.add(question);
                }

                return arrayList;
            } else {
                System.out.println("No connection!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean delete(int id, Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "DELETE FROM QUESTIONS WHERE QUESTION_ID=?"
                );
                pstmt.setInt(1, id);

                pstmt.executeUpdate();

                return true;
            } else {
                System.out.println("No connection!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
