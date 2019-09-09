package electionmachine;

import java.io.Serializable;

/**
 * @param <E>
 * @param <F>
 * @author Jonne
 */
public class Tuple<E, F> implements Serializable, Comparable<Tuple<Integer, Integer>> {
    private static final long serialVersionUID = 1L;

    public Integer candidateId;
    public Integer points;

    /**
     * @param e
     * @param f
     */

    public Tuple(Integer e, Integer f) {
        this.candidateId = e;
        this.points = f;
    }


    @Override
    public int compareTo(Tuple<Integer, Integer> o) {
        // TODO Auto-generated method stub
        return (int) this.points - o.points;
    }

}