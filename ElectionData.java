import java.util.LinkedList;
import java.util.HashMap;

class ElectionData {
    private HashMap<Integer, String> Vote1 = new HashMap();
    private HashMap<Integer, String> Vote2 = new HashMap();
    private HashMap<Integer, String> Vote3 = new HashMap();
    private HashMap<Integer, String> ballot = new HashMap();


    ElectionData() {
        this.ballot.put(ballot.size(), "Gompei");
        this.ballot.put(ballot.size(), "Husky");
    }

    public HashMap<Integer, String> getBallot(){
        return ballot;
    }

    /**
     *
     * @param firstChoice
     * @param secondChoice
     * @param thirdChoice
     * @throws DuplicateVotesException
     * @throws UnknownCandidateException
     */
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

    /**
     *
     * @param candidateName
     * @throws CandidateExistsException
     */
    public void addCandidate(String candidateName) throws CandidateExistsException{
        if (ballot.containsValue(candidateName))
            throw new  CandidateExistsException(candidateName);
        else
            ballot.put(ballot.size(), candidateName);
    }

    /**
     * Determines the winner based on 1st votes and return the candidate that has over 50% of votes if no candidate has
     * over 50% return string runoff required.
     * @return The name of the winner/Runoff required
     */
    public String findWinnerMostFirstVotes(){
        HashMap<String, Integer> totalVotes = new HashMap();
        for (int i = 0; i<Vote1.size(); i++){
            if (totalVotes.containsValue(Vote1.get(i))){
                int v = totalVotes.get(i);
                totalVotes.replace(Vote1.get(i), v+1);
            }
            else
                totalVotes.put(Vote1.get(i), 1);
        }

        for (int i = 0; i<Vote1.size(); i++){
            if (totalVotes.get(i) > (Vote1.size()/2)) {
                return Vote1.get(i);
            }
        }
        return "Runoff required";


    }

    /**
     * Determines the winner based on points, assigning three points to a 1st vote, two points to a 2nd vote, and one
     * point to a 3rd vote.
     * @return The name of the winner
     */
    public String findWinnerMostPoints(){
        HashMap<String, Integer> totalVotes = new HashMap<>();
        int winnerPoint = 0;
        String winner = "No votes counted";
        for (int i = 0; i<Vote1.size(); i++){
            //1st Choice
            if (totalVotes.containsValue(Vote1.get(i))){
                int v = totalVotes.get(i);
                totalVotes.replace(Vote1.get(i), v+3);
            }
            else
                totalVotes.put(Vote1.get(i), 3);

            //2nd Choice
            if (totalVotes.containsValue(Vote2.get(i))){
                int v = totalVotes.get(i);
                totalVotes.replace(Vote2.get(i), v+2);
            }
            else
                totalVotes.put(Vote2.get(i), 2);

            //3rd Choice
            if (totalVotes.containsValue(Vote3.get(i))){
                int v = totalVotes.get(i);
                totalVotes.replace(Vote3.get(i), v+1);
            }
            else
                totalVotes.put(Vote3.get(i), 1);
        }


        for (int i = 0; i<ballot.size(); i++){
            if (totalVotes.get(ballot.get(i)) > winnerPoint){
                winnerPoint = totalVotes.get(ballot.get(i));
                winner = ballot.get(i);
            }

        }

        return winner;
    }

}
