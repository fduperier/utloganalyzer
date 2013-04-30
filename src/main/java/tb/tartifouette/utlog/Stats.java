package tb.tartifouette.utlog;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import tb.tartifouette.utlog.keys.UserMap;
import tb.tartifouette.utlog.keys.WeaponPerKiller;
import tb.tartifouette.utlog.keys.WhoKilledWho;
import tb.tartifouette.utlog.keys.WhoKilledWhoWithWhat;
import tb.tartifouette.utlog.values.MapResult;
import tb.tartifouette.utlog.values.UserScore;
import tb.tartifouette.utlog.values.UserStats;

public class Stats {

	public static final String BLUE = "team_CTF_blueflag";
	public static final String RED = "team_CTF_redflag";

	private final Map<WhoKilledWhoWithWhat, Integer> statsKills1 = new HashMap<WhoKilledWhoWithWhat, Integer>();

	private final Map<WhoKilledWho, UserStats> statsKills2 = new HashMap<WhoKilledWho, UserStats>();

	private final Map<WeaponPerKiller, Integer> statsWeapons = new HashMap<WeaponPerKiller, Integer>();

	private final Map<String, MapResult> statsTeamFlag = new HashMap<String, MapResult>();

	private final Map<UserMap, UserScore> statsUserMapScore = new HashMap<UserMap, UserScore>();

	public void updateEnvironmentKill(String user) {

		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore
				.setTotalEnvironment(existingScore.getTotalEnvironment() + 1);
		statsUserMapScore.put(userMap, existingScore);
	}

	public void updateSuicide(String user) {

		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore.setTotalSuicides(existingScore.getTotalSuicides() + 1);
		statsUserMapScore.put(userMap, existingScore);
	}

	public void updateUserScore(String user, int score) {

		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore.setTotalScore(existingScore.getTotalScore() + score);
		existingScore.setNbPlays(existingScore.getNbPlays() + 1);
		statsUserMapScore.put(userMap, existingScore);
	}

	public void updateTeamFlag(String team) {
		MapResult result = statsTeamFlag.get(Context.getInstance()
				.getCurrentMap());
		if (result == null) {
			result = new MapResult();
		}
		if (isBlue(team)) {
			result.setFlagBlue(result.getFlagBlue() + 1);
		} else {
			result.setFlagRed(result.getFlagRed() + 1);
		}
		statsTeamFlag.put(Context.getInstance().getCurrentMap(), result);
	}

	public boolean isBlue(String team) {
		return BLUE.equals(team);
	}

	public void updateTeamKiller(String user) {
		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore.setTeamKiller(existingScore.getTeamKiller() + 1);
		statsUserMapScore.put(userMap, existingScore);
	}

	public void updateTeamKilled(String user) {

		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore.setTeamKilled(existingScore.getTeamKilled() + 1);
		statsUserMapScore.put(userMap, existingScore);
	}

	public void updateUserFlag(String user) {

		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore.setFlags(existingScore.getFlags() + 1);
		statsUserMapScore.put(userMap, existingScore);
	}

	public void updateWhoIsKilled(String user) {

		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore.setTotalDeaths(existingScore.getTotalDeaths() + 1);
		statsUserMapScore.put(userMap, existingScore);

	}

	public void updateWhoKilled(String user) {

		UserMap userMap = new UserMap(user, Context.getInstance()
				.getCurrentMap());
		UserScore existingScore = statsUserMapScore.get(userMap);
		if (existingScore == null) {
			existingScore = new UserScore();
		}
		existingScore.setTotalFrags(existingScore.getTotalFrags() + 1);
		statsUserMapScore.put(userMap, existingScore);
	}

	public void updateWeaponPerKiller(String killer, String weapon) {
		WeaponPerKiller key = new WeaponPerKiller();
		key.setKiller(killer);
		key.setWeapon(weapon);
		Integer count = statsWeapons.get(key);
		if (count == null) {
			count = 0;
		}
		count++;
		statsWeapons.put(key, count);
	}

	public void updateWhoKilledWho(String killer, String killed) {
		WhoKilledWho key = new WhoKilledWho();
		key.setKiller(killer);
		key.setKilled(killed);
		UserStats count = statsKills2.get(key);
		if (count == null) {
			count = new UserStats();
		}
		count.setNbFrags(count.getNbFrags() + 1);
		statsKills2.put(key, count);

		key = new WhoKilledWho();
		key.setKiller(killed);
		key.setKilled(killer);
		count = statsKills2.get(key);
		if (count == null) {
			count = new UserStats();
		}
		count.setNbKilled(count.getNbKilled() + 1);
		statsKills2.put(key, count);

	}

	public void updateWhoKilledWhoWithWhat(String killer, String killed,
			String weapon) {
		WhoKilledWhoWithWhat key = new WhoKilledWhoWithWhat();
		key.setKiller(killer);
		key.setKilled(killed);
		key.setWeapon(weapon);
		Integer count = statsKills1.get(key);
		if (count == null) {
			count = 0;
		}
		count++;
		statsKills1.put(key, count);
	}

	public Map<UserMap, UserScore> getStatsUserMapScore() {
		return statsUserMapScore;
	}

	public Map<String, UserScore> getStatsUserScore() {
		Map<String, UserScore> statsUserScore = new HashMap<String, UserScore>();
		for (Entry<UserMap, UserScore> score : statsUserMapScore.entrySet()) {
			UserMap key = score.getKey();
			UserScore stats = statsUserScore.get(key.getUser());
			if (stats == null) {
				stats = new UserScore();
				statsUserScore.put(key.getUser(), stats);
			}
			stats.add(score.getValue());
		}
		return statsUserScore;
	}

	public Map<String, MapResult> getStatsTeamFlag() {
		return statsTeamFlag;
	}

	public Map<WeaponPerKiller, Integer> getStatsWeapons() {
		return statsWeapons;
	}

	public Map<WhoKilledWho, UserStats> getStatsKills2() {
		return statsKills2;
	}

	public Map<WhoKilledWhoWithWhat, Integer> getStatsKills1() {
		return statsKills1;
	}

}
