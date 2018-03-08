//package mysql.impl.donations;
//
//import java.sql.ResultSet;
//
//import com.ruseps.model.definitions.ItemDefinition;
//import com.ruseps.world.content.MemberScrolls;
//import com.ruseps.world.content.PlayerPanel;
//import com.ruseps.world.entity.impl.player.Player;
//
//import mysql.Database;
//
//public class Donation implements Runnable {
//
//	public static final String HOST_ADDRESS = "173.212.229.171"; // website ip address
//	public static final String USERNAME = "fpcymnpp_ace";
//	public static final String PASSWORD = "Ux\"^Ct7ndK\"G6q=9";
//	public static final String DATABASE = "fpcymnpp_cart";
//	
//	private Player player;
//	
//	@Override
//	public void run() {
//		try {
//			Database db = new Database(HOST_ADDRESS, USERNAME, PASSWORD, DATABASE);
//			
//			if (!db.init()) {
//				System.err.println("[Donation] Failed to connect to database!");
//				return;
//			}
//			
//			String name = player.getUsername().replace("_", " ");
//			ResultSet rs = db.executeQuery("SELECT * FROM payments WHERE player_name='"+name+"' AND status='Completed' AND claimed=0");
//			while(rs.next()) {
//				String item_name = rs.getString("item_name");
//				int item_number = rs.getInt("item_number");
//				double amount = rs.getDouble("amount");
//				int quantity = rs.getInt("quantity");
//				
//				ResultSet result = db.executeQuery("SELECT * FROM products WHERE item_id="+item_number);
//				
//				if (result == null || !result.next()
//						|| !result.getString("item_name").equalsIgnoreCase(item_name)
//						|| result.getDouble("item_price") != amount && amount != result.getDouble("item_price") * quantity
//						|| quantity < 1 || quantity > Integer.MAX_VALUE) {
//					System.out.println(quantity * result.getDouble("item_price"));
//					System.out.println("[Donation] Invalid purchase for "+name+" (item: "+item_name+", amount: " +amount+ " quantity: " +quantity+ " id: "+item_number+")");
//					player.getPacketSender().sendMessage("Your donation was not found. Please contact a staff member for more help.");
//					continue;
//				}
//				for(int i = 0; i < quantity; i++) {
//					handleItems(item_number);
//				}
//				rs.updateInt("claimed", 1);
//				rs.updateRow();
//			}
//			
//			db.destroyAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void handleItems(int productId) {
//		int itemId = 0;
//		int amount = 0;//these will be your int for when u do items for sale in donar store.
//		switch(productId) {
//		case 0:
//			player.getPacketSender().sendMessage("Your donation was not found. Please contact a staff member for more help.");
//			break;
//		case 47: //website item Id (not the actual item Id)
//			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//            player.incrementAmountDonated(+10); //adds total to amount donated
//            player.getPointsHandler().setDonationPoints(10, true); //adds +10 donator points
//            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
//			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
//			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
//		case 48: //website item Id (not the actual item Id)
//			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//            player.incrementAmountDonated(+20); //adds total to amount donated
//            player.getPointsHandler().setDonationPoints(20, true); //adds +20 donator points
//            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
//			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator ranksec
//			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
//		case 49: //website item Id (not the actual item Id)/
//			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//            player.incrementAmountDonated(+50); //adds total to amount donated
//            player.getPointsHandler().setDonationPoints(50, true); //adds +50 donator points/this is giving them 50 points when they donate.
//            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
//			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
//			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
//		case 50: //website item Id (not the actual item Id)
//			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//            player.incrementAmountDonated(+100); //adds total to amount donated
//            player.getPointsHandler().setDonationPoints(100, true); //adds +100 donator points
//            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
//			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
//			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
//		case 51: //website item Id (not the actual item Id)
//			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//            player.incrementAmountDonated(+250); //adds total to amount donated
//            player.getPointsHandler().setDonationPoints(250, true); //adds +250 donator points
//            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
//			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
//			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
//		case 52: //website item Id (not the actual item Id)
//			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//            player.incrementAmountDonated(+500); //adds total to amount donated
//            player.getPointsHandler().setDonationPoints(500, true); //adds +500 donator points
//            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
//			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
//			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
//		case 53: //website item Id (not the actual item Id)
//			player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//            player.incrementAmountDonated(+1000); //adds total to amount donated
//            player.getPointsHandler().setDonationPoints(1000, true); //adds +1000 donator points
//            player.getPacketSender().sendMessage("You now have " + player.getPointsHandler().getDonationPoints() + ".");
//			player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//			MemberScrolls.checkForRankUpdate(player); //checks if player has gotten a higher donator rank
//			PlayerPanel.refreshPanel(player); //refreshes player panel to show total donator points and amount donated
//			break;
//		case 54://start of item cases
//		player.getPacketSender().sendMessage("<img=10><col=6600CC>@blu@ You Have Recieved Your donation! Thank you for your support!");
//		player.incrementAmountDonated(+10);
//	    itemId = 7774;//id of item
//	    amount = 1; //ammount 
//	    player.getInventory().add(itemId, amount);
//	    player.getPacketSender().sendMessage("Adding " + amount + " " + ItemDefinition.forId(itemId) + " to your inventory!");
//		player.getPacketSender().sendMessage("Your total is now at $" + player.getAmountDonated() + ".");
//		MemberScrolls.checkForRankUpdate(player);
//		PlayerPanel.refreshPanel(player);
//		break;
//			
//		}
//	}
//	
//	public Donation(Player player) {
//		this.player = player;
//	}
//}
