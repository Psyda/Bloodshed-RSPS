package com.ruseps.world.content.skill.impl.prayer;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.*;
import com.ruseps.model.definitions.ItemDefinition;
import com.ruseps.model.input.impl.EnterAmountOfBonesToSacrifice;
import com.ruseps.util.Misc;
import com.ruseps.world.content.Achievements;
import com.ruseps.world.content.Achievements.AchievementData;
import com.ruseps.world.content.transportation.TeleportHandler;
import com.ruseps.world.entity.impl.player.Player;


public class BonesOnAltar {

	public static void openInterface(Player player, int itemId) {

        player.getSkillManager().stopSkilling();
		player.setSelectedSkillingItem(itemId);
		player.setInputHandling(new EnterAmountOfBonesToSacrifice());
		player.getPacketSender().sendString(2799, ItemDefinition.forId(itemId).getName()).sendInterfaceModel(1746, itemId, 150) .sendChatboxInterface(4429);
		player.getPacketSender().sendString(2800, "How many would you like to offer?");
	}


    public static void offerBones(final Player player, final int amount) {

		final int boneId = player.getSelectedSkillingItem();
		player.getSkillManager().stopSkilling();
		final BonesData currentBone = BonesData.forId(boneId);
		if(currentBone == null)
			return;
		player.getPacketSender().sendInterfaceRemoval();
		player.setCurrentTask(new Task(2, player, true) {

			int amountSacrificed = 0;
			@Override
			public void execute() {

				if (amountSacrificed >= amount) {
					stop();
					return;
				}
                if (currentBone == BonesData.NOTED_DRAGOB_BONE && player.getAmountDonated() < 699) {
                    // TeleportHandler.teleportPlayer(player, new Position((3087 + Misc.getRandom(10)), 3502 + Misc.getRandom(2)), player.getSpellbook().getTeleportType());
                    stop();
                    player.getPacketSender().sendMessage("RUBY+");
                }

				if (!player.getInventory().contains(boneId)) {
					player.getPacketSender().sendMessage("You have run out of " + ItemDefinition.forId(boneId).getName() + ".");
					stop();
					return;
				}

				if (currentBone == BonesData.NOTED_DRAGOB_BONE && player.getAmountDonated() < 700) {
                player.getPacketSender().sendMessage("RUBY+");
                stop();
            }
				if (player.getInteractingObject() != null) {
					player.setPositionToFace(player.getInteractingObject().getPosition().copy());
					player.getInteractingObject().performGraphic(new Graphic(624));
				}
				if(currentBone == BonesData.BIG_BONES)
					Achievements.finishAchievement(player, AchievementData.BURY_A_BIG_BONE);
				else if(currentBone == BonesData.FROSTDRAGON_BONES) {
					Achievements.doProgress(player, AchievementData.BURY_25_FROST_DRAGON_BONES);
					Achievements.doProgress(player, AchievementData.BURY_500_FROST_DRAGON_BONES);
				}
				amountSacrificed++;
				player.getInventory().delete(boneId, 1);
				player.performAnimation(new Animation(713));
				player.getSkillManager().addExperience(Skill.PRAYER, (int) (currentBone.getBuryingXP() * 1.5));
			}
			@Override
			public void stop() {
				setEventRunning(false);
				player.getPacketSender().sendMessage("You have pleased the gods with your "+(amountSacrificed == 1 ? "sacrifice" : "sacrifices")+".");
			}
		});
		TaskManager.submit(player.getCurrentTask());
	}
}
