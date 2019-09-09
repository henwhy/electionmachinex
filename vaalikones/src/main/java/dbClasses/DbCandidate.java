package dbClasses;

import classes.Candidate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbCandidate {
    /**
     * Save method of object
     *
     * @param conn Connection to database
     * @return Saved object or null
     */
    public static Candidate save(Candidate candidate, Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into CANDIDATES(LASTNAME, FIRSTNAME, POLITICAL_PARTY, CITY, AGE, WHY_QUESTION, IMPROVE_QUESTION, PROFESSION) values(?,?,?,?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                pstmt.setString(1, candidate.getLastName());
                pstmt.setString(2, candidate.getFirstName());
                pstmt.setString(3, candidate.getPoliticalParty());
                pstmt.setString(4, candidate.getCity());
                pstmt.setInt(5, candidate.getAge());
                pstmt.setString(6, candidate.getWhyQuestion());
                pstmt.setString(7, candidate.getImproveQuestion());
                pstmt.setString(8, candidate.getProfession());

                pstmt.executeUpdate();

                //getting last inserted id
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    candidate.setCandidateId((int) rs.getLong(1));
                }

                return candidate;
            } else {
                System.out.println("No connection!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Candidate> getCandidates(Connection conn) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM CANDIDATES"
                );

                ResultSet rs = pstmt.executeQuery();

                ArrayList<Candidate> arrayList = new ArrayList<>();

                while (rs.next()) {
                    Candidate candidate = new Candidate(
                            rs.getInt("CANDIDATE_ID"),
                            rs.getString("LASTNAME"),
                            rs.getString("FIRSTNAME"),
                            rs.getString("POLITICAL_PARTY"),
                            rs.getString("CITY"),
                            rs.getInt("AGE"),
                            rs.getString("WHY_QUESTION"),
                            rs.getString("IMPROVE_QUESTION"),
                            rs.getString("PROFESSION")
                    );
                    arrayList.add(candidate);
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
                        "DELETE FROM CANDIDATES WHERE CANDIDATE_ID=?"
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
