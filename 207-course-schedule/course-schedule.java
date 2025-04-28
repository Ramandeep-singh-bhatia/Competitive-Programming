class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int count = numCourses;
        int[] inDegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] course: prerequisites){
            graph.get(course[1]).add(course[0]);
            inDegree[course[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                q.offer(i);
                count--;
            }
        }

        while(!q.isEmpty()){
            int c = q.poll();
            for(int course: graph.get(c)){
                inDegree[course]--;
                if(inDegree[course] == 0){
                    q.offer(course);
                    count--;
                }   
            }
        }

        return count == 0;
     }
}