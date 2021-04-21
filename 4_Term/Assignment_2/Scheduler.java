import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Scheduler {

    private Assignment[] assignmentArray;
    private Integer[] C;
    private Double[] max;
    private ArrayList<Assignment> solutionDynamic;
    private ArrayList<Assignment> solutionGreedy;

    /**
     * @throws IllegalArgumentException when the given array is empty
     */
    public Scheduler(Assignment[] assignmentArray) throws IllegalArgumentException {
        // Should be instantiated with an Assignment array
        // All the properties of this class should be initialized here
        if(assignmentArray.length <= 0){
    
            throw new IllegalArgumentException();
        }
        this.solutionDynamic = new ArrayList<>();
        this.solutionGreedy = new ArrayList<>();
        this.assignmentArray = assignmentArray;
        this.C = new Integer[this.assignmentArray.length];
        this.max = new Double[this.assignmentArray.length];
    }

    /**
     * @param index of the {@link Assignment}
     * @return Returns the index of the last compatible {@link Assignment},
     * returns -1 if there are no compatible assignments
     */
    private int binarySearch(int index) { // find best previous assignment
        int lo = 0, hi = index ;
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (compareTime(this.assignmentArray[mid].getFinishTime(),this.assignmentArray[index].getStartTime()))
            {
                if (compareTime(this.assignmentArray[mid + 1].getFinishTime(), this.assignmentArray[index].getStartTime()))
                    lo = mid + 1;
                else
                    return mid;
            }
            else
                hi = mid - 1;
        }

        return -1;
    }

    private boolean compareTime(String one, String two){// this method was used by binarysearch method
        String[] this_finish = one.split(":");
        String[] o_finish = two.split(":");

        int hour_this = Integer.valueOf(this_finish[0]);
        int min_this = Integer.valueOf(this_finish[1]);
        int hour_other = Integer.valueOf(o_finish[0]);
        int min_other = Integer.valueOf(o_finish[1]);

        if(hour_this>hour_other)
            return false;
        else if(hour_this<hour_other)
            return true;
        else{
            if(min_this>min_other)
                return false;
            else if(min_this<=min_other)
                return true;
        }
        return false;
    }
    /**
     * {@link #C} must be filled after calling this method
     */
    private void calculateC() {  // find best previous assignment for each array assignment
        this.C[0] = -1;
            for(int i = 1 ; i < this.C.length;i++){
                this.C[i] = binarySearch(i);
            }
    }


    /**
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleDynamic() { // main method for dynamic solution
        this.calculateC();
        this.calculateMax(this.assignmentArray.length-1);
        findSolutionDynamic(this.assignmentArray.length-1);
        Collections.reverse(this.solutionDynamic);   // reverse order for write correct order
        return this.solutionDynamic;
    }

    /**
     * {@link #solutionDynamic} must be filled after calling this method
     * @return
     */
    private void findSolutionDynamic(int i) { // control method, find best solution of dynamic method compare time and weight
        if(i < 0){
            return;
        }
        Double max_C = this.C[i]< 0 ? 0.0 : this.max[this.C[i]];
        Double max_C_low = i == 0 ? 0.0 :  this.max[i - 1];

                if (this.assignmentArray[i].getWeight() + max_C >= max_C_low) {
                    System.out.println("findSolutionDynamic("+i+")");
                    System.out.println("Adding "+this.assignmentArray[i].toString()+" to the dynamic schedule");
                    this.solutionDynamic.add(this.assignmentArray[i]);
                     findSolutionDynamic(this.C[i]);
                } else {
                    System.out.println("findSolutionDynamic("+(i)+")");
                     findSolutionDynamic(i - 1);
                }

    }

    /**
     * {@link #max} must be filled after calling this method
     */
    private Double calculateMax(int i) { // calculate max weight value for best solution
        if(i<0){
            return 0.0;
        }
        if(i == 0){
            System.out.println("calculateMax(0): Zero");
            this.max[0] = this.assignmentArray[0].getWeight();
            return this.max[0];
        }
            if(this.max[i] != null) {
                System.out.println("calculateMax("+i+"): Present");
                return this.max[i];
            }
            System.out.println("calculateMax("+i+"): Prepare");
            this.max[i] = Math.max(this.assignmentArray[i].getWeight()+calculateMax(this.C[i]),calculateMax(i-1));
            return  this.max[i];
    }

    /**
     * {@link #solutionGreedy} must be filled after calling this method
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleGreedy() {  // main method for greedy solution
        this.solutionGreedy.add(assignmentArray[0]);
        System.out.print("Adding "+this.assignmentArray[0].toString()+" to the greedy schedule");
        for(int i = 1 ; i < this.assignmentArray.length; i++){
            if(this.solutionGreedy.get(this.solutionGreedy.size()-1).compareTimeFinishtoStart(assignmentArray[i]) <= 0)   {
                    this.solutionGreedy.add(assignmentArray[i]);
                System.out.print("\nAdding "+this.assignmentArray[i].toString()+" to the greedy schedule");
            }
        }
        return this.solutionGreedy;
    }
}
