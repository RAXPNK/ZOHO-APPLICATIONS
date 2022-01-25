import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class DEMOATM
{
    public static  int[] notes={0,0,0,0};
    public static int[] amount={100,200,500,2000};
    public static int[] depositnotes=new int[4];
    public static String[] username={"rax","nirmala","sri"};
    public static String[] userpin={"1111","2222","3333"};
    public static String[] userid={"acc01","acc02","acc03"};
    public static int[] userbal={5000,3000,2000};
    public static String[][] usermin=new String[username.length][7];
    public static Scanner sc=new Scanner(System.in);
    public static SimpleDateFormat sdf= new SimpleDateFormat("dd//MM//yyyy HH:mm:ss");
    public static Date date=new Date();

    //admindeposit
    public static void admindeposit(){
        refreshscreen();
        System.out.println("\tWELCOME Mr.ADMIN");
        System.out.print("NUMBER OF 2000 NOTES : ");
        notes[3]=notes[3]+sc.nextInt();
        System.out.print("NUMBER OF 500 NOTES : ");
        notes[2]=notes[2]+sc.nextInt();
        System.out.print("NUMBER OF 200 NOTES : ");
        notes[1]=notes[1]+sc.nextInt();
        System.out.print("NUMBER OF 100 NOTES : ");
        notes[0]=notes[0]+sc.nextInt();
        System.out.println("AMOUNT DEPOSITED SUCCESSFULLY!!!");
        System.out.println("ENTER 1 TO CONTINUE");
        int one=sc.nextInt();
        if(one==1){}
    }

    //adminbalance
    public static void adminbalance(){
        refreshscreen();
        System.out.println("\tWELCOME Mr.ADMIN");
        int c2000=notes[3];
        int c500=notes[2];
        int c200=notes[1];
        int c100=notes[0];
        System.out.println("NUMBER OF 2000 NOTES : "+c2000);
        System.out.println("NUMBER OF 500 NOTES : "+c500);      
        System.out.println("NUMBER OF 200 NOTES : "+c200);      
        System.out.println("NUMBER OF 100 NOTES : "+c100);
        System.out.println("ENTER 1 TO CONTINUE");
        int one=sc.nextInt();
        if(one==1){}            
    }
    

    //changepin
    public static void changepin(int i){
        refreshscreen();
        System.out.println("\tWELCOME "+username[i].toUpperCase());
        System.out.print("ENTER NEW PIN : ");
        String newpin=sc.next();
        sc.nextLine();
        System.out.print("CONFIRM NEW PIN : ");
        String cnewpin=sc.next();
        sc.nextLine();
        if(newpin.equals(cnewpin)){
            userpin[i]=newpin;
        }
        refreshscreen();
    }

    //userdeposit
    public static void userdeposit(int i){
        refreshscreen();
        System.out.println("\tWELCOME "+username[i].toUpperCase());
        System.out.println();
        System.out.print("ENTER THE NUMBER OF 2000 NOTES : ");
        int tt=sc.nextInt();
        notes[3]=notes[3]+tt;
        System.out.print("ENTER THE NUMBER OF 500 NOTES : ");
        int fh=sc.nextInt();
        notes[2]=notes[2]+fh;
        System.out.print("ENTER THE NUMBER OF 200 NOTES : ");
        int th=sc.nextInt();
        notes[1]=notes[1]+th;
        System.out.print("ENTER THE NUMBER OF 100 NOTES : ");
        int oh=sc.nextInt();
        notes[1]=notes[1]+oh;
        int totalamount=(tt*2000)+(fh*500)+(th*200)+(oh*100);
        userbal[i]=userbal[i]+totalamount;
        System.out.println("AMOUNT DEPOSITED SUCCESFULLY!!!");
        System.out.println("ENTER 1 TO CONTINUE");
        usermin[i][0]=usermin[i][1];
        usermin[i][1]=usermin[i][2];
        usermin[i][3]=usermin[i][4];
        usermin[i][4]=usermin[i][5];
        usermin[i][5]=sdf.format(date) + " Rs: "+totalamount+" deposited ";
        int one=sc.nextInt();
        if(one ==1){}
        refreshscreen();
    }

    //transaction
    public static void transfer(int i){
        refreshscreen();
        System.out.println("\tWELCOME "+(username[i]).toUpperCase());
        System.out.println();
        System.out.print("ENTER ACCOUNT NUMBER TO TRANSFER : ");
        String accnum=sc.next();
        sc.nextLine();
        int transferid=0;
        int flag=0;
        for(int x=0;x<userid.length;x++){
            if(accnum.equals(userid[x])){
                transferid=x;
                flag=1;
                break;
            }
        }
        if(flag==1){
            System.out.print("ENTER THE AMOUNT TO BE TRANSFERED TO "+username[transferid].toUpperCase()+" : ");
            int amount=sc.nextInt();
            if(userbal[i]>=amount){
                userbal[i]-=amount;
                userbal[transferid]+=amount;
                usermin[i][0]=usermin[i][1];
                usermin[i][1]=usermin[i][2];
                usermin[i][2]=usermin[i][3];
                usermin[i][3]=usermin[i][4];
                usermin[i][4]=usermin[i][5];
                usermin[i][5]=sdf.format(date) + " Rs: "+amount+" transfered to "+username[transferid].toUpperCase();
                System.out.println("TRANSACTION SUCCESSFULL!!!");
            }
            else{
                System.out.println("INSUFFICIENT BALANCE!!!");
            }
        }
        else{
            System.out.println("INVALID ACCOUNT NUMBER!!!");
        }
        System.out.println("ENTER 1 TO CONTINUE");
        int one=sc.nextInt();
        if(one==1){}
    }

    //userbalance
    public static void userbalance(int i){
        refreshscreen();
        System.out.println("\tWELCOME "+username[i].toUpperCase());
        System.out.println("YOUR BALANCE : "+userbal[i]);
        System.out.println("PRESS 1 TO CONTINUE");
        int one=sc.nextInt();
        if(one==1){}
    }

    //withdraw
    public static void withdraw(int i){
        refreshscreen();
        System.out.println("\tWELCOME "+username[i].toUpperCase());
        System.out.println();
        System.out.print("ENTER AMOUNT TO BE WITHDRAWN : ");
        int wd=sc.nextInt();
        int wd1=wd;
        int wd2=wd;
        int cn1[]=new int[4];
        int cn2[]=new int[4];
        int count=0;
        int count1=0;     
        int count2=0; 
        if(count2<-2){System.out.println();}
        int count3=0;
        for(int j=0;j<4;j++){
            cn2[j]=notes[j];
            count3+=notes[j];
        }
        int wamount=(notes[3]*2000)+(notes[2]*500)+(notes[1]*200)+(notes[0]*100);
        if(wd%10==0 && wd<=wamount){
            v:while(wd>0){
                if(count3!=0){
                    if(wd1>=2000 && cn2[3]!=0){
                        cn2[3]--;
                        cn1[3]++;
                        wd1=wd1-2000;
                    }
                    else if(wd1>=500 && cn2[2]!=0){
                        cn2[2]--;
                        cn1[2]++;
                        wd1=wd1-500;
                    }
                    else if(wd1>=200 && cn2[1]!=0){
                        cn2[1]--;
                        cn1[1]++;
                        wd1=wd1-200;
                    }
                    else if(wd1>=100 && cn2[0]!=0){
                        cn2[0]--;
                        cn1[0]++;
                        wd1=wd1-100;
                    }
                    count3--;
                }
                else{
                    break v;
                }
            }
        }
        for(int j=0;j<4;j++){
            count2+=cn1[j];
            if(cn1[j]!=0){
                count1++;
                if(cn1[j]<=notes[j])
                count+=1;
            }
        }
        int main2000=0;int main500=0;int main200=0;int main100=0;
        if(wd<=wamount && wd<=userbal[i] && count==count1){
        if(wd%10==0){
            while(wd>0){
                if(wd>=2000 && cn1[3]!=0){
                    main2000++;
                    notes[3]--;
                    cn1[3]--;
                    wd=wd-2000;
                }
                if(wd>=500 && cn1[2]!=0){
                    main500++;
                    notes[2]--;
                    cn1[2]--;
                    wd=wd-500;
                }
                if(wd>=200 && cn1[1]!=0){
                    main200++;
                    notes[1]--;
                    cn1[1]--;
                    wd=wd-200;
                }
                if(wd>=100 && cn1[0]!=0){
                    main100++;
                    notes[0]--;
                    cn1[0]--;
                    wd=wd-100;
                }
            }
            userbal[i]-=wd2;
            System.out.println("2000 NOTES : "+main2000);
            System.out.println("500 NOTES : "+main500);
            System.out.println("200 NOTES : "+main200);
            System.out.println("100 NOTES : "+main100);
            System.out.println("THANK YOU FOR VISITING!!!");
        }
        else{
            System.out.println("ENTER AMOUNT ONLY IN HUNDREDS");
        }
        }
        else{
            System.out.println("INSUFFICIENT MONEY IN ATM MACHINE!!!");
            System.out.println("PLEASE VISIT NEARBY ATM!!!");
        }
        if(wd>0){
        usermin[i][0]=usermin[i][1];
        usermin[i][1]=usermin[i][2];
        usermin[i][3]=usermin[i][4];
        usermin[i][4]=usermin[i][5];
        usermin[i][5]=sdf.format(date) + " Rs: "+wd+" withdrawn";
        }
        System.out.println("PRESS 1 TO CONTINUE");
        int one=sc.nextInt();
        if(one==1){}
    }

    //ministatement
    public static void ministatement(int i){
        refreshscreen();
        System.out.println("\t  WELCOME "+username[i].toUpperCase());
        System.out.println("\t-MINI STATEMENT-");
        for(int j=0;j<7;j++){
            String xxx=usermin[i][6];
            if(usermin[i][j]!=xxx){
                System.out.println(usermin[i][j]);
            }
        }
        System.out.println("PRESS 1 TO CONTINUE");
        int one=sc.nextInt();
        if(one==1){}
    }

    //delay
    public static void delay(int delay){
        try{
            Thread.sleep(delay);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    //refreshscreen
    public static void refreshscreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[]args){
        int block=0;
        while(true){
            System.out.println("\tWELCOME TO ATM\n1.ADMIN\n2.USER\n3.EXIT");
            System.out.println();
            System.out.print("ENTER YOUR OPTION HERE : ");
            int mainoption=sc.nextInt();
            if(mainoption==1){
                a:while(true){
                    refreshscreen();
                    System.out.print("ENTER ADMINNAME : ");
                    String aname=sc.next();
                    sc.nextLine();
                    System.out.print("ENTER ADMIN PASSWORD : ");
                    String apassword=sc.next();
                    sc.nextLine();
                    refreshscreen();
                    if(aname.equals("admin") && apassword.equals("0000")){
                        while(true){
                            System.out.println("\tWELCOME Mr.ADMIN\n1.LOAD AMOUNT\n2.SHOW AMOUNT\n3.HOME");
                            int adminoption=sc.nextInt();
                            if(adminoption==1){
                                admindeposit();
                            }
                            else if(adminoption==2){
                                adminbalance();
                            }
                            else if(adminoption==3){
                                break a;
                            }
                            else{
                                System.out.println("INVALID INPUT!!!");
                                System.out.println("PLEASE CHOOSE FROM OPTIONS ABOVE!!!");
                                delay(4000);
                            }
                        }    
                    }
                    else{
                        System.out.println("INVALID ADMINNAME OR PASSWORD");
                        delay(2000);
                    }
                }
            }
            if(mainoption==2){
                refreshscreen();
                System.out.print("ENTER USERNAME : ");
                String uname=sc.next();
                sc.nextLine();
                System.out.print("ENTER PIN : ");
                String upin=sc.next();
                sc.nextLine();
                for(int i=0;i<uname.length();i++){
                    if(block!=3){
                        if(uname.equals(username[i]) && upin.equals(userpin[i])){
                            block=0;
                            a:while(true){
                                refreshscreen();
                                System.out.println("\tWELCOME "+(username[i]).toUpperCase());
                                System.out.println();
                                System.out.print("1.DEPOSIT AMOUNT\n2.WITHDRAW AMOUNT\n3.SHOW BALANCE AMOUNT\n4.TRANSFER AMOUNT\n5.CHANGE PIN\n6.MINI SATEMENT\n7.HOME");
                                System.out.println("");
                                System.out.print("ENTER YOUR OPTION HERE : ");
                                int useroption=sc.nextInt();
                                if(useroption==1){
                                    userdeposit(i);
                                }
                                else if(useroption==2){
                                    withdraw(i);
                                }
                                else if(useroption==3){
                                    userbalance(i);
                                }
                                else if(useroption==4){
                                    transfer(i);
                                }
                                else if(useroption==5){
                                    changepin(i);
                                }
                                else if(useroption==6){
                                    System.out.println("WORKING ON MINI STATEMENT");
                                    delay(5000);
                                    ministatement(i);     
                                }
                                else if(useroption==7){
                                    break a;
                                }
                                else{
                                    System.out.println("INVALID INPUT!!!");
                                    delay(2000);
                                }
                            }
                        }
                        else{
                            System.out.println("INVLAID USERNAME OR PASSWORD!!!");
                            break;
                        }
                    }
                    else{
                        System.out.println("YOUR ACCOUNT IS TEMPORARILY BLOCKED!!!");
                        System.out.println("PLEASE TRY AGAIN AFTER SOME TIME!!!");
                    }
                }
            }
            else if(mainoption==3){
                System.out.println("THANK YOU FOR COMING....");
                break;
            }
            refreshscreen();
        }
    }
}