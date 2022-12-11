import java.util.*;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
       return new SCCs(adj).hasCycle();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}
class SCCs{
    private ArrayList<Integer>[] adj;
    private int [] preVisits;
    private int [] postVisits;
    private Set<Integer> visited = new HashSet<>();
    private Set<Integer> sVisited = new HashSet<>();
    private Map<Integer,Integer> postorderMap = new HashMap<>();
    private int clock = 1;
    public SCCs(ArrayList<Integer>[] adj){
        this.adj = adj;
        this.preVisits = new int[adj.length];
        this.postVisits = new int[adj.length];
    }



    public int hasCycle(){
        for (int i=0 ;i< adj.length;i++){
            if(explore(i,i) == 1) return 1;
        }
        return 0;
    }


    private int explore(int index, int desiredIndex) {
        visited.add(index);
        if(adj[index].contains(desiredIndex)) return 1;
        for (int i :adj[index]){
            if(isVisited(i)) continue;
            if(explore(i,desiredIndex)==1) return 1;
        }
        return 0;


    }
    private boolean isVisited(int index){
        return visited.contains(index);
    }
    private void preVisit(int index){
        preVisits[index] = clock;
        clock++;
    }
    private void postVisit(int index){
        postVisits[index] = clock;
        clock++;
    }

}

