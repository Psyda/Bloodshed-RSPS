package mysql.impl.donations;

import java.sql.ResultSet;

import com.ruseps.world.content.MemberScrolls;
import com.ruseps.world.content.PlayerPanel;
import com.ruseps.world.entity.impl.player.Player;

import mysql.Database;

public class FoxDonate implements Runnable {

	public static final String HOST_ADDRESS = "173.212.229.171";//i put all tht right ? im going off my memory atm lol whic part lolnv
	public static final String USERNAME = "fpcymnpp_ace22";
	public static final String PASSWORD = "kS3MXR2Eur8F";
	public static final String DATABASE = "fpcymnpp_storeV2";
	
	private Player player;
	
	@Override
	public void run() {
		try {
			Database db = new Database(HOST_ADDRESS, USERNAME, PASSWORD, DATABASE);
			
			if (!db.init()) {
				System.err.println("[Donation] Failed to connect to database!");
				return;
			}
			
			String name = player.getUsername().replace("_", " ");
			ResultSet rs = db.executeQuery("SELECT * FROM payments WHERE player_name='"+name+"' AND claimed=0");
			if(rs == null) {
				return;
			}
			
			while(rs.next()) {
				String item_name = rs.getString("item_name");
				int item_number = rs.getInt("item_number");
				double amount = rs.getDouble("amount");
				int quantity = rs.getInt("quantity");
				
				ResultSet result = db.executeQuery("SELECT * FROM products WHERE item_id="+item_number);
				
				if (result == null || !result.next()
						|| !result.getString("item_name").equalsIgnoreCase(item_name)
						|| result.getDouble("item_price") != amount && amount != result.getDouble("item_price") * quantity
						|| quantity < 1 || quantity > Integer.MAX_VALUE) {
					System.out.println(quantity * result.getDouble("item_price"));
					System.out.println("[Donation] Invalid purchase for "+name+" (item: "+item_name+", amount: " +amount+ " quantity: " +quantity+ " id: "+item_number+")");
					player.getPacketSender().sendMessage("Your donation was not found. Please contact a staff member for more help.");
					continue;
				}
				for(int i = 0; i < quantity; i++) {
					handleItems(item_number);
				}
				rs.updateInt("claimed", 1);
				rs.updateRow();
			}
			
			db.destroyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleItems(int productId) {
		switch(productId) {
		case 0:
			player.getPacketSender().sendMessage("Your donation was not found. Please contact a staff member for more help.");
			break;
			
		case 19: //website item Id (not the actual item Id)
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
            player.incrementAmountDonated(+10); //adds total to amount donated
            player.getPointsHandler().incrementDonationPoints(+10);
            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
			break;
			
		case 20: //website item Id (not the actual item Id)
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
            player.incrementAmountDonated(+20); //adds total to amount donated
            player.getPointsHandler().setDonationPoints(20, true); //adds +20 donator points
            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator ranksec
			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
			break;
			
		case 21: //website item Id (not the actual item Id)/
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
            player.incrementAmountDonated(+50); //adds total to amount donated
            player.getPointsHandler().setDonationPoints(50, true); //adds +50 donator points/this is giving them 50 points when they donate.
            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
			break;
			
		case 22: //website item Id (not the actual item Id)
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
            player.incrementAmountDonated(+100); //adds total to amount donated
            player.getPointsHandler().setDonationPoints(100, true); //adds +100 donator points
            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
			break;
			
		case 23: //website item Id (not the actual item Id)
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
            player.incrementAmountDonated(+250); //adds total to amount donated
            player.getPointsHandler().setDonationPoints(250, true); //adds +250 donator points
            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
			break;
			
		case 24: //website item Id (not the actual item Id)
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
            player.incrementAmountDonated(+500); //adds total to amount donated
            player.getPointsHandler().setDonationPoints(500, true); //adds +500 donator points
            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
			break;
			
		case 25: //website item Id (not the actual item Id)
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
            player.incrementAmountDonated(+1000); //adds total to amount donated
            player.getPointsHandler().setDonationPoints(1000, true); //adds +1000 donator points
            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
			break;
			
		case 33:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(2568, 1);
			break;
			
		case 34:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(20059, 1);
			break;
			
		case 35:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(20057, 1);
			break;
			
		case 36:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(20058, 1);
			break;
			
		case 37:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(12927, 1);
			break;
		
		case 38:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(16887, 1);
			break;
			
		case 39:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(14047, 1);
			break;
			
		case 40:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(6183, 25);
			break;
			
		case 41:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(15501, 25);
			break;
			
		case 42:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(15501, 1);
			break;
			
		case 43:
			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
			player.getInventory().add(6183, 1);
			break;
			
			
		}
	}
	
	public FoxDonate(Player player) {
		this.player = player;
	}
}
