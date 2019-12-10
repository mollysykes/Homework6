import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

class ElectionData {
    HashMap<Integer, String> Vote1 = new HashMap();
    HashMap<Integer, String> Vote2 = new HashMap();
    HashMap<Integer, String> Vote3 = new HashMap();
    HashMap<Integer, String> ballot = new HashMap();

    Scanner keyboard = new Scanner(System.in);


    ElectionData() {
        this.ballot.put(ballot.size(), "Gompei");
        this.ballot.put(ballot.size(), "Husky");
    }

    public void printBallot() {
        System.out.println("The candidates are ");
        int size = ballot.size();
        for (int i = 0; i<ballot.size(); i++) {
            System.out.println(ballot.get(i));
        }
    }

    public void screen() {
        this.printBallot();
        System.out.println("Who do you want to vote for?");
        String candidate = keyboard.next();
        ballot.put(ballot.size(), candidate);
        System.out.println("You voted for " + candidate);
    }

    public int countVotes(String forcand) {
        int numvotes = 0;
        for (String s : votes) {
            if (s.equals(forcand))
                numvotes = numvotes+1;
        }
        return numvotes;
    }

    public void processVote(String firstChoice, String secondChoice, String thirdChoice) throws DuplicateVotesException, UnknownCandidateException{
        if (!ballot.containsValue(firstChoice))
            throw new UnknownCandidateException(firstChoice);
        if (!ballot.containsValue(secondChoice))
            throw new UnknownCandidateException(secondChoice);
        if (!ballot.containsValue(thirdChoice))
            throw new UnknownCandidateException(thirdChoice);
        if (firstChoice.equals(secondChoice))
            throw new DuplicateVotesException(firstChoice);
        if (thirdChoice.equals(secondChoice))
            throw new DuplicateVotesException(secondChoice);
        if (firstChoice.equals(thirdChoice))
            throw new DuplicateVotesException(firstChoice);
        Vote1.put(Vote1.size(), firstChoice);
        Vote2.put(Vote2.size(), secondChoice);
        Vote3.put(Vote3.size(), thirdChoice);
    }

    public void addCandidate(String candidateName) throws CandidateExistsException{
        if (ballot.containsValue(candidateName))
            throw new  CandidateExistsException(candidateName);
        else
            ballot.put(ballot.size(), candidateName);
    }

    public String findWinnerMostFirstVotes(){
        HashMap<String, Integer> totalVotes = new HashMap();
        int count = 0;
        String winner;
        for (int i = 0; i<Vote1.size(); i++){
            if (totalVotes.containsValue(Vote1.get(i))){
                int v = totalVotes.get(i);
                totalVotes.replace(Vote1.get(i), v+1);
            }
            else
                totalVotes.put(Vote1.get(i), 0);
            count += 1;
        }
        for (int i = 0; i<totalVotes.size(); i++){
            if (i == 0) {

                winner =
            }
        }

    }

    public String findWinnerMostPoints(){

    }

}
