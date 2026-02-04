import java.io.*;
import java.sql.*;

enum OccupiedType { VILLA, APARTMENT, HOUSE }
    
class DatabaseHandlers {

    private static final String URL  = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "postgres";
    private static final String PASS = "Ani99877";

    //site
    public void insertSite(Site site) {
        String sql = "INSERT INTO Site(type, size, maintenance, paid) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, site.getType());
            ps.setString(2, site.getSize());
            ps.setInt(3, site.getMaintenance());
            ps.setInt(4, site.getPaid());
            ps.executeUpdate();

            System.out.println("Site inserted!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displaySites() {
        String sql = "SELECT * FROM Site ORDER BY site_id";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("site_id") + " | " +
                        rs.getString("type") + " | " +
                        rs.getString("size") + " | " +
                        rs.getInt("maintenance") + " | " +
                        rs.getInt("paid")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePaid(int siteId, int newPaid) {
        String sql = "UPDATE Site SET paid = ? WHERE site_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newPaid);
            ps.setInt(2, siteId);
            int rows = ps.executeUpdate();

            if (rows > 0) 
                System.out.println("Pay updated");
            else 
                System.out.println("Site not found");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateSiteDetails(int siteId, String type, String size, int maintenance, int paid) {
        String sql = "UPDATE Site SET type=?, size=?, maintenance=?, paid=? WHERE site_id=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, type);
            ps.setString(2, size);
            ps.setInt(3, maintenance);
            ps.setInt(4, paid);
            ps.setInt(5, siteId);

            int rows = ps.executeUpdate();
            if (rows > 0) 
                System.out.println("Site updated");
            else 
                System.out.println("Site not found");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteSite(int siteId) {
        String sql = "DELETE FROM Site WHERE site_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, siteId);
            int rows = ps.executeUpdate();

            if (rows > 0) 
                System.out.println("Site deleted");
            else 
                System.out.println("Site not found");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showPendingAllSites() {
        String sql = "SELECT site_id, type, size, maintenance, paid, (maintenance - paid) AS pending " +
                     "FROM Site ORDER BY site_id";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("site_id") + " | " +
                        rs.getString("type") + " | " +
                        rs.getString("size") + " | " +
                        rs.getInt("maintenance") + " | " +
                        rs.getInt("paid") + " | " +
                        rs.getInt("pending")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //user
    public void insertUser(User user) {
        String sql = "INSERT INTO Users(name, site_id) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setInt(2, user.getSiteId());
            ps.executeUpdate();

            System.out.println("User inserted!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateUserName(int userId, String newName) {
        String sql = "UPDATE Users SET name=? WHERE user_id=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();
            if (rows > 0) 
                System.out.println("User updated!");
            else 
                System.out.println("User not found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            int rows = ps.executeUpdate();

            if (rows > 0) 
                System.out.println("User deleted!");
            else 
                System.out.println("User not found");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayUsers() {
        String sql = "SELECT user_id, name, site_id FROM Users ORDER BY user_id";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("user_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("site_id")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayUsersWithSiteDetails() {
        String sql =
                "SELECT u.user_id, u.name, s.site_id, s.type, s.size, s.maintenance, s.paid, (s.maintenance - s.paid) AS pending " +
                "FROM Users u JOIN Site s ON u.site_id = s.site_id ORDER BY u.user_id";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("user_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("site_id") + " | " +
                        rs.getString("type") + " | " +
                        rs.getString("size") + " | " +
                        rs.getInt("maintenance") + " | " +
                        rs.getInt("paid") + " | " +
                        rs.getInt("pending")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showUserOwnSite(int userId) {
        String sql =
                "SELECT u.user_id, u.name, s.site_id, s.type, s.size, s.maintenance, s.paid, (s.maintenance - s.paid) AS pending " +
                "FROM Users u JOIN Site s ON u.site_id = s.site_id WHERE u.user_id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("user_id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getInt("site_id") + " | " +
                            rs.getString("type") + " | " +
                            rs.getString("size") + " | " +
                            rs.getInt("maintenance") + " | " +
                            rs.getInt("paid") + " | " +
                            rs.getInt("pending")
                    );
                } else {
                    System.out.println("User not found");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void userPayMaintenance(int userId, int newPaid) {
        String sql1 = "SELECT site_id FROM Users WHERE user_id = ?";
        String sql2 = "UPDATE Site SET paid = ? WHERE site_id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps1 = con.prepareStatement(sql1)) {

            ps1.setInt(1, userId);
            try (ResultSet rs = ps1.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("User not found");
                    return;
                }
                int siteId = rs.getInt("site_id");

                try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                    ps2.setInt(1, newPaid);
                    ps2.setInt(2, siteId);
                    ps2.executeUpdate();
                    System.out.println("Payment updated");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



class Admin {
    private DatabaseHandlers db = new DatabaseHandlers();

    public void addUser(User user) { 
        db.insertUser(user); 
    }
    public void updateUserName(int userId, String newName) { 
        db.updateUserName(userId, newName); 
    }
    public void deleteUser(int userId) { 
        db.deleteUser(userId); 
    }
    public void viewUsers() { 
        db.displayUsers(); 
    }
    public void viewUsersWithSiteDetails() { 
        db.displayUsersWithSiteDetails(); 
    }

    public void addSite(Site site) { 
        db.insertSite(site); 
    }
    public void updatePaid(int siteId, int newPaid) { 
        db.updatePaid(siteId, newPaid); 
    }
    public void editSite(int siteId, String type, String size, int maintenance, int paid) {
        db.updateSiteDetails(siteId, type, size, maintenance, paid);
    }
    public void deleteSite(int siteId) { 
        db.deleteSite(siteId); 
    }
    public void viewSites() { 
        db.displaySites(); 
    }

    public void pendingAll() { 
        db.showPendingAllSites(); 
    }
}

class User {
    private int userId;     
    private String name;
    private int siteId;

    public User(String name, int siteId) {
        this.name = name;
        this.siteId = siteId;
    }

    public User(int userId, String name, int siteId) {
        this.userId = userId;
        this.name = name;
        this.siteId = siteId;
    }

    public int getUserId() { 
        return userId; 
    }
    public String getName() { 
        return name; 
    }
    public int getSiteId() { 
        return siteId; 
    }
}


abstract class Site {
    private String size;
    private int paid;

    public Site(String size, int paid) {
        this.size = size;
        this.paid = paid;
    }

    public String getSize() { 
        return size; 
    }
    public int getPaid() { 
        return paid; 
    }

    public abstract String getType();
    public abstract int getRatePerSqft();

    public int getMaintenance() {
        return calculateMaintenance();
    }

    public int calculateMaintenance() {
        int area = getAreaFromSize(size);
        return area * getRatePerSqft();
    }

    private int getAreaFromSize(String size) {
        String clean = size.toLowerCase().trim();
        String[] parts = clean.split("x");
        int a = Integer.parseInt(parts[0].trim());
        int b = Integer.parseInt(parts[1].trim());
        return a * b;
    }
}

class OpenSite extends Site {
    public OpenSite(String size, int paid) {
        super(size, paid);
    }

    public String getType() {
        return "Open Site";
    }

    public int getRatePerSqft() {
        return 6; 
    }
}

class OccupiedSite extends Site {
    private OccupiedType occupiedType;

    public OccupiedSite(String size, int paid, OccupiedType occupiedType) {
        super(size, paid);
        this.occupiedType = occupiedType;   
    }

    public String getType() {
        
        return switch (occupiedType) {
            case VILLA -> "Villa";
            case APARTMENT -> "Apartment";
            case HOUSE -> "House";
        };
    }

    public int getRatePerSqft() {
        return 9; 
    }

    public OccupiedType getOccupiedType() {
        return occupiedType;
    }
}

public class LayoutMaintenance {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Admin admin = new Admin();
    private static DatabaseHandlers db = new DatabaseHandlers();

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("\n---------------------------");
            System.out.println("   Layout Maintenance App");
            System.out.println("---------------------------");
            System.out.println("   1. Admin");
            System.out.println("   2. Site Owner");
            System.out.println("   3. Exit");
            System.out.print("   Enter choice: ");

            int choice = Integer.parseInt(br.readLine());

            if (choice == 1) {
                adminMenu();
            } else if (choice == 2) {
                userMenu();
            } else if (choice == 3) {
                System.out.println("Exit");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    // admin
    private static void adminMenu() throws IOException {
        System.out.print("\nEnter Admin password: ");
        String pwd = br.readLine();

        if (!pwd.equals("ABC")) {
            System.out.println("Wrong password!");
            return;
        }

        while (true) {
            System.out.println("\n----------- ADMIN MENU -----------");
            System.out.println("1. View Sites");
            System.out.println("2. View Users");
            System.out.println("3. View Users and Site Details");
            System.out.println("4. Add Site");
            System.out.println("5. Delete Site");
            System.out.println("6. Add User");
            System.out.println("7. Update User Name");
            System.out.println("8. Delete User");
            System.out.println("9. Collect/Update Paid");
            System.out.println("10. Pending (All Sites)");
            System.out.println("11. Back");
            System.out.print("Enter choice: ");

            int ch = Integer.parseInt(br.readLine());

            switch (ch) {
                case 1 -> admin.viewSites();
                case 2 -> admin.viewUsers();
                case 3 -> admin.viewUsersWithSiteDetails();
                case 4 -> addSiteFlow();
                case 5 -> {
                    System.out.print("Enter site_id to delete: ");
                    int siteId = Integer.parseInt(br.readLine());
                    admin.deleteSite(siteId);
                }

                case 6 -> {
                    System.out.print("Enter user name: ");
                    String name = br.readLine();
                    System.out.print("Enter site_id for user: ");
                    int siteId = Integer.parseInt(br.readLine());
                    admin.addUser(new User(name, siteId));
                }

                case 7 -> {
                    System.out.print("Enter user_id: ");
                    int uid = Integer.parseInt(br.readLine());
                    System.out.print("Enter new name: ");
                    String newName = br.readLine();
                    admin.updateUserName(uid, newName);
                }

                case 8 -> {
                    System.out.print("Enter user_id to delete: ");
                    int uid = Integer.parseInt(br.readLine());
                    admin.deleteUser(uid);
                }

                case 9 -> {
                    System.out.print("Enter site_id: ");
                    int sid = Integer.parseInt(br.readLine());
                    System.out.print("Enter new paid amount: ");
                    int paid = Integer.parseInt(br.readLine());
                    admin.updatePaid(sid, paid);
                }

                case 10 -> admin.pendingAll();

                case 11 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void addSiteFlow() throws IOException {
        System.out.println("\nAdd Site:");
        System.out.println("1. Open Site");
        System.out.println("2. Occupied Site");
        System.out.print("Choose: ");
        int t = Integer.parseInt(br.readLine());

        System.out.print("Enter size (40x60 / 30x50 / 30x40): ");
        String size = br.readLine();
        System.out.print("Enter paid amount: ");
        int paid = Integer.parseInt(br.readLine());

        Site site;
        if (t == 1) {
            site = new OpenSite(size, paid);
        } else {
            System.out.println("Occupied type: 1.Villa 2.Apartment 3.House");
            System.out.print("Choose: ");
            int oc = Integer.parseInt(br.readLine());

            OccupiedType ot = (oc == 1) ? OccupiedType.VILLA : (oc == 2) ? OccupiedType.APARTMENT : OccupiedType.HOUSE;

            site = new OccupiedSite(size, paid, ot);
        }

        admin.addSite(site);
        System.out.println("Maintenance calculated = " + site.getMaintenance());
    }  

    //user
    private static void userMenu() throws IOException {
        System.out.print("\nEnter your user_id: ");
        int userId = Integer.parseInt(br.readLine());

        while (true) {
            System.out.println("\n----------- SITE OWNER MENU -----------");
            System.out.println("1. View My Site Details");
            System.out.println("2. Pay/Update Maintenance (Paid amount)");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            int ch = Integer.parseInt(br.readLine());

            switch (ch) {
                case 1 -> db.showUserOwnSite(userId);
                case 2 -> {
                    System.out.print("Enter new paid amount: ");
                    int paid = Integer.parseInt(br.readLine());
                    db.userPayMaintenance(userId, paid);
                }
                case 3 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}