package com.xiaoyue.celestial_core.utils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ScheduleUtils {

	private static final List<Cache> tasks = new ArrayList<>();

	private final String id;
	private int currentTick;
	private final int requiredTick;
	private final Runnable action;
	public boolean running;

	public ScheduleUtils(String id, int requiredTick, Runnable action) {
		this.id = id;
		currentTick = 0;
		this.requiredTick = requiredTick;
		this.action = action;
	}

	public String getId() {
		return id;
	}

	public Runnable getAction() {
		return action;
	}

	public int getRequiredTick() {
		return requiredTick;
	}

	public int getCurrentTick() {
		return currentTick;
	}

	public void setCurrentTick(int currentTick) {
		this.currentTick = currentTick;
	}

	public boolean isRunning() {
		return running;
	}

	@Nullable
	public static ScheduleUtils getProxyFromId(String id) {
		if (tasks.isEmpty()) {
			return null;
		}
		for (Cache cache : tasks) {
			if (cache.proxy.getId().equals(id)) {
				return cache.proxy;
			}
		}
		return null;
	}

	public static void serverTick() {
		if (tasks.isEmpty()) return;
		for (Cache cache : tasks) {
			ScheduleUtils proxy = cache.getProxy();
			if (proxy.currentTick >= proxy.requiredTick) {
				proxy.action.run();
				proxy.running = false;
				cache.setUsed(true);
			} else {
				proxy.currentTick++;
				proxy.running = true;
			}
		}
		tasks.removeIf(Cache::isUsed);
	}

	public static void scheduleInTick(String id, int requiredTick, Runnable action) {
		ScheduleUtils proxy = new ScheduleUtils(id, requiredTick, action);
		tasks.add(new Cache(proxy, false));
	}

	private static class Cache {
		private final ScheduleUtils proxy;
		private boolean used;

		public Cache(ScheduleUtils proxy, boolean used) {
			this.proxy = proxy;
			this.used = used;
		}

		public ScheduleUtils getProxy() {
			return proxy;
		}

		public boolean isUsed() {
			return used;
		}

		public void setUsed(boolean isUsed) {
			this.used = isUsed;
		}
	}
}
