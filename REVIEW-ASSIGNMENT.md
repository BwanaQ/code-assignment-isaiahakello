Please provide review-comments for the code below:

``// Package name should be added`
// imports should be added
@Component
public class MyAction {
   // boolean debug can be removed since it  is set as true  and there is no statement that can change it to false in this code
    public boolean debug = true;
    
    @Autowired
    public DataSource ds;

     // we can avoid parsing many arguments, this should have been a single ListArray
    public Collection getCustomers(String firstName, String lastName, String address, String zipCode, String city) throws SQLException {
       
        Connection conn = ds.getConnection();
        //String query 1=1 can be removed since it 1 isnt a coloumn in table customers
        String query = new String("SELECT * FROM customers where 1=1");
        //repetion of if statements can be removed and only assert once that firstName is not null then
        //append all the conditions in a single statement to the String query 
        //remove '""'  eg  (query = query + " and first_name = '" + firstName + "'";) should be written as 
         // (query = query + " and firstName = :firstName";)
        if (firstName != null) {
            query = query + " and first_name = '" + firstName + "'";
        }
           
     
        if (firstName != null) {
            query = query + " and last_name = '" + firstName + "'";
        }
           
        if (firstName != null) {
            query = query + " and address = '" + address + "'";
        }
          
      
        if (firstName != null) {
            query = query + " and zip_code = '" + zipCode + "'";
        }
          
      
        if (firstName != null) {
            query = query + " and city = '" + city + "'";
        }
       
        Statement stmt = conn.createStatement();
       
        ResultSet rs = stmt.executeQuery(query);
        //List customer should be declared  as List<Customer> customers= new ArrayList();
        List customers = new ArrayList();
                
        while (rs.next()) {
        //declaring and assigning values directly to objects could have been: Object[] objects = { rs.getString(1), rs.getString(2) }
            Object[] objects = new Object[] { rs.getString(1), rs.getString(2) };
            //if(debug)  statement can be removed since the if statement will excecute regardless(debug is set to true).
            //call print directly instead
            // we can ommit parsing the integer 4, print will use it to loop 5 times  printing single space
            //which can be printed directly without looping and if need be can be declared in print
            if (debug) print(objects, 4);
            customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return customers;
    }

 // print function just prints 5 spaces, PrintUpper can be called from get customers directly
 //hence print can be deleted
      public void print(Object[] s, int indent) {
     //for loop in  can be removed, we can print 5 spaces this way: System.out.print('     ');
        for (int i=0; i<=indent; i++) System.out.print(' ');
        printUpper(s);
    }
    
    // try and catch clause can be remove in printUpper function by looping through words from i=0 ,while i less than
    // length of words  and increament i like: while(i<words.length){....
     public static void printUpper(Object [] words){
        int i = 0;
        try {
            while (true){
    //statement if(words[i].getClass() == String.class) will return 
    //an error instead use: if(words[i] instanceof java.lang.String)        
                if (words[i].getClass() == String.class) {
                //remove one semicolon and put a space between last ) and w
                    String so = (String)words[i];;
                // so can be printed directly :   System.out.println(so.toUpperCase();  
                    so = so.toUpperCase();
                    System.out.println(so);
                }
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            //iteration complete
        }
    }
}
```
