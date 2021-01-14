package Generic;

import java.math.BigInteger;

/*Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
        push(x) -- Push element x onto stack.
        pop() -- Removes the element on top of the stack.
        top() -- Get the top element.
        getMin() -- Retrieve the minimum element in the stack.

 https://stackoverflow.com/questions/7770569/big-o-notation-arrays-vs-linked-list-insertions
 https://medium.com/@mckenziefiege/arrays-linked-lists-and-big-o-notation-486727b6259b
 Static Array has constant time complexity for accessing element, pushing to the end, retrieving from the end,

  IT IS A PURE ALGEBRA QUESTION: JUST THAT.
 --> WHILE ADDING, STORE IN EACH CURRENT MINIMUM NODE(X) ( 2*CURRENT_MINIMUM-PREVIOUS_MINIMUM ) EXCEPT FOR FIRST NODE OBVIOUSLY. ELSE STORE ITS ACTUAL VALUE.
 --> IT MEANS IF WE REMOVE CURRENT MINIMUM VALUE; WE CAN DERIVE MINUMUM ELEMENT FROM REMAINING ELEMENTS USING THIS NODE VALUE =2* CURRENT_MINIMUM- NODE_VALUE.
     IF CURRENT_NODE_VALUE < CURRENT_MINIMUM, CURRENT_NODE IS A CURRENT_MINIMUM NODE.
*/
public class Stack_ConstantTime_UsingArray {
    int currentTotal = 0;
    BigInteger minValue;
    BigInteger[] data = new BigInteger[1000];

    public Stack_ConstantTime_UsingArray( int value){
        data[0] = BigInteger.valueOf(value);
        this.currentTotal = 1;
        this.minValue = BigInteger.valueOf(value);
    }
    public Stack_ConstantTime_UsingArray(){
    }
    public void push(int value){
        if (currentTotal == data.length) // Initialise a new array with 1000 slots more
        {
            BigInteger [] newData = new BigInteger[data.length + 1000];
            System.arraycopy(data, 0, newData, 0, data.length-1);
            data = newData;
        }
        if (this.minValue == null ) {
            this.minValue =  BigInteger.valueOf(value);
            data[currentTotal] = BigInteger.valueOf(value);
        }
        else if (this.minValue.compareTo(BigInteger.valueOf(value)) ==1) {
            data[currentTotal] =  (BigInteger.valueOf(value).multiply(BigInteger.valueOf(2))).subtract(minValue);;
            minValue = BigInteger.valueOf(value);
        }
        else {
            data[currentTotal] =  BigInteger.valueOf(value);
        }
        currentTotal++;
    }
    public int top(){
        if (currentTotal ==0) return -1;
        // IF CURRENT_NODE_VALUE < CURRENT_MINIMUM, CURRENT_NODE IS A CURRENT_MINIMUM NODE.
        if (data[currentTotal-1].compareTo(minValue) ==-1) {
             BigInteger prevMinValue = ( minValue.multiply(BigInteger.valueOf(2))).subtract(data[currentTotal-1]) ;
            return (prevMinValue.add(data[currentTotal-1])).divide(BigInteger.valueOf(2)).intValue() ;
        }
        return data[currentTotal-1].intValue();
    }
    public void pop(){
        if (currentTotal ==0) return;
        // IF CURRENT_NODE_VALUE < CURRENT_MINIMUM, CURRENT_NODE IS A CURRENT_MINIMUM NODE.
        if (data[currentTotal-1].compareTo(minValue)==-1) minValue = minValue.multiply(BigInteger.valueOf(2)).subtract(data[currentTotal-1]) ;
        if (currentTotal ==1) {
            minValue = null;
        }
        this.currentTotal--;
    }
    public int getMin() {
        return minValue.intValue();
    }
    public static void main(String args[]){
        Stack_ConstantTime_UsingArray minStack = new Stack_ConstantTime_UsingArray();
        //["MinStack","push","      push",        "push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
             //[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]

        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);

        int ans = minStack.top();System.out.println(ans);
        minStack.pop();
        ans = minStack.getMin();System.out.println(ans);

        minStack.pop();
        ans = minStack.getMin();System.out.println(ans);
        minStack.pop();
        minStack.push(2147483647);

        ans = minStack.top();System.out.println(ans);
        ans = minStack.getMin();System.out.println(ans);
        minStack.push(-2147483648);

        ans = minStack.top();System.out.println(ans);
        ans = minStack.getMin();System.out.println(ans);
        minStack.pop();

        ans = minStack.getMin();System.out.println(ans);
    }
}