/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electionmachine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonne
 */
public class User implements Serializable {

	private ArrayList<Integer> answer = new ArrayList<>(20);
    ArrayList<Tuple<Integer, Integer>> points = new ArrayList<>(20);

    /**
     * New information for User-object
     */

    public void fillAnswersAndPoints() {

        //filling the lists
        for (int i = 0; i < 20; i++) {
            this.answer.add(0);
            this.points.add(new Tuple<>(0, 0));
        }

    }
    /**
     * @return Integer-list of users answers.
     */
    public ArrayList<Integer> getAnswerList() {
        return this.answer;
    }

    /**
     * Hae pisteet-listasta yksittäiset pisteet
     *
     * @param candidateId ehdokkaan id-numero
     * @return pisteet ehdokkaaseen nähden
     */
    public Integer getPoints(int candidateId) {
        if (this.points.size() >= candidateId) {
            return this.points.get(candidateId).points;
        } else {
            return 0;
        }
    }

    /**
     * Aseta pisteet tiettyyn ehdokkaaseen nähden
     *
     * @param candidateId ehdokkaan id-numero
     * @param points Arvo, mikä lisätään
     */
    public void addPoints(Integer candidateId, Integer points) {
        this.points.set(candidateId, new Tuple<>(candidateId, points));
    }

    /**
     * Hae yksittäinen käyttäjän vastaus kysymykseen
     *
     * @param index kysymyksen numero
     * @return Yksittäinen integer-muotoinen vastaus käyttäjän vastaus-listasta
     */
    public Integer getAnswer(int index) {
        return this.answer.get(index);
    }

    /**
     * Lisää vastaus
     *
     * @param index kysymyksen numero
     * @param answer vastauksen arvo
     */
    public void addAnswer(Integer index, Integer answer) {
    	if (this.answer.size()==0) {
    		fillAnswersAndPoints();
    	}
        this.answer.set(index, answer);
    }

    /**
     * Hae parhaat ehdokkaat pistemäärän mukaan järjesteltynä
     *
     * @return Tuple-lista, (ehdokkaan id, pisteet)
     */
    public ArrayList<Tuple<Integer, Integer>> getBestCandidates() {

        /* Järjestä pisteet sisältävä Tuple.
         *  Javan Collections.sort oletuksena järjestää listat pienimmästä suurimpaan
         *  Collections.reverseOrder kääntää järjestyksen toisin päin
         */
    	try {
        Collections.sort(this.points, Collections.reverseOrder(comparator));
    	}
    	catch (Exception e) {
    	}
    	
//        this.pisteet.stream().forEach((tpl) -> {
//            logger.log(Level.INFO, "Ehdokas ID={0} pisteet={1}", new Object[]{tpl.ehdokasId, tpl.pisteet});
//        });

        return this.points;
    }

    //Tuplen järjestämiseen tarvittavan comparatorin muodostaminen
    //lähde: http://stackoverflow.com/questions/5690537/sorting-a-tuple-based-on-one-of-the-fields
    //Comparator<Tuple<Integer, Integer>> comparator = (Tuple<Integer, Integer> o1, Tuple<Integer, Integer> o2) -> o1.pisteet.compareTo(o2.pisteet);
    transient Comparator<Tuple<Integer, Integer>> comparator = new Comparator<Tuple<Integer, Integer>>() {
        @Override
        public int compare(Tuple<Integer, Integer> o1, Tuple<Integer, Integer> o2) {
            return o1.points.compareTo(o2.points);
        }
    };

}
