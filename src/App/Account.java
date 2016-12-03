package App;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Account {
    private Map<Integer, List<String>> account = new HashMap<>();
    private Random random = new Random();
    private String[] firstNames = "Shaun,Sheena,Zana,Marica,Camie,Forest,Hilton,Shaunta,Carly,Arlena,Elias,Edyth,Janita,Jolyn,Merry,Kai,Cristobal,Tashina,Marsha,Hermine,Ernie,Kelle,Loyd,Katlyn,Melinda,Rosenda,Rebeca,Francoise,Dick,Alanna,Renetta,Zulma,Yael,Carson,Pura,Jane,Antonio,Chiquita,Dorothy,Claudine,Ara,Jeri,Weston,Kaila,Kesha,Thresa,Usha,Rosann,Janee,Sharen".split(",");
    private String[] lastNames  = "Novak,Owens,Chandler,Donovan,Simon,Perry,Pham,Hester,Peterson,Vaughan,Pitts,Lozano,Shah,Phelps,Foster,Stark,Arroyo,Rice,Wu,Oneal,Mclaughlin,Rosario,Stokes,Wallace,Irwin,Sandoval,Decker,Leach,Briggs,Cochran,Mitchell,Werner,Tapia,Parrish,Moss,Clark,Mccullough,Delacruz,Howard,Krueger,Schaefer,Farrell,Peck,Gilbert,Weber,Bass,Mcgrath,Acevedo,Benton,Pace".split(",");
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String input;
    public Account(int num) throws Exception{
        generateAccounts(num);
        System.out.println(account);
        setUp();
    }

    private String getName() {
        return firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
    }

    private void generateAccounts(int x) {
        for(int i = 0; i <  x; i++) {
            List<String> temp = new ArrayList<>();
            // 1. USNAME
            temp.add(getName());
            // 2. PIN
            temp.add(String.valueOf(random.nextInt(1000 + 900000000 )).substring(3, 7));
            // 3. ACC BAL
            temp.add(String.valueOf(random.nextInt(1 + 99000)));
            // 4. CRED LIM
            temp.add(String.valueOf(random.nextInt(1 + 2000)));
            account.put(random.nextInt(8000 * 1000), temp);
        }
    }


    private void setUp() throws Exception{
        do {
            // 1. Get user to enter account number
            System.out.println("Please enter your account number: ");
            String accN = br.readLine();
            if(accN.isEmpty()) {
                System.out.println("PLEASE ENTER YOUR ACCOUNT NUMBER");
            } else {
                int accNum = Integer.parseInt(accN);
                // 2. If account exists with account number search for it and get user to enter pin.
                if (account.containsKey(accNum)) {
                    List<String> acc = account.get(accNum);
                    System.out.println("Please enter PIN: ");
                    input = br.readLine();
                    String pin = input;
                    if (acc.get(1).equals(pin)) {
                        System.out.println("Welcome " + acc.get(0) + ", how may we assist you today? \n \t 1. Account Balance \n \t 2. Withdraw  \n \t 3. Exit");
                        String option = br.readLine();
                        if(option.isEmpty()) {
                            System.out.println("PLEASE CHOOSE AN OPTION");
                        } else {
                            do {
                                switch (option) {
                                    case "1":
                                        System.out.println("£" + getAccountBalance(accNum));
                                        System.out.println("How else may we assist you today?");
                                        option = br.readLine();
                                        break;
                                    case "2":
                                        System.out.println("How much would you like to withdraw today? \n \t 1. £5.00 \n \t 2. £10.00 \n \t 3. £20.00");
                                        String o = br.readLine();
                                        if(o.isEmpty()) {
                                            System.out.println("ERROR: PLEASE TRY AGAIN");
                                            option = "1";
                                        }
                                        doWithdrawal(accNum, Integer.parseInt(o));
                                        System.out.println("How else may we assist you today?");
                                        option = br.readLine();
                                        break;
                                    case "3":
                                        setUp();
                                        break;
                                    default:
                                        System.out.println("Please choose an option \t 1. Account Balance \n" + " \t 2. Withdraw  \n" + " \t 3. Exit");
                                        option = br.readLine();
                                }
                            } while (!option.equals(""));
                        }
                    } else {
                        System.out.println("ERROR: INCORRECT PIN");
                    }
                } else {
                    System.out.println("ERROR: ACCOUNT NOT FOUND");
                }

            }
        } while(true);
    }

    private String getAccountBalance(int accountNum) {
        return account.get(accountNum).get(2);
    }

    private void doWithdrawal(int accNum, int opt) {
        int bal = 0;
        int cre = 0;
        int amount = 0;
        if(opt == 1) {
            amount = 5;
        } else if(opt == 2) {
            amount = 10;
        } else if(opt == 3) {
            amount = 20;
        }
        if(account.containsKey(accNum)) {
            bal = Integer.parseInt(account.get(accNum).get(2));
            cre = Integer.parseInt(account.get(accNum).get(3));
            if(amount < bal | amount > bal && amount < cre ) {
                account.get(accNum).add(2, String.valueOf(bal - amount));
            } else {
                System.out.println("You do not have available funds");
            }
        }
    }

    public String Exception() {
        return "ERROR";
    }

}

