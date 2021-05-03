package prog1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

class Account implements Serializable
{
    String accno,name;
    double balance;
    Account(){}
    Account(String a,String n,double b)
    {
        accno=a;
        name=n;
        balance=b;
    }
    public String toString()
    {
        return "Account No: "+accno+"\nName :"+name+"\nBalance:"+balance+"\n";
    }
}
public class Customer {
    public static void main(String args[]) throws Exception
    {
        Scanner gets=new Scanner(System.in);
        Account acc=null;
        HashMap<String,Account> hm=new HashMap<>();
        try
        {
            FileInputStream fis=new FileInputStream("Accounts.txt");
            ObjectInputStream ois=new ObjectInputStream(fis);
            int count=ois.readInt();   //Count how many accounts are there in that file
            for(int i=0;i<count;i++)
            {
                acc=(Account)ois.readObject();
                System.out.println(acc);
                hm.put(acc.accno,acc);//Pulling the account data from the file and filled in hashmap
            }
            fis.close();
            ois.close();
        }catch(Exception e){}
        FileOutputStream fos=new FileOutputStream("Accounts.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        int ch=0;
        String accno,name;
        double balance; 
        do
        {
            System.out.println("1.Create an Account\n2.Delete Account\n3.View Account\n4.View All Counts\n5.Save Accounts\n6.Exit");
            System.out.println("Enter your choice");
            ch=gets.nextInt();
            switch(ch)
            {
                case 1:System.out.println("Enter the Accno,name,balance");
                       accno=gets.next();
                       name=gets.next();
                       balance=gets.nextDouble();
                       acc=new Account(accno,name,balance);
                       hm.put(accno, acc);
                       System.out.println("Account Created for "+name);
                       break;
                case 2:System.out.println("Enter Accno");
                       accno=gets.next();
                       hm.remove(accno);
                       break;
                case 3:System.out.println("Enter Accno");
                       accno=gets.next();
                       acc=hm.get(accno);
                       System.out.println(acc);
                       break;
                case 4:for(Account a:hm.values())
                          System.out.println(a);
                       break;
                case 5:
                case 6: oos.writeInt(hm.size());
                        for(Account a:hm.values())
                              oos.writeObject(a);
            }
        }while(ch!=6);
         oos.close();
         oos.flush();
         fos.close();
    }
}
