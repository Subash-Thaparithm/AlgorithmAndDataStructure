package BalancedParenthesis;

public class balancedParenthesis
{
    public boolean isValid(String s)
    {
        StackLikeDS stack = new StackLikeDS();
        stack.setLength(0);
        stack.setTop(null);

        for (char c: s.toCharArray())
        {

         StackLikeDS n = new StackLikeDS(c);

         if( stack.isEmpty()) stack.add(n);
         else if (c == ')' )
         {
             if (stack.getTop().getValue() == '(' ) stack.remove();
             else stack.add(n);
         }
         else if (c == '}' )
         {
             if (stack.getTop().getValue() == '{' ) stack.remove();
             else stack.add(n);
         }
         else if (c == ']' )
         {
             if (stack.getTop().getValue() == '[' ) stack.remove();
             else stack.add(n);
         }

        }

        if (stack.isEmpty()) return true;
        else return false;

    }

    public  static void  main(String args[])
    {
      String s= "({[[]]})";
      new balancedParenthesis().isValid(s);
    }
}

class StackLikeDS
{
    private StackLikeDS top;
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

    public void add(StackLikeDS val)
    {
        this.setPrev(getTop());
        this.setTop(val);
       this.setLength(this.getLength() + 1);
    }
    public void remove()
    {
      this.setTop(this.getPrev());
      setLength(getLength() - 1);
    }
    public boolean isEmpty()
 {
     if (getLength() == 0) return  true;
     else return false;
 }

    public StackLikeDS getTop() {
        return top;
    }

    public void setTop(StackLikeDS top) {
        this.top = top;
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