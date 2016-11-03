package com.sohan.restfulapi.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.sohan.restfulapi.messenger.model.Message;
import com.sohan.restfulapi.messenger.model.Profile;

public class DatabaseCache {
	private static Map<Long, Message> messages = new HashMap<Long, Message>();

	private static Map<String, Profile> profiles = new HashMap<String, Profile>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

}
