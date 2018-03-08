package com.ruseps.world.content.combat.strategy.impl;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.Animation;
import com.ruseps.model.Flag;
import com.ruseps.model.Graphic;
import com.ruseps.model.Locations;
import com.ruseps.model.Position;
import com.ruseps.model.Projectile;
import com.ruseps.model.Skill;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.content.combat.CombatContainer;
import com.ruseps.world.content.combat.CombatHitTask;
import com.ruseps.world.content.combat.CombatType;
import com.ruseps.world.content.combat.effect.CombatPoisonEffect.PoisonType;
import com.ruseps.world.content.combat.strategy.CombatStrategy;
import com.ruseps.world.entity.impl.Character;
import com.ruseps.world.entity.impl.npc.NPC;
import com.ruseps.world.entity.impl.npc.NPCMovementCoordinator.Coordinator;
import com.ruseps.world.entity.impl.player.Player;

public class WiseOldMan implements CombatStrategy {

	public static NPC WISE_OLD_MAN;

	public static void spawn(int id, Position pos) {
		WISE_OLD_MAN = new NPC(id, pos);
		World.register(WISE_OLD_MAN);
		if(secondForm()) {
			WISE_OLD_MAN.forceChat("GRAAAAAAAAHHHHHHHH! NOW I'M ANGRY!!!");
		}
	}
	
	public static void death(final int id, final Position pos) {
		TaskManager.submit(new Task(id == 7010 ? 40 : 1) {
			@Override
			protected void execute() {
				spawn(id == 7010 ? 7005 : 7010, pos);
				stop();
			}
		});
	}
	
	@Override
	public boolean canAttack(Character entity, Character victim) {
		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		return null;
	}
	
	int maxHealsAllowed = 0;
	boolean switched;

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		if(victim.getConstitution() <= 0 || WISE_OLD_MAN.getConstitution() <= 0) {
			return true;
		}
		if(WISE_OLD_MAN.isChargingAttack() || !victim.isPlayer()) {
			return true;
		}
		Player target = (Player)victim;
		for (Player t : Misc.getCombinedPlayerList(target)) {
			if(t == null)
				continue;
			if (Locations.goodDistance(t.getPosition(), WISE_OLD_MAN.getPosition(), 1)) {
				WISE_OLD_MAN.getCombatBuilder().setVictim(t);
				new CombatHitTask(WISE_OLD_MAN.getCombatBuilder(), new CombatContainer(WISE_OLD_MAN, t, 1, CombatType.MAGIC, true)).handleAttack();
			}
		}
		int combatType = Misc.getRandom(4);
		if (combatType == 0 || combatType == 1) {
			if(!secondForm()) {
				WISE_OLD_MAN.forceChat("Your attacks are pitiful!");
			}
			WISE_OLD_MAN.performAnimation(new Animation(WISE_OLD_MAN.getDefinition().getAttackAnimation()));
			WISE_OLD_MAN.getCombatBuilder().setContainer(new CombatContainer(WISE_OLD_MAN, target, 1, 2, CombatType.MAGIC, true));
			new Projectile(WISE_OLD_MAN, target, 1823, 44, 3, 43, 43, 0).sendProjectile();
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					int skill = Misc.getRandom(5);
					Skill skillT = Skill.forId(skill);
					Player player = (Player) target;
					int lvl = player.getSkillManager().getCurrentLevel(skillT);
					if(Misc.getRandom(100) < 20) {
						lvl -= 1 + Misc.getRandom(4);
						player.getSkillManager().setCurrentLevel(skillT, player.getSkillManager().getCurrentLevel(skillT) - lvl <= 0 ?  1 : lvl);
						target.getPacketSender().sendMessage("Your " + skillT.getFormatName() +" has been slighly drained!");
					}
					stop();
				}
			});
		} else if(combatType == 2) {
			if (maxHealsAllowed < 10 && Misc.getRandom(100) <= 8) {
				if(WISE_OLD_MAN.getConstitution() < WISE_OLD_MAN.getDefaultConstitution()) {
					WISE_OLD_MAN.performAnimation(new Animation(829));
					WISE_OLD_MAN.setConstitution(WISE_OLD_MAN.getConstitution() + 50);
					target.getPacketSender().sendMessage("The wise old man absorbs some of your attack and heals himself!");
					maxHealsAllowed++;
				}
			}
			WISE_OLD_MAN.performAnimation(new Animation(1979));
			victim.getMovementQueue().freeze(10);
			victim.performGraphic(new Graphic(369));
			WISE_OLD_MAN.getCombatBuilder().setContainer(new CombatContainer(WISE_OLD_MAN, target, 1, 2, CombatType.MAGIC, true));
		} else if(combatType == 3) {
			int distanceX = target.getPosition().getX() - WISE_OLD_MAN.getPosition().getX();
			int distanceY = target.getPosition().getY() - WISE_OLD_MAN.getPosition().getY();
			if (distanceX > 4 || distanceX < -1 || distanceY > 4 || distanceY < -1)
				combatType = 4;
			else {
					WISE_OLD_MAN.performAnimation(new Animation(WISE_OLD_MAN.getDefinition().getAttackAnimation()));
					WISE_OLD_MAN.getCombatBuilder().setContainer(new CombatContainer(WISE_OLD_MAN, target, 1, 1, CombatType.MELEE, true));
				return true;
			}
		} else if(combatType == 4) {
			for (Player t : Misc.getCombinedPlayerList(target)) {
				if(t == null)
					continue;
				WISE_OLD_MAN.performAnimation(new Animation(WISE_OLD_MAN.getDefinition().getAttackAnimation()));
				new Projectile(WISE_OLD_MAN, victim, 2701, 44, 3, 43, 43, 0).sendProjectile();
				new CombatHitTask(WISE_OLD_MAN.getCombatBuilder(), new CombatContainer(WISE_OLD_MAN, t, 1, CombatType.RANGED, true)).handleAttack();
			}
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					for (Player t : Misc.getCombinedPlayerList(target)) {
						if(t == null)
							continue;
						WISE_OLD_MAN.getCombatBuilder().setVictim(t);
						WISE_OLD_MAN.performAnimation(new Animation(WISE_OLD_MAN.getDefinition().getAttackAnimation()));
						new Projectile(WISE_OLD_MAN, victim, 2701, 44, 3, 43, 43, 0).sendProjectile();
						new CombatHitTask(WISE_OLD_MAN.getCombatBuilder(), new CombatContainer(WISE_OLD_MAN, t, 1, CombatType.RANGED, true)).handleAttack();
					}
					stop();
				}
			});
		}
		if(WISE_OLD_MAN.getConstitution() <= 2000 && !secondForm()) {
			if (switched == true) {
				return false;
			}
			//target.poisonVictim(target, PoisonType.SUPER);
			target.sendMessage("The wise only man gets angry and poisons you...");
			WISE_OLD_MAN.forceChat("This isn't the last you've seen of me...");
			switched = true;
		}
		return true;
	}
	
	public static boolean secondForm() {
		return WISE_OLD_MAN.getId() == 7010;
	}

	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 10;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}
}