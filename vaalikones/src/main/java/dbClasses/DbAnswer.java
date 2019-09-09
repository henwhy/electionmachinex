package dbClasses;

import classes.Answer;
import classes.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAnswer {

    public static List<Answer> getAnswersByCandidateId(int id, Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("select * from ANSWERS where CANDIDATE_ID=?");
                preparedStatement.setInt(1, id);

                ResultSet rs = preparedStatement.executeQuery();

                List<Answer> answerList = new ArrayList<>();

                while (rs.next()) {
                    Answer answer = new Answer(
                            rs.getInt("ANSWER"),
                            rs.getString("COMMENT")
                    );
                    answerList.add(answer);
                }
                preparedStatement.close();
                rs.close();

                return answerList;
            } else {
                System.out.println("No connection!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
