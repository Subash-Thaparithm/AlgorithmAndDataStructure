package Generic;

import java.util.Stack;

public class ValidParenthesis_Stack
{
    public boolean isValid(String s)
    {
        StackLikeDS stack = new StackLikeDS();
        stack.setLength(0);
        stack.setPrev(null);

        for (char c: s.toCharArray())
        {


            if( stack.isEmpty()) stack = stack.add(c);
            else if (c == ')' )
            {
                if (stack.getValue() == '(' ) stack.remove();
                else stack = stack.add(c);
            }
            else if (c == '}' )
            {
                if (stack.getValue() == '{' ) stack.remove();
                else stack = stack.add(c);
            }
            else if (c == ']' )
            {
                if (stack.getValue() == '[' ) stack.remove();
                else stack = stack.add(c);
            }
            else stack = stack.add(c);
        }

        if (stack.isEmpty()) return true;
        else return false;

    }

    public boolean checkValidString(String s) {
        char[] s1 = s.toCharArray();
        int length = s.length() - 1;
        int openCount = 0, closedCount = 0;
        for (int i = 0; i <= length; i++)
        {
            if (s1[i] == '*' || s1[i] == '(') openCount++;
            else openCount--;
            if (s1[length - i] == '*' || s1[length - i] == ')') closedCount++;
            else closedCount--;
            if (openCount < 0 || closedCount < 0) return false;
        }
        return true;
    }

    boolean checkValidString_Convinving(String s) {
        //ast stack should be empty as well if * does not mean empty string
        char[] array = s.toCharArray();
        Stack<Integer> open = new Stack<>();
        Stack<Integer> ast = new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            if(array[i] == ')')
            {
                if(!open.isEmpty()) open.pop();
                else if(!ast.empty()) ast.pop();
                else return false;
            }
            else if(array[i] == '(')   open.push(i);
            else ast.push(i);
        }
        while(!open.empty() && !ast.empty())
        {
            if(open.peek() > ast.peek()) return false;
            open.pop();
            ast.pop();
        }
        return open.empty();
    }

    public  static void  main(String args[])
    {
    String s= "({[[]})";
   System.out.print(new ValidParenthesis_Stack().isValid(s));
}
}

class StackLikeDS
  {
    private char value;
    private int length;
    private StackLikeDS prev;

    public StackLikeDS(char value)
    {
        this.setValue(value);

    }
    public StackLikeDS()
    {

    }
    public StackLikeDS(StackLikeDS node)
    {
     this.value = node.value;
     this.length = node.length;
     this.prev = node.prev;
    }


    public StackLikeDS add(char val)
    {
        if (length == 0)
        {
            this.setValue(val);
            this.setLength(1);
        }
        else
        {
            StackLikeDS topNode = new StackLikeDS();
            topNode.setValue(val);
            topNode.length = this.length + 1;

            topNode.setPrev(this);

            return topNode;

        }
        return this;


    }
    public void remove()
    {
       if(this.prev == null)
       {
           StackLikeDS stack = new StackLikeDS();

           this.setLength(0);
           this.setPrev(null);

           this.setValue(stack.value);


       }
       else {
           this.length = prev.length;
           this.value = prev.value;
           this.prev = prev.prev;
       }

    }
    public boolean isEmpty()
    {
        if (getLength() == 0) return  true;
        else return false;
    }





    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public StackLikeDS getPrev() {
        return prev;
    }

    public void setPrev(StackLikeDS prev) {
        this.prev = prev;
    }
}
