package SegmentTree;

public class Segment {
    static int tree[];
    public static void init(int n){
        tree=new int[4*n];
    }
    public static void buildTree(int arr[],int i,int start,int end){
        if(start==end){
            tree[i]=arr[start];
            return;
        }
        int mid=(start+end)/2;
        buildTree(arr, 2*i+1, start, mid);
        buildTree(arr, 2*i+2, mid+1, end);
        tree[i]=Math.max(tree[2*i+1], tree[2*i+2]);
    }
    public static int buildST(int arr[],int i,int start,int end){
        if(start==end){
            tree[i]=arr[start];
            return arr[start];
        }
        int mid=(start+end)/2;
        buildST(arr, 2*i+1, start, mid);
        buildST(arr, 2*i+2, mid+1, end);
        tree[i]=tree[2*i+1]+tree[2*i+2];
        return tree[i];
    }
    public static int getSumUtil(int i,int si,int sj,int qi,int qj){
        if(qj<=si || qi>=sj){
            return 0;
        }else if(si>=qi && sj <=qj){
            return tree[i];
        }else{
            int mid=(si+sj)/2;
            int left=getSumUtil(2*i+1, si, mid, qi, qj);
            int right=getSumUtil(2*i+2, mid+1, sj, qi, qj);
            return left+right;
        }
    }
    public static int getSum(int arr[],int qi,int qj){
        int n=arr.length;
       return getSumUtil(0, 0, n-1, qi, qj);
    }
    public static void updatUtil(int i,int si,int sj,int idx,int diff) {
        if(idx>sj || idx<si){
            return;
        } 
        tree[i]+=diff;
        if(si!=sj){
            int mid=(si+sj)/2;
            updatUtil(2*i+1, si, mid, idx, diff);
            updatUtil(2*i+2, mid+1, sj, idx, diff);

        }
    }
    public static void  update(int arr[],int idx,int newVal) {
        int n=arr.length;
        int diff=newVal-arr[idx];
        arr[idx]=newVal;
        updatUtil(0, 0, n-1, idx, diff);
    }
    public static int getMax(int arr[],int qi,int qj){
        int n=arr.length;
        return getMaxUtil(0,0,n-1,qi,qj);
    }
    public static int getMaxUtil(int i,int si,int sj,int qi,int qj){
        if(si>qj||sj<qi){
            return Integer.MIN_VALUE;
        }else if(si>=qi && sj<=qj){
            return tree[i];
        }else{
            int mid=(si+sj)/2;
            int left=getMaxUtil(2*i+1, si, mid, qi, qj);
            int right=getMaxUtil(2*i+2, mid+1, sj, qi, qj);
            return Math.max(left, right);
        }
    }
    public static void update1(int arr[], int idx, int newVal) {
        arr[idx] = newVal;
        int n = arr.length;
        updateUtil2(0, 0, n - 1, idx, newVal);
    }
    
    public static void updateUtil2(int i, int si, int sj, int idx, int newVal) {
        if (idx < si || idx > sj) {
            return;
        }
        tree[i] = Math.max(tree[i], newVal);
        if (si != sj) {
            int mid = (si + sj) / 2;
            updateUtil2(2 * i + 1, si, mid, idx, newVal);
            updateUtil2(2 * i + 2, mid + 1, sj, idx, newVal);
        }
    }
    
    
    public static void main(String[] args) {
        int arr[]={6,8,-1,2,17,1,3,2,4};
        int n=arr.length;
        init(n);
        buildTree(arr, 0, 0, n-1);
        // for(int i=0;i<tree.length;i++){
        //     System.out.print(tree[i]+" ");
        // }
        System.out.println(getMax(arr, 2, 5));
        // update1(arr, 2, 5);
        update1(arr, 2, 20);
        System.out.println(getMax(arr, 2, 5));
    }
}
