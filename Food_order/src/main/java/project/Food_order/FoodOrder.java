package project.Food_order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Date ;

public class FoodOrder {
	int Idly = 30;
    int Dosa = 45;
    int Biryani = 120;
    int Pizza = 320;
    int Fired_Rice = 80;
    int Burger = 90;
    int Parotta = 65;
    int Chapathi = 30;
    int Cholapuri = 50;
    int Noodles = 75;
    int quantity;
    static int total;
    String again;
    String TableName;
    Date date;
	
	Scanner obj=new Scanner(System.in);
	public void displayMenu() {
		System.out.println();
		System.out.println("********* Welcome to Hotel Sathish  *********    ");
		System.out.println();
		System.out.println("================== MENU =================");
		System.out.println("	1.Idly			30/-		");
		System.out.println("	2.Dosa			45/-		");
		System.out.println("	3.Biryani		120/-		");
		System.out.println("	4.Pizza			320/-		");
		System.out.println("	5.Fired_Rice		80/-		");
		System.out.println("	6.Burger		90/-		");
		System.out.println("	7.Parotta		65/-		");
		System.out.println("	8.Chapathi		30/-		");
		System.out.println("	9.Cholapuri		50/-		");
		System.out.println("	10.Noodles		75/-		");
		System.out.println("==========================================");
		System.out.println();
		
	}
	
	public void generateBill()
	{
		System.out.println();
		System.out.println("========   *** Thank you  for ordering ***   =========");
		System.out.println("********    Your bill amount is : "+ total +"   *********");
	
	}
	public void takeOrder() {
        System.out.print("Enter your Table number: ");
        TableName = obj.nextLine();
        
        displayMenu();
        order();
    }

    public void order() {
        System.out.println("Please select your order: ");
        int ch = obj.nextInt();
        String itemName = "";
        int price = 0;
        switch (ch) {
            case 1: // Idly
                itemName = "Idly";
                price = Idly;
                System.out.println("You have selected Idly");
                break;
            case 2: // Dosa
                itemName = "Dosa";
                price = Dosa;
                System.out.println("You have selected Dosa");
                break;
            case 3: // Biryani
                itemName = "Biryani";
                price = Biryani;
                System.out.println("You have selected Biryani");
                break;
            case 4: // Pizza
                itemName = "Pizza";
                price = Pizza;
                System.out.println("You have selected Pizza");
                break;
            case 5: // Fired_Rice
                itemName = "Fired_Rice";
                price = Fired_Rice;
                System.out.println("You have selected Fired_Rice");
                break;
            case 6: // Burger
                itemName = "Burger";
                price = Burger;
                System.out.println("You have selected Burger");
                break;
            case 7: // Parotta
                itemName = "Parotta";
                price = Parotta;
                System.out.println("You have selected Parotta");
                break;
            case 8: // Chapathi
                itemName = "Chapathi";
                price = Chapathi;
                System.out.println("You have selected Chapathi");
                break;
            case 9: // Cholapuri
                itemName = "Cholapuri";
                price = Cholapuri;
                System.out.println("You have selected Cholapuri");
                break;
            case 10: // Noodle
                itemName = "Noodle";
                price = Noodles;
                System.out.println("You have selected Noodle");
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        System.out.print("Enter your desired Quantity: ");
        quantity = obj.nextInt();
        int totalPrice = quantity * price;
        total = total + totalPrice;

        saveOrderToDatabase(TableName, itemName, quantity, price, totalPrice);

        System.out.println();
        System.out.println("Do you wish to order anything else? (Y/N): ");
        again = obj.next();
        System.out.println();
        if (again.equalsIgnoreCase("Y")) {
            order();
        } else if (again.equalsIgnoreCase("N")) {
            generateBill();
            
            System.exit(1);
        } else {
            System.out.println("Invalid choice");
        }
    }

    private void saveOrderToDatabase(String TableName, String itemName, int quantity, int price, int totalPrice) {
        String sql = "INSERT INTO orders (table_name, item_name, quantity, price, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//pstmt.setDate(1, new java.sql.Date(date.getTime()));  
        	//pstmt.executeUpdate();  
           // System.out.println("Date inserted successfully!"); 
            pstmt.setString(1, TableName);
            pstmt.setString(2, itemName);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, price);
            pstmt.setInt(5, totalPrice);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        }  
    }

