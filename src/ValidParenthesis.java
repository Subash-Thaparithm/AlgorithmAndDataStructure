public class ValidParenthesis
{
    public boolean isValid(String s)
    {
        Top stack = new Top();
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

    public  static void  main(String args[])
{
    String s= "({[[]})";
   System.out.print(new ValidParenthesis().isValid(s));
}
}

class Top
{
    private char value;
    private int length;
    private Top prev;

    public Top(char value)
    {
        this.setValue(value);

    }
    public Top()
    {

    }
    public Top(Top node)
    {
     this.value = node.value;
     this.length = node.length;
     this.prev = node.prev;
    }


    public Top add(char val)
    {
        if (length == 0)
        {
            this.setValue(val);
            this.setLength(1);
        }
        else
        {
            Top topNode = new Top();
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
           Top stack = new Top();

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

    public Top getPrev() {
        return prev;
    }

    public void setPrev(Top prev) {
        this.prev = prev;
    }
}
// Definition for a binary tree ListNode.
/*
class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}*/
