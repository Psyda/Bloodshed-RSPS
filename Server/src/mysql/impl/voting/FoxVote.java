package mysql.impl.voting;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ruseps.model.Locations.Location;
import com.ruseps.world.World;
import com.ruseps.world.entity.impl.player.Player;

public class FoxVote implements Runnable {

	public static final String HOST = "173.212.229.171";
	public static final String USER = "fpcymnpp_ace";
	public static final String PASS = "Ux\"^Ct7ndK\"G6q=9";
	public static final String DATABASE = "fpcymnpp_vote";

	private Player player;
	private Connection conn;
	private Statement stmt;

	public FoxVote(Player player) {
		this.player = player;
	}

	public FoxVote() {
		
	}
	
	private static int VOTES;

	@Override
	public void run() {
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				System.out.println("Failing connecting to database!");
				return;
			}

			String name = player.getUsername().replace(" ", "_");
			ResultSet rs = executeQuery("SELECT * FROM fx_votes WHERE username='" + name + "' AND claimed=0 AND callback_date IS NOT NULL");
			if(rs == null) {
				return;
			}

			while (rs.next()) {
				String timestamp = rs.getTimestamp("callback_date").toString();
				String ipAddress = rs.getString("ip_address");
				int siteId = rs.getInt("site_id");

				if (player.getLocation() != Location.DUNGEONEERING) {
					player.getInventory().add(19670, 1);//vote scroll id, 1 vote scroll per vote
				} else {
					player.getBank(0).add(19670, 1);
				}
				System.out.println("[VoteSystem]Vote claimed by " + name + ". (sid: " + siteId + ", ip: " + ipAddress + ", time: " + timestamp + ")"); 

				rs.updateInt("claimed", 1); // do not delete otherwise they can
											// reclaim!
				rs.updateRow();
				if(VOTES >= 19) {
					World.sendMessage("<img=10> <col=008FB2>Another 20 votes have been claimed! Vote now using the ::vote command!");
					VOTES = 0;
				}
				VOTES++;
			}
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, pass);
			return true;
		} catch (SQLException e) {
			//System.out.println("Failing connecting to database!");
			return false;
		}
	}

	public void destroy() {
		try {
			conn.close();
			conn = null;
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int executeUpdate(String query) {
		try {
			this.stmt = this.conn.createStatement(1005, 1008);
			int results = stmt.executeUpdate(query);
			return results;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public ResultSet executeQuery(String query) {
		try {
			this.stmt = this.conn.createStatement(1005, 1008);
			ResultSet results = stmt.executeQuery(query);
			return results;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
