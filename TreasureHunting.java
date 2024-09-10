package treasurehunting;

import java.util.Arrays;
import java.util.Scanner;

public class TreasureHunting {

    private static int dfs(int r, int c,int tR,int tC, int[][] dungeon,boolean[][]vis,int steps,int[][]directions,String command) {
        if(r==tR&&c==tC){

            return steps;
        }
        if(vis[r][c]){
            return Integer.MAX_VALUE;
        }
        if(command.equals("A")&&dungeon[r][c]==-1){
            return Integer.MAX_VALUE;
        }
        vis[r][c]=true;
        int min=Integer.MAX_VALUE;
        for(int[] dir : directions){
            int nRow=r+dir[0];
            int nCol=c+dir[1];
            if(nRow>=0&&nCol>=0&&nRow<dungeon.length&&nCol<dungeon[0].length&& !vis[nRow][nCol]){
                min=Math.min(min,dfs(nRow,nCol,tR,tC,dungeon,vis,steps+1,directions,command));
            }
        }
        vis[r][c]=false;
        return min;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=5;
        int m=4;
        int[][]dungeon=new int[n][m];

        System.out.println("Enter Adventurer Position Row and Col");
        int adventurerLocationRow=scanner.nextInt()-1;
        int adventurerLocationCol= scanner.nextInt()-1;

        System.out.println("Enter Monster Position Row and Col");
        int monsterLocationRow=scanner.nextInt()-1;
        int monsterLocationCol=scanner.nextInt()-1;

        System.out.println("Enter Trigger Position Row and Col");
        int triggerLocationRow=scanner.nextInt()-1;
        int triggerLocationCol=scanner.nextInt()-1;

        System.out.println("Enter Gold Position Row and Col");
        int goldLocationRow=scanner.nextInt()-1;
        int goldLocationCol=scanner.nextInt()-1;

        System.out.println("Enter numer of pits : ");
        int pits=scanner.nextInt();
        for(int i=1;i<=pits;i++){
            System.out.println("Enter Pits Position Row and Col ");
            dungeon[scanner.nextInt()-1][scanner.nextInt()-1]=-1;
        }
        dungeon[goldLocationRow][goldLocationCol]=5;
        int[][]directions={{0,1},{1,0},{-1,0},{0,-1}};
        boolean[][]vis=new boolean[n][m];

        int minPathAdventurer= dfs(adventurerLocationRow,adventurerLocationCol,goldLocationRow,goldLocationCol,dungeon,vis,0,directions,"A");;


        int minPathMonster= dfs(monsterLocationRow,monsterLocationCol,goldLocationRow,goldLocationCol,dungeon,vis,0,directions,"M");;
        if(minPathMonster>=minPathAdventurer){
            System.out.println("Minimum Steps : "+minPathAdventurer);
        }else{
            int minPathAdventurerToTrigger=dfs(adventurerLocationRow,adventurerLocationCol,triggerLocationRow,triggerLocationCol,dungeon,vis,0,directions,"A");
            int minPathAdventurerToGoldFromTrigger=dfs(triggerLocationRow,triggerLocationCol,goldLocationRow,goldLocationCol,dungeon,vis,0,directions,"A");;
            System.out.println("Minimum Steps : "+(minPathAdventurerToTrigger+minPathAdventurerToGoldFromTrigger));
        }

    }


}
