import java.util.*;


//--------------------------------------------merchantdata
class merchantdata{
    public String Merchantname;
    public String Merhantpin;
    public String Merchantstatus;
    public int Merchantid;
    public Dictionary<String,ArrayList<String>> merprodlist=new Hashtable<>();

    merchantdata(String name,String pin,String merstatus,int id){
        this.Merchantname=name;
        this.Merhantpin=pin;
        this.Merchantstatus=merstatus;
        this.Merchantid=id;
    }
}

//-----------------------------------------------userdata
class userdata{
    public String username;
    public String userpin;
    public int userid;
    public int usermoney;
    public Dictionary<String, ArrayList<String>> cart= new Hashtable<>();   
    public List<String> Statement=new ArrayList<>();
    public List<String> buyproduct=new ArrayList<>();

    userdata(String name,String pin,int id){
        this.username=name;
        this.userpin=pin;
        this.userid=id;
        this.usermoney=0;
    }
}

//-----------------------------------------------mainclass
public class DEMOAMAZON{
    public static Scanner sc=new Scanner(System.in);
    static Dictionary<String,ArrayList<String>> allproducts=new Hashtable<>();

//----------------------------------------------refreshscreen
    public static void refreshscreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();      
    }

//------------------------------------------------delay
    public static void delay(int delay){
        try{
            Thread.sleep(delay);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

//----------------------------------------------admindata
    public static String Adminname="admin";
    public static String Adminpass="0000";
    public static int admintry=1;

//---------------------------------------------addmerchant
    public static void addmerchant(){
        refreshscreen();
        System.out.print("enter your merchant name: ");
        String name=sc.next();
        sc.nextLine();
        System.out.print("enter your merchant password: ");
        String pin=sc.next();
        sc.nextLine();
        Merchants.add(new merchantdata(name, pin, "yes", merchantid));
        System.out.println("merchant name: "+name+"  merchant id: "+merchantid);
        System.out.println("merchant added");
        merchantid+=1;
        delay(1000);
    }

//---------------------------------------------removemerchant
    public static void removemerchant(){
        refreshscreen();
        System.out.print("enter merchant name or id: ");
        String nameid=sc.next();
        sc.nextLine();
        int thismerchant=-1;
        int i=0;
        for(merchantdata merch : Merchants){
            if(nameid.equals(Integer.toString(merch.Merchantid))
            ||nameid.equals(merch.Merchantname)){
                thismerchant=i;
            }
            i+=1;
        }

        if(thismerchant!=1){
            Merchants.remove(thismerchant);
            System.out.println("merchant removed");
            delay(1000);
            System.out.println("");
        }

        else{
            System.out.println("no such merchant found");
            delay(1000);
        }       
    }

//-----------------------------------------allmerchants
    public static void allmerchants(){
        refreshscreen();
        if(Merchants.size()>0){
            for(merchantdata merch:Merchants){
                System.out.println(
                "merchant name: "+merch.Merchantname+" "+
                "merchant id: "+merch.Merchantid+" "+
                "merchant status: "+merch.Merchantstatus);
            }
        }
        else{
            System.out.println("no merchants yet");
            delay(500);
        }
    } 
    
//-----------------------------------------merchantapproval
    public static void merchantapproval(){
        refreshscreen();
        System.out.print("enter merchant name or id: ");
        String nameid=sc.next();
        sc.nextLine();

        for(merchantdata merch:Merchants){
            if(nameid.equals(merch.Merchantname)
            ||nameid.equals(Integer.toString(merch.Merchantid))){
                merch.Merchantstatus="yes";
                System.out.println("merchant approved");
                delay(500);
            }
            else{
                refreshscreen();
                System.out.println("merchant not found");
                delay(500);
            }
        }
    }

//-----------------------------------------addproduct
    public static void addpproduct(){
        refreshscreen();
        System.out.print("enter product name: ");
        String name=sc.next();
        sc.nextLine();

        List<String> products=new ArrayList<>();
        products.add("0");
        products.add("0");

        Enumeration<String> info=allproducts.keys();
        int x=-1;

        while(info.hasMoreElements()){
            if(name.equals(info.nextElement())){
                x=1;
            }
        }
        if(x==-1){
            allproducts.put(name,(ArrayList<String>)products);
            System.out.println("product added");
            delay(500);
        }
        else{
            System.out.println("product already added");
            delay(500);
        }   
    }

//----------------------------------------------adminlogin
    public static void adminlogin(){
        refreshscreen();
        while(admintry<=3){
            refreshscreen();
            System.out.print("\twelcome mr.admin\nenter admin name: ");
            String adminname=sc.next();
            sc.nextLine();
            System.out.print("enter admin pin: ");
            String adminpass=sc.next();
            sc.nextLine();
            if(adminname.equals(Adminname)&&adminpass.equals(Adminpass)){
                refreshscreen();
                adminhomepage();
            }
            else{
                admintry+=1;
                System.out.println("");
                System.out.println("you have "+(4-admintry)+" attempts remaining");
                System.out.println("invlaid name or password");
                delay(1500);
            }
        }
    }

//-----------------------------------------adminhomepage
    public static void adminhomepage(){
        refreshscreen();
        while(true){
            refreshscreen();
            System.out.println("\twelcome\n1.lsit all merchants\n2.merchant approval");
            System.out.println("3.add merchant\n4.remove merchant");
            System.out.println("5.view all products\n6.add product\n7.home");
            int adminoption=sc.nextInt();
            if(adminoption==1){
                allmerchants();
            }
            else if(adminoption==2){
                if(Merchants.size()>0){
                    merchantapproval();
                }
                else{
                    refreshscreen();
                    System.out.println("no merchnats yet");
                    delay(500);
                }
            }
            else if(adminoption==3){
                addmerchant();
            }
            else if(adminoption==4){
                removemerchant();
            }
            else if(adminoption==5){
                refreshscreen();
                System.out.println("");
                System.out.println(allproducts+"\nenter 1 to go back: ");
                int x=sc.nextInt();
                if(x==1){}
            }
            else if(adminoption==6){
                addpproduct();
            }
            else if(adminoption==7){
                main(null);
            }
            else{
                refreshscreen();
                System.out.println("invalid option"); 
                System.out.println("try again");
                delay(1000);
            }
    }
} 
    
//------------------------------------------merchantsides
    public static int merchanttry=1;
    public static int currentmerchant=-1;
    public static int merchantid=1;

//------------------------------------------merchantarray
    static ArrayList<merchantdata> Merchants=new ArrayList<>();
   
//-------------------------------------------merchantreg
    public static void merchantreg(){
        refreshscreen();
        System.out.print("enter merhcant name: ");
        String name=sc.next();
        sc.nextLine();
        System.out.print("enter merhant password: ");
        String pin=sc.next();
        sc.nextLine();
        Merchants.add(new merchantdata(name, pin, "no", merchantid));
        System.out.println("merchantid: "+merchantid);
        merchantid+=1;
        System.out.println("waiting for approval\ntry again later");
        delay(1000);
        merchhomepage();
    }

//------------------------------------------merchantlogin
    public static void merchantlogin(){
        refreshscreen();
        while(true){
            refreshscreen();
            while(merchanttry<=3 || true){
                System.out.print("\twelcome\nenter your id: ");
                String nameid=sc.next();
                sc.nextLine();
                System.out.print("enter your pin: ");
                String pin=sc.next();
                sc.nextLine();
                int i=0;

                for(merchantdata merch:Merchants){
                    if((nameid.equals(Integer.toString(merch.Merchantid))
                    || nameid.equals(merch.Merchantname))
                    && pin.equals(merch.Merhantpin)){
                        if(merch.Merchantstatus.equals("yes")){
                            merchanttry=3;
                            currentmerchant=i;
                            merchantmainpage();
                        }
                        else{
                            System.out.println("");
                            System.out.println("merchant not approved");
                            System.out.println("try again after sometime");
                            merchhomepage();
                        }
                    }
                    i+=1;
                }
                merchanttry-=1;
                merchhomepage();
            }
        }
    }

//-------------------------------------------showmerchantproduct
    public static void showmerchantproduct(){
        refreshscreen();
        System.out.println(Merchants.get(currentmerchant).merprodlist);
        System.out.println("enter 1 to go back: ");
        int ch=sc.nextInt();
        if(ch==1){
            merchantmainpage();
        }
    }

//-------------------------------------------merchantaddproduct
    public static void merchantaddproduct(int id){
        refreshscreen();
        System.out.print("enter product name: ");
        String productname=sc.next();
        sc.nextLine();
        Enumeration<String> info=allproducts.keys();
        int x=-1;

        while(info.hasMoreElements()){
            if(productname.equals(info.nextElement())){
                x=1;
            }
        }

        if(x==-1){
            System.out.println("product not found in admin list");
            delay(700);
        }
        else{
            System.out.print("enter product count available: ");
            int count=sc.nextInt();
            System.out.print("enter product price: ");
            int prodcutprice=sc.nextInt();
            int count1=count+ Integer.parseInt(allproducts.get(productname).get(0));

            List<String> arr=new ArrayList<>();
            arr.add(Integer.toString(count1));
            arr.add(Integer.toString(prodcutprice));
            allproducts.put(productname, (ArrayList<String>) arr);
            
            List<String> arr1=new ArrayList<>();
            String y="0";

            try{
                y=Merchants.get(currentmerchant).merprodlist.get(productname).get(0);
            }
            catch(Exception e){
                System.out.println("");
                delay(2000);
            }

            count +=Integer.parseInt(y);
            arr1.add(Integer.toString(count));
            arr1.add(Integer.toString(prodcutprice));
            Merchants.get(currentmerchant).merprodlist.put(productname, (ArrayList<String>) arr1);
            System.out.println("product added");
            delay(1000);
        }
    }

//-------------------------------------------merchantuppdateproduct
    public static void merchantupdateproduct(){
        refreshscreen();
        try {
            refreshscreen();
            System.out.print("enter product name: ");
            String productname=sc.next();
            sc.nextLine();

            if(!Merchants.get(currentmerchant).merprodlist.get(productname).get(0).equals("null")){
                System.out.print("enter count1: ");
                int count=sc.nextInt();
                System.out.print("enter price: ");
                int productprice=sc.nextInt();

                List<String> newprodlist=new ArrayList<>();
                String x=Merchants.get(currentmerchant).merprodlist.get(productname).get(0);
                newprodlist.add(Integer.toString(count));
                newprodlist.add(Integer.toString(productprice));
                Merchants.get(currentmerchant).merprodlist.put(productname, (ArrayList<String>)newprodlist);

                List<String> arr=new ArrayList<>();
                int count1=(Integer.parseInt(allproducts.get(productname).get(0)))-(Integer.parseInt(x)-count);
                arr.add(Integer.toString(count1));
                arr.add(Integer.toString(productprice));
                allproducts.put(productname,(ArrayList<String>)arr);
                System.out.println("product updated");
                delay(500);
            }
        } 
        catch (Exception e){
            refreshscreen();
            System.out.println("product not found");
            delay(500);
        }
    }

//------------------------------------------merchantcompareproduct
    public static void merchantcompareproduct(){
        System.out.print("enter product name: ");
        String productname=sc.next();
        sc.nextLine();
        System.out.println("name: "+productname);
        int x=0;

        for(int i=0;i<Merchants.size();i++){
            Enumeration<String> info=Merchants.get(i).merprodlist.keys();

            while(info.hasMoreElements()){
                if(info.nextElement().equals(productname)){
                    System.out.println(
                    "merchant: "+Merchants.get(i).Merchantname+
                    "\nprice: "+Merchants.get(i).merprodlist.get(productname).get(1));
                    x+=1;
                    System.out.println("");     
                }
            }
        }
        if(x<=0){
            System.out.println("product not found");
            delay(500);
        }    
    }

//-------------------------------------------merchanthomepage
    public static void merchhomepage(){
        refreshscreen();
        System.out.println("\twelcome\n1.login\n2.new registration\n3.home");
        System.out.print("enter your option here: ");
        int merchantoption = sc.nextInt();
        if(merchantoption==1){
            merchantlogin();
        }
        else if(merchantoption==2){
            merchantreg();
        }
        else if(merchantoption==3){
            main(null);
        }
        else{
            refreshscreen();
            System.out.println("invalid option\ntry again");
            delay(1000); 
        }
    }

//--------------------------------------------merchantmainpage
    public static void merchantmainpage(){
        refreshscreen();
        while(true){
            refreshscreen();
            System.out.println("\twelcome\n1.list all productcs\n2.add product");
            System.out.println("3.update product\n4.compare product\n5.home");
            int merchantoption = sc.nextInt();
            if(merchantoption==1){
                showmerchantproduct();
            }
            else if(merchantoption==2){
                merchantaddproduct(currentmerchant);
            }
            else if(merchantoption==3){
                merchantupdateproduct();
            }
            else if(merchantoption==4){
                refreshscreen();
                merchantcompareproduct();
                System.out.print("enter 1 to go back: ");
                int one=sc.nextInt();
                if(one==1){}
            }
            else if(merchantoption==5){
                    main(null);
            }    
            else{
                refreshscreen();
                System.out.println("invalid option\ntry again");
                delay(1000);
            }                   
        }
    }

//--------------------------------------------usersides
    static int usertry=1;
    static int currentuser=-1;
    static int userid=1;

//--------------------------------------------userarray
    static ArrayList<userdata> Users=new ArrayList<>();

//--------------------------------------------userreg
    public static void userreg(){
        refreshscreen();
        System.out.print("\tnew user registration\nenter user name: ");
        String name=sc.next();
        sc.nextLine();
        System.out.print("enter user password: ");
        String pin=sc.next();
        sc.nextLine();
        Users.add(new userdata(name, pin, userid));
        System.out.println("");
        System.out.println("userid: "+userid);
        userid+=1;
        System.out.println("");
        System.out.println("registration successfull");
        System.out.println("log in with your userid and pin");
        delay(1000);
        userlogin();
    }

//--------------------------------------------userproducts
    public static void userproducts(){
        refreshscreen();
        while(true){
            refreshscreen();
            Enumeration<String> info=allproducts.keys();
            while(info.hasMoreElements()){
                String pro=(String)info.nextElement();
                System.out.println(
                "product name: "+pro+
                "  product count: "+allproducts.get(pro).get(0));
            }

            System.out.println("");
            System.out.println("1.add to cart\n2.go back");
            int ch=sc.nextInt();
            if(ch==1){
                buyuser();
            }
            else if(ch==2){
                usermainpage();
            }
            else{
                System.out.println("enter a valid option");
                delay(500);
            }
        }
    }    

//--------------------------------------------userlogin
    public static void userlogin(){
        while(usertry>=0){
            refreshscreen();
            System.out.print("\twelcome\nenter user id: ");
            String id=sc.next();
            sc.nextLine();
            System.out.print("enter user pin: ");
            String pin=sc.next();
            sc.nextLine();
            for(int i=0;i<Users.size();i++){
                if(Integer.toString(Users.get(i).userid).equals(id)
                && (Users.get(i).userpin).equals(pin)){
                    currentuser=i;
                    usermainpage();
                    break;
                }
            }
            usertry-=1;
            userhomepage();
        }
    }

//-------------------------------------------buyuser
    public static void buyuser(){
        refreshscreen();
        System.out.print("product name: ");
        String product=sc.next();
        sc.nextLine();
        int x=-1;
    
        for(int i=0;i<allproducts.size();i++){
            if(Integer.parseInt(allproducts.get(product).get(0)) >0){
                x=i;
            }
        }
        if(x==-1){
            System.out.println("out of stock");
            delay(500);
        }
        else{
            previousorders(product);;
        }
    } 

//------------------------------------------showproduct
    public static void previousorders(String productname){
        int k = 0;
        for (int i = 0; i < Merchants.size(); i++) {
            Enumeration<String> enu = Merchants.get(i).merprodlist.keys();
            while (enu.hasMoreElements()){
                if (enu.nextElement().equals(productname)
                && Integer.parseInt(Merchants.get(i).merprodlist.get(productname).get(0)) > 0) {
                    System.out.println(
                    "count: "+ Merchants.get(i).merprodlist.get(productname).get(0) + 
                    " --price: "+ Merchants.get(i).merprodlist.get(productname).get(1)+
                    " --sold by: " + Merchants.get(i).Merchantname+
                    " --merchant id: "+Merchants.get(i).Merchantid);
                    k += 1;
                }
            }
        }
        if (k <= 0){
            System.out.println(" \nproduct not found");
            delay(500);
        } else {
            System.out.println("");
            System.out.print("enter merchant id: ");
            String M_Id = sc.next();
            sc.nextLine();
            int not = 0;

            for (int i = 0; i < Merchants.size(); i++) {
                if (Integer.toString(Merchants.get(i).Merchantid).equals(M_Id)){
                    System.out.println(M_Id +"-"+ productname);
                    System.out.print("count: ");
                    int count = sc.nextInt();

                    if (Integer.parseInt(Merchants.get(i).merprodlist.get(productname).get(0)) >= count){
                        List<String> arr = new ArrayList<>();
                        arr.add(Integer.toString(Integer.parseInt(Merchants.get(i).merprodlist.get(productname).get(0)) - count));
                        arr.add(Merchants.get(i).merprodlist.get(productname).get(1));
                        Merchants.get(i).merprodlist.put(productname,(ArrayList<String>) arr);

                        List<String> arr1 = new ArrayList<>();
                        arr1.add(Integer.toString(Integer.parseInt(allproducts.get(productname).get(0)) - count));
                        arr1.add(allproducts.get(productname).get(1));
                        allproducts.put(productname, (ArrayList<String>) arr1);

                        int cou = 0;
                        try {
                            cou = Integer.parseInt(Users.get(currentuser).cart.get(productname).get(0));
                        } catch (Exception e) {
                            cou = 0;
                        }

                        List<String> arr2 = new ArrayList<>();
                        arr2.add(Integer.toString(count + cou));
                        arr2.add(Merchants.get(i).merprodlist.get(productname).get(1));
                        productname = Merchants.get(i).Merchantid+"-"+productname;
                        Users.get(currentuser).cart.put(productname, (ArrayList<String>) arr2);
                        System.out.println("");
                        System.out.println("item added to cart");
                        delay(500);
                        not = 1;
                        break;
                    } 
                    else {
                        System.out.println("");
                        System.out.println("please enter a less count1");
                        delay(500);
                    }
                }
                else {
                    not = 1;
                }
            }
            if (not == 0) {
                refreshscreen();
                System.out.println("you have not registered yet\nplease register");
                delay(500);
             }
        }
    }

//------------------------------------------usercart
    public static void usercart(){
        refreshscreen();
        while(true){
            refreshscreen();
            System.out.println(Users.get(currentuser).cart);
            System.out.println("");
            System.out.println("1.buy now\n2.go back");
            System.out.print("enter here: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("enter product name: ");
                String product = sc.next();
                sc.nextLine();

                try {
                    System.out.println("enter product count: ");
                    int count = sc.nextInt();

                    if (count <= Integer.parseInt(Users.get(currentuser).cart.get(product).get(0))){
                        if(Integer.parseInt(Users.get(currentuser).cart.get(product).get(0)) > 0){   

                            if (count * Integer.parseInt(Users.get(currentuser).cart.get(product).get(1)) 
                            <= Users.get(currentuser).usermoney){  

                                List<String> arr = new ArrayList<>();
                                arr.add(Integer.toString(
                                        Integer.parseInt(Users.get(currentuser).cart.get(product).get(0))
                                                - count));
                                arr.add(Users.get(currentuser).cart.get(product).get(1));    

                                //updateusercart
                                Users.get(currentuser).cart.put(product, (ArrayList<String>) arr);

                                //updatebuyhistory
                                Users.get(currentuser).buyproduct
                                        .add(java.time.LocalDateTime.now() + "buy now " +product + " Price: "
                                                + count
                                                        * Integer.parseInt(Users.get(currentuser).cart
                                                        .get(product).get(1)));

                                //updateuseramount
                                Users.get(currentuser).usermoney-= (count
                                        * Integer.parseInt(Users.get(currentuser).cart.get(product).get(1)));
                                System.out.println("done");
                                delay(500);
                            } 
                            else {
                                System.out.println("less amount");
                                delay(500);
                            }
                        } 
                        else {
                            System.out.println(" \nno product");
                            delay(500);
                        }
                    } 
                    else {
                        System.out.println(" \nplease enter a lesser count1");
                        delay(500);
                    }
                } 
                catch (Exception e) {
                    System.out.println(" \nno such product");
                    delay(500);
                }
            } 
            else if (ch == 2) {
                usermainpage();
            } 
            else {
                refreshscreen();
                System.out.println("invalid option\ntry again");
                delay(500);
            }
        }
    }

//-------------------------------------------wallet 
static void userwallet(){
    refreshscreen();
    while(true){
        refreshscreen();
        System.out.print("1.check balance \n2.deposite \n3.statement \n4.go back");
        int ch = sc.nextInt();
        if(ch==1){
            refreshscreen();
            System.out.println("user name: "+ Users.get(currentuser).username);
            System.out.println("balance: "+ Users.get(currentuser).usermoney);
            System.out.print("enter 1 to go back: ");
            int one=sc.nextInt();
            if(one==1){}
        }
        else if(ch==2){
            refreshscreen();
            System.out.print("enter amount: ");
            int amount=sc.nextInt();
            Users.get(currentuser). Statement.add(java.time.LocalDateTime.now()
            +" deposit "+(amount+Users.get(currentuser).usermoney));
            Users.get(currentuser).usermoney+=amount;
            System.out.println("amount added");
            delay(500);
        }
        else if(ch==3){
            refreshscreen();
            System.out.println("\tmini statement");
            for(int k=Users.get(currentuser). Statement.size()-1;k>=0;k--){
                System.out.println(Users.get(currentuser). Statement.get(k));
                delay(1000);
            }
        }
        else if(ch==4){
            usermainpage();
        }
        else{
            refreshscreen();
            System.out.println("invalid option\ntry again");
            delay(500);
        }
    }
}

//------------------------------------------userhomepage
    public static void userhomepage(){
        refreshscreen();
        System.out.println("\twelcome\n1.user login\n2.user registration\n3.home");
        System.out.print("enter your optiom here: ");
        int useroption=sc.nextInt();
        if(useroption==1){
            userlogin();
        }
        else if(useroption==2){
            userreg();
        }
        else if(useroption==3){
            main(null);
        }
        else{
            refreshscreen();
            System.out.println("invalid option\ntry again");
            delay(1000);
        }
    }
//-------------------------------------------usermainpage
    public static void usermainpage(){
        refreshscreen();
        System.out.println("\twelcome "+Users.get(currentuser).username);
        while(true){
            refreshscreen();
            System.out.println("1.show all products\n2.previous order\n3.show cart");
            System.out.println("4.wallet\n5.home");
            System.out.print("enter your option here: ");
            int useroption=sc.nextInt();
            if(useroption==1){
                userproducts();
            }
            else if(useroption==2){
                refreshscreen();
                System.out.println("previous orders");
                for (int k = Users.get(currentuser).buyproduct.size() - 1; k >= 0; k--){
                    System.out.println(Users.get(currentuser).buyproduct.get(k));
                    System.out.print("enter 1 to go back: ");
                    int one=sc.nextInt();
                    if(one==1){}
                }
            }
            else if(useroption==3){
               usercart(); 
            }
            else if(useroption==4){
                userwallet();
            }
            else if(useroption==5){
                userhomepage();
            }
            else{
                refreshscreen();
                System.out.println("invalid option\ntry again");
                delay(500);
            }
        }
    }
//-------------------------------------------mainmethod
    public static void main(String[] args){
        refreshscreen();
        while(true){
            refreshscreen();
            System.out.println("\twelcome\n1.admin\n2.merchant\n3.user\n4.exit");
            System.out.print("enter your option here: ");
            int mainoption=sc.nextInt();  
            if(mainoption==1){
                adminlogin();
            }
            else if(mainoption==2){
                merchhomepage();
            }
            else if(mainoption==3){
                userhomepage();
            }
            else if(mainoption==4){
                refreshscreen();
                System.out.println("thank you for visiting");
                System.exit(0);
            }
            else{
                refreshscreen();
                System.out.println("invalid option\ntry again");
                delay(1000);
            }
        }
    }  
}
