import java.util.HashMap;
import java.util.Scanner;

public class VotingMachine {
    private ElectionData data = new ElectionData();

    Scanner keyboard = new Scanner(System.in);


    /**
     * Retrieves the ballot data and prints the candidates to the screen.
     */
    public void printBallot() {
        System.out.println("The candidates are ");
        for (int i = 0; i<data.getBallot().size(); i++) {
            System.out.println(data.getBallot().get(i));
        }
    }

    /**
     * Prompt the user to vote for three candidates, throw exceptions if there is an unknown name entered or the user
     * inputs the same name multiple times. Handles any thrown exceptions and prints the final votes.
     */
    public void screen() {
        this.printBallot();
        System.out.println("Who do you want to vote for? Enter your first choice: ");
        String candidate1 = keyboard.next();
        System.out.println("Enter your second choice: ");
        String candidate2 = keyboard.next();
        System.out.println("Enter your third choice: ");
        String candidate3 = keyboard.next();

        try{
            data.processVote(candidate1, candidate2, candidate3);
        }
        catch (UnknownCandidateException e){
            System.out.println(e.getName() + " was not found on ballot. Would you like to add them to the ballot? " );
            String response = keyboard.next();
            if (response.equals("y") || response.equals("Y")){
                addWriteIn(e.getName());
            }
            screen();
        }
        catch (DuplicateVotesException e){
            System.out.println(e.getName() + " was entered multiple times. Please vote for each candidate once.");
            screen();
        }

        System.out.println("You voted for " + candidate1 + ", " + candidate2 + ", and " + candidate3);
    }

    /**
     * Adds a candidate to the ballot as a write in.
     * @param name Candidate name that the UnknownCandidateException was thrown for.
     */
    private void addWriteIn(String name){
        try{
            data.addCandidate(name);
        }
        catch (CandidateExistsException e){
            System.out.println("This candidate already exists.");
            screen();
        }
        System.out.println(name + " has been added to the ballot.");
        screen();
    }

}
