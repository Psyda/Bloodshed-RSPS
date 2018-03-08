package mysql.impl.Highscores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.ruseps.model.GameMode;
import com.ruseps.model.PlayerRights;
import com.ruseps.model.Skill;
import com.ruseps.world.entity.impl.player.Player;
 
public class FoxHighscores implements Runnable {

	public static final String HOST = "173.212.229.171"; //
	public static final String USER = "fpcymnpp_ace";
	public static final String PASS = "Ux\"^Ct7ndK\"G6q=9";
	public static final String DATABASE = "fpcymnpp_highscores";

	public static final String TABLE = "hs_users";
	
	private Player player;
	private Connection conn;
	private Statement stmt;
	
	public FoxHighscores(Player player) {
		this.player = player;
	}
	
	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void run() {
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				return;
			}
			if(player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.MANAGER || player.getRights() == PlayerRights.ADMINISTRATOR || player.getRights() == PlayerRights.OWNER) {
				return;
			}
			if(player.getUsername().equalsIgnoreCase("wiglets") || player.getUsername().equalsIgnoreCase("soniq pker")
					|| player.getUsername().equalsIgnoreCase("conor") || player.getUsername().equalsIgnoreCase("lootz")){
				return;
			}
			
			String name = player.getUsername();
			
			PreparedStatement stmt1 = prepare("DELETE FROM "+TABLE+" WHERE username=?");
			stmt1.setString(1, player.getUsername());
			stmt1.execute();
				
			PreparedStatement stmt2 = prepare(generateQuery());
			stmt2.setString(1, player.getUsername());
			stmt2.setInt(2, player.getRights().ordinal());
			
			if(player.getGameMode() == GameMode.NORMAL) {
				stmt2.setInt(3, 0);
			} else if(player.getGameMode() == GameMode.IRONMAN) {
				stmt2.setInt(3, 1);
			} else if(player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
				stmt2.setInt(3, 2);
			}
			stmt2.setInt(4, player.getSkillManager().getTotalLevel()); // total level

			stmt2.setLong(5, player.getSkillManager().getTotalExp());
			
			for (int i = 0; i < 25; i++)
				stmt2.setInt(6 + i, (int)player.getSkillManager().getExperience(Skill.forId(i)));
			stmt2.execute();
			
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepare(String query) throws SQLException {
		return conn.prepareStatement(query);
	}
	
	public void destroy() {
        try {
    		conn.close();
        	conn = null;
        	if (stmt != null) {
    			stmt.close();
        		stmt = null;
        	}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public static String generateQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+TABLE+" (");
		sb.append("username, ");
		sb.append("rights, ");
		sb.append("mode, ");
		sb.append("total_level, ");
		sb.append("overall_xp, ");
		sb.append("attack_xp, ");
		sb.append("defence_xp, ");
		sb.append("strength_xp, ");
		sb.append("constitution_xp, ");
		sb.append("ranged_xp, ");
		sb.append("prayer_xp, ");
		sb.append("magic_xp, ");
		sb.append("cooking_xp, ");
		sb.append("woodcutting_xp, ");
		sb.append("fletching_xp, ");
		sb.append("fishing_xp, ");
		sb.append("firemaking_xp, ");
		sb.append("crafting_xp, ");
		sb.append("smithing_xp, ");
		sb.append("mining_xp, ");
		sb.append("herblore_xp, ");
		sb.append("agility_xp, ");
		sb.append("thieving_xp, ");
		sb.append("slayer_xp, ");
		sb.append("farming_xp, ");
		sb.append("runecrafting_xp, ");
		sb.append("construction_xp, ");
		sb.append("hunter_xp, ");
		sb.append("summoning_xp, ");
		sb.append("dungeoneering_xp) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sb.toString();
	}
	
}