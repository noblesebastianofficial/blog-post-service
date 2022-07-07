Please provide review-comments for the code below:

```
@Component
public class MyAction {
    public boolean debug = true;
    @Autowired
    public DataSource ds;

    public Collection getCustomers(String firstName, String lastName, String address, String zipCode, String city) throws SQLException {
        Connection conn = ds.getConnection();
        String query = new String("SELECT * FROM customers where 1=1");
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
        List customers = new ArrayList();
        while (rs.next()) {
            Object[] objects = new Object[] { rs.getString(1), rs.getString(2) };
            if (debug) print(objects, 4);
            customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return customers;
    }

    public void print(Object[] s, int indent) {
        for (int i=0; i<=indent; i++) System.out.print(' ');
        printUpper(s);
    }

    public static void printUpper(Object [] words){
        int i = 0;
        try {
            while (true){
                if (words[i].getClass() == String.class) {
                    String so = (String)words[i];;
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
### Comments
1. Debug is always true, so its not the best way to log the messages, use sl4j for logging so that log levels can be 
   controlled 
2. DataSource auto wiring is not proper, this should be private and could have used constructor injection 
3.String in line 10 should be replaced by StringBuilder if the application is single threaded otherwise use 
   StringBuffer
   
4. Lines 11,28,29 should be in try catch block
5. Prepared statement  should be used to avoid SQL injection.
5. Connection is not closed
6.ORM is mostly preferred in application, if its simple application then JDBC is fine
7. Object[] objects = new Object[] can be replaced by String array.
8. Streams would have been used instead of for loop to print fields.
9.For while(true), there is no end condition. This will result infinite loop 
   