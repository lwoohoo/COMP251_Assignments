import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/ //done
        public int probe(int key, int i) {
            //TODO: implement this function and change the return statement.
            return ((((this.A * key) % power2(this.w) >> (this.w - this.r))) + i) % power2(this.r);
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement
            int collisions = 0;
            //Table[i] = -1 implies empty slot
            //Table[i] != -1 implies full slot
            while (true) {
                int index = probe(key, collisions); //calculate working index
                if (index == Table.length - 1) break;
                int contents = Table[index]; //get value at hash index
                if (contents < 0) { //empty case
                    Table[index] = key; //insertion
                    break;
                } else {
                    collisions++; //increment # collisions
                }
            }
            return collisions;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray(int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Removes key k from the hash table. Returns the number of collisions encountered*/
         //maybe implement wrap around
        public int removeKey(int key){
            //TODO: implement this and change the return statement
            int collisions = 0;
            while (true) {
                int index = probe(key, collisions);
                if (index >= Table.length - 1) break;
                int contents = Table[index]; //get value at hash index
                if (contents == -1) break; //implied item not in table
                if (contents == key) { //item found
                    Table[index] = -2; //deletion
                    break;
                }
                collisions ++;
            }
            return collisions;
        }
}
