package mysql.impl.donations;

import java.sql.*;
import com.ruseps.util.Misc;
import com.ruseps.model.GameMode;
import com.ruseps.model.Item;
import com.ruseps.world.entity.impl.player.Player;
import com.ruseps.world.World;
import com.ruseps.world.content.PlayerPanel;

import com.ruseps.world.content.PointsHandler;
import com.ruseps.model.PlayerRights;

public class AutoDonations implements Runnable {

	public static Connection con = null;
	public static Statement stm;
	
	Player c = null;
	
	public AutoDonations(Player player) {
		this.c = player;
	}
	
	public void run() {
		Connection connection = null;
		String name = c.getUsername();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
			} catch (Exception e) {
				e.printStackTrace();
				connection = null;
			}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://ns536923.ip-144-217-68.net/simplici_donate", "simplici_main", "H#tDHaFx4jWjo]");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		
		

		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM donation WHERE username = ? AND claimed = 0");
				statement.setString(1, name);
				ResultSet rs = statement.executeQuery();
				boolean b = false;
				while(rs.next()){
					int prod = Integer.parseInt(rs.getString("productid"));
					int price = Integer.parseInt(rs.getString("price"));
					if(prod == 1 && price == 5){
						c.getPointsHandler().incrementDonationPoints(5);
						c.incrementAmountDonated(5);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 2 && price == 10){
						c.getPointsHandler().incrementDonationPoints(10);
						c.incrementAmountDonated(10);
						c.getPointsHandler().refreshPanel();
						
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

						b = true;
					} else if(prod == 3 && price == 15){
						c.getPointsHandler().incrementDonationPoints(15);
						c.incrementAmountDonated(15);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 4 && price == 20){
						c.getPointsHandler().incrementDonationPoints(20);
						c.incrementAmountDonated(20);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 5 && price == 25){
						c.getPointsHandler().incrementDonationPoints(30);
						c.incrementAmountDonated(25);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 6 && price == 30){
						c.getPointsHandler().incrementDonationPoints(30);
						c.incrementAmountDonated(30);
					c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 7 && price == 35){
						c.getPointsHandler().incrementDonationPoints(35);
						c.incrementAmountDonated(35);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 8 && price == 40){
						c.getPointsHandler().incrementDonationPoints(40);
						c.incrementAmountDonated(40);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					
					} else if(prod == 9 && price == 45){
						c.getPointsHandler().incrementDonationPoints(45);
						c.incrementAmountDonated(45);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 10 && price == 50){
						c.getPointsHandler().incrementDonationPoints(65);
						c.incrementAmountDonated(50);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 11 && price == 55){
						c.getPointsHandler().incrementDonationPoints(55);
						c.incrementAmountDonated(55);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 12 && price == 60){
						c.getPointsHandler().incrementDonationPoints(60);
						c.incrementAmountDonated(60);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);;
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 13 && price == 65){
						c.getPointsHandler().incrementDonationPoints(65);
						c.incrementAmountDonated(65);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 14 && price == 70){
						c.getPointsHandler().incrementDonationPoints(70);
						c.incrementAmountDonated(70);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 15 && price == 75){
						c.getPointsHandler().incrementDonationPoints(105);
						c.incrementAmountDonated(75);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 16 && price == 80){
						c.getPointsHandler().incrementDonationPoints(80);
						c.incrementAmountDonated(80);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 17 && price == 85){
						c.getPointsHandler().incrementDonationPoints(85);
						c.incrementAmountDonated(85);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 18 && price == 90){
						c.getPointsHandler().incrementDonationPoints(90);
						c.incrementAmountDonated(90);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 19 && price == 95){
						c.getPointsHandler().incrementDonationPoints(95);
						c.incrementAmountDonated(95);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} else if(prod == 20 && price == 100){
						c.getPointsHandler().incrementDonationPoints(150);
						c.incrementAmountDonated(100);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} 
					else if(prod == 21 && price == 125){
						c.getPointsHandler().incrementDonationPoints(185);
						c.incrementAmountDonated(125);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} 
					else if(prod == 22 && price == 150){
						c.getPointsHandler().incrementDonationPoints(230);
						c.incrementAmountDonated(150);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} 
					else if(prod == 23 && price == 175){
						c.getPointsHandler().incrementDonationPoints(270);
						c.incrementAmountDonated(175);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} 
					else if(prod == 24 && price == 200){
						c.getPointsHandler().incrementDonationPoints(310);
						c.incrementAmountDonated(200);
						c.getPointsHandler().refreshPanel();
						PlayerPanel.refreshPanel(c);
						checkForRankUpdate(c);
						b = true;
				        World.sendAdminMessage("@red@[ADMIN]@bla@ " +c.getUsername()+ " Has just claimed donation");

					} 
				}
				
				if(b){
					statement = connection.prepareStatement("UPDATE donation SET claimed = 1 WHERE username = ?");
					statement.setString(1, name);
					statement.executeUpdate();
				}

				c.getPacketSender().sendMessage("Thanks for donating. You have recieved your donation points!");
			} catch (Exception e) {
				c.getPacketSender().sendMessage("Couldn't find donation");
				e.printStackTrace();
				connection = null;
			}
		}
		return;
	}

	public static void checkForRankUpdate(Player player) {
		if(player.getRights().isStaff()) {
			return;
		}
		if(player.getGameMode() == GameMode.IRONMAN) {
			player.getPacketSender().sendMessage("@red@You did not recieve donator rank because you are an iron man!");
			return;
		}
		if(player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
			player.getPacketSender().sendMessage("You did not recieve donator rank because you are an iron man!");
			return;
		}
		
		PlayerRights rights = null;
		if(player.getAmountDonated() >= 20)
			rights = PlayerRights.BRONZE_MEMBER;
		if(player.getAmountDonated() >= 50)
			rights = PlayerRights.SILVER_MEMBER;
		if(player.getAmountDonated() >= 100)
			rights = PlayerRights.GOLD_MEMBER;
		if(player.getAmountDonated() >= 250)
			rights = PlayerRights.PLATINUM_MEMBER;
		if(player.getAmountDonated() >= 350)
			rights = PlayerRights.DIAMOND_MEMBER;
		if(player.getAmountDonated() >= 700)
			rights = PlayerRights.RUBY_MEMBER;
		if(rights != null && rights != player.getRights()) {
			player.getPacketSender().sendMessage("You've become a "+Misc.formatText(rights.toString().toLowerCase())+"! Congratulations!");
			player.setRights(rights);
			player.getPacketSender().sendRights();
		}
	}
	
}